package br.com.rhribeiro25.bank.model.dtos;

import br.com.rhribeiro25.bank.model.entity.ReceiptEntity;
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

    private BigDecimal newValue;

    private BigDecimal newBalance;

    private String originName;

    private String destinationName;

    private String transactionAt;

    private String transactionType;

    public static ReceiptDTOResponse returnDtoToShow(ReceiptEntity receipt) {
        return ReceiptDTOResponse.builder()
                .newValue(receipt.getValue())
                .originName(receipt.getOriginName())
                .destinationName(receipt.getDestinationName())
                .newBalance(receipt.getTransaction().getDestinationAccount().getBalance())
                .transactionType(receipt.getTransaction().getTransactionType().getDescription())
                .transactionAt(Formatting.dateToString_dd_MM_yyyy__HH_mm_ss(receipt.getTransactionAt()))
                .build();
    }

    public static Set<ReceiptDTOResponse> returnDtosToShow(Set<ReceiptEntity> receipts) {
        Set<ReceiptDTOResponse> receiptDTOs = new HashSet<>();
        receipts.forEach(receipt -> receiptDTOs.add(
                ReceiptDTOResponse.builder()
                        .newValue(receipt.getValue())
                        .originName(receipt.getOriginName())
                        .destinationName(receipt.getDestinationName())
                        .newBalance(receipt.getTransaction().getDestinationAccount().getBalance())
                        .transactionType(receipt.getTransaction().getTransactionType().getDescription())
                        .transactionAt(Formatting.dateToString_dd_MM_yyyy__HH_mm_ss(receipt.getTransactionAt()))
                        .build()
        ));
        return receiptDTOs;
    }
}
