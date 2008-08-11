package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * This class represents the StudyParticipantConcomitantMedication domain object associated with the StudyParticipantAssignment
 * report.
 * 
 * @author Sameer Sawant
 */
@Entity
@Table(name = "spa_concomitant_medications")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = { @Parameter(name = "sequence", value = "seq_spa_concomitant_medicat_id") })
public class StudyParticipantConcomitantMedication extends AbstractMutableDomainObject {
    private String agentName;
    
    private StudyParticipantAssignment assignment;

    // //// LOGIC

    @Transient
    public String getName() {
        return agentName;
    }

    // //// BOUND PROPERTIES

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }
    
    @ManyToOne(fetch = FetchType.LAZY)
    @Cascade(value={CascadeType.LOCK})
    public StudyParticipantAssignment getAssignment() {
        return assignment;
    }

    public void setAssignment(StudyParticipantAssignment assignment) {
        this.assignment = assignment;
    }
}
