package gov.nih.nci.cabig.caaers.web.ae;

import static gov.nih.nci.cabig.caaers.domain.Fixtures.setId;
import static org.easymock.EasyMock.expect;
import gov.nih.nci.cabig.caaers.dao.AdverseEventReportingPeriodDao;
import gov.nih.nci.cabig.caaers.dao.ExpeditedAdverseEventReportDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.StudyParticipantAssignmentDao;
import gov.nih.nci.cabig.caaers.dao.report.ReportDefinitionDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.domain.repository.AdverseEventRoutingAndReviewRepository;
import gov.nih.nci.cabig.caaers.domain.repository.AdverseEventRoutingAndReviewRepositoryImpl;
import gov.nih.nci.cabig.caaers.service.EvaluationService;
import gov.nih.nci.cabig.caaers.utils.IndexFixedList;
import gov.nih.nci.cabig.caaers.web.DwrFacadeTestCase;
import gov.nih.nci.cabig.caaers.web.dwr.AjaxOutput;
import gov.nih.nci.cabig.caaers.web.validation.validator.AdverseEventReportingPeriodValidator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.acegisecurity.Authentication;
import org.acegisecurity.context.SecurityContext;
import org.acegisecurity.userdetails.User;
import org.easymock.EasyMock;
import org.springframework.validation.Errors;

public class CaptureAdverseEventAjaxFacadeTest extends DwrFacadeTestCase{
	
	private CaptureAdverseEventAjaxFacade facade;
	private AdverseEventReportingPeriodDao adverseEventReportingPeriodDao;
    private StudyParticipantAssignmentDao assignmentDao;
	private ReportDefinitionDao reportDefinitionDao;
	private ExpeditedAdverseEventReportDao aeReportDao;
	private StudyDao studyDao;
    private StudyParticipantAssignment assignment;
	private EvaluationService evaluationService;
	private StudySite  studySite;
	private Study study;
	private AdverseEventReportingPeriodValidator adverseEventReportingPeriodValidator;
	
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		
		adverseEventReportingPeriodDao = registerDaoMockFor(AdverseEventReportingPeriodDao.class);
		assignmentDao = registerDaoMockFor(StudyParticipantAssignmentDao.class);
		reportDefinitionDao = registerDaoMockFor(ReportDefinitionDao.class);
		studyDao = registerDaoMockFor(StudyDao.class);
		aeReportDao = registerDaoMockFor(ExpeditedAdverseEventReportDao.class);
		
		assignment = registerMockFor(StudyParticipantAssignment.class);
        evaluationService = registerMockFor(EvaluationService.class);
        studySite  = registerMockFor(StudySite.class);
        study  = registerMockFor(Study.class);
        
