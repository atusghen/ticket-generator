package com.unibg.ticketgenerator.dao;

import com.unibg.ticketgenerator.entities.TipoA;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TipoARepository extends MongoRepository<TipoA, String> {
}