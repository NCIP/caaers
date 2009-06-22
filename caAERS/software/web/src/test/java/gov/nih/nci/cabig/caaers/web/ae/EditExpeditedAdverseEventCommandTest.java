/**
 * 
 */
package gov.nih.nci.cabig.caaers.web.ae;

import static org.easymock.EasyMock.expect;
import edu.nwu.bioinformatics.commons.DateUtils;
import gov.nih.nci.cabig.caaers.AbstractNoSecurityTestCase;
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
import gov.nih.nci.cabig.caaers.domain.StudyAgent;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportTree;
import gov.nih.nci.cabig.caaers.domain.report.Mandatory;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.domain.report.ReportMandatoryFieldDefinition;
import gov.nih.nci.cabig.caaers.domain.report.ReportVersion;
import gov.nih.nci.cabig.caaers.domain.report.TimeScaleUnit;
import gov.nih.nci.cabig.caaers.domain.repository.AdverseEventRoutingAndReviewRepository;
import gov.nih.nci.cabig.caaers.domain.repository.AdverseEventRoutingAndReviewRepositoryImpl;
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

import org.easymock.classextension.EasyMock;

/**
 * @author Sameer Work
 * @author Biju Joseph
 */
public class EditExpeditedAdverseEventCommandTest extends AbstractNoSecurityTestCase {
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
    private AdverseEventRoutingAndReviewRepository adverseEventRoutingAndReviewRepository;

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
		expeditedReportTree = new ExpeditedReportTree();
		renderDecisionManager = registerMockFor(RenderDecisionManager.class);
		reportRepository = registerMockFor(ReportRepositoryImpl.class);
		adverseEventRoutingAndReviewRepository = registerMockFor(AdverseEventRoutingAndReviewRepositoryImpl.class);
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
    	command.getAeReport().getReports().get(0).getReportDefinition().setReportType(Fixtures.createConfigProperty("RT_EXPEDITED"));
    	command.getAeReport().getReports().get(2).getReportDefinition().setReportType(Fixtures.createConfigProperty("RT_EXPEDITED"));
    	// Create the list of newlySelectedDefs.
    	// For the test case we will just populate the reportDefinitions of the reports in aeReport in a list
    	List<ReportDefinition> newlySelectedDefs = new ArrayList<ReportDefinition>();
    	for(Report report: command.getAeReport().getReports()){
    		newlySelectedDefs.add(report.getReportDefinition());
    	}
    	
