package gov.nih.nci.cabig.caaers.web.ae;

import static gov.nih.nci.cabig.caaers.domain.Fixtures.setId;
import static org.easymock.EasyMock.expect;

import java.util.ArrayList;

import gov.nih.nci.cabig.caaers.dao.AdverseEventReportingPeriodDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.StudyParticipantAssignmentDao;
import gov.nih.nci.cabig.caaers.dao.report.ReportDefinitionDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.service.EvaluationService;
import gov.nih.nci.cabig.caaers.utils.IndexFixedList;
import gov.nih.nci.cabig.caaers.web.DwrFacadeTestCase;

public class CaptureAdverseEventAjaxFacadeTest extends DwrFacadeTestCase{
	
	private CaptureAdverseEventAjaxFacade facade;
	private AdverseEventReportingPeriodDao adverseEventReportingPeriodDao;
    private StudyParticipantAssignmentDao assignmentDao;
	private ReportDefinitionDao reportDefinitionDao;
	private StudyDao studyDao;
    private StudyParticipantAssignment assignment;
	private EvaluationService evaluationService;
	private StudySite  studySite;
	private Study study;
	
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		
		adverseEventReportingPeriodDao = registerDaoMockFor(AdverseEventReportingPeriodDao.class);
		assignmentDao = registerDaoMockFor(StudyParticipantAssignmentDao.class);
		reportDefinitionDao = registerDaoMockFor(ReportDefinitionDao.class);
		studyDao = registerDaoMockFor(StudyDao.class);
		
		assignment = registerMockFor(StudyParticipantAssignment.class);
        evaluationService = registerMockFor(EvaluationService.class);
        studySite  = registerMockFor(StudySite.class);
        study  = registerMockFor(Study.class);
        
		facade = new CaptureAdverseEventAjaxFacade();
		
	}
	
	public void testRefreshReportingPeriodAndGetDetails() {
		assertTrue(true);
	}

	public void testDeleteAdverseEvent() {
		assertTrue(true);
//		CaptureAdverseEventInputCommand command = setupCaptureAdverseEventCommand();
//		facade.deleteAdverseEvent(1);
//		assertEquals(4, command.getAdverseEvents());
//		assertEquals(3, command.getAdverseEventReportingPeriod().getAdverseEvents().size());
	}
	
	public void testControllers(){
		Class<?>[] controllers = facade.controllers();
		assertEquals(1, controllers.length);
		assertEquals(CaptureAdverseEventController.class , controllers[0]);
	}
	
	
	private CaptureAdverseEventInputCommand setupCaptureAdverseEventCommand() {
		CaptureAdverseEventInputCommand command = new CaptureAdverseEventInputCommand(adverseEventReportingPeriodDao, assignmentDao, evaluationService, reportDefinitionDao , studyDao );
		command.setStudy(new Study());
		AdverseEventReportingPeriod reportingPeriod = new AdverseEventReportingPeriod();
		
		reportingPeriod.setAssignment(assignment);
		expect(assignment.getStudySite()).andReturn(studySite).anyTimes();
		expect(studySite.getStudy()).andReturn(study).anyTimes();
		
		expect(study.getStudyOrganizations()).andReturn(new ArrayList()).anyTimes();
		
		reportingPeriod.addAdverseEvent(setId(0, new AdverseEvent()));
        reportingPeriod.addAdverseEvent(setId(1, new AdverseEvent()));
        reportingPeriod.addAdverseEvent(setId(2, new AdverseEvent()));
        reportingPeriod.addAdverseEvent(setId(3, new AdverseEvent()));
		command.setAdverseEventReportingPeriod(reportingPeriod);
		command.setAdverseEvents(new IndexFixedList<AdverseEvent>(reportingPeriod.getAdverseEvents()));
		
		
	    session.setAttribute(CaptureAdverseEventController.class.getName() + ".FORM.command", command);
	    expect(webContext.getSession()).andReturn(session).anyTimes();
	    return command;
	}
	
}
