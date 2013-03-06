/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain.repository;

/**
 * This Class implements the AgentRepository Interface. 
 * Each method delegates the call to the underlying AgentDao.
 */

import gov.nih.nci.cabig.caaers.dao.AgentDao;
import gov.nih.nci.cabig.caaers.domain.Agent;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

 
/**
 * The Class AgentRepositoryImpl.
 */
@Transactional(readOnly = true)
public class AgentRepositoryImpl implements AgentRepository {
	
	/** The agent dao. */
	private AgentDao agentDao;
	
	/**
	 * This method is used to retrieve an agent having the specified name.
	 *
	 * @param name the name
	 * @return the agent by name
	 */
	public Agent getAgentByName(String name) {
		return agentDao.getByName(name);
	}

	/**
	 * This method is used to retrieve an agent having its ID.
	 *
	 * @param id the id
	 * @return Agent
	 */
	public Agent getAgentByID(Integer id) {
		return agentDao.getById(id);
	}

	/**
	 * This method is used to retrieve an agent having the specified nsc number.
	 *
	 * @param nscNumber the nsc number
	 * @return the agent by nsc number
	 */
	public Agent getAgentByNscNumber(String nscNumber) {
		return agentDao.getByNscNumber(nscNumber);
	}

	/**
	 * This method returns a list of agents matching the given criteria from the database.
	 *
	 * @param subnames the subnames
	 * @return the agents by subnames
	 */
	public List<Agent> getAgentsBySubnames(String[] subnames) {
		return agentDao.getBySubnames(subnames);
	}

	/**
	 * This method returns all agents in the database.
	 *
	 * @return the all agents
	 */
	public List<Agent> getAllAgents() {
		return agentDao.getAll();
	}

	/**
	 * This method saves the given agent.
	 *
	 * @param agent the agent
	 */
	@Transactional(readOnly = false)
	public void saveAgent(Agent agent) {
		agentDao.save(agent);
	}
	
	/**
	 * Sets the agent dao.
	 *
	 * @param agentDao the new agent dao
	 */
	public void setAgentDao(AgentDao agentDao) {
		this.agentDao = agentDao;
	}
}
