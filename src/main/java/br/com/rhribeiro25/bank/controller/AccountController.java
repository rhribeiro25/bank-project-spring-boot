package br.com.rhribeiro25.bank.controller;

import br.com.rhribeiro25.bank.error.exception.NotFoundException;
import br.com.rhribeiro25.bank.model.dtos.*;
import br.com.rhribeiro25.bank.model.entity.AccountEntity;
import br.com.rhribeiro25.bank.model.entity.ReceiptEntity;
import br.com.rhribeiro25.bank.model.entity.UserEntity;
import br.com.rhribeiro25.bank.service.AccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Map;
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

    @ApiOperation(value = "Depositar em conta", notes = "Este serviço deposita para uma conta existente no sistema!",
            response = ReceiptDTOResponse.class, tags = "conta")
    @PatchMapping(value = "/deposit", produces = "application/json", consumes = "application/json")
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?> deposit(@ApiParam(value = "Modelo de transação") @Valid @RequestBody DepositDTORequest transaction) {
        AccountEntity account = this.returnExistsAccount(transaction.getDestinationAccount().returnAccountEntity());
        ReceiptEntity receipt = accountService.deposit(account, transaction.getValue());
        return new ResponseEntity<>(ReceiptDTOResponse.returnDtoToShow(receipt), HttpStatus.OK);
    }

    @ApiOperation(value = "Sacar de conta", notes = "Este serviço saca de uma conta existente no sistema!",
            response = ReceiptDTOResponse.class, tags = "conta")
    @PatchMapping(value = "/withdrawal", produces = "application/json", consumes = "application/json")
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?> withdrawal(@ApiParam(value = "Modelo de transação") @Valid @RequestBody WithdrawalDTORequest transaction) {
        AccountEntity account = this.returnExistsAccount(transaction.getOriginAccount().returnAccountEntity());
        ReceiptEntity receipt = accountService.withdrawal(account, transaction.getValue());
        return new ResponseEntity<>(ReceiptDTOResponse.returnDtoToShow(receipt), HttpStatus.OK);
    }

    @ApiOperation(value = "Transferir entre contas", notes = "Este serviço transfere entre contas existentes no sistema!",
            response = ReceiptDTOResponse.class, tags = "conta")
    @PatchMapping(value = "/transfer", produces = "application/json", consumes = "application/json")
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?> transfer(@ApiParam(value = "Modelo de transação") @Valid  @RequestBody TransferDTORequest transaction) {
        AccountEntity originAccount = this.returnExistsAccount(transaction.getOriginAccount().returnAccountEntity());
        AccountEntity destinationAccount = this.returnExistsAccount(transaction.getDestinationAccount().returnAccountEntity());
        ReceiptEntity receipt = accountService.transfer(originAccount, destinationAccount, transaction.getValue());
        return new ResponseEntity<>(ReceiptDTOResponse.returnDtoToShow(receipt), HttpStatus.OK);
    }

    protected AccountEntity returnExistsAccount(AccountEntity acc) {
        AccountEntity account = accountService.findAccountByAccountAndAgency(acc.getAccount(), acc.getAgency());
        if (account == null)
            throw new NotFoundException("A conta de número " + acc.getAccount() + " e agência " + acc.getAccount() + " não pode ser encontrada.");
        return account;
    }

}