		facade = new CaptureAdverseEventAjaxFacade();
		
	}
	
	public void testRefreshReportingPeriodAndGetDetails() {
		assertTrue(true);
	}
	
	/**
	 * Should only mark the AE as retired. 
	 * Should  save  reporting period.
	 * 
	 */
	public void testDeleteAdverseEvent(){
		CaptureAdverseEventInputCommand command = setupCaptureAdverseEventCommand();
		facade.setReportingPeriodDao(adverseEventReportingPeriodDao);
		
		assertFalse(command.getAdverseEventReportingPeriod().getAdverseEvents().get(1).isRetired());

		adverseEventReportingPeriodDao.save(command.getAdverseEventReportingPeriod());
		replayMocks();
		
		AjaxOutput output = facade.deleteAdverseEvent(1, "");
		assertTrue(command.getAdverseEventReportingPeriod().getAdverseEvents().get(1).isRetired());
		assertFalse(output.getError());
		verifyMocks();
	}
	
	public void testControllers(){
		Class<?>[] controllers = facade.controllers();
		assertEquals(1, controllers.length);
		assertEquals(CaptureAdverseEventController.class , controllers[0]);
	}
	
	private CaptureAdverseEventInputCommand setupCaptureAdverseEventCommand() {
		CaptureAdverseEventInputCommand command = new CaptureAdverseEventInputCommand(adverseEventReportingPeriodDao, assignmentDao, evaluationService, reportDefinitionDao , studyDao, aeReportDao);
		command.setStudy(new Study());
		AdverseEventReportingPeriod reportingPeriod = new AdverseEventReportingPeriod();
		reportingPeriod.setId(1);
		StudyParticipantAssignment assignment = new StudyParticipantAssignment();
		assignment.setStudySite(new StudySite());
		assignment.getStudySite().setStudy(command.getStudy());
		
		reportingPeriod.setAssignment(assignment);
		//expect(assignment.getStudySite()).andReturn(studySite).anyTimes();
		//expect(studySite.getStudy()).andReturn(study).anyTimes();
		AdverseEvent ae0 = new AdverseEvent();
		Fixtures.createAdverseEventCtcTerm(ae0, Fixtures.createCtcTerm("a0", "c0"));
		reportingPeriod.addAdverseEvent(setId(0, ae0));
		
		AdverseEvent ae1 = new AdverseEvent();
		Fixtures.createAdverseEventCtcTerm(ae1, Fixtures.createCtcTerm("a1", "c1"));
		reportingPeriod.addAdverseEvent(setId(1, ae1));
		
		AdverseEvent ae2 = new AdverseEvent();
		Fixtures.createAdverseEventCtcTerm(ae2, Fixtures.createCtcTerm("a2", "c2"));
		reportingPeriod.addAdverseEvent(setId(2, ae2));
		
		AdverseEvent ae3 = new AdverseEvent();
		Fixtures.createAdverseEventCtcTerm(ae3, Fixtures.createCtcTerm("a3", "c3"));
		reportingPeriod.addAdverseEvent(setId(3, ae3));
		
		
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
	
	/**
	 * This method tests the case when there is a validation error.
	 * The adverseEventReportingPeriodValidator injected into the facade is an instance of the inner Validator class that creates
	 * an error in its validate() method.
	 * The test method checks the AjaxOutput method and confirms that it has an error populated in it correctly.
	 * @throws Exception
	 */
	public void testValidateAndAdvanceWorkflowWithErrors() throws Exception{
		CaptureAdverseEventInputCommand command = setupCaptureAdverseEventCommand();
		adverseEventReportingPeriodValidator = new AdverseEventReportingPeriodValidator(){
			public void validate(Object obj, Errors e) {
				AdverseEventReportingPeriod adverseEventReportingPeriod = (AdverseEventReportingPeriod) obj;
				e.reject("test error");
			}
		};
		facade.setAdverseEventReportingPeriodValidator(adverseEventReportingPeriodValidator);
		adverseEventReportingPeriodDao.reassociate(command.getAdverseEventReportingPeriod());
		studyDao.lock(command.getStudy());
		replayMocks();
		AjaxOutput output = facade.validateAndAdvanceWorkflow("Submit to Data Coordinator");
		assertNotNull("AjaxOutput not populated with errors", output);
		ArrayList<String> errorList = (ArrayList<String>)output.getObjectContent();
		assertEquals("Incorrect number of errors populated in the ajaxOutput object", 1, errorList.size());
		verifyMocks();
	}
	
	/**
	 * This method tests the case when there is no validation error.
	 * The adverseEventReportingPeriodValidator injected into the facade is an instance of the inner Validator class that returns 
	 * no error in its validate() method.
	 * The test method checks that the objectContent attribute of the AjaxOutput object returned is null.
	 * @throws Exception
	 */
	public void testValidateAndAdvanceWorkflowWithNoErros() throws Exception{
		CaptureAdverseEventInputCommand command = setupCaptureAdverseEventCommand();
		adverseEventReportingPeriodValidator = new AdverseEventReportingPeriodValidator(){
			public void validate(Object obj, Errors e) {
			}
		};
		facade.setAdverseEventReportingPeriodValidator(adverseEventReportingPeriodValidator);
		adverseEventReportingPeriodDao.reassociate(command.getAdverseEventReportingPeriod());
		studyDao.lock(command.getStudy());
		replayMocks();
		AjaxOutput output = facade.validateAndAdvanceWorkflow("Submit to Data Coordinator");
		assertNull("ObjectContent populated incorrectly when there were no errors", output.getObjectContent());
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
	
	public void testDeleteReviewComment() throws Exception{
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
        repository.deleteReportingPeriodReviewComment(command.getAdverseEventReportingPeriod(), 1);
        expect(webContext.forwardToString((String)EasyMock.anyObject())).andReturn("").once();
       
        replayMocks();
        facade.deleteReviewComment(1);
        verifyMocks();
	}
}
