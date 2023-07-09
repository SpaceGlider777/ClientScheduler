package dao;

import model.User;
import java.sql.SQLException;
import java.util.List;

/**
 * Interface for user database access.
 */
public interface UserDao {
    /**
     * Gets user given username and password.
     *
     * @param userName The username.
     * @param password The password.
     * @return The user or null if no user exists with the given username and password.
     * @throws SQLException from PreparedStatement.
     */
    User getUser(String userName, String password) throws SQLException;

    /**
     * Gets all users.
     *
     * @return all users.
     * @throws SQLException from PreparedStatement.
     */
    List<User> getAll() throws SQLException;
}
