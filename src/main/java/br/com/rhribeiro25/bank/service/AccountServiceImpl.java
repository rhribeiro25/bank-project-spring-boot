package br.com.rhribeiro25.bank.service;

import br.com.rhribeiro25.bank.error.exception.InternalServerErrorException;
import br.com.rhribeiro25.bank.model.entity.AccountEntity;
import br.com.rhribeiro25.bank.model.entity.ReceiptEntity;
import br.com.rhribeiro25.bank.model.entity.TransactionEntity;
import br.com.rhribeiro25.bank.model.enums.TransactionTypeEnum;
import br.com.rhribeiro25.bank.repository.AccountRepository;
import br.com.rhribeiro25.bank.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * @author Renan Ribeiro
 * @date 15/07/2020.
 */

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {


    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public ReceiptEntity deposit(AccountEntity account, BigDecimal value) {
        BigDecimal interest = value.multiply(new BigDecimal("0.005"));
        BigDecimal updatedValue = interest.add(value);
        BigDecimal total = updatedValue.add(account.getBalance());
        account.setBalance(total.setScale(2, RoundingMode.HALF_EVEN));

        ReceiptEntity receipt = ReceiptEntity.builder()
                .value(updatedValue.setScale(2, RoundingMode.HALF_EVEN))
                .destinationName(account.getUser().getName())
                .build();

        TransactionEntity transaction = TransactionEntity.builder()
                .transactionType(TransactionTypeEnum.DEPOSIT)
                .transactionAt(new Date())
                .destinationAccount(account)
                .receipt(receipt)
                .build();

        receipt.setTransaction(transaction);
        if (account.getDestinationTransactions() != null)
            account.setDestinationTransactions(new HashSet<>());
        account.getDestinationTransactions().add(transaction);
        transactionRepository.save(transaction);

        return receipt;
    }

    public ReceiptEntity withdrawal(AccountEntity account, BigDecimal value) {
        BigDecimal interest = value.multiply(new BigDecimal("0.01"));
        BigDecimal updatedValue = value.add(interest);
        BigDecimal total = account.getBalance().subtract(updatedValue);

        if (total.signum() < 0) {
            throw new InternalServerErrorException("Amounts (withdrawal + interest) cannot leave the account negative!");
        }

        account.setBalance(total.setScale(2, RoundingMode.HALF_EVEN));

        ReceiptEntity receipt = ReceiptEntity.builder()
                .value(updatedValue.setScale(2, RoundingMode.HALF_EVEN))
                .originName(account.getUser().getName())
                .build();

        TransactionEntity transaction = TransactionEntity.builder()
                .transactionType(TransactionTypeEnum.WITHDRAWAL)
                .transactionAt(new Date())
                .originAccount(account)
                .receipt(receipt)
                .build();

        receipt.setTransaction(transaction);
        if (account.getOriginTransactions() != null)
            account.setOriginTransactions(new HashSet<>());
        account.getOriginTransactions().add(transaction);
        transactionRepository.save(transaction);

        return receipt;
    }

    public ReceiptEntity transfer(AccountEntity originAccount, AccountEntity destinationAccount, BigDecimal value) {
        if (value.compareTo(originAccount.getBalance()) == 1) {
            throw new InternalServerErrorException("Insufficient account balance!");
        }
        originAccount.setBalance(originAccount.getBalance().subtract(value).setScale(2, RoundingMode.HALF_EVEN));
        destinationAccount.setBalance(destinationAccount.getBalance().add(value).setScale(2, RoundingMode.HALF_EVEN));
        ReceiptEntity receipt = ReceiptEntity.builder()
                .value(value.setScale(2, RoundingMode.HALF_EVEN))
                .originName(originAccount.getUser().getName())
                .destinationName(destinationAccount.getUser().getName())
                .build();
        TransactionEntity transaction = TransactionEntity.builder()
                .transactionType(TransactionTypeEnum.TRANSFER)
                .transactionAt(new Date())
                .originAccount(originAccount)
                .destinationAccount(destinationAccount)
                .receipt(receipt)
                .build();
        receipt.setTransaction(transaction);
        originAccount.setOriginTransactions(new HashSet<>());
        destinationAccount.setDestinationTransactions(new HashSet<>());

        originAccount.getOriginTransactions().add(transaction);
//        accountRepository.save(originAccount);

        destinationAccount.getDestinationTransactions().add(transaction);
//        accountRepository.save(destinationAccount);

        transactionRepository.save(transaction);
        return receipt;
    }

    public Set<TransactionEntity> findTransactionsByAccountId(Long id) {
        AccountEntity account = accountRepository.findAccountEntityById(id);
        Set<TransactionEntity> transactions = new HashSet<>();
        if (account != null && account.getOriginTransactions() != null)
            transactions.addAll(account.getOriginTransactions());
        if (account != null && account.getDestinationTransactions() != null)
            transactions.addAll(account.getDestinationTransactions());
        return transactions;
    }

    public AccountEntity findAccountByAccountAndAgency(String account, String agency) {
        return accountRepository.findAccountEntityByAccountAndAgency(account, agency);
    }
}
