
package com.unibg.ticketgenerator.dao;

import com.unibg.ticketgenerator.entities.Ticket;
import com.unibg.ticketgenerator.entities.Utente;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface UtenteRepository extends MongoRepository<Utente, String> {

    Optional<Utente> findByUsername(String username);
    // Per trovare l'utente
    Optional<Utente> findByTicket(Ticket ticket);
    Optional<Utente> findByCf(String cf);
    
}