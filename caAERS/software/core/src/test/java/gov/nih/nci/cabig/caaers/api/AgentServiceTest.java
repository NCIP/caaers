/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.api;

import gov.nih.nci.cabig.caaers.CaaersDbNoSecurityTestCase;
import gov.nih.nci.cabig.caaers.api.impl.AgentServiceImpl;
import gov.nih.nci.cabig.caaers.dao.AgentDao;
import gov.nih.nci.cabig.caaers.domain.Agent;

import java.util.ArrayList;
import java.util.List;

public class AgentServiceTest extends CaaersDbNoSecurityTestCase{
	
	    private AgentDao agentDao;
		private AgentServiceImpl agentService;
	
	 @Override
	    protected void setUp() throws Exception {
	        super.setUp();

	        agentService = (AgentServiceImpl) getDeployedApplicationContext().getBean("agentService");
	        agentDao = (AgentDao) getDeployedApplicationContext().getBean("agentDao");

	    }
	 
	 public void testAddAgents(){
		List<Agent> dbAgents = agentDao.getAll();
		 assertEquals(1,dbAgents.size());
		 
		 List<Agent> agents = new ArrayList<Agent>();
		 
		 Agent agent1 = new Agent();
		 agent1.setName("Agent 1");
		 agent1.setDescription("Description");
		 agent1.setNscNumber("NSC01");
		 agents.add(agent1);
		 
		 Agent agent2 = new Agent();
		 agent2.setName("Agent 2");
		 agent2.setDescription("Agent 2 description");
		 agent2.setNscNumber("NSC02");
		 agents.add(agent2);
		 
		 agentService.createOrUpdateAgents(agents);
		 
		 interruptSession();
		 
		 dbAgents = agentDao.getAll();
		 assertEquals(3,dbAgents.size());
	 }
	 
	 public void testAddAndUpdateAgents(){
			List<Agent> existingAgents = agentDao.getAll();
			 assertEquals(1,existingAgents.size());
			 assertEquals("Agent", existingAgents.get(0).getName());
			 assertEquals("Description", existingAgents.get(0).getDescription());
			 assertEquals("nsc", existingAgents.get(0).getNscNumber());
			 assertFalse(existingAgents.get(0).getRetiredIndicator());
			 
			 // update existing agent
			Agent updatedAgent = new Agent();
			updatedAgent.setName("updated Agent");
			updatedAgent.setDescription("updated description");
			updatedAgent.setNscNumber("nsc");
			updatedAgent.setRetiredIndicator(true);
			 
			 List<Agent> agents = new ArrayList<Agent>();
			 agents.add(updatedAgent);
			 
			 // add new agent
			 Agent agent1 = new Agent();
			 agent1.setName("Agent 1");
			 agent1.setDescription("Agent 1 description");
			 agent1.setNscNumber("NSC01");
			 agents.add(agent1);
			 
				// call the service		 
			 List<ProcessingOutcome> errorMssgs= agentService.createOrUpdateAgents(agents);
			 
			 assertEquals(2,errorMssgs.size());
			 for(ProcessingOutcome errMssg : errorMssgs){
                 System.out.println(errMssg.getBusinessId() + " : " + errMssg.getMessages());
				 assertNotNull(errMssg.getBusinessId());
                 assertFalse(errMssg.isFailed());
			 }
			 
			 interruptSession();
			 
			 List<Agent> updatedAgents = agentDao.getAll();
			 assertEquals(2,updatedAgents.size());
			 
             Agent a = updatedAgents.get(0);
             if(!a.getNscNumber().equals("nsc")){
                 a = updatedAgents.get(1);
             }
			 assertEquals("nsc",a.getNscNumber());
			 assertEquals("updated Agent",a.getName());
			 assertEquals("updated description",a.getDescription());
			 assertTrue(a.getRetiredIndicator());
			 
		 }

}
