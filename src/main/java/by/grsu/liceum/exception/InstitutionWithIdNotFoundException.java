package by.grsu.liceum.exception;

import java.util.UUID;

public class InstitutionWithIdNotFoundException extends RuntimeException {
    public InstitutionWithIdNotFoundException(UUID institutionId) {
        super(String.format("Institution with id %s not found", institutionId));
    }
}
