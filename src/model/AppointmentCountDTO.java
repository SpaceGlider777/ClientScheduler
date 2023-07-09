package model;

/**
 * Appointment DTO class.
 * Used for the first report.
 */
public class AppointmentCountDTO {
    /**
     * The appointment type.
     */
    private String type;

    /**
     * The month.
     */
    private String month;

    /**
     * The number of appointments.
     */
    private int count;

    /**
     * Constructor.
     *
     * @param type The appointment type.
     * @param month The month.
     * @param count The number of appointments.
     */
    public AppointmentCountDTO(String type, String month, int count) {
        this.type = type;
        this.month = month;
        this.count = count;
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
     * Sets the appointment type.
     *
     * @param type The appointment type.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets the month.
     *
     * @return the month.
     */
    public String getMonth() {
        return month;
    }

    /**
     * Sets the month.
     *
     * @param month The month.
     */
    public void setMonth(String month) {
        this.month = month;
    }

    /**
     * Gets the number of appointments.
     *
     * @return the number of appointments.
     */
    public int getCount() {
        return count;
    }

    /**
     * Sets the number of appointments.
     *
     * @param count The number of appointments.
     */
    public void setCount(int count) {
        this.count = count;
    }
}
