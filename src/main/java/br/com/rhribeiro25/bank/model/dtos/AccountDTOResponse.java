package br.com.rhribeiro25.bank.model.dtos;

import br.com.rhribeiro25.bank.model.entity.AccountEntity;
import br.com.rhribeiro25.bank.utils.Formatting;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
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

    private String createdAt;

    private String updatedAt;

    private String account;

    private BigDecimal balance;

    private String agency;

    public static AccountDTOResponse returnDtoToShow(AccountEntity account) {
        return AccountDTOResponse.builder()
                .id(account.getId())
                .agency(account.getAgency())
                .account(account.getAccount())
                .balance(account.getBalance())
                .createdAt(Formatting.dateToString_dd_MM_yyyy__HH_mm_ss(account.getCreatedAt()))
                .updatedAt(Formatting.dateToString_dd_MM_yyyy__HH_mm_ss(account.getUpdatedAt()))
                .build();
    }

    public static Set<AccountDTOResponse> returnDtosToShow(Set<AccountEntity> accounts) {
        Set<AccountDTOResponse> accountDTOs = new HashSet<>();
        accounts.forEach(account -> accountDTOs.add(
                AccountDTOResponse.builder()
                        .id(account.getId())
                        .agency(account.getAgency())
                        .account(account.getAccount())
                        .balance(account.getBalance())
                        .createdAt(Formatting.dateToString_dd_MM_yyyy__HH_mm_ss(account.getCreatedAt()))
                        .updatedAt(Formatting.dateToString_dd_MM_yyyy__HH_mm_ss(account.getUpdatedAt()))
                        .build()
        ));
        return accountDTOs;
    }
}
