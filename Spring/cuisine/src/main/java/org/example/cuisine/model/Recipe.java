package org.example.cuisine.model;

import java.util.List;

public class Recipe {

    Long id = null;
    String name;
    String components;
    String instructions;
    long categoryId;

    public Recipe() {
    }

    public Recipe(long id, String name, String components, String instructions, long categoryId) {
        this.id = id;
        this.name = name;
        this.components = components;
        this.instructions = instructions;
        this.categoryId = categoryId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComponents() {
        return components;
    }

    public void setComponents(String components) {
        this.components = components;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }
}
