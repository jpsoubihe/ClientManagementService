package entities;

import enums.ServiceType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    @GeneratedValue
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

    @Column(name = "contract")
    ServiceType contract;

    @ManyToOne
    List<User> users;


}
