package com.gabo.best_travel.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gabo.best_travel.domain.entities.HotelEntity;

public interface HotelRepository extends JpaRepository<HotelEntity, Long>{ 
    
}
