package com.gabo.best_travel.domain.repositories.jpa;

import org.springframework.data.repository.CrudRepository;

import com.gabo.best_travel.domain.entities.jpa.CustomerEntity;

public interface CustomerRepository extends CrudRepository<CustomerEntity, String> {

    
}
