package by.grsu.liceum.exception;

import by.grsu.liceum.entity.enums.RoleConstant;

public class InvalidRoleNameException extends RuntimeException {
    public InvalidRoleNameException(RoleConstant roleConstant) {
        super(String.format("Invalid role name %s", roleConstant.name()));
    }
}
