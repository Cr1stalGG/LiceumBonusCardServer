package by.grsu.liceum.service.enums;

public enum TransactionStatusConstant {
    TRANSACTION_STATUS_ADMIN_ACCRUAL_STATUS("ADMIN_ACCRUAL_STATUS"),
    TRANSACTION_STATUS_ACTIVITY_ACCRUAL_STATUS("ACTIVITY_ACCRUAL_STATUS"),
    TRANSACTION_STATUS_WRITE_OFF_STATUS("WRITE_OFF_STATUS"),
    TRANSACTION_STATUS_BUY_BONUS_STATUS("BUY_BONUS_STATUS");

    private String text;

    TransactionStatusConstant(String text){
        this.text = text;
    }

    public String getValue(){
        return this.text;
    }
}
