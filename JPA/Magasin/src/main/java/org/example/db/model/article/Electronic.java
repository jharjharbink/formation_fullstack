package org.example.db.model.article;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Electronic extends Article {
    @Column(name = "battery_max_capacity")
    private int batteryMaxCapacity;

    public Electronic(String description, double price, int stockQuantity, int batteryMaxCapacity) {
        super(description, price, stockQuantity);
        this.batteryMaxCapacity = batteryMaxCapacity;
    }

    @Override
    public String toString() {
        return "Clothe{" +
                "id=" + this.getId() +
                ", description=" + this.getDescription() +
                ", price=" + this.getPrice() +
                ", stockQuantity=" + this.getStockQuantity() +
                ", batteryMaxCapacity=" + batteryMaxCapacity +
                '}';
    }

}
