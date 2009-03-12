package gov.nih.nci.cabig.caaers.web.ae;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.easymock.classextension.EasyMock;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyFundingSponsor;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.service.EvaluationService;
import gov.nih.nci.cabig.caaers.utils.IndexFixedList;
import gov.nih.nci.cabig.caaers.web.RenderDecisionManager;
import gov.nih.nci.cabig.caaers.web.RenderDecisionManagerFactoryBean;
import gov.nih.nci.cabig.caaers.web.WebTestCase;

/**
 * 
 * @author Biju Joseph
 *
 */
public class CaptureAdverseEventControllerTest extends WebTestCase {
	
	CaptureAdverseEventInputCommand command;
	CaptureAdverseEventController controller;
	BindException errors;
	EvaluationService evaluationService;
	
	final RenderDecisionManager renderDecisionManager = new RenderDecisionManager();
	
	protected void setUp() throws Exception {
		super.setUp();
		evaluationService = registerMockFor(EvaluationService.class);
		command  = new CaptureAdverseEventInputCommand();
		command.setSelectedAesMap( new HashMap<Integer, Boolean>());
		command.setAllReportDefinitions(new ArrayList<ReportDefinition>());
		command.setReportDefinitionMap( new HashMap<Integer, Boolean>());
		command.setRequiredReportDefinitionsMap(new HashMap<ReportDefinition, List<AdverseEvent>>());
		command.setRequiredReportDefinitionIndicatorMap(new HashMap<Integer, Boolean>());
		command.setReportStatusMap(new HashMap<Integer, String>());
		command.setEvaluationService(evaluationService);
		
		controller = new CaptureAdverseEventController();
		controller.setRenderDecisionManagerFactoryBean(new RenderDecisionManagerFactoryBean(){
			@Override
			public RenderDecisionManager getRenderDecisionManager() {
				return renderDecisionManager;
			}
		});
		errors = new BindException(command,"command");
		request.setParameter(CaptureAdverseEventController.PARAM_PAGE, "2");
	}
	
	/**
	 * Tests the binding of certain variables into the command.
	 */
	public void testOnBindHttpServletRequestObjectBindException() throws Exception{
		request.setParameter("_action", "joel");
		request.setParameter("displayReportingPeriod", "true");
		request.setParameter("adverseEventReportingPeriod", "5");
		controller.onBind(request, command, errors);
		assertEquals("joel", command.get_action());
	}
	/**
	 * Tests the binding of certain variables into the command.
	 */
	public void testOnBindHttpServletRequestObjectBindException_ReportingPeriodNotExsist() throws Exception{
		request.setParameter("_action", "joel");
		request.setParameter("displayReportingPeriod", "true");
		request.setParameter("adverseEventReportingPeriod", "5");
		
		command.setAssignment(new StudyParticipantAssignment());
		controller.onBind(request, command, errors);
		assertEquals("joel", command.get_action());
		assertNull(command.getAssignment());
	}
	
