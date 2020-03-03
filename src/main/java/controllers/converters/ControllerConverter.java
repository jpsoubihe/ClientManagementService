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





}
