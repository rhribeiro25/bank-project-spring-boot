package br.com.rhribeiro25.bank.service;

import br.com.rhribeiro25.bank.model.entity.AccountEntity;
import br.com.rhribeiro25.bank.model.entity.UserEntity;
import br.com.rhribeiro25.bank.model.enums.UserStatusEnum;
import br.com.rhribeiro25.bank.repository.UserRepository;
import br.com.rhribeiro25.bank.utils.Formatting;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * @author Renan Ribeiro
 * @date 15/07/2020.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserServiceTests {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    UserServiceImpl userService;

    private List<UserEntity> users;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        this.users = new ArrayList<>();
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
    }

    @Test
    public void findByIdSuccess() {
        when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(this.users.get(0)));
        UserEntity user = userService.findById(1L);
        assertEquals(Formatting.stringToDate_yyyy_MM_dd__HH_mm_ss("2020-01-01 00:00:23.003"), user.getCreatedAt());
        assertEquals("João", user.getName());
        assertEquals("077.145.105-01", user.getCpf());
    }

    @Test
    public void saveSuccess() {
        UserEntity user = this.users.get(0);
        userService.save(user);
        verify(userRepository, times(1)).save(user);
    }
}
