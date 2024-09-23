package org.example.exo.billeterie.ihm.menu;

public class ActionMenu extends BaseMenu{
    public static String display(String actionToDisplay){
        return headline(actionToDisplay) +
                "1. Client.\n" +
                "2. Evènement.\n" +
                "3. Ticket.\n" +
                "0. Retour au menu principal";
    }
}
