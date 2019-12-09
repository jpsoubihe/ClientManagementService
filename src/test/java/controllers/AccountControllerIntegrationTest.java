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
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static converters.AccountConverter.fromAccount;
import static converters.AccountConverter.fromService;
import static java.util.Collections.emptyList;
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
                .hasFieldOrPropertyWithValue("contract", account2.getContract());

        HttpEntity<String> getRequest = new HttpEntity<String>(null, headers);


        ResponseEntity<List<AccountDto>> response = restTemplate.exchange("http://localhost:"+port+"/v1/account", HttpMethod.GET, getRequest, new ParameterizedTypeReference<List<AccountDto>>() {
        });

        List<AccountDto> accounts = response.getBody();

        assertThat(accounts)
                .isNotNull()
                .isNotEmpty();

        assertThat(accounts.get(0))
                .isNotNull()
                .isInstanceOf(AccountDto.class)
                .hasFieldOrPropertyWithValue("id", 1L)
                .hasFieldOrPropertyWithValue("beginDate", account1.getBeginDate())
                .hasFieldOrPropertyWithValue("email", account1.getEmail())
                .hasFieldOrPropertyWithValue("username", account1.getUsername())
                .hasFieldOrPropertyWithValue("password", account1.getPassword())
                .hasFieldOrPropertyWithValue("country", account1.getCountry())
                .hasFieldOrProperty("users")
                .hasFieldOrPropertyWithValue("contract", account1.getContract());

        ResponseEntity<AccountDto> getSingleResponse = restTemplate.exchange("http://localhost:"+port+"/v1/account/" + account1.getUsername(), HttpMethod.GET, getRequest, AccountDto.class);

        account1.setId(1L);

        assertThat(getSingleResponse.getBody())
                .isNotNull()
                .isInstanceOf(AccountDto.class)
                .hasFieldOrPropertyWithValue("id", 1L)
                .hasFieldOrPropertyWithValue("beginDate", account1.getBeginDate())
                .hasFieldOrPropertyWithValue("email", account1.getEmail())
                .hasFieldOrPropertyWithValue("username", account1.getUsername())
                .hasFieldOrPropertyWithValue("password", account1.getPassword())
                .hasFieldOrPropertyWithValue("country", account1.getCountry())
                .hasFieldOrPropertyWithValue("users", Arrays.asList(new User("joan",12,account1)))
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