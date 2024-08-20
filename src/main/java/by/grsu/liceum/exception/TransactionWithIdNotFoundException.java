package by.grsu.liceum.exception;

public class TransactionWithIdNotFoundException extends RuntimeException {
    public TransactionWithIdNotFoundException(long id) {
        super(String.format("Transaction with id %s not found", id));
    }
}
