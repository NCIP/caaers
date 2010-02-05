package gov.nih.nci.cabig.caaers.domain.repository;

import static org.easymock.EasyMock.expect;
import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.dao.AgentDao;
import gov.nih.nci.cabig.caaers.domain.Agent;

import java.util.ArrayList;
import java.util.List;

import org.easymock.classextension.EasyMock;

/**
 * This is the repository test class for managing agents
 * @author Moni
 *
 */
public class AgentRepositoryImplTest extends AbstractTestCase {

	AgentRepositoryImpl repositoryImpl;
	AgentDao agentDao;
	
	protected void setUp() throws Exception {
		super.setUp();
		repositoryImpl = new AgentRepositoryImpl();
		agentDao = registerDaoMockFor(AgentDao.class);
		repositoryImpl.setAgentDao(agentDao);
	}
	
	public void test_GetAgentByName() {
		Agent agentReturned = null;
		List<Agent> agentList = new ArrayList<Agent>();
		Agent agent = new Agent();
    	agent.setDescription("abcd");
    	agent.setName("abcd");
    	agent.setNscNumber("1234");
    	agentList.add(agent);
    	
    	expect(agentDao.getByName((String)EasyMock.anyObject())).andReturn(agent).anyTimes();
    	replayMocks();
    	agentReturned = repositoryImpl.getAgentByName("abcd");
    	verifyMocks();
		assertNotNull(agentReturned);
		assertEquals("abcd", agentReturned.getName());
	}
	
	public void test_GetAgentByNscNumber(){
		Agent agentReturned = null;
		List<Agent> agentList = new ArrayList<Agent>();
		Agent agent = new Agent();
    	agent.setDescription("abcd");
    	agent.setName("abcd");
    	agent.setNscNumber("1234");
    	agentList.add(agent);
    	
    	expect(agentDao.getByNscNumber((String)EasyMock.anyObject())).andReturn(agent).anyTimes();
    	replayMocks();
    	agentReturned = repositoryImpl.getAgentByNscNumber("1234");
    	verifyMocks();
		assertNotNull(agentReturned);
		assertEquals("1234", agentReturned.getNscNumber());
	}
	
	public void test_GetAgentsBySubnames(){
		List<Agent> agentListReturned = null;
		List<Agent> agentList = new ArrayList<Agent>();
		Agent agent = new Agent();
    	agent.setDescription("abcd");
    	agent.setName("abcd");
    	agent.setNscNumber("1234");
    	agentList.add(agent);
    	
    	expect(agentDao.getBySubnames((String[])EasyMock.anyObject())).andReturn(agentList).anyTimes();
    	replayMocks();
    	agentListReturned = repositoryImpl.getAgentsBySubnames(new String[] { "abcd" });
    	verifyMocks();
		assertNotNull(agentListReturned);
		assertEquals(1, agentListReturned.size());
		assertEquals("abcd", agentListReturned.get(0).getName());
	}
	
	public void test_GetAllAgents(){
		List<Agent> agentListReturned = null;
		List<Agent> agentList = new ArrayList<Agent>();
		Agent agent = new Agent();
    	agent.setDescription("abcd");
    	agent.setName("abcd");
    	agent.setNscNumber("1234");
    	agentList.add(agent);
    	Agent agent1 = new Agent();
    	agent.setDescription("xyzs");
    	agent.setName("weqer");
    	agent.setNscNumber("654321");
    	agentList.add(agent1);
    	
    	expect(agentDao.getAll()).andReturn(agentList).anyTimes();
    	
		replayMocks();
		agentListReturned = repositoryImpl.getAllAgents();
		verifyMocks();
		assertNotNull(agentListReturned);
		assertEquals(2, agentListReturned.size());
    	
	}
	
	public void test_SaveAgent(){
		
		Agent agent = new Agent();
    	agent.setDescription("abcd");
    	agent.setName("abcd");
    	agent.setNscNumber("1234");

    	agentDao.save((Agent)EasyMock.anyObject());
		
		replayMocks();
		repositoryImpl.saveAgent(agent);
		verifyMocks();
	}
}
