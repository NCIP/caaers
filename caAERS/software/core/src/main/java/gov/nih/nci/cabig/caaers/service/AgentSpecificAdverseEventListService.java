/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
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
    public void synchronizeStudyWithAgent(Study s, Agent a, boolean deleted);
    public void synchronizeStudyWithAgentTerm(Study s, AgentSpecificTerm at);
    public void synchronizeStudyWithAgentTerm(Study s, AgentSpecificTerm at, boolean deleted);
}
