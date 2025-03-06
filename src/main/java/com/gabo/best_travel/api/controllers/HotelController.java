package com.gabo.best_travel.api.controllers;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gabo.best_travel.api.models.response.HotelResponse;
import com.gabo.best_travel.infraestructure.services.HotelService;
import com.gabo.best_travel.util.SortType;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "hotel")
@AllArgsConstructor
@Tag(name = "Hotel operations")
public class HotelController {
    private final HotelService hotelService;

    @GetMapping
    @Operation(summary = "Get all hotels available retrieving #hotels per page and a size")
    public ResponseEntity<Page<HotelResponse>> getAll(
        @RequestParam Integer page,
        @RequestParam Integer size,
        @RequestHeader(required = false) SortType sortType){
            if (Objects.isNull(sortType)) sortType = SortType.NONE;
            var response = this.hotelService.readAll(page, size, sortType);
            return response.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(response);
    }

    @GetMapping(path = "less_price")
    @Operation(summary = "Get hotels less than an specified price")
    public ResponseEntity<Set<HotelResponse>> getLessPrice(
        @RequestParam BigDecimal price){
            var response = this.hotelService.readLessPrice(price);
            
            return response.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(response);
    }

    @GetMapping(path = "between_price")
    @Operation(summary = "Get hotels between a range of specified prices")
    public ResponseEntity<Set<HotelResponse>> getBetweenPrice(
        @RequestParam BigDecimal min, @RequestParam BigDecimal max){
            var response = this.hotelService.readBetweenPrice(min, max);
            
            return response.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(response);
    }

    @GetMapping(path = "rating")
    @Operation(summary = "Get hotels above an specified rating")
    public ResponseEntity<Set<HotelResponse>> getByRating(
        @RequestParam Integer rating){
            if (rating > 4) rating = 4;
            if (rating < 1) rating = 1;
            
            var response = this.hotelService.readByRating(rating);
            return response.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(response);
    }

}
