package com.gabo.best_travel.api.models.request;

import java.io.Serializable;
import java.util.Optional;

import com.gabo.best_travel.domain.entities.CustomerEntity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Data;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TourHotelRequest implements Serializable {
    private long id;
    private Integer totalDays;
}
