package gov.nih.nci.cabig.caaers.web.ae;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import gov.nih.nci.cabig.caaers.dao.AdverseEventReportingPeriodDao;
import gov.nih.nci.cabig.caaers.dao.ExpeditedAdverseEventReportDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.StudyParticipantAssignmentDao;
import gov.nih.nci.cabig.caaers.dao.report.ReportDao;
import gov.nih.nci.cabig.caaers.dao.report.ReportDefinitionDao;
import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportTree;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.report.ReportDelivery;
import gov.nih.nci.cabig.caaers.domain.repository.AdverseEventRoutingAndReviewRepository;
import gov.nih.nci.cabig.caaers.domain.repository.AdverseEventRoutingAndReviewRepositoryImpl;
import gov.nih.nci.cabig.caaers.domain.repository.ReportRepository;
import gov.nih.nci.cabig.caaers.domain.repository.ReportRepositoryImpl;
import gov.nih.nci.cabig.caaers.service.ReportSubmissionService;
import gov.nih.nci.cabig.caaers.web.RenderDecisionManager;
import gov.nih.nci.cabig.caaers.web.WebTestCase;
import gov.nih.nci.cabig.caaers.web.utils.WebUtils;

import org.easymock.classextension.EasyMock;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;

/**
 * 
 * @author Biju Joseph
 * 
 */
public class SubmitReportTabTest extends WebTestCase {
    protected SubmitExpeditedAdverseEventCommand command;
    protected Errors errors;
    protected ExpeditedAdverseEventReport aeReport;
	ReportSubmissionService reportSubmissionService;
	Report report;
    ReportRepository reportRepository;
    ReportDao reportDao;
	SubmitReportTab tab;

	protected void setUp() throws Exception {
        super.setUp();
        report = Fixtures.createReport("test");
        aeReport = Fixtures.createSavableExpeditedReport();
        aeReport.setReportingPeriod(Fixtures.createReportingPeriod());
        aeReport.addReport(report);
        reportDao = registerDaoMockFor(ReportDao.class);
        reportRepository = registerMockFor(ReportRepository.class);

        command = createCommand();

        errors = new BindException(command, "command");
        reportSubmissionService = registerMockFor(ReportSubmissionService.class);
        errors = new BindException(command, "command");
        tab = createTab();

	}

	public void testReferenceDataHttpServletRequestExpeditedAdverseEventInputCommand() {

		report.setId(new Integer(1));
		command.setReportId("1");
        EasyMock.expect(reportRepository.findReportDeliveries(report)).andReturn(new ArrayList<ReportDelivery>()).anyTimes();
		replayMocks();
		command.setAeReport(aeReport);
		tab.referenceData(request, command);
		assertEquals(0,command.getReportDeliveries().size());
		verifyMocks();
		
	}
	
	//will test pressing of back button on submit page. 
	public void testBackButtonOnSubmitPage(){
		request.setParameter("_target1", "x");
		replayMocks();
		tab.postProcess(request, null, errors); //command is null (should not throw NPE)
		verifyMocks();
	}

	public SubmitExpeditedAdverseEventCommand createCommand() {
        SubmitExpeditedAdverseEventCommand command = new SubmitExpeditedAdverseEventCommand(report, true, reportDao, reportRepository);
        
		return command;
	}



	protected SubmitReportTab createTab() {
		tab = new SubmitReportTab();
		tab.setReportSubmissionService(reportSubmissionService);
		return tab;
	}

}
