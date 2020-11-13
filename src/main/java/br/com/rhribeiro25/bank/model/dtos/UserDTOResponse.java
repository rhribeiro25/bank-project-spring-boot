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
import java.util.HashSet;
import java.util.Set;

/**
 * @author Renan Ribeiro
 * @date 15/07/2020.
 */

@Getter
@Builder
@ApiModel(value = "Resposta para usuário")
public class UserDTOResponse {

    private Long id;

    private Date createdAt;

    @ApiModelProperty(value = "Nome do Exame", required=true)
    @NotBlank(message = "{name.not.blank}")
    @Size(min = 7, max = 63, message = "{name.size}")
    private String name;


    @ApiModelProperty(value = "CPF do Usuário", required=true)
    @NotBlank(message = "{cpf.not.blank}")
    @Size(min = 11, max = 14, message = "{cpf.size}")
    private String cpf;

    @ApiModelProperty(value = "Conta Associada ao Usuário", hidden = true)
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
