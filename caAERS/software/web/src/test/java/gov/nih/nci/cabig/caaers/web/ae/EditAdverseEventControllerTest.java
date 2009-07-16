package gov.nih.nci.cabig.caaers.web.ae;

import static gov.nih.nci.cabig.caaers.domain.Fixtures.setId;
import gov.nih.nci.cabig.caaers.dao.StudyParticipantAssignmentDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.Grade;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportTree;
import gov.nih.nci.cabig.caaers.service.EvaluationService;
import gov.nih.nci.cabig.caaers.web.RenderDecisionManager;
import gov.nih.nci.cabig.caaers.web.WebTestCase;

import java.util.ArrayList;
import java.util.List;

import org.easymock.classextension.EasyMock;
/**
 * 
 * @author Biju Joseph
 *
 */
public class EditAdverseEventControllerTest extends WebTestCase {
	
	private EditExpeditedAdverseEventCommand command;
	private StudyParticipantAssignmentDao assignmentDao;
	private StudyParticipantAssignment assignment;
	private StudySite studySite;
	private EditAdverseEventController controller;
	private EvaluationService evaluationService;
	final RenderDecisionManager renderDecisionManager = new RenderDecisionManager();
	
	protected void setUp() throws Exception {
		super.setUp();
		assignment = registerMockFor(StudyParticipantAssignment.class);
		studySite = registerMockFor(StudySite.class);
		assignmentDao = registerDaoMockFor(StudyParticipantAssignmentDao.class);
		command = new EditExpeditedAdverseEventCommand(null, null, assignmentDao, null, 
								new ExpeditedReportTree(),renderDecisionManager, null, null, null );
		//Setup the command 
		ExpeditedAdverseEventReport report = new ExpeditedAdverseEventReport();
	     report.addAdverseEvent(setId(0, new AdverseEvent()));
	     report.addAdverseEvent(setId(1, new AdverseEvent()));
	     report.addAdverseEvent(setId(2, new AdverseEvent()));
	     report.addAdverseEvent(setId(3, new AdverseEvent()));
	     
	     AdverseEventReportingPeriod reportingPeriod = new AdverseEventReportingPeriod();
	     reportingPeriod.setAssignment(assignment);
	     report.setReportingPeriod(reportingPeriod);
         reportingPeriod.addAeReport(report);
	    command.setAeReport(report);
	    
	    evaluationService = registerMockFor(EvaluationService.class);
		controller = new EditAdverseEventController();
		controller.setEvaluationService(evaluationService);
	}
	
	public void testSupressValidation(){
		Study study = Fixtures.createStudy("test");
		Fixtures.createCtcV3Terminology(study);
		EasyMock.expect(assignment.getStudySite()).andReturn(studySite).anyTimes();
		EasyMock.expect(studySite.getStudy()).andReturn(study).anyTimes();
		request.setAttribute("gov.nih.nci.cabig.caaers.web.ae.EditAdverseEventController.PAGE.command", 9);
		replayMocks();
		boolean supressValidation = controller.suppressValidation(request, command);
		assertFalse(supressValidation);
		
		//for attribution it should be true
		request.setAttribute("gov.nih.nci.cabig.caaers.web.ae.EditAdverseEventController.PAGE.command", 8);
		supressValidation = controller.suppressValidation(request, command);
		assertTrue(supressValidation);
		verifyMocks();
	}
	
	public void testOnBind_From_CaptureAE(){
		//BJ : need to fill the testcase once the logic is final
		assertTrue(true); 
	}
	
	public void testOnBind_From_ManageReports(){
		//BJ : need to fill the testcase once the logic is final
		assertTrue(true); 
	}

}
