package by.grsu.liceum.exception;

public class AccountWithLoginNotFoundException extends RuntimeException {
    public AccountWithLoginNotFoundException(String login) {
        super(String.format("Account with login %s not found", login));
    }
}
