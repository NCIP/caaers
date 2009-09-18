package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.ctms.lang.ComparisonTools;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 * This class represents the OrganizationAssignedIdentifier domain object associated with the
 * Adverse event report.
 * 
 * @author Saurabh Agrawal
 */
@Entity
@DiscriminatorValue("1")
public class OrganizationAssignedIdentifier extends Identifier {

    public static final String SPONSOR_IDENTIFIER_TYPE = "Protocol Authority Identifier";
    public static final String COORDINATING_CENTER_IDENTIFIER_TYPE = "Coordinating Center Identifier";
    private Organization organization;

    /**
     * Returns the organization.
     * 
     * @return the organization
     */
    @ManyToOne
    @JoinColumn(name = "organization_id", nullable = false)
    @Cascade(value = { CascadeType.LOCK })
    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(final Organization organization) {
        this.organization = organization;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((organization == null) ? 0 : organization.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (!super.equals(obj)) return false;
		if(!(obj instanceof OrganizationAssignedIdentifier)) return false;
		
		OrganizationAssignedIdentifier other = (OrganizationAssignedIdentifier) obj;
		return ComparisonTools.nullSafeEquals(getOrganization(), other.getOrganization());

	}
    
}
