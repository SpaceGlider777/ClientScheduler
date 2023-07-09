package model;

/**
 * Contact class.
 */
public class Contact {
    /**
     * The contact ID.
     */
    private int contactId;

    /**
     * The contact name.
     */
    private String contactName;

    /**
     * The contact email.
     */
    private String email;

    /**
     * Constructor.
     *
     * @param contactId The contact ID.
     * @param contactName The contact name.
     * @param email The contact email.
     */
    public Contact(int contactId, String contactName, String email) {
        this.contactId = contactId;
        this.contactName = contactName;
        this.email = email;
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
     * Gets the contact name.
     *
     * @return the contact name.
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * Sets the contact name.
     *
     * @param contactName The contact name.
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
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
