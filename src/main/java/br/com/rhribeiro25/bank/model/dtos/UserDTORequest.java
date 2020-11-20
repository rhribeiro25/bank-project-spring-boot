package br.com.rhribeiro25.bank.model.dtos;

import br.com.rhribeiro25.bank.model.entity.AccountEntity;
import br.com.rhribeiro25.bank.model.entity.UserEntity;
import br.com.rhribeiro25.bank.model.enums.UserStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * @author Renan Ribeiro
 * @date 12/11/2020.
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTORequest {

    @Size(max = 127, message = "{name.size}")
    private String name;

    @Size(max = 14, message = "{cpf.size}")
    private String cpf;

    private UserStatusEnum status;

    private AccountDTORequest account;

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
