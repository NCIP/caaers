package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

 
/**
 * This class represents the CtcTerm domain object associated with the Adverse event report.
 * 
 * @author Rhett Sutphin
 */
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE )
@GenericGenerator(name = "id-generator", strategy = "native", parameters = {@Parameter(name = "sequence", value = "seq_ctc_terms_id")})
public class CtcTerm extends AbstractMutableDomainObject {

    /** The term. */
    private String term;
    
    /** The select. */
    private String select;
    
    /** The ctep term. */
    private String ctepTerm;
    
    /** The ctep code. */
    private String ctepCode;
    
    /** The category. */
    private CtcCategory category;
    
    /** The other required. */
    private boolean otherRequired;
    
    /** The contextual grades. */
    private List<CtcGrade> contextualGrades = new ArrayList<CtcGrade>();
    
    /** The definition. */
    private String definition;

    // //// LOGIC

    /**
     * Gets the full name.
     *
     * @return the full name
     */
    @Transient
    public String getFullName() {
    	 if (select == null) {
             return getTerm();
         } else {
             return getTerm() + " - " + getSelect();
         }
        
    }

    /**
     * Gets the full name with med dra.
     *
     * @return the full name with med dra
     */
    @Transient
    public String getFullNameWithMedDRA() {
        if (select == null) {
            return getTerm() + " - " + getCtepCode();
        } else {
            return getTerm() + " - " + getSelect() + " - " + getCtepCode();
        }
    }

    /**
     * Gets the grades.
     *
     * @return the grades
     */
    @Transient
    public List<CodedGrade> getGrades() {
        if (getContextualGrades() == null || getContextualGrades().size() == 0) {
            return Arrays.<CodedGrade> asList(Grade.values());
        } else {
            // this rigamarole is just to change the generic type without creating a new list
            return Collections.<CodedGrade> unmodifiableList(getContextualGrades());
        }
    }

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
     * Gets the select.
     *
     * @return the select
     */
    @Column(name = "select_ae")
    public String getSelect() {
        return select;
    }

    /**
     * Sets the select.
     *
     * @param select the new select
     */
    public void setSelect(String select) {
        this.select = select;
    }

    /**
     * Gets the ctep term.
     *
     * @return the ctep term
     */
    public String getCtepTerm() {
        return ctepTerm;
    }

    /**
     * Sets the ctep term.
     *
     * @param ctepTerm the new ctep term
     */
    public void setCtepTerm(String ctepTerm) {
        this.ctepTerm = ctepTerm;
    }

    /**
     * Gets the ctep code.
     *
     * @return the ctep code
     */
    public String getCtepCode() {
        return ctepCode;
    }

    /**
     * Sets the ctep code.
     *
     * @param ctepCode the new ctep code
     */
    public void setCtepCode(String ctepCode) {
        this.ctepCode = ctepCode;
    }

    /**
     * Gets the category.
     *
     * @return the category
     */
    @ManyToOne
    @JoinColumn(nullable=false)
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    public CtcCategory getCategory() {
        return category;
    }

    /**
     * Sets the category.
     *
     * @param category the new category
     */
    public void setCategory(CtcCategory category) {
        this.category = category;
    }

    /**
     * Checks if is other required.
     *
     * @return true, if is other required
     */
    public boolean isOtherRequired() {
        return otherRequired;
    }

    /**
     * Sets the other required.
     *
     * @param otherRequired the new other required
     */
    public void setOtherRequired(boolean otherRequired) {
        this.otherRequired = otherRequired;
    }

    /**
     * Gets the contextual grades.
     *
     * @return the contextual grades
     */
    @OneToMany(mappedBy = "term", orphanRemoval= true)
    @Cascade(value={CascadeType.ALL})
    @OrderBy("grade")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @Fetch(value = org.hibernate.annotations.FetchMode.SUBSELECT)
    public List<CtcGrade> getContextualGrades() {
        return contextualGrades;
    }

    /**
     * Sets the contextual grades.
     *
     * @param contextualGrades the new contextual grades
     */
    public void setContextualGrades(List<CtcGrade> contextualGrades) {
        this.contextualGrades = contextualGrades;
    }

    /**
     * Gets the definition.
     *
     * @return the definition
     */
    @Column(name = "term_definition")
    public String getDefinition() {
        return definition;
    }

    /**
     * Sets the definition.
     *
     * @param definition the new definition
     */
    public void setDefinition(String definition) {
        this.definition = definition;
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CtcTerm [term=" + term + ", select=" + select + ", ctepTerm="
				+ ctepTerm + ", ctepCode=" + ctepCode + ", otherRequired="
				+ otherRequired + ", definition=" + definition + "]";
	}
    
	public void addCtcGrade(CtcGrade ctcGrade){
		ctcGrade.setTerm(this);
		getContextualGrades().add(ctcGrade);
	}
    
}
