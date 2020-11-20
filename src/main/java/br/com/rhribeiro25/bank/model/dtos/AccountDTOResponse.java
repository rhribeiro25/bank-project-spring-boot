package br.com.rhribeiro25.bank.model.dtos;

import br.com.rhribeiro25.bank.model.entity.AccountEntity;
import br.com.rhribeiro25.bank.model.entity.UserEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Renan Ribeiro
 * @date 15/07/2020.
 */

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTOResponse {

    private Long id;

    private Date createdAt;

    private Date updatedAt;

    private String account;

    private String agency;

    @JsonBackReference("account")
    private UserDTOResponse user;

    public static AccountDTOResponse returnDtoToShow(AccountEntity account) {
        return AccountDTOResponse.builder()
                .id(account.getId())
                .createdAt(account.getCreatedAt())
                .updatedAt(account.getUpdatedAt())
                .account(account.getAccount())
                .agency(account.getAgency())
                .build();
    }

    public static Set<AccountDTOResponse> returnDtosToShow(Set<AccountEntity> accounts) {
        Set<AccountDTOResponse> accountDTOs = new HashSet<>();
        accounts.forEach(account -> accountDTOs.add(
                AccountDTOResponse.builder()
                        .id(account.getId())
                        .createdAt(account.getCreatedAt())
                        .updatedAt(account.getUpdatedAt())
                        .account(account.getAccount())
                        .agency(account.getAgency())
                        .build()
        ));
        return accountDTOs;
    }
}
