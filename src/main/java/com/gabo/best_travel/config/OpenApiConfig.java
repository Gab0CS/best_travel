package com.gabo.best_travel.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@Configuration
@OpenAPIDefinition(
    info = @Info(title = "Best travel API", 
    version = "1.0", 
    description = "Documentation for endpoints")
)
public class OpenApiConfig {
    
}
