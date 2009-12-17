package gov.nih.nci.cabig.caaers.web.ae;

import java.util.Arrays;

import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.Physician;
import gov.nih.nci.cabig.caaers.domain.Reporter;
import gov.nih.nci.cabig.caaers.domain.TreatmentInformation;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.report.ReportDelivery;
import gov.nih.nci.cabig.caaers.service.ReportSubmissionService;
import gov.nih.nci.cabig.caaers.web.utils.WebUtils;

import org.easymock.classextension.EasyMock;
import org.springframework.validation.BindException;

/**
 * 
 * @author Biju Joseph
 * 
 */
public class SubmitReportTabTest extends SubmitFlowTabTestCase {

	ReportSubmissionService reportSubmissionService;
	Report report;
	ReportDelivery delivery;
	SubmitReportTab tab;

	protected void setUp() throws Exception {
		super.setUp();
        reportSubmissionService = registerMockFor(ReportSubmissionService.class);
        errors = new BindException(command, "command");
        tab = createTab();
	}

	public void testReferenceDataHttpServletRequestExpeditedAdverseEventInputCommand() {
		report = Fixtures.createReport("test");
		report.setId(new Integer(1));
		delivery = new ReportDelivery();
		command.setReportIndex("0");
		command.setReportId("1");
		
		EasyMock.expect(aeReport.getReports()).andReturn(Arrays.asList(report)).anyTimes();
		EasyMock.expect(reportRepository.findReportDeliveries(report)).andReturn(Arrays.asList(delivery));
		EasyMock.expect(aeReport.getTreatmentInformation()).andReturn(new TreatmentInformation());
		EasyMock.expect(aeReport.getReporter()).andReturn(new Reporter());
		EasyMock.expect(aeReport.getPhysician()).andReturn(new Physician());
		
		replayMocks();
		assertTrue(command.getReportDeliveries().isEmpty());
		
		command.setAeReport(aeReport);
		
		tab.referenceData(request, command);
		assertEquals(1,command.getReportDeliveries().size());
		verifyMocks();
		
	}
	
	//will test pressing of back button on submit page. 
	public void testBackButtonOnSubmitPage(){
		request.setParameter("_target1", "x");
		replayMocks();
		tab.postProcess(request, null, errors); //command is null (should not throw NPE)
		verifyMocks();
	}

	@Override
	public SubmitExpeditedAdverseEventCommand createCommand() {
		SubmitExpeditedAdverseEventCommand command = new SubmitExpeditedAdverseEventCommand(expeditedReportDao, reportDefinitionDao, 
				assignmentDao, reportingPeriodDao, expeditedReportTree,  renderDecisionManager, reportRepository,  adverseEventRoutingAndReviewRepository);
		
		return command;
	}



	protected SubmitReportTab createTab() {
		tab = new SubmitReportTab();
		tab.setReportSubmissionService(reportSubmissionService);
		return tab;
	}

}
