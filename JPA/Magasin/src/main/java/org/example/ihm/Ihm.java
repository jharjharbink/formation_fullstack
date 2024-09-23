package org.example.ihm;

import org.example.exceptions.*;
import org.example.ihm.enums.navigation.MenuType;
import org.example.ihm.menu.*;
import org.example.ihm.menu.article.*;
import org.example.ihm.menu.article.update.ArticleUpdateMenu;
import org.example.ihm.menu.article.update.ClotheUpdateMenu;
import org.example.ihm.menu.article.update.ElectronicUpdateMenu;
import org.example.ihm.menu.article.update.FoodUpdateMenu;
import org.example.ihm.menu.client.ClientGestionMenu;
import org.example.ihm.menu.client.ClientUpdateMenu;
import org.example.ihm.menu.client.ConsultClientMenu;
import org.example.ihm.menu.sale.SaleMenu;
import org.example.ihm.menu.reports.SaleByPeriodMenu;
import org.example.ihm.menu.sale.SaleGestionMenu;
import org.example.ihm.menu.reports.SaleReportAndAnalysisMenu;

import java.util.Scanner;

import static org.example.ihm.menu.BaseMenu.menuOptionDispatcher;

public class Ihm {

    protected static Scanner scanner = new Scanner(System.in);

    public static void start(MenuType menuType) {
        while (true) {
            try {
                menuType = displayMenuChoice(menuType);
            } catch (ReturnToInventoryGestionMenuException e) {
                menuType = MenuType.ARTICLE_GESTION_MENU;
            } catch (ExitProgramException e) {
                scanner.close();
                return;
            } catch (Exception e) {
                System.out.println(e);
                scanner.close();
                return;
            }
        }
    }

    private static MenuType displayMenuChoice(MenuType menuType) throws WrongSearchChoiceException, ExitProgramException {
        int userMenuChoice = askUserMenuChoice(menuType);
        return menuOptionDispatcher(menuType, userMenuChoice, scanner);
    }

    public static int askUserMenuChoice(MenuType menuType){
        displayMenu(menuType);
        return menuChoiceController(menuType);
    }

    private static void displayMenu(MenuType menuType){
        switch(menuType){
            case MAIN_MENU -> new MainMenu().display();
            case ARTICLE_GESTION_MENU -> new ArticleGestionMenu().display();
            case ARTICLE_CREATION_MENU -> new CreateArticleMenu().display();
            case ARTICLE_CONSULT_MENU -> new ConsultArticleMenu().display();
            case SALE_GESTION_MENU -> new SaleGestionMenu().display();
            case SALE_MENU -> new SaleMenu().display();
            case CLIENT_GESTION_MENU -> new ClientGestionMenu().display();
            case CLIENT_CONSULT_MENU -> new ConsultClientMenu().display();
            case SALE_REPORT_AND_ANALYSIS_MENU -> new SaleReportAndAnalysisMenu().display();
            case SALE_BY_PERIOD_MENU -> new SaleByPeriodMenu().display();
            case CLIENT_UPDATE_MENU -> new ClientUpdateMenu().display();
            case CLOTHE_CATEGORY_MENU -> new ClotheCategoryMenu().display();
            case CLOTHE_SIZE_MENU -> new ClotheSizeMenu().display();
            case ARTICLE_UPDATE_MENU -> new ArticleUpdateMenu().display();
            case CLOTHE_UPDATE_MENU -> new ClotheUpdateMenu().display();
            case ELECTRONIC_UPDATE_MENU -> new ElectronicUpdateMenu().display();
            case FOOD_UPDATE_MENU -> new FoodUpdateMenu().display();
            default -> throw new WrongSearchChoiceException("Wrong menuType in displayMenu");
        }
    }

    private static int getLimitMaxChoice(MenuType menuType){    // TODO put in InputControl class
        return switch (menuType){
            case MAIN_MENU -> MainMenu.menuLength;
            case ARTICLE_GESTION_MENU -> ArticleGestionMenu.menuLength;
            case ARTICLE_CREATION_MENU -> CreateArticleMenu.menuLength;
            case ARTICLE_CONSULT_MENU -> ConsultArticleMenu.menuLength;
            case SALE_GESTION_MENU -> SaleGestionMenu.menuLength;
            case SALE_MENU -> SaleMenu.menuLength;
            case CLIENT_GESTION_MENU -> ClientGestionMenu.menuLength;
            case CLIENT_CONSULT_MENU -> ConsultClientMenu.menuLength;
            case SALE_REPORT_AND_ANALYSIS_MENU -> SaleReportAndAnalysisMenu.menuLength;
            case SALE_BY_PERIOD_MENU -> SaleByPeriodMenu.menuLength;
            case CLIENT_UPDATE_MENU -> ClientUpdateMenu.menuLength;
            case CLOTHE_CATEGORY_MENU -> ClotheCategoryMenu.menuLength;
            case CLOTHE_SIZE_MENU -> ClotheSizeMenu.menuLength;
            case ARTICLE_UPDATE_MENU -> ArticleUpdateMenu.menuLength;
            case CLOTHE_UPDATE_MENU -> ClotheUpdateMenu.menuLength;
            case ELECTRONIC_UPDATE_MENU -> ElectronicUpdateMenu.menuLength;
            case FOOD_UPDATE_MENU -> FoodUpdateMenu.menuLength;
            default -> throw new WrongSearchChoiceException("Wrong menuType in getLimitMaxChoice");
        };
    }

    private static int menuChoiceController(MenuType menuType) {    // TODO put in InputControl class
        int limitMax = getLimitMaxChoice(menuType);
        int userChoice;
        while (true) {
            try {
                userChoice = Integer.parseInt(scanner.nextLine());
                if (userChoice < 0 || userChoice > limitMax) {
                    throw new Exception("Vous devez rentrer un chiffre compris entre 0 et " + limitMax + ".");
                }

            } catch (NumberFormatException e) {
                System.out.print("Saisie incorrecte ! (pas un entier)");
                continue;

            //} catch (IllegalStateException e) {
            //    break;
            } catch (Exception e) {
                System.out.print("Saisie incorrecte ! (pas dans les choix disponibles)");
                continue;
            }
            break;
        }
        return userChoice;
    }


}
