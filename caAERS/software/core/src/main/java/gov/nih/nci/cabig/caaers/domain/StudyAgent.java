/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.ctms.collections.LazyListHelper;
import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

import java.util.List;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

 
/**
 * This class represents the StudyAgent domain object associated with the Adverse event report.
 * 
 * @author Krikor Krumlian
 */

@Entity
@Table(name = "study_agents")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = { @Parameter(name = "sequence", value = "seq_study_agents_id") })
public class StudyAgent extends StudyIntervention {

    /** The lazy list helper. */
    private LazyListHelper lazyListHelper;
    
    /** The agent. */
    private Agent agent;
    
    /** The agent as string. */
    private String agentAsString;
    
    /** The other agent. */
    private String otherAgent;

    /** The participation. */
    @Embedded
    private Participation participation;
    
    /** The ind type. */
    private INDType indType;
    
    /** The part of lead ind. */
    private Boolean partOfLeadIND;

    /*
     * Constructor -- Initializes participation at create time
     */
    /**
     * Instantiates a new study agent.
     */
    public StudyAgent() {
        this.setStudyTherapyType(StudyTherapyType.DRUG_ADMINISTRATION);
        participation = new Participation();
        lazyListHelper = new LazyListHelper();
        lazyListHelper.add(StudyAgentINDAssociation.class, new StudyAgentChildInstantiateFactory<StudyAgentINDAssociation>(this,StudyAgentINDAssociation.class));
    }
    
    /**
     * Instantiates a new study agent.
     *
     * @param agent the agent
     */
    public StudyAgent(Agent agent){
        this();
        this.agent = agent;
        
    }

    /**
     * Gets the agent.
     *
     * @return the agent
     */
    @ManyToOne
    @JoinColumn(name = "agent_id")
    // We should never create new agents here, so no cascades
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
     * Gets the study agent ind associations internal.
     *
     * @return the study agent ind associations internal
     */
    @OneToMany(mappedBy = "studyAgent", fetch = FetchType.LAZY)
    @Cascade({ CascadeType.ALL, CascadeType.DELETE_ORPHAN })
    @Fetch(value = org.hibernate.annotations.FetchMode.SUBSELECT)
    public List<StudyAgentINDAssociation> getStudyAgentINDAssociationsInternal() {
        return lazyListHelper.getInternalList(StudyAgentINDAssociation.class);
    }

    /**
     * Sets the study agent ind associations internal.
     *
     * @param studyAgentINDAssociations the new study agent ind associations internal
     */
    public void setStudyAgentINDAssociationsInternal(List<StudyAgentINDAssociation> studyAgentINDAssociations) {
        lazyListHelper.setInternalList(StudyAgentINDAssociation.class, studyAgentINDAssociations);
    }

    /**
     * Gets the study agent ind associations.
     *
     * @return the study agent ind associations
     */
    @Transient
    public List<StudyAgentINDAssociation> getStudyAgentINDAssociations() {
        return lazyListHelper.getLazyList(StudyAgentINDAssociation.class);
    }

    /**
     * Sets the study agent ind associations.
     *
     * @param studyAgentINDAssociations the new study agent ind associations
     */
    @Transient
    public void setStudyAgentINDAssociations(
                    List<StudyAgentINDAssociation> studyAgentINDAssociations) {
        setStudyAgentINDAssociationsInternal(studyAgentINDAssociations);
    }

    /**
     * Gets the agent as string.
     *
     * @return the agent as string
     */
    @Transient
    public String getAgentAsString() {
        return agentAsString;
    }

    /**
     * Sets the agent as string.
     *
     * @param agentAsString the new agent as string
     */
    public void setAgentAsString(String agentAsString) {
        this.agentAsString = agentAsString;
    }

    /**
     * Gets the participation.
     *
     * @return the participation
     */
    public Participation getParticipation() {
        return participation;
    }

    /**
     * Sets the participation.
     *
     * @param participation the new participation
     */
    public void setParticipation(Participation participation) {
        this.participation = participation;
    }

    /**
     * Gets the investigational new drug indicator.
     *
     * @return the investigational new drug indicator
     */
    @Transient
    public boolean getInvestigationalNewDrugIndicator() {
        return getStudyAgentINDAssociations() != null && getStudyAgentINDAssociations().size() > 0;
    }

    /**
     * Adds the study agent ind association.
     *
     * @param ass the ass
     */
    @Transient
    public void addStudyAgentINDAssociation(StudyAgentINDAssociation ass) {
        getStudyAgentINDAssociations().add(ass);
        ass.setStudyAgent(this);
    }

    /**
     * Gets the ind type.
     *
     * @return the ind type
     */
    public INDType getIndType() {
        return indType;
    }

    /**
     * Sets the ind type.
     *
     * @param indType the new ind type
     */
    public void setIndType(INDType indType) {
        this.indType = indType;
    }

    /**
     * Gets the other agent.
     *
     * @return the other agent
     */
    public String getOtherAgent() {
        return otherAgent;
    }

    /**
     * Sets the other agent.
     *
     * @param otherAgent the new other agent
     */
    public void setOtherAgent(String otherAgent) {
        this.otherAgent = otherAgent;
    }

    /**
     * Gets the part of lead ind.
     *
     * @return the part of lead ind
     */
    public Boolean getPartOfLeadIND() {
        return partOfLeadIND;
    }

    /**
     * Sets the part of lead ind.
     *
     * @param partOfLeadIND the new part of lead ind
     */
    public void setPartOfLeadIND(Boolean partOfLeadIND) {
        this.partOfLeadIND = partOfLeadIND;
    }
    
    /**
     * Gets the part of lead ind as string.
     *
     * @return the part of lead ind as string
     */
    @Transient
    public String getPartOfLeadINDAsString(){
    	if(partOfLeadIND == null) return "";
    	return partOfLeadIND ? "Yes" : "No";
    }

    /**
     * Gets the agent name.
     *
     * @return the agent name
     */
    @Transient
    public String getAgentName() {
        if (StringUtils.isNotEmpty(otherAgent)) return otherAgent;
        if (agent != null) return agent.getDisplayName();
        return "no-agent-name";
    }

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.StudyIntervention#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((agent == null) ? 0 : agent.hashCode());
        result = prime * result + ((otherAgent == null) ? 0 : otherAgent.hashCode());
        result = prime * result + (getId() == null ? 0 : getId().hashCode());
        return result;
    }

    // /OBJECT METHODS

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.StudyIntervention#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof StudyAgent)) return false;
        final StudyAgent other = (StudyAgent) obj;
        if(this.isRetired() || other.isRetired()) return false;
        if (agent == null) {
            if (other.agent != null) return false;
        } else if (!agent.equals(other.agent)) return false;
        if (otherAgent == null) {
            if (other.otherAgent != null) return false;
        } else if (!otherAgent.equals(other.otherAgent)) return false;
        return true;
    }
    
    @Transient
    @Override
    public  String getInterventionName() {
    	return getAgentName();
    }

}
