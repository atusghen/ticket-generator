package com.unibg.ticketgenerator.dao;

import java.util.Optional;

import com.unibg.ticketgenerator.security.model.ERole;
import com.unibg.ticketgenerator.security.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface RoleRepository extends MongoRepository<Role, String> {
    Optional<Role> findByName(ERole name);
}