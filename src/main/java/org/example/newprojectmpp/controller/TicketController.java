package org.example.newprojectmpp.controller;

import org.example.newprojectmpp.service.TicketService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class TicketController {
    private static final Logger logger = LogManager.getLogger(TicketController.class);
    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
        logger.info("TicketController initialized");
    }

    public void handleIssueTicket(String customerName, List<String> tourists, String address, int seats) {
        try {
            logger.debug("Handling ticket issuance for: {}", customerName);
            ticketService.issueTicket(customerName, tourists, address, seats);
        } catch (Exception e) {
            logger.error("Error issuing ticket: {}", e.getMessage(), e);
            throw e;
        }
    }

    public boolean handleFindTicket(String customerName) {
        logger.debug("Handling ticket search for: {}", customerName);
        return ticketService.findTicketByCustomer(customerName);
    }

    public void handleCancelTicket(String customerName) {
        logger.debug("Handling ticket cancellation for: {}", customerName);
        ticketService.cancelTicket(customerName);
    }
}