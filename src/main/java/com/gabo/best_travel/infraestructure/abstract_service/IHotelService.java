package com.gabo.best_travel.infraestructure.abstract_service;

import java.util.Set;

import com.gabo.best_travel.api.models.response.HotelResponse;

public interface IHotelService extends CatalogService<HotelResponse>{
    Set<HotelResponse> readGreaterThan(Integer rating);
}