	/**
	 * Tests the binding of certain variables into the command.
	 */
	public void testOnBindHttpServletRequestObjectBindException_ReportingPeriodExsist() throws Exception{
		request.setParameter("_action", "joel");
		request.setParameter("displayReportingPeriod", "true");
		request.setParameter("adverseEventReportingPeriod", "5");
		
		AdverseEventReportingPeriod reportingPeriod = Fixtures.createReportingPeriod();
		Study s = reportingPeriod.getStudy();
		Fixtures.createCtcV3Terminology(s);
		
		command.setAdverseEventReportingPeriod(reportingPeriod);
		controller.onBind(request, command, errors);
		assertEquals("joel", command.get_action());
		assertSame(reportingPeriod.getAssignment(), command.getAssignment());
	}
	/**
	 * This method tests {@link CaptureAdverseEventController#referenceData(javax.servlet.http.HttpServletRequest, Object, org.springframework.validation.Errors, int)}
	 * @throws Exception
	 */
	public void testReferenceData_CheckSummary() throws Exception{
		Participant participant = Fixtures.createParticipant("Joel", "Joseph");
		command.setParticipant(participant);
		Study study = Fixtures.createStudy("Test");
		study.setAdeersReporting(true);
		Organization org = Fixtures.createOrganization("Test Org", "ABCD");
		StudyFundingSponsor sponsor = Fixtures.createStudyFundingSponsor(org);
		sponsor.setPrimary(true);
		study.getStudyOrganizations().add(sponsor);
		command.setStudy(study);
		AdverseEventReportingPeriod reportingPeriod = Fixtures.createReportingPeriod(1, "11/11/2008", "11/14/2008");
		command.setAdverseEventReportingPeriod(reportingPeriod);
		command.setAssignment(reportingPeriod.getAssignment());
		
		EasyMock.expect(evaluationService.applicableReportDefinitions(command.getAssignment())).andReturn(new ArrayList<ReportDefinition>());
		EasyMock.expect(evaluationService.findRequiredReportDefinitions(command.getAdverseEventReportingPeriod())).andReturn(new HashMap<ReportDefinition, List<AdverseEvent>>());
		replayMocks();
		Map refDataMap = controller.referenceData(request, command, errors, 2);
		assertNotNull(refDataMap);
		assertTrue(refDataMap.containsKey("routineAeSummary"));
		Map<String, String> summary = (Map<String, String>)refDataMap.get("routineAeSummary");
		assertEquals("Joel Joseph", summary.get("Participant"));
		assertEquals("Start Date: 12/11/08", summary.get("Course"));
		verifyMocks();
	}
	/**
	 * This method tests {@link CaptureAdverseEventController#referenceData(javax.servlet.http.HttpServletRequest, Object, org.springframework.validation.Errors, int)}
	 * @throws Exception
	 */
	public void testReferenceData_ForRenderDecision() throws Exception{
		Participant participant = Fixtures.createParticipant("Joel", "Joseph");
		command.setParticipant(participant);
		Study study = Fixtures.createStudy("Test");
		study.setAdeersReporting(true);
		Organization org = Fixtures.createOrganization("Test Org", "ABCD");
		StudyFundingSponsor sponsor = Fixtures.createStudyFundingSponsor(org);
		sponsor.setPrimary(true);
		study.getStudyOrganizations().add(sponsor);
		command.setStudy(study);
		AdverseEventReportingPeriod reportingPeriod = Fixtures.createReportingPeriod(1, "11/11/2008", "11/14/2008");
		command.setAdverseEventReportingPeriod(reportingPeriod);
		command.setAssignment(reportingPeriod.getAssignment());
		
		EasyMock.expect(evaluationService.applicableReportDefinitions(command.getAssignment())).andReturn(new ArrayList<ReportDefinition>());
		EasyMock.expect(evaluationService.findRequiredReportDefinitions(command.getAdverseEventReportingPeriod())).andReturn(new HashMap<ReportDefinition, List<AdverseEvent>>());
		replayMocks();
		
		Map refDataMap = controller.referenceData(request, command, errors, 2);
		assertNotNull(refDataMap);
		assertFalse(renderDecisionManager.canRenderField("adverseEvents[].serious", request, response));
		assertFalse(renderDecisionManager.canRenderField("adverseEventReportingPeriod.evaluatedAdverseEvents[].displaySerious", request, response));
		verifyMocks();
	}
	/**
	 * This method tests {@link CaptureAdverseEventController#referenceData(javax.servlet.http.HttpServletRequest, Object, org.springframework.validation.Errors, int)}
	 * @throws Exception
	 */
	public void testReferenceData_ForRenderDecisionOnDCPAdEERSStudy() throws Exception{
		Participant participant = Fixtures.createParticipant("Joel", "Joseph");
		command.setParticipant(participant);
		Study study = Fixtures.createStudy("Test");
		study.setAdeersReporting(false);
		Organization org = Fixtures.createOrganization("Test Org", "DCP");
		StudyFundingSponsor sponsor = Fixtures.createStudyFundingSponsor(org);
		sponsor.setPrimary(true);
		study.getStudyOrganizations().add(sponsor);
		command.setStudy(study);
		AdverseEventReportingPeriod reportingPeriod = Fixtures.createReportingPeriod(1, "11/11/2008", "11/14/2008");
		command.setAdverseEventReportingPeriod(reportingPeriod);
		command.setAssignment(reportingPeriod.getAssignment());
		
		EasyMock.expect(evaluationService.applicableReportDefinitions(command.getAssignment())).andReturn(new ArrayList<ReportDefinition>());
		EasyMock.expect(evaluationService.findRequiredReportDefinitions(command.getAdverseEventReportingPeriod())).andReturn(new HashMap<ReportDefinition, List<AdverseEvent>>());
		replayMocks();
		
		Map refDataMap = controller.referenceData(request, command, errors, 2);
		assertNotNull(refDataMap);
		assertTrue(renderDecisionManager.canRenderField("adverseEvents[].serious", request, response));
		assertTrue(renderDecisionManager.canRenderField("adverseEventReportingPeriod.evaluatedAdverseEvents[].displaySerious", request, response));
		verifyMocks();
	}
	
