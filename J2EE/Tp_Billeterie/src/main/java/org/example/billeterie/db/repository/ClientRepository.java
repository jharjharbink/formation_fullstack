package org.example.exo.billeterie.db.repository;

import org.example.exo.billeterie.db.model.Client;
import org.example.exo.billeterie.db.repository.BaseRepository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class ClientRepository extends BaseRepository<Client> {
    public ClientRepository(EntityManager em) {
        super(em);
    }
}
