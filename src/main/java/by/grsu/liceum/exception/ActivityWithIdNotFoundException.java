package by.grsu.liceum.exception;

import java.util.UUID;

public class ActivityWithIdNotFoundException extends RuntimeException {
    public ActivityWithIdNotFoundException(UUID id) {
        super(String.format("Activity with id %s not found", id));
    }
}
