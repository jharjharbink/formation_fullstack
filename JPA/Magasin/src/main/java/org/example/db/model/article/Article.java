package org.example.db.model.article;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.example.db.model.ArticleSale;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public abstract class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id_article")
    private long id;

    private String description;

    private double price;

    @Column(name = "stock_quantity")
    private int stockQuantity;

    @OneToMany(mappedBy = "article", fetch = FetchType.EAGER)
    private List<ArticleSale> saleContents = new ArrayList<>();;

    public Article(String description, double price, int stockQuantity) {
        this.description = description;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }
}
