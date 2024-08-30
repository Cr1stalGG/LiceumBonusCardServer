package by.grsu.liceum.exception;

import java.util.UUID;

public class BonusWithIdNotFoundException extends RuntimeException {
    public BonusWithIdNotFoundException(UUID id) {
        super(String.format("Bonus with id %s not found", id));
    }
}
