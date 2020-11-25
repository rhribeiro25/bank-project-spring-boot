package br.com.rhribeiro25.bank.controller;

import br.com.rhribeiro25.bank.model.dtos.UserDTOResponse;
import br.com.rhribeiro25.bank.model.entity.AccountEntity;
import br.com.rhribeiro25.bank.model.entity.UserEntity;
import br.com.rhribeiro25.bank.model.enums.UserStatusEnum;
import br.com.rhribeiro25.bank.repository.UserRepository;
import br.com.rhribeiro25.bank.utils.Formatting;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * @author Renan Ribeiro
 * @date 15/07/2020.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @MockBean
    private UserRepository userRepository;

    private Set<UserEntity> users;
    private UserEntity user;
    private HttpEntity request;

    @TestConfiguration
    static class SpringBootTestConfig {
        @Bean
        public RestTemplateBuilder restTemplateBuilder() {
            return new RestTemplateBuilder().basicAuthentication("user", "bankProject@2020");
        }
    }

    @Before
    public void setup() {
        users = new HashSet<>();
        users.add(UserEntity.builder()
                .id(1L)
                .name("João")
                .cpf("077.145.105-01")
                .status(UserStatusEnum.ACTIVE)
                .createdAt(Formatting.stringToDate_yyyy_MM_dd__HH_mm_ss("2020-01-01 00:00:23.003"))
                .account(AccountEntity.builder()
                        .id(1L)
                        .account("0100758-1")
                        .agency("2250")
                        .createdAt(Formatting.stringToDate_yyyy_MM_dd__HH_mm_ss("2020-01-01 00:00:23.003"))
                        .build())
                .build());
        users.add(UserEntity.builder()
                .id(2L)
                .name("Maria")
                .cpf("077.145.105-02")
                .status(UserStatusEnum.ACTIVE)
                .createdAt(Formatting.stringToDate_yyyy_MM_dd__HH_mm_ss("2020-01-02 00:00:23.003"))
                .account(AccountEntity.builder()
                        .id(2L)
                        .account("0100758-2")
                        .agency("2250")
                        .createdAt(Formatting.stringToDate_yyyy_MM_dd__HH_mm_ss("2020-01-02 00:00:23.003"))
                        .build())
                .build());
        users.add(UserEntity.builder()
                .id(3L)
                .name("Erick")
                .cpf("077.145.105-03")
                .status(UserStatusEnum.ACTIVE)
                .createdAt(Formatting.stringToDate_yyyy_MM_dd__HH_mm_ss("2020-01-03 00:00:23.003"))
                .account(AccountEntity.builder()
                        .id(3L)
                        .account("0100758-3")
                        .agency("2250")
                        .createdAt(Formatting.stringToDate_yyyy_MM_dd__HH_mm_ss("2020-01-03 00:00:23.003"))
                        .build())
                .build());

        user = UserEntity.builder()
                .id(4L)
                .name("Filipe")
                .cpf("077.145.105-04")
                .status(UserStatusEnum.ACTIVE)
                .createdAt(new Date())
                .account(AccountEntity.builder()
                        .id(4L)
                        .account("0100758-4")
                        .agency("2250")
                        .createdAt(new Date())
                        .build())
                .build();

        request = new HttpEntity<>(user);
    }

    /**
     * FindById
     */
    @Test
    public void findByIdHttpStatus400() {
        BDDMockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        ResponseEntity<String> response = restTemplate.getForEntity("/api/bank/users/test", String.class);
        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(400);
    }

    @Test
    public void findByIdHttpStatus401() {
        restTemplate = restTemplate.withBasicAuth("test", "test");
        BDDMockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        ResponseEntity<String> response = restTemplate.getForEntity("/api/bank/users/1", String.class);
        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(401);
    }

    @Test
    public void findByIdHttpStatus404() {
        BDDMockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        ResponseEntity<String> response = restTemplate.getForEntity("/api/bank/users/11", String.class);
        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(404);
    }

    @Test
    public void findByIdHttpStatus405() {
        BDDMockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        ResponseEntity<String> response = restTemplate.postForEntity("/api/bank/users/1", user, String.class);
        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(405);
    }

    /**
     * Create
     */
    @Test
    public void createHttpStatus403() {
        BDDMockito.when(userRepository.save(user)).thenReturn(user);
        ResponseEntity<String> response = restTemplate.postForEntity("/api/bank/users", request, String.class);
        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(403);
    }

    @Test
    public void createHttpStatus404() {
        restTemplate = restTemplate.withBasicAuth("admin", "bankProject@2020");
        BDDMockito.when(userRepository.save(user)).thenReturn(user);
        ResponseEntity<String> response = restTemplate.postForEntity("/api/bank", request, String.class);
        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(404);
    }

    /**
     * Update
     */
    @Test
    public void updateHttpStatus405() {
        restTemplate = restTemplate.withBasicAuth("admin", "bankProject@2020");
        BDDMockito.when(userRepository.save(user)).thenReturn(user);
        BDDMockito.when(userRepository.existsById(4L)).thenReturn(true);
        ResponseEntity<UserEntity> exchange = restTemplate.exchange("/api/bank/users/4", HttpMethod.POST, request,
                UserEntity.class);
        Assertions.assertThat(exchange.getStatusCodeValue()).isEqualTo(405);
    }

    /**
     * Delete
     */
    @Test
    public void deleteHttpStatus401() {
        restTemplate = restTemplate.withBasicAuth("test", "bankProject@2020");
        BDDMockito.doNothing().when(userRepository).deleteById(4L);
        BDDMockito.when(userRepository.existsById(4L)).thenReturn(true);
        ResponseEntity<String> exchange = restTemplate.exchange("/api/bank/users/4", HttpMethod.DELETE, null,
                String.class);
        Assertions.assertThat(exchange.getStatusCodeValue()).isEqualTo(401);
    }

    @Test
    public void deleteHttpStatus403() {
        BDDMockito.doNothing().when(userRepository).deleteById(4L);
        BDDMockito.when(userRepository.existsById(4L)).thenReturn(true);
        ResponseEntity<String> exchange = restTemplate.exchange("/api/bank/users/4", HttpMethod.DELETE, null,
                String.class);
        Assertions.assertThat(exchange.getStatusCodeValue()).isEqualTo(403);
    }

    @Test
    public void deleteHttpStatus404() {
        restTemplate = restTemplate.withBasicAuth("admin", "bankProject@2020");
        BDDMockito.doNothing().when(userRepository).deleteById(4L);
        BDDMockito.when(userRepository.existsById(4L)).thenReturn(false);
        ResponseEntity<String> exchange = restTemplate.exchange("/users/4", HttpMethod.DELETE, null,
                String.class);
        Assertions.assertThat(exchange.getStatusCodeValue()).isEqualTo(404);
    }

}
