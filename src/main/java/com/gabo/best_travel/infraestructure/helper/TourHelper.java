package com.gabo.best_travel.infraestructure.helper;

import org.springframework.stereotype.Component;

import com.gabo.best_travel.domain.repositories.ReservationRepository;
import com.gabo.best_travel.domain.repositories.TicketRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Transactional
@Component
@AllArgsConstructor
public class TourHelper {
    private final TicketRepository ticketRepository;
    private final ReservationRepository reservationRepository;
}
