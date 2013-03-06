/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.admin;


import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.dao.CtcDao;
import gov.nih.nci.cabig.caaers.dao.MeddraVersionDao;
import gov.nih.nci.cabig.caaers.domain.Agent;
import gov.nih.nci.cabig.caaers.domain.AgentSpecificCtcTerm;
import gov.nih.nci.cabig.caaers.domain.AgentSpecificMeddraLowLevelTerm;
import gov.nih.nci.cabig.caaers.domain.AgentSpecificTerm;
import gov.nih.nci.cabig.caaers.domain.Ctc;
import gov.nih.nci.cabig.caaers.domain.CtcCategory;
import gov.nih.nci.cabig.caaers.domain.CtcTerm;
import gov.nih.nci.cabig.caaers.domain.MeddraVersion;
import gov.nih.nci.cabig.caaers.domain.Term;
import gov.nih.nci.cabig.caaers.domain.meddra.LowLevelTerm;
import gov.nih.nci.cabig.caaers.domain.repository.AgentRepository;
import gov.nih.nci.cabig.caaers.service.AgentSpecificAdverseEventListService;
import gov.nih.nci.cabig.caaers.web.AbstractAjaxFacade;
import gov.nih.nci.cabig.ctms.web.tabs.Tab;

import java.util.ArrayList;
import java.util.List;

import org.easymock.classextension.EasyMock;
import org.springframework.mock.web.MockHttpServletRequest;

public class AgentEditControllerTest extends AbstractTestCase {
	
