package model;

/**
 * Division class.
 */
public class FirstLevelDivision {
    /**
     * The division ID.
     */
    private int divisionId;

    /**
     * The division.
     */
    private String division;

    /**
     * The country ID.
     */
    private int countryId;

    /**
     * Constructor.
     *
     * @param divisionId The division ID.
     * @param division The division.
     * @param countryId The country ID.
     */
    public FirstLevelDivision(int divisionId, String division, int countryId) {
        this.divisionId = divisionId;
        this.division = division;
        this.countryId = countryId;
    }

    /**
     * Gets the division ID.
     *
     * @return the division ID.
     */
    public int getDivisionId() {
        return divisionId;
    }

    /**
     * Sets the division ID.
     *
     * @param divisionId The division ID.
     */
    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
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
     * Gets the country ID.
     *
     * @return the country ID.
     */
    public int getCountryId() {
        return countryId;
    }

    /**
     * Sets the country ID.
     *
     * @param countryId The country ID.
     */
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }
}
