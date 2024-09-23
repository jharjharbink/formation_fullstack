package org.example.exo.billeterie.ihm.menu;

public class ReadMenu extends BaseMenu{
    public static String display(){
        return headline("lire") +
                "1. Tout récupérer.\n" +
                "2. Récupérer par id.\n" +
                "0. Retour au menu principal";
    }
}
