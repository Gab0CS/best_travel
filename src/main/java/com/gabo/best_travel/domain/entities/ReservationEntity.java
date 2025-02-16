package com.gabo.best_travel.domain.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import org.springframework.cglib.core.Local;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
    
    @ManyToOne  
    @JoinColumn(name = "hotel_id") //The column of fly that we need to map
    private HotelEntity hotel; //Fk, One to many, one hotel can have several reservations
    @ManyToOne  
    @JoinColumn(name = "tour_id", nullable = true) 
    private TourEntity tour;

    @ManyToOne  
    @JoinColumn(name = "customer_id", nullable = true) 
    private CustomerEntity customer;
} 
