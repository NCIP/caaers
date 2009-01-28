package gov.nih.nci.cabig.caaers.domain.repository;

import gov.nih.nci.cabig.caaers.CaaersNoSecurityTestCase;
import gov.nih.nci.cabig.caaers.dao.AdverseEventReportingPeriodDao;
import gov.nih.nci.cabig.caaers.dao.ExpeditedAdverseEventReportDao;
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
import gov.nih.nci.cabig.caaers.domain.dto.ExpeditedAdverseEventReportDTO;
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
import org.jbpm.graph.def.Transition;
import org.jbpm.graph.exe.ProcessInstance;
/**
 * 
 * @author Biju Joseph
 *
 */
public class AdverseEventRoutingAndReviewRepositoryImplTest extends CaaersNoSecurityTestCase {
	
	
	AdverseEventReportingPeriodDao rpDao;
	ExpeditedAdverseEventReportDao rDao;
	AERoutingAndReviewDTOFactory factory;
	WorkflowService wfService;
	AdverseEventRoutingAndReviewRepositoryImpl impl;
	ProcessInstance processInstance;
	ContextInstance contextInstance;
	
	Map<String, Object> variables = new HashMap<String, Object>();
	
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		rDao = registerDaoMockFor(ExpeditedAdverseEventReportDao.class);
		rpDao = registerDaoMockFor(AdverseEventReportingPeriodDao.class);
		factory = registerMockFor(AERoutingAndReviewDTOFactory.class);
		wfService = registerMockFor(WorkflowService.class);
		processInstance = registerMockFor(ProcessInstance.class);
		contextInstance = registerMockFor(ContextInstance.class);
		
