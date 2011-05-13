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
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CtcCategory extends AbstractImmutableDomainObject {

    /** The name. */
    private String name;
    
    /** The ctc. */
    private Ctc ctc;
    
    /** The terms. */
    private List<CtcTerm> terms;

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
     * Gets the ctc.
     *
     * @return the ctc
     */
    @ManyToOne
    @JoinColumn(name = "version_id")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    public Ctc getCtc() {
        return ctc;
    }

    /**
     * Sets the ctc.
     *
     * @param ctc the new ctc
     */
    public void setCtc(Ctc ctc) {
        this.ctc = ctc;
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
    public List<CtcTerm> getTerms() {
        return terms;
    }

    /**
     * Sets the terms.
     *
     * @param terms the new terms
     */
    public void setTerms(List<CtcTerm> terms) {
        this.terms = terms;
    }
}
