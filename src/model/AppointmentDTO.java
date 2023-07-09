package model;

import java.time.LocalDateTime;

/**
 * Appointment DTO class.
 */
public class AppointmentDTO {
    /**
     * The appointment ID.
     */
    private int appointmentId;

    /**
     * The title.
     */
    private String title;

    /**
     * The description.
     */
    private String description;

    /**
     * The location.
     */
    private String location;

    /**
     * The type.
     */
    private String type;

    /**
     * The start date and time.
     */
    private LocalDateTime start;

    /**
     * The end date and time.
     */
    private LocalDateTime end;

    /**
     * The customer ID.
     */
    private int customerId;

    /**
     * The user ID.
     */
    private int userId;

    /**
     * The contact ID.
     */
    private int contactId;

    /**
     * The contact email.
     */
    private String email;

    /**
     * Constructor.
     *
     * @param appointmentId The appointment ID.
     * @param title The title.
     * @param description The description.
     * @param location The location.
     * @param type The type.
     * @param start The start date and time.
     * @param end The end date and time.
     * @param customerId The customer ID.
     * @param userId The user ID.
     * @param contactId The contact ID.
     * @param email The contact email.
     */
    public AppointmentDTO(int appointmentId, String title, String description, String location, String type, LocalDateTime start, LocalDateTime end, int customerId, int userId, int contactId, String email) {
        this.appointmentId = appointmentId;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.start = start;
        this.end = end;
        this.customerId = customerId;
        this.userId = userId;
        this.contactId = contactId;
        this.email = email;
    }

    /**
     * Gets the appointment ID.
     *
     * @return the appointment ID.
     */
    public int getAppointmentId() {
        return appointmentId;
    }

    /**
     * Sets the appointment ID.
     *
     * @param appointmentId The appointment ID.
     */
    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    /**
     * Gets the title.
     *
     * @return the title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title.
     *
     * @param title The title.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the description.
     *
     * @return the description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description.
     *
     * @param description The description.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the location.
     *
     * @return the location.
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets the location.
     *
     * @param location The location.
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Gets the type.
     *
     * @return the type.
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the type.
     *
     * @param type The type.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets the start date and time.
     *
     * @return the start date and time.
     */
    public LocalDateTime getStart() {
        return start;
    }

    /**
     * Sets the start date and time.
     *
     * @param start The start date and time.
     */
    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    /** Gets the end date and time.
     *
     * @return the end date and time.
     */
    public LocalDateTime getEnd() {
        return end;
    }

    /**
     * Sets the end date and time.
     *
     * @param end The end date and time.
     */
    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    /**
     * Gets the customer ID.
     *
     * @return the customer ID.
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * Sets the customer ID.
     *
     * @param customerId The customer ID.
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     * Gets the user ID.
     *
     * @return the user ID.
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets the user ID.
     *
     * @param userId The user ID.
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Gets the contact ID.
     *
     * @return the contact ID.
     */
    public int getContactId() {
        return contactId;
    }

    /**
     * Sets the contact ID.
     *
     * @param contactId The contact ID.
     */
    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    /**
     * Gets the contact email.
     *
     * @return the contact email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the contact email.
     *
     * @param email The contact email.
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
