package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.ctms.domain.AbstractImmutableDomainObject;

import javax.persistence.Entity;

/**
 * @author Krikor Krumlian
 */
@Entity
public class ChemoAgent extends AbstractImmutableDomainObject {
    private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
