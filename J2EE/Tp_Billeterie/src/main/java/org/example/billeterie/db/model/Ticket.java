package org.example.exo.billeterie.db.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue
    @Column(name = "id_ticket")
    private long id;

    @Column(name = "user_place_number")
    private int userPlaceNumber;

    private String type;

    @ManyToOne
    @JoinColumn(name = "id_client")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "id_event")
    private Event event;

    public Ticket(int userPlaceNumber, String type, Client client, Event event) {
        this.userPlaceNumber = userPlaceNumber;
        this.type = type;
        this.client = client;
        this.event = event;
    }
}
