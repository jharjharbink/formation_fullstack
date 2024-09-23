package org.example.ihm.input.actions.article;

import org.example.db.Size;
import org.example.ihm.Ihm;
import org.example.ihm.menu.article.ClotheSizeMenu;

import java.util.Scanner;

import static org.example.ihm.enums.navigation.MenuType.CLOTHE_SIZE_MENU;

public class ClotheSizeUserInput implements UserInput<Size> {

    private final Scanner scanner;

    public ClotheSizeUserInput(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public Size askValue(String message) {
        int userChoice = Ihm.askUserMenuChoice(CLOTHE_SIZE_MENU);
        return ClotheSizeMenu.menuOptions(userChoice);
    }
}

