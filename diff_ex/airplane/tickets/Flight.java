package airplane.tickets;

import java.util.Objects;

public class Flight {

    private final String destination;
    private double price;

    public Flight(String destination, double price) {
        this.destination = destination;
        this.price = price;
    }

    public double discount(int age, int loyalPoints) {
        double  priceAfterDiscount = price;
        if (age >= 60) {
            priceAfterDiscount = priceAfterDiscount - price * 0.1;
        } if (loyalPoints >= 50) {
            priceAfterDiscount = priceAfterDiscount - price * 0.05;
        }

        return priceAfterDiscount;
    }

    public String getDestination() {
        return destination;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return Objects.equals(destination, flight.destination);
    }

    @Override
    public int hashCode() {
        return Objects.hash(destination);
    }

    @Override
    public String toString() {
        return "Flight{" +
                "destination='" + destination + '\'' +
                ", price=" + price +
                '}';
    }
}
