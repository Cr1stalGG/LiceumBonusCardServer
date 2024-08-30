package by.grsu.liceum.exception;

public class CountOfMembersIsExpiredException extends RuntimeException {
    public CountOfMembersIsExpiredException() {
        super("Count of members is expired");
    }
}
