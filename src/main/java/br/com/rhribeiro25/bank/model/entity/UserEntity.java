package br.com.rhribeiro25.bank.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
@AllArgsConstructor
public class UserEntity {

    public UserEntity(){
        this.account = new AccountEntity();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @Column(nullable = false)
    private Date createdAt;

    @Column(nullable = false)
    @ApiModelProperty(notes = "Nome do usuário", name="name", required = false, value="string")
    private String name;

    @Column(nullable = false, unique = true)
    @ApiModelProperty(notes = "CPF do usuário", name="name", required = true, value="string")
    private String cpf;

    @EqualsAndHashCode.Exclude
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "account_id", referencedColumnName = "id", nullable = false)
    @ApiModelProperty(notes = "Conta associada ao usuário", required = true)
    private AccountEntity account;

}
