package dao;

import javafx.collections.FXCollections;
import model.AppointmentCountDTO;
import model.AppointmentDTO;
import util.CurrentUser;
import util.JDBC;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Implementation of AppointmentDao.
 */
public class AppointmentDaoImpl implements AppointmentDao {
    /**
     * {@inheritDoc}
     */
    @Override
    public List<AppointmentDTO> getAll() throws SQLException {
        List<AppointmentDTO> appointments = FXCollections.observableArrayList();
        String sql = "SELECT appointments.*, Email FROM appointments INNER JOIN contacts ON appointments.Contact_ID = contacts.Contact_ID ORDER BY Appointment_ID";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int appointmentId = rs.getInt("Appointment_ID");
            String title = rs.getString("Title");
            String description = rs.getString("Description");
            String location = rs.getString("Location");
            String type = rs.getString("Type");
            LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();
            LocalDateTime end = rs.getTimestamp("End").toLocalDateTime();
            int customerId = rs.getInt("Customer_ID");
            int userId = rs.getInt("User_ID");
            int contactId = rs.getInt("Contact_ID");
            String email = rs.getString("Email");
            AppointmentDTO appointment = new AppointmentDTO(appointmentId, title, description, location, type, start, end, customerId, userId, contactId, email);
            appointments.add(appointment);
        }

        return appointments;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<AppointmentDTO> getAllInFifteenMinutes(LocalDateTime dateTime) throws SQLException {
        List<AppointmentDTO> appointments = FXCollections.observableArrayList();
        String sql = "SELECT *, TIMESTAMPDIFF(MINUTE, ?, start) AS diff FROM appointments HAVING diff BETWEEN 0 AND 15 ORDER BY diff";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setTimestamp(1, Timestamp.valueOf(dateTime));
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int appointmentId = rs.getInt("Appointment_ID");
            String title = rs.getString("Title");
            String description = rs.getString("Description");
            String location = rs.getString("Location");
            String type = rs.getString("Type");
            LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();
            LocalDateTime end = rs.getTimestamp("End").toLocalDateTime();
            int customerId = rs.getInt("Customer_ID");
            int userId = rs.getInt("User_ID");
            int contactId = rs.getInt("Contact_ID");
            AppointmentDTO appointment = new AppointmentDTO(appointmentId, title, description, location, type, start, end, customerId, userId, contactId, null);
            appointments.add(appointment);
        }

        return appointments;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<AppointmentCountDTO> getAllByTypeAndMonth() throws SQLException {
        List<AppointmentCountDTO> appointmentCounts = FXCollections.observableArrayList();
        String sql = "SELECT MONTHNAME(Start) AS m, Type, COUNT(*) AS c from appointments GROUP BY m, Type ORDER BY m";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            String month = rs.getString("m");
            String type = rs.getString("Type");
            int count = rs.getInt("c");
            AppointmentCountDTO appointmentCountDTO = new AppointmentCountDTO(type, month, count);
            appointmentCounts.add(appointmentCountDTO);
        }

        return appointmentCounts;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getCountByCustomerId(int customerId) throws SQLException {
        int count = 0;
        String sql = "SELECT COUNT(*) AS num FROM appointments WHERE Customer_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, customerId);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            count = rs.getInt("num");
        }

        return count;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getCountByTimeAndCustomerId(LocalDateTime start, LocalDateTime end, int customerId) throws SQLException {
        int count = 0;
        String sql = "SELECT COUNT(*) AS num FROM appointments WHERE Customer_ID = ? AND " +
                     "(? BETWEEN start AND end OR ? BETWEEN start AND end OR start BETWEEN ? AND ?)";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, customerId);
        ps.setTimestamp(2, Timestamp.valueOf(start));
        ps.setTimestamp(3, Timestamp.valueOf(end));
        ps.setTimestamp(4, Timestamp.valueOf(start));
        ps.setTimestamp(5, Timestamp.valueOf(end));
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            count = rs.getInt("num");
        }

        return count;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getCountByTimeAndCustomerId(LocalDateTime start, LocalDateTime end, int customerId, int appointmentId) throws SQLException {
        int count = 0;
        String sql = "SELECT COUNT(*) AS num FROM appointments WHERE Customer_ID = ? AND Appointment_ID != ? AND " +
                "(? BETWEEN start AND end OR ? BETWEEN start AND end OR start BETWEEN ? AND ?)";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, customerId);
        ps.setInt(2, appointmentId);
        ps.setTimestamp(3, Timestamp.valueOf(start));
        ps.setTimestamp(4, Timestamp.valueOf(end));
        ps.setTimestamp(5, Timestamp.valueOf(start));
        ps.setTimestamp(6, Timestamp.valueOf(end));
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            count = rs.getInt("num");
        }

        return count;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void create(String title, String description, String location, int contactId, String type, LocalDateTime start, LocalDateTime end, int customerId, int userId) throws SQLException {
        String sql = "INSERT INTO appointments (Title, Description, Location, Type, Start, End, Create_Date, Created_By, Last_Update, Last_Updated_By, Customer_ID, User_ID, Contact_ID) " +
                     "VALUES (?, ?, ?, ?, ?, ?, NOW(), ?, NOW(), ?, ?, ?, ?)";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, title);
        ps.setString(2, description);
        ps.setString(3, location);
        ps.setString(4, type);
        ps.setTimestamp(5, Timestamp.valueOf(start));
        ps.setTimestamp(6, Timestamp.valueOf(end));
        ps.setString(7, CurrentUser.LoggedInUser.getUserName());
        ps.setString(8, CurrentUser.LoggedInUser.getUserName());
        ps.setInt(9, customerId);
        ps.setInt(10, userId);
        ps.setInt(11, contactId);
        ps.executeUpdate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(int appointmentId, String title, String description, String location, int contactId, String type, LocalDateTime start, LocalDateTime end, int customerId, int userId) throws SQLException {
        String sql = "UPDATE appointments SET Title = ?, Description = ?, Location = ?, Type = ?, Start = ?, End = ?, Last_Update = NOW(), Last_Updated_By = ?, Customer_ID = ?, User_ID = ?, Contact_ID = ? " +
                     "WHERE Appointment_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, title);
        ps.setString(2, description);
        ps.setString(3, location);
        ps.setString(4, type);
        ps.setTimestamp(5, Timestamp.valueOf(start));
        ps.setTimestamp(6, Timestamp.valueOf(end));
        ps.setString(7, CurrentUser.LoggedInUser.getUserName());
        ps.setInt(8, customerId);
        ps.setInt(9, userId);
        ps.setInt(10, contactId);
        ps.setInt(11, appointmentId);
        ps.executeUpdate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(int appointmentId) throws SQLException {
        String sql = "DELETE FROM appointments WHERE Appointment_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, appointmentId);
        ps.executeUpdate();
    }
}
