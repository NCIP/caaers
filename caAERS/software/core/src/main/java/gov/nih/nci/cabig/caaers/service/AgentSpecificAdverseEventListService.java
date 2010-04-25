package gov.nih.nci.cabig.caaers.service;

import gov.nih.nci.cabig.caaers.domain.Agent;
import gov.nih.nci.cabig.caaers.domain.AgentSpecificTerm;
import gov.nih.nci.cabig.caaers.domain.Study;

import java.util.List;

/**
 * 
 * @author Ion C. Olaru
 * 
 */
public interface AgentSpecificAdverseEventListService {
    public List<AgentSpecificTerm> getListByAgent(Integer agentID);
    public void synchronizeStudyWithAgent(Study s, Agent a);
    public void synchronizeStudyWithAgentTerm(Study s, AgentSpecificTerm at);
}
