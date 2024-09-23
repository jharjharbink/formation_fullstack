package org.example.exo.billeterie.ihm.interaction;

import org.example.exo.billeterie.db.DatabaseAction;
import org.example.exo.billeterie.db.model.Address;
import org.example.exo.billeterie.ihm.MenuType;
import org.example.exo.billeterie.util.exceptions.WrongSearchChoiceException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import static org.example.exo.billeterie.db.DatabaseAction.READ_BY_ID_ACTION;

public abstract class BaseInteraction implements Interaction{
    protected Scanner scanner;

    protected BaseInteraction(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void dispatcher(MenuType menuType) {
        switch (menuType){
            case CREATE_MENU -> displayCreate();
            case READ_ALL_MENU -> displayGetAll();
            case READ_BY_ID_MENU -> displayGetByID(READ_BY_ID_ACTION);
            case UPDATE_MENU -> displayUpdate();
            case DELETE_MENU -> displayDelete();
            default -> throw new WrongSearchChoiceException("Unknown choice in dispatcher: " + menuType);
        }
    }

    public void displayCreate() {}
    public <T> T displayGetByID(DatabaseAction action){return null;}
    public void displayGetAll(){}
    public void displayUpdate(){}
    public void displayDelete(){}

    protected String getActionString(DatabaseAction action){      // TODO find better name for method name
        return switch (action){
            case CREATE_ACTION -> "créer";
            case READ_ACTION -> "lire";
            case READ_ALL_ACTION -> "tout lire";
            case READ_BY_ID_ACTION -> "lire par ID";
            case UPDATE_ACTION -> "changer";
            case DELETE_ACTION -> "supprimer";
            default -> throw new WrongSearchChoiceException("Unknown choice in getActionString: " + action);
        };
    }

    protected Address createAddress(){
        System.out.println("Quel est son addresse ?");

        String city = askString("ville");
        String street = askString("Voie");
        int streetNbr = askInt("numéro de rue");

        return new Address(streetNbr, street, city);
    }

    protected Date askDate(){
        String dateString = askString("Quel est sa date ? (format 13/01/2001)");

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date;
        try {
            date = formatter.parse(dateString);
            System.out.println("Converted Date: " + date);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please enter the date in the format MM/dd/yyyy.");
            throw new RuntimeException(e);
        }
        return date;
    }

    protected String askString(String message){
        System.out.println(message);
        return scanner.nextLine();
    }

    protected int askInt(String message){
        System.out.println(message);
        int userInput = scanner.nextInt();
        scanner.nextLine();
        return userInput;
    }

    protected long askLong(String message){
        System.out.println(message);
        long userInput = scanner.nextLong();
        scanner.nextLine();
        return userInput;
    }

    protected void displayBddAction(boolean creation){
        String actionState;
        if (creation)
            actionState = "créer";
        else
            actionState = "échec de la création";
        System.out.println(actionState);
    }

    protected <T> void displayList(List<T> itemList){
        for (T item : itemList)
            System.out.println(item);
    }

}
