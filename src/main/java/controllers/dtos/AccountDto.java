package controllers.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

import static java.util.Collections.emptyList;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {

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
//    @JsonIgnore
    List<User> users = new ArrayList<>();
}
