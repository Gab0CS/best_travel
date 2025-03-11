package com.gabo.best_travel.domain.repositories.jpa;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gabo.best_travel.domain.entities.jpa.HotelEntity;

public interface HotelRepository extends JpaRepository<HotelEntity, Long>{ 
    Set<HotelEntity> findByPriceLessThan(BigDecimal price);
    Set<HotelEntity> findByPriceBetween(BigDecimal min, BigDecimal max);
    Set<HotelEntity> findByRatingGreaterThan(Integer rating);
    Optional<HotelEntity> findByReservationId(UUID id);

}
