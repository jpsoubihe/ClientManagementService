package controllers.converters;

import controllers.dtos.AccountDto;
import enums.Country;
import enums.ServiceType;
import exceptions.InvalidContractException;
import exceptions.InvalidCountryException;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@Builder
public class ControllerConverter {

    public static AccountDto fromService(services.dtos.AccountDto dto){

        return AccountDto.builder()
                .beginDate(dto.getBeginDate())
                .contract(dto.getContract().toString())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .username(dto.getUsername())
                .country(dto.getCountry().toString())
                .users(dto.getUsers())
                .build();

    }

    public static services.dtos.AccountDto toService(AccountDto dto){

        return services.dtos.AccountDto.builder()
                .beginDate(LocalDate.now())
                .contract(convertToServiceType(dto.getContract()))
                .email(dto.getEmail())
                .password(dto.getPassword())
                .country(convertToCountry(dto.getCountry()))
                .username(dto.getUsername())
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
