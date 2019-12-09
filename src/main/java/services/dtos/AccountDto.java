package services.dtos;

import entities.User;
import enums.ServiceType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {

    LocalDate beginDate;

    String email;

    String username;

    String password;

    ServiceType contract;

    List<User> users;
}
