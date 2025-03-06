package com.gabo.best_travel.api.controllers;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gabo.best_travel.api.models.request.TicketRequest;
import com.gabo.best_travel.api.models.response.TicketResponse;
import com.gabo.best_travel.infraestructure.abstract_service.ITicketService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping(path = "ticket")
@AllArgsConstructor
@Tag(name = "Fly tickets")
public class TicketController {
    private final ITicketService ticketService;

    @ApiResponse(responseCode = "400", 
    description = "When request have an invalid field",
    content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = Errors.class))
    }
    )

    @PostMapping
    @Operation(summary = "Creates a new ticket for an existing fly, needed customer Id an a Fly id")
    public ResponseEntity<TicketResponse> post(@Valid @RequestBody TicketRequest request){
        return ResponseEntity.ok(ticketService.create(request));
    }

    @GetMapping(path = "{id}")    
    @Operation(summary = "Returns a ticket providing an UUID ticket ID")
    public ResponseEntity<TicketResponse> get(@PathVariable UUID id){
         return ResponseEntity.ok(ticketService.read(id));
    }

    @PutMapping(path = "{id}")
    @Operation(summary = "Updates price, departure date or arrival date from a fly")
    public ResponseEntity<TicketResponse> put(@Valid @PathVariable UUID id, 
    @RequestBody TicketRequest request){
        return ResponseEntity.ok(this.ticketService.update(request, id));
    }

    @DeleteMapping(path = "{id}")    
    @Operation(summary = "Deletes a fly, needed a UUID")
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        this.ticketService.delete(id); 
        return ResponseEntity.noContent().build();
    }

    @GetMapping    
    @Operation(summary = "Returns fly's by brice setting a max amount of price")
    public ResponseEntity<Map<String, BigDecimal>> getFlyPrice(@RequestParam Long flyId){
        return ResponseEntity.ok(Collections.singletonMap("flyPrice", this.ticketService.findPrice(flyId)));
    }
}
