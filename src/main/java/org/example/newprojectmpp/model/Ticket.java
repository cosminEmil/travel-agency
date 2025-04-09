package org.example.newprojectmpp.model;

import java.util.List;

public class Ticket {
    private int id;
    private String customerName;
    private List<String> tourists;
    private String customerAddress;
    private int noSeats;

    public Ticket(String customerName, List<String> tourists, String customerAddress, int noSeats) {
        this.customerName = customerName;
        this.tourists = tourists;
        this.customerAddress = customerAddress;
        this.noSeats = noSeats;
    }

    public Ticket() {

    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public List<String> getTourists() {
        return tourists;
    }

    public void setTourists(List<String> tourists) {
        this.tourists = tourists;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public int getNoSeats() {
        return noSeats;
    }

    public void setNoSeats(int noSeats) {
        this.noSeats = noSeats;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
