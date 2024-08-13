package by.grsu.liceum.exception;

public class NullableAccountCreationDtoException extends RuntimeException {
    public NullableAccountCreationDtoException(){
        super("Cannot save nullable account dto");
    }
}
