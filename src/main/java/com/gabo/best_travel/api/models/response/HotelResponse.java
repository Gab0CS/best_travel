package com.gabo.best_travel.api.models.response;

import java.io.Serializable;
import java.math.BigDecimal;

public class HotelResponse implements Serializable{
    private Long id;
    private String name;
    private String address;
    private Integer rating;
    private BigDecimal price;

}
