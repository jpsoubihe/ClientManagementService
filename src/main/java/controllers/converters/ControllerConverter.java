package controllers.converters;

import controllers.dtos.AccountDto;
import entities.Account;
import lombok.Builder;
import lombok.Data;

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
                .users(dto.getUsers())
                .build();

    }

    public static services.dtos.AccountDto toService(AccountDto dto){

        return services.dtos.AccountDto.builder()
                .beginDate(dto.getBeginDate())
                .contract(dto.getContract())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .username(dto.getUsername())
                .users(dto.getUsers())
                .build();

    }
}
