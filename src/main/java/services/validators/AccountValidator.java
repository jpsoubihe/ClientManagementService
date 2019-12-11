package services.validators;

import exceptions.InvalidAccountException;
import services.dtos.AccountDto;

public class AccountValidator {

    public static void validateAccount(AccountDto dto){
        if(dto.getEmail().isEmpty())
            throw new InvalidAccountException("no email to register your account");
        if(dto.getUsername().isEmpty())
            throw new InvalidAccountException("no username to register your account");
        if(dto.getPassword().isEmpty())
            throw new InvalidAccountException("no password to register your account");
    }
}
