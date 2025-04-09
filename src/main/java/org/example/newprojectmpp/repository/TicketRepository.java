package org.example.newprojectmpp.repository;

import javafx.collections.ObservableList;
import org.example.newprojectmpp.model.Ticket;

import java.sql.*;
import java.util.Collection;
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
    public ObservableList<Ticket> findAll() {
        String sql = "SELECT * FROM tickets;";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Ticket ticket = new Ticket();
                ticket.setId(rs.getInt("id"));
                ticket.setCustomerName(rs.getString("customer_name"));
                String []tourists = rs.getString("tourists").split(",");
                tourists.stream(Collection< Array<String> >);
                //ticket.setTourists(rs.getString(rs.));
            }
        } catch (SQLException e) {
            logger.error("Failed to fetch tickets");
            throw new RuntimeException(e);
        }
    }
}