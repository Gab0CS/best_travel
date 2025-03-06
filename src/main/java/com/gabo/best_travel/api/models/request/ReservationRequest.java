package com.gabo.best_travel.api.models.request;


import java.io.Serializable;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ReservationRequest {

    // Validations just for Strings
    @Size(min = 18, max = 20, message = "The size must be between 18 and 20 characters")
    @NotBlank(message = "Id client is mandatory")
    private String idClient;

    @Positive
    @NotNull(message = "Id hotel is mandatory")
    private Long idHotel;
    @Min(value =  1, message = "Min one days to make a reservation")
    @Max(value = 30, message = "Max 30 days to make a reservation")
    @NotNull(message = "Total days is mandatory")
    private Integer totalDays;
    //@Pattern(regexp = "^(.*)@(.*)$")
    @Email(message = "Invalid email")
    private String email;

}
