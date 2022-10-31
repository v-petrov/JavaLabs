package tickets.loader;

import tickets.events.Concert;
import tickets.events.Event;
import tickets.events.VolleyBallMatch;
import tickets.exceptions.UnrecognisedRowException;
import tickets.loader.Importable;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TicketLoader implements Importable {

    private static final int TYPE_OF_EVENTS = 0;

    private static final int STAR_NAME = 1;
    private static final int STAR_PLACE = 2;
    private static final int STAR_DATE = 3;
    private static final int STAR_TIME = 4;
    private static final int STAR_TICKET_NUMBER = 5;
    private static final int STAR_TICKET_PRICE = 6;

    private static final int FIRST_TEAM = 1;
    private static final int SECOND_TEAM = 2;
    private static final int GAME_PLACE = 3;
    private static final int GAME_DATE = 4;
    private static final int GAME_TIME = 5;
    private static final int GAME_TICKET_NUMBER = 6;
    private static final int GAME_TICKET_PRICE = 7;


    @Override
    public Event[] importDataFromFile() throws IOException {
        Event[] events;

        try (var reader = new BufferedReader(new FileReader("dailytickets.txt"))) {
            events = new Event[rows()];
            int index = 0;
            int row = 1;

            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");
                Event event = returnEvent(trimTokens(tokens), row++);
                if (event != null) {
                    events[index++] = event;
                }
            }
        }

        return events;
    }

    private int rows() throws IOException {
        int rows = 0;
        try (var reader = new BufferedReader(new FileReader("dailytickets.txt"))) {
            while (reader.readLine() != null) {
                rows++;
            }
        }

        return rows;
    }

    private String[] trimTokens(String[] tokens) {
        for (int i = 0; i < tokens.length; i++) {
            tokens[i] = tokens[i].trim();
        }

        return tokens;
    }

    private Event returnEvent(String[] tokens, int row) {
        int typeOfEvent;
        String starName;
        String fTeam;
        String sTeam;
        String place;
        String date;
        String time;
        int numberOfTickets;
        double ticketPrice;
        Event event = null;

        try {
            typeOfEvent = Integer.parseInt(tokens[TYPE_OF_EVENTS]);
            if (typeOfEvent == 1) {
                starName = tokens[STAR_NAME];
                place = tokens[STAR_PLACE];
                date = tokens[STAR_DATE];
                time = tokens[STAR_TIME];
                numberOfTickets = Integer.parseInt(tokens[STAR_TICKET_NUMBER]);
                ticketPrice = Double.parseDouble(tokens[STAR_TICKET_PRICE]);

                event = new Concert(starName, place, date, time, numberOfTickets, ticketPrice);

            } else if (typeOfEvent == 2) {
                fTeam = tokens[FIRST_TEAM];
                sTeam = tokens[SECOND_TEAM];
                place = tokens[GAME_PLACE];
                date = tokens[GAME_DATE];
                time = tokens[GAME_TIME];
                numberOfTickets = Integer.parseInt(tokens[GAME_TICKET_NUMBER]);
                ticketPrice = Double.parseDouble(tokens[GAME_TICKET_PRICE]);

                event = new VolleyBallMatch(fTeam, sTeam, place, date, time, numberOfTickets, ticketPrice);
            }
        } catch (Exception e) {
            try {
                throw new UnrecognisedRowException("");
            } catch (UnrecognisedRowException u) {
                System.out.println("Row: " + row + ", is unrecognizable");
            }

        }

        return event;
    }
}
