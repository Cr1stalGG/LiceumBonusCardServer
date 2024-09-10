package by.grsu.liceum.exception;

import by.grsu.liceum.entity.Role;

import java.util.List;
import java.util.UUID;

public class FoundedAccountHasNoAdminPermitionsException extends RuntimeException {
    public FoundedAccountHasNoAdminPermitionsException(UUID id, List<Role> roles) {
        super(String.format("Account with id %s has no admin permitions: %s", id, roles.toString()));
    }
}
