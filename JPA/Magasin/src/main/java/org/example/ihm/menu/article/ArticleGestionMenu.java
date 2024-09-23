package org.example.ihm.menu.article;

import org.example.exceptions.NotFoundException;
import org.example.exceptions.WrongSearchChoiceException;
import org.example.ihm.enums.navigation.BackMenuOptions;
import org.example.ihm.enums.navigation.MenuType;
import org.example.ihm.input.gestions.ArticleUserInput;
import org.example.ihm.menu.BaseMenu;
import org.example.ihm.menu.MainMenu;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static org.example.ihm.enums.navigation.BackMenuOptions.RETURN_TO_MAIN_MENU;


public class ArticleGestionMenu extends BaseMenu {
    private final static List<String> menuElements = Arrays.asList("Gestion Inventaire",
            "Créer",
            "Modifier",
            "Supprimer",
            "Consulter",
            "Restocker");
    private final static BackMenuOptions backMenuOption = RETURN_TO_MAIN_MENU;
    public final static int menuLength = setMenuLength(menuElements, backMenuOption);     // TODO seems like a bad practice (setter in abstract method. accessed not through getter)

    public ArticleGestionMenu() {
        super(menuElements, backMenuOption);
    }

    public static MenuType menuOptions(int userMenuChoice, Scanner scanner){
        try{
            return switch (userMenuChoice) {
                case 1 -> MenuType.ARTICLE_CREATION_MENU;
                case 2 -> MenuType.ARTICLE_UPDATE_MENU;
                case 3 -> new ArticleUserInput(scanner).askDeleteArticle();
                case 4 -> MenuType.ARTICLE_CONSULT_MENU;
                case 5 -> new ArticleUserInput(scanner).askRestock();
                //case 0 -> throw new ReturnToMainMenuException();
                case 0 -> MenuType.MAIN_MENU;
                default -> throw new WrongSearchChoiceException("Unknown choice by user with menuType " + MainMenu.class.getSimpleName());
            };
        } catch (NotFoundException e){
            System.out.println(userMenuChoice);
        }
        return MenuType.MAIN_MENU;
    }
}
