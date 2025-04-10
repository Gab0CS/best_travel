package com.gabo.best_travel.infraestructure.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Currency;
import java.util.Objects;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gabo.best_travel.api.models.request.ReservationRequest;
import com.gabo.best_travel.api.models.response.HotelResponse;
import com.gabo.best_travel.api.models.response.ReservationResponse;
import com.gabo.best_travel.domain.entities.jpa.HotelEntity;
import com.gabo.best_travel.domain.entities.jpa.ReservationEntity;
import com.gabo.best_travel.domain.repositories.jpa.CustomerRepository;
import com.gabo.best_travel.domain.repositories.jpa.HotelRepository;
import com.gabo.best_travel.domain.repositories.jpa.ReservationRepository;
import com.gabo.best_travel.infraestructure.abstract_service.IReservationService;
import com.gabo.best_travel.infraestructure.helper.ApiCurrencyConnectorHelper;
import com.gabo.best_travel.infraestructure.helper.BlackListHelper;
import com.gabo.best_travel.infraestructure.helper.CustomerHelper;
import com.gabo.best_travel.infraestructure.helper.EmailHelper;
import com.gabo.best_travel.util.enums.Tables;
import com.gabo.best_travel.util.exceptions.IdNotFoundException;

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
    private final CustomerHelper customerHelper;
    private final BlackListHelper blackListHelper;
    private final ApiCurrencyConnectorHelper currencyConnectorHelper;
    private final EmailHelper emailHelper;

    @Override
    public BigDecimal findPrice(Long hotelId, Currency currency) {
       var hotel = this.hotelRepository.findById(hotelId).orElseThrow(() -> new IdNotFoundException(Tables.hotel.name()));
       var priceInDollars = hotel.getPrice().add(hotel.getPrice().multiply(charger_price_percentage));
       if (currency.equals(Currency.getInstance("USD"))) return priceInDollars;

       var currencyDto = this.currencyConnectorHelper.getCurrency(currency);
       log.info("Api currency in {}, response: {}" ,currencyDto.getExcahngeDate().toString(), currencyDto.getRates());
       return priceInDollars.multiply(currencyDto.getRates().get(currency));
    }

    @Override
    public ReservationResponse create(ReservationRequest request) {
        blackListHelper.isInBlackListCustomer(request.getIdClient());
        HotelEntity hotel = this.hotelRepository.findById(request.getIdHotel()).orElseThrow(() -> new IdNotFoundException(Tables.hotel.name()));
        var customer = this.customerRepository.findById(request.getIdClient()).orElseThrow(() -> new IdNotFoundException(Tables.customer.name()));
        
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
        this.customerHelper.increase(customer.getDni(), ReservationService.class);
        if (Objects.nonNull(request.getEmail())) {
            this.emailHelper.sendMail(request.getEmail(), customer.getFullName(), Tables.reservation.name());
        }
        return this.entityToResponse(reservationPersisted);
    }

    @Override
    public ReservationResponse read(UUID id) {
        var reservationFromDB = this.reservationRepository.findById(id).orElseThrow(() -> new IdNotFoundException(Tables.reservation.name()));

        return this.entityToResponse(reservationFromDB);
    }

    @Override
    public ReservationResponse update(ReservationRequest request, UUID id) {
        var hotel = this.hotelRepository.findById(request.getIdHotel()).orElseThrow(() -> new IdNotFoundException(Tables.hotel.name()));
        
        var reservationToUpdate = this.reservationRepository.findById(id).orElseThrow(() -> new IdNotFoundException(Tables.reservation.name()));
        reservationToUpdate.setHotel(hotel);
        reservationToUpdate.setTotalDays(request.getTotalDays());
        reservationToUpdate.setDateTimeReservation(LocalDateTime.now());
        reservationToUpdate.setDateStart(LocalDate.now());
        reservationToUpdate.setDateEnd(LocalDate.now().plusDays(request.getTotalDays()));
        reservationToUpdate.setPrice(hotel.getPrice());
        var reservationUpdated = this.reservationRepository.save(reservationToUpdate);
        log.info("Reservation updated with id {}" ,reservationToUpdate.getId());
        return this.entityToResponse(reservationUpdated);
    }

    @Override
    public void delete(UUID id) {
        var reservationToDelete = reservationRepository.findById(id).orElseThrow(() -> new IdNotFoundException(Tables.reservation.name()));
        this.reservationRepository.delete(reservationToDelete);
    }

    private ReservationResponse entityToResponse(ReservationEntity entity){
        var response = new ReservationResponse();
        BeanUtils.copyProperties(entity, response);
        var hotelResponse = new HotelResponse();
        BeanUtils.copyProperties(entity.getHotel(), hotelResponse);
        response.setHotel(hotelResponse);
        
        return response;
    }

    public static final BigDecimal charger_price_percentage = BigDecimal.valueOf(0.20);
    
}
