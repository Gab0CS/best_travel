package com.gabo.best_travel.infraestructure.services;

import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.gabo.best_travel.api.models.request.TourRequest;
import com.gabo.best_travel.api.models.response.TourResponse;
import com.gabo.best_travel.domain.entities.FlyEntity;
import com.gabo.best_travel.domain.entities.HotelEntity;
import com.gabo.best_travel.domain.entities.ReservationEntity;
import com.gabo.best_travel.domain.entities.TicketEntity;
import com.gabo.best_travel.domain.entities.TourEntity;
import com.gabo.best_travel.domain.repositories.CustomerRepository;
import com.gabo.best_travel.domain.repositories.FlyRepository;
import com.gabo.best_travel.domain.repositories.HotelRepository;
import com.gabo.best_travel.domain.repositories.TourRepository;
import com.gabo.best_travel.infraestructure.abstract_service.ITourService;
import com.gabo.best_travel.infraestructure.helper.TourHelper;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Transactional
@Service
@AllArgsConstructor
public class TourService implements ITourService {
    
    private final TourRepository tourRepository;
    private final FlyRepository flyRepository;
    private final HotelRepository hotelRepository;
    private final CustomerRepository customerRepository;
    private final TourHelper tourHelper;
    
    
    @Override
    public TourResponse create(TourRequest request) {
        var customer = customerRepository.findById(request.getCustomerId()).orElseThrow();
        var flights = new HashSet<FlyEntity>();
        request.getFlights().forEach(fly -> flights.add(this.flyRepository.findById(fly.getId()).orElseThrow()));
        var hotels = new HashMap<HotelEntity, Integer>();
        request.getHotels().forEach(hotel -> hotels.put(
            this.hotelRepository
            .findById(hotel.getId())
            .orElseThrow(), hotel.getTotalDays()));

        var tourToSave = TourEntity.builder()
            .tickets(this.tourHelper.createTickets(flights, customer))
            .reservations(this.tourHelper.createReservations(hotels, customer))
            .customer(customer)
            .build();

        var tourSaved = this.tourRepository.save(tourToSave);
        return TourResponse.builder()
        .reservationsIds(tourSaved.getReservations().stream().map(ReservationEntity::getId).collect(Collectors.toSet()))
        .ticketsIds(tourSaved.getTickets().stream().map(TicketEntity::getId).collect(Collectors.toSet()))
        .id(tourSaved.getId())
         .build();

    }

    @Override
    public TourResponse read(Long id) {
        var tourFromDb = this.tourRepository.findById(id).orElseThrow();

        return TourResponse.builder()
        .reservationsIds(tourFromDb.getReservations().stream().map(ReservationEntity::getId).collect(Collectors.toSet()))
        .ticketsIds(tourFromDb.getTickets().stream().map(TicketEntity::getId).collect(Collectors.toSet()))
        .id(tourFromDb.getId())
        .build();
        
    }

    @Override
    public void delete(Long id) {
        var tourToDelete = this.tourRepository.findById(id).orElseThrow();
        this.tourRepository.delete(tourToDelete);
    }

    @Override
    public void removeTicket(UUID ticketId, Long tourId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeTicket'");
    }

    @Override
    public UUID addTicket(Long flyId, Long tourId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addTicket'");
    }

    @Override
    public void removeReservation(UUID reservationId, Long tourId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeReservation'");
    }

    @Override
    public UUID addReservation(Long reservationId, Long tourId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addReservation'");
    }
    
}
