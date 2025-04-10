package com.gabo.best_travel.api.controllers;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;

import com.gabo.best_travel.api.models.response.FlyResponse;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gabo.best_travel.infraestructure.abstract_service.IFlyService;
import com.gabo.best_travel.util.SortType;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.experimental.var;

@RestController
@RequestMapping(path = "fly")
@AllArgsConstructor
@Tag(name = "Existing Flys")
public class FlyController {
    //For a controller we inject the interface
    private final IFlyService flyService;

    @GetMapping
    @Operation(summary = "To return all available flights with a page and size ")
    public ResponseEntity<Page<FlyResponse>> getAll(
        @RequestParam Integer page,
        @RequestParam Integer size,
        @RequestHeader(required = false) SortType sortType){
            if (Objects.isNull(sortType)) sortType = SortType.NONE;
            @SuppressWarnings("deprecation")
            var response = this.flyService.readAll(page, size, sortType);
            return response.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(response);
    }

    @GetMapping(path = "less_price")
    @Operation(summary = "Returns flights with a price less than provided")
    public ResponseEntity<Set<FlyResponse>> getLessPrice(
        @RequestParam BigDecimal price){
            @SuppressWarnings("deprecation")
            var response = this.flyService.readLessPrice(price);
            
            return response.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(response);
    }

    @GetMapping(path = "between_price")
    @Operation(summary = "Returns flights between two prices than provided")
    public ResponseEntity<Set<FlyResponse>> getBetweenPrice(
        @RequestParam BigDecimal min, @RequestParam BigDecimal max){
            @SuppressWarnings("deprecation")
            var response = this.flyService.readBetweenPrice(min, max);
            
            return response.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(response);
    }

    @GetMapping(path = "origin_destiny")
    @Operation(summary = "Returns flights within an specific origin and destiny")
    public ResponseEntity<Set<FlyResponse>> getByOriginDestiny(
        @RequestParam String origin, @RequestParam String destiny){
            @SuppressWarnings("deprecation")
            var response = this.flyService.readByOriginDestiny(origin, destiny);
            return response.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(response);
    }

    

}
