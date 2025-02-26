package com.gabo.best_travel.infraestructure.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoPeriod;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.gabo.best_travel.api.models.request.ReservationRequest;
import com.gabo.best_travel.api.models.response.FlyResponse;
import com.gabo.best_travel.api.models.response.HotelResponse;
import com.gabo.best_travel.api.models.response.ReservationResponse;
import com.gabo.best_travel.api.models.response.TicketResponse;
import com.gabo.best_travel.domain.entities.HotelEntity;
import com.gabo.best_travel.domain.entities.ReservationEntity;
import com.gabo.best_travel.domain.entities.TicketEntity;
import com.gabo.best_travel.domain.repositories.CustomerRepository;
import com.gabo.best_travel.domain.repositories.HotelRepository;
import com.gabo.best_travel.domain.repositories.ReservationRepository;
import com.gabo.best_travel.infraestructure.abstract_service.IReservationService;
import com.gabo.best_travel.util.BestTravelUtil;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Transactional
@Service
@Slf4j
@AllArgsConstructor
public class ReservationService implements IReservationService {

    private final HotelRepository hotelRepository;
    private final CustomerRepository customerRepository;
    private final ReservationRepository reservationRepository;

    @Override
    public ReservationResponse create(ReservationRequest request) {
        var hotel = this.hotelRepository.findById(request.getIdHotel()).orElseThrow();
        var customer = this.customerRepository.findById(request.getIdClient()).orElseThrow();
        
        var reservationToPersist = ReservationEntity.builder()
        .id(UUID.randomUUID())
        .hotel(hotel)
        .customer(customer)
        .totalDays(request.getTotalDays())
        .dateTimeReservation(LocalDateTime.now())
        .dateStart(LocalDate.now())
        .dateEnd(LocalDate.now().plusDays(request.getTotalDays()))
        .price(hotel.getPrice().add(hotel.getPrice().multiply(charger_price_percentage)))
        .build();        

        var reservationPersisted = reservationRepository.save(reservationToPersist);
        log.info("Reservation succesfully saved with id: {}", reservationPersisted.getId());

        return this.entityToResponse(reservationPersisted);
    }

    @Override
    public ReservationResponse read(UUID id) {
        return null;
    }

    @Override
    public ReservationResponse update(ReservationRequest request, UUID id) {
        return null;
    }

    @Override
    public void delete(UUID id) {

    }

    private ReservationResponse entityToResponse(ReservationEntity entity){
        var response = new ReservationResponse();
        BeanUtils.copyProperties(entity, response);
        var hotelResponse = new HotelResponse();
        BeanUtils.copyProperties(entity.getHotel(), hotelResponse);
        response.setHotel(hotelResponse);
        
        return response;
    }

    private static final BigDecimal charger_price_percentage = BigDecimal.valueOf(0.20);
    
}
