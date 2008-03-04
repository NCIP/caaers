/*
 * Sematicbits Copyright message
 */
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
@DiscriminatorValue("role")
public class RoleBasedRecipient extends Recipient {

    /** The roleName, should match with the role name, present in CTMS. */
    @Column(name = "ROLE_NAME")
    String roleName;

    public RoleBasedRecipient() {
        this("");
    }

    public RoleBasedRecipient(String role) {
        roleName = role;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    @Transient
    public String getContact() {
        return roleName;
    }

    @Override
    public String toString() {
        return "ROLE :" + roleName;
    }

}
