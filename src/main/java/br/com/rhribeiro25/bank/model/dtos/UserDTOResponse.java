package br.com.rhribeiro25.bank.model.dtos;

import br.com.rhribeiro25.bank.model.entity.AccountEntity;
import br.com.rhribeiro25.bank.model.entity.UserEntity;
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
public class UserDTOResponse {

    private Long id;

    private Date createdAt;

    private String name;

    private String cpf;

    private AccountEntity account;

    public static UserDTOResponse returnDtoToShow(UserEntity user) {
        return UserDTOResponse.builder()
                .id(user.getId())
                .createdAt(user.getCreatedAt())
                .name(user.getName())
                .cpf(user.getCpf())
                .build();
    }

    public static Set<UserDTOResponse> returnDtosToShow(Set<UserEntity> users) {
        Set<UserDTOResponse> userDTOs = new HashSet<>();
        users.forEach(user -> userDTOs.add(
                UserDTOResponse.builder()
                        .id(user.getId())
                        .createdAt(user.getCreatedAt())
                        .name(user.getName())
                        .cpf(user.getCpf())
                        .build()
        ));
        return userDTOs;
    }
}
