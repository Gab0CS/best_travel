package com.gabo.best_travel.infraestructure.services;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.gabo.best_travel.api.models.response.FlyResponse;
import com.gabo.best_travel.domain.entities.FlyEntity;
import com.gabo.best_travel.domain.repositories.FlyRepository;
import com.gabo.best_travel.infraestructure.abstract_service.IFlyService;
import com.gabo.best_travel.util.SortType;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Transactional
@Service
@AllArgsConstructor
public class FlyService implements IFlyService {

    private final FlyRepository flyRepository;

    @Override
    public Page<FlyResponse> readAll(Integer page, Integer size, SortType sortType) {
        PageRequest pageRequest = null;
        switch (sortType) {
            case NONE: pageRequest = PageRequest.of(page, size); 
                break;
            case LOWER: pageRequest = PageRequest.of(page,size, Sort.by(FIELD_BY_SORT).ascending());
                break;
            case UPPER: pageRequest = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).descending());
                break;
        }
        return this.flyRepository.findAll(pageRequest).map(this::entityToResponse);
    }

    @Override
    public Set<FlyResponse> readLessPrice(BigDecimal price) {
        return this.flyRepository.selectLessPrice(price)
        .stream()
        .map(this::entityToResponse) // Like in the readAll we had to implement a Map but it's a Set so we need to stream() 
        .collect(Collectors.toSet()); // followed by a collector

    }

    @Override
    public Set<FlyResponse> readBetweenPrice(BigDecimal min, BigDecimal max) {
        return this.flyRepository.selectBetweenPrice(min, max)
        .stream()
        .map(this::entityToResponse) // Like in the readAll we had to implement a Map but it's a Set so we need to stream() 
        .collect(Collectors.toSet()); // followed by a collector
    }

    @Override
    public Set<FlyResponse> readByOriginDestiny(String origin, String destiny) { 
        return this.flyRepository.selectOriginDestiny(origin, destiny)
        .stream()
        .map(this::entityToResponse) // Like in the readAll we had to implement a Map but it's a Set so we need to stream() 
        .collect(Collectors.toSet()); // followed by a collector
    }

    private FlyResponse entityToResponse(FlyEntity entity){
        FlyResponse response = new FlyResponse();
        BeanUtils.copyProperties(entity, response);
        return response;
    }
     
}
