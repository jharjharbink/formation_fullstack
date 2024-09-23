package org.example.ihm.menu;

import org.example.exceptions.WrongSearchChoiceException;
import org.example.ihm.enums.navigation.BackMenuOptions;
import org.example.ihm.enums.navigation.MenuType;
import org.example.ihm.menu.article.*;
import org.example.ihm.menu.article.update.ArticleUpdateMenu;
import org.example.ihm.menu.client.ClientGestionMenu;
import org.example.ihm.menu.client.ConsultClientMenu;
import org.example.ihm.menu.sale.SaleMenu;
import org.example.ihm.menu.reports.SaleByPeriodMenu;
import org.example.ihm.menu.sale.SaleGestionMenu;
import org.example.ihm.menu.reports.SaleReportAndAnalysisMenu;

import java.util.List;
import java.util.Scanner;

import static org.example.ihm.enums.navigation.BackMenuOptions.NO_RETURN;

public abstract class BaseMenu implements Menus{

    protected static String menuString;     // TODO check if should be set through another method/way

    protected BaseMenu(List<String> menuElements, BackMenuOptions backMenuOption) {
        BaseMenu.menuString = makeMenu(menuElements, backMenuOption);
    }

    public void display(){
        System.out.println(menuString);
    }

    public static MenuType menuOptionDispatcher(MenuType menuType, int userMenuChoice, Scanner scanner){
        return switch (menuType){
            case MAIN_MENU -> MainMenu.menuOptions(userMenuChoice);
            case ARTICLE_GESTION_MENU -> ArticleGestionMenu.menuOptions(userMenuChoice, scanner);
            case ARTICLE_CREATION_MENU -> CreateArticleMenu.menuOptions(userMenuChoice, scanner);
            case ARTICLE_CONSULT_MENU -> ConsultArticleMenu.menuOptions(userMenuChoice, scanner);
            case SALE_GESTION_MENU -> SaleGestionMenu.menuOptions(userMenuChoice, scanner);
            // case MAKE_SALE_MENU -> SaleMenu.menuOptions(userMenuChoice, scanner);
            case CLIENT_GESTION_MENU -> ClientGestionMenu.menuOptions(userMenuChoice, scanner);
            case CLIENT_CONSULT_MENU -> ConsultClientMenu.menuOptions(userMenuChoice, scanner);
            case SALE_REPORT_AND_ANALYSIS_MENU -> SaleReportAndAnalysisMenu.menuOptions(userMenuChoice, scanner);
            case SALE_BY_PERIOD_MENU -> SaleByPeriodMenu.menuOptions(userMenuChoice, scanner);
            case ARTICLE_UPDATE_MENU -> ArticleUpdateMenu.menuOptions(userMenuChoice, scanner);
            // case CLIENT_UPDATE_MENU -> ClientUpdateMenu.menuOptions(userMenuChoice);
            default -> throw new WrongSearchChoiceException("Wrong menuType in getMenuChoice");
        };
    }

    protected static int setMenuLength(List<String> menuElements, BackMenuOptions backMenuOption){
        if (backMenuOption != NO_RETURN)
            return menuElements.size() + 1;
        return menuElements.size();
    }

    protected String makeMenu(List<String> menuElements, BackMenuOptions backMenuOption) {
        String menuString = makeLines(menuElements);
        return menuString + addReturnChoice(backMenuOption);
    }

    private String makeLines(List<String> menuElements){
        String menuString = "";
        for(int i=0; i < menuElements.size(); i++)
            menuString += addHeadLineOrLine(menuElements.get(i), i);
        return menuString;
    }

    private String addHeadLineOrLine(String headLineOrLine, int menuElementIndex){
        if(menuElementIndex ==0)
            return addHeadline(headLineOrLine);
        else
            return addLine(headLineOrLine, menuElementIndex);
    }

    private String addHeadline(String headlineString){
        return "\n=== Menu " + headlineString + " ===\n\n";
    }

    private String addLine(String line, int menuElementIndex){
        return menuElementIndex + ". " + line + "\n";
    }

    private String addReturnChoice(BackMenuOptions backMenuOption){
        String prefixString = "0. ";
        return switch(backMenuOption){
            case CLOSE_PROGRAM -> prefixString + "Fermer le programme\n";
            case RETURN_TO_MAIN_MENU -> prefixString + "Retour Au menu Principal\n";
            case RETURN_TO_ARTICLE_GESTION_MENU -> prefixString + "Retour à la gestion d'inventaire\n";
            case RETURN_TO_SALE_GESTION_MENU -> prefixString + "Abbandonner Vente\n";
            case RETURN_TO_CLIENT_GESTION_MENU -> prefixString + "Retour à la gestion Client\n";
            case RETURN_TO_INVENTORY_CONSULT_MENU -> prefixString + "Retour à la consultation d'articles";
            case RETURN_TO_SALE_REPORT_AND_ANALYSIS_MENU -> prefixString + "Retour aux rapports et analyses de ventes";
            case NO_RETURN -> "";
            default -> throw new WrongSearchChoiceException("Wrong value in addReturnChoice for option: " + backMenuOption + "\n");
        };
    }
}
