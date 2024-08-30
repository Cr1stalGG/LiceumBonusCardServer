package by.grsu.liceum.exception;

public class InvalidActivityCodeException extends RuntimeException {
    public InvalidActivityCodeException(String activityCode, String requestCode) {
        super(String.format("Invalid activity code: {expected: %s, taken: %s}", activityCode, requestCode));
    }
}
