package gov.nih.nci.cabig.caaers.service;

import gov.nih.nci.cabig.caaers.AbstractNoSecurityTestCase;
import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.api.AdeersReportGenerator;
import gov.nih.nci.cabig.caaers.dao.ExpeditedAdverseEventReportDao;
import gov.nih.nci.cabig.caaers.dao.report.ReportDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.Grade;
import gov.nih.nci.cabig.caaers.domain.ReportFormatType;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.domain.report.ReportDelivery;
import gov.nih.nci.cabig.caaers.domain.report.ReportDeliveryDefinition;
import gov.nih.nci.cabig.caaers.domain.report.ReportVersion;
import gov.nih.nci.cabig.caaers.domain.repository.ReportRepository;
import gov.nih.nci.cabig.caaers.esb.client.BroadcastException;
import gov.nih.nci.cabig.caaers.esb.client.impl.CaaersAdeersMessageBroadcastServiceImpl;
import gov.nih.nci.cabig.caaers.service.ReportSubmissionService.ReportSubmissionContext;
import gov.nih.nci.cabig.caaers.tools.mail.CaaersJavaMailSender;
import gov.nih.nci.cabig.ctms.lang.NowFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.jms.JMSException;

import org.easymock.classextension.EasyMock;
import org.springframework.context.MessageSource;

/**
 * 
 * @author Biju Joseph
 *
 */
public class ReportSubmissionServiceTest extends AbstractNoSecurityTestCase {
	
	ReportSubmissionService service;
 	protected CaaersAdeersMessageBroadcastServiceImpl messageBroadcastService;
    protected CaaersJavaMailSender caaersJavaMailSender;
    private AdeersReportGenerator adeersReportGenerator;
    private SchedulerService schedulerService;
    private ReportRepository reportRepository;
    
    private ReportDao reportDao;
	private ExpeditedAdverseEventReportDao expeditedAdverseEventReportDao;
	private MessageSource messageSource;
	
	Report report;
	ExpeditedAdverseEventReport aeReport;
	AdverseEvent ae1, ae2;
	AdverseEventReportingPeriod rp;
	
	ReportDefinition rd ;
	List<String> emails = new ArrayList<String>();
	List<ReportDelivery> systemDeliveries = new ArrayList<ReportDelivery>();
	
	final Date d = new Date();
	
	protected void setUp() throws Exception {
		super.setUp();
		service = new ReportSubmissionService();
		messageBroadcastService = registerMockFor(CaaersAdeersMessageBroadcastServiceImpl.class);
		caaersJavaMailSender = registerMockFor(CaaersJavaMailSender.class);
		adeersReportGenerator = registerMockFor(AdeersReportGenerator.class);
		schedulerService = registerMockFor(SchedulerService.class);
		reportRepository = registerMockFor(ReportRepository.class);
		reportDao = registerDaoMockFor(ReportDao.class);
		expeditedAdverseEventReportDao = registerDaoMockFor(ExpeditedAdverseEventReportDao.class);
		messageSource = registerMockFor(MessageSource.class);
		
		service.setMessageSource(messageSource);
		service.setReportRepository(reportRepository);
		service.setAdeersReportGenerator(adeersReportGenerator);
		service.setSchedulerService(schedulerService);
		service.setNowFactory(new NowFactory() {
			 @Override
			 public Date getNow() {
				return d;
			 }	
		});
		
		service.setReportDao(reportDao);
		service.setExpeditedAdverseEventReportDao(expeditedAdverseEventReportDao);
		service.setCaaersJavaMailSender(new CaaersJavaMailSender() {
			@Override
			public void sendMail(String[] to, String subject, String content,String[] attachmentFilePaths) {
				assertEquals("An AdEERS Expedited Adverse Event Report for C, D C() on Test() has successfully been submitted to AdEERS. Please refer to the attached AdEERS report for complete details.", content);
			}
		});
		
		service.setMessageBroadcastService(new CaaersAdeersMessageBroadcastServiceImpl(){
			@Override
			public void broadcast(String message) throws BroadcastException {
				assertEquals("<AdverseEventReport><EXTERNAL_SYSTEMS>www.biju.com::myusername::password</EXTERNAL_SYSTEMS><REPORT_ID>110</REPORT_ID>" +
						"<SUBMITTER_EMAIL>just@frank.net</SUBMITTER_EMAIL><id>110</id><biju>Joseph</biju></AdverseEventReport>", message);
			}
			@Override
			public void initialize() throws BroadcastException, JMSException {
			}
		});
		
		report = Fixtures.createReport("Test");
		report.setId(110);
		ReportVersion lastVersion =  report.getLastVersion();
		lastVersion.setSubmitter(Fixtures.createSubmitter());
		lastVersion.setCcEmails("joel@gmail.com");
		
		aeReport = Fixtures.createSavableExpeditedReport();
		report.setAeReport(aeReport);
		
		ae1 = Fixtures.createAdverseEvent(1, Grade.DEATH);
		ae1.setPostSubmissionUpdatedDate(new Date());
		ae2 = Fixtures.createAdverseEvent(2, Grade.LIFE_THREATENING);
		ae2.setPostSubmissionUpdatedDate(new Date());
		
		aeReport.addAdverseEvent(ae1);
		aeReport.addAdverseEvent(ae2);
		rp = Fixtures.createReportingPeriod();
		aeReport.setReportingPeriod(rp);
		
		
		rd = Fixtures.createReportDefinition("Joel");
		rd.setReportFormatType(ReportFormatType.ADEERSPDF);
		
		report.setReportDefinition(rd);
		
		
		ReportDeliveryDefinition deliveryDef = Fixtures.createReportDeliveryDefinition(ReportDeliveryDefinition.ENDPOINT_TYPE_URL, 
					ReportDeliveryDefinition.ENTITY_TYPE_SYSTEM);
		deliveryDef.setUserName("myusername");
		deliveryDef.setPassword("password");
		deliveryDef.setEndPoint("www.biju.com");
		
		ReportDelivery delivery = deliveryDef.createReportDelivery();
		delivery.setEndPoint("www.biju.com");
		systemDeliveries.add(delivery);
		
		
	}
	
