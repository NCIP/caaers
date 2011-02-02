package gov.nih.nci.cabig.caaers.domain.report;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

 
/**
 * The Class ContactMechanismBasedRecipient.
 *
 * @author <a href="mailto:biju.joseph@semanticbits.com">Biju Joseph</a> Created-on : May 11, 2007
 * @version %I%, %G%
 * @since 1.0
 */
@Entity
@DiscriminatorValue("contact")
public class ContactMechanismBasedRecipient extends Recipient {

    /**
     * Instantiates a new contact mechanism based recipient.
     */
    public ContactMechanismBasedRecipient() {
        this("");
    }

    /**
     * Instantiates a new contact mechanism based recipient.
     *
     * @param contact the contact
     */
    public ContactMechanismBasedRecipient(String contact) {
        this.contactName = contact;
    }

    /** The contact name. */
    @Column(name = "CONTACT_NAME")
    private String contactName;

    /**
     * Gets the contact name.
     *
     * @return the contact
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * Sets the contact name.
     *
     * @param contactName the new contact name
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.report.Recipient#getContact()
     */
    @Override
    @Transient
    public String getContact() {
        return contactName;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return " " + contactName + " ";
    }
}
