package gov.nih.nci.cabig.caaers.service;

import gov.nih.nci.cabig.caaers.AbstractNoSecurityTestCase;
import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.api.AdeersReportGenerator;
import gov.nih.nci.cabig.caaers.dao.report.ReportDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.ReportFormatType;
import gov.nih.nci.cabig.caaers.domain.ReportStatus;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.report.ReportContent;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.domain.report.ReportDelivery;
import gov.nih.nci.cabig.caaers.domain.report.ReportDeliveryDefinition;
import gov.nih.nci.cabig.caaers.domain.report.ReportVersion;
import gov.nih.nci.cabig.caaers.esb.client.BroadcastException;
import gov.nih.nci.cabig.caaers.esb.client.impl.CaaersAdeersMessageBroadcastServiceImpl;
import gov.nih.nci.cabig.caaers.tools.mail.CaaersJavaMailSender;
import gov.nih.nci.cabig.ctms.lang.NowFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jms.JMSException;

import org.easymock.classextension.EasyMock;

/**
 * 
 * @author Biju Joseph
 *
 */
public class ReportSubmissionServiceTest extends AbstractNoSecurityTestCase {
	
	ReportSubmissionService service;
	ExpeditedAdverseEventReport aeReport;
	ReportDefinition rd ;
	List<String> emails = new ArrayList<String>();
	List<ReportDelivery> systemDeliveries = new ArrayList<ReportDelivery>();
	ReportVersion lastVersion;
	AdeersReportGenerator generator;
	ReportDao reportDaoMock;
	NowFactory nowFactory;
	SchedulerService scheduler;
	
	String xml;
	String[] pdfFilePaths;
	 
	final Date d = new Date();
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		service = new ReportSubmissionService();
		
		//modify all the key attributes, with mock.
		reportDaoMock = registerDaoMockFor(ReportDao.class);
		
		generator = registerMockFor(AdeersReportGenerator.class);
		scheduler = registerMockFor(SchedulerService.class);
		nowFactory = new NowFactory() {
		 @Override
		 public Date getNow() {
			return d;
		 }	
		};
		service.setNowFactory(nowFactory);
		service.setAdeersReportGenerator(generator);
		service.setCaaersJavaMailSender(new CaaersJavaMailSender() {
			@Override
			public void sendMail(String[] to, String subject, String content,String[] attachmentFilePaths) {
				assertEquals("An AdEERS Expedited Adverse Event Report for C, D C() on Test() has successfully been submitted to AdEERS. Please refer to the attached AdEERS report for complete details.", content);
			}
		});
		
		service.setMessageBroadcastService(new CaaersAdeersMessageBroadcastServiceImpl(){
			@Override
			public void broadcast(String message) throws BroadcastException {
				assertEquals("<AdverseEventReport><EXTERNAL_SYSTEMS>www.biju.com::myusername::password</EXTERNAL_SYSTEMS><REPORT_ID>55</REPORT_ID>" +
						"<SUBMITTER_EMAIL>just@frank.net</SUBMITTER_EMAIL><id>110</id><biju>Joseph</biju></AdverseEventReport>", message);
			}
			@Override
			public void initialize() throws BroadcastException, JMSException {
			}
		});
		service.setSchedulerService(scheduler);
		service.setReportDao(reportDaoMock);
		
		aeReport = Fixtures.createSavableExpeditedReport();
		AdverseEventReportingPeriod rp = Fixtures.createReportingPeriod();
		aeReport.setReportingPeriod(rp);
		rd = Fixtures.createReportDefinition("Joel");
		rd.setReportFormatType(ReportFormatType.ADEERSPDF);
		
