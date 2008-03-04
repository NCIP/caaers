package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.ctms.domain.AbstractImmutableDomainObject;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * This class represents the LabTerm domain object associated with the Adverse event report.
 * 
 * @author Krikor Krumlian
 */
@Entity
public class LabTerm extends AbstractImmutableDomainObject {
    private String term;

    private LabCategory category;

    // //// BEAN PROPERTIES

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    @ManyToOne
    public LabCategory getCategory() {
        return category;
    }

    public void setCategory(LabCategory category) {
        this.category = category;
    }

}
