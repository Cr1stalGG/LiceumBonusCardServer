package by.grsu.liceum.exception;

public class NullableGroupCreationDtoException extends RuntimeException {
    public NullableGroupCreationDtoException() {
        super("Cannot save group with nullable info");
    }
}
