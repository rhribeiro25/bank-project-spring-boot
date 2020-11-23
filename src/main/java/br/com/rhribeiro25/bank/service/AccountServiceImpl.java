package br.com.rhribeiro25.bank.service;

import br.com.rhribeiro25.bank.error.exception.InternalServerErrorException;
import br.com.rhribeiro25.bank.error.exception.NotFoundException;
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

	public ReceiptEntity deposit(AccountEntity acc, BigDecimal value){
		AccountEntity account = accountRepository.findAccountEntityByAccountAndAgency(acc.getAccount(), acc.getAgency());
		BigDecimal interest = value.multiply(new BigDecimal("0.005"));
		BigDecimal updatedValue = interest.add(value);
		BigDecimal total = updatedValue.add(account.getBalance());
		account.setBalance(total.setScale(2, RoundingMode.HALF_EVEN));

		ReceiptEntity receipt = ReceiptEntity.builder()
				.value(updatedValue.setScale(2, RoundingMode.HALF_EVEN))
				.transactionAt(new Date())
				.destinationName(account.getUser().getName())
				.build();

		TransactionEntity transaction = TransactionEntity.builder()
				.transactionType(TransactionTypeEnum.DEPOSIT)
				.createdAt(new Date())
				.destinationAccount(account)
				.receipt(receipt)
				.build();

		receipt.setTransaction(transaction);
		account.setTransactions(new HashSet<>());
		transactionRepository.save(transaction);

		account.getTransactions().add(transaction);
		accountRepository.save(account);
		return receipt;
	}

	public ReceiptEntity withdrawal(AccountEntity acc, BigDecimal value){
		AccountEntity account = accountRepository.findAccountEntityByAccountAndAgency(acc.getAccount(), acc.getAgency());
		BigDecimal interest = value.multiply(new BigDecimal("0.01"));
		BigDecimal updatedValue = value.subtract(interest);
		BigDecimal total = account.getBalance().subtract(updatedValue);

		if(total.signum() < 0){
			throw new InternalServerErrorException("Os valores (Saque + juros), não podem negativar a conta!");
		}

		account.setBalance(total.setScale(2, RoundingMode.HALF_EVEN));

		ReceiptEntity receipt = ReceiptEntity.builder()
				.value(updatedValue.setScale(2, RoundingMode.HALF_EVEN))
				.transactionAt(new Date())
				.originName(account.getUser().getName())
				.build();

		TransactionEntity transaction = TransactionEntity.builder()
				.transactionType(TransactionTypeEnum.WITHDRAWAL)
				.createdAt(new Date())
				.originAccount(account)
				.receipt(receipt)
				.build();

		receipt.setTransaction(transaction);
		account.setTransactions(new HashSet<>());
		transactionRepository.save(transaction);

		account.getTransactions().add(transaction);
		accountRepository.save(account);
		return receipt;
	}
}
