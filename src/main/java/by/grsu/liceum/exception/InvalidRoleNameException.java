package by.grsu.liceum.exception;


public class InvalidRoleNameException extends RuntimeException {
    public InvalidRoleNameException(String roleConstant) {
        super(String.format("Invalid role name %s", roleConstant));
    }
}
