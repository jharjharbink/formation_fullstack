package org.example.ihm.menu.article.update;

import org.example.exceptions.ReturnToInventoryGestionMenuException;
import org.example.exceptions.WrongSearchChoiceException;
import org.example.ihm.enums.navigation.BackMenuOptions;
import org.example.ihm.menu.BaseMenu;
import org.example.ihm.enums.update.article.FoodUpdateChoice;
import org.example.ihm.menu.article.CreateArticleMenu;

import java.util.Arrays;
import java.util.List;

import static org.example.ihm.enums.navigation.BackMenuOptions.RETURN_TO_ARTICLE_GESTION_MENU;

public class FoodUpdateMenu extends BaseMenu implements ArticleUpdateMenus{

    private final static List<String> menuElements = Arrays.asList("MAJ Nourriture",
            "Description",
            "Prix",
            "Quantité en stock",
            "Date de péremption");
    private final static BackMenuOptions backMenuOption = RETURN_TO_ARTICLE_GESTION_MENU;
    public final static int menuLength = setMenuLength(menuElements, backMenuOption);     // TODO seems like a bad practice (setter in abstract method. accessed not through getter)

    public FoodUpdateMenu() {
        super(menuElements, backMenuOption);
    }

    public FoodUpdateChoice menuOptions(int userMenuChoice) {
        return switch (userMenuChoice) {
            case 1 -> FoodUpdateChoice.DESCRIPTION;
            case 2 -> FoodUpdateChoice.PRICE;
            case 3 -> FoodUpdateChoice.STOCK_QUANTITY;
            case 4 -> FoodUpdateChoice.EXPIRATION_DATE;
            case 0 -> throw new ReturnToInventoryGestionMenuException();
            default ->
                    throw new WrongSearchChoiceException("Unknown choice by user with menuType " + CreateArticleMenu.class.getSimpleName());
        };
    }
}
