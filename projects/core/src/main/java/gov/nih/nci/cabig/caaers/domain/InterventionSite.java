package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.ctms.domain.AbstractImmutableDomainObject;

import javax.persistence.Entity;

/**
 * This class represents the InterventionSite domain object associated with the Adverse event
 * report.
 * 
 * @author Krikor Krumlian
 */
@Entity
public class InterventionSite extends AbstractImmutableDomainObject {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
