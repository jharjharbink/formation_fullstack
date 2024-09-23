package org.example.ihm.menu.article.update;

import org.example.exceptions.ReturnToInventoryGestionMenuException;
import org.example.exceptions.WrongSearchChoiceException;
import org.example.ihm.enums.navigation.BackMenuOptions;
import org.example.ihm.menu.BaseMenu;
import org.example.ihm.enums.update.article.ClotheUpdateChoices;
import org.example.ihm.menu.Menus;
import org.example.ihm.menu.article.CreateArticleMenu;

import java.util.Arrays;
import java.util.List;

import static org.example.ihm.enums.navigation.BackMenuOptions.RETURN_TO_ARTICLE_GESTION_MENU;

public class ClotheUpdateMenu extends BaseMenu implements ArticleUpdateMenus {

    private final static List<String> menuElements = Arrays.asList("MAJ Vêtement",
            "Description",
            "Prix",
            "Quantité en stock",
            "Catégorie",
            "Taille");
    private final static BackMenuOptions backMenuOption = RETURN_TO_ARTICLE_GESTION_MENU;
    public final static int menuLength = setMenuLength(menuElements, backMenuOption);     // TODO seems like a bad practice (setter in abstract method. accessed not through getter)

    public ClotheUpdateMenu() {
        super(menuElements, backMenuOption);
    }

    public ClotheUpdateChoices menuOptions(int userMenuChoice) {
        return switch (userMenuChoice) {
            case 1 -> ClotheUpdateChoices.DESCRIPTION;
            case 2 -> ClotheUpdateChoices.PRICE;
            case 3 -> ClotheUpdateChoices.STOCK_QUANTITY;
            case 4 -> ClotheUpdateChoices.CATEGORY;
            case 5 -> ClotheUpdateChoices.SIZE;

            case 0 -> throw new ReturnToInventoryGestionMenuException();
            default ->
                    throw new WrongSearchChoiceException("Unknown choice by user with menuType " + CreateArticleMenu.class.getSimpleName());
        };
    }
}