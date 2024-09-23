package org.example.ihm.menu.sale;

import org.example.db.SaleState;
import org.example.exceptions.WrongSearchChoiceException;
import org.example.ihm.enums.navigation.BackMenuOptions;
import org.example.ihm.enums.navigation.MenuType;
import org.example.ihm.enums.navigation.SaleGestion;
import org.example.ihm.input.gestions.SaleUserInput;
import org.example.ihm.menu.BaseMenu;
import org.example.ihm.menu.MainMenu;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static org.example.ihm.enums.navigation.BackMenuOptions.RETURN_TO_SALE_GESTION_MENU;


public class SaleMenu extends BaseMenu {
    private final static List<String> menuElements = Arrays.asList("Vente",
            "Enregistrer nouvel Article dans la vente",
            "Arrêter vente");
    private final static BackMenuOptions backMenuOption = RETURN_TO_SALE_GESTION_MENU;
    public final static int menuLength = setMenuLength(menuElements, backMenuOption);     // TODO seems like a bad practice (setter in abstract method. accessed not through getter)

    public SaleMenu() {
        super(menuElements, backMenuOption);
    }

    public static SaleGestion menuOptions(int userMenuChoice){
        return switch (userMenuChoice) {
            case 1 -> SaleGestion.CONTINUE;
            case 2 -> SaleGestion.STOP;
            case 0 -> SaleGestion.ABANDON;
            default -> throw new WrongSearchChoiceException("Unknown choice by user with menuType " + MainMenu.class.getSimpleName());
        };
    }

}