	public void testGetSubmissionContext() {
		assertSame(report, ReportSubmissionContext.getSubmissionContext(report).report);
	}

	public void testGenerateReportContent() throws Exception{
		EasyMock.expect(adeersReportGenerator.generateCaaersXml(aeReport, report)).andReturn("hello");
		EasyMock.expect(adeersReportGenerator.generateExternalReports(report,"hello",report.getLastVersion().getId())).andReturn(new String[]{"testing"});
		replayMocks();
		
		ReportSubmissionContext context = ReportSubmissionContext.getSubmissionContext(report);
		assertNull(context.caaersXML);
		assertNull(report.getLastVersion().getLastReportTracking().getCaaersXMLGenerated());
		assertNull(report.getLastVersion().getLastReportTracking().getAttachmentGenerated());
		
		service.generateReportContent(context);
		
		assertEquals("hello", context.caaersXML);
		assertEquals("testing", context.pdfReportPaths[0]);
		assertNotNull(report.getLastVersion().getLastReportTracking().getCaaersXMLGenerated());
		assertNotNull(report.getLastVersion().getLastReportTracking().getAttachmentGenerated());
		
		verifyMocks();
		
	}

	public void testDoPreSubmitReport() throws Exception{
		EasyMock.expect(adeersReportGenerator.generateCaaersXml(aeReport, report)).andReturn("hello");
		EasyMock.expect(adeersReportGenerator.generateExternalReports(report,"hello",report.getLastVersion().getId())).andReturn(new String[]{"testing"});
		EasyMock.expect(reportRepository.findReportDeliveries(report)).andReturn(new ArrayList<ReportDelivery>());
		assertTrue(report.getLastVersion().getReportedAdversEvents() == null);
		assertTrue(report.getLastVersion().getContents() == null);
		
		replayMocks();
		
		ReportSubmissionContext context = ReportSubmissionContext.getSubmissionContext(report);
		
		assertNull(context.caaersXML);
		assertNull(report.getLastVersion().getLastReportTracking().getCaaersXMLGenerated());
		assertNull(report.getLastVersion().getLastReportTracking().getAttachmentGenerated());
		
		
		service.doPreSubmitReport(context);
		
		assertEquals("hello", context.caaersXML);
		assertEquals("testing", context.pdfReportPaths[0]);
		assertNotNull(report.getLastVersion().getLastReportTracking().getCaaersXMLGenerated());
		assertNotNull(report.getLastVersion().getLastReportTracking().getAttachmentGenerated());
		
		assertNotNull(report.getLastVersion().getContents());
		
		verifyMocks();
		
	}

	public void testDoPostSubmitReport() {
		
		EasyMock.expect(reportRepository.createChildReports(report)).andReturn(new ArrayList<Report>());
		
		ReportSubmissionContext context = ReportSubmissionContext.getSubmissionContext(report);
		
		assertNull(context.caaersXML);
		assertNull(report.getLastVersion().getLastReportTracking().getCaaersXMLGenerated());
		assertNull(report.getLastVersion().getLastReportTracking().getAttachmentGenerated());
		
		schedulerService.unScheduleNotification(report);
		
		replayMocks();
		service.doPostSubmitReport(context);
		assertNull(ae1.getPostSubmissionUpdatedDate());
		assertNull(ae2.getPostSubmissionUpdatedDate());
		verifyMocks();
	}


