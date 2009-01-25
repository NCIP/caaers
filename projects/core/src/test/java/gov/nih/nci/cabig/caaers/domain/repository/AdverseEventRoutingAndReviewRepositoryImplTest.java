package gov.nih.nci.cabig.caaers.domain.repository;

import gov.nih.nci.cabig.caaers.CaaersNoSecurityTestCase;
import gov.nih.nci.cabig.caaers.dao.AdverseEventReportingPeriodDao;
import gov.nih.nci.cabig.caaers.dao.query.AdverseEventReportingPeriodForReviewQuery;
import gov.nih.nci.cabig.caaers.dao.report.ReportDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.ReviewStatus;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.domain.dto.AdverseEventReportDTO;
import gov.nih.nci.cabig.caaers.domain.dto.AdverseEventReportingPeriodDTO;
import gov.nih.nci.cabig.caaers.domain.factory.AERoutingAndReviewDTOFactory;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.workflow.ReportReviewComment;
import gov.nih.nci.cabig.caaers.domain.workflow.ReportingPeriodReviewComment;
import gov.nih.nci.cabig.caaers.domain.workflow.ReviewComment;
import gov.nih.nci.cabig.caaers.domain.workflow.WorkflowConfig;
import gov.nih.nci.cabig.caaers.service.workflow.WorkflowService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.easymock.classextension.EasyMock;
import org.jbpm.context.exe.ContextInstance;
import org.jbpm.graph.exe.ProcessInstance;
/**
 * 
 * @author Biju Joseph
 *
 */
public class AdverseEventRoutingAndReviewRepositoryImplTest extends CaaersNoSecurityTestCase {
	
	
	AdverseEventReportingPeriodDao rpDao;
	ReportDao rDao;
	AERoutingAndReviewDTOFactory factory;
	WorkflowService wfService;
	AdverseEventRoutingAndReviewRepositoryImpl impl;
	ProcessInstance processInstance;
	ContextInstance contextInstance;
	
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		rDao = registerDaoMockFor(ReportDao.class);
		rpDao = registerDaoMockFor(AdverseEventReportingPeriodDao.class);
		factory = registerMockFor(AERoutingAndReviewDTOFactory.class);
		wfService = registerMockFor(WorkflowService.class);
		processInstance = registerMockFor(ProcessInstance.class);
		contextInstance = registerMockFor(ContextInstance.class);
		
