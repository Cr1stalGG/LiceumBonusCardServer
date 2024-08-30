package by.grsu.liceum.exception;

import java.util.UUID;

public class NotEnoughBalanceError extends RuntimeException {
    public NotEnoughBalanceError(UUID id, long balance){
        super(String.format("Not enough balance of user with id %s: %s coins", id, balance));
    }
}