	public void testDoSubmit() throws Exception{
		report.setReportDeliveries(systemDeliveries);
		String xml = "<AdverseEventReport><id>110</id><biju>Joseph</biju></AdverseEventReport>";
		EasyMock.expect(adeersReportGenerator.generateCaaersXml(aeReport, report)).andReturn(xml);
		EasyMock.expect(adeersReportGenerator.generateExternalReports(report,xml,report.getLastVersion().getId())).andReturn(new String[]{"dummy.pdf"});
		reportDao.flush();
		reportDao.save(report);
		expeditedAdverseEventReportDao.save(aeReport);
		replayMocks();
		service.submitReport(report);
		assertNotNull(report.getLastVersion().getContents());
		assertNotNull(ae1.getPostSubmissionUpdatedDate());
		assertNotNull(ae2.getPostSubmissionUpdatedDate());
		verifyMocks();
	}

	public void testNotifyEmailRecipients()  throws Exception {
		EasyMock.expect(messageSource.getMessage(   EasyMock.eq("submission.success.subject"), (Object[])EasyMock.anyObject(), EasyMock.eq(Locale.getDefault()))).andReturn("hello");
		EasyMock.expect(messageSource.getMessage(EasyMock.eq("email.submission.content"), 
					(Object[])EasyMock.anyObject(),
					EasyMock.eq(Locale.getDefault())
					)).andReturn("An AdEERS Expedited Adverse Event Report for C, D C() on Test() has successfully been submitted to AdEERS. Please refer to the attached AdEERS report for complete details.");
		emails.add("joel@jj.com");
		replayMocks();
		ReportSubmissionContext context = ReportSubmissionContext.getSubmissionContext(report);
		context.caaersXML = "<AdverseEventReport><id>110</id><biju>Joseph</biju></AdverseEventReport>";
		context.pdfReportPaths = new String[]{"dummy.pdf"};
		
		service.notifyEmailRecipients(context);
		verifyMocks();
	}

	public void testNotifyExternalSystems() throws Exception{
		
		ReportSubmissionContext context = ReportSubmissionContext.getSubmissionContext(report);
		context.caaersXML = "<AdverseEventReport><id>110</id><biju>Joseph</biju></AdverseEventReport>";
		report.setReportDeliveries(systemDeliveries);
		
		replayMocks();
		service.notifyExternalSystems(context);
		verifyMocks();
	}

	
/*
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
				assertEquals("<AdverseEventReport><EXTERNAL_SYSTEMS>www.biju.com::myusername::password</EXTERNAL_SYSTEMS><REPORT_ID>5</REPORT_ID>" +
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
		EasyMock.expect(generator.generateCaaersXml(aeReport,report)).andReturn(xml);
		EasyMock.expect(report.getId()).andReturn(5);
		EasyMock.expect(report.getAeReport()).andReturn(aeReport);
		
		EasyMock.expect(report.hasSystemDeliveries()).andReturn(true);
		EasyMock.expect(report.getExternalSystemDeliveries()).andReturn(systemDeliveries);
		EasyMock.expect(report.getLastVersion()).andReturn(lastVersion).anyTimes();
		EasyMock.expect(generator.generateExternalReports(report, xml)).andReturn(pdfFilePaths);
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
		EasyMock.expect(generator.generateCaaersXml(aeReport,report)).andReturn(xml);
		EasyMock.expect(report.hasSystemDeliveries()).andReturn(false);
		scheduler.unScheduleNotification(report);
		
		EasyMock.expect(report.getLastVersion()).andReturn(lastVersion).anyTimes();
		EasyMock.expect(generator.generateExternalReports(report, xml)).andReturn(pdfFilePaths);
		report.setStatus(ReportStatus.COMPLETED);
		report.setSubmissionUrl("");
		report.setSubmissionMessage("");
		report.setSubmittedOn(nowFactory.getNow());
		
		EasyMock.expect(report.getEmailRecipients()).andReturn(emails);
		EasyMock.expect(report.getAeReport()).andReturn(aeReport);
		
		
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
		ReportTracking reportTracking = new ReportTracking();
		service.notifyEmailRecipients(report, xml, pdfFilePaths,reportTracking);
		verifyMocks();
		
	}

	public void testNotifyExternalSystems() throws Exception {
		Report report = registerMockFor(Report.class);
		EasyMock.expect(report.getExternalSystemDeliveries()).andReturn(systemDeliveries);
		EasyMock.expect(report.getLastVersion()).andReturn(lastVersion);
		EasyMock.expect(report.getId()).andReturn(5);
		replayMocks();
		ReportTracking reportTracking = new ReportTracking();
		service.notifyExternalSystems(report, xml, reportTracking);
		verifyMocks();
		
	}
*/
	
}
