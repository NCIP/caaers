package gov.nih.nci.cabig.caaers.domain.repository;

import edu.emory.mathcs.backport.java.util.Arrays;
import gov.nih.nci.cabig.caaers.AbstractNoSecurityTestCase;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.report.ReportDao;
import gov.nih.nci.cabig.caaers.dao.report.ReportDefinitionDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.Attribution;
import gov.nih.nci.cabig.caaers.domain.ConfigProperty;
import gov.nih.nci.cabig.caaers.domain.CtcTerm;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.Investigator;
import gov.nih.nci.cabig.caaers.domain.LocalInvestigator;
import gov.nih.nci.cabig.caaers.domain.LocalResearchStaff;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.ReportStatus;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.domain.SiteInvestigator;
import gov.nih.nci.cabig.caaers.domain.SiteResearchStaff;
import gov.nih.nci.cabig.caaers.domain.StudyInvestigator;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.StudyPersonnel;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.domain.attribution.AdverseEventAttribution;
import gov.nih.nci.cabig.caaers.domain.factory.ReportFactory;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.domain.report.ReportType;
import gov.nih.nci.cabig.caaers.service.SchedulerService;
import gov.nih.nci.cabig.caaers.tools.configuration.Configuration;
import gov.nih.nci.cabig.ctms.domain.DomainObject;
import gov.nih.nci.cabig.ctms.lang.NowFactory;

import java.util.Date;

import org.easymock.EasyMock;

/**
 * @author Rhett Sutphin
 * @author Biju Joseph
 */
public class ReportRepositoryTest extends AbstractNoSecurityTestCase {

    private static final String TERM = "Auralmonagem";

    private ReportRepositoryImpl reportRepository;

    private ExpeditedAdverseEventReport expeditedData;
    