	/**
	 * This method tests {@link CaptureAdverseEventController#processFinish(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, Object, BindException)}
	 */
	public void testProcessFinishInvalidActionParameter() throws Exception{
		command.setPrimaryAdverseEventId(5);
		Participant participant = Fixtures.createParticipant("Joel", "Joseph");
		participant.setId(5);
		command.setParticipant(participant);
		Study study = Fixtures.createStudy("Test");
		study.setId(5);
		command.setStudy(study);
		command.setAdverseEvents(new IndexFixedList<AdverseEvent>(new ArrayList<AdverseEvent>()));
		
		request.setAttribute("_action", "abcd");
		request.setAttribute("_reportId","5");
		
		ModelAndView mv = controller.processFinish(request, response, command, errors);
		assertEquals(5, mv.getModel().get("aeReport"));
		assertEquals(5, mv.getModel().get("study"));
		assertEquals(5, mv.getModel().get("participant"));
		
		assertEquals("abcd",session.getAttribute("action"));
		assertNotNull(session.getAttribute("adverseEventList"));
		assertNotNull(session.getAttribute("reportDefnList"));
		assertEquals(5, session.getAttribute("primaryAEId"));
	}
	
	/**
	 * This method tests {@link CaptureAdverseEventController#processFinish(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, Object, BindException)}
	 */
	public void testProcessFinishCreateActionParameter() throws Exception{
		command.setPrimaryAdverseEventId(5);
		Participant participant = Fixtures.createParticipant("Joel", "Joseph");
		participant.setId(5);
		command.setParticipant(participant);
		Study study = Fixtures.createStudy("Test");
		study.setId(5);
		command.setStudy(study);
		command.setAdverseEvents(new IndexFixedList<AdverseEvent>(new ArrayList<AdverseEvent>()));
		
		request.setAttribute("_action", "createNew");
		request.setAttribute("_reportId","5");
		
		ModelAndView mv = controller.processFinish(request, response, command, errors);
		assertNull(mv.getModel().get("aeReport"));
		assertNull(session.getAttribute("aeReportId"));
	}
	
	/**
	 * This method tests {@link CaptureAdverseEventController#processFinish(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, Object, BindException)}
	 */
	public void testProcessFinishAmendActionParameter() throws Exception{
		command.setPrimaryAdverseEventId(5);
		Participant participant = Fixtures.createParticipant("Joel", "Joseph");
		participant.setId(5);
		command.setParticipant(participant);
		Study study = Fixtures.createStudy("Test");
		study.setId(5);
		command.setStudy(study);
		command.setAdverseEvents(new IndexFixedList<AdverseEvent>(new ArrayList<AdverseEvent>()));
		
		request.setAttribute("_action", "amendReport");
		request.setAttribute("_reportId","5");
		
		ModelAndView mv = controller.processFinish(request, response, command, errors);
		assertEquals(5, mv.getModel().get("aeReport"));
		assertEquals(5, session.getAttribute("aeReportId"));
	}
}
