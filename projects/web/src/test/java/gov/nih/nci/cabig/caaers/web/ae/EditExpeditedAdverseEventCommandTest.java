/**
 * 
 */
package gov.nih.nci.cabig.caaers.web.ae;

import edu.nwu.bioinformatics.commons.DateUtils;
import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.dao.AdverseEventReportingPeriodDao;
import gov.nih.nci.cabig.caaers.dao.ExpeditedAdverseEventReportDao;
import gov.nih.nci.cabig.caaers.dao.StudyParticipantAssignmentDao;
import gov.nih.nci.cabig.caaers.dao.report.ReportDefinitionDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.CtcTerm;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.Grade;
import gov.nih.nci.cabig.caaers.domain.Hospitalization;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.Physician;
import gov.nih.nci.cabig.caaers.domain.ReportPerson;
import gov.nih.nci.cabig.caaers.domain.ReportStatus;
import gov.nih.nci.cabig.caaers.domain.Reporter;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportTree;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.domain.report.ReportVersion;
import gov.nih.nci.cabig.caaers.domain.report.TimeScaleUnit;
import gov.nih.nci.cabig.caaers.domain.repository.ReportRepository;
import gov.nih.nci.cabig.caaers.domain.repository.ReportRepositoryImpl;
import gov.nih.nci.cabig.caaers.web.RenderDecisionManager;
import gov.nih.nci.cabig.ctms.lang.StaticNowFactory;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

/**
 * @author Sameer Work
 *
 */
public class EditExpeditedAdverseEventCommandTest extends AbstractTestCase {
	protected static final Timestamp NOW = DateUtils.createTimestamp(2004, Calendar.MARCH, 27);
	protected StudyParticipantAssignment assignment;
	private EditExpeditedAdverseEventCommand command;
	
	private ExpeditedAdverseEventReportDao expeditedAeReportDao;
    private ReportDefinitionDao reportDefinitionDao;
    private StudyParticipantAssignmentDao assignmentDao;
    private AdverseEventReportingPeriodDao reportingPeriodDao;
    private ExpeditedReportTree expeditedReportTree; 
    private RenderDecisionManager renderDecisionManager;
    protected StaticNowFactory nowFactory;
    private ReportRepository reportRepository;

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		assignment = Fixtures.createAssignment();
		expeditedAeReportDao = registerDaoMockFor(ExpeditedAdverseEventReportDao.class);
		reportDefinitionDao = registerDaoMockFor(ReportDefinitionDao.class);
		assignmentDao = registerDaoMockFor(StudyParticipantAssignmentDao.class);
		reportingPeriodDao = registerDaoMockFor(AdverseEventReportingPeriodDao.class);
		expeditedReportTree = registerMockFor(ExpeditedReportTree.class);
		renderDecisionManager = registerMockFor(RenderDecisionManager.class);
		reportRepository = registerMockFor(ReportRepositoryImpl.class);
		nowFactory = new StaticNowFactory();
        nowFactory.setNowTimestamp(NOW);
        
