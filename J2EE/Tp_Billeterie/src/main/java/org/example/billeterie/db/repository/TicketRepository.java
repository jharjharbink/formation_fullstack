package org.example.exo.billeterie.db.repository;

import org.example.exo.billeterie.db.model.Event;
import org.example.exo.billeterie.db.model.Ticket;

import javax.persistence.EntityManager;
import java.util.List;

public class TicketRepository extends BaseRepository<Ticket> {
    public TicketRepository(EntityManager em) {
        super(em);
    }

    public List<Ticket> listTicketsByEvent(long eventId){
        Event event = em.find(Event.class, eventId);
        return event.getTickets();
    }
}
