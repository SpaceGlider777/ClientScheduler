package dao;

import model.CustomerCountDTO;
import model.CustomerDTO;
import java.sql.SQLException;
import java.util.List;

/**
 * Interface for customer database access.
 */
public interface CustomerDao {
    /**
     * Gets all customers.
     *
     * @return all customers.
     * @throws SQLException from PreparedStatement.
     */
    List<CustomerDTO> getAll() throws SQLException;

    /**
     * Gets all customers including division data.
     * Used for customer table.
     *
     * @return all customers including division data.
     * @throws SQLException from PreparedStatement.
     */
    List<CustomerDTO> getAllWithDivisionData() throws SQLException;

    /**
     * Gets counts of customers by country and division..
     * Used for third report.
     *
     * @return counts of customers by country and division.
     * @throws SQLException from PreparedStatement.
     */
    List<CustomerCountDTO> getAllByCountryAndDivision() throws SQLException;

    /**
     * Creates a customer.
     *
     * @param name The name.
     * @param address The address.
     * @param postalCode The postal code.
     * @param phone The phone.
     * @param divisionId The division ID.
     * @throws SQLException from PreparedStatement.
     */
    void create(String name, String address, String postalCode, String phone, int divisionId) throws SQLException;

    /**
     * Updates a customer.
     *
     * @param customerId The customer ID.
     * @param name The name.
     * @param address The address.
     * @param postalCode The postal code.
     * @param phone The phone.
     * @param divisionId The division ID.
     * @throws SQLException from PreparedStatement.
     */
    void update(int customerId, String name, String address, String postalCode, String phone, int divisionId) throws SQLException;

    /**
     * Deletes a customer.
     *
     * @param customerId The customer ID.
     * @throws SQLException from PreparedStatement.
     */
    void delete(int customerId) throws SQLException;
}
