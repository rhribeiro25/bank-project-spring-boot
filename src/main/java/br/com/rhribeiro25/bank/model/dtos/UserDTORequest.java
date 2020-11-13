package br.com.rhribeiro25.bank.model.dtos;

import br.com.rhribeiro25.bank.model.entity.AccountEntity;
import br.com.rhribeiro25.bank.model.entity.UserEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

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
    @Size(min = 7, max = 63, message = "{name.size}")
    private String name;

    @ApiModelProperty(value = "CPF do Usuário", required=true)
    @NotBlank(message = "{cpf.not.blank}")
    @Size(min = 11, max = 14, message = "{cpf.size}")
    private String cpf;

    @ApiModelProperty(value = "Conta Associada ao Usuário", hidden = true)
    private AccountEntity account;

    public UserEntity returnUserToSave() {
        return UserEntity.builder()
                .name(name)
                .cpf(cpf)
                .build();
    }

    public UserEntity returnUserToUpdate() {
        return UserEntity.builder()
                .name(name)
                .cpf(cpf)
                .build();
    }
}
