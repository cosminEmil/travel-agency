package org.example.newprojectmpp.repository;

import org.example.newprojectmpp.model.Ticket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class TicketRepository extends BaseRepository<Ticket> {
    public TicketRepository(Connection connection) {
        super(connection, "tickets");
    }

    @Override
    protected String getInsertFields() {
        return "(customer_name, tourists, customer_address, no_seats)";
    }

    @Override
    protected String getPlaceholders() {
        return "(?, ?, ?, ?)";
    }

    @Override
    protected String getSearchCondition() {
        return "customer_name = ? AND customer_address = ?";
    }

    @Override
    protected String getDeleteCondition() {
        return "customer_name = ?";
    }

    @Override
    protected void setAddParameters(PreparedStatement stmt, Ticket entity) throws SQLException {
        stmt.setString(1, entity.getCustomerName());
        stmt.setString(2, String.join(",", entity.getTourists())); // Store list as comma-separated
        stmt.setString(3, entity.getCustomerAddress());
        stmt.setInt(4, entity.getNoSeats());
    }

    @Override
    protected void setSearchParameters(PreparedStatement stmt, Ticket entity) throws SQLException {
        stmt.setString(1, entity.getCustomerName());
        stmt.setString(2, entity.getCustomerAddress());
    }

    @Override
    protected void setRemoveParameters(PreparedStatement stmt, Ticket entity) throws SQLException {
        stmt.setString(1, entity.getCustomerName());
    }

    @Override
    protected void setId(Ticket entity, int id) {
        entity.setId(id);
    }

    @Override
    public List<Ticket> findAll() {
        return List.of();
    }
}