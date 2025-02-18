package com.gabo.best_travel;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.Formatter.BigDecimalLayoutForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.gabo.best_travel.domain.entities.ReservationEntity;
import com.gabo.best_travel.domain.entities.TicketEntity;
import com.gabo.best_travel.domain.entities.TourEntity;
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

		var customer = customerRepository.findById("VIKI771012HMCRG093").get();
		log.info("Client name: " + customer.getFullName());

		var fly = flyRepository.findById(11L).orElseThrow();
		log.info("Fly: " + fly.getOriginName() + " - " + fly.getDestinyName());
		var tour = TourEntity.builder().customer(customer).build();
		var hotel = hotelRepository.findById(3L).orElseThrow();
		log.info("Hotel: " + hotel.getName());


		var ticket = TicketEntity.builder()
		.id(UUID.randomUUID())
		.price(fly.getPrice().multiply(BigDecimal.TEN))
		.arrivalDate(LocalDateTime.now())
		.departureDate(LocalDateTime.now())
		.purchaseDate(LocalDateTime.now())
		.customer(customer)
		.tour(tour)
		.fly(fly)
		.build();

		var reservation = ReservationEntity.builder()
		.id(UUID.randomUUID())
		.dateTimeReservation(LocalDateTime.now())
		.dateEnd(LocalDate.now())
		.dateStart(LocalDate.now())
		.hotel(hotel)
		.customer(customer)
		.tour(tour)
		.totalDays(1)
		.price(hotel.getPrice().multiply(BigDecimal.TEN))
		.build();

		tour.addReservation(reservation);
		tour.updateReservations();

		tour.addTicket(ticket);
		tour.updateTickets();

		var tourSaved = this.tourRepository.save(tour);
		Thread.sleep(8000);
		this.tourRepository.deleteById(tourSaved.getId());
	}

}
