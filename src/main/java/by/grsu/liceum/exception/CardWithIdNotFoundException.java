package by.grsu.liceum.exception;

import java.util.UUID;

public class CardWithIdNotFoundException extends RuntimeException {
    public CardWithIdNotFoundException(UUID id) {
        super(String.format("Card with id %s not found", id));
    }
}
