/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

 
/**
 * This class represents the CtcGrade domain object associated with the Adverse event report.
 * 
 * @author Rhett Sutphin
 */
@Entity
@GenericGenerator(name = "id-generator", strategy = "native", parameters = {@Parameter(name = "sequence", value = "seq_ctc_grades_id")})
public class CtcGrade extends AbstractMutableDomainObject implements CodedGrade {

    /** The grade. */
    private Grade grade;
    
    /** The term. */
    private CtcTerm term;
    
    /** The text. */
    private String text;

    // //// LOGIC

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.CodedGrade#getCode()
     */
    @Transient
    public Integer getCode() {
        return getGrade().getCode();
    }

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.CodedGrade#getDisplayName()
     */
    @Transient
    public String getDisplayName() {
        return getText();
    }

    // //// BEAN PROPERTIES

    /**
     * Gets the grade.
     *
     * @return the grade
     */
    @Type(type = "grade")
    @Column(name = "grade_code")
    public Grade getGrade() {
        return grade;
    }

    /**
     * Sets the grade.
     *
     * @param grade the new grade
     */
    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    /**
     * Gets the term.
     *
     * @return the term
     */
    @ManyToOne
    @JoinColumn(nullable=false)
    public CtcTerm getTerm() {
        return term;
    }

    /**
     * Sets the term.
     *
     * @param term the new term
     */
    public void setTerm(CtcTerm term) {
        this.term = term;
    }

    /**
     * Gets the text.
     *
     * @return the text
     */
    @Column(name = "grade_text")
    public String getText() {
        return text;
    }

    /**
     * Sets the text.
     *
     * @param text the new text
     */
    public void setText(String text) {
        this.text = text;
    }
    
    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.CodedGrade#getName()
     */
    @Transient
    public String getName(){
    	return grade.getName();
    }
}
