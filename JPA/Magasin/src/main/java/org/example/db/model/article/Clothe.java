package org.example.db.model.article;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.example.db.ClotheCategory;
import org.example.db.Size;

import javax.persistence.Entity;

@Entity
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Clothe extends Article {
    private ClotheCategory category;
    private Size size;

    public Clothe(String description, double price, int stockQuantity, ClotheCategory category, Size size) {
        super(description, price, stockQuantity);
        this.category = category;
        this.size = size;
    }

    @Override
    public String toString() {
        return "Clothe{" +
                "id=" + this.getId() +
                ", description=" + this.getDescription() +
                ", price=" + this.getPrice() +
                ", stockQuantity=" + this.getStockQuantity() +
                ", category=" + category +
                ", size=" + size +
                '}';
    }
}
