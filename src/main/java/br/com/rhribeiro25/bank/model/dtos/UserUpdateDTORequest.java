package br.com.rhribeiro25.bank.model.dtos;

import br.com.rhribeiro25.bank.model.entity.AccountEntity;
import br.com.rhribeiro25.bank.model.entity.UserEntity;
import br.com.rhribeiro25.bank.model.enums.UserStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Size;

/**
 * @author Renan Ribeiro
 * @date 12/11/2020.
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateDTORequest {

    @Size(max = 127, message = "{name.size}")
    private String name;

    @Size(max = 14, message = "{cpf.size}")
    private String cpf;

    private UserStatusEnum status;

    @Valid
    private AccountUpdateDTORequest account;

    public UserEntity returnUserEntity() {
        AccountEntity account = null;
        if (this.account != null) account = this.account.returnAccountEntity();
        return UserEntity.builder()
                .status(status)
                .name(name)
                .cpf(cpf)
                .account(account)
                .build();
    }
}
