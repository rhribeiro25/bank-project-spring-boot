package br.com.rhribeiro25.bank.controller;

import br.com.rhribeiro25.bank.model.dtos.AccountDTORequest;
import br.com.rhribeiro25.bank.model.dtos.AccountDTOResponse;
import br.com.rhribeiro25.bank.model.dtos.ReceiptDTOResponse;
import br.com.rhribeiro25.bank.model.dtos.UserDTOResponse;
import br.com.rhribeiro25.bank.model.entity.AccountEntity;
import br.com.rhribeiro25.bank.model.entity.ReceiptEntity;
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
            response = UserDTOResponse.class, tags = "conta")
    @PatchMapping(value = "/deposit", produces = "application/json", consumes = "application/json")
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?> deposit(@ApiParam(value = "Modelo de conta") @Valid @RequestBody AccountDTORequest account) {
        ReceiptEntity receipt = accountService.deposit(account.returnAccountEntity(), account.getValue());
        return new ResponseEntity<>(ReceiptDTOResponse.returnDtoToShow(receipt), HttpStatus.OK);
    }

    @ApiOperation(value = "Sacar de conta", notes = "Este serviço saca de uma conta existente no sistema!",
            response = UserDTOResponse.class, tags = "conta")
    @PatchMapping(value = "/withdrawal", produces = "application/json", consumes = "application/json")
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?> withdrawal(@ApiParam(value = "Modelo de conta") @Valid @RequestBody AccountDTORequest account) {
        ReceiptEntity receipt = accountService.withdrawal(account.returnAccountEntity(), account.getValue());
        return new ResponseEntity<>(ReceiptDTOResponse.returnDtoToShow(receipt), HttpStatus.OK);
    }

}