		lastVersion = new ReportVersion();
		lastVersion.setSubmitter(Fixtures.createSubmitter());
		lastVersion.setCcEmails("joel@gmail.com");
		lastVersion.setContents(new ArrayList<ReportContent>());
		
		
		ReportDeliveryDefinition deliveryDef = Fixtures.createReportDeliveryDefinition(ReportDeliveryDefinition.ENDPOINT_TYPE_URL, ReportDeliveryDefinition.ENTITY_TYPE_SYSTEM);
		deliveryDef.setUserName("myusername");
		deliveryDef.setPassword("password");
		deliveryDef.setEndPoint("www.biju.com");
		
		ReportDelivery delivery = deliveryDef.createReportDelivery();
		delivery.setEndPoint("www.biju.com");
		systemDeliveries.add(delivery);
		

		xml = "<AdverseEventReport><id>110</id><biju>Joseph</biju></AdverseEventReport>";
		
		pdfFilePaths = new String[]{"dummy.pdf"};
	}
	public void testSubmitReportExternalSystemPath() throws Exception {
		Date now = nowFactory.getNow();
		Report report = registerMockFor(Report.class);
		EasyMock.expect(report.getAeReport()).andReturn(aeReport);
		EasyMock.expect(generator.generateCaaersXml(aeReport)).andReturn(xml);
		EasyMock.expect(report.hasSystemDeliveries()).andReturn(true);
		EasyMock.expect(report.getExternalSystemDeliveries()).andReturn(systemDeliveries);
		EasyMock.expect(report.getLastVersion()).andReturn(lastVersion).anyTimes();
		EasyMock.expect(generator.generateExternalReports(report, xml)).andReturn(pdfFilePaths);
		EasyMock.expect(report.getId()).andReturn(55);
		report.setStatus(ReportStatus.INPROCESS);
		report.setSubmissionUrl("");
		report.setSubmissionMessage("");
		report.setSubmittedOn(nowFactory.getNow());
		reportDaoMock.save(report);
		reportDaoMock.flush();
		replayMocks();
		service.submitReport(report);
		verifyMocks();
	}

	public void testSubmitReportOnlyEmailSubmission() throws Exception {
		Date now = nowFactory.getNow();
		Report report = registerMockFor(Report.class);
		EasyMock.expect(generator.generateCaaersXml(aeReport)).andReturn(xml);
		EasyMock.expect(report.hasSystemDeliveries()).andReturn(false);
		EasyMock.expect(report.getLastVersion()).andReturn(lastVersion).anyTimes();
		EasyMock.expect(generator.generateExternalReports(report, xml)).andReturn(pdfFilePaths);
		report.setStatus(ReportStatus.COMPLETED);
		report.setSubmissionUrl("");
		report.setSubmissionMessage("");
		report.setSubmittedOn(nowFactory.getNow());
		
		EasyMock.expect(report.getEmailRecipients()).andReturn(emails);
		EasyMock.expect(report.getAeReport()).andReturn(aeReport);
		scheduler.unScheduleNotification(report);
		
		reportDaoMock.save(report);
		reportDaoMock.flush();
		replayMocks();
		service.submitReport(report);
		verifyMocks();
	}

	public void testNotifyEmailRecipients() throws Exception {

		Report report = registerMockFor(Report.class);
		emails.add("joel@jj.com");
		EasyMock.expect(report.getEmailRecipients()).andReturn(emails);
		EasyMock.expect(report.getAeReport()).andReturn(aeReport);
		EasyMock.expect(report.getReportDefinition()).andReturn(rd);
		replayMocks();
		service.notifyEmailRecipients(report, xml, pdfFilePaths);
		verifyMocks();
		
	}

	public void testNotifyExternalSystems() throws Exception {
		Report report = registerMockFor(Report.class);
		EasyMock.expect(report.getExternalSystemDeliveries()).andReturn(systemDeliveries);
		EasyMock.expect(report.getLastVersion()).andReturn(lastVersion);
		EasyMock.expect(report.getId()).andReturn(55);
		replayMocks();
		service.notifyExternalSystems(report, xml, pdfFilePaths);
		verifyMocks();
		
	}

	
}
