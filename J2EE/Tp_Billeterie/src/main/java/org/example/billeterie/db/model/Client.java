package org.example.exo.billeterie.db.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Client {

    @Id
    @GeneratedValue
    @Column(name = "id_client")
    private long id;

    private String name;

    private String surname;

    private int age;

    @Column(name = "phone_nbr")
    private String phoneNbr;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "client")
    private List<Ticket> tickets;

    public Client(String name, String surname, int age, String phoneNbr, Address address) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.phoneNbr = phoneNbr;
        this.address = address;
    }
}
