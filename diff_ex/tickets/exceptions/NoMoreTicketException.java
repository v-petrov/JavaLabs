package tickets.exceptions;

public class NoMoreTicketException extends Exception {

    private final String message;
    public NoMoreTicketException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
