package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.BeanUtils;

/**
 * This class represents the SturyParticipantPriorTherapyAgent domain object associated with the StudyParticipantAssignment
 *
 * @author Sameer Sawant
 */
@Entity
@Table(name = "spa_prior_therapy_agents")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = {@Parameter(name = "sequence", value = "seq_spa_prior_therapy_agent_id")})
public class StudyParticipantPriorTherapyAgent extends AbstractMutableDomainObject {
    private StudyParticipantPriorTherapy priorTherapy;

    private ChemoAgent chemoAgent;

    // //// LOGIC

    @Transient
    public String getName() {
        if (getChemoAgent() != null) {
            return getChemoAgent().getName();
        } else {
            return null;
        }
    }

    // //// BOUND PROPERTIES

    @ManyToOne
    @JoinColumn(name = "chemo_agent_id")
    public ChemoAgent getChemoAgent() {
        return chemoAgent;
    }

    public void setChemoAgent(ChemoAgent agent) {
        this.chemoAgent = agent;
    }

    // This is annotated this way so that the IndexColumn in the parent
    // will work with the bidirectional mapping
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "spa_prior_therapy_id", insertable = false, updatable = false, nullable = false)
    public StudyParticipantPriorTherapy getPriorTherapy() {
        return priorTherapy;
    }

    public void setPriorTherapy(StudyParticipantPriorTherapy priorTherapy) {
        this.priorTherapy = priorTherapy;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((priorTherapy == null) ? 0 : priorTherapy.hashCode());
        result = prime * result + ((chemoAgent == null) ? 0 : chemoAgent.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        final StudyParticipantPriorTherapyAgent other = (StudyParticipantPriorTherapyAgent) obj;
        if (priorTherapy == null) {
            if (other.priorTherapy != null) return false;
        } else if (!priorTherapy.equals(other.priorTherapy)) return false;
        if (chemoAgent == null) {
            if (other.chemoAgent != null) return false;
        } else if (!chemoAgent.equals(other.chemoAgent)) return false;
        return true;
    }


    public static StudyParticipantPriorTherapyAgent createAssignmentPriorTherapyAgent(PriorTherapyAgent priorTherapyAgent) {
        if (priorTherapyAgent != null) {
            StudyParticipantPriorTherapyAgent studyParticipantPriorTherapyAgent = new StudyParticipantPriorTherapyAgent();
            BeanUtils.copyProperties(priorTherapyAgent, studyParticipantPriorTherapyAgent, new String[]{"id", "gridId", "version"
                    , "priorTherapy"});

            return studyParticipantPriorTherapyAgent;
        }
        return null;
    }
}
