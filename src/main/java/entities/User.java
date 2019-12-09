package entities;

import enums.Country;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {

    @Id
    @Column(name = "name")
    String name;

    @Column(name = "country")
    Country country;

    @Column(name = "age")
    int age;


}
