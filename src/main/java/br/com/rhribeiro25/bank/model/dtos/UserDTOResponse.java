package br.com.rhribeiro25.bank.model.dtos;

import br.com.rhribeiro25.bank.model.entity.UserEntity;
import br.com.rhribeiro25.bank.utils.Formatting;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTOResponse {

    private Long id;

    private String createdAt;

    private String updatedAt;

    private String name;

    private String cpf;

    private AccountDTOResponse account;

    public static UserDTOResponse returnDtoToShow(UserEntity user) {
        return UserDTOResponse.builder()
                .id(user.getId())
                .cpf(user.getCpf())
                .name(user.getName())
                .account(AccountDTOResponse.returnDtoToShow(user.getAccount()))
                .createdAt(Formatting.dateToString_dd_MM_yyyy__HH_mm_ss(user.getCreatedAt()))
                .updatedAt(Formatting.dateToString_dd_MM_yyyy__HH_mm_ss(user.getUpdatedAt()))
                .build();
    }

    public static Set<UserDTOResponse> returnDtosToShow(Set<UserEntity> users) {
        Set<UserDTOResponse> userDTOs = new HashSet<>();
        users.forEach(user -> userDTOs.add(
                UserDTOResponse.builder()
                        .id(user.getId())
                        .cpf(user.getCpf())
                        .name(user.getName())
                        .account(AccountDTOResponse.returnDtoToShow(user.getAccount()))
                        .createdAt(Formatting.dateToString_dd_MM_yyyy__HH_mm_ss(user.getCreatedAt()))
                        .updatedAt(Formatting.dateToString_dd_MM_yyyy__HH_mm_ss(user.getUpdatedAt()))
                        .build()
        ));
        return userDTOs;
    }
}
