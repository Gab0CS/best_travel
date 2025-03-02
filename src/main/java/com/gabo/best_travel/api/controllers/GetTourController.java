package com.gabo.best_travel.api.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gabo.best_travel.api.models.request.TourRequest;
import com.gabo.best_travel.api.models.response.TourResponse;
import com.gabo.best_travel.infraestructure.abstract_service.ITourService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "tour")
@AllArgsConstructor
public class GetTourController {
    
    private final ITourService tourService;

    @PostMapping
    public ResponseEntity<TourResponse> post(@RequestBody TourRequest request){
        return ResponseEntity.ok(this.tourService.create(request));
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<TourResponse> get(@PathVariable Long id){
        return ResponseEntity.ok(this.tourService.read(id));
    }
    @DeleteMapping(path = "{id}")
    public ResponseEntity<TourResponse> delete(@PathVariable Long id){
        this.tourService.delete(id);
        return ResponseEntity.noContent().build() ;
    }

}
