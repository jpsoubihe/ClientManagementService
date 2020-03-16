package converters;

import controllers.dtos.AccountDto;
import entities.Account;
import enums.Country;
import enums.ServiceType;
import exceptions.InvalidContractException;
import exceptions.InvalidCountryException;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class AccountConverter {

    public static AccountDto fromService(
            services
                    .dtos.AccountDto dto
    ) {

        return AccountDto.builder()
                .id(dto.getId())
                .beginDate(dto.getBeginDate())
                .contract(dto.getContract())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .username(dto.getUsername())
                .country(dto.getCountry())
                .users(dto.getUsers())
                .build();

    }

    public static services.dtos.AccountDto toService(AccountDto dto){

        return services.dtos.AccountDto.builder()
                .beginDate(dto.getBeginDate())
                .contract(dto.getContract())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .country(dto.getCountry())
                .username(dto.getUsername())
                .users(dto.getUsers())
                .build();
    }

    public static services.dtos.AccountDto fromAccount(Account account){

        return services.dtos.AccountDto.builder()
                .id(account.getId())
                .username(account.getUsername())
                .password(account.getPassword())
                .email(account.getEmail())
                .contract(account.getContract())
                .country(account.getCountry())
                .beginDate(account.getBeginDate())
                .build();

    }

    public static Account toAccount(services.dtos.AccountDto dto){

        return Account.builder()
                .beginDate(dto.getBeginDate())
                .contract(dto.getContract())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .username(dto.getUsername())
                .country(dto.getCountry())
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
