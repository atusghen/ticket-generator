package com.unibg.ticketgenerator.dao;

import com.unibg.ticketgenerator.entities.Ticket;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TicketsRepository extends MongoRepository<Ticket, String> {
    @Override
    void delete(Ticket entity);

    @Override
    List<Ticket> findAll();
}