package org.example.newprojectmpp.service;

import org.example.newprojectmpp.model.Flight;
import org.example.newprojectmpp.repository.IRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.stream.Collectors;

public class FlightService {
    private static final Logger logger = LogManager.getLogger(FlightService.class);
    private final IRepository<Flight> flightRepository;

    public FlightService(IRepository<Flight> flightRepository) {
        this.flightRepository = flightRepository;
        logger.info("FlightService initialized");
    }

    public void addFlight(String destination, String departure, String airport, int availableSeats) {
        logger.debug("Adding new flight to: {}", destination);
        Flight flight = new Flight(destination, departure, airport, availableSeats);
        flightRepository.add(flight);
        logger.info("Flight added successfully: {} to {}", departure, destination);
    }

    public List<Flight> findFlightsByDestination(String destination) {
        logger.debug("Searching flights to: {}", destination);
        Flight searchFlight = new Flight(destination, null, null, 0);
        // This assumes your org.example.newprojectmpp.repository's search method can be adapted for this
        // Alternatively, implement a specific org.example.newprojectmpp.repository method
        return getAllFlights().stream()
                .filter(f -> f.getDestination().equalsIgnoreCase(destination))
                .collect(Collectors.toList());
    }

    public List<Flight> getAllFlights() {
        // This would require adding a findAll() method to your IRepository
        // For now using search with a dummy object
        Flight dummy = new Flight(null, null, null, 0);
        return flightRepository.search(dummy) ? List.of() : List.of(); // Placeholder
    }

    public void cancelFlight(String departure, String destination) {
        logger.debug("Canceling flight: {} to {}", departure, destination);
        Flight flight = new Flight(destination, departure, null, 0);
        flightRepository.remove(flight);
        logger.info("Flight canceled: {} to {}", departure, destination);
    }
}