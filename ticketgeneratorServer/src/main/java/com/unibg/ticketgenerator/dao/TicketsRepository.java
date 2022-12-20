package com.unibg.ticketgenerator.dao;

import com.unibg.ticketgenerator.entities.Ticket;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TicketsRepository extends MongoRepository<Ticket, String> {
}