package br.com.rhribeiro25.bank.model.dtos;

import br.com.rhribeiro25.bank.model.entity.AccountEntity;
import br.com.rhribeiro25.bank.model.entity.UserEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author Renan Ribeiro
 * @date 12/11/2020.
 */

@Getter
@Builder
@ApiModel(value = "Requisição de Usuário")
public class UserDTORequest {

    @ApiModelProperty(value = "Nome do Usuário", required=true)
    @NotBlank(message = "{name.not.blank}")
    @Size(max = 127, message = "{name.size}")
    private String name;

    @ApiModelProperty(value = "CPF do Usuário", required=true)
    @NotBlank(message = "{cpf.not.blank}")
    @Size(max = 14, message = "{cpf.size}")
    private String cpf;

    @ApiModelProperty(value = "Conta Associada ao Usuário")
    private AccountEntity account;

    public UserEntity returnUserToSave() {
        return UserEntity.builder()
                .name(name)
                .cpf(cpf)
                .account(account)
                .build();
    }

    public UserEntity returnUserToUpdate() {
        return UserEntity.builder()
                .name(name)
                .cpf(cpf)
                .account(account)
                .build();
    }
}
