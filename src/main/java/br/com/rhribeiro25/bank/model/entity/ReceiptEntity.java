package br.com.rhribeiro25.bank.model.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Renan Ribeiro
 * @date 12/11/2020.
 */

@Entity
@Table(name = "receipts")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReceiptEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition="Decimal(19,2) default '0.00'")
    private BigDecimal value;

    private String originName;

    private String destinationName;

    @Column(nullable = false)
    private Date transactionAt;

    @EqualsAndHashCode.Exclude
    @OneToOne(mappedBy = "receipt", fetch = FetchType.LAZY)
    private TransactionEntity transaction;
}

