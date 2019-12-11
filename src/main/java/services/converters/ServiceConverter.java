package services.converters;

import entities.Account;
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
}
