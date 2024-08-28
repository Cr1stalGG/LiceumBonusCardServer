package by.grsu.liceum.exception;

public class InvalidPermissionsException extends RuntimeException {
    public InvalidPermissionsException() {
        super("U have no permissions to do this option");
    }
}
