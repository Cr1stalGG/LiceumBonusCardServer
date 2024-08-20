package by.grsu.liceum.exception;

public class InvalidTransactionStatusException extends RuntimeException {
    public InvalidTransactionStatusException(String name) {
        super(String.format("Invalid transaction status name: %s", name));
    }
}
