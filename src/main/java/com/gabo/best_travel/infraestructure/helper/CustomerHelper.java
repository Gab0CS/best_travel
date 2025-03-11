package com.gabo.best_travel.infraestructure.helper;

import org.hibernate.annotations.Comment;
import org.springframework.stereotype.Component;

import com.gabo.best_travel.domain.repositories.jpa.CustomerRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Transactional
@Component
@AllArgsConstructor
public class CustomerHelper {

    private final CustomerRepository customerRepository;

    public void increase(String customerId, Class<?> type){
        var customerToUpdate = this.customerRepository.findById(customerId).orElseThrow();
        switch (type.getSimpleName()) {
            case "TourService":
                customerToUpdate.setTotalTours(customerToUpdate.getTotalTours() + 1);
                break;
            case "TicketService":
                customerToUpdate.setTotalFlights(customerToUpdate.getTotalFlights() + 1);
                break;
            case "ReservationService":
                customerToUpdate.setTotalLodgings(customerToUpdate.getTotalLodgings() + 1);
                break;
        
            default:
                break;
        }
        this.customerRepository.save(customerToUpdate);
    }

    
}
