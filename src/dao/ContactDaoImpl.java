package dao;

import javafx.collections.FXCollections;
import model.Contact;
import util.JDBC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Implementation of ContactDao.
 */
public class ContactDaoImpl implements ContactDao {
    /**
     * {@inheritDoc}
     */
    @Override
    public List<Contact> getAll() throws SQLException {
        List<Contact> contacts = FXCollections.observableArrayList();
        String sql = "SELECT * FROM contacts";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int contactId = rs.getInt("Contact_ID");
            String contactName = rs.getString("Contact_Name");
            String email = rs.getString("Email");
            Contact contact = new Contact(contactId, contactName, email);
            contacts.add(contact);
        }

        return contacts;
    }
}
