package by.grsu.liceum.exception;

public class InvalidTicketCodeException extends RuntimeException {
    public InvalidTicketCodeException(String code) {
        super(String.format("Invalid ticket code: %s", code));
    }
}
