package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;

/**
 * This class represents the StudyParticipantPreExistingCondition domain object associated with the StudyParticipantAssignment
 *
 * @author Sameer Sawant
 */
@Entity
@Table(name = "spa_pre_existing_conds")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = {@Parameter(name = "sequence", value = "seq_spa_pre_existing_conds_id")})
public class StudyParticipantPreExistingCondition extends AbstractMutableDomainObject {
    private PreExistingCondition preExistingCondition;

    private StudyParticipantAssignment assignment;

    private String other;

    // //// LOGIC

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

    @ManyToOne
    public PreExistingCondition getPreExistingCondition() {
        return preExistingCondition;
    }

    public void setPreExistingCondition(PreExistingCondition preExistingCondition) {
        this.preExistingCondition = preExistingCondition;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @Cascade(value = {CascadeType.LOCK})
    public StudyParticipantAssignment getAssignment() {
        return assignment;
    }

    public void setAssignment(StudyParticipantAssignment assignment) {
        this.assignment = assignment;
    }


    ///OBJECT METHODS
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


    public static StudyParticipantPreExistingCondition createAssignmentPreExistingCondition(SAEReportPreExistingCondition saeReportPreExistingCondition) {
        if (saeReportPreExistingCondition != null) {
            StudyParticipantPreExistingCondition studyParticipantPreExistingCondition = new StudyParticipantPreExistingCondition();
            BeanUtils.copyProperties(saeReportPreExistingCondition, studyParticipantPreExistingCondition, new String[]{"id", "gridId", "version", "assignment"});
            return studyParticipantPreExistingCondition;
        }
        return null;

    }
}
