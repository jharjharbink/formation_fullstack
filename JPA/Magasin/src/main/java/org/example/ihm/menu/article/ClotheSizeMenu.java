package org.example.ihm.menu.article;

import org.example.db.Size;
import org.example.exceptions.ReturnToInventoryGestionMenuException;
import org.example.exceptions.WrongSearchChoiceException;
import org.example.ihm.enums.navigation.BackMenuOptions;
import org.example.ihm.menu.BaseMenu;
import org.example.ihm.menu.Menus;

import java.util.Arrays;
import java.util.List;


import static org.example.ihm.enums.navigation.BackMenuOptions.RETURN_TO_ARTICLE_GESTION_MENU;

public class ClotheSizeMenu extends BaseMenu implements Menus {

    private final static List<String> menuElements = Arrays.asList("Choix Taille Vêtement",
            "XS",
            "S",
            "M",
            "L",
            "XL");
    private final static BackMenuOptions backMenuOption = RETURN_TO_ARTICLE_GESTION_MENU;
    public final static int menuLength = setMenuLength(menuElements, backMenuOption);     // TODO seems like a bad practice (setter in abstract method. accessed not through getter)

    public ClotheSizeMenu() {
        super(menuElements, backMenuOption);
    }

    public static Size menuOptions(int userMenuChoice){
        return switch (userMenuChoice) {
            case 1 -> Size.XS;
            case 2 -> Size.S;
            case 3 -> Size.M;
            case 4 -> Size.L;
            case 5 -> Size.XL;
            case 0 -> throw new ReturnToInventoryGestionMenuException();
            default -> throw new WrongSearchChoiceException("Unknown choice by user with menuType " + CreateArticleMenu.class.getSimpleName());
        };
    }
}
