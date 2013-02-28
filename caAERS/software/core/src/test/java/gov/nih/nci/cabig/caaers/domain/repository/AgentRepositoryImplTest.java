/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain.repository;

import static org.easymock.EasyMock.expect;
import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.DaoTestCase;
import gov.nih.nci.cabig.caaers.dao.AgentDao;
import gov.nih.nci.cabig.caaers.dao.query.AgentQuery;
import gov.nih.nci.cabig.caaers.domain.Agent;

import java.util.ArrayList;
import java.util.List;

import org.easymock.classextension.EasyMock;

/**
 * This is the repository test class for managing agents
 * @author Ion C. Olaru
 *         - Refactored to use DBUnit instead of EasyMocks
 *
 */
public class AgentRepositoryImplTest extends DaoTestCase {

	AgentRepository agentRepository = (AgentRepository)getDeployedApplicationContext().getBean("agentRepository");
	
	protected void setUp() throws Exception {
		super.setUp();
	}
	
	public void testGetAgentByName() {
		Agent agentReturned;
    	agentReturned = agentRepository.getAgentByName("agent-01");
		assertNotNull(agentReturned);
		assertEquals("agent-01", agentReturned.getName());
		assertEquals("001", agentReturned.getNscNumber());
	}

	public void testGetAgentByNSC() {
		Agent agentReturned;
    	agentReturned = agentRepository.getAgentByNscNumber("002");
		assertNotNull(agentReturned);
		assertEquals("agent-02", agentReturned.getName());
		assertEquals("002", agentReturned.getNscNumber());
	}

	public void testGetAllAgents() {
		List<Agent> agents = agentRepository.getAllAgents();
		assertNotNull(agents);
		assertEquals(3, agents.size());
	}

	public void testGetByNameFilterByRetired() {
		List<Agent> agents = agentRepository.getAgentsByNameAndNsc("01", "001", true);
		assertNotNull(agents);
		assertEquals(1, agents.size());
	}

}
