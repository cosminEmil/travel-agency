package repository;

import model.Flight;
import repository.BaseRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}