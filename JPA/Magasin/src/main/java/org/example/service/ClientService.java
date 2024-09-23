package org.example.service;

import org.example.db.model.Client;
import org.example.db.repository.ClientRepository;
import org.example.exceptions.NotFoundException;
import org.example.ihm.enums.update.client.ClientUpdateChoices;

import java.util.List;

public class ClientService {
    private ClientRepository clientRepository;

    public ClientService() {
        this.clientRepository = new ClientRepository();
    }

    public boolean create(String name,String email){
        Client customer = Client.builder()
                .name(name)
                .mail(email)
                .build();

        return clientRepository.create(customer);
    }

    public boolean update(long id, ClientUpdateChoices itemToChange, String itemToChangeValue) {

        Client client = clientRepository.selectById(Client.class, id);

        if (client == null)
            return false;

        if (itemToChange == ClientUpdateChoices.NAME)
            client.setName(itemToChangeValue);
        else
            client.setMail(itemToChangeValue);

        return clientRepository.update(client);
    }

    public boolean delete (long id) throws NotFoundException {
        Client client = clientRepository.selectById(Client.class, id);
        if(client != null){
            return clientRepository.delete(client);
        }
        throw new NotFoundException("can't find client with id: " + id);
    }

    public Client selectById (long id) throws NotFoundException {
        Client client = clientRepository.selectById(Client.class, id);
        if(client != null){
            return client;
        }
        throw new NotFoundException("can't find client with id: " + id);
    }

    public List<Client> selectAll (){
        return clientRepository.selectAll(Client.class);
    }

    public List<Client> selectByName(String name){
        return clientRepository.selectClientByName(name);
    }



}
