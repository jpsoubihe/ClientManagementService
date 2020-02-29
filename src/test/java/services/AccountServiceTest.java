package services;

import entities.Account;
import enums.Country;
import enums.ServiceType;
import exceptions.InvalidAccountException;
import exceptions.InvalidContractException;
import exceptions.InvalidCountryException;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import repositories.AccountRepository;
import services.dtos.AccountDto;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@DisplayName("Unit Test - Account Service")
@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {


    @Spy
    @InjectMocks
    AccountService accountService;

    @Mock
    AccountRepository accountRepository;

    Account account1 = Account.builder()
            .id(Long.parseLong("1234"))
            .beginDate(LocalDate.now())
            .email("jp.soubihe@gmail.com")
            .username("jp.soubihe")
            .password("1234")
            .country(Country.BRASIL)
            .contract(ServiceType.BASIC)
            .users(Arrays.asList())
            .build();

    AccountDto accountDto1 = AccountDto.builder()
            .beginDate(account1.getBeginDate())
            .contract(account1.getContract().toString())
            .email(account1.getEmail())
            .password(account1.getPassword())
            .username(account1.getUsername())
            .country(account1.getCountry().toString())
            .users(account1.getUsers())
            .build();


    Account account2 = Account.builder()
            .id(Long.parseLong("4321"))
            .beginDate(LocalDate.now())
            .email("marinarocha15@gmail.com")
            .username("marinaRocha")
            .password("4321")
            .country(Country.EUA)
            .contract(ServiceType.BASIC)
            .users(Arrays.asList())
            .build();

    AccountDto accountDto2 = AccountDto.builder()
            .beginDate(account2.getBeginDate())
            .contract(account2.getContract().toString())
            .email(account2.getEmail())
            .password(account2.getPassword())
            .username(account2.getUsername())
            .country(account2.getCountry().toString())
            .users(account2.getUsers())
            .build();

    List<Account> accounts = Arrays.asList(account1, account2);

    @Test
    @DisplayName("Should get all the accounts stored on account repository")
    public void getAllAccountsSuccessfullyTest() {

        when(accountRepository.findAll())
                .thenReturn(accounts);

        assertThat(accountService.getAllAccounts())
                .isNotNull()
                .isNotEmpty()
                .isEqualTo(Arrays.asList(accountDto1, accountDto2));

    }

    @Test
    @DisplayName("Should get an empty list if there's no stored on account repository")
    public void getAllAccountsEmptySuccessfullyTest() {

        when(accountRepository.findAll())
                .thenReturn(emptyList());

        assertThat(accountService.getAllAccounts())
                .isNotNull()
                .isEmpty();
    }

    @Test
    @DisplayName("Should get a specific account on account repository given by its username")
    public void getAccountSuccessfullyTest() {
        when(accountRepository
                .findByUsername(account1.getUsername()))
                .thenReturn(java.util.Optional.ofNullable(account1));

        assertThat(accountService
                .getAccount(account1.getUsername())
        )
                .isNotNull()
                .isInstanceOf(AccountDto.class)
                .isEqualTo(accountDto1);
    }

    @Test
    @DisplayName("Should throw InvalidAccountException if the respective account does not exist")
    public void getNonExistedAccountTest() {
        when(accountRepository
                .findByUsername(account1.getUsername()))
                .thenReturn(Optional.empty());

        assertThrows(InvalidAccountException.class, () -> accountService.getAccount(account1.getUsername()));

    }

    @Test
    @DisplayName("Should post an account at account repository and return it")
    public void postAccountSuccessfullyTest() {

        when(accountRepository.save(any(Account.class))).thenReturn(account1);

        assertThat(accountService.postAccount(accountDto1))
                .isNotNull()
                .isInstanceOf(AccountDto.class)
                .isEqualTo(accountDto1);
    }

    @Test
    @DisplayName("Should throw InvalidAccountException when posting with empty email")
    public void postAccountWithEmptyEmailTest() {

        accountDto1.setEmail("");

        assertThrows(InvalidAccountException.class, () -> accountService.postAccount(accountDto1));
    }

    @Test
    @DisplayName("Should throw InvalidAccountException when posting with empty username")
    public void postAccountWithEmptyUsernameTest() {

        accountDto1.setUsername("");

        assertThrows(InvalidAccountException.class, () -> accountService.postAccount(accountDto1));
    }

    @Test
    @DisplayName("Should throw InvalidAccountException when posting with empty password")
    public void postAccountWithEmptyPasswordTest() {

        accountDto1.setPassword("");

        assertThrows(InvalidAccountException.class, () -> accountService.postAccount(accountDto1));
    }

    @Test
    @DisplayName("Should throw InvalidAccountException when posting with empty contract")
    public void postAccountWithEmptyContractTest() {

        accountDto1.setContract("");

        assertThrows(InvalidContractException.class, () -> accountService.postAccount(accountDto1));
    }

    @Test
    @DisplayName("Should throw InvalidAccountException when posting with empty country")
    public void postAccountWithEmptyCountryTest() {

        accountDto1.setCountry("");

        assertThrows(InvalidCountryException.class, () -> accountService.postAccount(accountDto1));
    }

    @Test
    @DisplayName("Should delete account with success if exists")
    public void deleteAccountSuccessfullyTest() {

        when(accountRepository
                .findByUsername(account1.getUsername()))
                .thenReturn(Optional.ofNullable(account1));

        doNothing().when(accountRepository).delete(any(Account.class));

        assertThat(accountService.deleteAccount(account1.getUsername()))
                .isNotNull()
                .isEqualTo(accountDto1);
    }

    @Test
    @DisplayName("Should throw InvalidAccountException if the account to be deleted doesn't exist")
    public void deleteNonExistentAccountTest() {
        when(accountRepository
                .findByUsername(account1.getUsername()))
                .thenReturn(Optional.empty());

        assertThrows(InvalidAccountException.class,() -> accountService.deleteAccount(account1.getUsername()));
    }

    @Test
    @DisplayName("To be completed")
    public void updateAccountSuccessfullyTest() {
        assertThat(accountService.updateAccount(accountDto1)).isNull();
    }



}