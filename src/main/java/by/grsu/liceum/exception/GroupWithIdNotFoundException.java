package by.grsu.liceum.exception;

public class GroupWithIdNotFoundException extends RuntimeException {
    public GroupWithIdNotFoundException(long groupId) {
        super(String.format("Group with id %s not found", groupId));
    }
}
