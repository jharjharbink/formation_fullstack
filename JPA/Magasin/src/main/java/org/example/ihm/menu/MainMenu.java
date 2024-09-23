package org.example.ihm.menu;

import org.example.exceptions.ExitProgramException;
import org.example.exceptions.WrongSearchChoiceException;
import org.example.ihm.enums.navigation.BackMenuOptions;
import org.example.ihm.enums.navigation.MenuType;


import java.util.Arrays;
import java.util.List;

import static org.example.ihm.enums.navigation.BackMenuOptions.CLOSE_PROGRAM;

public class MainMenu extends BaseMenu implements Menus{

    private final static List<String> menuElements = Arrays.asList("Principal",
            "Gestion de l'inventaire",
            "Gestion des ventes",
            "Gestion des clients",
            "Rapports et analyses de Ventes");
    private final static BackMenuOptions backMenuOption = CLOSE_PROGRAM;
    public final static int menuLength = setMenuLength(menuElements, backMenuOption);     // TODO seems like a bad practice

    public MainMenu() {
        super(menuElements, backMenuOption);
    }

    public static MenuType menuOptions(int userMenuChoice){
        return switch (userMenuChoice) {
            case 1 -> MenuType.ARTICLE_GESTION_MENU;
            case 2 -> MenuType.SALE_GESTION_MENU;
            case 3 -> MenuType.CLIENT_GESTION_MENU;
            case 4 -> MenuType.SALE_REPORT_AND_ANALYSIS_MENU;
            case 0 -> throw new ExitProgramException();
            default -> throw new WrongSearchChoiceException("Unknown choice by user with menuType " + MainMenu.class.getSimpleName());
        };
    }
}
