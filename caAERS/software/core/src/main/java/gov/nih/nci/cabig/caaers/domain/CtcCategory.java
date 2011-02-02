package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.ctms.domain.AbstractImmutableDomainObject;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

/**
 * This class represents the CtcCategory domain object associated with the Adverse event report.
 * 
 * @author Rhett Sutphin
 */
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class CtcCategory extends AbstractImmutableDomainObject {

    private String name;
    private Ctc ctc;
    private List<CtcTerm> terms;

    // //// BEAN PROPERTIES

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne
    @JoinColumn(name = "version_id")
    @Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
    public Ctc getCtc() {
        return ctc;
    }

    public void setCtc(Ctc ctc) {
        this.ctc = ctc;
    }

    @OneToMany(mappedBy = "category")
    @OrderBy
    @Fetch(value = org.hibernate.annotations.FetchMode.SUBSELECT)
    // by ID for consistency
    public List<CtcTerm> getTerms() {
        return terms;
    }

    public void setTerms(List<CtcTerm> terms) {
        this.terms = terms;
    }
}
