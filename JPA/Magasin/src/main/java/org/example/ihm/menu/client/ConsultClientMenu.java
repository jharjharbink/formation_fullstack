package org.example.ihm.menu.client;

import org.example.exceptions.NotFoundException;
import org.example.exceptions.WrongSearchChoiceException;
import org.example.ihm.enums.navigation.BackMenuOptions;
import org.example.ihm.enums.navigation.MenuType;
import org.example.ihm.input.gestions.ClientUserInput;
import org.example.ihm.menu.BaseMenu;
import org.example.ihm.menu.article.CreateArticleMenu;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static org.example.ihm.enums.navigation.BackMenuOptions.RETURN_TO_CLIENT_GESTION_MENU;

public class ConsultClientMenu extends BaseMenu {

    private final static List<String> menuElements = Arrays.asList("Consultation de Client",
            "Afficher tout",
            "Afficher par id",
            "Afficher par nom");
    private final static BackMenuOptions backMenuOption = RETURN_TO_CLIENT_GESTION_MENU;
    public final static int menuLength = setMenuLength(menuElements, backMenuOption);     // TODO seems like a bad practice (setter in abstract method. accessed not through getter)

    public ConsultClientMenu() {
        super(menuElements, backMenuOption);
    }

    public static MenuType menuOptions(int userMenuChoice, Scanner scanner){
        try{
            return switch (userMenuChoice) {
                case 1 -> new ClientUserInput(scanner).askAllClient();
                case 2 -> new ClientUserInput(scanner).askClientById();
                case 3 -> new ClientUserInput(scanner).askClientByName();
                case 0 -> MenuType.CLIENT_GESTION_MENU;
                //case 0 -> throw new ReturnToClientGestionMenuException();
                default -> throw new WrongSearchChoiceException("Unknown choice by user with menuType " + CreateArticleMenu.class.getSimpleName());
            };
        } catch (NotFoundException e) {
            System.out.println(e);
        }
        return MenuType.MAIN_MENU;
    }
}
