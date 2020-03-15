package entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import enums.Country;
import enums.ServiceType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long id;

    @Column(name = "begin_date")
    LocalDate beginDate;

    @Column(name = "email")
    String email;

    @Column(name = "username")
    String username;

    @Column(name = "password")
    String password;

    @Column(name = "country")
    Country country;

    @Column(name = "contract")
    ServiceType contract;

    @OneToMany(mappedBy = "account")
    List<User> users;


}