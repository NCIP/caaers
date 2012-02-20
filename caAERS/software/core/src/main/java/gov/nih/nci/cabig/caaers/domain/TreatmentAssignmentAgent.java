package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.ctms.domain.DomainObject;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author Ion C. Olaru
 *         Date: 1/5/12 -1:45 PM
 */
@Entity
@Table(name = "ta_agents")
public class TreatmentAssignmentAgent extends TreatmentAssignmentStudyIntervention {

    private StudyAgent studyAgent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "study_agent_id", nullable = false)
    public StudyAgent getStudyAgentInternal() {
        return studyAgent;
    }

    public void setStudyAgentInternal(StudyAgent studyAgent) {
        this.studyAgent = studyAgent;
    }
    
    @Transient
    public StudyAgent getStudyAgent() {
        return getStudyAgentInternal();
    }

    public void setStudyAgent(StudyAgent studyAgent) {
    	setStudyAgentInternal(studyAgent);
    	if (studyAgent.getAgent() == null) return;
    	// shouldHonor = true if study has a CTEP Ind and given studyAgent is CTEP Ind
    	// shouldHonor = true if study has no CTEP Ind and given studyAgent is not CTEP Ind
    	// shouldHonor = false if study has a CTEP Ind and given studyAgent is not CTEP Ind
    	// All other combinations are invalid
    	boolean shouldHonor = !(studyAgent.getStudy().hasLeadCTEPInds() ^ studyAgent.isCTEPLead());
    	//TODO: Import Expected AEs
    	for (AgentSpecificTerm agentSpecificTerm: studyAgent.getAgent().getAgentSpecificTerms()){
    		if(!hasTerm(agentSpecificTerm.getTerm())){
    			AbstractStudyInterventionExpectedAE asiea = null;
    			if (agentSpecificTerm instanceof AgentSpecificCtcTerm) {
    				asiea = new StudyInterventionExpectedCtcTerm(this, agentSpecificTerm, shouldHonor && agentSpecificTerm.isExpected());
				}else {
					asiea = new StudyInterventionExpectedMeddraLowLevelTerm(this, agentSpecificTerm, shouldHonor && agentSpecificTerm.isExpected());
				}
    			this.addAbstractStudyInterventionExpectedAEs(asiea);
    		}
    	}
    } 
    
    private boolean hasTerm(DomainObject term){
    	for(AbstractStudyInterventionExpectedAE asiea : getAbstractStudyInterventionExpectedAEs()){
    		if (asiea.getTerm().getId().equals(term.getId())){
    			return true;
    		}
    	}
    	return false;
    }

    @Override
    @Transient
    public StudyIntervention getStudyIntervention() {
        return this.getStudyAgent();
    }
}
