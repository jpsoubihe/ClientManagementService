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

/**
 * Provides the classes necessary to create an
 * applet and the classes an applet uses
 * to communicate with its applet context.
 * <p>
 * The applet framework involves two entities:
 * the applet and the applet context.
 * An applet is an embeddable window (see the
 * {@link java.awt.Panel} class) with a few extra
 * methods that the applet context can use to
 * initialize, start, and stop the applet.
 *
 * @since 1.0
 * @see java.awt
 */
@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    UserRepository userRepository;

    public List<AccountDto> getAllAccounts() {

        List<Account> accounts = accountRepository.findAll();

        if(accounts.isEmpty()) {
            return emptyList();
        }

        return accounts.stream()
                .map(account -> fromAccount(account))
                .map(acc -> {
                    List<User> accountUsers = userRepository.findByAccountId(acc.getId());
                    acc.setUsers(accountUsers);
                    return acc;
                })
                .collect(Collectors.toList());
    }

    public AccountDto getAccount(String username) {

        Account account = accountRepository
                .findByUsername(username)
                .orElseThrow(() ->
                        new InvalidAccountException("there's no username"));

        List<User> users = userRepository.findByAccountId(account.getId());

        AccountDto dto = fromAccount(account);
        dto.setUsers(users);

        return dto;
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

//        if(!account.getUsers().isEmpty()) {
//            deleteUsers(account.getUsers());
//        }

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
