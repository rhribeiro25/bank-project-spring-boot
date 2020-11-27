package br.com.rhribeiro25.bank.model.enums;

/**
 * @author Renan Ribeiro
 * @date 12/11/2020.
 */

public enum UserStatusEnum {

    ACTIVE("Ativo"),
    INACTIVE("Inativo");

    private String description;

    UserStatusEnum(String description) {
        this.description = description;
    }

    public String getDescricao() {
        return description;
    }
}
