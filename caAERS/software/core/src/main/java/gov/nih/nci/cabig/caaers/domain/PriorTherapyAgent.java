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
 * This class represents the PriorTherapyAgent domain object associated with the Adverse event
 * report.
 *
 * @author Rhett Sutphin
 */
@Entity
@Table(name = "prior_therapy_agents")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = {@Parameter(name = "sequence", value = "seq_prior_therapy_agents_id")})
public class PriorTherapyAgent extends AbstractMutableDomainObject {
    
    /** The sae report prior therapy. */
    private SAEReportPriorTherapy saeReportPriorTherapy;

    /** The chemo agent. */
    private ChemoAgent chemoAgent;

    // //// LOGIC

    /**
     * Gets the name.
     *
     * @return the name
     */
    @Transient
    public String getName() {
        if (getChemoAgent() != null) {
            return getChemoAgent().getName();
        } else {
            return null;
        }
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

    // This is annotated this way so that the IndexColumn in the parent
    // will work with the bidirectional mapping
    /**
     * Gets the sae report prior therapy.
     *
     * @return the sae report prior therapy
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ae_prior_therapy_id", insertable = false, updatable = false, nullable = false)
    public SAEReportPriorTherapy getSaeReportPriorTherapy() {
        return saeReportPriorTherapy;
    }

    /**
     * Sets the sae report prior therapy.
     *
     * @param saeReportPriorTherapy the new sae report prior therapy
     */
    public void setSaeReportPriorTherapy(SAEReportPriorTherapy saeReportPriorTherapy) {
        this.saeReportPriorTherapy = saeReportPriorTherapy;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((saeReportPriorTherapy == null) ? 0 : saeReportPriorTherapy.hashCode());
        result = prime * result + ((chemoAgent == null) ? 0 : chemoAgent.hashCode());
        return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        final PriorTherapyAgent other = (PriorTherapyAgent) obj;
        if (saeReportPriorTherapy == null) {
            if (other.saeReportPriorTherapy != null) return false;
        } else if (!saeReportPriorTherapy.equals(other.saeReportPriorTherapy)) return false;
        if (chemoAgent == null) {
            if (other.chemoAgent != null) return false;
        } else if (!chemoAgent.equals(other.chemoAgent)) return false;
        return true;
    }

    // /OBJECT Methods

    /**
     * Creates the sae report prior therapy agent.
     *
     * @param studyParticipantPriorTherapyAgent the study participant prior therapy agent
     * @return the prior therapy agent
     */
    public static PriorTherapyAgent createSaeReportPriorTherapyAgent(StudyParticipantPriorTherapyAgent studyParticipantPriorTherapyAgent) {


        if (studyParticipantPriorTherapyAgent != null) {
            PriorTherapyAgent priorTherapyAgent = copy(studyParticipantPriorTherapyAgent);

            return priorTherapyAgent;
        }
        return null;

    }

    /**
     * Copy.
     *
     * @param object the object
     * @return the prior therapy agent
     */
    private static PriorTherapyAgent copy(Object object) {
        PriorTherapyAgent priorTherapyAgent = new PriorTherapyAgent();
        BeanUtils.copyProperties(object, priorTherapyAgent, new String[]{"id", "gridId",
                "version", "saeReportPriorTherapy"});
        return priorTherapyAgent;
    }

    /**
     * Copy.
     *
     * @return the prior therapy agent
     */
    public PriorTherapyAgent copy() {
        return copy(this);

    }
}
