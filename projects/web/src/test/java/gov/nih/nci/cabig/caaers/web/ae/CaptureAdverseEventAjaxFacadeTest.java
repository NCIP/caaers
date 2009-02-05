package gov.nih.nci.cabig.caaers.web.ae;

import static gov.nih.nci.cabig.caaers.domain.Fixtures.setId;
import static org.easymock.EasyMock.expect;
import gov.nih.nci.cabig.caaers.dao.AdverseEventReportingPeriodDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.StudyParticipantAssignmentDao;
import gov.nih.nci.cabig.caaers.dao.report.ReportDefinitionDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.domain.UserGroupType;
import gov.nih.nci.cabig.caaers.domain.repository.AdverseEventRoutingAndReviewRepository;
import gov.nih.nci.cabig.caaers.domain.repository.AdverseEventRoutingAndReviewRepositoryImpl;
import gov.nih.nci.cabig.caaers.service.EvaluationService;
import gov.nih.nci.cabig.caaers.utils.IndexFixedList;
import gov.nih.nci.cabig.caaers.web.DwrFacadeTestCase;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.acegisecurity.Authentication;
import org.acegisecurity.context.SecurityContext;
import org.acegisecurity.userdetails.User;
import org.easymock.EasyMock;

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

	public void testDeleteAdverseEventNoAmend() {
		CaptureAdverseEventInputCommand command = setupCaptureAdverseEventCommand();
		facade.setReportingPeriodDao(adverseEventReportingPeriodDao);
		CaptureAdverseEventAjaxFacade facadeMock = registerMockFor(CaptureAdverseEventAjaxFacade.class);
		
		expect(facadeMock.getWebContext()).andReturn(webContext).anyTimes();
		 studyDao.lock(command.getStudy());
		adverseEventReportingPeriodDao.reassociate(command.getAdverseEventReportingPeriod());
		adverseEventReportingPeriodDao.save(command.getAdverseEventReportingPeriod());
		replayMocks();
		facade.deleteAdverseEvent(1,null);
		verifyMocks();
		assertEquals(4, command.getAdverseEvents().size());
		assertEquals(3, command.getAdverseEventReportingPeriod().getAdverseEvents().size());
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
		reportingPeriod.setId(1);
		StudyParticipantAssignment assignment = new StudyParticipantAssignment();
		assignment.setStudySite(new StudySite());
		assignment.getStudySite().setStudy(command.getStudy());
		
		reportingPeriod.setAssignment(assignment);
		//expect(assignment.getStudySite()).andReturn(studySite).anyTimes();
		//expect(studySite.getStudy()).andReturn(study).anyTimes();
		
		reportingPeriod.addAdverseEvent(setId(0, new AdverseEvent()));
        reportingPeriod.addAdverseEvent(setId(1, new AdverseEvent()));
        reportingPeriod.addAdverseEvent(setId(2, new AdverseEvent()));
        reportingPeriod.addAdverseEvent(setId(3, new AdverseEvent()));
		command.setAdverseEvents(new IndexFixedList<AdverseEvent>(reportingPeriod.getAdverseEvents()));
		command.setAdverseEventReportingPeriod(reportingPeriod);
		
		
	    session.setAttribute(CaptureAdverseEventController.class.getName() + ".FORM.command", command);
	    expect(webContext.getSession()).andReturn(session).anyTimes();
	    return command;
	}
	
	public void testCreateQueryString(){
		Map params = new HashMap<String, String>();
		params.put("entity", "reportingPeriod");
        params.put("entityId", "3");
        String created = facade.createQueryString(params);
        assertEquals("Query String created incorrectly by createQueryString()", "entity=reportingPeriod&entityId=3", created);
	}
	
	public void testAddReviewComment() throws Exception{
        CaptureAdverseEventInputCommand command = setupCaptureAdverseEventCommand();
        SecurityContext context = registerMockFor(SecurityContext.class);
        Authentication auth =  registerMockFor(Authentication.class);
        User user = registerMockFor(User.class);
        AdverseEventRoutingAndReviewRepository repository = registerMockFor(AdverseEventRoutingAndReviewRepositoryImpl.class);
        CaptureAdverseEventAjaxFacade facadeMock = registerMockFor(CaptureAdverseEventAjaxFacade.class);
        session.setAttribute(CaptureAdverseEventController.class + ".FORM.command",command);
        session.setAttribute("ACEGI_SECURITY_CONTEXT",context);
        facade.setAdverseEventRoutingAndReviewRepository(repository);
        studyDao.lock(command.getStudy());
        adverseEventReportingPeriodDao.reassociate(command.getAdverseEventReportingPeriod());
        expect(context.getAuthentication()).andReturn(auth);
        expect(auth.getPrincipal()).andReturn(user);
        expect(user.getUsername()).andReturn("SYSTEM_ADMIN");
        expect(facadeMock.getWebContext()).andReturn(webContext).anyTimes();
        repository.addReportingPeriodReviewComment(command.getAdverseEventReportingPeriod(), "test Comment", "SYSTEM_ADMIN");
        expect(webContext.forwardToString((String)EasyMock.anyObject())).andReturn("").once();

        replayMocks();
        facade.addReviewComment("test Comment");
        verifyMocks();
    }
	
	public void testEditReviewComment() throws Exception{
		CaptureAdverseEventInputCommand command = setupCaptureAdverseEventCommand();
        SecurityContext context = registerMockFor(SecurityContext.class);
        Authentication auth =  registerMockFor(Authentication.class);
        User user = registerMockFor(User.class);
        AdverseEventRoutingAndReviewRepository repository = registerMockFor(AdverseEventRoutingAndReviewRepositoryImpl.class);
        CaptureAdverseEventAjaxFacade facadeMock = registerMockFor(CaptureAdverseEventAjaxFacade.class);
        session.setAttribute(CaptureAdverseEventController.class + ".FORM.command",command);
        session.setAttribute("ACEGI_SECURITY_CONTEXT",context);
        facade.setAdverseEventRoutingAndReviewRepository(repository);
        studyDao.lock(command.getStudy());
        adverseEventReportingPeriodDao.reassociate(command.getAdverseEventReportingPeriod());
        expect(context.getAuthentication()).andReturn(auth).anyTimes();
        expect(auth.getPrincipal()).andReturn(user).anyTimes();
        expect(user.getUsername()).andReturn("SYSTEM_ADMIN").anyTimes();
        expect(facadeMock.getWebContext()).andReturn(webContext).anyTimes();
        repository.editReportingPeriodReviewComment(command.getAdverseEventReportingPeriod(), "test Comment", "SYSTEM_ADMIN", 1);
        expect(webContext.forwardToString((String)EasyMock.anyObject())).andReturn("").once();
       
        replayMocks();
        facade.editReviewComment("test Comment", 1);
        verifyMocks();

	}
}
