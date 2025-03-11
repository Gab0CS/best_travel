package com.gabo.best_travel.api.models.request;

import java.io.Serializable;
import java.util.Optional;

import com.gabo.best_travel.domain.entities.jpa.CustomerEntity;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Data;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TourHotelRequest implements Serializable {
    @Size(min = 18, max = 20, message = "The size must be between 18 and 20 characters")
    @NotBlank(message = "Id client is mandatory")
    private long id;
    @Min(value =  1, message = "Min one days to make a reservation")
    @Max(value = 30, message = "Max 30 days to make a reservation")
    @NotNull(message = "Total days is mandatory")
    private Integer totalDays;
}
