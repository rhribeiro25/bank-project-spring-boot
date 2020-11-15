package br.com.rhribeiro25.bank.controller;

import br.com.rhribeiro25.bank.error.exception.NotFoundException;
import br.com.rhribeiro25.bank.model.dtos.UserDTORequest;
import br.com.rhribeiro25.bank.model.dtos.UserDTOResponse;
import br.com.rhribeiro25.bank.model.entity.UserEntity;
import br.com.rhribeiro25.bank.model.enums.UserStatusEnum;
import br.com.rhribeiro25.bank.service.UserService;
import io.swagger.annotations.*;
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
    @ApiOperation(value = "Atualizar Usuário", notes = "Este serviço atualiza um usuário existente no sistema!",
        response = UserDTOResponse.class, tags = "Usuario")
    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @ApiParam(value = "Modelo de Usuário")
    @Valid @RequestBody  UserDTORequest user) {
        UserEntity updateUser = userService.save(user.returnUserToSave());
        return new ResponseEntity<>(UserDTOResponse.returnDtoToShow(updateUser), HttpStatus.OK);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces="application/json", consumes="application/json")
    @ApiOperation(value = "Deletar Usuário por ID", notes = "Este serviço deleta um usuário existente no sistema!",
            response = UserDTOResponse.class, tags = "Usuario")
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?> delete(@ApiParam(value = "Id do Usuário")
                                    @PathVariable("id") Long id) {
        UserEntity user = this.returnExistsUser(id);
        userService.delete(user);
        return new ResponseEntity<>("Successful to delete User!", HttpStatus.OK);
    }

    @RequestMapping(value = "/find-by-id/{id}", method = RequestMethod.GET, produces="application/json", consumes="application/json")
    @ApiOperation(value = "Buscar Usuário por ID", notes = "Este serviço busca um usuário por ID!",
            response = UserDTOResponse.class, tags = "Usuario")
    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<?> findById(@ApiParam(value = "Id do Usuário")
                                      @PathVariable("id") Long id) {
        UserEntity user = this.returnExistsUser(id);
        return new ResponseEntity<>(UserDTOResponse.returnDtoToShow(user), HttpStatus.OK);
    }

    @RequestMapping(value = "/find-all", method = RequestMethod.GET, produces="application/json", consumes="application/json")
    @ApiOperation(value = "Buscar todos Usuários", notes = "Este serviço busca todos os usuários existente no sistema!",
            response = UserDTOResponse.class, tags = "Usuario")
    @GetMapping("/find-all")
    public ResponseEntity<?> findAll() {
        Set<UserEntity> users = userService.findAll();
        this.verifyExistsUsers(users);
        return new ResponseEntity<>(UserDTOResponse.returnDtosToShow(users), HttpStatus.OK);
    }

    @RequestMapping(value = "/find-by-status/{userStatus}", method = RequestMethod.GET, produces="application/json", consumes="application/json")
    @ApiOperation(value = "Buscar Usuários por status", notes = "Este serviço busca usuários por status!",
            response = UserDTOResponse.class, tags = "Usuario")
    @GetMapping("/find-by-status/{userStatus}")
    public ResponseEntity<?> findByStatus(@ApiParam(value = "Status do Usuário")
                                          @PathVariable("userStatus") UserStatusEnum userStatus) {
        Set<UserEntity> users = userService.findByStatus(userStatus);
        this.verifyExistsUsers(users);
        return new ResponseEntity<>(UserDTOResponse.returnDtosToShow(users), HttpStatus.OK);
    }

    protected UserEntity returnExistsUser(Long id) {
        UserEntity user = userService.findById(id);
        if (user == null)
            throw new NotFoundException("User not found by ID: " + id);
        return user;
    }

    protected void verifyExistsUser(Long id) {
        if (userService.findById(id) == null)
            throw new NotFoundException("User not found by ID: " + id);
    }

    protected void verifyExistsUsers(Set<UserEntity> users) {
        if (users == null || users.size() == 0)
            throw new NotFoundException("Users not found");
    }
}
