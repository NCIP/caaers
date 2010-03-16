/**
 * 
 */
package gov.nih.nci.cabig.caaers.web.ae;

import edu.nwu.bioinformatics.commons.DateUtils;
import gov.nih.nci.cabig.caaers.AbstractNoSecurityTestCase;
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
import gov.nih.nci.cabig.caaers.domain.Physician;
import gov.nih.nci.cabig.caaers.domain.ReportPerson;
import gov.nih.nci.cabig.caaers.domain.ReportStatus;
import gov.nih.nci.cabig.caaers.domain.Reporter;
import gov.nih.nci.cabig.caaers.domain.StudyAgent;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportTree;
import gov.nih.nci.cabig.caaers.domain.report.*;
import gov.nih.nci.cabig.caaers.domain.repository.AdverseEventRoutingAndReviewRepository;
import gov.nih.nci.cabig.caaers.domain.repository.AdverseEventRoutingAndReviewRepositoryImpl;
import gov.nih.nci.cabig.caaers.domain.repository.ReportRepository;
import gov.nih.nci.cabig.caaers.domain.repository.ReportRepositoryImpl;
import gov.nih.nci.cabig.caaers.rules.business.service.EvaluationServiceImpl;
import gov.nih.nci.cabig.caaers.service.EvaluationService;
import gov.nih.nci.cabig.caaers.web.RenderDecisionManager;
import gov.nih.nci.cabig.ctms.lang.StaticNowFactory;

import java.sql.Timestamp;
import java.util.*;


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
    private EvaluationService evaluationService;

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
        evaluationService = new EvaluationServiceImpl(){
            public void evaluateMandatoryness(ExpeditedAdverseEventReport aeReport, Report report) {
                Fixtures.updateMandatoryFields(report.getReportDefinition(), report);
            }
        };
        
        command = createCommand();
	}
	
    protected EditExpeditedAdverseEventCommand createCommand() {
        return createMinimallyValidMockCommand();
    }

	
//  Below test cases will be fixed after we finalize workflow related changes....
    
