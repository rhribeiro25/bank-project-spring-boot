package br.com.rhribeiro25.bank.repository;

import br.com.rhribeiro25.bank.model.entity.UserEntity;
import br.com.rhribeiro25.bank.model.enums.UserStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * @author Renan Ribeiro
 * @date 15/07/2020.
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    public UserEntity findByIdAndStatus(Long id, UserStatusEnum status);

    public Set<UserEntity> findByStatus(UserStatusEnum status);


}
