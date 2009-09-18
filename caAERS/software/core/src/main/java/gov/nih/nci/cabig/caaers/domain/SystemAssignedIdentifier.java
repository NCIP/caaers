package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.ctms.lang.ComparisonTools;

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((systemName == null) ? 0 : systemName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		
		if (!super.equals(obj)) return false;
		if (!(obj instanceof SystemAssignedIdentifier)) return false;
		
		SystemAssignedIdentifier other = (SystemAssignedIdentifier) obj;
		return ComparisonTools.nullSafeEquals(getSystemName(), other.getSystemName());
		
	}
    
    
}
