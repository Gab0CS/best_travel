package com.gabo.best_travel.infraestructure.services;

import java.math.BigDecimal;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.PageRequest;

import java.util.stream.Collectors;

import com.gabo.best_travel.api.models.response.HotelResponse;
import com.gabo.best_travel.domain.entities.HotelEntity;
import com.gabo.best_travel.domain.repositories.HotelRepository;
import com.gabo.best_travel.infraestructure.abstract_service.IHotelService;
import com.gabo.best_travel.util.SortType;

import lombok.AllArgsConstructor;


@Transactional(readOnly = true)
@Service
@AllArgsConstructor
public class HotelService implements IHotelService {

    private final HotelRepository hotelRepository;

    @Override
    public Page<HotelResponse> readAll(Integer page, Integer size, SortType sortType) {
        PageRequest pageRequest = null;

        switch (sortType) {
            case NONE: pageRequest = PageRequest.of(page, size); 
                break;
            case LOWER: pageRequest = PageRequest.of(page,size, Sort.by(FIELD_BY_SORT).ascending());
                break;
            case UPPER: pageRequest = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).descending());
                break;
        }
        return this.hotelRepository.findAll(pageRequest).map(this::entityToResponse);
    }

    @Override
    public Set<HotelResponse> readLessPrice(BigDecimal price) {
        return this.hotelRepository.findByPriceLessThan(price)
        .stream()
        .map(this::entityToResponse)
        .collect(Collectors.toSet());
    }

    @Override
    public Set<HotelResponse> readBetweenPrice(BigDecimal min, BigDecimal max) {
        return this.hotelRepository.findByPriceBetween(min, max)
        .stream()
        .map(this::entityToResponse)
        .collect(Collectors.toSet());
    }

    @Override
    public Set<HotelResponse> readByRating(Integer rating) {
        return this.hotelRepository.findByRatingGreaterThan(rating)
        .stream()
        .map(this::entityToResponse)
        .collect(Collectors.toSet());
    }
    
    private HotelResponse entityToResponse(HotelEntity entity){
        HotelResponse response = new HotelResponse();
        BeanUtils.copyProperties(entity, response);
        return response;
    }

}
