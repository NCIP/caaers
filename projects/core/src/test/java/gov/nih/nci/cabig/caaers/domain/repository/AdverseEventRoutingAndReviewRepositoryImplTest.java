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
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.domain.dto.AdverseEventReportDTO;
import gov.nih.nci.cabig.caaers.domain.dto.AdverseEventReportingPeriodDTO;
import gov.nih.nci.cabig.caaers.domain.factory.AERoutingAndReviewDTOFactory;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.workflow.ReportReviewComment;
import gov.nih.nci.cabig.caaers.domain.workflow.ReportingPeriodReviewComment;
import gov.nih.nci.cabig.caaers.domain.workflow.ReviewComment;
import gov.nih.nci.cabig.caaers.service.workflow.WorkflowService;

import java.util.ArrayList;
import java.util.List;

import org.easymock.classextension.EasyMock;

public class AdverseEventRoutingAndReviewRepositoryImplTest extends CaaersNoSecurityTestCase {
	
	
	AdverseEventReportingPeriodDao rpDao;
	ReportDao rDao;
	AERoutingAndReviewDTOFactory factory;
	WorkflowService wfService;
	AdverseEventRoutingAndReviewRepositoryImpl impl;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		rDao = registerDaoMockFor(ReportDao.class);
		rpDao = registerDaoMockFor(AdverseEventReportingPeriodDao.class);
		factory = registerMockFor(AERoutingAndReviewDTOFactory.class);
		wfService = registerMockFor(WorkflowService.class);
		
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

	public void testAddReportReviewComment() {
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
	}

	public void testAddReportingPeriodReviewComment() {
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
		ReviewStatus rs = ReviewStatus.DRAFT_INCOMPLETE;
		List<ReviewStatus> rtStatuses = new ArrayList<ReviewStatus>();
		Report r = Fixtures.createReport("test");
		
		EasyMock.expect(wfService.advanceWorkflow(wfId, rs)).andReturn(rtStatuses);
		EasyMock.expect(rDao.getById(id)).andReturn(r);
		rDao.save(r);
		replayMocks();
		List<ReviewStatus> statuses = impl.advanceReportWorkflow(wfId, rs, id);
		
		verifyMocks();
		
		
	}
	
	public void testAdvanceReportingPeriodWorkflow(){
		Integer id = 5;
		Integer wfId = 5;
		ReviewStatus rs = ReviewStatus.DRAFT_INCOMPLETE;
		List<ReviewStatus> rtStatuses = new ArrayList<ReviewStatus>();
		AdverseEventReportingPeriod rp = Fixtures.createReportingPeriod();
		
		EasyMock.expect(wfService.advanceWorkflow(wfId, rs)).andReturn(rtStatuses);
		EasyMock.expect(rpDao.getById(id)).andReturn(rp);
		rpDao.save(rp);
		replayMocks();
		List<ReviewStatus> statuses = impl.advanceReportingPeriodWorkflow(wfId, rs, id);
		
		verifyMocks();
		
	}

}
