package com.gabo.best_travel.infraestructure.abstract_service;

import java.util.Set;

import com.gabo.best_travel.api.models.response.FlyResponse;

public interface IFlyService extends CatalogService<FlyResponse> {

    Set<FlyResponse> readByOriginDestiny(String origin, String destiny);
    
}