//    /**
//     * This method tests the amendReports method in the command when the Workflow is enabled in the 
//     * admin page.
//     * @throws Exception
//     */
//    public void testAmendReportsWorkflowEnabled() throws Exception{
////    	addReportsToAeReport();
////    	
////    	command.getAeReport().setWorkflowId(1);
////    	command.setWorkflowEnabled(true);
////    	// Make the reports amendable.
////    	for(Report report: command.getAeReport().getReports()){
////    		report.getReportDefinition().setAmendable(true);
////    		//modify the amendment map.
////    		command.getAmendedReportsMap().put(report.getReportDefinition(), report);
////    	}
////    		
////    	reportRepository.createAndAmendReport((ReportDefinition) EasyMock.anyObject(), (Report) EasyMock.anyObject(), EasyMock.anyBoolean());
////    	EasyMock.expectLastCall().times(4);
////    	
////    	expect(adverseEventRoutingAndReviewRepository.enactReportWorkflow(command.getAeReport())).andReturn(new Long(1)).times(1);
////    
////    	replayMocks();
////    	command.amendReports();
////    	verifyMocks();
//    	
//    	fail("BJ fix me");
//    }
//    
//    /**
//     * This method tests the amendReports method in the command when the Workflow is disabled in the
//     * admin page.
//     * @throws Exception
//     */
//    public void testAmendReportsWorkflowDisabled() throws Exception{
////    	addReportsToAeReport();
////    	
////    	command.getAeReport().setWorkflowId(1);
////    	command.setWorkflowEnabled(false);
////    	// Make the reports amendable.
////    	for(Report report: command.getAeReport().getReports()){
////    		report.getReportDefinition().setAmendable(true);
////    		//modify the amendment map.
////    		command.getAmendedReportsMap().put(report.getReportDefinition(), report);
////    	}
////    		
////    	reportRepository.createAndAmendReport((ReportDefinition) EasyMock.anyObject(), (Report) EasyMock.anyObject(), EasyMock.anyBoolean());
////    	EasyMock.expectLastCall().times(4);
////    	replayMocks();
////    	command.amendReports();
////    	verifyMocks();
//    	fail("BJ fix me");
//    }
//    
//    /**
//     * This method tests the amendReports method in the command, when the reports being amended don't
//     * have any workflow associated to them.
//     * @throws Exception
//     */
//    public void testAmendReportsNotAssociatedWithWorkflow() throws Exception{
////    	addReportsToAeReport();
////    	
////    	command.setWorkflowEnabled(true);
////    	// Make the reports amendable.
////    	for(Report report: command.getAeReport().getReports()){
////    		report.getReportDefinition().setAmendable(true);
////    		//modify the amendment map.
////    		command.getAmendedReportsMap().put(report.getReportDefinition(), report);
////    	}
////    		
////    	reportRepository.createAndAmendReport((ReportDefinition) EasyMock.anyObject(), (Report) EasyMock.anyObject(), EasyMock.anyBoolean());
////    	EasyMock.expectLastCall().times(4);
////    	
////    	replayMocks();
////    	command.amendReports();
////    	
////    	verifyMocks();
//    	fail("BJ fix me");
//
//    }
//    
//    
//    public void testAmendReportsWithWorkflowEnabled() throws Exception{
////    	addReportsToAeReport();
////    	command.setWorkflowEnabled(true);
////    	command.getAeReport().setWorkflowId(1);
////    	// setup the arraylist- reportListForAmendment
////    	Report report = command.getAeReport().getReports().get(0);
////    	command.getAmendedReportsMap().put(report.getReportDefinition(), report);
////    	
////    	reportRepository.createAndAmendReport(report.getReportDefinition(), report, false);
////    	expect(adverseEventRoutingAndReviewRepository.enactReportWorkflow(command.getAeReport())).andReturn(new Long(1));
////    	replayMocks();
////    	command.amendReports();
////    	verifyMocks();
//    	fail("BJ fix me");
//    }
//    
//    public void testAmendReportsWithWorkflowDisabled() throws Exception{
////    	addReportsToAeReport();
////    	command.setWorkflowEnabled(false);
////    	Report report = command.getAeReport().getReports().get(0);
////    	command.getAmendedReportsMap().put(report.getReportDefinition(), report);
////    	reportRepository.createAndAmendReport(report.getReportDefinition(), report, false);
////
////    	replayMocks();
////    	command.amendReports();
////    	verifyMocks();
//    	fail("BJ fix me");
//    }
    
    public void addReportsToAeReport(){
    	for(int i = 0; i < 4; i++){
    		Report report = new Report();
    		report.setId(i);
    		ReportDefinition reportDefinition = new ReportDefinition();
    		reportDefinition.addReportMandatoryFieldDefinition( new ReportMandatoryFieldDefinition("adverseEvents[].grade", RequirednessIndicator.MANDATORY));
    		reportDefinition.addReportMandatoryFieldDefinition( new ReportMandatoryFieldDefinition("adverseEvents[].startDate",RequirednessIndicator.MANDATORY));
    		reportDefinition.addReportMandatoryFieldDefinition( new ReportMandatoryFieldDefinition("responseDescription.presentStatus",RequirednessIndicator.MANDATORY));
    		reportDefinition.addReportMandatoryFieldDefinition( new ReportMandatoryFieldDefinition("treatmentInformation.treatmentAssignment",RequirednessIndicator.MANDATORY));
    		
    		reportDefinition.setAmendable(false);
    		reportDefinition.setGroup(Fixtures.createConfigProperty("NOT_EXPEDITED"));
    		reportDefinition.setName("repDefn " + i);
    		reportDefinition.setId(i);
    		report.setReportDefinition(reportDefinition);
    		ReportVersion reportVersion=  report.getLastVersion();
    		reportVersion.setReportStatus(ReportStatus.PENDING);
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
        Map<Integer, Collection<ExpeditedReportSection>> map = new HashMap<Integer, Collection<ExpeditedReportSection>>();
        map.put(0, sections);
    	command.setMandatorySectionMap(map);
    	
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
        Map<Integer, Collection<ExpeditedReportSection>> map = new HashMap<Integer, Collection<ExpeditedReportSection>>();
        map.put(0, sections);
    	command.setMandatorySectionMap(map);
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
        Map<Integer, Collection<ExpeditedReportSection>> map = new HashMap<Integer, Collection<ExpeditedReportSection>>();
        map.put(0, sections);
    	command.setMandatorySectionMap(map);
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
        Map<Integer, Collection<ExpeditedReportSection>> map = new HashMap<Integer, Collection<ExpeditedReportSection>>();
        map.put(0, sections);
    	command.setMandatorySectionMap(map);
    	assertEquals(0, command.getAeReport().getTreatmentInformation().getCourseAgents().size());
    	
    	StudyAgent studyAgent = Fixtures.createStudyAgent("test");
    	studyAgent.setPartOfLeadIND(true);
    	command.getAeReport().getStudy().getStudyAgents().add(studyAgent);
    	
    	command.initializeMandatorySectionFields();
    	assertEquals(1, command.getAeReport().getTreatmentInformation().getCourseAgents().size());
    	
    }
    
    protected final EditExpeditedAdverseEventCommand createMockCommand() {
        return new EditExpeditedAdverseEventCommand(expeditedAeReportDao, reportDefinitionDao, 
        		assignmentDao, reportingPeriodDao, expeditedReportTree, renderDecisionManager, reportRepository, adverseEventRoutingAndReviewRepository, evaluationService);
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
    
    /**
     * Test the scenario when associatedToWorkflow
     */
    public void testIsAssociatedToWorkflowPositive(){
    	addReportsToAeReport();
    	command.setWorkflowEnabled(true);
    	
    	//select all report defs
    	command.getSelectedReportDefinitions().add(command.getAeReport().getReports().get(0).getReportDefinition());
    	command.getSelectedReportDefinitions().add(command.getAeReport().getReports().get(1).getReportDefinition());
    	command.getSelectedReportDefinitions().add(command.getAeReport().getReports().get(2).getReportDefinition());
    	command.getSelectedReportDefinitions().add(command.getAeReport().getReports().get(3).getReportDefinition());
    	
    	//set the workflow id for first report and test for association to workflow
    	command.getAeReport().getReports().get(0).setWorkflowId(2);
    	
    	Boolean isAssociatedToWorkflow = command.isAssociatedToWorkflow();
    	assertTrue("isAssociatedToWorkflow is returning incorrect value when the selected report is" +
    			" assoicated to workflow", isAssociatedToWorkflow);
    }
    
    /**
     * Test the scenario when not associatedToWorkflow
     */
    public void testIsAssociatedToWorkflowNegative(){
    	addReportsToAeReport();
    	command.setWorkflowEnabled(true);
    	//select all report defs
    	command.getSelectedReportDefinitions().add(command.getAeReport().getReports().get(0).getReportDefinition());
    	command.getSelectedReportDefinitions().add(command.getAeReport().getReports().get(1).getReportDefinition());
    	command.getSelectedReportDefinitions().add(command.getAeReport().getReports().get(2).getReportDefinition());
    	command.getSelectedReportDefinitions().add(command.getAeReport().getReports().get(3).getReportDefinition());
    	
    	//not setting the workflow id and testing for association to workflow
    	Boolean isAssociatedToWorkflow = command.isAssociatedToWorkflow();
    	assertFalse("isAssociatedToWorkflow is returning incorrect value when the selected reports" +
    			" are not associated to any workfow", isAssociatedToWorkflow);
    }
    
    /**
     * Test the scenario when not associatedToWorkflow
     */
    public void testIsAssocitedToWorkflowDisabled(){
    	addReportsToAeReport();
    	command.setWorkflowEnabled(false);
    	
    	command.getSelectedReportDefinitions().add(command.getAeReport().getReports().get(0).getReportDefinition());
    	command.getSelectedReportDefinitions().add(command.getAeReport().getReports().get(1).getReportDefinition());
    	
    	Boolean isAssociatedToWorkflow = command.isAssociatedToWorkflow();
    	assertFalse("isAssociatedToWorkflow is returning incorrect value when Routing and" +
    			" Review is disabled at System level", isAssociatedToWorkflow);
    }
    
    
    /**
     * Test the scenario when all reports, are selected. 
     */
    public void testUpdateFieldMandatoryness(){
    	addReportsToAeReport();
    	command.getAeReport().setId(5);
    	
    	//select all report defs
    	command.getSelectedReportDefinitions().add(command.getAeReport().getReports().get(0).getReportDefinition());
    	command.getSelectedReportDefinitions().add(command.getAeReport().getReports().get(1).getReportDefinition());
    	command.getSelectedReportDefinitions().add(command.getAeReport().getReports().get(2).getReportDefinition());
    	command.getSelectedReportDefinitions().add(command.getAeReport().getReports().get(3).getReportDefinition());

    	command.updateFieldMandatoryness();
    	assertTrue(command.getMandatoryProperties().isMandatory("adverseEvents[0].grade"));
    	assertFalse(command.getMandatoryProperties().isMandatory("concomitantMedications[].agentName"));
    }

    //test scenario where only one report is selected
    public void testUpdateFieldMandatorynessWhenSomeReportsAreInactive(){
    	addReportsToAeReport();
    	command.getAeReport().setId(4);
    	command.getAeReport().getReports().get(0).setStatus(ReportStatus.WITHDRAWN);
    	command.getAeReport().getReports().get(2).setStatus(ReportStatus.WITHDRAWN);
    	command.getAeReport().getReports().get(3).setStatus(ReportStatus.WITHDRAWN);
    	
    	//add only report 1 in selected report
    	command.getSelectedReportDefinitions().add(command.getAeReport().getReports().get(1).getReportDefinition());
    	
    	
    	command.getAeReport().getReports().get(1).getReportDefinition().addReportMandatoryFieldDefinition(new ReportMandatoryFieldDefinition("concomitantMedications[].agentName", 
    			RequirednessIndicator.MANDATORY));
    	command.updateFieldMandatoryness();
    	assertTrue(command.getMandatoryProperties().isMandatory("adverseEvents[0].grade"));
    	assertTrue(command.getMandatoryProperties().isMandatory("concomitantMedications[].agentName"));
    }
    
    
    
  
    
   
    
}
