package com.gabo.best_travel.domain.entities.jpa;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "reservation")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ReservationEntity implements Serializable {

    @Id
    private UUID id;
    @Column(name = "date_reservation")
    private LocalDateTime dateTimeReservation;
    private LocalDate dateStart;
    private LocalDate dateEnd;
    private Integer totalDays;
    private BigDecimal price; 
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id") //The column of fly that we need to map
    private HotelEntity hotel; //Fk, One to many, one hotel can have several reservations
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tour_id", nullable = true) 
    private TourEntity tour;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = true) 
    private CustomerEntity customer;
} 
