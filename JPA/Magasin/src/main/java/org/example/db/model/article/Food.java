package org.example.db.model.article;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

@Entity
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Food extends Article {
    @Column(name = "expiration_date")
    private Date expirationDate;

    public Food(String description, double price, int stockQuantity, Date expirationDate) {
        super(description, price, stockQuantity);
        this.expirationDate = expirationDate;
    }

    @Override
    public String toString() {
        return "Clothe{" +
                "id=" + this.getId() +
                ", description=" + this.getDescription() +
                ", price=" + this.getPrice() +
                ", stockQuantity=" + this.getStockQuantity() +
                ", expirationDate=" + expirationDate +
                '}';
    }

}
