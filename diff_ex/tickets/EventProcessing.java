package tickets;

import tickets.events.Concert;
import tickets.events.Event;
import tickets.events.VolleyBallMatch;
import tickets.loader.TicketLoader;

import java.io.IOException;

public class EventProcessing {

    public static Concert[] concerts;
    public static VolleyBallMatch[] volleyBallMatches;

    public static void processTickets() throws IOException {
        int cntConcerts = 0;
        int cntVolleyBallMatches = 0;
        int indexConcert = 0;
        int indexVolleyballMatches = 0;

        TicketLoader ticketLoader = new TicketLoader();
        Event[] events = ticketLoader.importDataFromFile();

        for (Event event : events) {
            if (event == null) {
                continue;
            }

            if (event instanceof Concert) {
                cntConcerts++;

            } else if (event instanceof VolleyBallMatch) {
                cntVolleyBallMatches++;
            }
        }

        concerts = new Concert[cntConcerts];
        volleyBallMatches = new VolleyBallMatch[cntVolleyBallMatches];

        for (Event event : events) {
            if (event == null) {
                continue;
            }

            if (event instanceof Concert) {
                concerts[indexConcert++] = (Concert) event;

            } else if (event instanceof VolleyBallMatch) {
                volleyBallMatches[indexVolleyballMatches++] = (VolleyBallMatch) event;
            }
        }
    }
}
