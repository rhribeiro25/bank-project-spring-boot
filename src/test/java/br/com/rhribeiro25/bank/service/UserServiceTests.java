package br.com.rhribeiro25.bank.service;

import br.com.rhribeiro25.bank.model.entity.AccountEntity;
import br.com.rhribeiro25.bank.model.entity.UserEntity;
import br.com.rhribeiro25.bank.model.enums.UserStatusEnum;
import br.com.rhribeiro25.bank.repository.UserRepository;
import br.com.rhribeiro25.bank.utils.Formatting;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

/**
 * @author Renan Ribeiro
 * @date 15/07/2020.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class UserServiceTests {

    @MockBean
    private UserRepository userRepository;

    @Autowired
    UserService userService;

    private static final Long id = 1L;
    private static final String name = "Erick Willian";
    private static final String cpf = "077.145.105-01";
    private static final UserStatusEnum status = UserStatusEnum.ACTIVE;
    private static final String date = "2020-01-01 00:00:23.003";
    private static final String account = "0100758-1";
    private static final String agency = "2260";


    @Test
    public void testFindById() {
        when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(this.getMockUser()));
        UserEntity user = userService.findById(1L);
        assertEquals(Formatting.stringToDate_yyyy_MM_dd__HH_mm_ss("2020-01-01 00:00:23.003"), user.getCreatedAt());
        assertEquals("Erick Willian", user.getName());
        assertEquals("077.145.105-01", user.getCpf());
    }

    @Test
    public void testFindActiveById() {
        Set<UserEntity> list = new HashSet<>();
        list.add(getMockUser());
        BDDMockito.given(userRepository.findByStatus(UserStatusEnum.ACTIVE)).willReturn(list);
        Set<UserEntity> users = userService.findAll();
        UserEntity user = users.iterator().next();
        assertNotNull(user);
        assertEquals(Formatting.stringToDate_yyyy_MM_dd__HH_mm_ss("2020-01-01 00:00:23.003"), user.getCreatedAt());
        assertEquals("Erick Willian", user.getName());
        assertEquals("077.145.105-01", user.getCpf());
    }

    @Test
    public void testSave() {
        BDDMockito.given(userRepository.save(Mockito.any(UserEntity.class))).willReturn(getMockUser());
        UserEntity user = userService.save(getMockUser());
        assertNotNull(user);
        assertEquals(Formatting.stringToDate_yyyy_MM_dd__HH_mm_ss("2020-01-01 00:00:23.003"), user.getCreatedAt());
        assertEquals("Erick Willian", user.getName());
        assertEquals("077.145.105-01", user.getCpf());
    }

    private UserEntity getMockUser() {
        return UserEntity.builder()
                .id(id)
                .name(name)
                .cpf(cpf)
                .status(status)
                .createdAt(Formatting.stringToDate_yyyy_MM_dd__HH_mm_ss(date))
                .account(AccountEntity.builder()
                        .id(3L)
                        .account(account)
                        .agency(agency)
                        .createdAt(Formatting.stringToDate_yyyy_MM_dd__HH_mm_ss(date))
                        .build())
                .build();
    }
}

