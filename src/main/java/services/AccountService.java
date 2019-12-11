package services;

import entities.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.AccountRepository;
import services.dtos.AccountDto;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;
import static services.converters.ServiceConverter.fromAccount;
import static services.converters.ServiceConverter.toAccount;
import static services.validators.AccountValidator.validateAccount;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    public List<AccountDto> getAllAccounts(){

        List<Account> accounts = accountRepository.findAll();

        if(!accounts.isEmpty())
            return accounts.stream().map(a -> fromAccount(a)).collect(Collectors.toList());

        return emptyList();
    }

    public AccountDto postAccount(AccountDto dto){
        validateAccount(dto);

        Account account = accountRepository.save(toAccount(dto));

        return fromAccount(account);
    }
}
