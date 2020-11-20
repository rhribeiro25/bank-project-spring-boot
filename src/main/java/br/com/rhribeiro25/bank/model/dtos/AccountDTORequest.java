package br.com.rhribeiro25.bank.model.dtos;

import br.com.rhribeiro25.bank.model.entity.AccountEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * @author Renan Ribeiro
 * @date 12/11/2020.
 */

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTORequest {

    @Size(max = 23, message = "{name.size}")
    private String account;

    @Size(max = 11, message = "{cpf.size}")
    private String agency;

    public AccountEntity returnAccountEntity() {
        return AccountEntity.builder()
                .account(account)
                .agency(agency)
                .build();
    }
}