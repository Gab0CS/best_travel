package com.gabo.best_travel.infraestructure.abstract_service;

import java.math.BigDecimal;
import java.util.UUID;

import com.gabo.best_travel.api.models.request.TicketRequest;
import com.gabo.best_travel.api.models.response.TicketResponse;

public interface ITicketService extends CrudService<TicketRequest, TicketResponse, UUID> {

    BigDecimal findPrice(Long flyId);
    
}
