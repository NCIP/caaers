package gov.nih.nci.cabig.caaers.web.ae;

import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import gov.nih.nci.cabig.caaers.dao.AdverseEventReportingPeriodDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.repository.AdverseEventRoutingAndReviewRepository;
import gov.nih.nci.cabig.caaers.domain.repository.AdverseEventRoutingAndReviewRepositoryImpl;
import gov.nih.nci.cabig.caaers.domain.workflow.ReportingPeriodReviewComment;
import gov.nih.nci.cabig.caaers.domain.workflow.ReviewComment;
import gov.nih.nci.cabig.caaers.web.WebTestCase;


import static org.easymock.EasyMock.expect;
/**
 * This class tests - RoutingAndReviewCommentController
 * @author Sameer Sawant
 */
public class RoutingAndReviewCommentControllerTest extends WebTestCase{
	
	protected AdverseEventRoutingAndReviewRepository adverseEventRoutingAndReviewRepository;
	protected AdverseEventReportingPeriodDao adverseEventReportingPeriodDao;
	protected BindException errors;
	
	protected RoutingAndReviewCommentController controller;
	
	protected void setUp() throws Exception {
		super.setUp();
	
		adverseEventReportingPeriodDao = registerDaoMockFor(AdverseEventReportingPeriodDao.class);
		adverseEventRoutingAndReviewRepository = registerMockFor(AdverseEventRoutingAndReviewRepositoryImpl.class);
		
		controller = new RoutingAndReviewCommentController();
		controller.setAdverseEventReportingPeriodDao(adverseEventReportingPeriodDao);
		controller.setAdverseEventRoutingAndReviewRepository(adverseEventRoutingAndReviewRepository);
		
		errors = new BindException(new RoutingAndReviewCommentCommand(),"command");
	}
	
	public RoutingAndReviewCommentCommand setupCommand(){
		RoutingAndReviewCommentCommand command = new RoutingAndReviewCommentCommand();
		command.setEntity("reportingPeriod");
		command.setEntityId(1);
		command.setComment("testComment");
		command.setUserId("SYSTEM_ADMIN");
		command.setCommentId(1);
		return command;
	}
	/**
	 * This method tests the processFormSubmission method of the controller.
	 */
	public void testProcessFormSubmissionWithDeletePopupCommentAction() throws Exception{
		RoutingAndReviewCommentCommand command = setupCommand();
		request.setParameter("action", "deletePopupComment");
		adverseEventRoutingAndReviewRepository.deleteReportingPeriodReviewComment(1, 1);
		expect(adverseEventRoutingAndReviewRepository.fetchReviewCommentsForReportingPeriod(1)).andReturn(null);
		replayMocks();
		ModelAndView mv = controller.processFormSubmission(request, response, command, errors);
		verifyMocks();
	}
	
	public void testProcessFormSubmissionWithEditPopupCommentAction() throws Exception{
		RoutingAndReviewCommentCommand command = setupCommand();
		request.setParameter("action", "editPopupComment");
		AdverseEventReportingPeriod reportingPeriod = Fixtures.createReportingPeriod();
		expect(adverseEventReportingPeriodDao.getById(1)).andReturn(reportingPeriod);
		adverseEventRoutingAndReviewRepository.editReportingPeriodReviewComment(reportingPeriod, "testComment", "SYSTEM_ADMIN", 1);
		expect(adverseEventRoutingAndReviewRepository.fetchReviewCommentsForReportingPeriod(1)).andReturn(null);
		replayMocks();
		ModelAndView mv = controller.processFormSubmission(request, response, command, errors);
		verifyMocks();
	}
	
	public void testProcessFormSubmissionWithAddPopupComment() throws Exception{
		RoutingAndReviewCommentCommand command = setupCommand();
		request.setParameter("action", "addPopupComment");
		AdverseEventReportingPeriod reportingPeriod = Fixtures.createReportingPeriod();
		expect(adverseEventReportingPeriodDao.getById(1)).andReturn(reportingPeriod);
		adverseEventRoutingAndReviewRepository.addReportingPeriodReviewComment(reportingPeriod, "testComment", "SYSTEM_ADMIN");
		expect(adverseEventRoutingAndReviewRepository.fetchReviewCommentsForReportingPeriod(1)).andReturn(null);
		replayMocks();
		ModelAndView mv = controller.processFormSubmission(request, response, command, errors);
		verifyMocks();
	}
}