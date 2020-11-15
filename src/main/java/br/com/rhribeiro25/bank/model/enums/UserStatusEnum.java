package br.com.rhribeiro25.bank.model.enums;

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
