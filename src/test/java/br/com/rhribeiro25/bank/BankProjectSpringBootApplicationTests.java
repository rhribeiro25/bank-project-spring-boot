package br.com.rhribeiro25.bank;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Renan Ribeiro
 * @date 12/11/2020.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
class BankProjectSpringBootApplicationTests {

    @Test
    void contextLoads() {
    }

}
