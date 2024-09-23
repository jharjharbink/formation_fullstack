package org.example.db.model;

import org.example.db.SaleState;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Sale {
    @Id
    @GeneratedValue
    @Column(name = "id_sale")
    private long id;
    private SaleState state;
    private Date date;

    @OneToMany(mappedBy = "sale", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<ArticleSale> saleContents;

    @ManyToOne
    @JoinColumn(name = "id_client")
    private Client client;

    public Sale(SaleState state, Date date, Client client) {
        this.state = state;
        this.date = date;
        this.saleContents = new ArrayList<>();
        this.client = client;
    }

    public void addArticleSale(ArticleSale articleSale){
        this.saleContents.add(articleSale);
    }
}