		impl = new AdverseEventRoutingAndReviewRepositoryImpl();
		impl.setAdverseEventReportingPeriodDao(rpDao);
		impl.setRoutingAndReviewFactory(factory);
		impl.setExpeditedAdverseEventReportDao(rDao);
		impl.setWorkflowService(wfService);
	}
	
	public void testFetchReviewCommentsForReport() {
		ExpeditedAdverseEventReport r = Fixtures.createSavableExpeditedReport();
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
		
		ExpeditedAdverseEventReport  r = Fixtures.createSavableExpeditedReport();
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
		String userId = "tester";
		AdverseEventReportingPeriod rp = Fixtures.createReportingPeriod();
		ExpeditedAdverseEventReport aeReport = Fixtures.createSavableExpeditedReport();
		aeReport.setWorkflowId(1);
		aeReport.setReviewStatus(ReviewStatus.DRAFT_INCOMPLETE); 
		rp.addAeReport(aeReport);
		
		List<AdverseEventReportingPeriod> reportingPeriods = new ArrayList<AdverseEventReportingPeriod>();
		reportingPeriods.add(rp);
		
		rp.setReviewStatus(ReviewStatus.DRAFT_INCOMPLETE);
		rp.setWorkflowId(1);
		
		
		AdverseEventReportingPeriodDTO rpDto = new AdverseEventReportingPeriodDTO();
		ExpeditedAdverseEventReportDTO rDto = new ExpeditedAdverseEventReportDTO();
		EasyMock.expect(rpDao.findAdverseEventReportingPeriods((AdverseEventReportingPeriodForReviewQuery) EasyMock.anyObject())).andReturn(reportingPeriods);
	
		EasyMock.expect(factory.createAdverseEventEvalutionPeriodDTO(rp, userId)).andReturn(rpDto);
		EasyMock.expect(factory.createAdverseEventReportDTO(aeReport, userId)).andReturn(rDto);
		replayMocks();
		
		Participant participant = Fixtures.createParticipant("Joel", "biju");
		Study study = Fixtures.createStudy("Hello");
		StudySite studySite = new StudySite();
		ReviewStatus reviewStatus = null;
		
		List<AdverseEventReportingPeriodDTO> dtos = impl.findAdverseEventReportingPeriods(participant, study, studySite, reviewStatus, userId);
		
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
		ExpeditedAdverseEventReport  r = Fixtures.createSavableExpeditedReport();
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
		String loginId = "SYSTEM_ADMIN";
		ReviewStatus reviewStatus = ReviewStatus.DRAFT_INCOMPLETE;
		ExpeditedAdverseEventReport  r = Fixtures.createSavableExpeditedReport();
		List<String> transitions = new ArrayList<String>();
		EasyMock.expect(wfService.nextTransitionNames(wfId, loginId)).andReturn(transitions);
		EasyMock.expect(wfService.advanceWorkflow(wfId, transitionToTake)).andReturn(reviewStatus);
		EasyMock.expect(rDao.getById(id)).andReturn(r);
		rDao.save(r);
		replayMocks();
		List<String> transitionsNames = impl.advanceReportWorkflow(wfId, transitionToTake, id, loginId);
		
		verifyMocks();
		
		
	}
	
	public void testAdvanceReportingPeriodWorkflow(){
		Integer id = 5;
		Integer wfId = 5;
		String loginId = "SYSTEM_ADMIN";
		String transitionToTake = "abcd";
		List<String> transitions = new ArrayList<String>();
		ReviewStatus rs = ReviewStatus.DRAFT_INCOMPLETE;
		AdverseEventReportingPeriod rp = Fixtures.createReportingPeriod();
		
		EasyMock.expect(wfService.advanceWorkflow(wfId, transitionToTake)).andReturn(rs);
		EasyMock.expect(wfService.nextTransitionNames(wfId, loginId)).andReturn(transitions);
		EasyMock.expect(rpDao.getById(id)).andReturn(rp);
		rpDao.save(rp);
		replayMocks();
		List<String> transitionNames = impl.advanceReportingPeriodWorkflow(wfId, transitionToTake, id, loginId);
		
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
		
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put(WorkflowService.VAR_STUDY_ID, site.getStudy().getId());
		variables.put(WorkflowService.VAR_WF_TYPE, AdverseEventReportingPeriod.class.getName());
		variables.put(WorkflowService.VAR_REPORTING_PERIOD_ID, reportingPeriod.getId());
		
		EasyMock.expect(wfService.createProcessInstance("test", variables)).andReturn(processInstance);
	    EasyMock.expect(processInstance.getId()).andReturn(processId).anyTimes();
	    rpDao.save(reportingPeriod);
		replayMocks();
		impl.enactReportingPeriodWorkflow(reportingPeriod);
		verifyMocks();
	}
	
	public void testEnactReportWorkflow(){
		long processId = 5;
		StudyParticipantAssignment assignment = Fixtures.createAssignment();
		ExpeditedAdverseEventReport aeReport = Fixtures.createSavableExpeditedReport();
		aeReport.setId(55);
		AdverseEventReportingPeriod reportingPeriod = Fixtures.createReportingPeriod();
		WorkflowConfig workflowConfig = Fixtures.createWorkflowConfig("test");
		StudySite site = assignment.getStudySite();
		Map<String, WorkflowConfig> workflowConfigs = new HashMap<String, WorkflowConfig>();
		workflowConfigs.put("report", workflowConfig);
		site.setWorkflowConfigs(workflowConfigs);
		reportingPeriod.addAeReport(aeReport);
		aeReport.setAssignment(assignment);
		
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put(WorkflowService.VAR_STUDY_ID, site.getStudy().getId());
		variables.put(WorkflowService.VAR_WF_TYPE, ExpeditedAdverseEventReport.class.getName());
		variables.put(WorkflowService.VAR_EXPEDITED_REPORT_ID, aeReport.getId());
		
		EasyMock.expect(wfService.createProcessInstance("test", variables)).andReturn(processInstance);
	    EasyMock.expect(processInstance.getId()).andReturn(processId).anyTimes();
	    rDao.save(aeReport);
		replayMocks();
		impl.enactReportWorkflow(aeReport);
		verifyMocks();
	}
}
