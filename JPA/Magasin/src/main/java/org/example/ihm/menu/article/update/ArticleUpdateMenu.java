package org.example.ihm.menu.article.update;

import org.example.db.ArticleTypes;
import org.example.exceptions.WrongSearchChoiceException;
import org.example.ihm.enums.navigation.BackMenuOptions;
import org.example.ihm.enums.navigation.MenuType;
import org.example.ihm.input.gestions.ArticleUserInput;
import org.example.ihm.menu.BaseMenu;
import org.example.ihm.menu.Menus;
import org.example.ihm.menu.article.CreateArticleMenu;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static org.example.ihm.enums.navigation.BackMenuOptions.RETURN_TO_ARTICLE_GESTION_MENU;

public class ArticleUpdateMenu extends BaseMenu implements Menus {

    private final static List<String> menuElements = Arrays.asList("MAJ d'Article",
            "Vêtement",
            "Electronique",
            "Nourriture");
    private final static BackMenuOptions backMenuOption = RETURN_TO_ARTICLE_GESTION_MENU;
    public final static int menuLength = setMenuLength(menuElements, backMenuOption);     // TODO seems like a bad practice (setter in abstract method. accessed not through getter)

    public ArticleUpdateMenu() {
        super(menuElements, backMenuOption);
    }

    public static MenuType menuOptions(int userMenuChoice, Scanner scanner){
        return switch (userMenuChoice) {
            case 1 -> new ArticleUserInput(scanner).askUpdateArticle(ArticleTypes.CLOTHE);
            case 2 -> new ArticleUserInput(scanner).askUpdateArticle(ArticleTypes.ELECTRONIC);
            case 3 -> new ArticleUserInput(scanner).askUpdateArticle(ArticleTypes.FOOD);
            case 0 -> MenuType.ARTICLE_GESTION_MENU;
            default -> throw new WrongSearchChoiceException("Unknown choice by user with menuType " + CreateArticleMenu.class.getSimpleName());
        };
    }
}
