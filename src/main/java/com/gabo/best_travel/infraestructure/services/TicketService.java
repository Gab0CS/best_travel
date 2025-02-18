package com.gabo.best_travel.infraestructure.services;

import java.util.UUID;

import com.gabo.best_travel.api.models.request.TicketRequest;
import com.gabo.best_travel.api.models.response.TicketResponse;
import com.gabo.best_travel.infraestructure.abstract_service.ITicketService;

public class TicketService implements ITicketService {

    @Override
    public TicketResponse create(TicketRequest request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public TicketResponse read(UUID id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'read'");
    }

    @Override
    public TicketResponse update(TicketRequest request, UUID id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(UUID id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }
    
}
