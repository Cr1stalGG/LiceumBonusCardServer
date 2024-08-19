package by.grsu.liceum.exception;

public class InvalidBonusCountException extends RuntimeException {
    public InvalidBonusCountException(long id, int count) {
        super(String.format("Bonus with id %s has invalid count %s. Ticket creation is unreal.", id, count));
    }
}
