package org.example.newprojectmpp.repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public abstract class BaseRepository<T> implements IRepository<T> {
    // 1. Logger initialization
    protected static final Logger logger = LogManager.getLogger(BaseRepository.class);
    protected final Connection connection;
    protected final String tableName;

    public BaseRepository(Connection connection, String tableName) {
        this.connection = connection;
        this.tableName = tableName;
        logger.debug("Initialized org.example.newprojectmpp.repository for table: {}", tableName);
    }

    protected abstract void setAddParameters(PreparedStatement stmt, T entity) throws SQLException;
    protected abstract void setSearchParameters(PreparedStatement stmt, T entity) throws SQLException;
    protected abstract void setRemoveParameters(PreparedStatement stmt, T entity) throws SQLException;

    @Override
    public void add(T entity) {
        // 2. Log the start of the operation
        logger.trace("Attempting to add entity to table: {}", tableName);

        String sql = "INSERT INTO " + tableName + getInsertFields() + " VALUES " + getPlaceholders();
        try (PreparedStatement stmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            // 3. Log parameter binding
            logger.debug("Preparing statement: {}", sql);
            setAddParameters(stmt, entity);

            int affectedRows = stmt.executeUpdate();
            logger.debug("Affected rows: {}", affectedRows);

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    int generatedId = rs.getInt(1);
                    setId(entity, generatedId); //Set generated ID
                    // 4. Success log
                    logger.info("Successfully added entity to {} with ID: {}", tableName, generatedId);
                }
            }
        } catch (SQLException e) {
            // 5. Error logging with stack trace;
            logger.error("Failed to add entity to table {}: {}", tableName, e.getMessage(), e);
            throw new RuntimeException("Failed to add entity", e);
        }
    }

    @Override
    public boolean search(T entity) {
        logger.trace("Searching in table: {}", tableName);
        String sql = "SELECT 1 FROM " + tableName + " WHERE " + getSearchCondition();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            logger.debug("Executing search query: {}", sql);
            setSearchParameters(stmt, entity);

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                boolean found =  rs.next();
                logger.debug("Search result in {}: {}", tableName, found);
                return found;
            }
        } catch (SQLException e) {
            logger.error("Search failed in table {}: {}", tableName, e.getMessage(), e);
            throw new RuntimeException("Search failed", e);
        }
    }

    @Override
    public void remove(T entity) {
        logger.trace("Attempting to remove from table: {}", tableName);
        String sql = "DELETE FROM " + tableName + " WHERE " + getDeleteCondition();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            logger.debug("Preparing delete statement: {}", sql);
            setRemoveParameters(stmt, entity);

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                logger.info("Successfully removed {} record(s) from {}", affectedRows, tableName);
            } else {
                logger.warn("No records matched deletion criteria in {}", tableName);
            }
        } catch (SQLException e) {
            logger.error("Deletion failed in table {}: {}", tableName, e.getMessage(), e);
            throw new RuntimeException("Remove failed", e);
        }
    }

    protected abstract String getInsertFields();
    protected abstract String getPlaceholders();
    protected abstract String getSearchCondition();
    protected abstract String getDeleteCondition();
    protected abstract void setId(T entity, int id);
}
