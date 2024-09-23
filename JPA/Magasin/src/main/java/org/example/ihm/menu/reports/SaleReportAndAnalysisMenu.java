package org.example.ihm.menu.reports;

import org.example.exceptions.WrongSearchChoiceException;
import org.example.ihm.enums.navigation.BackMenuOptions;
import org.example.ihm.enums.navigation.MenuType;
import org.example.ihm.input.gestions.ReportUserInput;
import org.example.ihm.menu.BaseMenu;
import org.example.ihm.menu.MainMenu;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static org.example.ihm.enums.navigation.BackMenuOptions.RETURN_TO_MAIN_MENU;

public class SaleReportAndAnalysisMenu extends BaseMenu {

    private final static List<String> menuElements = Arrays.asList("Rapports et Analyses de ventes",
            "Ventes par produit",
            "Ventes par période",
            "Ventes par Client");
    private final static BackMenuOptions backMenuOption = RETURN_TO_MAIN_MENU;
    public final static int menuLength = setMenuLength(menuElements, backMenuOption);     // TODO seems like a bad practice (setter in abstract method. accessed not through getter)

    public SaleReportAndAnalysisMenu() {
        super(menuElements, backMenuOption);
    }

    public static MenuType menuOptions(int userMenuChoice, Scanner scanner) {
        switch (userMenuChoice) {
            case 1:
                return new ReportUserInput(scanner).askArticleSaleByArticleId();
            case 2:
                return MenuType.SALE_BY_PERIOD_MENU;
            case 3 :
                System.out.println("Ventes par  Client");
                return MenuType.SALE_GESTION_MENU;
            case 0:
                 return MenuType.MAIN_MENU;
            //case 0 -> throw new ReturnToMainMenuException();
            default:
                throw new WrongSearchChoiceException("Unknown choice by user with menuType " + MainMenu.class.getSimpleName());
        }
    }
}
