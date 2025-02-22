package com.gabo.best_travel.api.controllers;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gabo.best_travel.api.models.request.TicketRequest;
import com.gabo.best_travel.api.models.response.TicketResponse;
import com.gabo.best_travel.infraestructure.abstract_service.ITicketService;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping(path = "ticket")
@AllArgsConstructor
public class TicketController {
    private final ITicketService ticketService;

    @PostMapping
    public ResponseEntity<TicketResponse> post(@RequestBody TicketRequest request){
        return ResponseEntity.ok(ticketService.create(request));
    }

    @GetMapping(path = "{id}")    
    public ResponseEntity<TicketResponse> get(@PathVariable UUID id){
         return ResponseEntity.ok(ticketService.read(id));
    }
}
