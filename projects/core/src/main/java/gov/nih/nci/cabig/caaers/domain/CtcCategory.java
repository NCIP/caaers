package gov.nih.nci.cabig.caaers.domain;

import javax.persistence.Entity;

/**
 * @author Rhett Sutphin
 */
@Entity
public class CtcCategory extends AbstractImmutableDomainObject {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
