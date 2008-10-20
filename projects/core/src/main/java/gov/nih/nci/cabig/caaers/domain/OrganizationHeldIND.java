package gov.nih.nci.cabig.caaers.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue("ORG")
public class OrganizationHeldIND extends INDHolder {
    private Organization organization;

    @ManyToOne(optional = false, fetch=FetchType.LAZY)
    @JoinColumn(name = "org_id", nullable = false)
    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    @Override
    @Transient
    public String getName() {
        if (organization != null) return organization.getName();
        return null;
    }
}
