package gov.nih.nci.cabig.caaers.domain;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject; 

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author Rhett Sutphin
 */
@Entity
@Table(name = "prior_therapy_agents")
@GenericGenerator(name="id-generator", strategy = "native",
    parameters = {
        @Parameter(name="sequence", value="seq_prior_therapy_agents_id")
    }
)
public class PriorTherapyAgent extends AbstractMutableDomainObject{
	private AdverseEventPriorTherapy adverseEventPriorTherapy;
    private ChemoAgent chemoAgent;

    ////// LOGIC

    @Transient
    public String getName() {
        if (getChemoAgent() != null) {
            return getChemoAgent().getName();
        } else {
            return null;
        }
    }

    ////// BOUND PROPERTIES

    @ManyToOne
    @JoinColumn(name="chemo_agent_id")
    public ChemoAgent getChemoAgent() {
        return chemoAgent;
    }

    public void setChemoAgent(ChemoAgent agent) {
        this.chemoAgent = agent;
    }
    
    // This is annotated this way so that the IndexColumn in the parent
    // will work with the bidirectional mapping
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ae_prior_therapy_id", insertable=false, updatable=false, nullable=false)
    public AdverseEventPriorTherapy getAdverseEventPriorTherapy() {
        return adverseEventPriorTherapy;
    }

    public void setAdverseEventPriorTherapy(AdverseEventPriorTherapy adverseEventPriorTherapy) {
        this.adverseEventPriorTherapy = adverseEventPriorTherapy;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((adverseEventPriorTherapy == null) ? 0
						: adverseEventPriorTherapy.hashCode());
		result = prime * result
				+ ((chemoAgent == null) ? 0 : chemoAgent.hashCode());
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
		final PriorTherapyAgent other = (PriorTherapyAgent) obj;
		if (adverseEventPriorTherapy == null) {
			if (other.adverseEventPriorTherapy != null)
				return false;
		} else if (!adverseEventPriorTherapy
				.equals(other.adverseEventPriorTherapy))
			return false;
		if (chemoAgent == null) {
			if (other.chemoAgent != null)
				return false;
		} else if (!chemoAgent.equals(other.chemoAgent))
			return false;
		return true;
	}
    
    ///OBJECT Methods
    
}
