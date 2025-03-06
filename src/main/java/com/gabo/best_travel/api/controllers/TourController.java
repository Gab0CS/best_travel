package com.gabo.best_travel.api.controllers;

import java.util.Collections;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gabo.best_travel.api.models.request.TourRequest;
import com.gabo.best_travel.api.models.response.TourResponse;
import com.gabo.best_travel.infraestructure.abstract_service.ITourService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "tour")
@AllArgsConstructor
public class TourController {
    
    private final ITourService tourService;

    @ApiResponse(responseCode = "400", 
    description = "When request have an invalid field",
    content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = Errors.class))
    }
    )
    
    @PostMapping
    @Operation(summary = "Saves in system a Tour based in list hotels and flights list")
    public ResponseEntity<TourResponse> post(@Valid @RequestBody TourRequest request){
        System.out.println(tourService.getClass().getSimpleName());
        return ResponseEntity.ok(this.tourService.create(request));
    }

    @GetMapping(path = "{id}")
    @Operation(summary = "Returns a tour with a provided ID")
    public ResponseEntity<TourResponse> get(@PathVariable Long id){
        return ResponseEntity.ok(this.tourService.read(id));
    }
    @DeleteMapping(path = "{id}")
    @Operation(summary = "Deletes a tour with a provided ID")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        this.tourService.delete(id);
        return ResponseEntity.noContent().build() ;
    }

    @PatchMapping(path = "{tourId}/remove_ticket/{ticketId}")
    @Operation(summary = "Returns a ticket from a tour with a provided ID")
    public ResponseEntity<TourResponse> deleteTicket(@Valid @PathVariable Long tourId,@PathVariable UUID ticketId){
        this.tourService.removeTicket(tourId, ticketId);
        return ResponseEntity.noContent().build() ;
    }

    @PatchMapping(path = "{tourId}/add_ticket/{flyId}")
    @Operation(summary = "Returns a ticket from a tour with a provided ID")
    public ResponseEntity<Map<String, UUID >> postTicket(@PathVariable Long tourId,@PathVariable Long flyId){
        var response = Collections.singletonMap("ticketId", this.tourService.addTicket(tourId, flyId));
        return ResponseEntity.ok(response);
    }

    @PatchMapping(path = "{tourId}/remove_reservation/{reservationId}")
    @Operation(summary = "Deletes an hotel reservation from a tour with a provided ID")
    public ResponseEntity<TourResponse> deleteReservation(@PathVariable Long tourId,@PathVariable UUID ticketId){
        this.tourService.removeTicket(tourId, ticketId);
        return ResponseEntity.noContent().build() ;
    }

    @PatchMapping(path = "{tourId}/add_reservation/{hotelId}")
    @Operation(summary = "Returns a ticket from a tour with a provided ID")
    public ResponseEntity<Map<String, UUID >> postTicket(
        @PathVariable Long tourId, 
        @PathVariable Long hotelId,
        @RequestParam Integer totalDays){
        var response = Collections.singletonMap("ticketId", this.tourService.addReservation(tourId, hotelId, totalDays));
        return ResponseEntity.ok(response);
    }

}
