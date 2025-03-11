package com.gabo.best_travel.domain.entities.jpa;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "ticket")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TicketEntity implements Serializable {
    @Id
    private UUID id;
    private LocalDateTime departureDate;
    private LocalDateTime arrivalDate;
    private LocalDateTime purchaseDate;
    private BigDecimal price;
    
    @ManyToOne(fetch = FetchType.LAZY)  
    @JoinColumn(name = "fly_id") //The column of fly that we need to map
    private FlyEntity fly; //Fk, One to many, one ticket can have several tickets
    
    @ManyToOne(fetch = FetchType.LAZY)  
    @JoinColumn(name = "tour_id", nullable = true) 
    private TourEntity tour;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = true) 
    private CustomerEntity customer;
}
