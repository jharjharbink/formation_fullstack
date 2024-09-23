package org.example.exo.billeterie.ihm;

import org.example.exo.billeterie.ihm.interaction.ClientInteraction;
import org.example.exo.billeterie.ihm.interaction.EventInteraction;
import org.example.exo.billeterie.ihm.interaction.TicketInteraction;
import org.example.exo.billeterie.ihm.menu.*;
import org.example.exo.billeterie.util.exceptions.ExitProgramException;
import org.example.exo.billeterie.util.exceptions.ReturnToMainMenuException;
import org.example.exo.billeterie.util.exceptions.WrongSearchChoiceException;


import static org.example.exo.billeterie.ihm.MenuType.*;

import java.util.Scanner;




// TODO create enum for DatabaseAction and use it with DatabaseObject for update & delete
// TODO make menu for update choice in displayUpdateClient()

public class IHM {
    Scanner scanner;

    public IHM() {
        this.scanner = new Scanner(System.in);
    }

    public void start(MenuType menuType) {
        while (true) {
            try {
                askUserMenuChoice(menuType);
            } catch (ReturnToMainMenuException | WrongSearchChoiceException e) {
            } catch (ExitProgramException e) {
                scanner.close();
                break;
            } catch (Exception e) {
                System.out.println(e);
                scanner.close();
                break;
            } finally {
                menuType = MAIN_MENU;
            }
        }
    }

    private int menuChoiceController(MenuType menuType) {
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

            } catch (Exception e) {
                System.out.print("Saisie incorrecte ! (pas dans les choix disponibles)");
                continue;
            }
            break;
        }
        return userChoice;
    }

    private void askUserMenuChoice(MenuType menuType) throws ReturnToMainMenuException, WrongSearchChoiceException, ExitProgramException {
        System.out.println(displayMenu(menuType));
        int userMenuChoice = menuChoiceController(menuType);
        menuDispatcher(menuType, userMenuChoice);
    }

    private void menuDispatcher(MenuType menuType, int userMenuChoice) throws ExitProgramException, ReturnToMainMenuException {
        switch (menuType){
            case MAIN_MENU:
                switch (userMenuChoice) {   // TODO check if should be in MainMenu Class
                    case 1 -> start(CREATE_MENU);
                    case 2 -> start(READ_MENU);
                    case 3 -> start(UPDATE_MENU);
                    case 4 -> start(DELETE_MENU);
                    case 0 -> throw new ExitProgramException();
                    default -> throw new WrongSearchChoiceException("Unknown choice by user with menuType " + menuType);
                }
                break;
            case READ_MENU:
                switch (userMenuChoice) {   // TODO check if should be in ReadMenu Class
                    case 1 -> start(READ_ALL_MENU);
                    case 2 -> start(READ_BY_ID_MENU);
                    case 0 -> throw new ReturnToMainMenuException();
                    default -> throw new WrongSearchChoiceException("Unknown choice by user with menuType " + menuType);
                }
                break;
            case CREATE_MENU, READ_ALL_MENU, READ_BY_ID_MENU, UPDATE_MENU, DELETE_MENU:
                switch (userMenuChoice) {   // TODO check if should be in BaseMenu Class
                    case 1 -> new ClientInteraction(scanner).dispatcher(menuType);
                    case 2 -> new EventInteraction(scanner).dispatcher(menuType);
                    case 3 -> new TicketInteraction(scanner).dispatcher(menuType);
                    case 0 -> throw new ReturnToMainMenuException();
                    default -> throw new WrongSearchChoiceException("Unknown choice by user with menuType " + menuType);
                }
                break;
            default: throw new WrongSearchChoiceException("Wrong menuType in getMenuChoice");
        }
    }

    private int getLimitMaxChoice(MenuType menuType){
        return switch (menuType){
            case MAIN_MENU -> 4;
            case CREATE_MENU,
                 DELETE_MENU,
                 READ_ALL_MENU,
                 READ_BY_ID_MENU,
                 UPDATE_MENU -> 3;
            case READ_MENU -> 2;
            default -> throw new WrongSearchChoiceException("Wrong menuType in getLimitMaxChoice");
        };
    }

    private String displayMenu(MenuType menuType){
        if (menuType == MAIN_MENU)
            return MainMenu.display();

        if (menuType == READ_MENU)
            return ReadMenu.display();

        String actionToDisplay = getActionToDisplay(menuType);
        return ActionMenu.display(actionToDisplay);
    }

    private static String getActionToDisplay(MenuType menuType){    // TODO find better name for method name
        return switch (menuType) {
            case CREATE_MENU -> "créer";
            case READ_ALL_MENU -> "tout récupérer";
            case READ_BY_ID_MENU -> "sélectionner";
            case UPDATE_MENU -> "mise à jour";
            case DELETE_MENU -> "supprimer";
            default -> throw new WrongSearchChoiceException("Unknown choice by user");
        };
    }

}