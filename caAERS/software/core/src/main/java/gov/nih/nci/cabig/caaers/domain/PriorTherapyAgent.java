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
    private SAEReportPriorTherapy saeReportPriorTherapy;

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
    @JoinColumn(name = "ae_prior_therapy_id", insertable = false, updatable = false, nullable = false)
    public SAEReportPriorTherapy getSaeReportPriorTherapy() {
        return saeReportPriorTherapy;
    }

    public void setSaeReportPriorTherapy(SAEReportPriorTherapy saeReportPriorTherapy) {
        this.saeReportPriorTherapy = saeReportPriorTherapy;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((saeReportPriorTherapy == null) ? 0 : saeReportPriorTherapy.hashCode());
        result = prime * result + ((chemoAgent == null) ? 0 : chemoAgent.hashCode());
        return result;
    }

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

    public static PriorTherapyAgent createSaeReportPriorTherapyAgent(StudyParticipantPriorTherapyAgent studyParticipantPriorTherapyAgent) {


        if (studyParticipantPriorTherapyAgent != null) {
            PriorTherapyAgent priorTherapyAgent = copy(studyParticipantPriorTherapyAgent);

            return priorTherapyAgent;
        }
        return null;

    }

    private static PriorTherapyAgent copy(Object object) {
        PriorTherapyAgent priorTherapyAgent = new PriorTherapyAgent();
        BeanUtils.copyProperties(object, priorTherapyAgent, new String[]{"id", "gridId",
                "version", "saeReportPriorTherapy"});
        return priorTherapyAgent;
    }

    public PriorTherapyAgent copy() {
        return copy(this);

    }
}
