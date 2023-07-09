package model;

/**
 * Customer Count DTO class.
 * Used for the third report.
 */
public class CustomerCountDTO {
    /**
     * The country.
     */
    private String country;

    /**
     * The division.
     */
    private String division;

    /**
     * The number of appointments.
     */
    private int count;

    /**
     * Constructor.
     *
     * @param country The country.
     * @param division The division.
     * @param count The number of appointments.
     */
    public CustomerCountDTO(String country, String division, int count) {
        this.country = country;
        this.division = division;
        this.count = count;
    }

    /**
     * Gets the country.
     *
     * @return the country.
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets the country.
     *
     * @param country The country.
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Gets the division.
     *
     * @return the division.
     */
    public String getDivision() {
        return division;
    }

    /**
     * Sets the division.
     *
     * @param division The division.
     */
    public void setDivision(String division) {
        this.division = division;
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
