package airplane.tickets;

import java.time.LocalDateTime;

public record Ticket(Customer customer, String destination, double price, LocalDateTime localDateTime) {

    @Override
    public String toString() {
        return "Ticket{" +
                "customer=" + customer +
                ", destination='" + destination + '\'' +
                ", price=" + price +
                ", localDateTime=" + localDateTime +
                '}';
    }
}
