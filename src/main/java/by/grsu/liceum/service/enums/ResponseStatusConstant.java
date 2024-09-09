package by.grsu.liceum.service.enums;

public enum ResponseStatusConstant {
    RESPONSE_STATUS_BONUS_LIFE_ENDED("BONUS_LIFE_ENDED"),
    RESPONSE_STATUS_TICKET_LIFE_ENDED("TICKET_LIFE_ENDED"),
    RESPONSE_STATUS_ACCOUNT_GRADE_UP("ACCOUNT_GRADE_UP"),
    RESPONSE_STATUS_ACCOUNT_DELETED("ACCOUNT_DELETED");

    private String text;

    ResponseStatusConstant(String text){
        this.text = text;
    }

    public String getValue(){
        return this.text;
    }
}