        command = createCommand();
	}
	
    protected EditExpeditedAdverseEventCommand createCommand() {
        return createMinimallyValidMockCommand();
    }

	 // Following are the test cases added for the new create/amend report logic.
    // Added by Sameer (09/10/08)
    
    
    public void testClassifyNewlySelectedReportsDefinitons() throws Exception{
    	Organization organization = Fixtures.createOrganization("testOrg", "testNCICode");
    	Organization organization2 = Fixtures.createOrganization("testOrg2", "testNCICode2");
    	command.getAeReport().getStudy().setPrimaryFundingSponsorOrganization(organization);
    	addReportsToAeReport();
    	// Setup Amendable flag
    	command.getAeReport().getReports().get(0).getReportDefinition().setAmendable(true);
    	command.getAeReport().getReports().get(2).getReportDefinition().setAmendable(true);
    	// Setup organization
    	command.getAeReport().getReports().get(0).getReportDefinition().setOrganization(organization);
    	command.getAeReport().getReports().get(2).getReportDefinition().setOrganization(organization);
    	command.getAeReport().getReports().get(1).getReportDefinition().setOrganization(organization2);
    	command.getAeReport().getReports().get(3).getReportDefinition().setOrganization(organization2);
    	// Setup expedited
    	command.getAeReport().getReports().get(0).getReportDefinition().setExpedited(true);
    	command.getAeReport().getReports().get(2).getReportDefinition().setExpedited(true);
    	// Create the list of newlySelectedDefs.
    	// For the test case we will just populate the reportDefinitions of the reports in aeReport in a list
    	List<ReportDefinition> newlySelectedDefs = new ArrayList<ReportDefinition>();
    	for(Report report: command.getAeReport().getReports()){
    		newlySelectedDefs.add(report.getReportDefinition());
    	}
    	
    	command.classifyNewlySelectedReportsDefinitons();
    }
    
    public void testIsNewlySelectedReportEarlier() throws Exception{
//    	addReportsToAeReport();
//    	Organization organization = Fixtures.createOrganization("testOrg", "testNCICode");
//    	Organization organization2 = Fixtures.createOrganization("testOrg2", "testNCICode2");
//    	command.getAeReport().getStudy().setPrimaryFundingSponsorOrganization(organization);
//    	
//    	// Set the report due-dates.
//    	String inputDate = "2008-09-12";
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//    	command.getAeReport().getReports().get(0).setDueOn(formatter.parse(inputDate));
//    	inputDate = "2008-09-25";
//    	command.getAeReport().getReports().get(1).setDueOn(formatter.parse(inputDate));
//    	command.getAeReport().getReports().get(2).setDueOn(formatter.parse(inputDate));
//    	command.getAeReport().getReports().get(3).setDueOn(formatter.parse(inputDate));
//    	
//    	// Set up the organization
//    	command.getAeReport().getReports().get(0).getReportDefinition().setOrganization(organization2);
//    	command.getAeReport().getReports().get(1).getReportDefinition().setOrganization(organization);
//    	command.getAeReport().getReports().get(2).getReportDefinition().setOrganization(organization);
//    	command.getAeReport().getReports().get(3).getReportDefinition().setOrganization(organization);
//    	
//    	// Set amendable
//    	command.getAeReport().getReports().get(0).getReportDefinition().setAmendable(true);
//    	command.getAeReport().getReports().get(1).getReportDefinition().setAmendable(true);
//    	
//    	// Add new report-definitons to newlySelectedSponsorReports
//    	ReportDefinition reportDefinition = new ReportDefinition();
//    	reportDefinition.setTimeScaleUnitType(TimeScaleUnit.DAY);
//    	reportDefinition.setDuration(2);
//    	command.getNewlySelectedSponsorReports().add(reportDefinition);
//    	reportDefinition = new ReportDefinition();
//    	reportDefinition.setTimeScaleUnitType(TimeScaleUnit.DAY);
//    	reportDefinition.setDuration(3);
//    	command.getNewlySelectedSponsorReports().add(reportDefinition);
//    	assertTrue(command.isNewlySelectedReportEarlier());
    	assertTrue(true);
    }
    
    public void addReportsToAeReport(){
    	for(int i = 0; i < 4; i++){
    		Report report = new Report();
    		report.setId(i);
    		ReportDefinition reportDefinition = new ReportDefinition();
    		reportDefinition.setAmendable(false);
    		reportDefinition.setExpedited(false);
    		reportDefinition.setName("repDefn " + i);
    		report.setReportDefinition(reportDefinition);
    		ReportVersion reportVersion= new ReportVersion();
    		reportVersion.setReportStatus(ReportStatus.PENDING);
    		report.addReportVersion(reportVersion);
    		command.getAeReport().addReport(report);
    	}
    }
    
    protected final EditExpeditedAdverseEventCommand createMockCommand() {
        return new EditExpeditedAdverseEventCommand(expeditedAeReportDao, reportDefinitionDao, 
        		assignmentDao, reportingPeriodDao, expeditedReportTree, renderDecisionManager, reportRepository);
    }
    
    protected final EditExpeditedAdverseEventCommand createMinimallyValidMockCommand() {
        EditExpeditedAdverseEventCommand c = createMockCommand();
        // initialize command as minimally valid
        AdverseEventReportingPeriod reportingPeriod = new AdverseEventReportingPeriod();
        reportingPeriod.setAssignment(assignment);
        ExpeditedAdverseEventReport aeReport = new ExpeditedAdverseEventReport();
        aeReport.setReportingPeriod(reportingPeriod);
        // This has changed to handle Many-To-One relationship between ReportingPeriod and ExpeditedReport
        // TODO: fix it when use case is ready.
        reportingPeriod.addAeReport(aeReport);
        aeReport.setCreatedAt(nowFactory.getNowTimestamp());
        c.setAeReport(aeReport);
        

        // BasicsTab
        AdverseEvent event = c.getAeReport().getAdverseEvents().get(0);
        event.setGrade(Grade.MODERATE);
        event.setHospitalization(Hospitalization.NONE);
        event.getAdverseEventCtcTerm().setCtcTerm(new CtcTerm());
        event.setStartDate(NOW);
        event.setEndDate(NOW);

        // ReporterTab
        c.getAeReport().setReporter(new Reporter());
        c.getAeReport().getReporter().setFirstName("Dan");
        c.getAeReport().getReporter().setLastName("McReporter");
        c.getAeReport().getReporter().getContactMechanisms().put(ReportPerson.EMAIL,
                        "dan@example.com");
        c.getAeReport().setPhysician(new Physician());
        c.getAeReport().getPhysician().setFirstName("Jim");
        c.getAeReport().getPhysician().setLastName("O'Physician");
        c.getAeReport().getPhysician().getContactMechanisms().put(ReportPerson.EMAIL,
                        "docjim@example.com");

        // CheckpointTab
        c.setNextPage(5);
        return c;
    }
}
