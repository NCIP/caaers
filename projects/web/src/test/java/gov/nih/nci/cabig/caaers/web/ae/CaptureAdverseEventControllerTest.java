package gov.nih.nci.cabig.caaers.web.ae;

import org.springframework.validation.BindException;

import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
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
	
	protected void setUp() throws Exception {
		super.setUp();
		command  = new CaptureAdverseEventInputCommand();
		controller = new CaptureAdverseEventController();
		errors = new BindException(command,"command");
	}
	
	/**
	 * Tests the binding of certain variables into the command.
	 */
	public void testOnBindHttpServletRequestObjectBindException() throws Exception{
		request.setParameter(CaptureAdverseEventController.PARAM_PAGE, "2");
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
		request.setParameter(CaptureAdverseEventController.PARAM_PAGE, "2");
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
		request.setParameter(CaptureAdverseEventController.PARAM_PAGE, "2");
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
	
	public void testReferenceData(){
		fail("not implemented");
	}
	
	public void testProcessFinish(){
		fail("not impl");
	}
}
