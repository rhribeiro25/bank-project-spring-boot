package br.com.rhribeiro25.bank;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author Renan Ribeiro
 * @date 12/11/2020.
 */

@SpringBootApplication
@EntityScan(basePackages = { "br.com.rhribeiro25.bank.model.entity" })
@EnableJpaRepositories(basePackages = { "br.com.rhribeiro25.bank.repository" })
@Slf4j
public class BankProjectSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankProjectSpringBootApplication.class, args);
		log.info("🚀 Server ready at http://localhost:9090/api");
	}

}
