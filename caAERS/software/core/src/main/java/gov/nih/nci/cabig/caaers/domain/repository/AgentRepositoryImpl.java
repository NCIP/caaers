package gov.nih.nci.cabig.caaers.domain.repository;

/**
 * This Class implements the AgentRepository Interface. 
 * Each method delegates the call to the underlying AgentDao.
 */

import gov.nih.nci.cabig.caaers.dao.AgentDao;
import gov.nih.nci.cabig.caaers.dao.query.AgentQuery;
import gov.nih.nci.cabig.caaers.domain.Agent;

import java.util.ArrayList;
import java.util.List;

import gov.nih.nci.cabig.caaers.domain.Study;
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

	public List<Agent> search(AgentQuery q) {
		return (List<Agent>)agentDao.search(q);
	}

    public List<Agent> getAgentsByNameAndNsc(String name, String nsc, boolean filterByRetired) {
        List<Agent> agents;
        AgentQuery q = new AgentQuery();
        q.filterByName(name);
        q.filterByNSC(nsc);
        if (filterByRetired) q.filterByRetiredStatus(false);
        agents = search(q);
        return agents;
    }

    public List<Agent> getAgentsByNameOrNsc(String name, String nsc, boolean filterByRetired) {
        List<Agent> agents;
        AgentQuery q = new AgentQuery();
        q.filterByNameOrNSC(name, nsc);
        if (filterByRetired) q.filterByRetiredStatus(false);
        agents = search(q);
        return agents;
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
