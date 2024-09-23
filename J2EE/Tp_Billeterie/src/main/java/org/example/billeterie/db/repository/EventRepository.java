package org.example.exo.billeterie.db.repository;

import org.example.exo.billeterie.db.model.Event;

import javax.persistence.EntityManager;

public class EventRepository extends BaseRepository<Event>{
    public EventRepository(EntityManager em) {
        super(em);
    }
}
