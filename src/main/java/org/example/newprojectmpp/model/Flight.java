package org.example.newprojectmpp.model;

public class Flight {
    private int id;
    private String destination;
    private String departure;
    private String airport;
    private int noAvailableSeats;

    public Flight(String destination, String departure, String airport, int noAvailableSeats) {
        this.destination = destination;
        this.departure = departure;
        this.airport = airport;
        this.noAvailableSeats = noAvailableSeats;
    }

    public Flight() {

    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getAirport() {
        return airport;
    }

    public void setAirport(String airport) {
        this.airport = airport;
    }

    public int getNoAvailableSeats() {
        return noAvailableSeats;
    }

    public void setNoAvailableSeats(int noAvailableSeats) {
        this.noAvailableSeats = noAvailableSeats;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

}
