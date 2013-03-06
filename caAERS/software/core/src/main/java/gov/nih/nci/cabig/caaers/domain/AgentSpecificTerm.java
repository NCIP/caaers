/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;
import gov.nih.nci.cabig.ctms.domain.DomainObject;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;

 
/**
 * The Class AgentSpecificTerm.
 *
 * @param <T> the generic type
 * @author Ion C. Olaru
 * 
 * This class is to support Agent Specific AE List
 * Each agent has a list of expected AE's which shoudl be added to the study once the study has this agent on it.
 */

@Entity
@Table(name = "agent_terms")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = {@Parameter(name = "sequence", value = "seq_agent_terms_id")})
@DiscriminatorColumn(name = "term_type", discriminatorType = DiscriminatorType.STRING)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class AgentSpecificTerm<T extends DomainObject> extends AbstractMutableDomainObject {
    
    /** The term. */
    private T term;
    
    /** The agent. */
    private Agent agent;
    
    /** The deleted. */
    private boolean deleted;
    
    /** The other toxicity. */
    private String otherToxicity;

    /**
     * Gets the agent.
     *
     * @return the agent
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agent_id", nullable = false)
    public Agent getAgent() {
        return agent;
    }

    /**
     * Sets the agent.
     *
     * @param agent the new agent
     */
    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    /**
     * Gets the term.
     *
     * @return the term
     */
    @Transient
    public T getTerm() {
        return term;
    }

    /**
     * Sets the term.
     *
     * @param term the new term
     */
    public void setTerm(T term) {
        this.term = term;
    }

    /**
     * Checks if is other required.
     *
     * @return true, if is other required
     */
    @Transient
    public abstract boolean isOtherRequired();

    /**
     * Checks if is med dra.
     *
     * @return true, if is med dra
     */
    @Transient
    public abstract boolean isMedDRA();

    /**
     * Gets the full name.
     *
     * @return the full name
     */
    @Transient
    public abstract String getFullName();

    /**
     * Copy.
     *
     * @return the agent specific term
     */
    public AgentSpecificTerm copy() {
        AgentSpecificTerm term = (AgentSpecificTerm) BeanUtils.instantiateClass(getClass());
        BeanUtils.copyProperties(this, term, new String[]{"id", "gridId", "version", "agent"});
        return term;
    }

    /**
     * Gets the deleted.
     *
     * @return the deleted
     */
    @Transient
    public boolean getDeleted() {
        return deleted;
    }

    /**
     * Sets the deleted.
     *
     * @param deleted the new deleted
     */
    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    /**
     * Gets the other toxicity.
     *
     * @return the other toxicity
     */
    public String getOtherToxicity() {
        return otherToxicity;
    }

    /**
     * Sets the other toxicity.
     *
     * @param otherToxicity the new other toxicity
     */
    public void setOtherToxicity(String otherToxicity) {
        this.otherToxicity = otherToxicity;
    }
}
