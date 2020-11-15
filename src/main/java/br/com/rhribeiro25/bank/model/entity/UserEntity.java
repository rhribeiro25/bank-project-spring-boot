package br.com.rhribeiro25.bank.model.entity;

import br.com.rhribeiro25.bank.model.enums.UserStatusEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author Renan Ribeiro
 * @date 12/11/2020.
 */

@Entity
@Table(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @Column(nullable = false)
    private Date createdAt;

    @Column(nullable = false, length = 127)
    @ApiModelProperty(notes = "Nome do usuário", name="name", required = false, value="string")
    private String name;

    @Column(nullable = false, length = 23, unique = true)
    @ApiModelProperty(notes = "CPF do usuário", name="name", required = true, value="string")
    private String cpf;

    @Column(nullable = false, length = 63)
    private UserStatusEnum status;

    @EqualsAndHashCode.Exclude
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "account_id", referencedColumnName = "id", nullable = false)
    @ApiModelProperty(notes = "Conta associada ao usuário", required = true)
    private AccountEntity account;

}
