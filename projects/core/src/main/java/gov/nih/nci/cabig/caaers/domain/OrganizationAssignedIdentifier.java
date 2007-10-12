package gov.nih.nci.cabig.caaers.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author Saurabh Agrawal
 */
@Entity
@DiscriminatorValue("1")
public class OrganizationAssignedIdentifier extends Identifier {
	public static final String SPONSOR_IDENTIFIER_TYPE = "Sponsor Identifier";
	public static final String COORDINATING_CENTER_IDENTIFIER_TYPE = "Coordinating Center Identifier";

	private Organization organization;

	/**
	 * Returns the organization.
	 * 
	 * @return the organization
	 */
	@ManyToOne
	@JoinColumn(name = "organization_id", nullable = false)
	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(final Organization organization) {
		this.organization = organization;
	}

}
