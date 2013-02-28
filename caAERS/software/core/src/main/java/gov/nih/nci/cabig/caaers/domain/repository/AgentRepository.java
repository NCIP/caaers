/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain.repository;

import gov.nih.nci.cabig.caaers.dao.query.AgentQuery;
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

	public List<Agent> getAgentsByNameAndNsc(String name, String nsc, boolean filterByRetired);

    public List<Agent> getAgentsByNameOrNsc(String name, String nsc, boolean filterByRetired);

	/**
	 * This method saves the given agent.
	 *
	 * @param agent the agent
	 */
	public void saveAgent(Agent agent);

    public List<Agent> search(AgentQuery q);
}
