package br.com.rhribeiro25.bank.controller;

import br.com.rhribeiro25.bank.error.exception.NotFoundException;
import br.com.rhribeiro25.bank.model.dtos.UserCreateDTORequest;
import br.com.rhribeiro25.bank.model.dtos.UserDTOResponse;
import br.com.rhribeiro25.bank.model.dtos.UserUpdateDTORequest;
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
import java.util.Set;

/**
 * @author Renan Ribeiro
 * @date 15/07/2020.
 */

@Api(value = "User API")
@RestController
@RequestMapping(value = "/api/bank/users")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "Save user", notes = "This service saves a new user to the system",
            response = UserDTOResponse.class, tags = "Users")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "", produces="application/json", consumes="application/json")
    @PreAuthorize("hasRole('ADMIN')")
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?> save(@ApiParam(value = "User model") @Valid @RequestBody UserCreateDTORequest user) {
        UserEntity newUser = userService.save(user.returnUserEntity());
        return new ResponseEntity<>(UserDTOResponse.returnDtoToShow(newUser), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update user by ID", notes = "This service updates a user to the system",
            response = UserDTOResponse.class, tags = "Users")
    @PatchMapping(value = "/{id}", produces="application/json", consumes="application/json")
    @PreAuthorize("hasRole('ADMIN')")
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?> update(@ApiParam(value = "User ID") @PathVariable("id") Long id,
                                    @ApiParam(value = "User Model") @Valid @RequestBody UserUpdateDTORequest user) {
        UserEntity currentUser = this.returnExistsUser(id);
        UserEntity updateUser = user.returnUserEntity();
        UserEntity updatedUser = userService.update(currentUser, updateUser);
        return new ResponseEntity<>(UserDTOResponse.returnDtoToShow(updatedUser), HttpStatus.OK);
    }

    @ApiOperation(value = "Delete user by ID", notes = "This service delete a user to the system",
            response = String.class, tags = "Users")
    @DeleteMapping(value = "/{id}", produces="text/plain")
    @PreAuthorize("hasRole('ADMIN')")
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?> delete(@ApiParam(value = "User ID") @PathVariable("id") Long id) {
        UserEntity user = this.returnExistsUserActive(id);
        userService.delete(user);
        return new ResponseEntity<>("Successful to delete User!", HttpStatus.OK);
    }

    @ApiOperation(value = "Find user by ID", notes = "This service finds a user in the system",
            response = UserDTOResponse.class, tags = "Users")
    @GetMapping(value = "/{id}", produces="application/json")
    public ResponseEntity<?> findById(@ApiParam(value = "User ID") @PathVariable("id") Long id) {
        UserEntity user = this.returnExistsUserActive(id);
        return new ResponseEntity<>(UserDTOResponse.returnDtoToShow(user), HttpStatus.OK);
    }

    @ApiOperation(value = "Find all users", notes = "This service finds all users in the system",
            response = UserDTOResponse.class, tags = "Users")
    @GetMapping(value = "", produces="application/json")
    public ResponseEntity<?> findAll() {
        Set<UserEntity> users = userService.findAll();
        this.verifyExistsUsers(users);
        return new ResponseEntity<>(UserDTOResponse.returnDtosToShow(users), HttpStatus.OK);
    }

    protected UserEntity returnExistsUserActive(Long id) {
        UserEntity user = userService.findActiveById(id);
        if (user == null)
            throw new NotFoundException("User of ID " + id + ", was not found.");
        return user;
    }

    protected UserEntity returnExistsUser(Long id) {
        UserEntity user = userService.findById(id);
        if (user == null)
            throw new NotFoundException("User of ID " + id + ", was not found.");
        return user;
    }

    protected void verifyExistsUsers(Set<UserEntity> users) {
        if (users == null || users.size() == 0)
            throw new NotFoundException("Users not found.");
    }
}
