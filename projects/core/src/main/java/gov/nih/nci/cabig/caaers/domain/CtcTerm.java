package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.ctms.domain.AbstractImmutableDomainObject;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Column;
import javax.persistence.Transient;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.util.List;
import java.util.Arrays;
import java.util.Collections;

/**
 * This class represents the CtcTerm domain object associated with the Adverse event report.
 * 
 * @author Rhett Sutphin
 */
@Entity
public class CtcTerm extends AbstractImmutableDomainObject {
    private String term;

    private String select;

    private String ctepTerm;

    private String ctepCode;

    private CtcCategory category;

    private boolean otherRequired;

    private List<CtcGrade> contextualGrades;

    // //// LOGIC

    @Transient
    public String getFullName() {
    	 if (select == null) {
             return getTerm();
         } else {
             return getTerm() + " - " + getSelect();
         }
        
    }

    @Transient
    public String getFullNameWithMedDRA() {
        if (select == null) {
            return getTerm() + " - " + getCtepCode();
        } else {
            return getTerm() + " - " + getSelect() + " - " + getCtepCode();
        }
    }

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

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    @Column(name = "select_ae")
    public String getSelect() {
        return select;
    }

    public void setSelect(String select) {
        this.select = select;
    }

    public String getCtepTerm() {
        return ctepTerm;
    }

    public void setCtepTerm(String ctepTerm) {
        this.ctepTerm = ctepTerm;
    }

    public String getCtepCode() {
        return ctepCode;
    }

    public void setCtepCode(String ctepCode) {
        this.ctepCode = ctepCode;
    }

    @ManyToOne
    public CtcCategory getCategory() {
        return category;
    }

    public void setCategory(CtcCategory category) {
        this.category = category;
    }

    public boolean isOtherRequired() {
        return otherRequired;
    }

    public void setOtherRequired(boolean otherRequired) {
        this.otherRequired = otherRequired;
    }

    @OneToMany(mappedBy = "term")
    @Cascade(value={CascadeType.ALL})
    @OrderBy("grade")
    public List<CtcGrade> getContextualGrades() {
        return contextualGrades;
    }

    public void setContextualGrades(List<CtcGrade> contextualGrades) {
        this.contextualGrades = contextualGrades;
    }
}
