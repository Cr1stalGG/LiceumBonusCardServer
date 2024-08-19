package by.grsu.liceum.exception;

public class TicketWithIdNotFoundException extends RuntimeException {
    public TicketWithIdNotFoundException(long id) {
        super(String.format("Ticket with id %s not found", id));
    }
}
