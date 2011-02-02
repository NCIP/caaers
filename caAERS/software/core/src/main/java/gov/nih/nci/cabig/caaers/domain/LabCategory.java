package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.ctms.domain.AbstractImmutableDomainObject;
import org.hibernate.annotations.Fetch;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

 
/**
 * This class represents the LabCategory domain object associated with the Adverse event report.
 * 
 * @author Krikor Krumlian
 */
@Entity
public class LabCategory extends AbstractImmutableDomainObject {
    
    /** The name. */
    private String name;

    /** The terms. */
    private List<LabTerm> terms;

    /** The lab version. */
    private LabVersion labVersion;

    // //// BEAN PROPERTIES

    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name.
     *
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the lab version.
     *
     * @return the lab version
     */
    @ManyToOne
    @JoinColumn(name = "version_id")
    public LabVersion getLabVersion() {
        return labVersion;
    }

    /**
     * Sets the lab version.
     *
     * @param labVersion the new lab version
     */
    public void setLabVersion(LabVersion labVersion) {
        this.labVersion = labVersion;
    }

    /**
     * Gets the terms.
     *
     * @return the terms
     */
    @OneToMany(mappedBy = "category")
    @OrderBy
    @Fetch(value = org.hibernate.annotations.FetchMode.SUBSELECT)
    // by ID for consistency
    public List<LabTerm> getTerms() {
        return terms;
    }

    /**
     * Sets the terms.
     *
     * @param terms the new terms
     */
    public void setTerms(List<LabTerm> terms) {
        this.terms = terms;
    }
}
