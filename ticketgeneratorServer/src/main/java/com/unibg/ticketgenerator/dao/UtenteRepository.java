package com.unibg.ticketgenerator.dao;

import com.unibg.ticketgenerator.entities.TipoA;
import com.unibg.ticketgenerator.entities.Utente;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UtenteRepository extends MongoRepository<Utente, String> {

    Optional<Utente> findByUsername(String username);
}