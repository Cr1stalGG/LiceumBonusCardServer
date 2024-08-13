package by.grsu.liceum.exception;

public class AccountWithIdNotFoundException extends RuntimeException {
    public AccountWithIdNotFoundException(long id){
        super(String.format("Account with id %s not found", id));
    }
}
