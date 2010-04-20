package gov.nih.nci.cabig.caaers.service;

import gov.nih.nci.cabig.caaers.domain.AgentSpecificTerm;

import java.util.List;

/**
 * 
 * @author Ion C. Olaru
 * 
 */
public interface AgentSpecificAdverseEventListService {
    public List<AgentSpecificTerm> getListByAgent(Integer agentID);
}
