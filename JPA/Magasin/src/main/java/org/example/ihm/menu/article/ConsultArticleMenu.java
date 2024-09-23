package org.example.ihm.menu.article;

import org.example.exceptions.NotFoundException;
import org.example.exceptions.WrongSearchChoiceException;
import org.example.ihm.enums.navigation.BackMenuOptions;
import org.example.ihm.enums.navigation.MenuType;
import org.example.ihm.input.gestions.ArticleUserInput;
import org.example.ihm.menu.BaseMenu;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static org.example.ihm.enums.navigation.BackMenuOptions.RETURN_TO_ARTICLE_GESTION_MENU;


public class ConsultArticleMenu extends BaseMenu {

    private final static List<String> menuElements = Arrays.asList("Consultation d'Article",
            "Afficher tout",
            "Afficher par id",
            "Afficher par description");
    private final static BackMenuOptions backMenuOption = RETURN_TO_ARTICLE_GESTION_MENU;
    public final static int menuLength = setMenuLength(menuElements, backMenuOption);     // TODO seems like a bad practice (setter in abstract method. accessed not through getter)

    public ConsultArticleMenu() {
        super(menuElements, backMenuOption);
    }

    public static MenuType menuOptions(int userMenuChoice, Scanner scanner){
        try{
            return switch (userMenuChoice) {
                case 1 -> new ArticleUserInput(scanner).askAllArticle();
                case 2 -> new ArticleUserInput(scanner).askArticleById();
                case 3 -> new ArticleUserInput(scanner).askArticleByDescription();
                case 0 -> MenuType.ARTICLE_GESTION_MENU;
                //case 0 -> throw new ReturnToInventoryGestionMenuException();
                default -> throw new WrongSearchChoiceException("Unknown choice by user with menuType " + CreateArticleMenu.class.getSimpleName());
            };
        } catch (NotFoundException e) {
            System.out.println(e);
        }
        return MenuType.MAIN_MENU;
    }
}
