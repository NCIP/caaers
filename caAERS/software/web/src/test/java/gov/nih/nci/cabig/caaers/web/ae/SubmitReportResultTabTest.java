package gov.nih.nci.cabig.caaers.web.ae;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import gov.nih.nci.cabig.caaers.AbstractNoSecurityTestCase;
import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.dao.AdverseEventReportingPeriodDao;
import gov.nih.nci.cabig.caaers.dao.ExpeditedAdverseEventReportDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.StudyParticipantAssignmentDao;
import gov.nih.nci.cabig.caaers.dao.report.ReportDao;
import gov.nih.nci.cabig.caaers.dao.report.ReportDefinitionDao;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportTree;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.repository.AdverseEventRoutingAndReviewRepository;
import gov.nih.nci.cabig.caaers.domain.repository.ReportRepository;
import gov.nih.nci.cabig.caaers.web.RenderDecisionManager;
import static org.easymock.EasyMock.expect;



/**
 * This class tests - SubmitReportResultTab.java
 * @author Sameer Sawant
 */
public class SubmitReportResultTabTest extends AbstractNoSecurityTestCase{
	SubmitReportResultTab tab;
	SubmitExpeditedAdverseEventCommand command;
    
    ExpeditedAdverseEventReport aeReport;
	
	ExpeditedAdverseEventReportDao expeditedAeReportDao;
	ReportDefinitionDao reportDefinitonDao;
	StudyParticipantAssignmentDao assignmentDao;
	AdverseEventReportingPeriodDao reportingPeriodDao;
	ExpeditedReportTree expeditedReportTree;
	RenderDecisionManager renderDecisionManager;
	ReportRepository reportRepository;
	AdverseEventRoutingAndReviewRepository adverseEventRoutingAndReviewRepository;
	ReportDao reportDao;
    StudyDao studyDao;
	HttpServletRequest request;
    Report report;
	
	protected void setUp() throws Exception {
		super.setUp();
		tab = new SubmitReportResultTab();
		aeReport = Fixtures.createSavableExpeditedReport();
        aeReport.setReportingPeriod(Fixtures.createReportingPeriod());
		expeditedAeReportDao = registerDaoMockFor(ExpeditedAdverseEventReportDao.class);
		reportDefinitonDao = registerDaoMockFor(ReportDefinitionDao.class);
		assignmentDao = registerDaoMockFor(StudyParticipantAssignmentDao.class);
		reportingPeriodDao = registerDaoMockFor(AdverseEventReportingPeriodDao.class);
		expeditedReportTree = registerMockFor(ExpeditedReportTree.class);
		renderDecisionManager = registerMockFor(RenderDecisionManager.class);
		reportRepository = registerMockFor(ReportRepository.class);
		adverseEventRoutingAndReviewRepository = registerMockFor(AdverseEventRoutingAndReviewRepository.class);
		reportDao = registerDaoMockFor(ReportDao.class);
		request = registerMockFor(HttpServletRequest.class);
        studyDao = registerDaoMockFor(StudyDao.class);
		tab.setReportDao(reportDao);
		
	}
	
	private void setupCommand(){
        Report report = Fixtures.createReport("10 day report");
        report.setAeReport(aeReport);
        report.setId(1);
        command = new SubmitExpeditedAdverseEventCommand(report, true, reportDao, reportRepository);
		command.setReportId("1");
	}
	
	public void testReferenceData() throws Exception{
        setupCommand();
		Report report = Fixtures.createReport("10 day report");
		report.setId(1);
		replayMocks();
		Map<String, Object> refdata = tab.referenceData(request, command);
		verifyMocks();
		assertTrue("Report submitted flag not set to true", command.isSubmissionInprogress());
	}
	
}