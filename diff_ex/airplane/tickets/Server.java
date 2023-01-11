package airplane.tickets;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private static final int FLIGHT = 0;
    private static final int PRICE = 1;
    private static final int PORT = 8080;
    private final Set<Flight> flights;

    private final ExecutorService executor = Executors.newFixedThreadPool(5);

    public Server() {
        flights = new HashSet<>();
        loadFlights();
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.start();
    }

    private void loadFlights() {
        try (BufferedReader reader = new BufferedReader(new FileReader("flightPrices.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");
                flights.add(new Flight(tokens[FLIGHT], Double.parseDouble(tokens[PRICE])));
            }
        } catch (IOException e) {
            System.out.println("Error with reading from the file!");
        }
    }

    public void start() {
        System.out.println("Server is listening!");
        try(ServerSocket server = new ServerSocket(PORT)) {
            while (true) {
                Socket client = server.accept();
                System.out.println("Client accepted!");

                Stewardess stewardess = new Stewardess(client, flights);
                executor.submit(stewardess);

            }
        } catch (IOException e) {
            executor.shutdownNow();
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }
}
