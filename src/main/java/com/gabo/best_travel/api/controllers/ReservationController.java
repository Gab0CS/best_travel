package com.gabo.best_travel.api.controllers;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Currency;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gabo.best_travel.api.models.request.ReservationRequest;
import com.gabo.best_travel.api.models.request.TicketRequest;
import com.gabo.best_travel.api.models.response.ReservationResponse;
import com.gabo.best_travel.api.models.response.ReservationResponse;
import com.gabo.best_travel.infraestructure.abstract_service.IReservationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "reservation")
@AllArgsConstructor
@Tag(name = "Reservation operations")
public class ReservationController {
    
    private final IReservationService reservationService;

    @ApiResponse(responseCode = "400", 
    description = "When request have an invalid field",
    content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = Errors.class))
    }
    )

    @PostMapping
    @Operation(summary = "Creates a new reservation, needed idCustomer, idHotel, total days and email of customer")
    public ResponseEntity<ReservationResponse> post(@Valid @RequestBody ReservationRequest request) {
        return ResponseEntity.ok(reservationService.create(request));
    }

    @GetMapping(path = "{id}")    
    @Operation(summary = "Returns a reservation with a provided ID (That's a UUID)")
    public ResponseEntity<ReservationResponse> get(@PathVariable UUID id){
         return ResponseEntity.ok(reservationService.read(id));
    }

    @PutMapping(path = "{id}")
    @Operation(summary = "Updating some field of the reservation")
    public ResponseEntity<ReservationResponse> put(@Valid @PathVariable UUID id, 
    @RequestBody ReservationRequest request){
        return ResponseEntity.ok(this.reservationService.update(request, id));
    }

    @DeleteMapping(path = "{id}")    
    @Operation(summary = "Delete a reservation providing an ID")
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        this.reservationService.delete(id); 
        return ResponseEntity.noContent().build();
    }

    @GetMapping    
    @Operation(summary = "Delete a reservation providing an ID")
    public ResponseEntity<Map<String, BigDecimal>> getReservationPrice(
        @RequestParam Long hotelId,
        @RequestHeader(required = false) Currency currency
        ){
        if (Objects.isNull(currency)) currency = Currency.getInstance("USD"); 
        return ResponseEntity.ok(Collections.singletonMap("ticketPrice", this.reservationService.findPrice(hotelId, currency)));
    }
}