	private AgentEditController agentEditController;
	private AgentRepository agentRepository;
	private MockHttpServletRequest request;
	private AgentSpecificAdverseEventListService agentSpecificAdverseEventListService;
	private CtcDao ctcDao;
	private MeddraVersionDao meddraVersionDao;
	private AgentSpecificCtcTerm agentSpecificCtcTerm;
	private CtcTerm ctcTerm;
	private CtcCategory ctcCategory;
	private Ctc ctc;
	private List<AgentSpecificTerm> list;
	private AgentSpecificMeddraLowLevelTerm agentSpecificMeddraLowLevelTerm;
	private LowLevelTerm lowLevelTerm;
	private MeddraVersion meddraVersion;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		agentEditController = new AgentEditController();
		agentRepository = registerMockFor(AgentRepository.class);
		agentSpecificAdverseEventListService = registerMockFor(AgentSpecificAdverseEventListService.class);
		ctcDao = registerMockFor(CtcDao.class);
		meddraVersionDao = registerMockFor(MeddraVersionDao.class);
		agentEditController.setAgentRepository(agentRepository);
		agentEditController.setService(agentSpecificAdverseEventListService);
		agentEditController.setCtcDao(ctcDao);
		agentEditController.setMeddraVersionDao(meddraVersionDao);
		request = new MockHttpServletRequest();
		agentSpecificCtcTerm = new AgentSpecificCtcTerm();
		agentSpecificCtcTerm.setId(1);
		ctcTerm = registerMockFor(CtcTerm.class);
		ctcCategory = registerMockFor(CtcCategory.class);
		ctc = registerMockFor(Ctc.class);
		agentSpecificCtcTerm.setTerm(ctcTerm);
		list = new ArrayList<AgentSpecificTerm>();
		agentSpecificMeddraLowLevelTerm = new AgentSpecificMeddraLowLevelTerm();
		agentSpecificMeddraLowLevelTerm.setId(1);
		lowLevelTerm = registerMockFor(LowLevelTerm.class);
		meddraVersion = registerMockFor(MeddraVersion.class);
		agentSpecificMeddraLowLevelTerm.setTerm(lowLevelTerm);
	}

	public void testFormBackingObject1() throws Exception{
		request.setParameter("agentID", "1");
		Agent agent = new Agent();
		agent.setId(1);
		EasyMock.expect(agentRepository.getAgentByID(1)).andReturn(agent);
		EasyMock.expect(agentSpecificAdverseEventListService.getListByAgent(1)).andReturn(new ArrayList<AgentSpecificTerm>());
		replayMocks();
		AgentCommand ac = (AgentCommand)agentEditController.formBackingObject(request);
		assertNotNull(ac);
		assertNull(ac.getCtcVersion());
		assertNull(ac.getTerminology());
		verifyMocks();
	}
	
	public void testFormBackingObject2() throws Exception{
		request.setParameter("agentID", "1");
		Agent agent = new Agent();
		agent.setId(1);
		EasyMock.expect(agentRepository.getAgentByID(1)).andReturn(agent);
		list.add(agentSpecificCtcTerm);
		EasyMock.expect(agentSpecificAdverseEventListService.getListByAgent(1)).andReturn(list);
		expectCtcDao();
		replayMocks();
		AgentCommand ac = (AgentCommand)agentEditController.formBackingObject(request);
		assertNotNull(ac);
		assertEquals(ctc, ac.getCtcVersion());
		assertEquals(Term.CTC, ac.getTerminology());
		verifyMocks();
	}
	
	public void testFormBackingObject3() throws Exception{
		request.setParameter("agentID", "1");
		Agent agent = new Agent();
		agent.setId(1);
		EasyMock.expect(agentRepository.getAgentByID(1)).andReturn(agent);
		list.add(agentSpecificMeddraLowLevelTerm);
		EasyMock.expect(agentSpecificAdverseEventListService.getListByAgent(1)).andReturn(list);
		expectMeddraVersionDao();
		replayMocks();
		AgentCommand ac = (AgentCommand)agentEditController.formBackingObject(request);
		assertNotNull(ac);
		assertEquals(meddraVersion, ac.getMeddraVersion());
		assertEquals(Term.MEDDRA, ac.getTerminology());
		verifyMocks();
	}
	
	public void testFormBackingObject4() throws Exception{
		request.setParameter("agentID", "1");
		request.setParameter("showSuccessMessage", "true");
		Agent agent = new Agent();
		agent.setId(1);
		EasyMock.expect(agentRepository.getAgentByID(1)).andReturn(agent);
		EasyMock.expect(agentSpecificAdverseEventListService.getListByAgent(1)).andReturn(new ArrayList<AgentSpecificTerm>());
		replayMocks();
		AgentCommand ac = (AgentCommand)agentEditController.formBackingObject(request);
		assertNotNull(ac);
		assertNull(ac.getCtcVersion());
		assertNull(ac.getTerminology());
		assertEquals("Information saved successfully", request.getAttribute("flashMessage"));
		verifyMocks();
	}
	
	public void testShouldSave(){
		assertTrue(agentEditController.shouldSave(request, new AgentCommand(), new Tab<AgentCommand>()));
		request.setParameter(AbstractAjaxFacade.AJAX_REQUEST, "true");
		assertFalse(agentEditController.shouldSave(request, new AgentCommand(), new Tab<AgentCommand>()));
	}
	
	public void testGetViewName(){
		request.setParameter(AbstractAjaxFacade.AJAX_SUBVIEW_PARAMETER, "something");
		assertEquals("admin/ajax/something", agentEditController.getViewName(request, new AgentCommand(), 1));
	}
	
	private void expectCtcDao(){
		EasyMock.expect(ctcTerm.getCategory()).andReturn(ctcCategory);
		EasyMock.expect(ctcCategory.getCtc()).andReturn(ctc);
		EasyMock.expect(ctc.getId()).andReturn(1);
		EasyMock.expect(ctcDao.getById(1)).andReturn(ctc);
	}
	
	private void expectMeddraVersionDao(){
		EasyMock.expect(lowLevelTerm.getMeddraVersion()).andReturn(meddraVersion);
		EasyMock.expect(meddraVersion.getId()).andReturn(1);
		EasyMock.expect(meddraVersionDao.getById(1)).andReturn(meddraVersion);
	}

}
