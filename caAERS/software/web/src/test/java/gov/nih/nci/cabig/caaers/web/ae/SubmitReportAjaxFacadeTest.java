package gov.nih.nci.cabig.caaers.web.ae;

import static org.easymock.EasyMock.expect;
import gov.nih.nci.cabig.caaers.dao.AdverseEventReportingPeriodDao;
import gov.nih.nci.cabig.caaers.dao.ExpeditedAdverseEventReportDao;
import gov.nih.nci.cabig.caaers.dao.StudyParticipantAssignmentDao;
import gov.nih.nci.cabig.caaers.dao.report.ReportDefinitionDao;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.ReportStatus;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportTree;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.report.ReportVersion;
import gov.nih.nci.cabig.caaers.domain.repository.AdverseEventRoutingAndReviewRepository;
import gov.nih.nci.cabig.caaers.domain.repository.ReportRepository;
import gov.nih.nci.cabig.caaers.web.DwrFacadeTestCase;
import gov.nih.nci.cabig.caaers.web.RenderDecisionManager;
import gov.nih.nci.cabig.caaers.web.dwr.AjaxOutput;


/**
 * This class tests the class - SubmitReportAjaxFacade
 * @author Sameer Sawant
 */
public class SubmitReportAjaxFacadeTest extends DwrFacadeTestCase{
	
	private SubmitReportAjaxFacade facade;
	private ExpeditedAdverseEventReportDao aeReportDao;
	private ReportDefinitionDao reportDefinitionDao;
	private StudyParticipantAssignmentDao assignmentDao;
	private AdverseEventReportingPeriodDao reportingPeriodDao;
	private ExpeditedReportTree expeditedReportTree;
	private RenderDecisionManager renderDecisionManager;
	private ReportRepository reportRepository;
	private AdverseEventRoutingAndReviewRepository adverseEventRoutingAndReviewRepository;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		aeReportDao = registerDaoMockFor(ExpeditedAdverseEventReportDao.class);
		reportDefinitionDao = registerDaoMockFor(ReportDefinitionDao.class);
		assignmentDao = registerDaoMockFor(StudyParticipantAssignmentDao.class);
		reportingPeriodDao = registerDaoMockFor(AdverseEventReportingPeriodDao.class);
		expeditedReportTree = registerMockFor(ExpeditedReportTree.class);
		renderDecisionManager = registerMockFor(RenderDecisionManager.class);
		reportRepository = registerMockFor(ReportRepository.class);
		adverseEventRoutingAndReviewRepository = registerMockFor(AdverseEventRoutingAndReviewRepository.class);
	
		facade = new SubmitReportAjaxFacade();
	}
	
	public void testFetchReportSubmissionStatus() throws Exception{
		SubmitExpeditedAdverseEventCommand command = setupSubmitExpeditedAdverseEventCommand();
		ExpeditedAdverseEventReport aeReport = new ExpeditedAdverseEventReport();
		aeReport.setId(1);
		Report report = new Report();
		ReportVersion reportVersion =  report.getLastVersion();
		reportVersion.setReportStatus(ReportStatus.COMPLETED);
		aeReport.addReport(report);
		SubmitReportAjaxFacade facadeMock = registerMockFor(SubmitReportAjaxFacade.class);
		expect(facadeMock.getWebContext()).andReturn(webContext).anyTimes();
		facade.setAeReportDao(aeReportDao);
		expect(aeReportDao.getById(1)).andReturn(aeReport);
		expect(webContext.getCurrentPage()).andReturn("/pages/ae/submitReport");
        expect(webContext.forwardToString("/pages/ae/submitReport?aeReport=1&subview=reportSubmissionStatus")).andReturn("The HTML");
        replayMocks();
        AjaxOutput output = facade.fetchReportSubmissionStatus("1", "0");
        verifyMocks();
        assertEquals("Incorrect object set in ajaxOutput when the ReportStatus was COMPLETE", "COMPLETED", output.getObjectContent());
	}
	
	private SubmitExpeditedAdverseEventCommand setupSubmitExpeditedAdverseEventCommand(){
		SubmitExpeditedAdverseEventCommand command = new SubmitExpeditedAdverseEventCommand(aeReportDao,
				reportDefinitionDao, assignmentDao, reportingPeriodDao, expeditedReportTree, renderDecisionManager,
				reportRepository, adverseEventRoutingAndReviewRepository);
		
		session.setAttribute(SubmitReportController.class.getName() + ".FORM.command", command);
	    expect(webContext.getSession()).andReturn(session).anyTimes();
		return command;
	}
}