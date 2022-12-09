package com.unibg.ticketgenerator.dao;

import com.unibg.ticketgenerator.entities.Role;
import com.unibg.ticketgenerator.entities.UserRole;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<UserRole, String> {
        Optional<UserRole> findByName(Role name);

}
