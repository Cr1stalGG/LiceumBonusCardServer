package by.grsu.liceum.exception;

import java.util.UUID;

public class TicketWithIdNotFoundException extends RuntimeException {
    public TicketWithIdNotFoundException(UUID id) {
        super(String.format("Ticket with id %s not found", id));
    }
}
