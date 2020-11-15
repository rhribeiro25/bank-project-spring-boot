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

    @Column(nullable = false)
    private Date createdAt;

    @Column(nullable = false, length = 23, unique = true)
    private String account;

    @Column(nullable = false, length = 11)
    private String agency;

    @EqualsAndHashCode.Exclude
    @OneToOne(mappedBy = "account", fetch = FetchType.EAGER)
    private UserEntity user;

    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "originAccount", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Set<TransactionEntity> transactions;

}
