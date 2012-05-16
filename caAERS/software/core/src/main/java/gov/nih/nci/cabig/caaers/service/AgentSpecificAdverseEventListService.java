package gov.nih.nci.cabig.caaers.service;

import gov.nih.nci.cabig.caaers.domain.AgentSpecificTerm;
import gov.nih.nci.cabig.caaers.domain.StudyAgent;

import java.util.List;

/**
 * 
 * @author Ion C. Olaru
 * 
 */
public interface AgentSpecificAdverseEventListService {
    public List<AgentSpecificTerm> getListByAgent(Integer agentID);
    //public void synchronizeStudyWithAgent(Study s, Agent a);
    //public void synchronizeStudyWithAgent(StudyAgent sa);
    //public void synchronizeStudyWithAgent(Study s, Agent a, boolean deleted);
    public void synchronizeStudyWithAgent(StudyAgent sa, String action);
    //public void synchronizeStudyWithAgentTerm(Study s, AgentSpecificTerm at);
//    public void synchronizeStudyWithAgentTerm(StudyAgent sa, AgentSpecificTerm at);
    //public void synchronizeStudyWithAgentTerm(Study s, AgentSpecificTerm at, boolean deleted);
    public void synchronizeStudyWithAgentTerm(StudyAgent s, AgentSpecificTerm at, String action);

}
