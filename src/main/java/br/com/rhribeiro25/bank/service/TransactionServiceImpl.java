package br.com.rhribeiro25.bank.service;

import br.com.rhribeiro25.bank.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author Renan Ribeiro
 * @date 15/07/2020.
 */

@Service
@Slf4j
public class TransactionServiceImpl implements TransactionService {


	@Autowired
	private AccountRepository accountRepository;

}
