package by.grsu.liceum.exception;

import java.util.UUID;

public class ActivityTypeWithIdNotFoundException extends RuntimeException {
    public ActivityTypeWithIdNotFoundException(UUID id) {
        super(String.format("Activity type with id %s not found", id));
    }
}
