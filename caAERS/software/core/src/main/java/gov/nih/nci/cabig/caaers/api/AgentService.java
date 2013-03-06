/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
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
