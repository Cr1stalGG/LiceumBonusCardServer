package by.grsu.liceum.exception;

public class SolvedActivityWithIdNotFoundException extends RuntimeException {
    public SolvedActivityWithIdNotFoundException(long id) {
        super(String.format("Solved activity with id %s not found", id));
    }
}
