package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.ctms.collections.LazyListHelper;
import gov.nih.nci.cabig.ctms.domain.DomainObject;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

 
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
    
    /** The treatment assignment agents */
    private List<TreatmentAssignmentAgent> treatmentAssignmentAgents;
    
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
        treatmentAssignmentAgents = new ArrayList<TreatmentAssignmentAgent>();
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
    @OneToMany(mappedBy = "studyAgent", fetch = FetchType.LAZY, orphanRemoval = true)
    @Cascade({ CascadeType.ALL })
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
        if (getAgent() == null) {
            if (other.getAgent() != null) return false;
        } else if (!getAgent().equals(other.getAgent())) return false;
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
    
    /*
     * Determined whether this has IND of CTEP and is Lead
     * 
     */
    @Transient
     public boolean isCTEPLead() {
        if(isRetired()) return false;
        if(BooleanUtils.isNotTrue(getPartOfLeadIND())) return false;
        if(getStudyAgentINDAssociations() == null || getStudyAgentINDAssociations().isEmpty()) return false;
        for(StudyAgentINDAssociation a : getStudyAgentINDAssociations())  {
           if(a.getInvestigationalNewDrug().getINDHolder() instanceof  OrganizationHeldIND) {
               boolean isCTEP = ((OrganizationHeldIND)a.getInvestigationalNewDrug().getINDHolder()).getOrganization().isCtep()  ;
               if(isCTEP) return true;
           }
        }
        return false;
        
        
     }

    @OneToMany(mappedBy = "studyAgent", fetch = FetchType.LAZY, orphanRemoval = true)
    @Cascade({ CascadeType.ALL })
    @Fetch(value = org.hibernate.annotations.FetchMode.SUBSELECT)
	public List<TreatmentAssignmentAgent> getTreatmentAssignmentAgents() {
		return treatmentAssignmentAgents;
	}

	public void setTreatmentAssignmentAgents(
			List<TreatmentAssignmentAgent> treatmentAssignmentAgents) {
		this.treatmentAssignmentAgents = treatmentAssignmentAgents;
	}
	
	public boolean shouldHonor(){
		// shouldHonor = true if study has a CTEP Ind and given studyAgent is CTEP Ind
    	// shouldHonor = true if study has no CTEP Ind and given studyAgent is not CTEP Ind
    	// shouldHonor = false if study has a CTEP Ind and given studyAgent is not CTEP Ind
    	// All other combinations are invalid
		if(isCTEPLead() && !getStudy().hasLeadCTEPInds()) throw new IllegalStateException("Agent is CTEP lead but study has no CTEP INDs. Its an invalid state.");
		return !(getStudy().hasLeadCTEPInds() ^ isCTEPLead());
	}
	
	@Transient
	public void removeTreatmentAssignmentAgents(){
		for(TreatmentAssignmentAgent treatmentAssignmentAgent: treatmentAssignmentAgents){
			treatmentAssignmentAgent.getTreatmentAssignment().getTreatmentAssignmentStudyInterventions().remove(treatmentAssignmentAgent);
		}
		treatmentAssignmentAgents.clear();
	}
	
//	@Transient
//    public void syncTreatmentAssignmentAgentSpecificTerm(String action){
//		if(getAgent() != null){
//	    	for(AgentSpecificTerm agentSpecificTerm : getAgent().getAgentSpecificTerms()){
//				syncTreatmentAssignmentAgentSpecificTerm(agentSpecificTerm, action);
//	    	}
//		}
//    }
	
	@Transient
    public void syncTreatmentAssignmentAgentSpecificTerm(AgentSpecificTerm agentSpecificTerm, String action){
    	for(TreatmentAssignmentAgent taa : getTreatmentAssignmentAgents()){
    		if(action.equals(AgentSpecificTerm.EXPTECTED_AE_DELETED)){
    			taa.getTreatmentAssignment().removeExpectedAE(taa, agentSpecificTerm.getTerm());
    		}else if(action.equals(AgentSpecificTerm.EXPTECTED_AE_ADDED)){
    			taa.getTreatmentAssignment().addExpectedAE(taa, agentSpecificTerm);
    		}else if(action.equals(AgentSpecificTerm.EXPTECTED_AE_UPDATED)){
    			taa.getTreatmentAssignment().updateExpectedAE(taa, agentSpecificTerm);
    		}
    	}
    }

}
