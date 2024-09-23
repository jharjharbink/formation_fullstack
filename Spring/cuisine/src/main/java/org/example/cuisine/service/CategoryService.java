package org.example.cuisine.service;

import org.example.cuisine.model.Category;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CategoryService {
    private final Map<Long, Category> categories;

    public CategoryService() {
        this.categories = new HashMap<>();

        Category entree = new Category(0, "entree", "une entrée ça se mange en premier");
        Category plat = new Category(1, "plat", "un plat ça se mange entre l'entrée et le déssert");
        Category dessert = new Category(2, "dessert", "un déssert ça se mange en dernier");

        categories.put(entree.getId(), entree);
        categories.put(plat.getId(), plat);
        categories.put(dessert.getId(), dessert);
    }

    public Category getCategory(long id) {
        return categories.get(id);
    }

    public List<Category> getAllCategory() {
        return categories.values().stream().toList();
    }

    public void addCategory(Category category) {
        long categoryId = categories.size();

        while (categories.containsKey(categoryId))
            categoryId++;

        category.setId(categoryId);
        categories.put(category.getId(), category);
    }

    public void updateCategory(Category category) {
        deleteStudent(category.getId());
        categories.put(category.getId(), category);
    }

    public void deleteStudent(long id) {
        categories.remove(id);
    }

}
