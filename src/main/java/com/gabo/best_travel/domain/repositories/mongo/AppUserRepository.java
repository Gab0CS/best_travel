package com.gabo.best_travel.domain.repositories.mongo;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.gabo.best_travel.domain.entities.documents.AppUserDocument;

public interface AppUserRepository extends MongoRepository<AppUserDocument,String> {

    Optional<AppUserDocument> findByUsername(String username);
    
}
