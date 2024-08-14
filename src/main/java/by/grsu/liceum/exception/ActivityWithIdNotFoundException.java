package by.grsu.liceum.exception;

public class ActivityWithIdNotFoundException extends RuntimeException {
    public ActivityWithIdNotFoundException(long id) {
        super(String.format("Activity with id %s not found", id));
    }
}
