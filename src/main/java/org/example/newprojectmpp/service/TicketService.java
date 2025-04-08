package org.example.newprojectmpp.service;

import org.example.newprojectmpp.model.Ticket;
import org.example.newprojectmpp.repository.IRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class TicketService {
    private static final Logger logger = LogManager.getLogger(TicketService.class);
    private final IRepository<Ticket> ticketRepository;

    public TicketService(IRepository<Ticket> ticketRepository) {
        this.ticketRepository = ticketRepository;
        logger.info("TicketService initialized");
    }

    public void issueTicket(String customerName, List<String> tourists, String address, int seats) {
        logger.debug("Issuing ticket for: {}", customerName);
        Ticket ticket = new Ticket(customerName, tourists, address, seats);
        ticketRepository.add(ticket);
        logger.info("Ticket issued successfully for: {}", customerName);
    }

    public boolean findTicketByCustomer(String customerName) {
        logger.debug("Searching ticket for: {}", customerName);
        Ticket searchTicket = new Ticket(customerName, null, null, 0);
        return ticketRepository.search(searchTicket);
    }

    public void cancelTicket(String customerName) {
        logger.debug("Canceling ticket for: {}", customerName);
        Ticket ticket = new Ticket(customerName, null, null, 0);
        ticketRepository.remove(ticket);
        logger.info("Ticket canceled for: {}", customerName);
    }
}