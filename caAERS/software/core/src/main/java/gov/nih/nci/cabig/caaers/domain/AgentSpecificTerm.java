package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;
import gov.nih.nci.cabig.ctms.domain.DomainObject;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;

/**
 * @author Ion C. Olaru
 *
 * This class is to support Agent Specific AE List
 * Each agent has a list of expected AE's which shoudl be added to the study once the study has this agent on it.
 * 
 */

@Entity
@Table(name = "agent_terms")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = {@Parameter(name = "sequence", value = "seq_agent_term_id")})
@DiscriminatorColumn(name = "term_type", discriminatorType = DiscriminatorType.STRING)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class AgentSpecificTerm<T extends DomainObject> extends AbstractMutableDomainObject {
    private T term;
    private Agent agent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agent_id", nullable = false)
    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    @Transient
    public T getTerm() {
        return term;
    }

    public void setTerm(T term) {
        this.term = term;
    }

    @Transient
    public abstract boolean isOtherRequired();

    @Transient
    public abstract boolean isMedDRA();

    @Transient
    public abstract String getFullName();

    public AgentSpecificTerm copy() {
        AgentSpecificTerm term = (AgentSpecificTerm) BeanUtils.instantiateClass(getClass());
        BeanUtils.copyProperties(this, term, new String[]{"id", "gridId", "version", "agent"});
        return term;
    }
}