		impl = new AdverseEventRoutingAndReviewRepositoryImpl();
		impl.setAdverseEventReportingPeriodDao(rpDao);
		impl.setRoutingAndReviewFactory(factory);
		impl.setReportDao(rDao);
		impl.setWorkflowService(wfService);
	}
	
	public void testFetchReviewCommentsForReport() {
		Report r = Fixtures.createReport("test");
		List<ReportReviewComment> reviewComments = new ArrayList<ReportReviewComment>();
		r.setReviewComments(reviewComments);
		Integer reportId = 5;

		EasyMock.expect(rDao.getById(reportId)).andReturn(r);
		replayMocks();
		List<? extends  ReviewComment> comments = impl.fetchReviewCommentsForReport(reportId);
		verifyMocks();
		assertSame(reviewComments, comments);
	}

	public void testFetchReviewCommentsForReportingPeriod() {
		AdverseEventReportingPeriod rp = Fixtures.createReportingPeriod();
		List<ReportingPeriodReviewComment> reviewComments = new ArrayList<ReportingPeriodReviewComment>();
		rp.setReviewComments(reviewComments);
		Integer rpId = 5;
		EasyMock.expect(rpDao.getById(rpId)).andReturn(rp);
		replayMocks();
		List<? extends  ReviewComment> comments = impl.fetchReviewCommentsForReportingPeriod(rpId);
		verifyMocks();
		assertSame(reviewComments, comments);
	}

	/*public void testAddReportReviewComment() {
		Integer reportId = 5;
		String comment = "mycomment";
		String userId = "userId";
		
		Report r = Fixtures.createReport("test");
		r.setReviewComments(new ArrayList<ReportReviewComment>());
		EasyMock.expect(rDao.getById(reportId)).andReturn(r);
		rDao.save(r);
		replayMocks();
		impl.addReportReviewComment(reportId, comment, userId);
		verifyMocks();
	}*/
	
	public void testAddReportingPeriodReviewCommentWithObject(){
		String comment = "mycomment";
		String userId = "userId";
		AdverseEventReportingPeriod rp = Fixtures.createReportingPeriod();
		rp.setReviewComments(new ArrayList<ReportingPeriodReviewComment>());
		impl.addReportingPeriodReviewComment(rp, comment, userId);
		assertEquals("Incorrect number of comments", 1, rp.getReviewComments().size());
	}

	public void testAddReportingPeriodReviewCommentWithId() {
		Integer reportingPeriodId = 5;
		String comment = "mycomment";
		String userId = "userId";
		
		AdverseEventReportingPeriod rp = Fixtures.createReportingPeriod();
		rp.setReviewComments(new ArrayList<ReportingPeriodReviewComment>());
		EasyMock.expect(rpDao.getById(reportingPeriodId)).andReturn(rp);
		rpDao.save(rp);
		replayMocks();
		impl.addReportingPeriodReviewComment(reportingPeriodId, comment, userId);
		verifyMocks();
	}
	
	public void testEditReportingPeriodReviewCommentWithoutObject(){
		String newComment = "new Comment";
		String userId = "userId";
		Integer commentId = 2;
		AdverseEventReportingPeriod rp = Fixtures.createReportingPeriod();
		ReportingPeriodReviewComment comment = Fixtures.createReportingPeriodReviewComment(1, "comment 1");
		ArrayList<ReportingPeriodReviewComment> commentsList = new ArrayList<ReportingPeriodReviewComment>();
		commentsList.add(comment);
		comment = Fixtures.createReportingPeriodReviewComment(2, "comment 2");
		commentsList.add(comment);
		rp.setReviewComments(commentsList);
		impl.editReportingPeriodReviewComment(rp, newComment, userId, commentId);
		assertEquals("Edit comment isnt working correctly", "new Comment", rp.getReviewComments().get(1).getUserComment());
	}
	
	public void testEditReportingPeriodReviewCommentWithId(){
		Integer reportingPeriodId = 5;
		String newComment = "new Comment";
		String userId = "userId";
		Integer commentId = 2;
		
		AdverseEventReportingPeriod rp = Fixtures.createReportingPeriod();
		ReportingPeriodReviewComment comment = Fixtures.createReportingPeriodReviewComment(1, "comment 1");
		ArrayList<ReportingPeriodReviewComment> commentsList = new ArrayList<ReportingPeriodReviewComment>();
		commentsList.add(comment);
		comment = Fixtures.createReportingPeriodReviewComment(2, "comment 2");
		commentsList.add(comment);
		rp.setReviewComments(commentsList);
		EasyMock.expect(rpDao.getById(reportingPeriodId)).andReturn(rp);
		rpDao.save(rp);
		replayMocks();
		impl.editReportingPeriodReviewComment(reportingPeriodId, newComment, userId, commentId);
		verifyMocks();
		
		assertEquals("Edit comment isnt working correctly", "new Comment", rp.getReviewComments().get(1).getUserComment());
	}

	public void testFindAdverseEventReportingPeriods() {
		AdverseEventReportingPeriod rp = Fixtures.createReportingPeriod();
		ExpeditedAdverseEventReport aeReport = Fixtures.createSavableExpeditedReport();
		Report r = Fixtures.createReport("abc");
		r.setWorkflowId(1);
		r.setReviewStatus(ReviewStatus.DRAFT_INCOMPLETE); 
		aeReport.addReport(r);
		rp.addAeReport(aeReport);
		
		List<AdverseEventReportingPeriod> reportingPeriods = new ArrayList<AdverseEventReportingPeriod>();
		reportingPeriods.add(rp);
		
		rp.setReviewStatus(ReviewStatus.DRAFT_INCOMPLETE);
		rp.setWorkflowId(1);
		
		
		AdverseEventReportingPeriodDTO rpDto = new AdverseEventReportingPeriodDTO();
		AdverseEventReportDTO rDto = new AdverseEventReportDTO();
		EasyMock.expect(rpDao.findAdverseEventReportingPeriods((AdverseEventReportingPeriodForReviewQuery) EasyMock.anyObject())).andReturn(reportingPeriods);
	
		EasyMock.expect(factory.createAdverseEventEvalutionPeriodDTO(rp)).andReturn(rpDto);
		EasyMock.expect(factory.createAdverseEventReportDTO(r, aeReport)).andReturn(rDto);
		replayMocks();
		
		Participant participant = Fixtures.createParticipant("Joel", "biju");
		Study study = Fixtures.createStudy("Hello");
		StudySite studySite = new StudySite();
		ReviewStatus reviewStatus = null;
		
		List<AdverseEventReportingPeriodDTO> dtos = impl.findAdverseEventReportingPeriods(participant, study, studySite, reviewStatus);
		
		verifyMocks();
		
		assertEquals(1, dtos.size());
		assertEquals(1, dtos.get(0).getReports().size());
	}

	public void testIsReportingPeriodHavingSpecifiedReviewStatus() {
		AdverseEventReportingPeriod rp = Fixtures.createReportingPeriod();
		boolean result = impl.isReportingPeriodHavingSpecifiedReviewStatus(rp, null);
		assertTrue(result);
		
	}

	public void testIsEntityHavingSpecifiedReviewStatus() {
		Report r = Fixtures.createReport("abc");
		r.setReviewStatus(ReviewStatus.DRAFT_INCOMPLETE);
		
		boolean result = impl.isEntityHavingSpecifiedReviewStatus(null, r);
		assertTrue(result);
		
		r.setReviewStatus(null);
		result = impl.isEntityHavingSpecifiedReviewStatus(ReviewStatus.DRAFT_INCOMPLETE, r);
		assertFalse(result);
		
		r.setReviewStatus(ReviewStatus.DRAFT_INCOMPLETE);
		result = impl.isEntityHavingSpecifiedReviewStatus(ReviewStatus.DRAFT_INCOMPLETE, r);
		assertTrue(result);
		
	}
	
	public void testAdvanceReportWorkflow(){
		Integer id = 5;
		Integer wfId = 5;
		String transitionToTake = "abcd";
		ReviewStatus reviewStatus = ReviewStatus.DRAFT_INCOMPLETE;
		Report r = Fixtures.createReport("test");
		List<String> transitionNames = new ArrayList<String>();
		
		EasyMock.expect(wfService.advanceWorkflow(wfId, transitionToTake)).andReturn(reviewStatus);
		EasyMock.expect(rDao.getById(id)).andReturn(r);
		EasyMock.expect(wfService.nextTransitionNames(wfId)).andReturn(transitionNames);
		rDao.save(r);
		replayMocks();
		List<String> transitions = impl.advanceReportWorkflow(wfId, transitionToTake, id);
		
		verifyMocks();
		
		
	}
	
	public void testAdvanceReportingPeriodWorkflow(){
		Integer id = 5;
		Integer wfId = 5;
		String transitionToTake = "abcd";
		List<String> transitionNames = new ArrayList<String>();
		ReviewStatus rs = ReviewStatus.DRAFT_INCOMPLETE;
		AdverseEventReportingPeriod rp = Fixtures.createReportingPeriod();
		
		EasyMock.expect(wfService.advanceWorkflow(wfId, transitionToTake)).andReturn(rs);
		EasyMock.expect(wfService.nextTransitionNames(wfId)).andReturn(transitionNames);
		EasyMock.expect(rpDao.getById(id)).andReturn(rp);
		rpDao.save(rp);
		replayMocks();
		List<String> transitions = impl.advanceReportingPeriodWorkflow(wfId, transitionToTake, id);
		
		verifyMocks();
		
	}
	
	public void testEnactReportingPeriodWorkflow() {
		long processId = 5;
		StudyParticipantAssignment assignment = Fixtures.createAssignment();
		AdverseEventReportingPeriod reportingPeriod = Fixtures.createReportingPeriod();
		reportingPeriod.setId(44);
		WorkflowConfig workflowConfig = Fixtures.createWorkflowConfig("test");
		StudySite site = assignment.getStudySite();
		Map<String, WorkflowConfig> wfConfigMap = new HashMap<String, WorkflowConfig>();
		wfConfigMap.put("reportingPeriod", workflowConfig);
		site.setWorkflowConfigs(wfConfigMap);
		reportingPeriod.setAssignment(assignment);
		
		EasyMock.expect(wfService.createProcessInstance("test")).andReturn(processInstance);
		EasyMock.expect(processInstance.getContextInstance()).andReturn(contextInstance);
	    contextInstance.addVariables((Map)EasyMock.anyObject());
	    EasyMock.expect(processInstance.getId()).andReturn(processId).anyTimes();
	    EasyMock.expect(wfService.saveProcessInstance(processInstance)).andReturn(processId);
	    rpDao.save(reportingPeriod);
		replayMocks();
		impl.enactReportingPeriodWorkflow(reportingPeriod);
		verifyMocks();
	}
	
	public void testEnactReportWorkflow(){
		long processId = 5;
		Report report = Fixtures.createReport("test");
		report.setId(55);
		StudyParticipantAssignment assignment = Fixtures.createAssignment();
		ExpeditedAdverseEventReport aeReport = Fixtures.createSavableExpeditedReport();
		AdverseEventReportingPeriod reportingPeriod = Fixtures.createReportingPeriod();
		WorkflowConfig workflowConfig = Fixtures.createWorkflowConfig("test");
		StudySite site = assignment.getStudySite();
		Map<String, WorkflowConfig> workflowConfigs = new HashMap<String, WorkflowConfig>();
		workflowConfigs.put("report", workflowConfig);
		site.setWorkflowConfigs(workflowConfigs);
		reportingPeriod.addAeReport(aeReport);
		aeReport.setAssignment(assignment);
		aeReport.addReport(report);
		
		EasyMock.expect(wfService.createProcessInstance("test")).andReturn(processInstance);
		EasyMock.expect(processInstance.getContextInstance()).andReturn(contextInstance);
	    contextInstance.addVariables((Map)EasyMock.anyObject());
	    EasyMock.expect(processInstance.getId()).andReturn(processId).anyTimes();
	    EasyMock.expect(wfService.saveProcessInstance(processInstance)).andReturn(processId);
	    rDao.save(report);
		replayMocks();
		impl.enactReportWorkflow(report);
		verifyMocks();
	}
}
