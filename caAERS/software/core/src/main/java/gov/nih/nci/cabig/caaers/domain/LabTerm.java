package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.ctms.domain.AbstractImmutableDomainObject;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

 
/**
 * This class represents the LabTerm domain object associated with the Adverse event report.
 * 
 * @author Krikor Krumlian
 */
@Entity
@Table(name = "lab_terms")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = { @Parameter(name = "sequence", value = "lab_terms_id_seq") })
public class LabTerm extends AbstractMutableRetireableDomainObject {
    
    /** The term. */
    private String term;

    /** The category. */
    private LabCategory category;

    // //// BEAN PROPERTIES

    /**
     * Gets the term.
     *
     * @return the term
     */
    public String getTerm() {
        return term;
    }

    /**
     * Sets the term.
     *
     * @param term the new term
     */
    public void setTerm(String term) {
        this.term = term;
    }

    /**
     * Gets the category.
     *
     * @return the category
     */
    @ManyToOne
    public LabCategory getCategory() {
        return category;
    }

    /**
     * Sets the category.
     *
     * @param category the new category
     */
    public void setCategory(LabCategory category) {
        this.category = category;
    }

}
