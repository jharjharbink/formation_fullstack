package org.example.db.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
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
    private String mail;

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    private List<Sale> sales;

    public Client(String name, String mail) {
        this.name = name;
        this.mail = mail;
        this.sales = new ArrayList<>();
    }
}
