package org.example.cuisine.service;

import org.example.cuisine.model.Recipe;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RecipeService {
    private final Map<Long, Recipe> recipes;

    public RecipeService() {
        this.recipes = new HashMap<>();

        CategoryService categoryService = new CategoryService();

        Recipe soupeTomate = new Recipe(
                0,
                "soupe tomate",
                "eau et tomate",
                "mélanger tomate et eau puis chauffer",
                0);

        Recipe soupeOignon = new Recipe(
                1,
                "soupe oignon",
                "eau et oignon",
                "mélanger oignon et eau puis chauffer",
                0);

        Recipe patePesto = new Recipe(
                2,
                "pate pesto",
                "pate et pesto",
                "mélanger pate et pesto puis réchauffer",
                1);

        Recipe pateParmesan = new Recipe(
                3,
                "pate parmesan",
                "pate et parmesan",
                "mélanger pate et parmesan puis réchauffer",
                1);

        Recipe saladeFruit = new Recipe(
                4,
                "salade de fruit",
                "fruit1 et fruit2",
                "mélanger fruit1 et fruit2",
                2);

        recipes.put(soupeTomate.getId(), soupeTomate);
        recipes.put(soupeOignon.getId(), soupeOignon);
        recipes.put(patePesto.getId(), patePesto);
        recipes.put(pateParmesan.getId(), pateParmesan);
        recipes.put(saladeFruit.getId(), saladeFruit);
    }

    public Recipe getRecipe(long id) {
        return recipes.get(id);
    }

    public List<Recipe> getAllRecipe() {
        return recipes.values().stream().toList();
    }

    public void addRecipe(Recipe recepe) {
        long recepeId = recipes.size();

        while (recipes.containsKey(recepeId))
            recepeId++;

        recepe.setId(recepeId);
        recipes.put(recepe.getId(), recepe);
    }

    public void updateRecipe(Recipe recipe) {
        deleteRecipe(recipe.getId());
        recipes.put(recipe.getId(), recipe);
    }

    public void deleteRecipe(long id) {
        recipes.remove(id);
    }

    public List<Recipe> getRecipesByName(String name) {
        List<Recipe> foundRecipes = new ArrayList();
        for (Recipe recipe : recipes.values())
            if (Objects.equals(recipe.getName(), name))
                foundRecipes.add(recipe);
        return foundRecipes;
    }

}

