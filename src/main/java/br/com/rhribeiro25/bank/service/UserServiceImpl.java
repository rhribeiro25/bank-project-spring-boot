package br.com.rhribeiro25.bank.service;

import br.com.rhribeiro25.bank.model.entity.UserEntity;
import br.com.rhribeiro25.bank.model.enums.UserStatusEnum;
import br.com.rhribeiro25.bank.repository.UserRepository;
import br.com.rhribeiro25.bank.utils.Formatting;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashSet;
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

	@Override
	public Set<UserEntity> findAll() {
		return userRepository.findByStatus(UserStatusEnum.ACTIVE);
	}

	@Override
	public UserEntity findById(Long id) {
		return userRepository.findById(id).orElse(null);
	}

	@Override
	public Set<UserEntity> findByStatus(UserStatusEnum status) {
		return userRepository.findByStatus(status);
	}

	@Override
	public UserEntity findActiveById(Long id) {
		return userRepository.findByIdAndStatus(id, UserStatusEnum.ACTIVE);
	}

	@Override
	public UserEntity save(UserEntity user) {
		user.setCreatedAt(new Date());
		user.setStatus(UserStatusEnum.ACTIVE);
		user.getAccount().setCreatedAt(new Date());
		userRepository.save(user);
		return user;
	}

	@Override
	public UserEntity update(UserEntity user) {
		userRepository.save(user);
		return user;
	}

	@Override
	public void delete(UserEntity user) {
		user.setStatus(UserStatusEnum.INACTIVE);
		userRepository.save(user);
	}

	@Override
	public boolean existsById(Long id) {
		return userRepository.existsById(id);
	}

}
