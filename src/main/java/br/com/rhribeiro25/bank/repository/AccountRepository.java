package br.com.rhribeiro25.bank.repository;

import br.com.rhribeiro25.bank.model.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Renan Ribeiro
 * @date 15/07/2020.
 */
@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {

    public AccountEntity findAccountEntityByAccountAndAgency(String account, String agency);

    public AccountEntity findAccountEntityById(Long id);

}
