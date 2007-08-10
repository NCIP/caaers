package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.ctms.domain.AbstractImmutableDomainObject;

import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * @author Krikor Krumlian
 */

// No Direct conncection to Meddra tables implemented yet - Will need to be 
// If or when we start supporting multiple versions of MedDRA

@Entity
@Table(name = "meddra_versions")
public class MeddraVersion extends AbstractImmutableDomainObject {
    private String name;

    ////// BEAN PROPERTIES

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
