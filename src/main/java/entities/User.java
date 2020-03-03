package entities;

import lombok.*;

import javax.persistence.*;

@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
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