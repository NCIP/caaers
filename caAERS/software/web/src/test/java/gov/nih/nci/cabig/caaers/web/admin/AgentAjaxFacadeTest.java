/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.dao.CtcTermDao;
import gov.nih.nci.cabig.caaers.dao.meddra.LowLevelTermDao;
import gov.nih.nci.cabig.caaers.domain.Agent;
import gov.nih.nci.cabig.caaers.domain.AgentSpecificTerm;
import gov.nih.nci.cabig.caaers.domain.CtcTerm;
import gov.nih.nci.cabig.caaers.domain.meddra.LowLevelTerm;
import gov.nih.nci.cabig.caaers.domain.repository.AgentRepository;
import gov.nih.nci.cabig.caaers.web.DwrFacadeTestCase;
import gov.nih.nci.cabig.caaers.web.dwr.AjaxOutput;

import java.util.ArrayList;
import java.util.List;

import org.easymock.classextension.EasyMock;
import org.springframework.mock.web.MockHttpSession;

public class AgentAjaxFacadeTest extends DwrFacadeTestCase {
	
	private AgentAjaxFacade agentAjaxFacade;
	private AgentRepository agentRepository;
	private Agent agent;
	private CtcTermDao ctcTermDao;
	private LowLevelTermDao lowLevelTermDao;
	private List<AgentSpecificTerm> list;
	private CtcTerm ctcTerm;
	private LowLevelTerm lowLevelTerm;
	private MockHttpSession session;
	private AgentCommand agentCommand;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		agentAjaxFacade = new AgentAjaxFacade();
		agentRepository = registerMockFor(AgentRepository.class);
		ctcTermDao = registerMockFor(CtcTermDao.class);
		lowLevelTermDao = registerMockFor(LowLevelTermDao.class);
		agentAjaxFacade.setCtcTermDao(ctcTermDao);
		agentAjaxFacade.setAgentRepository(agentRepository);
		agentAjaxFacade.setLowLevelTermDao(lowLevelTermDao);
		agent = registerMockFor(Agent.class);
		ctcTerm = new CtcTerm();
		lowLevelTerm = new LowLevelTerm();
		session = new MockHttpSession();
		agentCommand = new AgentCommand();
		list = new ArrayList<AgentSpecificTerm>();
		agentCommand.setAgentSpecificTerms(list);
	}

	public void testAddAgentSpecificTerms1() throws Exception{
		session.setAttribute("gov.nih.nci.cabig.caaers.web.admin.AgentEditController.FORM.command", agentCommand);
		EasyMock.expect(webContext.getCurrentPage()).andReturn("something");
		EasyMock.expect(webContext.getSession()).andReturn(session).times(1);
		EasyMock.expect(agentRepository.getAgentByID(1)).andReturn(agent);
		EasyMock.expect(ctcTermDao.getById(2)).andReturn(ctcTerm);
		EasyMock.expect(webContext.forwardToString(EasyMock.isA(String.class))).andReturn("Test HTML");
		replayMocks();
		AjaxOutput out = agentAjaxFacade.addAgentSpecificTerms(1, "ctep", new int[]{2});
		assertNotNull(out);
		assertEquals("Test HTML", out.getHtmlContent());
		assertEquals(1, agentCommand.getAgentSpecificTerms().size());
	}
	
	public void testAddAgentSpecificTerms2() throws Exception{
		session.setAttribute("gov.nih.nci.cabig.caaers.web.admin.AgentEditController.FORM.command", agentCommand);
		EasyMock.expect(webContext.getCurrentPage()).andReturn("something");
		EasyMock.expect(webContext.getSession()).andReturn(session).times(1);
		EasyMock.expect(agentRepository.getAgentByID(1)).andReturn(agent);
		EasyMock.expect(lowLevelTermDao.getById(2)).andReturn(lowLevelTerm);
		EasyMock.expect(webContext.forwardToString(EasyMock.isA(String.class))).andReturn("Test HTML");
		replayMocks();
		AjaxOutput out = agentAjaxFacade.addAgentSpecificTerms(1, "meddra", new int[]{2});
		assertNotNull(out);
		assertEquals("Test HTML", out.getHtmlContent());
		assertEquals(1, agentCommand.getAgentSpecificTerms().size());
	}

}
