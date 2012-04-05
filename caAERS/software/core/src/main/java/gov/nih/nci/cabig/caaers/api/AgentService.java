package gov.nih.nci.cabig.caaers.api;

import gov.nih.nci.cabig.caaers.domain.Agent;
import gov.nih.nci.cabig.caaers.domain.EntityErrorMessage;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

/**
 * Organization Creation and Updation.
 * @author Ramakrishna
 *
 */
@Transactional(readOnly=false)
public interface AgentService {
	
	public List<EntityErrorMessage> createOrUpdateAgents(List<Agent> agents);

}
