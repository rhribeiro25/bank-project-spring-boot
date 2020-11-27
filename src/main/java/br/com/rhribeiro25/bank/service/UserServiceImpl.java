package br.com.rhribeiro25.bank.service;

import br.com.rhribeiro25.bank.model.entity.UserEntity;
import br.com.rhribeiro25.bank.model.enums.UserStatusEnum;
import br.com.rhribeiro25.bank.repository.AccountRepository;
import br.com.rhribeiro25.bank.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;


/**
 * @author Renan Ribeiro
 * @date 15/07/2020.
 */

@Service
@Slf4j
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    @Cacheable(value = "usersCache")
    public Set<UserEntity> findAll() {
        return userRepository.findByStatus(UserStatusEnum.ACTIVE);
    }

    @Override
    @Cacheable(value = "userCache")
    public UserEntity findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    @Cacheable(value = "userActiveCache")
    public UserEntity findActiveById(Long id) {
        return userRepository.findByIdAndStatus(id, UserStatusEnum.ACTIVE);
    }

    @Override
    @CacheEvict(value = { "usersCache", "userCache" , "userActiveCache"}, allEntries = true)
    public UserEntity save(UserEntity user) {
        user.getAccount().setBalance(BigDecimal.ZERO);
        user.getAccount().setCreatedAt(new Date());
        user.setCreatedAt(new Date());
        user.setStatus(UserStatusEnum.ACTIVE);
        user.getAccount().setUser(user);
        return userRepository.save(user);
    }

    @Override
    @CacheEvict(value = { "usersCache", "userCache" , "userActiveCache"}, allEntries = true)
    public UserEntity update(UserEntity currentUser, UserEntity updateUser) {
        currentUser.setUpdatedAt(new Date());
        if (updateUser.getName() != null) currentUser.setName(updateUser.getName());
        if (updateUser.getCpf() != null) currentUser.setCpf(updateUser.getCpf());
        if (updateUser.getStatus() != null) currentUser.setStatus(updateUser.getStatus());
        if (updateUser.getAccount() != null) {
            currentUser.getAccount().setUpdatedAt(new Date());
            if (updateUser.getAccount().getAccount() != null)
                currentUser.getAccount().setAccount(updateUser.getAccount().getAccount());
            if (updateUser.getAccount().getAgency() != null)
                currentUser.getAccount().setAgency(updateUser.getAccount().getAgency());
        }
        return userRepository.save(currentUser);
    }

    @Override
    @CacheEvict(value = { "usersCache", "userCache" , "userActiveCache"}, allEntries = true)
    public void delete(UserEntity user) {
        user.setStatus(UserStatusEnum.INACTIVE);
        userRepository.save(user);
    }

}
