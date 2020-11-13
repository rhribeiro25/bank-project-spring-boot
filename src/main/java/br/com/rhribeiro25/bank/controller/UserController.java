package br.com.rhribeiro25.bank.controller;

import br.com.rhribeiro25.bank.model.dtos.UserDTORequest;
import br.com.rhribeiro25.bank.model.dtos.UserDTOResponse;
import br.com.rhribeiro25.bank.model.entity.UserEntity;
import br.com.rhribeiro25.bank.service.UserService;
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

/**
 * @author Renan Ribeiro
 * @date 15/07/2020.
 */

@Api(value = "User API")
@RestController
@RequestMapping("/bank/users")
@Slf4j
public class UserController {


    @Autowired
    private UserService userService;

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces="application/json", consumes="application/json")
    @ApiOperation(value = "Salvar Usuário", notes = "Este serviço salva um novo usuário no sistema!",
        response = UserDTOResponse.class, tags = "Usuario")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?> save(@ApiParam(value = "Modelo de Usuário") @Valid @RequestBody UserDTORequest user) {
        UserEntity newUser = userService.save(user.returnUserToSave());
        return new ResponseEntity<>(UserDTOResponse.returnDtoToShow(newUser), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces="application/json", consumes="application/json")
    @ApiOperation(value = "Salvar Usuário", notes = "Este serviço atualiza um usuário existente no sistema!",
        response = UserDTOResponse.class, tags = "Usuario")
    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @ApiParam(value = "Modelo de Usuário")
    @Valid @RequestBody  UserDTORequest user) {
        UserEntity updateUser = userService.save(user.returnUserToSave());
        return new ResponseEntity<>(UserDTOResponse.returnDtoToShow(updateUser), HttpStatus.CREATED);
    }
}
