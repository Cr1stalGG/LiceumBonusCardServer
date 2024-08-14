package by.grsu.liceum.exception;

public class CardWithIdNotFoundException extends RuntimeException {
    public CardWithIdNotFoundException(long id) {
        super(String.format("Card with id %s not found", id));
    }
}
