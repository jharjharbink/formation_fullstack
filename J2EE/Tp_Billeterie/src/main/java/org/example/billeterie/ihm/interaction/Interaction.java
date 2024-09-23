package org.example.exo.billeterie.ihm.interaction;

import org.example.exo.billeterie.db.DatabaseAction;
import org.example.exo.billeterie.ihm.MenuType;

public interface Interaction {
    public void dispatcher(MenuType menuType);
    public void displayCreate();
    public <T> T displayGetByID(DatabaseAction action);
    public void displayGetAll();
    public void displayUpdate();
    public void displayDelete();
}
