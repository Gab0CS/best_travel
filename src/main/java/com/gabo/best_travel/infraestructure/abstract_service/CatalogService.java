package com.gabo.best_travel.infraestructure.abstract_service;

import java.math.BigDecimal;
import java.util.Set;

import org.springframework.data.domain.Page;

import com.gabo.best_travel.util.SortType;

public interface CatalogService<R> {

    Page<R> readAll(Integer page, Integer size, SortType sortType);
    
    Set<R> readLessPrice(BigDecimal price);

    Set<R> readBetweenPrice(BigDecimal min, BigDecimal max);

    String FIELD_BY_SORT = "price";
    
} 
