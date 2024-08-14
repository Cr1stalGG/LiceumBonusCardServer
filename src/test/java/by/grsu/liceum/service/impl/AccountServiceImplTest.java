package by.grsu.liceum.service.impl;

import by.grsu.liceum.dto.account.AccountFullDto;
import by.grsu.liceum.entity.Account;
import by.grsu.liceum.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
public class AccountServiceImplTest {
    @Spy
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountServiceImpl accountService;

    @Test
    public void findByIdTest(){
        Account account = Account.builder()
                .id(1)
                .login("asdasd")
                .fatherName("asdasd")
                .lastName("asdasd")
                .phoneNumber("asdasdasd")
                .tickets(new ArrayList<>())
                .build();

        when(accountRepository.findById(account.getId())).thenReturn(account);

        AccountFullDto trueDto = accountService.findById(account.getId());

        assertEquals(trueDto.getFirstName(), account.getFirstName());
        assertNotNull(trueDto);
    }
}
