package controllers.dtos;

import entities.User;
import enums.Country;
import enums.ServiceType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {

    Long id;

    @Builder.Default
    LocalDate beginDate = LocalDate.now();

    @Builder.Default
    String email = "";

    @Builder.Default
    String username = "";

    @Builder.Default
    String password = "";

    ServiceType contract;

    Country country;

    @Builder.Default
    List<User> users = new ArrayList<>();
}
