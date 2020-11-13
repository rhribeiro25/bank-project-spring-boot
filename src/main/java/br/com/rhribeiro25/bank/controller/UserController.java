package br.com.rhribeiro25.bank.controller;

import br.com.rhribeiro25.bank.model.dtos.UserDTORequest;
import br.com.rhribeiro25.bank.model.dtos.UserDTOResponse;
import br.com.rhribeiro25.bank.model.entity.UserEntity;
import br.com.rhribeiro25.bank.service.UserService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author Renan Ribeiro
 * @date 15/07/2020.
 */

@Api(value = "User API")
@RestController
@RequestMapping("/bank/user")
@Slf4j
public class UserController {


    @Autowired
    private UserService userService;

    @ApiOperation(value = "Salvar Usuário", notes = "Salva Usuário")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "CREATED", response = UserDTOResponse.class)})
    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?> save(@ApiParam(value = "Json de Laboratório")
                                  @Valid @RequestBody UserDTORequest userDTO) {
        UserEntity user = userService.save(userDTO.returnUserToSave());
        return new ResponseEntity<>(UserDTOResponse.returnDtoToShow(user), HttpStatus.CREATED);
    }


}
