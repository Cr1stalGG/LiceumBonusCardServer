package by.grsu.liceum.service.enums;

public enum RoleConstant {
    ROLE_USER("ROLE_USER"),
    ROLE_TEACHER("ROLE_TEACHER"),
    ROLE_HEAD_TEACHER("ROLE_HEAD_TEACHER"),
    ROLE_SALE_UNIT("ROLE_SALE_UNIT"),
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_SUPER_ADMIN("ROLE_SUPER_ADMIN");

    private String text;

    RoleConstant(String text){
        this.text = text;
    }

    public String getValue(){
        return this.text;
    }
}
