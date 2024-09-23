package org.example.exo.billeterie.ihm.menu;

import org.example.exo.billeterie.ihm.MenuType;
import org.example.exo.billeterie.ihm.MenuType.*;
import org.example.exo.billeterie.util.exceptions.WrongSearchChoiceException;

import static org.example.exo.billeterie.ihm.MenuType.CREATE_MENU;
import static org.example.exo.billeterie.ihm.MenuType.READ_ALL_MENU;
import static org.example.exo.billeterie.ihm.MenuType.READ_BY_ID_MENU;
import static org.example.exo.billeterie.ihm.MenuType.UPDATE_MENU;
import static org.example.exo.billeterie.ihm.MenuType.DELETE_MENU;



public class BaseMenu {
    protected static String headline(String headlineString){
        return "\n=== Menu " + headlineString + " ===\n\n";
    }
}
