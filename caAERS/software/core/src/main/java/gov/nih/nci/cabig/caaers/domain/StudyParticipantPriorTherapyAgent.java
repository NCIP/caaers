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
 * This class represents the SturyParticipantPriorTherapyAgent domain object associated with the StudyParticipantAssignment.
 *
 * @author Sameer Sawant
 */
@Entity
@Table(name = "spa_prior_therapy_agents")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = {@Parameter(name = "sequence", value = "seq_spa_prior_therapy_agent_id")})
public class StudyParticipantPriorTherapyAgent extends AbstractMutableDomainObject {
    
    /** The prior therapy. */
    private StudyParticipantPriorTherapy priorTherapy;

    /** The chemo agent. */
    private ChemoAgent chemoAgent;
    
    private Agent agent;

    // //// LOGIC

    /**
     * Gets the name.
     *
     * @return the name
     */
    @Transient
    public String getName() {
        if(getAgent() != null) return agent.getDisplayName();
        if(getChemoAgent() != null) return getChemoAgent().getName();
        return null;
    }

    // //// BOUND PROPERTIES

    /**
     * Gets the chemo agent.
     *
     * @return the chemo agent
     */
    @ManyToOne
    @JoinColumn(name = "chemo_agent_id")
    public ChemoAgent getChemoAgent() {
        return chemoAgent;
    }

    /**
     * Sets the chemo agent.
     *
     * @param agent the new chemo agent
     */
    public void setChemoAgent(ChemoAgent agent) {
        this.chemoAgent = agent;
    }

    @ManyToOne
    @JoinColumn(name= "agent_id")
    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    // This is annotated this way so that the IndexColumn in the parent
    // will work with the bidirectional mapping
    /**
     * Gets the prior therapy.
     *
     * @return the prior therapy
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "spa_prior_therapy_id", insertable = false, updatable = false, nullable = false)
    public StudyParticipantPriorTherapy getPriorTherapy() {
        return priorTherapy;
    }

    /**
     * Sets the prior therapy.
     *
     * @param priorTherapy the new prior therapy
     */
    public void setPriorTherapy(StudyParticipantPriorTherapy priorTherapy) {
        this.priorTherapy = priorTherapy;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getPriorTherapy() == null) ? 0 : getPriorTherapy().hashCode());
        result = prime * result + ((getChemoAgent() == null) ? 0 : getChemoAgent().hashCode());
        result = prime * result + ((getAgent() == null) ? 0 : getAgent().hashCode());
        return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        
        final StudyParticipantPriorTherapyAgent other = (StudyParticipantPriorTherapyAgent) obj;
        if (getPriorTherapy() == null) {
            if (other.getPriorTherapy() != null) return false;
        } else if (!getPriorTherapy().equals(other.getPriorTherapy())) return false;
        if (getChemoAgent() == null) {
            if (other.getChemoAgent() != null) return false;
        } else if (!getChemoAgent().equals(other.getChemoAgent())) return false;

        if (getAgent() == null) {
            if (other.getAgent() != null) return false;
        } else if (!getAgent().equals(other.getAgent())) return false;

        return true;
    }


    /**
     * Creates the assignment prior therapy agent.
     *
     * @param priorTherapyAgent the prior therapy agent
     * @return the study participant prior therapy agent
     */
    public static StudyParticipantPriorTherapyAgent createAssignmentPriorTherapyAgent(PriorTherapyAgent priorTherapyAgent) {
        if (priorTherapyAgent != null) {
            StudyParticipantPriorTherapyAgent studyParticipantPriorTherapyAgent = new StudyParticipantPriorTherapyAgent();
            BeanUtils.copyProperties(priorTherapyAgent, studyParticipantPriorTherapyAgent, new String[]{"id", "gridId", "version", "priorTherapy"});
            return studyParticipantPriorTherapyAgent;
        }
        return null;
    }
}
