package br.com.rhribeiro25.bank.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author Renan Ribeiro
 * @date 12/11/2020.
 */

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransferDTORequest {

    private AccountUpdateDTORequest originAccount;

    private AccountUpdateDTORequest destinationAccount;

    private BigDecimal value;

}