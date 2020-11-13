package br.com.rhribeiro25.bank.repository;

import br.com.rhribeiro25.bank.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Renan Ribeiro
 * @date 15/07/2020.
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {




}
