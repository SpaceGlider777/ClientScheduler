package dao;

import javafx.collections.FXCollections;
import model.User;
import util.JDBC;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Implementation for UserDao.
 */
public class UserDaoImpl implements UserDao {
    /**
     * {@inheritDoc}
     */
    @Override
    public User getUser(String userName, String password) throws SQLException {
        String sql = "SELECT * FROM users WHERE User_Name = ? AND Password = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, userName);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            int userId = rs.getInt("User_ID");
            return new User(userId, userName, password);
        } else {
            return null;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<User> getAll() throws SQLException {
        List<User> users = FXCollections.observableArrayList();
        String sql = "SELECT * FROM users";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int userId = rs.getInt("User_ID");
            String userName = rs.getString("User_Name");
            User user = new User(userId, userName, null);
            users.add(user);
        }

        return users;
    }
}
