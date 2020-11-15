package br.com.rhribeiro25.bank.service;

import br.com.rhribeiro25.bank.model.entity.UserEntity;
import br.com.rhribeiro25.bank.model.enums.UserStatusEnum;

import java.util.Set;

/**
 * @author Renan Ribeiro
 * @date 15/07/2020.
 */

public interface UserService {

    public UserEntity save(UserEntity user);

    public Set<UserEntity> findAll();

    public UserEntity findById(Long id);

    public UserEntity findActiveById(Long id);

    public UserEntity update(UserEntity UserEntity);

    public void delete(UserEntity lab);

    public boolean existsById(Long id);

    public Set<UserEntity> findByStatus(UserStatusEnum status);

}
