package by.grsu.liceum.exception;

import java.util.UUID;

public class AccountWithIdNotFoundException extends RuntimeException {
    public AccountWithIdNotFoundException(UUID id){
        super(String.format("Account with id %s not found", id));
    }
}
