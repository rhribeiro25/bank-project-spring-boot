package br.com.rhribeiro25.bank.service;

import br.com.rhribeiro25.bank.model.entity.AccountEntity;
import br.com.rhribeiro25.bank.model.entity.ReceiptEntity;

import java.math.BigDecimal;

/**
 * @author Renan Ribeiro
 * @date 15/07/2020.
 */

public interface AccountService {

    public ReceiptEntity deposit(AccountEntity acc, BigDecimal value);

    public ReceiptEntity withdrawal(AccountEntity acc, BigDecimal value);

}
