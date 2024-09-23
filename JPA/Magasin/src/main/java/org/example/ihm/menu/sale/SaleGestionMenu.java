package org.example.ihm.menu.sale;

import org.example.exceptions.WrongSearchChoiceException;
import org.example.ihm.enums.navigation.BackMenuOptions;
import org.example.ihm.enums.navigation.MenuType;
import org.example.ihm.input.gestions.SaleUserInput;
import org.example.ihm.menu.BaseMenu;
import org.example.ihm.menu.MainMenu;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static org.example.ihm.enums.navigation.BackMenuOptions.RETURN_TO_MAIN_MENU;

public class SaleGestionMenu extends BaseMenu {
    private final static List<String> menuElements = Arrays.asList("Gestion Ventes",
            "Enregistrer nouvelle vente");
    private final static BackMenuOptions backMenuOption = RETURN_TO_MAIN_MENU;
    public final static int menuLength = setMenuLength(menuElements, backMenuOption);     // TODO seems like a bad practice (setter in abstract method. accessed not through getter)

    public SaleGestionMenu() {
        super(menuElements, backMenuOption);
    }

    public static MenuType menuOptions(int userMenuChoice, Scanner scanner){
        return switch (userMenuChoice) {
            case 1 -> new SaleUserInput(scanner).beginSale();
            case 0 -> MenuType.MAIN_MENU;
            default -> throw new WrongSearchChoiceException("Unknown choice by user with menuType " + MainMenu.class.getSimpleName());
        };
    }

}
