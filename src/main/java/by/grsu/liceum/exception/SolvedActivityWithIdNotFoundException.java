package by.grsu.liceum.exception;

import java.util.UUID;

public class SolvedActivityWithIdNotFoundException extends RuntimeException {
    public SolvedActivityWithIdNotFoundException(UUID id) {
        super(String.format("Solved activity with id %s not found", id));
    }
}
