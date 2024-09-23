package org.example.db.repository;

import org.example.db.model.Client;
import org.hibernate.query.Query;

import java.util.List;

public class ClientRepository extends BaseRepository<Client> {
    public ClientRepository() {
        super();
    }

    public List<Client> selectClientByName(String clientName){
        List<Client> clientList;
        Query<Client> sellQuery;

        session = sessionFactory.openSession();
        sellQuery = session.createQuery("from Client where name = :clientName", Client.class);
        sellQuery.setParameter("clientName",clientName);

        clientList = sellQuery.list();
        session.close();
        return clientList;
    }
}
