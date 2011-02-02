package gov.nih.nci.cabig.caaers.domain.repository;

import gov.nih.nci.cabig.caaers.domain.Agent;

import java.util.List;

 
/**
 * This service interface is used for all CRUD operations on an AGENT/s.
 *
 * @author Moni
 */
public interface AgentRepository {
	
	/**
	 * This method returns all agents in the database.
	 *
	 * @return the all agents
	 */
	public List<Agent> getAllAgents();
	
	/**
	 * This method returns a list of agents matching the given criteria from the database.
	 *
	 * @param subnames the subnames
	 * @return the agents by subnames
	 */
	public List<Agent> getAgentsBySubnames(String[] subnames);

    /**
     * This method is used to retrieve an agent having its ID.
     *
     * @param id the id
     * @return Agent
     */
    public Agent getAgentByID(Integer id);

	/**
	 * This method is used to retrieve an agent having the specified name.
	 *
	 * @param name the name
	 * @return the agent by name
	 */
	public Agent getAgentByName(String name);
	
	
	/**
	 * This method is used to retrieve an agent having the specified nsc number.
	 *
	 * @param nscNumber the nsc number
	 * @return the agent by nsc number
	 */
	public Agent getAgentByNscNumber(String nscNumber);
	
	/**
	 * This method saves the given agent.
	 *
	 * @param agent the agent
	 */
	public void saveAgent(Agent agent);

}