    private ReportDao reportDao;
    private ReportFactory reportFactory;
    private SchedulerService schedulerService;
    private NowFactory nowFactory;
    private ReportDefinitionDao reportDefinitionDao;
    private StudyDao studyDao;
    private Configuration configuration;

    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        reportRepository = new ReportRepositoryImpl();
        reportDao = registerDaoMockFor(ReportDao.class);
        reportDefinitionDao = registerDaoMockFor(ReportDefinitionDao.class);
        reportFactory = registerMockFor(ReportFactory.class);
        schedulerService = registerMockFor(SchedulerService.class);
        studyDao = registerDaoMockFor(StudyDao.class);
        configuration = registerMockFor(Configuration.class);
        nowFactory = new NowFactory();
        
        
        reportRepository.setReportDao(reportDao);
        reportRepository.setReportFactory(reportFactory);
        reportRepository.setSchedulerService(schedulerService);
        reportRepository.setNowFactory(nowFactory);
        reportRepository.setReportDefinitionDao(reportDefinitionDao);
        reportRepository.setStudyDao(studyDao);
        reportRepository.setConfiguration(configuration);
        
        
        expeditedData = new ExpeditedAdverseEventReport();
        CtcTerm ctcTerm = new CtcTerm();
        ctcTerm.setTerm(TERM);
        expeditedData.getAdverseEvents().get(0).getAdverseEventCtcTerm().setCtcTerm(ctcTerm);
    }

    public void testFindEmailAddress() {
        StudyParticipantAssignment assignment = Fixtures.createAssignment();
        StudySite site = assignment.getStudySite();
        StudyInvestigator studyInvestigator = new StudyInvestigator();
        studyInvestigator.setRoleCode("Site Principal Investigator");
        SiteInvestigator siteInvestigator = new SiteInvestigator();
        Investigator investigator = new LocalInvestigator();
        investigator.setEmailAddress("biju@kk.com");
        siteInvestigator.setInvestigator(investigator);
        studyInvestigator.setSiteInvestigator(siteInvestigator);
        site.addStudyInvestigators(studyInvestigator);

        ResearchStaff staff = new LocalResearchStaff();
        SiteResearchStaff siteResearchStaff = new SiteResearchStaff();
        staff.setEmailAddress("aa@kk.com");
        siteResearchStaff.setResearchStaff(staff);
        StudyPersonnel studyPersonnel = new StudyPersonnel();
        studyPersonnel.setRoleCode("Participant Coordinator");
        studyPersonnel.setSiteResearchStaff(siteResearchStaff);
        site.addStudyPersonnel(studyPersonnel);

        AdverseEventReportingPeriod reportingPeriod = new AdverseEventReportingPeriod();
        reportingPeriod.setAssignment(assignment);
        expeditedData.setReportingPeriod(reportingPeriod);
        
        ReportRepository impl = (ReportRepository) reportRepository;
//        List<String> addresses = expeditedData.findEmailAddress("SPI", impl);
//        assertEquals(1, addresses.size());
//        List<String> addresses2 = expeditedData.findEmailAddress("PC", impl);
//        assertEquals(1, addresses2.size());
    }
    
    
    public void testUnAmendReport(){
    	Report report = Fixtures.createReport("test");
    	report.setStatus(ReportStatus.AMENDED);
    	report.setAmendedOn(new Date());
    	
    	reportDao.save(report);
    	
    	replayMocks();
    	
    	assertNotNull(report.getAmendedOn());
    	reportRepository.unAmendReport(report);
    	
    	assertNull(report.getAmendedOn());
    	assertEquals(ReportStatus.COMPLETED, report.getLastVersion().getReportStatus());
    	
    	verifyMocks();
    }
    
    public void testWithdrawReport(){
    	Report report = Fixtures.createReport("test");
    	report.setStatus(ReportStatus.INPROCESS);
    	
    	schedulerService.unScheduleNotification(report);
    	reportDao.save(report);
    	
    	replayMocks();
    	
    	assertNull(report.getWithdrawnOn());
    	reportRepository.withdrawReport(report);
    	
    	assertNotNull(report.getWithdrawnOn());
    	assertEquals(ReportStatus.WITHDRAWN, report.getLastVersion().getReportStatus());
    	
    	verifyMocks();
    }
    
    public void testCreateReport(){
    	
    	Organization org = Fixtures.createOrganization("test", 1);
    	
    	ConfigProperty cp1 = Fixtures.createConfigProperty("t1");
    	cp1.setId(1);
    	
    	ReportDefinition rd1 = Fixtures.createReportDefinition("rd1", org, cp1);
    	rd1.setId(1);
    	
    	StudyParticipantAssignment assignment = Fixtures.createAssignment();
    	AdverseEventReportingPeriod reportingPeriod = new AdverseEventReportingPeriod();
        reportingPeriod.setAssignment(assignment);
        expeditedData.setReportingPeriod(reportingPeriod);
        
        Report report = Fixtures.createReport("test");
    	report.setStatus(ReportStatus.INPROCESS);
    	report.setReportDefinition(rd1);
        
        studyDao.reassociateStudyOrganizations(expeditedData.getStudy().getStudyOrganizations());
        
        EasyMock.expect(reportFactory.createReport(rd1,expeditedData, rd1.getBaseDate())).andReturn(report);
        EasyMock.expect(configuration.get(Configuration.ENABLE_WORKFLOW)).andReturn(false);
//        reportDefinitionDao.lock(rd1);
        reportDao.save(report);
        schedulerService.scheduleNotification(report);
        
    	replayMocks();
    	reportRepository.createReport(rd1, expeditedData);
    	
    	verifyMocks();
    }
    
    //case, when we have to increment the amendment number, ie. creation by amending another. 
    public void testCreateReport_WhenThereExistsSubmitted_REPORT(){
    	
    	Organization org = Fixtures.createOrganization("test", 1);
    	
    	ConfigProperty cp1 = Fixtures.createConfigProperty("t1");
    	cp1.setId(1);
    	
    	ReportDefinition rd1 = Fixtures.createReportDefinition("rd1", org, cp1);
    	rd1.setId(1);
    	rd1.setReportType(ReportType.REPORT);
    	
    	StudyParticipantAssignment assignment = Fixtures.createAssignment();
    	AdverseEventReportingPeriod reportingPeriod = new AdverseEventReportingPeriod();
        reportingPeriod.setAssignment(assignment);
        expeditedData.setReportingPeriod(reportingPeriod);
        
        Report report = Fixtures.createReport("test");
    	report.setStatus(ReportStatus.INPROCESS);
    	report.setReportDefinition(rd1);
    	
    	Report reportSubmitted = Fixtures.createReport("submitted");
    	reportSubmitted.setStatus(ReportStatus.COMPLETED);
    	reportSubmitted.getLastVersion().setReportVersionId("0");
    	reportSubmitted.setReportDefinition(rd1);
    	expeditedData.addReport(reportSubmitted);
    	
        
        studyDao.reassociateStudyOrganizations(expeditedData.getStudy().getStudyOrganizations());
        
        EasyMock.expect(reportFactory.createReport(rd1,expeditedData, rd1.getBaseDate())).andReturn(report);
        EasyMock.expect(configuration.get(Configuration.ENABLE_WORKFLOW)).andReturn(false);
//        reportDefinitionDao.lock(rd1);
        reportDao.save(report);
        schedulerService.scheduleNotification(report);
        
    	replayMocks();
    	reportRepository.createReport(rd1, expeditedData);
    	assertEquals("1" ,report.getLastVersion().getReportVersionId());
    	verifyMocks();
    }
    
    //create flow checked.
    public void testProcessReports(){
    	
    	Organization org = Fixtures.createOrganization("test", 1);
    	
    	ConfigProperty cp1 = Fixtures.createConfigProperty("t1");
    	cp1.setId(1);
    	
    	ReportDefinition rd1 = Fixtures.createReportDefinition("rd1", org, cp1);
    	rd1.setId(1);
    	
    	StudyParticipantAssignment assignment = Fixtures.createAssignment();
    	AdverseEventReportingPeriod reportingPeriod = new AdverseEventReportingPeriod();
        reportingPeriod.setAssignment(assignment);
        expeditedData.setReportingPeriod(reportingPeriod);
        
        Report report = Fixtures.createReport("test");
    	report.setStatus(ReportStatus.INPROCESS);
    	report.setReportDefinition(rd1);
        
        studyDao.reassociateStudyOrganizations(expeditedData.getStudy().getStudyOrganizations());
        
        EasyMock.expect(reportFactory.createReport(rd1,expeditedData, rd1.getBaseDate())).andReturn(report);
        EasyMock.expect(configuration.get(Configuration.ENABLE_WORKFLOW)).andReturn(false);
        
//        reportDefinitionDao.lock(rd1);
        reportDao.save(report);
        schedulerService.scheduleNotification(report);
        
    	replayMocks();
    	reportRepository.processReports(expeditedData, null, null, null, Arrays.asList(new ReportDefinition[]{rd1}));
    	
    	verifyMocks();
    }
    //will test report getting replaced. 
    public void testIsGettingReplaced(){
    	
    	Organization org = Fixtures.createOrganization("test", 1);
    	
    	ConfigProperty cp1 = Fixtures.createConfigProperty("t1");
    	cp1.setId(1);
    	ConfigProperty cp2 = Fixtures.createConfigProperty("t2");
    	cp2.setId(2);
    	
    	ReportDefinition rd1 = Fixtures.createReportDefinition("rd1", org, cp1);
    	rd1.setId(1);
    	ReportDefinition rd2 = Fixtures.createReportDefinition("rd2", org, cp2);
    	rd2.setId(2);
    	
    	Report r1 = Fixtures.createReport("test");
    	r1.setReportDefinition(rd1);
    	r1.setId(1);
    	
    	Report r2 = Fixtures.createReport("test2");
    	r2.setId(2);
    	r2.getReportDefinition().setId(3);
    	
    	boolean replaced = reportRepository.isGettingReplaced(r1, Arrays.asList(new ReportDefinition[]{rd1, rd2}));
    	assertTrue(replaced);
    	
    	replaced = reportRepository.isGettingReplaced(r2, Arrays.asList(new ReportDefinition[]{rd1, rd2}));
    	assertFalse(replaced);
    	
    	replaced = reportRepository.isGettingReplaced(r2, Arrays.asList(new ReportDefinition[]{}));
    	assertFalse(replaced);
    	
    	replaced = reportRepository.isGettingReplaced(r2, null);
    	assertFalse(replaced);
    }

    private Report createAttributionMandatoryReport() {
        Report report = new Report();
        report.setReportDefinition(new ReportDefinition());
        report.setAeReport(expeditedData);
        report.getReportDefinition().setAttributionRequired(true);
        // TODO:
        // report.setAttributionMandatory(true);
        return report;
    }

    private <C extends DomainObject, A extends AdverseEventAttribution<C>> A createAttribution(C cause, Attribution level, Class<A> klass) throws IllegalAccessException,
            InstantiationException {
        A attr = klass.newInstance();
        attr.setAttribution(level);
        attr.setCause(cause);
        return attr;
    }

}
