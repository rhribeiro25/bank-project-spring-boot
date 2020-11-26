package br.com.rhribeiro25.bank.model.dtos;

import br.com.rhribeiro25.bank.model.entity.ReceiptEntity;
import br.com.rhribeiro25.bank.model.entity.TransactionEntity;
import br.com.rhribeiro25.bank.utils.Formatting;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
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
public class ReceiptDTOResponse {

    private BigDecimal realValueTransaction;

    private BigDecimal newBalance;

    private String originName;

    private String destinationName;

    private String transactionAt;

    private String transactionType;

    public static ReceiptDTOResponse returnDtoToShow(ReceiptEntity receipt) {
        return ReceiptDTOResponse.builder()
                .realValueTransaction(receipt.getValue())
                .originName(receipt.getOriginName())
                .destinationName(receipt.getDestinationName())
                .newBalance(ReceiptDTOResponse.returnNewBalance(receipt))
                .transactionType(receipt.getTransaction().getTransactionType().getDescription())
                .transactionAt(Formatting.dateToString_dd_MM_yyyy__HH_mm_ss(receipt.getTransaction().getTransactionAt()))
                .build();
    }

    public static Set<ReceiptDTOResponse> returnDtosToShow(Set<TransactionEntity> transactions) {
        Set<ReceiptDTOResponse> receiptDTOs = new HashSet<>();
        transactions.forEach(transaction ->
                receiptDTOs.add(
                        ReceiptDTOResponse.builder()
                                .realValueTransaction(transaction.getReceipt().getValue())
                                .originName(transaction.getReceipt().getOriginName())
                                .destinationName(transaction.getReceipt().getDestinationName())
                                .transactionType(transaction.getTransactionType().getDescription())
                                .transactionAt(Formatting.dateToString_dd_MM_yyyy__HH_mm_ss(transaction.getTransactionAt()))
                                .build()
                )
        );
        return receiptDTOs;
    }

    public static BigDecimal returnNewBalance(ReceiptEntity receipt) {
        BigDecimal newBalance = null;

        // Transfer
        if (receipt.getTransaction().getOriginAccount() != null && receipt.getTransaction().getDestinationAccount() != null)
            newBalance = receipt.getTransaction().getOriginAccount().getBalance();

        // Withdrawal
        else if (receipt.getTransaction().getOriginAccount() != null)
            newBalance = receipt.getTransaction().getOriginAccount().getBalance();

        // Deposit
        else if (receipt.getTransaction().getDestinationAccount() != null)
            newBalance = receipt.getTransaction().getDestinationAccount().getBalance();

        return newBalance;
    }
}
