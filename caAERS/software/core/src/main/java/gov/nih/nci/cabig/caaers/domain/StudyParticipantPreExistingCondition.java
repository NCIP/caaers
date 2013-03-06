/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.BeanUtils;

 
/**
 * This class represents the StudyParticipantPreExistingCondition domain object associated with the StudyParticipantAssignment.
 *
 * @author Sameer Sawant
 */
@Entity
@Table(name = "spa_pre_existing_conds")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = {@Parameter(name = "sequence", value = "seq_spa_pre_existing_conds_id")})
public class StudyParticipantPreExistingCondition extends AbstractMutableDomainObject {
    
    /** The pre existing condition. */
    private PreExistingCondition preExistingCondition;

    /** The assignment. */
    private StudyParticipantAssignment assignment;

    /** The other. */
    private String other;

    // //// LOGIC

    /**
     * Gets the name.
     *
     * @return the name
     */
    @Transient
    public String getName() {
        if (getPreExistingCondition() != null) {
            return getPreExistingCondition().getText();
        } else if (getOther() != null) {
            return "Other: " + getOther();
        } else {
            return null;
        }
    }

    // //// BOUND PROPERTIES

    /**
     * Gets the pre existing condition.
     *
     * @return the pre existing condition
     */
    @ManyToOne
    public PreExistingCondition getPreExistingCondition() {
        return preExistingCondition;
    }

    /**
     * Sets the pre existing condition.
     *
     * @param preExistingCondition the new pre existing condition
     */
    public void setPreExistingCondition(PreExistingCondition preExistingCondition) {
        this.preExistingCondition = preExistingCondition;
    }

    /**
     * Gets the other.
     *
     * @return the other
     */
    public String getOther() {
        return other;
    }

    /**
     * Sets the other.
     *
     * @param other the new other
     */
    public void setOther(String other) {
        this.other = other;
    }

    /**
     * Gets the assignment.
     *
     * @return the assignment
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @Cascade(value = {CascadeType.LOCK})
    public StudyParticipantAssignment getAssignment() {
        return assignment;
    }

    /**
     * Sets the assignment.
     *
     * @param assignment the new assignment
     */
    public void setAssignment(StudyParticipantAssignment assignment) {
        this.assignment = assignment;
    }


    ///OBJECT METHODS
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((other == null) ? 0 : other.hashCode());
        result = prime
                * result
                + ((preExistingCondition == null) ? 0 : preExistingCondition
                .hashCode());
        return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final StudyParticipantPreExistingCondition other = (StudyParticipantPreExistingCondition) obj;
        if (this.other == null) {
            if (other.other != null)
                return false;
        } else if (!this.other.equals(other.other))
            return false;
        if (preExistingCondition == null) {
            if (other.preExistingCondition != null)
                return false;
        } else if (!preExistingCondition.equals(other.preExistingCondition))
            return false;
        return true;
    }


    /**
     * Creates the assignment pre existing condition.
     *
     * @param saeReportPreExistingCondition the sae report pre existing condition
     * @return the study participant pre existing condition
     */
    public static StudyParticipantPreExistingCondition createAssignmentPreExistingCondition(SAEReportPreExistingCondition saeReportPreExistingCondition) {
        if (saeReportPreExistingCondition != null) {
            StudyParticipantPreExistingCondition studyParticipantPreExistingCondition = new StudyParticipantPreExistingCondition();
            BeanUtils.copyProperties(saeReportPreExistingCondition, studyParticipantPreExistingCondition, new String[]{"id", "gridId", "version", "assignment"});
            return studyParticipantPreExistingCondition;
        }
        return null;

    }
}
