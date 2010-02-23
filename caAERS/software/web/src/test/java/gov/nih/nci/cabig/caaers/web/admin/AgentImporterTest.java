package gov.nih.nci.cabig.caaers.web.admin;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
import gov.nih.nci.cabig.caaers.domain.Agent;
import gov.nih.nci.cabig.caaers.domain.repository.AgentRepository;
import gov.nih.nci.cabig.caaers.domain.repository.AgentRepositoryImpl;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.web.WebTestCase;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.easymock.EasyMock;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;


/**
 *	Input String has to have 2 tokens.Valid samples as given below
 *	NSC, Agent Name                                                                                                                   
 *	"723227","(161-180)ESO-1 Peptide"                                                                                                 
 *	"726584","(H115D)VHL35 Peptide"                                                                                                   
 *	"726190","(R)-(-)-Gossypol acetic acid (Ascenta's AT-101)" 
 * 
 * @author Monish Dombla
 *
 */
public class AgentImporterTest extends WebTestCase {
	
	private AgentRepository agentRepository;
	private AgentImporter importer;
	private DomainObjectImportOutcome<Agent> agentImportOutcome;
	private ImportCommand command;
	private Map<String,Agent> agentMap = null;
	
	protected void setUp() throws Exception {
		super.setUp();
		importer = new AgentImporter();
		command = new ImportCommand();
		agentRepository = registerMockFor(AgentRepositoryImpl.class);
	}
		
	public void testProcessAgent_ValidRecord(){

		String agentRecord = "\"708478\",\"GM-CSF cDNA / gold (Plasmid Vector pWRG3218)\"";
		agentImportOutcome = importer.processAgent(agentRecord, 1);
		assertNotNull(agentImportOutcome);
		Agent agent = agentImportOutcome.getImportedDomainObject();
		assertNotNull(agent);
		assertEquals("708478", agent.getNscNumber());
		assertEquals("GM-CSF cDNA / gold (Plasmid Vector pWRG3218)", agent.getName());
		
	}
	
	public void testProcessAgent_InValidRecord(){
		String agentRecord = "\"708478\"";
		agentImportOutcome = importer.processAgent(agentRecord, 1011);
		assertNotNull(agentImportOutcome);
		Agent agent = agentImportOutcome.getImportedDomainObject();
		assertNull(agent);
		assertNotNull(agentImportOutcome.getMessages());
		assertEquals(1, agentImportOutcome.getMessages().size());
		assertEquals("Invalid agent record found at line ::: 1011", agentImportOutcome.getMessages().get(0).getMessage());
	}
	
	public void testProcessEntities() {
		
		try {
			File agentsFile = getResources("classpath*:gov/nih/nci/cabig/caaers/web/admin/testdata/agents.txt")[0].getFile();
			assertTrue(agentsFile.exists());
			
			agentMap = new HashMap<String,Agent>();
			Agent agent1 = new Agent();
			agent1.setNscNumber("54321");
			agent1.setName("Viola");
			agentMap.put("54321", agent1);
			Agent agent2 = new Agent();
			agent2.setNscNumber("098765");
			agent2.setName("Change me");
			agentMap.put("098765", agent2);
			
			importer.setAgentMap(agentMap);
			importer.processEntities(agentsFile, command);
			
			assertNotNull(command);
			
			assertNotNull(command.getImportableAgents());
			assertEquals(10, command.getImportableAgents().size());
			
			assertNotNull(command.getUpdateableAgents());
			assertEquals(1, command.getUpdateableAgents().size());
			
			assertNotNull(command.getNonImportableAgents());
			assertEquals(2, command.getNonImportableAgents().size());
			
		} catch (IOException e) {
			fail("No Exception is expected");
		}
	}
	
	
	public void testGetMap(){
		
		importer.setAgentRepository(agentRepository);
		importer.setAgentMap(null);
		List<Agent> agentList = new ArrayList<Agent>();
		Agent agent1 = new Agent();
		agent1.setNscNumber("54321");
		agent1.setName("Viola");
		Agent agent2 = new Agent();
		agent2.setNscNumber("098765");
		agent2.setName("Change me");
		agentList.add(agent1);
		agentList.add(agent2);
		
		expect(agentRepository.getAllAgents()).andReturn(agentList).anyTimes();
		replayMocks();
		agentMap = importer.getAgentMap();
		verifyMocks();
		assertNotNull(agentMap);
		
		agentMap = new HashMap<String,Agent>();
		Map returnedMap = null; 
		agentMap.put("54321", agent1);
		agentMap.put("098765", agent2);
		importer.setAgentMap(agentMap);
		returnedMap = importer.getAgentMap();
		
	}
	
	public void testSave(){
		
		ImportCommand command = new ImportCommand();
		
		Agent agent1 = new Agent();
		agent1.setNscNumber("54321");
		agent1.setName("Viola");
		
		Agent agent2 = new Agent();
		agent2.setNscNumber("098765");
		agent2.setName("Change me");
		
		DomainObjectImportOutcome<Agent> importOutcome1 = new DomainObjectImportOutcome<Agent>();
		importOutcome1.setImportedDomainObject(agent1);
		importOutcome1.setSavable(true);
		command.addImportableAgent(importOutcome1);
		
		DomainObjectImportOutcome<Agent> importOutcome2 = new DomainObjectImportOutcome<Agent>();
		importOutcome2.setImportedDomainObject(agent2);
		importOutcome2.setSavable(true);
		command.addUpdateableAgent(importOutcome2);
		
		importer.setAgentRepository(agentRepository);
		
		agentRepository.saveAgent((Agent)EasyMock.anyObject());
		expectLastCall().anyTimes();
		
		replayMocks();
		importer.save(command, null);
		verifyMocks();
		
	}
	
	
	private static Resource[] getResources(String pattern) throws IOException {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources(pattern);
        return resources;
    }
}
