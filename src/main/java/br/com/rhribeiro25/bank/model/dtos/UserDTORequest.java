package br.com.rhribeiro25.bank.model.dtos;

import br.com.rhribeiro25.bank.model.entity.AccountEntity;
import br.com.rhribeiro25.bank.model.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Renan Ribeiro
 * @date 12/11/2020.
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTORequest {

    @NotBlank(message = "{name.not.blank}")
    @Size(max = 127, message = "{name.size}")
    private String name;

    @NotBlank(message = "{cpf.not.blank}")
    @Size(max = 14, message = "{cpf.size}")
    private String cpf;

    @NotNull(message = "{type.not.null}")
    private AccountDTORequest account;

    public UserEntity returnUserEntity() {
        AccountEntity ac = account.returnAccountEntity();
        return UserEntity.builder()
                .name(name)
                .cpf(cpf)
                .account(ac)
                .build();
    }
}
