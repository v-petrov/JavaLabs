package tickets.events;

import tickets.exceptions.NoMoreTicketException;

public class Concert extends Event {

    protected final String starName;

    public Concert(String starName, String place, String date, String startHour, int numberOfTickets, double priceOfTicket) {
        super(place, date, startHour, numberOfTickets, priceOfTicket);
        this.starName = starName;
    }

    @Override
    public boolean sellTickets(int num) throws NoMoreTicketException {
        if (numberOfTickets - num < 0) {
            throw new NoMoreTicketException("The requested tickets are more than what we have available!");
        }

        numberOfTickets -= num;
        System.out.println("Tickets sold: " + num + ", Available ones: " + numberOfTickets);
        return true;
    }
}
