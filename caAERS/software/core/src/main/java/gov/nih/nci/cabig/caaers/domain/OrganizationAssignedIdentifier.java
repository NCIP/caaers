/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
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

    /** The Constant SPONSOR_IDENTIFIER_TYPE. */
    public static final String SPONSOR_IDENTIFIER_TYPE = "Protocol Authority Identifier";
    
    /** The Constant COORDINATING_CENTER_IDENTIFIER_TYPE. */
    public static final String COORDINATING_CENTER_IDENTIFIER_TYPE = "Coordinating Center Identifier";
    
    /** The organization. */
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

    /**
     * Sets the organization.
     *
     * @param organization the new organization
     */
    public void setOrganization(final Organization organization) {
        this.organization = organization;
    }

	/* (non-Javadoc)
	 * @see gov.nih.nci.cabig.caaers.domain.Identifier#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((organization == null) ? 0 : organization.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.cabig.caaers.domain.Identifier#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (!super.equals(obj)) return false;
		if(!(obj instanceof OrganizationAssignedIdentifier)) return false;
		
		OrganizationAssignedIdentifier other = (OrganizationAssignedIdentifier) obj;
		return ComparisonTools.nullSafeEquals(getOrganization(), other.getOrganization());

	}
    
}
