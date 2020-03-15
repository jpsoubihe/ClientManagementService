package controllers;

import application.Application;
import controllers.dtos.AccountDto;
import entities.Account;
import entities.User;
import enums.Country;
import enums.ServiceType;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.event.annotation.AfterTestExecution;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class AccountControllerIntegrationTest {

    User user1 = User.builder()
            .age(12)
            .name("joan")
            .build();

    Account account1 = Account.builder()
            .contract(ServiceType.PREMIUM)
            .country(Country.BRASIL)
            .password("1234")
            .username("jpsoubihe")
            .email("jpsoubihe@hotmail.com")
            .beginDate(LocalDate.parse("2019-10-03"))
            .users(Arrays.asList(user1))
            .build();

    User user2 = User.builder()
            .age(16)
            .name("testUser")
            .build();

    Account account2 = Account.builder()
            .contract(ServiceType.BASIC)
            .country(Country.BRASIL)
            .password("1234")
            .username("marina")
            .email("marina@hotmail.com")
            .beginDate(LocalDate.parse("2019-10-03"))
            .users(Arrays.asList(user2))
            .build();


    @LocalServerPort
    private int port;

    private TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();

    @Test
    public void getAllAccountsTest() {

        user1.setAccount(account1);
        user2.setAccount(account2);


        HttpEntity<AccountDto> addAccount = new HttpEntity<>(
                AccountDto.builder()
                        .beginDate(LocalDate.now())
                        .contract(account2.getContract())
                        .country(account2.getCountry())
                        .email(account2.getEmail())
                        .password(account2.getPassword())
                        .username(account2.getUsername())
                        .users(account2.getUsers())
                        .build(),
                headers);

        ResponseEntity<AccountDto> addResponse = restTemplate.exchange("http://localhost:"+port+"/v1/account", HttpMethod.POST, addAccount,AccountDto.class);

        assertThat(addResponse.getStatusCode())
                .isEqualTo(HttpStatus.OK);

        assertThat(addResponse.getBody())
                .isNotNull()
                .isInstanceOf(AccountDto.class)
                .hasFieldOrPropertyWithValue("beginDate", LocalDate.now())
                .hasFieldOrPropertyWithValue("email", account2.getEmail())
                .hasFieldOrPropertyWithValue("username", account2.getUsername())
                .hasFieldOrPropertyWithValue("password", account2.getPassword())
                .hasFieldOrPropertyWithValue("country", account2.getCountry())
                .hasFieldOrPropertyWithValue("contract", account2.getContract())
                .hasFieldOrProperty("users");

        HttpEntity<String> getRequest = new HttpEntity<String>(null, headers);


        ResponseEntity<List<Account>> response = restTemplate.exchange("http://localhost:"+port+"/v1/account", HttpMethod.GET, getRequest, new ParameterizedTypeReference<List<Account>>() {
        });

        List<Account> accounts = response.getBody();

        assertThat(accounts)
                .isNotNull()
                .isNotEmpty();

        assertThat(accounts.get(0))
                .isNotNull()
                .isInstanceOf(Account.class)
                .hasFieldOrPropertyWithValue("beginDate", account1.getBeginDate())
                .hasFieldOrPropertyWithValue("email", account1.getEmail())
                .hasFieldOrPropertyWithValue("username", account1.getUsername())
                .hasFieldOrPropertyWithValue("password", account1.getPassword())
                .hasFieldOrPropertyWithValue("country", account1.getCountry())
                .hasFieldOrPropertyWithValue("contract", account1.getContract());

        ResponseEntity<Account> getSingleResponse = restTemplate.exchange("http://localhost:"+port+"/v1/account/" + account1.getUsername(), HttpMethod.GET, getRequest, Account.class);

        assertThat(getSingleResponse.getBody())
                .isNotNull()
                .isInstanceOf(Account.class)
                .hasFieldOrPropertyWithValue("beginDate", account1.getBeginDate())
                .hasFieldOrPropertyWithValue("email", account1.getEmail())
                .hasFieldOrPropertyWithValue("username", account1.getUsername())
                .hasFieldOrPropertyWithValue("password", account1.getPassword())
                .hasFieldOrPropertyWithValue("country", account1.getCountry())
                .hasFieldOrPropertyWithValue("contract", account1.getContract());



    }

    @AfterAll
    @Test
    public void cleanDB() {
        HttpEntity<?> deleteRequest = new HttpEntity<>(null,headers);
        log.info("Cleaning db");
        ResponseEntity<AccountDto> deleteResponse = restTemplate.exchange("http://localhost:"+port+"/v1/account/"+account2
                .getUsername(),HttpMethod.DELETE,deleteRequest,AccountDto.class);
    }




}