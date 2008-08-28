package gov.nih.nci.cabig.caaers.domain.report;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

/**
 * 
 * @author <a href="mailto:biju.joseph@semanticbits.com">Biju Joseph</a> Created-on : May 11, 2007
 * @version %I%, %G%
 * @since 1.0
 */
@Entity
@DiscriminatorValue("contact")
public class ContactMechanismBasedRecipient extends Recipient {

    public ContactMechanismBasedRecipient() {
        this("");
    }

    public ContactMechanismBasedRecipient(String contact) {
        this.contactName = contact;
    }

    @Column(name = "CONTACT_NAME")
    private String contactName;

    /**
     * @return the contact
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * @param contact
     *                the contact to set
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    @Override
    @Transient
    public String getContact() {
        return contactName;
    }

    @Override
    public String toString() {
        return " " + contactName + " ";
    }
}
