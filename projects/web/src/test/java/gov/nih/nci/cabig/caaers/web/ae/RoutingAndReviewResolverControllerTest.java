package gov.nih.nci.cabig.caaers.web.ae;


import org.acegisecurity.Authentication;
import org.acegisecurity.context.SecurityContext;
import org.acegisecurity.userdetails.User;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import gov.nih.nci.cabig.caaers.dao.AdverseEventReportingPeriodDao;
import gov.nih.nci.cabig.caaers.dao.ExpeditedAdverseEventReportDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.repository.CSMUserRepository;
import gov.nih.nci.cabig.caaers.domain.repository.CSMUserRepositoryImpl;
import gov.nih.nci.cabig.caaers.web.WebTestCase;
import static org.easymock.EasyMock.expect;

/**
 * This class tests - RoutingAndReviewResolverController.java
 * @author Sameer Sawant
 */
public class RoutingAndReviewResolverControllerTest extends WebTestCase{
	
	protected AdverseEventReportingPeriodDao adverseEventReportingPeriodDao;
	protected ExpeditedAdverseEventReportDao expeditedAdverseEventReportDao;
	protected SecurityContext context;
	protected Authentication auth;
	protected User user;
	
	protected RoutingAndReviewResolverController controller;
	
	protected void setUp() throws Exception {
		super.setUp();
		
		expeditedAdverseEventReportDao = registerDaoMockFor(ExpeditedAdverseEventReportDao.class);
		adverseEventReportingPeriodDao = registerDaoMockFor(AdverseEventReportingPeriodDao.class);
		context = registerMockFor(SecurityContext.class);
		auth =  registerMockFor(Authentication.class);
        user = registerMockFor(User.class);
		session.setAttribute("ACEGI_SECURITY_CONTEXT",context);
		
		
		controller = new RoutingAndReviewResolverController();
		controller.setAdverseEventReportingPeriodDao(adverseEventReportingPeriodDao);
		controller.setExpeditedAdverseEventReportDao(expeditedAdverseEventReportDao);
	}
		
	/**
	 * This method tests handleRequestInternal with an EvaluationPeriod in context. This is the flow of the controller
	 * when an evaluationPeriod link is clicked on the RoutingAndReview page. The user is SYSTEM_ADMIN.
	 * @throws Exception
	 */
	public void testHandleRequestInternalForReportingPeriodSystemAdmin() throws Exception{
		request.setParameter("adverseEventReportingPeriod", "1");
		AdverseEventReportingPeriod reportingPeriod = Fixtures.createReportingPeriod();
		reportingPeriod.getStudy().setId(1);
		reportingPeriod.getParticipant().setId(1);
		expect(adverseEventReportingPeriodDao.getById(1)).andReturn(reportingPeriod);
        replayMocks();
        ModelAndView mv = controller.handleRequest(request, response);
        verifyMocks();
        assertNotNull("ModelAndView object returned by handleRequestInternal cannot be null", mv);
	}
	
	/**
	 * This method tests handleRequestInternal with an AeReport in context. This is the flow of the controller
	 * when an aeReport link is clicked on the RoutingAndReview page. The user is SYSTEM_ADMIN.
	 * @throws Exception
	 */
	public void testHandleRequestInternalForAeReportSystemAdmin() throws Exception{
		request.setParameter("aeReport", "1");
		ExpeditedAdverseEventReport aeReport = Fixtures.createSavableExpeditedReport();
		
        replayMocks();
        ModelAndView mv = controller.handleRequest(request, response);
        verifyMocks();
        assertNotNull("ModelAndView object returned by handleRequestInternal cannot be null", mv);
	}
	
	/**
	 * This method tests handleRequestInternal with an EvaluationPeriod in context. This is the flow of the controller
	 * when an evaluationPeriod link is clicked on the RoutingAndReview page. The user is not SYSTEM_ADMIN.
	 * @throws Exception
	 */
	/*public void testHandleRequestInternalForReportingPeriodNotSystemAdmin() throws Exception{
		request.setParameter("adverseEventReportingPeriod", "1");
		AdverseEventReportingPeriod reportingPeriod = Fixtures.createReportingPeriod();
		reportingPeriod.getStudy().setId(1);
		reportingPeriod.getParticipant().setId(1);
		expect(adverseEventReportingPeriodDao.getById(1)).andReturn(reportingPeriod);
		expect(context.getAuthentication()).andReturn(auth);
        expect(auth.getPrincipal()).andReturn(user);
        expect(user.getUsername()).andReturn("TEST_USER");
        expect(csmUserRepository.isSuperUser("SYSTEM_ADMIN")).andReturn(true);
        replayMocks();
        ModelAndView mv = controller.handleRequest(request, response);
        verifyMocks();
        assertNotNull("ModelAndView object returned by handleRequestInternal cannot be null", mv);
	}*/
}