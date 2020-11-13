package br.com.rhribeiro25.bank.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * @author Renan Ribeiro
 * @date 12/11/2020.
 */

@Entity
@Table(name = "accounts")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @Column(nullable = false)
    private Date createdAt;

    @Column(nullable = false, unique = true)
    private String account;

    @Column(nullable = false)
    private String agency;

    @EqualsAndHashCode.Exclude
    @ApiModelProperty(value = "Conta associada ao Usuário", hidden = true)
    @OneToOne(mappedBy = "account", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private UserEntity user;

    @EqualsAndHashCode.Exclude
    @ApiModelProperty(value = "Conta associada as Transações", hidden = true)
    @OneToMany(mappedBy = "originAccount", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Set<TransactionEntity> transactions;

}
