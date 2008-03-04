package gov.nih.nci.cabig.caaers.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author Saurabh Agrawal
 */
@Entity
@DiscriminatorValue("2")
public class SystemAssignedIdentifier extends Identifier {

    public static final String MRN_IDENTIFIER_TYPE = "MRN";

    private String systemName;

    /**
     * Returns the system name.
     * 
     * @return the system name
     */
    @Column(name = "system_name", nullable = true)
    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(final String systemName) {
        this.systemName = systemName;
    }
}
