package br.com.rhribeiro25.bank.model.entity;

import br.com.rhribeiro25.bank.model.enums.TransactionTypeEnum;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Renan Ribeiro
 * @date 12/11/2020.
 */

@Entity
@Table(name = "transactions")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Date transactionAt;

    @Column(nullable = false)
    private TransactionTypeEnum transactionType;

    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name="origin_account_id", referencedColumnName = "id")
    private AccountEntity originAccount;

    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name="destination_account_id", referencedColumnName = "id")
    private AccountEntity destinationAccount;

    @EqualsAndHashCode.Exclude
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "receipt_id", referencedColumnName = "id", nullable = false)
    private ReceiptEntity receipt;

}
