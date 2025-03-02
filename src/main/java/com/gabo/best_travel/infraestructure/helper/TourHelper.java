package com.gabo.best_travel.infraestructure.helper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.gabo.best_travel.domain.entities.CustomerEntity;
import com.gabo.best_travel.domain.entities.FlyEntity;
import com.gabo.best_travel.domain.entities.HotelEntity;
import com.gabo.best_travel.domain.entities.ReservationEntity;
import com.gabo.best_travel.domain.entities.TicketEntity;
import com.gabo.best_travel.domain.repositories.ReservationRepository;
import com.gabo.best_travel.domain.repositories.TicketRepository;
import com.gabo.best_travel.infraestructure.services.ReservationService;
import com.gabo.best_travel.infraestructure.services.TicketService;
import com.gabo.best_travel.util.BestTravelUtil;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Transactional
@Component
@AllArgsConstructor
public class TourHelper {
    
    private final TicketRepository ticketRepository;
    private final ReservationRepository reservationRepository;


    private Set<TicketEntity> createTickets(Set<FlyEntity> flights, CustomerEntity customer){
        var response = new HashSet<TicketEntity>(flights.size());

        flights.forEach(fly -> {
            var ticketToPersist = TicketEntity.builder()
            .id(UUID.randomUUID())
            .fly(fly)
            .customer(customer)
            .price(fly.getPrice().add(fly.getPrice().multiply(TicketService.charger_price_percentage)))
            .purchaseDate(LocalDateTime.now())
            .departureDate(BestTravelUtil.getRandomSoon())
            .arrivalDate(BestTravelUtil.getRandomLater())
            .build();

            response.add(this.ticketRepository.save(ticketToPersist));
        });
        return response;
    }

    private Set<ReservationEntity> createReservations(HashMap<HotelEntity, Integer> hotels, CustomerEntity customer){
        var response = new HashSet<ReservationEntity>(hotels.size());

        hotels.forEach((hotel, totalDays) -> {
            var reservationToPersist = ReservationEntity.builder()
            .id(UUID.randomUUID())
            .hotel(hotel)
            .customer(customer)
            .totalDays(totalDays)
            .dateTimeReservation(LocalDateTime.now())
            .dateStart(LocalDate.now())
            .dateEnd(LocalDate.now().plusDays(totalDays))
            .price(hotel.getPrice().add(hotel.getPrice().multiply(ReservationService.charger_price_percentage)))
            .build();  

            response.add(this.reservationRepository.save(reservationToPersist));
        });
        
        return response;
    }

    
}
