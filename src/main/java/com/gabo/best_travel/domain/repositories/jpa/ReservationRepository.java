package com.gabo.best_travel.domain.repositories.jpa;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.gabo.best_travel.domain.entities.jpa.ReservationEntity;

public interface ReservationRepository extends CrudRepository<ReservationEntity, UUID>  {

}