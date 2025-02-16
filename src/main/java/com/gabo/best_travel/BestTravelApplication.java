package com.gabo.best_travel;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.gabo.best_travel.domain.repositories.CustomerRepository;
import com.gabo.best_travel.domain.repositories.FlyRepository;
import com.gabo.best_travel.domain.repositories.HotelRepository;
import com.gabo.best_travel.domain.repositories.ReservationRepository;
import com.gabo.best_travel.domain.repositories.TicketRepository;
import com.gabo.best_travel.domain.repositories.TourRepository;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class BestTravelApplication implements CommandLineRunner {

	private final HotelRepository hotelRepository;
	private final FlyRepository flyRepository;
	private final TicketRepository ticketRepository;
	private final ReservationRepository reservationRepository;
	private final CustomerRepository customerRepository;
	private final TourRepository tourRepository;
	
	public BestTravelApplication(
		HotelRepository hotelRepository,
		FlyRepository flyRepository,
		TicketRepository ticketRepository,
		ReservationRepository reservationRepository,
		CustomerRepository customerRepository,
		TourRepository tourRepository) {
		this.hotelRepository = hotelRepository;
		this.flyRepository = flyRepository;
		this.ticketRepository = ticketRepository;
		this.reservationRepository = reservationRepository;
		this.customerRepository = customerRepository;
		this.tourRepository = tourRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(BestTravelApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		// var fly = flyRepository.findById(15L).get();
		// var hotel = hotelRepository.findById(5L).get();
		// var ticket = ticketRepository.findById(UUID.fromString("12345678-1234-5678-2236-567812345678")).get();
		// var reservation = reservationRepository.findById(UUID.fromString("12345678-1234-5678-1234-567812345678")).get();
		// var customer = customerRepository.findById("VIKI771012HMCRG093").get();

		// log.info(String.valueOf(fly));
		// log.info(String.valueOf(hotel));
		// log.info(String.valueOf(ticket));
		// log.info(String.valueOf(reservation));
		// log.info(String.valueOf(customer));

		// this.flyRepository.selectLessPrice(BigDecimal.valueOf(20)).forEach(f -> System.out.println(f));
		// this.flyRepository.selectBetweenPrice(BigDecimal.valueOf(10), BigDecimal.valueOf(15)).forEach(f -> System.out.println(f));

		var fly = flyRepository.findByTicketId(UUID.fromString("12345678-1234-5678-2236-567812345678"));
		
		System.out.println(fly);
	}

}
