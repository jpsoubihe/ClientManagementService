package services.dtos;

import entities.User;
import enums.Country;
import enums.ServiceType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {

    Long id;

    LocalDate beginDate;

    @Builder.Default
    String email = "";

    @Builder.Default
    String username = "";

    @Builder.Default
    String password = "";

    Country country;

    ServiceType contract;

    @Builder.Default
    List<User> users = new ArrayList<>();
}
