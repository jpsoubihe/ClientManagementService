package services;

import application.Application;
import entities.Account;
import enums.Country;
import enums.ServiceType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import repositories.AccountRepository;
import services.dtos.AccountDto;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = Application.class)
@DisplayName(value = "Account Service Test - Unit Test")
class AccountServiceTest {

    @Spy
    @InjectMocks
    AccountService accountService;

    @Mock
    AccountRepository accountRepository;

    Account account1;

    Account account2;

    @Test
    @DisplayName(value = "should return a list of Accounts")
    public void shouldGetAllAccount(){
        account1 =  Account.builder()
                .country(Country.BRASIL)
                .beginDate(LocalDate.now())
                .contract(ServiceType.BASIC)
                .email("jpsoubihe@hotmail.com")
                .password("1234")
                .username("jpsoubihe")
                .users(Arrays.asList())
                .id(Long.parseLong("1"))
                .build();

        account2 =  Account.builder()
                .country(Country.BRASIL)
                .beginDate(LocalDate.now())
                .contract(ServiceType.PREMIUM)
                .email("marinarocha15@gmail.com")
                .password("4321")
                .username("mrocha")
                .users(Arrays.asList())
                .id(Long.parseLong("2"))
                .build();

        when(accountRepository.findAll()).thenReturn(Arrays.asList(account1,account2));

        List<AccountDto> dtos = accountService.getAllAccounts();

        assertTrue(dtos.size() == 2);
        assertTrue(dtos.get(1).getEmail().equals("marinarocha15@gmail.com"));
        assertTrue(dtos.get(0).getEmail().equals("jpsoubihe@hotmail.com"));
    }
}