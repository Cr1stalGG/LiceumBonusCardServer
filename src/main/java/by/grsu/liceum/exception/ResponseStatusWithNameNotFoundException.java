package by.grsu.liceum.exception;

public class ResponseStatusWithNameNotFoundException extends RuntimeException {
    public ResponseStatusWithNameNotFoundException(String responseStatusName) {
        super(String.format("Response status with name '%s' not found", responseStatusName));
    }
}
