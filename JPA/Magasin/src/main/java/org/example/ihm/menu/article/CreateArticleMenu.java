package org.example.ihm.menu.article;

import org.example.exceptions.WrongSearchChoiceException;
import org.example.ihm.enums.navigation.BackMenuOptions;
import org.example.ihm.enums.navigation.MenuType;
import org.example.ihm.input.gestions.ArticleUserInput;
import org.example.ihm.menu.BaseMenu;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static org.example.ihm.enums.navigation.BackMenuOptions.RETURN_TO_ARTICLE_GESTION_MENU;

public class CreateArticleMenu extends BaseMenu {
    private final static List<String> menuElements = Arrays.asList("Creation d'Article",
            "Vêtement",
            "Electronique",
            "Nourriture");
    private final static BackMenuOptions backMenuOption = RETURN_TO_ARTICLE_GESTION_MENU;
    public final static int menuLength = setMenuLength(menuElements, backMenuOption);     // TODO seems like a bad practice (setter in abstract method. accessed not through getter)

    public CreateArticleMenu() {
        super(menuElements, backMenuOption);
    }

    public static MenuType menuOptions(int userMenuChoice, Scanner scanner){
        return switch (userMenuChoice) {
            case 1 -> new ArticleUserInput(scanner).askClotheCreation();
            case 2 -> new ArticleUserInput(scanner).askElectronicCreation();
            case 3 -> new ArticleUserInput(scanner).askFoodCreation();
            case 0 -> MenuType.ARTICLE_GESTION_MENU;
//            case 0 -> throw new ReturnToInventoryGestionMenuException();
            default -> throw new WrongSearchChoiceException("Unknown choice by user with menuType " + CreateArticleMenu.class.getSimpleName());
        };
    }
}
