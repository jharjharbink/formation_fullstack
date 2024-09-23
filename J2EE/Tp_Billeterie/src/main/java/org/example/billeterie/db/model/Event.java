package org.example.exo.billeterie.db.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Event {

    @Id
    @GeneratedValue
    @Column(name = "id_event")
    private long id;

    private String name;

    @Column(name = "place_nbr")
    private int placeNbr;

    private Date date;

    private String heure;

    @Embedded
    private Address adress;

    @OneToMany(mappedBy = "event")
    private List<Ticket> tickets;

    public Event(String nom, int placeNbr, Date date, String heure, Address adress) {
        this.name = nom;
        this.placeNbr = placeNbr;
        this.date = date;
        this.heure = heure;
        this.adress = adress;

    }
}
