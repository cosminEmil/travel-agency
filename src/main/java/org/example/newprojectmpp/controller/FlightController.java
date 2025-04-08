package org.example.newprojectmpp.controller;

import org.example.newprojectmpp.model.Flight;
import org.example.newprojectmpp.service.FlightService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class FlightController {
    private static final Logger logger = LogManager.getLogger(FlightController.class);
    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
        logger.info("FlightController initialized");
    }

    public void handleAddFlight(String destination, String departure, String airport, int availableSeats) {
        try {
            logger.debug("Handling add flight request");
            flightService.addFlight(destination, departure, airport, availableSeats);
        } catch (Exception e) {
            logger.error("Error adding flight: {}", e.getMessage(), e);
            throw e;
        }
    }

    public List<Flight> handleSearchFlights(String destination) {
        logger.debug("Handling flight search for: {}", destination);
        return flightService.findFlightsByDestination(destination);
    }

    public void handleCancelFlight(String departure, String destination) {
        logger.debug("Handling flight cancellation: {} to {}", departure, destination);
        flightService.cancelFlight(departure, destination);
    }
}