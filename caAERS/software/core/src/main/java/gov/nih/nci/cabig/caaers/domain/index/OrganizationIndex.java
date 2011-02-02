package gov.nih.nci.cabig.caaers.domain.index;

import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

 
/**
 * The Class OrganizationIndex.
 */
@Entity
@Table(name = "organization_index")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = {@Parameter(name = "sequence", value = "seq_organization_index_id") })
public class OrganizationIndex extends AbstractMutableDomainObject{
	
	/** The login id. */
	private String loginId;
	
	/** The organization. */
	private Organization organization;

    /** The role code. */
    private Integer roleCode;

    /**
     * Gets the role code.
     *
     * @return the role code
     */
    public Integer getRoleCode() {
        return roleCode;
    }

    /**
     * Sets the role code.
     *
     * @param roleCode the new role code
     */
    public void setRoleCode(Integer roleCode) {
        this.roleCode = roleCode;
    }

    /**
     * Gets the login id.
     *
     * @return the login id
     */
    public String getLoginId() {
		return loginId;
	}
	
	/**
	 * Sets the login id.
	 *
	 * @param loginId the new login id
	 */
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	/**
	 * Gets the organization.
	 *
	 * @return the organization
	 */
	@ManyToOne
    @JoinColumn(name = "organization_id")
	public Organization getOrganization() {
		return organization;
	}
	
	/**
	 * Sets the organization.
	 *
	 * @param organization the new organization
	 */
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

}
