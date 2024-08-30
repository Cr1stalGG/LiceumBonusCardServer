package by.grsu.liceum.exception;

import java.util.UUID;

public class TransactionWithIdNotFoundException extends RuntimeException {
    public TransactionWithIdNotFoundException(UUID id) {
        super(String.format("Transaction with id %s not found", id));
    }
}
