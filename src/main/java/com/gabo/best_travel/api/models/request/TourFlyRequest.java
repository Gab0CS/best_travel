package com.gabo.best_travel.api.models.request;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Data;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TourFlyRequest implements Serializable {
    private long id;
}
