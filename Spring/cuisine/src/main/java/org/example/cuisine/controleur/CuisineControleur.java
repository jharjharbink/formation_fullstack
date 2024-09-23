package org.example.cuisine.controleur;

import org.example.cuisine.model.Category;
import org.example.cuisine.model.Recipe;
import org.example.cuisine.service.CategoryService;
import org.example.cuisine.service.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CuisineControleur {

    private final CategoryService categoryService;
    private final RecipeService recipeService;

    public CuisineControleur(CategoryService categoryService, RecipeService recipeService) {
        this.categoryService = categoryService;
        this.recipeService = recipeService;
    }

    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("/recipes")
    public String recipes(Model model){
        List<Recipe> recips = recipeService.getAllRecipe();  // @RequestParam(name = "search", required = false) String search,
        List<Category> categories = categoryService.getAllCategory();
        model.addAttribute("recipes", recips);
        model.addAttribute("categories", categories);
        return "recipes";
    }

    @GetMapping("/categories")
    public String categories(Model model){
        List<Category> categories = categoryService.getAllCategory();
        model.addAttribute("categories", categories);
        return "categories";
    }

    @GetMapping("/formulaireRecette")
    public String formRecipe(Model model){
        List<Category> categories = categoryService.getAllCategory();
        Recipe recipe = new Recipe();
        model.addAttribute("categories", categories);
        model.addAttribute("recipe", recipe);
        return "formRecipe";
    }


    @PostMapping("/formulaireRecette")
    public String saveRecipe(@ModelAttribute("recipe") Recipe recipe, Model model) {   // @Valid , BindingResult bindingResult

        if (recipe.getId() != null) {
            recipeService.updateRecipe(recipe);
        } else  {
            recipeService.addRecipe(recipe);
        }

        List<Recipe> recips = recipeService.getAllRecipe();
        model.addAttribute("recips", recips);
        return "redirect:/recips";
    }

    @GetMapping("/formulaireCategory")
    public String formCategorie(Model model){
        model.addAttribute("category", new Category());
        return "formCategory";
    }

    @PostMapping("/formulaireCategory")
    public String saveCategory(@ModelAttribute("categorie") Category category, Model model) {
        categoryService.addCategory(category);
        return "redirect:/formulaireCategory";
    }

    @GetMapping("/recipe/{id}")
    public String detailRecipe(@PathVariable("id") Long id, Model model){
        List<Category> categories = categoryService.getAllCategory();
        model.addAttribute("categories", categories);

        Recipe recipe = recipeService.getRecipe(id);

        if (recipe != null) {
            model.addAttribute("recipe", recipe);
            return "formRecipe";
        } else {
            List<Recipe> recipes = recipeService.getAllRecipe();
            model.addAttribute("recipes", recipes);
            return "recipes";
        }
    }

    @GetMapping("/category/{id}")
    public String detailCategory(@PathVariable("id") Long id, Model model){

        Category category = categoryService.getCategory(id);

        if (category != null) {
            model.addAttribute("category", category);
            return "formCategory";
        } else {
            List<Category> categories = categoryService.getAllCategory();
            model.addAttribute("categories", categories);
            return "categories";
        }
    }

    @GetMapping("/deleteRecipe/{id}")
    public String delete(@PathVariable("id") Long id, Model model) {
        recipeService.deleteRecipe(id);
        return recipes(model);
    }


}
