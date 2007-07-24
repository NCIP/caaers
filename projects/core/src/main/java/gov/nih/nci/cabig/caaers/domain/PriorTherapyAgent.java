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
@Table(name = "prior_theray_agents")
@GenericGenerator(name="id-generator", strategy = "native",
    parameters = {
        @Parameter(name="sequence", value="seq_prior_therapy_agents_id")
    }
)
public class PriorTherapyAgent extends AbstractMutableDomainObject{
	private AdverseEventPriorTherapy adverseEventPriorTherapy;
    private Agent agent;

    ////// LOGIC

    @Transient
    public String getName() {
        if (getAgent() != null) {
            return getAgent().getName();
        } else {
            return null;
        }
    }

    ////// BOUND PROPERTIES

    @ManyToOne
    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
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
