package by.grsu.liceum.exception;

public class BonusWithIdNotFoundException extends RuntimeException {
    public BonusWithIdNotFoundException(long id) {
        super(String.format("Bonus with id %s not found", id));
    }
}
