package gov.nih.nci.cabig.caaers.api;

import gov.nih.nci.cabig.caaers.domain.Agent;
import gov.nih.nci.cabig.caaers.domain.EntityErrorMessage;

import java.util.List;

/**
 * Organization Creation and Updation.
 * @author Ramakrishna
 *
 */
public interface AgentService {
	
	public List<EntityErrorMessage> createOrUpdateAgents(List<Agent> agents);

}
