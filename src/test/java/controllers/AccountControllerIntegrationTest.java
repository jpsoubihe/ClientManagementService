package controllers;

import application.Application;
import entities.Account;
import entities.User;
import enums.Country;
import enums.ServiceType;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
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

    @LocalServerPort
    private int port;

    private TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();

    @Test
    public void getAllAccountsTest() {

        user1.setAccount(account1);

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

        Account account2 = Account.builder()
                .contract(ServiceType.BASIC)
                .country(Country.BRASIL)
                .password("1234")
                .username("marina")
                .email("marina@hotmail.com")
                .beginDate(LocalDate.parse("2019-10-03"))
                .users(Arrays.asList())
                .build();



    }




}