package model;

/**
 * Customer DTO class.
 */
public class CustomerDTO {
    /**
     * The customer ID.
     */
    private int customerId;

    /**
     * The customer name.
     */
    private String customerName;

    /**
     * The address.
     */
    private String address;

    /**
     * The postal code.
     */
    private String postalCode;

    /**
     * The phone.
     */
    private String phone;

    /**
     * The country.
     */
    private String country;

    /**
     * The division ID.
     */
    private int divisionId;

    /**
     * The division.
     */
    private String division;

    /**
     * Constructor.
     *
     * @param customerId The customer ID.
     * @param customerName The customer name.
     * @param address The address.
     * @param postalCode The postal code.
     * @param phone The phone.
     * @param country The country.
     * @param divisionId The division ID.
     * @param division The division.
     */
    public CustomerDTO(int customerId, String customerName, String address, String postalCode, String phone, String country, int divisionId, String division) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.country = country;
        this.divisionId = divisionId;
        this.division = division;
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
     * Gets the customer name.
     *
     * @return the customer name.
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * Sets the customer name.
     *
     * @param customerName The customer name.
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * Gets the address.
     *
     * @return the address.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the address.
     *
     * @param address The address.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets the postal code.
     *
     * @return the postal code.
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Sets the postal code.
     *
     * @param postalCode The postal code.
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * Gets the phone.
     *
     * @return the phone.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the phone.
     *
     * @param phone The phone.
     */
    public void setPhone(String phone) {
        this.phone = phone;
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
}
