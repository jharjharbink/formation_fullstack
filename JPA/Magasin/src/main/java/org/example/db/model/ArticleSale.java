package org.example.db.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.example.db.model.article.Article;

import javax.persistence.*;


@Entity
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ArticleSale {
    @Id
    @GeneratedValue
    @Column(name = "id_article_sell")
    private long id;


    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_sale")
    private Sale sale;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_article")
    private Article article;

    private int quantity;

    public ArticleSale(int quantity, Article article, Sale sale) {
        this.sale = sale;
        this.article = article;
        this.quantity = quantity;

    }
}
