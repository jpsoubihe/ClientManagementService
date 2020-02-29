package services.validators;

import exceptions.InvalidAccountException;
import exceptions.InvalidContractException;
import exceptions.InvalidCountryException;
import services.dtos.AccountDto;

public class AccountValidator {

    public static void validateAccount(AccountDto dto){
        if(dto.getEmail().isEmpty()) {
            throw new InvalidAccountException("no email to register your account");
        }

        if(dto.getUsername().isEmpty()) {
            throw new InvalidAccountException("no username to register your account");
        }

        if(dto.getPassword().isEmpty()) {
            throw new InvalidAccountException("no password to register your account");
        }

        if(dto.getContract().isEmpty()) {
            throw new InvalidContractException("no contract to register your account");
        }

        if(dto.getCountry().isEmpty()) {
            throw new InvalidCountryException("no Country to register your account");
        }


    }
}
