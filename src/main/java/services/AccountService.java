package services;

import entities.Account;
import entities.User;
import exceptions.InvalidAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.AccountRepository;
import repositories.UserRepository;
import services.dtos.AccountDto;

import java.util.List;
import java.util.stream.Collectors;

import static converters.AccountConverter.fromAccount;
import static converters.AccountConverter.toAccount;
import static java.util.Collections.emptyList;
import static services.validators.AccountValidator.validateAccount;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    UserRepository userRepository;

    public List<AccountDto> getAllAccounts() {

        List<Account> accounts = accountRepository.findAll();

        if (!accounts.isEmpty()) {
            return accounts
                    .stream()
                    .map(a -> fromAccount(a))
                    .collect(Collectors.toList());
        }


        return emptyList();
    }

    public AccountDto getAccount(String username) {

        Account account = accountRepository
                .findByUsername(username)
                .orElseThrow(() ->
                        new InvalidAccountException("there's no username"));

        return fromAccount(account);
    }

    public AccountDto postAccount(AccountDto dto) {
        validateAccount(dto);

        Account account = accountRepository.save(toAccount(dto));

        return fromAccount(account);
    }

    public AccountDto deleteAccount(String username) {

        Account account = accountRepository
                .findByUsername(username)
                .orElseThrow(() ->
                        new InvalidAccountException("there's no username"));

        AccountDto dto = fromAccount(account);

        if(!account.getUsers().isEmpty()) {
            deleteUsers(account.getUsers());
        }

        accountRepository.delete(account);

//        AccountDto dto = fromAccount(account);

        return dto;
    }


    //ToDo: update account operation
    public AccountDto updateAccount(AccountDto dto) {
        return null;
    }

    public void deleteUsers(List<User> users) {
        users.forEach(user -> userRepository.delete(user));
    }
}
