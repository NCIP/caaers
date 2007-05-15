package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.ctms.domain.AbstractImmutableDomainObject;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Column;
import javax.persistence.Transient;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import java.util.List;
import java.util.Arrays;

/**
 * @author Rhett Sutphin
 */
@Entity
public class CtcTerm extends AbstractImmutableDomainObject {
    private String term;
    private String select;
    private String ctepTerm;
    private Integer ctepCode;
    private CtcCategory category;
    private boolean otherRequired;
    private List<CtcGrade> contextualGrades;

    ////// LOGIC

    @Transient
    public String getFullName() {
        if (select == null) {
            return getTerm();
        } else {
            return getTerm() + " - " + getSelect();
        }
    }

    @Transient
    public List<? extends CodedGrade> getGrades() {
        if (getContextualGrades() == null || getContextualGrades().size() == 0) {
            return Arrays.asList(Grade.values());
        } else {
            return getContextualGrades();
        }
    }

    ////// BEAN PROPERTIES

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

    public Integer getCtepCode() {
        return ctepCode;
    }

    public void setCtepCode(Integer ctepCode) {
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
    @OrderBy("grade")
    public List<CtcGrade> getContextualGrades() {
        return contextualGrades;
    }

    public void setContextualGrades(List<CtcGrade> contextualGrades) {
        this.contextualGrades = contextualGrades;
    }
}
