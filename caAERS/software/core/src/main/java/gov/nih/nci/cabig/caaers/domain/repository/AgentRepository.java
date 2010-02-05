package gov.nih.nci.cabig.caaers.domain.repository;

import gov.nih.nci.cabig.caaers.domain.Agent;

import java.util.List;

/**
 * This service interface is used for all CRUD operations on an AGENT/s
 * @author Moni
 *
 */
public interface AgentRepository {
	
	/**
	 * This method returns all agents in the database. 
	 * @return
	 */
	public List<Agent> getAllAgents();
	
	/**
	 * This method returns a list of agents matching the given criteria from the database
	 * @param subnames
	 * @return
	 */
	public List<Agent> getAgentsBySubnames(String[] subnames);
	
	/**
	 * This method is used to retrieve an agent having the specified name. 
	 * @param name
	 * @return
	 */
	public Agent getAgentByName(String name);
	
	
	/**
	 * This method is used to retrieve an agent having the specified nsc number.
	 * @param nscNumber
	 * @return
	 */
	public Agent getAgentByNscNumber(String nscNumber);
	
	/**
	 * This method saves the given agent
	 * @param agent
	 */
	public void saveAgent(Agent agent);

}
