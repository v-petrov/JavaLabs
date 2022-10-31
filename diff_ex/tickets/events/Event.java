package tickets.events;

import tickets.exceptions.NoMoreTicketException;


public abstract class Event {

    protected String place;
    protected String date;
    protected String startHour;
    protected int numberOfTickets;
    protected double priceOfTicket;

    public Event(String place, String date, String startHour, int numberOfTickets, double priceOfTicket) {
        this.place = place;
        this.date = date;
        this.startHour = startHour;
        this.numberOfTickets = numberOfTickets;
        this.priceOfTicket = priceOfTicket;
    }

    public abstract boolean sellTickets(int num) throws NoMoreTicketException;
}
