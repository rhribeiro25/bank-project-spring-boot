package br.com.rhribeiro25.bank.model.enums;

/**
 * @author Renan Ribeiro
 * @date 12/11/2020.
 */

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
