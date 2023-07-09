package dao;

import model.AppointmentCountDTO;
import model.AppointmentDTO;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Interface for appointment database access.
 */
public interface AppointmentDao {
    /**
     * Gets all appointments.
     * Used for appointments table.
     *
     * @return all appointments.
     * @throws SQLException from PreparedStatement.
     */
    List<AppointmentDTO> getAll() throws SQLException;

    /**
     * Gets all appointments between 0-15 minutes from the given LocalDateTime.
     * Used to alert user upon login.
     *
     * @param dateTime The given LocalDateTime to compare to.
     * @return all appointments between 0-15 minutes from dateTime.
     * @throws SQLException from PreparedStatement.
     */
    List<AppointmentDTO> getAllInFifteenMinutes(LocalDateTime dateTime) throws SQLException;

    /**
     * Gets counts for appointments by type and month.
     * Used for report 1.
     *
     * @return a list of counts for appointments by type and month.
     * @throws SQLException from PreparedStatement.
     */
    List<AppointmentCountDTO> getAllByTypeAndMonth() throws SQLException;

    /**
     * Gets number of appointments for a given customer.
     * Used to check if customer is safe to delete.
     *
     * @param customerId The customer to get appointments for.
     * @return the number of appointments for the customer.
     * @throws SQLException from PreparedStatement.
     */
    int getCountByCustomerId(int customerId) throws SQLException;

    /**
     * Gets number of appointments that overlap with given start and end LocalDateTime for a customer.
     * Used to check for overlapping appointments when creating a new appointment.
     *
     * @param start The start date and time.
     * @param end The end date and time.
     * @param customerId The customer to get appointments for.
     * @return The number of appointments that overlap.
     * @throws SQLException from PreparedStatement.
     */
    int getCountByTimeAndCustomerId(LocalDateTime start, LocalDateTime end, int customerId) throws SQLException;

    /**
     * Gets number of appointments that overlap with given start and end LocalDateTime for a customer.
     * Used to check for overlapping appointments when updating an appointment.
     *
     * @param start The start date and time.
     * @param end The end date and time.
     * @param customerId The customer to get appointments for.
     * @param appointmentId The appointment to exclude since it is being updated.
     * @return The number of appointments that overlap.
     * @throws SQLException from PreparedStatement.
     */
    int getCountByTimeAndCustomerId(LocalDateTime start, LocalDateTime end, int customerId, int appointmentId) throws SQLException;

    /**
     * Creates an appointment.
     *
     * @param title The title.
     * @param description The description.
     * @param location The location.
     * @param contactId The contact ID.
     * @param type The type.
     * @param start The start date and time.
     * @param end The end date and time.
     * @param customerId The customer ID.
     * @param userId The user ID.
     * @throws SQLException from PreparedStatement.
     */
    void create(String title, String description, String location, int contactId, String type, LocalDateTime start, LocalDateTime end, int customerId, int userId) throws SQLException;

    /**
     * Updates an appointment.
     *
     * @param appointmentId The appointment ID.
     * @param title The title.
     * @param description The description.
     * @param location The location.
     * @param contactId The contact ID.
     * @param type The type.
     * @param start The start date and time.
     * @param end The end date and time.
     * @param customerId The customer ID.
     * @param userId The user ID.
     * @throws SQLException from PreparedStatement.
     */
    void update(int appointmentId, String title, String description, String location, int contactId, String type, LocalDateTime start, LocalDateTime end, int customerId, int userId) throws SQLException;

    /**
     * Deletes an appointment.
     *
     * @param appointmentId The appointment ID.
     * @throws SQLException from PreparedStatement.
     */
    void delete(int appointmentId) throws SQLException;
}
