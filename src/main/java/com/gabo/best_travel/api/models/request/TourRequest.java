package com.gabo.best_travel.api.models.request;

import java.io.Serializable;
import java.util.Set;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TourRequest implements Serializable {
    @Size(min = 18, max = 20, message = "The size must be between 18 and 20 characters")
    @NotBlank(message = "Id client is mandatory")
    public String customerId;
    private Set<TourFlyRequest> flights;
    private Set<TourHotelRequest> hotels;
    @Email(message = "Invalid email")
    private String email;
}
