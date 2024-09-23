package org.example.exo.billeterie.ihm.interaction;

import org.example.exo.billeterie.db.DatabaseAction;
import org.example.exo.billeterie.db.model.Address;
import org.example.exo.billeterie.db.model.Client;
import org.example.exo.billeterie.db.model.Event;
import org.example.exo.billeterie.db.repository.EventRepository;
import org.example.exo.billeterie.ihm.MenuType;
import org.example.exo.billeterie.util.EntityManagerSingleton;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import static org.example.exo.billeterie.db.DatabaseAction.READ_BY_ID_ACTION;
import static org.example.exo.billeterie.db.DatabaseAction.UPDATE_ACTION;

public class EventInteraction extends BaseInteraction {

    private EventRepository eventRepository;

    public EventInteraction(Scanner scanner) {
        super(scanner);
        this.eventRepository = new EventRepository(EntityManagerSingleton.getEntityManager());
    }

    public void displayCreate() {
        String name = askString("Quel est son nom ?");
        int placeNbr = askInt("Quel est son nombre de place ?");
        Date date = askDate();
        String heureString = askString("à quel heure a t'il lieu ? (format 13h45)");
        Address address = createAddress();

        Event event = new Event(name, placeNbr, date, heureString, address);
        boolean creation = eventRepository.create(event);
        displayBddAction(creation);
    }

    public Event displayGetByID(DatabaseAction action) {
        String actionString = getActionString(action);
        long eventId = askLong("Quel est l'id de l'event que vous voulez " + actionString + " ?");

        Event event = eventRepository.selectById(Event.class, eventId);
        System.out.println(event);
        return event;
    }

    @Override
    public void displayGetAll() {
        List<Event> clientList = eventRepository.selectAll(Event.class);
        displayList(clientList);
    }

    @Override
    public void displayUpdate() {
        String columnChoice = askString("Quel est l'élement que vous voulez changer ? (nom, places, date, heure, adresse)");

        Event event = displayGetByID(UPDATE_ACTION);

        System.out.println("saisissez son " + columnChoice);
        switch(columnChoice){
            case "nom":
                String newName = scanner.nextLine();
                event.setName(newName);
                break;
            case "places":
                int newSurname = scanner.nextInt();
                scanner.nextLine();
                event.setPlaceNbr(newSurname);
                break;
            case "date":
                Date date = askDate();
                event.setDate(date);
                break;
            case "heure":
                String heure = scanner.nextLine();
                event.setHeure(heure);
                break;
            case "adresse":
                Address newAdress = createAddress();
                event.setAdress(newAdress);
        }
        boolean modification = eventRepository.update(event);
        displayBddAction(modification);
    }

    @Override
    public void displayDelete() {
        long clientId = askLong("Quel l'id du client que vous voulez supprimer ?");
        Client client = eventRepository.selectById(Client.class, clientId);
        boolean deletion = eventRepository.delete(client);
        displayBddAction(deletion);
    }
}
