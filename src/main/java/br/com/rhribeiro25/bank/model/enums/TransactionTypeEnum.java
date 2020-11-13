package br.com.rhribeiro25.bank.model.enums;

public enum TransactionTypeEnum {
    TRANSFER("Transferência"),
    DEPOSIT("Depósito"),
    WITHDRAWAL("Saque");

    private String description;

    TransactionTypeEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
