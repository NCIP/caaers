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
    
    /** The expectedness frequency. */
    private Double expectednessFrequency;
    
    /** The grade1 frequency. */
    private Double grade1Frequency;
    
    /** The grade2 frequency. */
    private Double grade2Frequency;
    
    /** The grade3 frequency. */
    private Double grade3Frequency;
    
    /** The grade4 frequency. */
    private Double grade4Frequency;
    
    /** The grade5 frequency. */
    private Double grade5Frequency;
    
    /** The expected. */
    private boolean expected = false;

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

	public Double getExpectednessFrequency() {
		return expectednessFrequency;
	}

	public void setExpectednessFrequency(Double expectednessFrequency) {
		this.expectednessFrequency = expectednessFrequency;
	}

	public Double getGrade1Frequency() {
		return grade1Frequency;
	}

	public void setGrade1Frequency(Double grade1Frequency) {
		this.grade1Frequency = grade1Frequency;
	}

	public Double getGrade2Frequency() {
		return grade2Frequency;
	}

	public void setGrade2Frequency(Double grade2Frequency) {
		this.grade2Frequency = grade2Frequency;
	}

	public Double getGrade3Frequency() {
		return grade3Frequency;
	}

	public void setGrade3Frequency(Double grade3Frequency) {
		this.grade3Frequency = grade3Frequency;
	}

	public Double getGrade4Frequency() {
		return grade4Frequency;
	}

	public void setGrade4Frequency(Double grade4Frequency) {
		this.grade4Frequency = grade4Frequency;
	}

	public Double getGrade5Frequency() {
		return grade5Frequency;
	}

	public void setGrade5Frequency(Double grade5Frequency) {
		this.grade5Frequency = grade5Frequency;
	}

	public boolean isExpected() {
		return expected;
	}

	public void setExpected(boolean expected) {
		this.expected = expected;
	}
}