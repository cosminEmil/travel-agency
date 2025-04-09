package org.example.newprojectmpp.repository;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.newprojectmpp.model.Flight;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class FlightRepository extends BaseRepository<Flight> {
    public FlightRepository(Connection connection) {
        super(connection, "flights");
    }

    @Override
    protected String getInsertFields() {
        return "(destination, departure, airport, no_available_seats)";
    }

    @Override
    protected String getPlaceholders() {
        return "(?, ?, ?, ?)";
    }

    @Override
    protected String getSearchCondition() {
        return "destination = ? AND departure = ? AND airport = ?";
    }

    @Override
    protected String getDeleteCondition() {
        return "destination = ? AND departure = ?";
    }

    @Override
    protected void setAddParameters(PreparedStatement stmt, Flight entity) throws SQLException {
        stmt.setString(1, entity.getDestination());
        stmt.setString(2, entity.getDeparture());
        stmt.setString(3, entity.getAirport());
        stmt.setInt(4, entity.getNoAvailableSeats());
    }

    @Override
    protected void setSearchParameters(PreparedStatement stmt, Flight entity) throws SQLException {
        stmt.setString(1, entity.getDestination());
        stmt.setString(2, entity.getDeparture());
        stmt.setString(3, entity.getAirport());
    }

    @Override
    protected void setRemoveParameters(PreparedStatement stmt, Flight entity) throws SQLException {
        stmt.setString(1, entity.getDestination());
        stmt.setString(2, entity.getDeparture());
    }

    @Override
    protected void setId(Flight entity, int id) {
        entity.setId(id);
    }

    @Override
    public ObservableList<Flight> findAll() {
        ObservableList<Flight> flights = FXCollections.observableArrayList();
        String sql = "SELECT * FROM flights";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Flight flight = new Flight();
                flight.setId(rs.getInt("id"));
                flight.setDestination(rs.getString("destination"));
                flight.setDeparture(rs.getString("departure"));
                flight.setAirport(rs.getString("airport"));
                flight.setNoAvailableSeats(rs.getInt("no_available_seats"));
                flights.add(flight);
            }
        } catch (SQLException e) {
            logger.error("Failed to fetch flights", e);
            throw new RuntimeException(e);
        }
        return flights;
    }
}