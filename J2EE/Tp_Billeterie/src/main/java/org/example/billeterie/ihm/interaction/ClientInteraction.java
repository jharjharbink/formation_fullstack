package org.example.exo.billeterie.ihm.interaction;

import org.example.exo.billeterie.db.DatabaseAction;
import org.example.exo.billeterie.db.model.Address;
import org.example.exo.billeterie.db.model.Client;
import org.example.exo.billeterie.db.model.Event;
import org.example.exo.billeterie.db.repository.ClientRepository;
import org.example.exo.billeterie.ihm.MenuType;
import org.example.exo.billeterie.util.EntityManagerSingleton;

import java.util.List;
import java.util.Scanner;

import static org.example.exo.billeterie.db.DatabaseAction.READ_BY_ID_ACTION;
import static org.example.exo.billeterie.db.DatabaseAction.UPDATE_ACTION;

public class ClientInteraction extends BaseInteraction {

    private final ClientRepository clientRepository;

    public ClientInteraction(Scanner scanner) {
        super(scanner);
        this.clientRepository = new ClientRepository(EntityManagerSingleton.getEntityManager());
    }

    public void displayCreate() {
        String clientName = askString("Quel est son nom ?");
        String clientSurname = askString("Quel est son prénom ?");
        int clientAge = askInt("Quel est son age ?");
        String clientPhoneNbr = askString("Quel est son numéro de tel ?");

        Address clientAddress = createAddress();
        Client client = new Client(clientName, clientSurname, clientAge, clientPhoneNbr, clientAddress);

        boolean creation = clientRepository.create(client);
        displayBddAction(creation);
    }

    public Client displayGetByID(DatabaseAction action) {
        String actionString = getActionString(action);
        long clientId = askLong("Quel est l'id du client que vous voulez " + actionString + " ?");

        Client client = clientRepository.selectById(Client.class, clientId);
        System.out.println(client);
        return client;
    }

    public void displayGetAll() {
        List<Client> clientList = clientRepository.selectAll(Client.class);
        displayList(clientList);
    }

    public void displayUpdate() {

        System.out.println("Quel est l'élement que vous voulez changer ? (nom, prenom, age, tel adresse)");  // TODO make menu
        String columnChoice = scanner.nextLine();

        Client client = displayGetByID(UPDATE_ACTION);

        System.out.println("saisissez son " + columnChoice);
        switch(columnChoice){
            case "nom":
                String newName = scanner.nextLine();
                client.setName(newName);
                break;
            case "prenom":
                String newSurname = scanner.nextLine();
                client.setSurname(newSurname);
                break;
            case "age":
                int newAge = scanner.nextInt();
                client.setAge(newAge);
                break;
            case "tel":
                String newTel = scanner.nextLine();
                client.setPhoneNbr(newTel);
                break;
            case "adresse":
                Address newAddress = createAddress();
                client.setAddress(newAddress);
        }
        boolean modification = clientRepository.update(client);
        displayBddAction(modification);
    }

    public void displayDelete() {
        long clientId = askLong("Quel l'id du client que vous voulez supprimer ?");

        Client client = clientRepository.selectById(Client.class, clientId);
        boolean deletion = clientRepository.delete(client);
        displayBddAction(deletion);
    }
}
