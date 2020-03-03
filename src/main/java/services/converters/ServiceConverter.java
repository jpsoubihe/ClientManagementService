package services.converters;

import entities.Account;
import enums.Country;
import enums.ServiceType;
import exceptions.InvalidContractException;
import exceptions.InvalidCountryException;
import services.dtos.AccountDto;

public class ServiceConverter {

    public static AccountDto fromAccount(Account account){

        return AccountDto.builder()
                .users(account.getUsers())
                .username(account.getUsername())
                .password(account.getPassword())
                .email(account.getEmail())
                .contract(account.getContract())
                .country(account.getCountry())
                .beginDate(account.getBeginDate())
                .build();

    }

    public static Account toAccount(AccountDto dto){

        return Account.builder()
                .beginDate(dto.getBeginDate())
                .contract(dto.getContract())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .username(dto.getUsername())
                .country(dto.getCountry())
                .users(dto.getUsers())
                .build();

    }

    private static ServiceType convertToServiceType(String contract){
        if(contract.equalsIgnoreCase("PREMIUM"))
            return ServiceType.PREMIUM;
        else if(contract.equalsIgnoreCase("BASIC"))
            return ServiceType.BASIC;
        else
            throw new InvalidContractException("the respective contract for your account is not possible");
    }

    private static Country convertToCountry(String country){
        if(country.equalsIgnoreCase("BRASIL"))
            return Country.BRASIL;
        else if(country.equalsIgnoreCase("EUA"))
            return Country.EUA;
        else if(country.equalsIgnoreCase("CANADA"))
            return Country.CANADA;
        else if(country.equalsIgnoreCase("CHINA"))
            return Country.CHINA;
        else if(country.equalsIgnoreCase("UNITED KINGDOM"))
            return Country.UNITED_KINGDOM;
        else
            throw new InvalidCountryException("unfortunately this region does not have our services yet");
    }
}
