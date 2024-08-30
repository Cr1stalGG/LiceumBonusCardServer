package by.grsu.liceum.exception;

import java.util.UUID;

public class GroupWithIdNotFoundException extends RuntimeException {
    public GroupWithIdNotFoundException(UUID groupId) {
        super(String.format("Group with id %s not found", groupId));
    }
}
