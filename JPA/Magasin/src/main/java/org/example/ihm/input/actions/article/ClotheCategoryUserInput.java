package org.example.ihm.input.actions.article;

import org.example.db.ClotheCategory;
import org.example.ihm.Ihm;
import org.example.ihm.menu.article.ClotheCategoryMenu;

import java.util.Scanner;

import static org.example.ihm.enums.navigation.MenuType.CLOTHE_CATEGORY_MENU;

public class ClotheCategoryUserInput implements UserInput<ClotheCategory> {
    private final Scanner scanner;

    public ClotheCategoryUserInput(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public ClotheCategory askValue(String message) {
        int userChoice1 = Ihm.askUserMenuChoice(CLOTHE_CATEGORY_MENU);
        return ClotheCategoryMenu.menuOptions(userChoice1);
    }
}