    	command.classifyNewlySelectedReportsDefinitons();
    }
    
    public void testIsNewlySelectedReportEarlier() throws Exception{
    	addReportsToAeReport();
    	Organization organization = Fixtures.createOrganization("testOrg", "testNCICode");
    	Organization organization2 = Fixtures.createOrganization("testOrg2", "testNCICode2");
    	command.getAeReport().getStudy().setPrimaryFundingSponsorOrganization(organization);
    	
    	// Set the report due-dates.
    	String inputDate = "2008-09-12";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    	command.getAeReport().getReports().get(0).setDueOn(formatter.parse(inputDate));
    	inputDate = "2008-09-25";
    	command.getAeReport().getReports().get(1).setDueOn(formatter.parse(inputDate));
    	command.getAeReport().getReports().get(2).setDueOn(formatter.parse(inputDate));
    	command.getAeReport().getReports().get(3).setDueOn(formatter.parse(inputDate));
    	
    	// Set up the organization
    	command.getAeReport().getReports().get(0).getReportDefinition().setOrganization(organization2);
    	command.getAeReport().getReports().get(1).getReportDefinition().setOrganization(organization);
    	command.getAeReport().getReports().get(2).getReportDefinition().setOrganization(organization);
    	command.getAeReport().getReports().get(3).getReportDefinition().setOrganization(organization);
    	
    	// Set amendable
    	command.getAeReport().getReports().get(0).getReportDefinition().setAmendable(true);
    	command.getAeReport().getReports().get(1).getReportDefinition().setAmendable(true);
    	
    	// Add new report-definitons to newlySelectedSponsorReports
    	ReportDefinition reportDefinition = new ReportDefinition();
    	reportDefinition.setTimeScaleUnitType(TimeScaleUnit.DAY);
    	reportDefinition.setDuration(2);
    	command.getNewlySelectedSponsorReports().add(reportDefinition);
    	reportDefinition = new ReportDefinition();
    	reportDefinition.setTimeScaleUnitType(TimeScaleUnit.DAY);
    	reportDefinition.setDuration(3);
    	command.getNewlySelectedSponsorReports().add(reportDefinition);
    	assertFalse(command.isNewlySelectedSponsorReportEarlier());
    }
    
   /* public List<Report> createReportList(){
    	List<Report> reportList = new ArrayList<Report>();
    	for(int i = 0; i < 4; i++){
    		Report report = new Report();
    		report.setId(i);
    		ReportDefinition reportDefinition = new ReportDefinition();
    		reportDefinition.setAmendable(true);
    		reportDefinition.setExpedited(false);
    		reportDefinition.setName("repDefn " + i);
    		report.setReportDefinition(reportDefinition);
    		ReportVersion reportVersion= new ReportVersion();
    		reportVersion.setReportStatus(ReportStatus.PENDING);
    		report.addReportVersion(reportVersion);
    		reportList.add(report);
    	}
    	return reportList;
    }*/
    
    /**
     * This method tests the amendReports method in the command when the Workflow is enabled in the 
     * admin page.
     * @throws Exception
     */
    public void testAmendReportsWorkflowEnabled() throws Exception{
    	addReportsToAeReport();
    	
    	command.getAeReport().setWorkflowId(1);
    	command.setWorkflowEnabled(true);
    	// Make the reports amendable.
    	for(Report report: command.getAeReport().getReports()){
    		report.getReportDefinition().setAmendable(true);
    		//modify the amendment map.
    		command.getAmendedReportsMap().put(report.getReportDefinition(), report);
    	}
    		
    	reportRepository.createAndAmendReport((ReportDefinition) EasyMock.anyObject(), (Report) EasyMock.anyObject(), EasyMock.anyBoolean());
    	EasyMock.expectLastCall().times(4);
    	
    	expect(adverseEventRoutingAndReviewRepository.enactReportWorkflow(command.getAeReport())).andReturn(new Long(1)).times(1);
    
    	replayMocks();
    	command.amendReports();
    	verifyMocks();
    }
    
    /**
     * This method tests the amendReports method in the command when the Workflow is disabled in the
     * admin page.
     * @throws Exception
     */
    public void testAmendReportsWorkflowDisabled() throws Exception{
    	addReportsToAeReport();
    	
    	command.getAeReport().setWorkflowId(1);
    	command.setWorkflowEnabled(false);
    	// Make the reports amendable.
    	for(Report report: command.getAeReport().getReports()){
    		report.getReportDefinition().setAmendable(true);
    		//modify the amendment map.
    		command.getAmendedReportsMap().put(report.getReportDefinition(), report);
    	}
    		
    	reportRepository.createAndAmendReport((ReportDefinition) EasyMock.anyObject(), (Report) EasyMock.anyObject(), EasyMock.anyBoolean());
    	EasyMock.expectLastCall().times(4);
    	replayMocks();
    	command.amendReports();
    	verifyMocks();
    }
    
    /**
     * This method tests the amendReports method in the command, when the reports being amended don't
     * have any workflow associated to them.
     * @throws Exception
     */
    public void testAmendReportsNotAssociatedWithWorkflow() throws Exception{
    	addReportsToAeReport();
    	
    	command.setWorkflowEnabled(true);
    	// Make the reports amendable.
    	for(Report report: command.getAeReport().getReports()){
    		report.getReportDefinition().setAmendable(true);
    		//modify the amendment map.
    		command.getAmendedReportsMap().put(report.getReportDefinition(), report);
    	}
    		
    	reportRepository.createAndAmendReport((ReportDefinition) EasyMock.anyObject(), (Report) EasyMock.anyObject(), EasyMock.anyBoolean());
    	EasyMock.expectLastCall().times(4);
    	
    	replayMocks();
    	command.amendReports();
    	
    	verifyMocks();

    }
    
    public void addReportsToAeReport(){
    	for(int i = 0; i < 4; i++){
    		Report report = new Report();
    		report.setId(i);
    		ReportDefinition reportDefinition = new ReportDefinition();
    		reportDefinition.addReportMandatoryFieldDefinition( new ReportMandatoryFieldDefinition("adverseEvents[].grade",Mandatory.MANDATORY));
    		reportDefinition.addReportMandatoryFieldDefinition( new ReportMandatoryFieldDefinition("adverseEvents[].startDate",Mandatory.MANDATORY));
    		reportDefinition.addReportMandatoryFieldDefinition( new ReportMandatoryFieldDefinition("responseDescription.presentStatus",Mandatory.MANDATORY));
    		reportDefinition.addReportMandatoryFieldDefinition( new ReportMandatoryFieldDefinition("treatmentInformation.treatmentAssignment",Mandatory.MANDATORY));
    		
    		reportDefinition.setAmendable(false);
    		reportDefinition.setReportType(Fixtures.createConfigProperty("NOT_EXPEDITED"));
    		reportDefinition.setName("repDefn " + i);
    		reportDefinition.setId(i);
    		report.setReportDefinition(reportDefinition);
    		ReportVersion reportVersion= new ReportVersion();
    		reportVersion.setReportStatus(ReportStatus.PENDING);
    		report.addReportVersion(reportVersion);
    		command.getAeReport().addReport(report);
    	}
    }
    /**
     * This method tests {@link AbstractExpeditedAdverseEventInputCommand#makeAdverseEventPrimary(Integer)}
     */
    public void testMakeAdverseEventPrimary(){
    	List<AdverseEvent> aeList = command.getAeReport().getAdverseEvents();
    	//makesure 4  aes
    	for(int i = 0; i < 4 ; i++){
    		aeList.get(i).setId(i);
    	}
    	
    	command.makeAdverseEventPrimary(2);
    	assertEquals(new Integer(2), command.getAeReport().getAdverseEvents().get(0).getId());
    	assertEquals(new Integer(0), command.getAeReport().getAdverseEvents().get(1).getId());
    	assertEquals(4, command.getAeReport().getAdverseEvents().size());
    }
    
    /**
     * This method tests {@link AbstractExpeditedAdverseEventInputCommand#makeAdverseEventPrimary(Integer)}
     */
    public void testMakeAdverseEventPrimary_NothingChangedWhenPrimaryAEIdIsNull(){
    	List<AdverseEvent> aeList = command.getAeReport().getAdverseEvents();
    	//makesure 4  aes
    	for(int i = 0; i < 4 ; i++){
    		aeList.get(i).setId(i);
    	}
    	
    	command.makeAdverseEventPrimary(null);
    	assertEquals(new Integer(0), command.getAeReport().getAdverseEvents().get(0).getId());
    	assertEquals(new Integer(1), command.getAeReport().getAdverseEvents().get(1).getId());
    	assertEquals(4, command.getAeReport().getAdverseEvents().size());
    }
    
    /**
     * This method tests {@link AbstractExpeditedAdverseEventInputCommand#makeAdverseEventPrimary(Integer)}
     */
    public void testMakeAdverseEventPrimary_NothingChangedWhenPrimaryAEIdIsZERO(){
    	List<AdverseEvent> aeList = command.getAeReport().getAdverseEvents();
    	//makesure 4  aes
    	for(int i = 0; i < 4 ; i++){
    		aeList.get(i).setId(i);
    	}
    	
    	command.makeAdverseEventPrimary(0);
    	assertEquals(new Integer(0), command.getAeReport().getAdverseEvents().get(0).getId());
    	assertEquals(new Integer(1), command.getAeReport().getAdverseEvents().get(1).getId());
    	assertEquals(4, command.getAeReport().getAdverseEvents().size());
    }
    
    /**
     * This method tests {@link AbstractExpeditedAdverseEventInputCommand#initializeMandatorySectionFields()}
     */
    public void testInitializeMandatorySectionFields(){
    	List<ExpeditedReportSection> sections = new ArrayList<ExpeditedReportSection>();
    	sections.add(ExpeditedReportSection.LABS_SECTION);
    	command.setMandatorySections(sections);
    	
    	assertEquals(0, command.getAeReport().getLabs().size());
    	
    	command.initializeMandatorySectionFields();
    	assertEquals(1, command.getAeReport().getLabs().size());
    	
    	assertEquals(0, command.getAeReport().getTreatmentInformation().getCourseAgents().size());
    }
    
    /**
     * This method tests {@link AbstractExpeditedAdverseEventInputCommand#initializeMandatorySectionFields()}
     */
    public void testInitializeMandatorySectionFieldsWhenAgentInterventionIsNotMandatory(){
    	List<ExpeditedReportSection> sections = new ArrayList<ExpeditedReportSection>();
    	sections.add(ExpeditedReportSection.STUDY_INTERVENTIONS);
    	command.setMandatorySections(sections);
    	assertEquals(0, command.getAeReport().getTreatmentInformation().getCourseAgents().size());
    	command.getAeReport().getStudy().getStudyAgents().clear(); //emptyout the study agents
    	command.initializeMandatorySectionFields();
    	
    	assertEquals(0, command.getAeReport().getTreatmentInformation().getCourseAgents().size());
    }
    
    /**
     * This method tests {@link AbstractExpeditedAdverseEventInputCommand#initializeMandatorySectionFields()}
     */
    public void testInitializeMandatorySectionFieldsWhenInterventionIsMandatory(){
    	List<ExpeditedReportSection> sections = new ArrayList<ExpeditedReportSection>();
    	sections.add(ExpeditedReportSection.AGENTS_INTERVENTION_SECTION);
    	command.setMandatorySections(sections);
    	assertEquals(0, command.getAeReport().getTreatmentInformation().getCourseAgents().size());
    	command.getAeReport().getStudy().getStudyAgents().clear(); //emptyout the study agents
    	command.initializeMandatorySectionFields();
    	
    	assertEquals(1, command.getAeReport().getTreatmentInformation().getCourseAgents().size());
    }
    
    /**
     * This method tests {@link AbstractExpeditedAdverseEventInputCommand#initializeMandatorySectionFields()}
     */
    public void testInitializeMandatorySectionFieldsWhenInterventionIsMandatoryAndStudyHasIND(){
    	List<ExpeditedReportSection> sections = new ArrayList<ExpeditedReportSection>();
    	sections.add(ExpeditedReportSection.AGENTS_INTERVENTION_SECTION);
    	command.setMandatorySections(sections);
    	assertEquals(0, command.getAeReport().getTreatmentInformation().getCourseAgents().size());
    	
    	StudyAgent studyAgent = Fixtures.createStudyAgent("test");
    	studyAgent.setPartOfLeadIND(true);
    	command.getAeReport().getStudy().getStudyAgents().add(studyAgent);
    	
    	command.initializeMandatorySectionFields();
    	assertEquals(1, command.getAeReport().getTreatmentInformation().getCourseAgents().size());
    	
    }
    
    protected final EditExpeditedAdverseEventCommand createMockCommand() {
        return new EditExpeditedAdverseEventCommand(expeditedAeReportDao, reportDefinitionDao, 
        		assignmentDao, reportingPeriodDao, expeditedReportTree, renderDecisionManager, reportRepository, adverseEventRoutingAndReviewRepository, null);
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
        c.setWorkflowEnabled(true);
        return c;
    }
    
    public void testInitializeExistingReportMap() throws Exception{
    	addReportsToAeReport();
    	command.getAeReport().getReports().get(1).getLastVersion().setReportStatus(ReportStatus.COMPLETED);
    	command.getAeReport().getReports().get(3).getLastVersion().setReportStatus(ReportStatus.COMPLETED);
    	
    	command.initializeExistingReportMap();
    	assertEquals(4, command.getExistingReportMap().size());
    }
    
    public void testAmendReportsWithWorkflowEnabled() throws Exception{
    	addReportsToAeReport();
    	command.setWorkflowEnabled(true);
    	command.getAeReport().setWorkflowId(1);
    	// setup the arraylist- reportListForAmendment
    	Report report = command.getAeReport().getReports().get(0);
    	command.getAmendedReportsMap().put(report.getReportDefinition(), report);
    	
    	reportRepository.createAndAmendReport(report.getReportDefinition(), report, false);
    	expect(adverseEventRoutingAndReviewRepository.enactReportWorkflow(command.getAeReport())).andReturn(new Long(1));
    	replayMocks();
    	command.amendReports();
    	verifyMocks();
    }
    
    public void testAmendReportsWithWorkflowDisabled() throws Exception{
    	addReportsToAeReport();
    	command.setWorkflowEnabled(false);
    	Report report = command.getAeReport().getReports().get(0);
    	command.getAmendedReportsMap().put(report.getReportDefinition(), report);
    	reportRepository.createAndAmendReport(report.getReportDefinition(), report, false);

    	replayMocks();
    	command.amendReports();
    	verifyMocks();
    }
    
 
    
    public void testRefreshMandatoryProperties(){
    	addReportsToAeReport();
    	command.getAeReport().setId(5);
    	command.refreshMandatoryProperties();
    	assertTrue(command.getMandatoryProperties().isMandatory("adverseEvents[0].grade"));
    	assertFalse(command.getMandatoryProperties().isMandatory("concomitantMedications[].agentName"));
    }
    
    public void testRefreshMandatoryPropertiesWhenSomeReportsAreInactive(){
    	addReportsToAeReport();
    	command.getAeReport().setId(4);
    	command.getAeReport().getReports().get(0).setStatus(ReportStatus.WITHDRAWN);
    	command.getAeReport().getReports().get(2).setStatus(ReportStatus.WITHDRAWN);
    	command.getAeReport().getReports().get(3).setStatus(ReportStatus.WITHDRAWN);
    	command.getAeReport().getReports().get(1).getReportDefinition().addReportMandatoryFieldDefinition(new ReportMandatoryFieldDefinition("concomitantMedications[].agentName", 
    			Mandatory.MANDATORY));
    	command.refreshMandatoryProperties();
    	assertTrue(command.getMandatoryProperties().isMandatory("adverseEvents[0].grade"));
    	assertTrue(command.getMandatoryProperties().isMandatory("concomitantMedications[].agentName"));
    }
    
    public void testRefreshMandatoryPropertiesWhenAllReportsAreInactive(){
    	addReportsToAeReport();
    	command.getAeReport().setId(4);
    	command.getAeReport().getReports().get(0).setStatus(ReportStatus.WITHDRAWN);
    	command.getAeReport().getReports().get(1).setStatus(ReportStatus.WITHDRAWN);
    	command.getAeReport().getReports().get(2).setStatus(ReportStatus.WITHDRAWN);
    	command.getAeReport().getReports().get(3).setStatus(ReportStatus.WITHDRAWN);
    	command.refreshMandatoryProperties();
    	assertFalse(command.getMandatoryProperties().isMandatory("adverseEvents[0].grade"));
    	assertFalse(command.getMandatoryProperties().isMandatory("concomitantMedications[].agentName"));
    }
    
    public void testRefreshMandatoryPropertiesWhenAeReportIsNew(){
    	addReportsToAeReport();
    	command.getSelectedReportDefinitions().add(command.getAeReport().getReports().get(0).getReportDefinition());
    	command.refreshMandatoryProperties();
    	assertTrue(command.getMandatoryProperties().isMandatory("adverseEvents[0].grade"));
    	assertTrue(command.getMandatoryProperties().isMandatory("adverseEvents[0].startDate"));
    	assertFalse(command.getMandatoryProperties().isMandatory("concomitantMedications[].agentName"));
    }
    
   
    
}
