package gov.nih.nci.cabig.caaers.api;

import gov.nih.nci.cabig.caaers.domain.Agent;

import java.util.List;

/**
 * Organization Creation and Updation.
 * @author Ramakrishna
 *
 */
public interface AgentService {
	
	public List<ProcessingOutcome> createOrUpdateAgents(List<Agent> agents);

}
