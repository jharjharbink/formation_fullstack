package org.example.exo.billeterie.ihm.menu;

public class MainMenu extends BaseMenu{
    public static String display(){
        return headline("Principal") +
                "\nVeuillez sélectionner un menu\n" +
                "1. Créer.\n" +
                "2. Lire.\n" +
                "3. Mettre à jour.\n" +
                "4. Delete.\n" +
                "0. Quitter le programme";
    }
}
