package gov.nih.nci.cabig.caaers.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

 
/**
 * The Class OrganizationHeldIND.
 */
@Entity
@DiscriminatorValue("ORG")
public class OrganizationHeldIND extends INDHolder {
    
    /** The organization. */
    private Organization organization;

    /**
     * Gets the organization.
     *
     * @return the organization
     */
    @ManyToOne(optional = false, fetch=FetchType.LAZY)
    @JoinColumn(name = "org_id", nullable = false)
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

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.INDHolder#getName()
     */
    @Override
    @Transient
    public String getName() {
        if (organization != null) return organization.getName();
        return null;
    }

    @Override
    @Transient
    public boolean isNciAffiliate() {
        if(getOrganization() != null){
            return Organization.NCI_ORG_CODES.contains(getOrganization().getNciInstituteCode());
        }
        return super.isNciAffiliate();
    }
}
