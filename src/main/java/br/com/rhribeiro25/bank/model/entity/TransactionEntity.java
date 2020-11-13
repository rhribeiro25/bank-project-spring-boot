package br.com.rhribeiro25.bank.model.entity;

import br.com.rhribeiro25.bank.model.enums.TransactionTypeEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
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
@AllArgsConstructor
public class TransactionEntity {

    public TransactionEntity(){
        this.receipt = new ReceiptEntity();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @Column(nullable = false)
    private Date createdAt;

    @Column(nullable = false)
    private TransactionTypeEnum transactionType;

    @EqualsAndHashCode.Exclude
    @ApiModelProperty(value = "Transação associada a Conta origem", hidden = true)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="origin_account_id", referencedColumnName = "id")
    private AccountEntity originAccount;

    @EqualsAndHashCode.Exclude
    @ApiModelProperty(value = "Transação associada a Conta destino", hidden = true)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="destination_account_id", referencedColumnName = "id")
    private AccountEntity destinationAccount;

    @EqualsAndHashCode.Exclude
    @ApiModelProperty(value = "Transação associada ao recibo", hidden = true)
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "receipt_id", referencedColumnName = "id", nullable = false)
    private ReceiptEntity receipt;

}
