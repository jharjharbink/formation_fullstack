package org.example.ihm.menu.client;

import org.example.exceptions.WrongSearchChoiceException;
import org.example.ihm.enums.navigation.BackMenuOptions;
import org.example.ihm.enums.update.client.ClientUpdateChoices;
import org.example.ihm.menu.BaseMenu;
import org.example.ihm.menu.Menus;
import org.example.ihm.menu.article.CreateArticleMenu;

import java.util.Arrays;
import java.util.List;

import static org.example.ihm.enums.navigation.BackMenuOptions.RETURN_TO_CLIENT_GESTION_MENU;

public class ClientUpdateMenu extends BaseMenu implements Menus {

    private final static List<String> menuElements = Arrays.asList("MAJ client",
            "Nom",
            "Mail");
    private final static BackMenuOptions backMenuOption = RETURN_TO_CLIENT_GESTION_MENU;
    public final static int menuLength = setMenuLength(menuElements, backMenuOption);     // TODO seems like a bad practice (setter in abstract method. accessed not through getter)

    public ClientUpdateMenu() {
        super(menuElements, backMenuOption);
    }

    public static ClientUpdateChoices menuOptions(int userMenuChoice){
        return switch (userMenuChoice) {
            case 1 -> ClientUpdateChoices.NAME;
            case 2 -> ClientUpdateChoices.EMAIL;
            case 0 -> ClientUpdateChoices.STOP_UPDATE;
            default -> throw new WrongSearchChoiceException("Unknown choice by user with menuType " + CreateArticleMenu.class.getSimpleName());
        };
    }
}
