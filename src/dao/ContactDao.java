package dao;

import model.Contact;
import java.sql.SQLException;
import java.util.List;

/**
 * Interface for contact database access.
 */
public interface ContactDao {
    /**
     * Gets all contacts.
     *
     * @return all contacts.
     * @throws SQLException from PreparedStatement.
     */
    List<Contact> getAll() throws SQLException;
}
