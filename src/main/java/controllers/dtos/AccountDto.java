package controllers.dtos;

import entities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

import static java.util.Collections.emptyList;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {

    LocalDate beginDate;

    @Builder.Default
    String email = "";

    @Builder.Default
    String username = "";

    @Builder.Default
    String password = "";

    @Builder.Default
    String contract = "";

    @Builder.Default
    String country = "";

    List<User> users;
}
