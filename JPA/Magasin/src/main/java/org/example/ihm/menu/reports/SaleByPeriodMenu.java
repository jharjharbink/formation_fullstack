package org.example.ihm.menu.reports;


import org.example.exceptions.WrongSearchChoiceException;
import org.example.ihm.enums.navigation.BackMenuOptions;
import org.example.ihm.enums.navigation.MenuType;
import org.example.ihm.menu.BaseMenu;
import org.example.ihm.menu.article.CreateArticleMenu;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static org.example.ihm.enums.navigation.BackMenuOptions.RETURN_TO_SALE_REPORT_AND_ANALYSIS_MENU;

public class SaleByPeriodMenu extends BaseMenu {

    private final static List<String> menuElements = Arrays.asList("Ventes par dates",
            "depuis une date",
            "entre deux dates");
    private final static BackMenuOptions backMenuOption = RETURN_TO_SALE_REPORT_AND_ANALYSIS_MENU;
    public final static int menuLength = setMenuLength(menuElements, backMenuOption);     // TODO seems like a bad practice (setter in abstract method. accessed not through getter)

    public SaleByPeriodMenu() {
        super(menuElements, backMenuOption);
    }

    public static MenuType menuOptions(int userMenuChoice, Scanner scanner){
        switch (userMenuChoice) {
            case 1:
                System.out.println("afficher la saisie d'une date");
                return MenuType.SALE_GESTION_MENU;
            case 2:
                System.out.println("afficher la saisie de deux dates");
                return MenuType.SALE_GESTION_MENU;
            case 0:
                return MenuType.SALE_REPORT_AND_ANALYSIS_MENU;
              //  throw new ReturnToSaleReportAndAnalysisMenu();
            default:
                throw new WrongSearchChoiceException("Unknown choice by user with menuType " + CreateArticleMenu.class.getSimpleName());
        }
    }

}
