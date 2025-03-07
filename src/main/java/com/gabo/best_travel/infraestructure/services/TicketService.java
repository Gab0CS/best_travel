package com.gabo.best_travel.infraestructure.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gabo.best_travel.api.models.request.TicketRequest;
import com.gabo.best_travel.api.models.response.FlyResponse;
import com.gabo.best_travel.api.models.response.TicketResponse;
import com.gabo.best_travel.domain.entities.TicketEntity;
import com.gabo.best_travel.domain.repositories.CustomerRepository;
import com.gabo.best_travel.domain.repositories.FlyRepository;
import com.gabo.best_travel.domain.repositories.TicketRepository;
import com.gabo.best_travel.infraestructure.abstract_service.ITicketService;
import com.gabo.best_travel.infraestructure.helper.BlackListHelper;
import com.gabo.best_travel.infraestructure.helper.CustomerHelper;
import com.gabo.best_travel.util.BestTravelUtil;
import com.gabo.best_travel.util.enums.Tables;
import com.gabo.best_travel.util.exceptions.IdNotFoundException;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Transactional
@Service
@Slf4j
@AllArgsConstructor
public class TicketService implements ITicketService {

    private final FlyRepository flyRepository;
    private final CustomerRepository customerRepository;
    private final TicketRepository ticketRepository;
    private final CustomerHelper customerHelper;
    private final BlackListHelper blackListHelper;



    @Override
    public BigDecimal findPrice(Long flyId) {
       var fly = this.flyRepository.findById(flyId).orElseThrow(() -> new IdNotFoundException(Tables.fly.name()));
       return fly.getPrice().add(fly.getPrice().multiply(charger_price_percentage));
    }

    @Override
    public TicketResponse create(TicketRequest request) {
        blackListHelper.isInBlackListCustomer(request.getIdClient());
        var fly = flyRepository.findById(request.getIdFly()).orElseThrow(() -> new IdNotFoundException(Tables.fly.name()));
        var customer = customerRepository.findById(request.getIdClient()).orElseThrow(() -> new IdNotFoundException(Tables.customer.name()));

        var ticketToPersist = TicketEntity.builder()
        .id(UUID.randomUUID())
        .fly(fly)
        .customer(customer)
        .price(fly.getPrice().add(fly.getPrice().multiply(charger_price_percentage)))
        .purchaseDate(LocalDateTime.now())
        .departureDate(BestTravelUtil.getRandomSoon())
        .arrivalDate(BestTravelUtil.getRandomLater())
        .build();

        var ticketPersisted = this.ticketRepository.save(ticketToPersist);

        log.info("Ticket saved with id: {}", ticketPersisted.getId());

        customerHelper.increase(customer.getDni(), TicketService.class);

        return this.entityToResponse(ticketPersisted);
    }

    @Override
    public TicketResponse read(UUID id) {
        var ticketFromDB = this.ticketRepository.findById(id).orElseThrow(() -> new IdNotFoundException(Tables.ticket.name()));

        return this.entityToResponse(ticketFromDB);
    }

    @Override
    public TicketResponse update(TicketRequest request, UUID id) {
        var ticketToUpdate = ticketRepository.findById(id).orElseThrow(() -> new IdNotFoundException(Tables.ticket.name()));
        var fly = flyRepository.findById(request.getIdFly()).orElseThrow(() -> new IdNotFoundException(Tables.fly.name()));

        ticketToUpdate.setFly(fly);
        ticketToUpdate.setPrice(fly.getPrice().add(fly.getPrice().multiply(charger_price_percentage)));
        ticketToUpdate.setDepartureDate(BestTravelUtil.getRandomSoon());
        ticketToUpdate.setArrivalDate(BestTravelUtil.getRandomLater());

        var ticketUpdated = this.ticketRepository.save(ticketToUpdate);

        log.info("Ticket updated with id {}" ,ticketUpdated.getId());

        return this.entityToResponse(ticketToUpdate);
    }

    @Override
    public void delete(UUID id) {
        var ticketToDelete = ticketRepository.findById(id).orElseThrow(() -> new IdNotFoundException(Tables.ticket.name()));
        this.ticketRepository.delete(ticketToDelete);
    }
    
    private TicketResponse entityToResponse(TicketEntity entity){
        var response = new TicketResponse();
        BeanUtils.copyProperties(entity, response);
        var flyResponse = new FlyResponse();
        BeanUtils.copyProperties(entity.getFly(), flyResponse);
        response.setFly(flyResponse);
        
        return response;
    }

    public static final BigDecimal charger_price_percentage = BigDecimal.valueOf(0.25);
    
}
