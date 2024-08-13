package by.grsu.liceum.exception;

public class NotEnoughBalanceError extends RuntimeException {
    public NotEnoughBalanceError(long id, long balance){
        super(String.format("Not enough balance of user with id %s: %s coins", id, balance));
    }
}
