package gov.nih.nci.cabig.caaers.domain;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject; 

import javax.persistence.Column;
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
}
