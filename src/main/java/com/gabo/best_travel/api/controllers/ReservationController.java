package com.gabo.best_travel.api.controllers;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gabo.best_travel.api.models.request.ReservationRequest;
import com.gabo.best_travel.api.models.request.TicketRequest;
import com.gabo.best_travel.api.models.response.ReservationResponse;
import com.gabo.best_travel.api.models.response.ReservationResponse;
import com.gabo.best_travel.infraestructure.abstract_service.IReservationService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "reservation")
@AllArgsConstructor
public class ReservationController {
    
    private final IReservationService reservationService;

    @PostMapping
    public ResponseEntity<ReservationResponse> post(@Valid @RequestBody ReservationRequest request) {
        return ResponseEntity.ok(reservationService.create(request));
    }

    @GetMapping(path = "{id}")    
    public ResponseEntity<ReservationResponse> get(@PathVariable UUID id){
         return ResponseEntity.ok(reservationService.read(id));
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<ReservationResponse> put(@Valid @PathVariable UUID id, 
    @RequestBody ReservationRequest request){
        return ResponseEntity.ok(this.reservationService.update(request, id));
    }

    @DeleteMapping(path = "{id}")    
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        this.reservationService.delete(id); 
        return ResponseEntity.noContent().build();
    }

    @GetMapping    
    public ResponseEntity<Map<String, BigDecimal>> getReservationPrice(@RequestParam Long reservationId){
        return ResponseEntity.ok(Collections.singletonMap("ticketPrice", this.reservationService.findPrice(reservationId)));
    }
}
