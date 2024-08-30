package by.grsu.liceum.exception;

import java.util.UUID;

public class InvalidBonusCountException extends RuntimeException {
    public InvalidBonusCountException(UUID id, int count) {
        super(String.format("Bonus with id %s has invalid count %s. Ticket creation is unreal.", id, count));
    }
}
