package com.gabo.best_travel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.gabo.best_travel.domain.repositories.mongo.AppUserRepository;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
public class BestTravelApplication {
	public static void main(String[] args) {
		SpringApplication.run(BestTravelApplication.class, args);
	}
}
