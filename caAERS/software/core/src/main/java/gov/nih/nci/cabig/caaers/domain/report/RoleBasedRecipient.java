/*
 * Sematicbits Copyright message
 */
package gov.nih.nci.cabig.caaers.domain.report;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

 
/**
 * The Class RoleBasedRecipient.
 *
 * @author <a href="mailto:biju.joseph@semanticbits.com">Biju Joseph</a> Created-on : May 11, 2007
 * @version %I%, %G%
 * @since 1.0
 */
@Entity
@DiscriminatorValue("role")
public class RoleBasedRecipient extends Recipient {

    /** The roleName, should match with the role name, present in CTMS. */
    @Column(name = "ROLE_NAME")
    String roleName;

    /**
     * Instantiates a new role based recipient.
     */
    public RoleBasedRecipient() {
        this("");
    }

    /**
     * Instantiates a new role based recipient.
     *
     * @param role the role
     */
    public RoleBasedRecipient(String role) {
        roleName = role;
    }

    /**
     * Gets the role name.
     *
     * @return the role name
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * Sets the role name.
     *
     * @param roleName the new role name
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.report.Recipient#getContact()
     */
    @Override
    @Transient
    public String getContact() {
        return roleName;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ROLE :" + roleName;
    }

}
