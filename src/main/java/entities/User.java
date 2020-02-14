package entities;

import enums.Country;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {

    @Id
    @Column(name = "name")
    String name;

    @Column(name = "age")
    int age;

    @ManyToOne
    Account account;


}
