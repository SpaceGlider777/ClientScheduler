package dao;

import javafx.collections.FXCollections;
import model.CustomerCountDTO;
import model.CustomerDTO;
import util.CurrentUser;
import util.JDBC;
import java.sql.*;
import java.util.List;

/**
 * Implementation of CustomerDao.
 */
public class CustomerDaoImpl implements CustomerDao {
    /**
     * {@inheritDoc}
     */
    @Override
    public List<CustomerDTO> getAll() throws SQLException {
        List<CustomerDTO> customers = FXCollections.observableArrayList();
        String sql = "SELECT * FROM customers";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int customerId = rs.getInt("Customer_ID");
            String customerName = rs.getString("Customer_Name");
            String address = rs.getString("Address");
            String postalCode = rs.getString("Postal_Code");
            String phone = rs.getString("Phone");
            int divisionId = rs.getInt("Division_ID");
            CustomerDTO customer = new CustomerDTO(customerId, customerName, address, postalCode, phone, null, divisionId, null);
            customers.add(customer);
        }

        return customers;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<CustomerDTO> getAllWithDivisionData() throws SQLException {
        List<CustomerDTO> customers = FXCollections.observableArrayList();
        String sql = "SELECT customers.*, Country, Division FROM customers INNER JOIN first_level_divisions ON customers.Division_ID = first_level_divisions.Division_ID " +
                     "INNER JOIN countries ON first_level_divisions.Country_ID = countries.Country_ID ORDER BY Customer_ID";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int customerId = rs.getInt("Customer_ID");
            String customerName = rs.getString("Customer_Name");
            String address = rs.getString("Address");
            String postalCode = rs.getString("Postal_Code");
            String phone = rs.getString("Phone");
            String country = rs.getString("Country");
            int divisionId = rs.getInt("Division_ID");
            String division = rs.getString("Division");
            CustomerDTO customer = new CustomerDTO(customerId, customerName, address, postalCode, phone, country, divisionId, division);
            customers.add(customer);
        }

        return customers;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<CustomerCountDTO> getAllByCountryAndDivision() throws SQLException {
        List<CustomerCountDTO> customers = FXCollections.observableArrayList();
        String sql = "SELECT Country, Division, COUNT(*) AS c FROM customers " +
                     "INNER JOIN first_level_divisions AS fld " +
                     "ON customers.Division_ID = fld.Division_ID " +
                     "INNER JOIN countries " +
                     "ON fld.Country_ID = countries.Country_ID " +
                     "GROUP BY fld.Division_ID, countries.Country_ID";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            String country = rs.getString("Country");
            String division = rs.getString("Division");
            int count = rs.getInt("c");
            CustomerCountDTO customerCount = new CustomerCountDTO(country, division, count);
            customers.add(customerCount);
        }

        return customers;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void create(String name, String address, String postalCode, String phone, int divisionId) throws SQLException {
        String sql = "INSERT INTO Customers (Customer_Name, Address, Postal_Code, Phone, Create_Date, Created_By, Last_Update, Last_Updated_By, Division_ID) " +
                     "VALUES (?, ?, ?, ?, NOW(), ?, NOW(), ?, ?)";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, name);
        ps.setString(2, address);
        ps.setString(3, postalCode);
        ps.setString(4, phone);
        ps.setString(5, CurrentUser.LoggedInUser.getUserName());
        ps.setString(6, CurrentUser.LoggedInUser.getUserName());
        ps.setInt(7, divisionId);
        ps.executeUpdate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(int customerId, String name, String address, String postalCode, String phone, int divisionId) throws SQLException {
        String sql = "UPDATE Customers SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Last_Update = NOW(), Last_Updated_By = ?, Division_ID = ? " +
                     "WHERE Customer_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, name);
        ps.setString(2, address);
        ps.setString(3, postalCode);
        ps.setString(4, phone);
        ps.setString(5, CurrentUser.LoggedInUser.getUserName());
        ps.setInt(6, divisionId);
        ps.setInt(7, customerId);
        ps.executeUpdate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(int customerId) throws SQLException {
        String sql = "DELETE FROM Customers WHERE Customer_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, customerId);
        ps.executeUpdate();
    }
}
