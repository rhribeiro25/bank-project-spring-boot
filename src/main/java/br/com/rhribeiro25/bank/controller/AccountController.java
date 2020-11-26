package br.com.rhribeiro25.bank.controller;

import br.com.rhribeiro25.bank.error.exception.NotFoundException;
import br.com.rhribeiro25.bank.model.dtos.DepositDTORequest;
import br.com.rhribeiro25.bank.model.dtos.ReceiptDTOResponse;
import br.com.rhribeiro25.bank.model.dtos.TransferDTORequest;
import br.com.rhribeiro25.bank.model.dtos.WithdrawalDTORequest;
import br.com.rhribeiro25.bank.model.entity.AccountEntity;
import br.com.rhribeiro25.bank.model.entity.ReceiptEntity;
import br.com.rhribeiro25.bank.model.entity.TransactionEntity;
import br.com.rhribeiro25.bank.service.AccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

/**
 * @author Renan Ribeiro
 * @date 15/07/2020.
 */

@Api(value = "Account API")
@RestController
@RequestMapping("/api/bank/account")
@Slf4j
public class AccountController {

    @Autowired
    private AccountService accountService;

    @ApiOperation(value = "Deposit into account", notes = "This service deposits into an existing account in the system",
            response = ReceiptDTOResponse.class, tags = "Accounts")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/deposit", produces = "application/json", consumes = "application/json")
    @PreAuthorize("hasRole('ADMIN')")
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?> deposit(@ApiParam(value = "Transaction model") @Valid @RequestBody DepositDTORequest transaction) {
        AccountEntity account = this.returnExistsAccount(transaction.getDestinationAccount().returnAccountEntity());
        ReceiptEntity receipt = accountService.deposit(account, transaction.getValue());
        return new ResponseEntity<>(ReceiptDTOResponse.returnDtoToShow(receipt), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Withdraw from account", notes = "This service withdraws from an existing account in the system",
            response = ReceiptDTOResponse.class, tags = "Accounts")
    @PostMapping(value = "/withdrawal", produces = "application/json", consumes = "application/json")
    @PreAuthorize("hasRole('ADMIN')")
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?> withdrawal(@ApiParam(value = "Transaction model") @Valid @RequestBody WithdrawalDTORequest transaction) {
        AccountEntity account = this.returnExistsAccount(transaction.getOriginAccount().returnAccountEntity());
        ReceiptEntity receipt = accountService.withdrawal(account, transaction.getValue());
        return new ResponseEntity<>(ReceiptDTOResponse.returnDtoToShow(receipt), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Transfer between accounts", notes = "This service transfers between existing accounts in the system",
            response = ReceiptDTOResponse.class, tags = "Accounts")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/transfer", produces = "application/json", consumes = "application/json")
    @PreAuthorize("hasRole('ADMIN')")
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?> transfer(@ApiParam(value = "Transaction model") @Valid  @RequestBody TransferDTORequest transaction) {
        AccountEntity originAccount = this.returnExistsAccount(transaction.getOriginAccount().returnAccountEntity());
        AccountEntity destinationAccount = this.returnExistsAccount(transaction.getDestinationAccount().returnAccountEntity());
        ReceiptEntity receipt = accountService.transfer(originAccount, destinationAccount, transaction.getValue());
        return new ResponseEntity<>(ReceiptDTOResponse.returnDtoToShow(receipt), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Find account transactions", notes = "This service finds all account transactions in the system",
            response = ReceiptDTOResponse.class, tags = "Accounts")
    @ResponseStatus(HttpStatus.CREATED)
    @GetMapping(value = "/{id}/transactions", produces = "application/json")
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?> findTransactions(@ApiParam(value = "Account ID") @PathVariable("id") Long id) {
        Set<TransactionEntity> transactions = this.returnExistsTransactions(id);
        return new ResponseEntity<>(ReceiptDTOResponse.returnDtosToShow(transactions), HttpStatus.OK);
    }

    protected AccountEntity returnExistsAccount(AccountEntity acc) {
        AccountEntity account = accountService.findAccountByAccountAndAgency(acc.getAccount(), acc.getAgency());
        if (account == null) {
            log.warn("Account of number " + acc.getAccount() + " and agency " + acc.getAccount() + " was not found.");
            throw new NotFoundException("Account of number " + acc.getAccount() + " and agency " + acc.getAccount() + " was not found.");
        }
        return account;
    }

    protected Set<TransactionEntity> returnExistsTransactions(Long id) {
        Set<TransactionEntity> transactions = accountService.findTransactionsByAccountId(id);
        if (transactions == null) {
            log.warn("Account transactions of ID " + id + " were not found.");
            throw new NotFoundException("Account transactions of ID " + id + " were not found.");
        }
        return transactions;
    }

}
