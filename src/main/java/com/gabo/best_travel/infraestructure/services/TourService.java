package com.gabo.best_travel.infraestructure.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.gabo.best_travel.api.models.request.TourRequest;
import com.gabo.best_travel.api.models.response.TourResponse;
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public TourResponse read(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'read'");
    }

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
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
