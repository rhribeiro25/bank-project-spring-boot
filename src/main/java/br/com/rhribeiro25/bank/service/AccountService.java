package br.com.rhribeiro25.bank.service;

import br.com.rhribeiro25.bank.model.entity.AccountEntity;
import br.com.rhribeiro25.bank.model.entity.ReceiptEntity;
import br.com.rhribeiro25.bank.model.entity.TransactionEntity;

import java.math.BigDecimal;
import java.util.Set;

/**
 * @author Renan Ribeiro
 * @date 15/07/2020.
 */

public interface AccountService {

    public ReceiptEntity deposit(AccountEntity acc, BigDecimal value);

    public ReceiptEntity withdrawal(AccountEntity acc, BigDecimal value);

    public ReceiptEntity transfer(AccountEntity accOrigin, AccountEntity accDestination, BigDecimal value);

    public AccountEntity findAccountByAccountAndAgency(String account, String agency);

    public Set<TransactionEntity> findTransactionsByAccountId(Long id);
}
