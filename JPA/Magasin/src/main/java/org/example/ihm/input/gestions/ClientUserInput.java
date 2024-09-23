package org.example.ihm.input.gestions;

import org.example.db.model.Client;
import org.example.exceptions.NotFoundException;
import org.example.exceptions.WrongSearchChoiceException;
import org.example.ihm.enums.update.client.ClientUpdateChoices;
import org.example.ihm.Ihm;
import org.example.ihm.enums.navigation.MenuType;
import org.example.ihm.menu.client.ClientUpdateMenu;
import org.example.service.ClientService;

import java.util.List;
import java.util.Scanner;

import static org.example.ihm.enums.navigation.MenuType.CLIENT_GESTION_MENU;
import static org.example.ihm.enums.navigation.MenuType.CLIENT_UPDATE_MENU;

public class ClientUserInput extends BaseUserInput {

    private ClientService clientService;

    public ClientUserInput(Scanner scanner) {
        super(scanner);
        this.clientService = new ClientService();
    }

    public MenuType askClientCreation(){
        String name = askString("Quel est son nom ?");
        String mail = askString("Quel est son email ?");

        boolean isCreated = clientService.create(name, mail);

        if (isCreated)
            System.out.println("Client Créer");
        else
            System.out.println("Problème à la création");
        return CLIENT_GESTION_MENU;
    }

    public MenuType askClientUpdate() throws NotFoundException {
        ClientService clientService = new ClientService();

        long clientId = askLong("Quel est l'id du client que vous voulez changer ?");

        int userChoice = Ihm.askUserMenuChoice(CLIENT_UPDATE_MENU);
        ClientUpdateChoices itemToChange = ClientUpdateMenu.menuOptions(userChoice);

        String itemToChangeValue;
        switch (itemToChange){
            case ClientUpdateChoices.NAME -> itemToChangeValue = askString("Quel est son nouveau nom ?");
            case ClientUpdateChoices.EMAIL -> itemToChangeValue = askString("Quel est son nouveau email ?");
            case ClientUpdateChoices.STOP_UPDATE -> {return CLIENT_GESTION_MENU;}
            default -> throw new WrongSearchChoiceException("Wrong search choice selection in askClientUpdate()");
        }
        boolean isUpdated = clientService.update(clientId, itemToChange, itemToChangeValue);

        if (isUpdated)
            System.out.println("Client Mis à jour");
        else
            System.out.println("Problème à la mise à jour");
        return CLIENT_GESTION_MENU;
    }

    public MenuType askClientDelete() throws NotFoundException {
        long clientId = askLong("Quel est l'id du client que vous voulez supprimer ?");
        boolean isDeleted = clientService.delete(clientId);

        if (isDeleted)
            System.out.println("Client Supprimer");
        else
            System.out.println("Problème à la suppression");
        return CLIENT_GESTION_MENU;

    }

    public MenuType askClientById() throws NotFoundException {
        long clientId = askLong("Quel est l'id du client que vous voulez consulter ?");
        Client client = clientService.selectById(clientId);

        System.out.println(client);
        return MenuType.CLIENT_CONSULT_MENU;
    }

    public MenuType askAllClient(){
        List<Client> clients = clientService.selectAll();

        for (Client client : clients)
            System.out.println(client);
        return MenuType.CLIENT_CONSULT_MENU;
    }

    public MenuType askClientByName(){
        String clientName = askString("Quel est le nom du client que vous voulez consulter ?");

        List<Client> clients = clientService.selectByName(clientName);

        for (Client client : clients)
            System.out.println(client);
        return MenuType.CLIENT_CONSULT_MENU;
    }
}
