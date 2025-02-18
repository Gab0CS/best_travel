package com.gabo.best_travel.api.models.response;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class FlyResponse implements Serializable {
    private Long id;
    private Double originLat;
    private Double destinyLat;
    private Double originLng;
    private Double destinyLng;
    private String destinyName;
    private BigDecimal price;
    private Aeroline aeroline;
}
