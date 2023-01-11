package airplane.tickets;

import java.io.*;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.*;

public class Stewardess implements Runnable {
    private static final int FIRST_NAME = 0;
    private static final int SECOND_NAME = 1;
    private static final int AGE = 2;
    private final Socket client;
    private final Set<Flight> flights;

    private static final List<Customer> customers;

    static {
        customers = loadCustomers();
    }

    public Stewardess(Socket client, Set<Flight> flights) {
        this.client = client;
        this.flights = flights;
    }


    @Override
    public void run() {
        try (PrintStream out = new PrintStream(client.getOutputStream());
             Scanner in = new Scanner(client.getInputStream())) {

            out.println("Hello client! Please tell what is your desired destination!");
            String destination = in.nextLine();
            Flight desiredFlight = findDestination(destination);
            if (desiredFlight != null) {
                out.println("Your destination is: " + desiredFlight.getDestination());
            } else {
                out.println("Sorry but we don't provide flights to that destination! Next client :|");
                return;
            }

            out.println("How many tickets do you want to buy!");
            int quantity = Integer.parseInt(in.nextLine());

            Ticket ticket = buyTicket(in, out, desiredFlight, quantity);
            out.println("Thank you, here is your ticket x" + quantity + ": " + ticket + ", safe flight!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Flight findDestination(String destination) {
        for (Flight flight : flights) {
            if (flight.getDestination().equals(destination)) {
                return flight;
            }
        }

        return null;
    }

    private Ticket buyTicket(Scanner in, PrintStream out, Flight flight, int quantity) {
        out.println("Please identify yourself!");
        String customer = in.nextLine();

        Customer newCustomer;
        synchronized (Objects.requireNonNull(customers)) {
            newCustomer = customerInIt(customer);
            if (newCustomer == null) {
                newCustomer = creatingCustomer(customer);
                customers.add(newCustomer);
                saveCustomers();
                out.println("This is your first time flying with us, so from now on you can start collecting loyal points for future discounts!");
            }
            out.println("Currently, you have: " + newCustomer.getLoyalPoints() + " loyal points!");
        }

        int ageOfCustomer = newCustomer.getAge();
        int loyalPointsOfCustomer = newCustomer.getLoyalPoints();

        double priceAfterDiscount = flight.discount(ageOfCustomer, loyalPointsOfCustomer);
        if (loyalPointsOfCustomer >= 50) {
            newCustomer.chargeLoyalPoints();
        }


        if (priceAfterDiscount != flight.getPrice()) {
            out.println("Okay, that will be " + quantity * priceAfterDiscount + " after the discount");
        } else {
            out.println("Okay, that will be " + quantity * priceAfterDiscount);
        }

        double money;
        while (true) {
            money = Double.parseDouble(in.nextLine());
            if (money == quantity * priceAfterDiscount) {
                newCustomer.addLoyalPoints();
                return new Ticket(newCustomer, flight.getDestination(), priceAfterDiscount, LocalDateTime.now());
            } else {
                out.println("Please give me the correct amount!");
            }
        }
    }

    private Customer creatingCustomer(String customer) {
        String[] tokens = customer.split(",");
        return new Customer(tokens[0], tokens[1], Integer.parseInt(tokens[2]), 0);
    }

    private Customer customerInIt(String currCustomer) {
        String[] tokens = currCustomer.split(",");
        assert customers != null;
        for (Customer customer : customers) {
            if (customer.getFirstName().equals(tokens[FIRST_NAME]) && customer.getSecondName().equals(tokens[SECOND_NAME]) && customer.getAge() == Integer.parseInt(tokens[AGE])) {
                return customer;
            }
        }
        return null;
    }

    private void saveCustomers() {
        try (ObjectOutputStream obj = new ObjectOutputStream(new FileOutputStream("customers.bin"))) {
            obj.writeObject(customers);

        } catch (FileNotFoundException e) {
            System.out.println("File couldn't be found!");
            e.printStackTrace();

        } catch (IOException e) {
            System.out.println("Error with IO!");
            e.printStackTrace();
        }
    }
    @SuppressWarnings("unchecked")
    private static List<Customer> loadCustomers() {
        try (ObjectInputStream obj = new ObjectInputStream(new FileInputStream("customers.bin"))) {
            return (List<Customer>) obj.readObject();

        } catch (ClassNotFoundException e) {
            System.out.println("Class couldn't be found!");
            e.printStackTrace();

        } catch (FileNotFoundException e) {
            System.out.println("File couldn't be found!");
            e.printStackTrace();

        } catch (IOException e) {
            System.out.println("Error with IO!");
            e.printStackTrace();
        }

        return null;
    }
}