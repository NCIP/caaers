package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.ctms.domain.AbstractImmutableDomainObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

/**
 * This class represents the CtcGrade domain object associated with the Adverse event report.
 * 
 * @author Rhett Sutphin
 */
@Entity
public class CtcGrade extends AbstractImmutableDomainObject implements CodedGrade {
    private Grade grade;

    private CtcTerm term;

    private String text;

    // //// LOGIC

    @Transient
    public Integer getCode() {
        return getGrade().getCode();
    }

    @Transient
    public String getDisplayName() {
        return getText();
    }

    // //// BEAN PROPERTIES

    @Type(type = "grade")
    @Column(name = "grade_code")
    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    @ManyToOne
    public CtcTerm getTerm() {
        return term;
    }

    public void setTerm(CtcTerm term) {
        this.term = term;
    }

    @Column(name = "grade_text")
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    
    @Transient
    public String getName(){
    	return grade.getName();
    }
}
