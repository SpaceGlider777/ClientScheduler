package util;

import dao.AppointmentDao;
import dao.AppointmentDaoImpl;
import javafx.scene.control.Alert;
import model.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Utility class for validating customer and appointment forms.
 * Displays a notification when invalid.
 */
public class ValidatorUtil {
    /**
     * Validates appointment fields.
     *
     * @param title The title.
     * @param description The description.
     * @param location The location.
     * @param contact The contact.
     * @param type The type.
     * @param startDate The start date.
     * @param startHour The start hour.
     * @param startMin The start minute.
     * @param endHour The end hour.
     * @param endMin The end minute.
     * @param customer The customer.
     * @param user The user.
     * @return true if all fields are valid, false otherwise.
     */
    public static boolean validate(String title, String description, String location, Contact contact, String type, LocalDate startDate, String startHour, String startMin, String endHour, String endMin, CustomerDTO customer, User user) {
        boolean isValid = true;
        StringBuilder errorMsg = new StringBuilder();

        if (title.isBlank()) {
            errorMsg.append("Title cannot be blank.\n");
            isValid = false;
        }

        if (description.isBlank()) {
            errorMsg.append("Description cannot be blank.\n");
            isValid = false;
        }

        if (location.isBlank()) {
            errorMsg.append("Location cannot be blank.\n");
            isValid = false;
        }

        if (contact == null) {
            errorMsg.append("Please select a contact.\n");
            isValid = false;
        }

        if (type.isBlank()) {
            errorMsg.append("Type cannot be blank.\n");
            isValid = false;
        }

        if (startDate == null) {
            errorMsg.append("Please select a start date.\n");
            isValid = false;
        }

        try {
            int startHourInt = Integer.parseInt(startHour);
            if (startHourInt < 0 || startHourInt > 23) {
                errorMsg.append("Invalid start hour.\n");
                isValid = false;
            }
        } catch (NumberFormatException e) {
            errorMsg.append("Start hour must be a number.\n");
            isValid = false;
        }

        try {
            int startMinInt = Integer.parseInt(startMin);
            if (startMinInt < 0 || startMinInt > 59) {
                errorMsg.append("Invalid start minutes.\n");
                isValid = false;
            }
        } catch (NumberFormatException e) {
            errorMsg.append("Start minutes must be a number.\n");
            isValid = false;
        }

        try {
            int endHourInt = Integer.parseInt(endHour);
            if (endHourInt < 0 || endHourInt > 23) {
                errorMsg.append("Invalid end hour.\n");
                isValid = false;
            }
        } catch (NumberFormatException e) {
            errorMsg.append("End hour must be a number.\n");
            isValid = false;
        }

        try {
            int endMinInt = Integer.parseInt(endMin);
            if (endMinInt < 0 || endMinInt > 59) {
                errorMsg.append("Invalid end minutes.\n");
                isValid = false;
            }
        } catch (NumberFormatException e) {
            errorMsg.append("End minutes must be a number.\n");
            isValid = false;
        }

        try {
            int startHourInt = Integer.parseInt(startHour);
            int startMinInt = Integer.parseInt(startMin);
            int endHourInt = Integer.parseInt(endHour);
            int endMinInt = Integer.parseInt(endMin);

            if (endHourInt < startHourInt || (endHourInt == startHourInt && endMinInt <= startMinInt)) {
                errorMsg.append("End time cannot be before start time.\n");
                isValid = false;
            }
        } catch (NumberFormatException ignored) { }

        if (customer == null) {
            errorMsg.append("Please select a customer.\n");
            isValid = false;
        }

        if (user == null) {
            errorMsg.append("Please select a user.\n");
            isValid = false;
        }

        try {
            LocalTime startTime = LocalTime.of(Integer.parseInt(startHour), Integer.parseInt(startMin));
            LocalDateTime startDateTime = LocalDateTime.of(startDate, startTime);

            if (!TimezoneUtil.isDuringBusinessHours(startDateTime)) {
                errorMsg.append("Start time is not between 8AM - 10PM EST.\n");
                isValid = false;
            }
        } catch (Exception e) { }

        try {
            LocalTime endTime = LocalTime.of(Integer.parseInt(endHour), Integer.parseInt(endMin));
            LocalDateTime endDateTime = LocalDateTime.of(startDate, endTime);

            if (!TimezoneUtil.isDuringBusinessHours(endDateTime)) {
                errorMsg.append("End time is not between 8AM - 10PM EST.\n");
                isValid = false;
            }
        } catch (Exception e) { }

        if (!isValid) {
            NotificationUtil.displayAlert(Alert.AlertType.ERROR, "Save Appointment", errorMsg.toString());
        }

        return isValid;
    }

    /**
     * Validates if an appointment overlaps with another appointment.
     * Used when creating an appointment.
     *
     * @param start The start date and time.
     * @param end The end date and time.
     * @param customerId The customer ID.
     * @return true if the appointment does not overlap, false otherwise.
     * @throws SQLException from DAO.
     */
    public static boolean validateTimeOverlap(LocalDateTime start, LocalDateTime end, int customerId) throws SQLException {
        AppointmentDao appointmentDao = new AppointmentDaoImpl();
        int count = appointmentDao.getCountByTimeAndCustomerId(start, end, customerId);

        if (count > 0) {
            NotificationUtil.displayAlert(Alert.AlertType.ERROR, "Save Appointment", "Time overlaps with another appointment.");
            return false;
        }
        return true;
    }

    /**
     * Validates if an appointment overlaps with another appointment.
     * Used when updating an appointment.
     *
     * @param start The start date and time.
     * @param end The end date and time.
     * @param customerId The customer ID.
     * @param appointmentId The ID of the appointment that is being updated.
     * @return true if the appointment does not overlap, false otherwise.
     * @throws SQLException from DAO.
     */
    public static boolean validateTimeOverlap(LocalDateTime start, LocalDateTime end, int customerId, int appointmentId) throws SQLException {
        AppointmentDao appointmentDao = new AppointmentDaoImpl();
        int count = appointmentDao.getCountByTimeAndCustomerId(start, end, customerId, appointmentId);

        if (count > 0) {
            NotificationUtil.displayAlert(Alert.AlertType.ERROR, "Save Appointment", "Time overlaps with another appointment.");
            return false;
        }
        return true;
    }

    /**
     * Validates customer fields.
     *
     * @param name The name.
     * @param address The address.
     * @param postalCode The postal code.
     * @param phone The phone.
     * @param country The country.
     * @param division The division.
     * @return true if all fields are valid, false otherwise.
     */
    public static boolean validate(String name, String address, String postalCode, String phone, Country country, FirstLevelDivision division) {
        boolean isValid = true;
        StringBuilder errorMsg = new StringBuilder();

        if (name.isBlank()) {
            errorMsg.append("Name cannot be blank.\n");
            isValid = false;
        }

        if (address.isBlank()) {
            errorMsg.append("Address cannot be blank.\n");
            isValid = false;
        }

        if (postalCode.isBlank()) {
            errorMsg.append("Postal code cannot be blank.\n");
            isValid = false;
        }

        if (phone.isBlank()) {
            errorMsg.append("Phone cannot be blank.\n");
            isValid = false;
        }

        if (country == null) {
            errorMsg.append("Please select a country.\n");
            isValid = false;
        }

        if (division == null) {
            errorMsg.append("Please select a division.\n");
            isValid = false;
        }

        if (!isValid) {
            NotificationUtil.displayAlert(Alert.AlertType.ERROR, "Save Customer", errorMsg.toString());
        }

        return isValid;
    }
}
