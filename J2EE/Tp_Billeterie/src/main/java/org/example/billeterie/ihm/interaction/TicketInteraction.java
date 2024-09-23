package org.example.exo.billeterie.ihm.interaction;

import org.example.exo.billeterie.db.DatabaseAction;
import org.example.exo.billeterie.db.model.Client;
import org.example.exo.billeterie.db.model.Event;
import org.example.exo.billeterie.db.model.Ticket;
import org.example.exo.billeterie.db.repository.ClientRepository;
import org.example.exo.billeterie.db.repository.TicketRepository;
import org.example.exo.billeterie.ihm.MenuType;
import org.example.exo.billeterie.util.EntityManagerSingleton;

import java.util.List;
import java.util.Scanner;

import static org.example.exo.billeterie.db.DatabaseAction.READ_BY_ID_ACTION;
import static org.example.exo.billeterie.db.DatabaseAction.UPDATE_ACTION;

public class TicketInteraction extends BaseInteraction {

    private TicketRepository ticketRepository;

    public TicketInteraction(Scanner scanner) {
        super(scanner);
        this.ticketRepository = new TicketRepository(EntityManagerSingleton.getEntityManager());
    }

    @Override
    public void displayCreate() {
        long eventId = askLong("Quel est l'id de l'évent ?");
        long clientId = askLong("Quel est l'id de l'évent ?");
        String type = askString("quel est son type ? (standard, gold ou VIP)");

        // TODO check if I should create a Service for below code
        int userPlaceNumber = ticketRepository.listTicketsByEvent(eventId).size();
        Event event = ticketRepository.selectById(Event.class, eventId);
        Client client = ticketRepository.selectById(Client.class, clientId);

        Ticket ticket = new Ticket(userPlaceNumber, type, client, event);
        boolean creation = ticketRepository.create(ticket);

        // TODO's below code stop here
        displayBddAction(creation);

    }

    @Override
    public Ticket displayGetByID(DatabaseAction action) {
        String actionString = getActionString(action);
        long ticketId = askLong("Quel est l'id du ticket que vous voulez " + actionString + " ?");

        Ticket ticket = ticketRepository.selectById(Ticket.class, ticketId);
        System.out.println(ticket);
        return ticket;
    }

    @Override
    public void displayGetAll() {
        List<Ticket> ticketList = ticketRepository.selectAll(Ticket.class);
        displayList(ticketList);
    }

    @Override
    public void displayUpdate() {
        String columnChoice = askString("Quel est l'élement que vous voulez changer ? (place, type, client, event)");

        Ticket ticket = displayGetByID(UPDATE_ACTION);

        System.out.print("saisissez son " + columnChoice);
        switch(columnChoice){
            case "nom":
                int newClientPlaceNumber = scanner.nextInt();
                scanner.nextLine();
                ticket.setUserPlaceNumber(newClientPlaceNumber);
                break;
            case "type":
                String newType = scanner.nextLine();  //TODO make menu for type
                scanner.nextLine();
                ticket.setType(newType);
                break;
            case "client":
                Client client = new ClientInteraction(scanner).displayGetByID(READ_BY_ID_ACTION);
                ticket.setClient(client);  // TODO remove old_client-ticket link
                break;
            case "event":
                Event event = new EventInteraction(scanner).displayGetByID(READ_BY_ID_ACTION); // TODO remove old_event-ticket link
                ticket.setEvent(event);
                break;
        }
        System.out.println();
        boolean modification = ticketRepository.update(ticket);
        displayBddAction(modification);
    }

    @Override
    public void displayDelete() {
        long ticketId = askLong("Quel l'id du ticket que vous voulez supprimer ?");

        Ticket ticket = ticketRepository.selectById(Ticket.class, ticketId);
        boolean deletion = ticketRepository.delete(ticket);
        displayBddAction(deletion);
    }
}
