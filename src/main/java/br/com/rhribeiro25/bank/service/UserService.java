package br.com.rhribeiro25.bank.service;

import br.com.rhribeiro25.bank.model.entity.UserEntity;

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

    public UserEntity update(UserEntity currentUser, UserEntity updateUser);

    public void delete(UserEntity lab);

}
