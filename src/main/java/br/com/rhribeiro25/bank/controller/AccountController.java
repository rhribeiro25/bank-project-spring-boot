package br.com.rhribeiro25.bank.controller;

import br.com.rhribeiro25.bank.service.AccountService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    private AccountService accountService ;



}
