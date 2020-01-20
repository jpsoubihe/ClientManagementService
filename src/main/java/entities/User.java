package entities;

import enums.Country;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {

    @Id
    @Column(name = "name")
    String name;

    @Column(name = "age")
    int age;


}
