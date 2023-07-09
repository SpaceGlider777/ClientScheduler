package model;

/**
 * Country class.
 */
public class Country {
    /**
     * The country ID.
     */
    private int countryId;

    /**
     * The country name.
     */
    private String country;

    /**
     * Constructor.
     *
     * @param countryId The country ID.
     * @param country The country name.
     */
    public Country(int countryId, String country) {
        this.countryId = countryId;
        this.country = country;
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

    /**
     * Gets the country name.
     *
     * @return the country name.
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets the country name.
     *
     * @param country The country name.
     */
    public void setCountry(String country) {
        this.country = country;
    }
}
