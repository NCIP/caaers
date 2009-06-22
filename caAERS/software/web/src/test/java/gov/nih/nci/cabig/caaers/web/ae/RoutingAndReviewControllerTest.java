package gov.nih.nci.cabig.caaers.web.ae;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.web.servlet.ModelAndView;

import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.StudyParticipantAssignmentDao;
import gov.nih.nci.cabig.caaers.dao.StudySiteDao;
import gov.nih.nci.cabig.caaers.domain.ReviewStatus;
import gov.nih.nci.cabig.caaers.domain.dto.RoutingAndReviewSearchResultsDTO;
import gov.nih.nci.cabig.caaers.domain.repository.AdverseEventRoutingAndReviewRepository;
import gov.nih.nci.cabig.caaers.domain.repository.AdverseEventRoutingAndReviewRepositoryImpl;
import gov.nih.nci.cabig.caaers.domain.repository.CSMUserRepository;
import gov.nih.nci.cabig.caaers.domain.repository.CSMUserRepositoryImpl;
import gov.nih.nci.cabig.caaers.service.workflow.WorkflowService;
import gov.nih.nci.cabig.caaers.service.workflow.WorkflowServiceImpl;
import gov.nih.nci.cabig.caaers.web.WebTestCase;

import static org.easymock.EasyMock.expect;

/**
 * This class tests - RoutingAndReviewController.
 * 
 * @author Sameer Sawant
 */
public class RoutingAndReviewControllerTest extends WebTestCase{

	private RoutingAndReviewController controller;
	private RoutingAndReviewCommand command;
	private ParticipantDao participantDao;
    private StudyDao studyDao;
    private StudySiteDao studySiteDao;
    private StudyParticipantAssignmentDao assignmentDao;
    private WorkflowService workflowService;
    private AdverseEventRoutingAndReviewRepository adverseEventRoutingAndReviewRepository;
    private CSMUserRepository csmUserRepository;
    private ModelAndView modelAndView;
    protected static final Collection<ReviewStatus> REVIEW_STATUS = new ArrayList<ReviewStatus>(7);
    private static final String PAGINATION_ACTION = "paginationAction";
    private static final String CURRENT_PAGE_NUMBER = "currentPageNumber";
    private RoutingAndReviewSearchResultsDTO resultsDTO;
    
    
    protected void setUp() throws Exception {
		super.setUp();
		participantDao = registerDaoMockFor(ParticipantDao.class);
		studyDao = registerDaoMockFor(StudyDao.class);
		studySiteDao = registerDaoMockFor(StudySiteDao.class);
		assignmentDao = registerDaoMockFor(StudyParticipantAssignmentDao.class);
		workflowService = registerMockFor(WorkflowServiceImpl.class);
		adverseEventRoutingAndReviewRepository = registerMockFor(AdverseEventRoutingAndReviewRepositoryImpl.class);
		csmUserRepository = registerMockFor(CSMUserRepositoryImpl.class);
		resultsDTO = registerMockFor(RoutingAndReviewSearchResultsDTO.class);
		modelAndView = new ModelAndView("test");
		controller = new RoutingAndReviewController();
		command = new RoutingAndReviewCommand();
		command.setSearchResultsDTO(resultsDTO);
    }
    
    public void testProcessPaginationSubmissionForNextPage() throws Exception{
    	request.setParameter(PAGINATION_ACTION, "nextPage");
    	request.setParameter("numberOfResultsPerPage", "10");
    	request.getSession().setAttribute(CURRENT_PAGE_NUMBER, 5);
    	expect(resultsDTO.getTotalResultCount()).andReturn(75).anyTimes();
    	resultsDTO.filterResultMap(50, 59);
    	replayMocks();
    	controller.processPaginationSubmission(request, command, modelAndView);
    	verifyMocks();
    	assertEquals("Current Page number set incorrectly", new Integer(6), (Integer)request.getSession().getAttribute(CURRENT_PAGE_NUMBER));
    }
    
    public void testProcessPaginationSubmissionForPrevPage() throws Exception{
    	RoutingAndReviewCommand commandMock = registerMockFor(RoutingAndReviewCommand.class);
    	request.setParameter(PAGINATION_ACTION, "prevPage");
    	request.setParameter("numberOfResultsPerPage", "10");
    	request.getSession().setAttribute(CURRENT_PAGE_NUMBER, 5);
    	expect(resultsDTO.getTotalResultCount()).andReturn(75).anyTimes();
    	resultsDTO.filterResultMap(30, 39);
    	replayMocks();
    	controller.processPaginationSubmission(request, command, modelAndView);
    	verifyMocks();
    	assertEquals("Current Page number set incorrectly", new Integer(4), (Integer)request.getSession().getAttribute(CURRENT_PAGE_NUMBER));
    }
    
    public void testProcessPaginationSubmissionForFirstPage() throws Exception{
    	RoutingAndReviewCommand commandMock = registerMockFor(RoutingAndReviewCommand.class);
    	request.setParameter(PAGINATION_ACTION, "firstPage");
    	request.setParameter("numberOfResultsPerPage", "10");
    	request.getSession().setAttribute(CURRENT_PAGE_NUMBER, 5);
    	expect(resultsDTO.getTotalResultCount()).andReturn(75).anyTimes();
    	resultsDTO.filterResultMap(0, 9);
    	replayMocks();
    	controller.processPaginationSubmission(request, command, modelAndView);
    	verifyMocks();
    	assertEquals("Current Page number set incorrectly", new Integer(1), (Integer)request.getSession().getAttribute(CURRENT_PAGE_NUMBER));
    }
    
    public void testProcessPaginationSubmissionForLastPage() throws Exception{
    	RoutingAndReviewCommand commandMock = registerMockFor(RoutingAndReviewCommand.class);
    	request.setParameter(PAGINATION_ACTION, "lastPage");
    	request.setParameter("numberOfResultsPerPage", "10");
    	request.getSession().setAttribute(CURRENT_PAGE_NUMBER, 5);
    	expect(resultsDTO.getTotalResultCount()).andReturn(75).anyTimes();
    	resultsDTO.filterResultMap(70, 74);
    	replayMocks();
    	controller.processPaginationSubmission(request, command, modelAndView);
    	verifyMocks();
    	assertEquals("Current Page number set incorrectly", new Integer(8), (Integer)request.getSession().getAttribute(CURRENT_PAGE_NUMBER));
    }
    
    public void testProcessPaginationSubmissionForNumberOfResultsPerPage() throws Exception{
    	RoutingAndReviewCommand commandMock = registerMockFor(RoutingAndReviewCommand.class);
    	request.setParameter(PAGINATION_ACTION, "numberOfResultsPerPage");
    	request.setParameter("numberOfResultsPerPage", "15");
    	request.getSession().setAttribute(CURRENT_PAGE_NUMBER, 5);
    	expect(resultsDTO.getTotalResultCount()).andReturn(75).anyTimes();
    	resultsDTO.filterResultMap(0, 14);
    	replayMocks();
    	controller.processPaginationSubmission(request, command, modelAndView);
    	verifyMocks();
    	assertEquals("Current Page number set incorrectly", new Integer(1), (Integer)request.getSession().getAttribute(CURRENT_PAGE_NUMBER));
    }
}