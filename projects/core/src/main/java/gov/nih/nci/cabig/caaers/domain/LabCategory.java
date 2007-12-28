package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.ctms.domain.AbstractImmutableDomainObject;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.OrderBy;
import java.util.List;

/**
 * @author Krikor Krumlian
 */
@Entity
public class LabCategory extends AbstractImmutableDomainObject {
    private String name;
    private List<LabTerm> terms;
    private LabVersion labVersion;

    ////// BEAN PROPERTIES

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne
    @JoinColumn(name = "version_id")
    public LabVersion getLabVersion() {
        return labVersion;
    }

    public void setLabVersion(LabVersion labVersion) {
        this.labVersion = labVersion;
    }

    @OneToMany(mappedBy = "category")
    @OrderBy // by ID for consistency
    public List<LabTerm> getTerms() {
        return terms;
    }

    public void setTerms(List<LabTerm> terms) {
        this.terms = terms;
    }
}
