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

import com.gabo.best_travel.api.models.response.FlyResponse;
import com.gabo.best_travel.api.models.response.HotelResponse;
import com.gabo.best_travel.infraestructure.services.HotelService;
import com.gabo.best_travel.util.SortType;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "hotel")
@AllArgsConstructor
public class HotelController {
    private final HotelService hotelService;

    @GetMapping
    public ResponseEntity<Page<HotelResponse>> getAll(
        @RequestParam Integer page,
        @RequestParam Integer size,
        @RequestHeader(required = false) SortType sortType){
            if (Objects.isNull(sortType)) sortType = SortType.NONE;
            @SuppressWarnings("deprecation")
            var response = this.hotelService.readAll(page, size, sortType);
            return response.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(response);
    }

    @GetMapping(path = "less_price")
    public ResponseEntity<Set<HotelResponse>> getLessPrice(
        @RequestParam BigDecimal price){
            @SuppressWarnings("deprecation")
            var response = this.hotelService.readLessPrice(price);
            
            return response.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(response);
    }

    @GetMapping(path = "between_price")
    public ResponseEntity<Set<HotelResponse>> getBetweenPrice(
        @RequestParam BigDecimal min, @RequestParam BigDecimal max){
            @SuppressWarnings("deprecation")
            var response = this.hotelService.readBetweenPrice(min, max);
            
            return response.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(response);
    }

    @GetMapping(path = "rating")
    public ResponseEntity<Set<HotelResponse>> getByRating(
        @RequestParam Integer rating){
            @SuppressWarnings("deprecation")
            var response = this.hotelService.readByRating(rating);
            
            return response.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(response);
    }

}
