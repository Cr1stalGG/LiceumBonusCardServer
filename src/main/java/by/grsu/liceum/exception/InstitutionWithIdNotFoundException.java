package by.grsu.liceum.exception;

public class InstitutionWithIdNotFoundException extends RuntimeException {
    public InstitutionWithIdNotFoundException(long institutionId) {
        super(String.format("Institution with id %s not found", institutionId));
    }
}
