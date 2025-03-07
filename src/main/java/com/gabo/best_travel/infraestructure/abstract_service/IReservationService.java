package com.gabo.best_travel.infraestructure.abstract_service;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.UUID;

import com.gabo.best_travel.api.models.request.ReservationRequest;
import com.gabo.best_travel.api.models.response.ReservationResponse;

public interface IReservationService extends CrudService<ReservationRequest, ReservationResponse, UUID> {

    public BigDecimal findPrice(Long flyId, Currency currency);
} 