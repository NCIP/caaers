package gov.nih.nci.cabig.caaers.domain.repository;

/**
 * This Class implements the AgentRepository Interface. 
 * Each method delegates the call to the underlying AgentDao.
 */

import gov.nih.nci.cabig.caaers.dao.AgentDao;
import gov.nih.nci.cabig.caaers.domain.Agent;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public class AgentRepositoryImpl implements AgentRepository {
	
	private AgentDao agentDao;
	
	/**
	 * This method is used to retrieve an agent having the specified name. 
	 * @param name
	 * @return
	 */
	public Agent getAgentByName(String name) {
		return agentDao.getByName(name);
	}

	/**
	 * This method is used to retrieve an agent having its ID
	 * @param id
	 * @return Agent
	 */
	public Agent getAgentByID(Integer id) {
		return agentDao.getById(id);
	}

	/**
	 * This method is used to retrieve an agent having the specified nsc number.
	 * @param nscNumber
	 * @return
	 */
	public Agent getAgentByNscNumber(String nscNumber) {
		return agentDao.getByNscNumber(nscNumber);
	}

	/**
	 * This method returns a list of agents matching the given criteria from the database
	 * @param subnames
	 * @return
	 */
	public List<Agent> getAgentsBySubnames(String[] subnames) {
		return agentDao.getBySubnames(subnames);
	}

	/**
	 * This method returns all agents in the database. 
	 * @return
	 */
	public List<Agent> getAllAgents() {
		return agentDao.getAll();
	}

	/**
	 * This method saves the given agent
	 * @param agent
	 */
	@Transactional(readOnly = false)
	public void saveAgent(Agent agent) {
		agentDao.save(agent);
	}
	
	public void setAgentDao(AgentDao agentDao) {
		this.agentDao = agentDao;
	}
}
