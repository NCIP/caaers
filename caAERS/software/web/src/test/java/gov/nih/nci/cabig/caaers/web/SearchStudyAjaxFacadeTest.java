package gov.nih.nci.cabig.caaers.web;

import gov.nih.nci.cabig.caaers.domain.Agent;
import gov.nih.nci.cabig.caaers.domain.repository.AgentRepository;
import gov.nih.nci.cabig.caaers.web.search.SearchStudyAjaxFacade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.easymock.classextension.EasyMock;
import org.springframework.mock.web.MockHttpServletRequest;

public class SearchStudyAjaxFacadeTest extends DwrFacadeTestCase {

	private SearchStudyAjaxFacade searchStudyAjaxFacade;
	private AgentRepository agentRepository;
	private List<Agent> agents;
	private MockHttpServletRequest request;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		searchStudyAjaxFacade = new SearchStudyAjaxFacade();
		agentRepository = registerMockFor(AgentRepository.class);
		searchStudyAjaxFacade.setAgentRepository(agentRepository);
		agents = new ArrayList<Agent>();
		Agent agent = new Agent();
		agent.setId(1);
		agent.setName("name1");
		agent.setNscNumber("nsc1");
		agent.setRetiredIndicator(true);
		agent.setDescription("desc1");
		agents.add(agent);
		agent = new Agent();
		agent.setId(2);
		agent.setName("name2");
		agent.setNscNumber("nsc2");
		agent.setRetiredIndicator(true);
		agent.setDescription("desc2");
		agents.add(agent);
		request = new MockHttpServletRequest();
	}
	
	public void testGetAgentsTable(){
		EasyMock.expect(agentRepository.getAgentsByNameAndNsc("text", "nsc", false)).andReturn(agents);
		replayMocks();
		List<Agent> returnedAgents = searchStudyAjaxFacade.getAgentsTable(new HashMap(), "text", "nsc", request);
		assertEquals(2, returnedAgents.size());
		assertNull(returnedAgents.get(0).getDescription());
		assertNull(returnedAgents.get(1).getDescription());
		verifyMocks();
	}
	
}
