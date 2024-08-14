package by.grsu.liceum.exception;

public class ActivityTypeWithIdNotFoundException extends RuntimeException {
    public ActivityTypeWithIdNotFoundException(long id) {
        super(String.format("Activity type with id %s not found", id));
    }
}
