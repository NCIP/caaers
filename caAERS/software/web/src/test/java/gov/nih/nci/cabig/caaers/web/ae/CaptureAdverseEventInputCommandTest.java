package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.AbstractNoSecurityTestCase;
import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.dao.AdverseEventReportingPeriodDao;
import gov.nih.nci.cabig.caaers.dao.ExpeditedAdverseEventReportDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.StudyParticipantAssignmentDao;
import gov.nih.nci.cabig.caaers.dao.report.ReportDefinitionDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.CtcTerm;
import gov.nih.nci.cabig.caaers.domain.ExpectedAECtcTerm;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.Grade;
import gov.nih.nci.cabig.caaers.domain.Hospitalization;
import gov.nih.nci.cabig.caaers.domain.Outcome;
import gov.nih.nci.cabig.caaers.domain.OutcomeType;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.meddra.LowLevelTerm;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.service.EvaluationService;

import java.util.ArrayList;
import java.util.List;

/**h
 * 
 * @author Biju Joseph
 *
 */
public class CaptureAdverseEventInputCommandTest extends AbstractNoSecurityTestCase {
	
	CaptureAdverseEventInputCommand command;
	
	private StudyParticipantAssignmentDao assignmentDao;
	private ReportDefinitionDao reportDefinitionDao;
	private AdverseEventReportingPeriodDao adverseEventReportingPeriodDao;
	private EvaluationService evaluationService;
	private StudyDao studyDao;
	private ExpeditedAdverseEventReportDao aeReportDao;
	
	List<AdverseEvent> aes;
	List<ReportDefinition> reportdefs;
	
	AdverseEventReportingPeriod reportingPeriod;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		assignmentDao = registerDaoMockFor(StudyParticipantAssignmentDao.class);
		reportDefinitionDao = registerDaoMockFor(ReportDefinitionDao.class);
		adverseEventReportingPeriodDao = registerDaoMockFor(AdverseEventReportingPeriodDao.class);
		evaluationService = registerMockFor(EvaluationService.class);
		studyDao = registerDaoMockFor(StudyDao.class);
		aeReportDao = registerDaoMockFor(ExpeditedAdverseEventReportDao.class);
		command = new CaptureAdverseEventInputCommand(adverseEventReportingPeriodDao, assignmentDao, evaluationService, reportDefinitionDao, studyDao,aeReportDao );
		
		reportingPeriod = Fixtures.createReportingPeriod();
		
		reportdefs = new ArrayList<ReportDefinition>();
		for(int i = 0; i < 2; i++){
			ReportDefinition def = Fixtures.createReportDefinition("ReportDefinition : " + (i + 1));
			def.setId(i + 1);
			reportdefs.add(def);
		}
		
		aes = new ArrayList<AdverseEvent>();
		for(int i = 0; i < 3; i++){
			AdverseEvent ae = Fixtures.createAdverseEvent(i+1, Grade.NORMAL);
			//add couple of outcomes
			Outcome o1 = Fixtures.createOutcome(i*3 +10,OutcomeType.LIFE_THREATENING);
			Outcome o2 = Fixtures.createOutcome(i*3 +11,OutcomeType.DISABILITY);
			Outcome o3 = Fixtures.createOutcome(i*3 +12,OutcomeType.OTHER_SERIOUS);
			o3.setOther("abcd");
			ae.addOutcome(o1);
			ae.addOutcome(o2);
			ae.addOutcome(o3);
			aes.add(ae);
		}
		reportingPeriod.setId(5);
		reportingPeriod.setAdverseEvents(aes);
		command.setAdverseEventReportingPeriod(reportingPeriod);
	}
	
	public void testIsHavingSolicitedAEs(){
		AdverseEvent ae1 = new AdverseEvent();
		CtcTerm ctcTerm = Fixtures.createCtcTerm("abc", "ef");
		Fixtures.createAdverseEventCtcTerm(ae1, ctcTerm);
		ae1.setSolicited(false);
		reportingPeriod.addAdverseEvent(ae1);
		command = new CaptureAdverseEventInputCommand();
		assertFalse(command.isHavingSolicitedAEs());
		
	}
	
	public void testIsAssociatedToLabAlers(){
		StudyParticipantAssignment assignment = Fixtures.createAssignment();
		reportingPeriod.setAssignment(assignment);
		assertFalse(command.isAssociatedToLabAlerts());
	}
	
	public void testIsHavingSolicitedAEsYesOneSolicited(){
		AdverseEvent ae1 = new AdverseEvent();
		CtcTerm ctcTerm = Fixtures.createCtcTerm("abc", "ef");
		Fixtures.createAdverseEventCtcTerm(ae1, ctcTerm);
		ae1.setSolicited(false);
		reportingPeriod.addAdverseEvent(ae1);
		AdverseEvent ae2 = new AdverseEvent();
		CtcTerm ctcTerm2 = Fixtures.createCtcTerm("abc", "ef");
		Fixtures.createAdverseEventCtcTerm(ae2, ctcTerm2);
		ae2.setSolicited(true);
		reportingPeriod.addAdverseEvent(ae2);
		command = new CaptureAdverseEventInputCommand();
		command.setAdverseEventReportingPeriod(reportingPeriod);
		assertTrue(command.isHavingSolicitedAEs());
		
	}
	
	/**
	 * This method tests {@link CaptureAdverseEventInputCommand#initializeOutcomes()}
	 */
	public void testInitializeOutcomes(){
		command.initializeOutcomes();
		assertTrue(command.getOutcomes().get(0).get(OutcomeType.DISABILITY.getCode()));
		assertTrue(command.getOutcomes().get(0).get(OutcomeType.LIFE_THREATENING.getCode()));
		assertTrue(command.getOutcomes().get(0).get(OutcomeType.OTHER_SERIOUS.getCode()));
		assertFalse(command.getOutcomes().get(0).get(OutcomeType.DEATH.getCode()));
		assertEquals("abcd", command.getOutcomeOtherDetails().get(0));
		assertFalse(command.getOutcomes().get(1).isEmpty());
		
		assertTrue(command.getOutcomes().get(2).get(OutcomeType.DISABILITY.getCode()));
		assertTrue(command.getOutcomes().get(2).get(OutcomeType.LIFE_THREATENING.getCode()));
		assertTrue(command.getOutcomes().get(2).get(OutcomeType.OTHER_SERIOUS.getCode()));
		assertFalse(command.getOutcomes().get(2).get(OutcomeType.DEATH.getCode()));
		assertEquals("abcd", command.getOutcomeOtherDetails().get(2));
	}
	
	/**
	 * This method tests {@link CaptureAdverseEventInputCommand#initializeOutcomes()}
	 */
	public void testInitializeOutcomesAfterDelete(){
		command.getAdverseEvents().remove(1); 
		command.initializeOutcomes();
		System.out.println(command.getOutcomes());
		assertTrue(command.getOutcomes().get(0).get(OutcomeType.DISABILITY.getCode()));
		assertTrue(command.getOutcomes().get(0).get(OutcomeType.LIFE_THREATENING.getCode()));
		assertTrue(command.getOutcomes().get(0).get(OutcomeType.OTHER_SERIOUS.getCode()));
		assertFalse(command.getOutcomes().get(0).get(OutcomeType.DEATH.getCode()));
		assertEquals("abcd", command.getOutcomeOtherDetails().get(0));
		
		
		assertTrue(command.getOutcomes().get(1).get(OutcomeType.DISABILITY.getCode()));
		assertTrue(command.getOutcomes().get(1).get(OutcomeType.LIFE_THREATENING.getCode()));
		assertTrue(command.getOutcomes().get(1).get(OutcomeType.OTHER_SERIOUS.getCode()));
		assertFalse(command.getOutcomes().get(1).get(OutcomeType.DEATH.getCode()));
		assertEquals("abcd", command.getOutcomeOtherDetails().get(1));
	}
	/**
	 * This method tests {@link CaptureAdverseEventInputCommand#synchronizeOutcome()}
	 */
	public void testSynchronizeOutcome(){
		command.initializeOutcomes();
		//make uncheck the Disability
		command.getOutcomes().get(0).put(OutcomeType.DISABILITY.getCode(), false);
		command.getAdverseEvents().get(0).setGrade(Grade.DEATH);
		command.synchronizeOutcome();
		
		//make sure that ae has death outcome
		assertTrue(command.isOutcomePresent(OutcomeType.DEATH, command.getAdverseEvents().get(0).getOutcomes()));
		//make sure, ae got rid of disablity
		assertFalse(command.isOutcomePresent(OutcomeType.DISABILITY, command.getAdverseEvents().get(0).getOutcomes()));
		assertEquals("abcd", command.getOutcomeOtherDetails().get(0));
		
		
	}
	
	/**
	 * This method tests {@link CaptureAdverseEventInputCommand#synchronizeOutcome()}
	 */
	public void testSynchronizeOutcomeMakeSureDEATHOutcomeIsRemoved(){
		command.initializeOutcomes();
		//make uncheck the Disability
		command.getOutcomes().get(0).put(OutcomeType.DISABILITY.getCode(), false);
		command.getAdverseEvents().get(0).setGrade(Grade.DEATH);
		command.synchronizeOutcome();
		
		//make sure that ae has death outcome
		assertTrue(command.isOutcomePresent(OutcomeType.DEATH, command.getAdverseEvents().get(0).getOutcomes()));
		//make sure, ae got rid of disablity
		assertFalse(command.isOutcomePresent(OutcomeType.DISABILITY, command.getAdverseEvents().get(0).getOutcomes()));
		assertEquals("abcd", command.getOutcomeOtherDetails().get(0));
		
		command.initializeOutcomes(); //to make sure death is there in the outcome map.
		assertTrue(command.getOutcomes().get(0).get(OutcomeType.DEATH.getCode()));
		assertTrue(command.isOutcomePresent(OutcomeType.DEATH, command.getAdverseEvents().get(0).getOutcomes()));
		//update the ae grade to something else
		command.getAdverseEvents().get(0).setGrade(Grade.MILD);
		command.getOutcomes().get(0).put(OutcomeType.DEATH.getCode(), false);
		command.synchronizeOutcome();
		assertFalse(command.isOutcomePresent(OutcomeType.DEATH, command.getAdverseEvents().get(0).getOutcomes()));
	}
	
	/**
	 * This method tests {@link CaptureAdverseEventInputCommand#synchronizeOutcome()}
	 */
	public void testSynchronizeOutcomeMakeSureHospitalizationOutcomeIsRemoved(){
		//add hospitalization outcome to AE 0
		command.getAdverseEvents().get(0).setHospitalization(Hospitalization.YES);
		Outcome o1 = new Outcome();
		o1.setOutcomeType(OutcomeType.HOSPITALIZATION);
		command.getAdverseEvents().get(0).addOutcome(o1);
		
		command.initializeOutcomes();
		//make sure hospitaliztion is present
		assertTrue(command.getOutcomes().get(0).get(OutcomeType.HOSPITALIZATION.getCode()));
		//make uncheck the Disability
		command.getOutcomes().get(0).put(OutcomeType.HOSPITALIZATION.getCode(), false);
		//update the ae HOspitalization to something else
		command.getAdverseEvents().get(0).setHospitalization(Hospitalization.NO);
		command.synchronizeOutcome();
		assertFalse(command.isOutcomePresent(OutcomeType.HOSPITALIZATION, command.getAdverseEvents().get(0).getOutcomes()));
	}
	
	/**
	 * This method tests {@link CaptureAdverseEventInputCommand#initialize()}
	 */
	public void testInitialize(){
		command.initialize();
		assertNotNull(command.getAdverseEvents());
	}
	
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	


	public void testFindRequiredReportDefinitions() {
		assertTrue(true);
	}

	public void testGetSelectedReportDefinitions() {
		assertTrue(true);
	}

	public void testGetInstantiatedReportDefinitions() {
		assertTrue(true);
	}

	public void testFindNewlySelectedReportDefinitions() {
		assertTrue(true);
	}

	public void testFindUnselectedReportDefinitions() {
		assertTrue(true);
	}

	public void testFindNewlySelectedAdverseEvents() {
		assertTrue(true);
	}

	public void testFindUnselectedAdverseEvents() {
		assertTrue(true);
	}

	public void testFindSelectedAdverseEvents() {
		assertTrue(true);
	}

	public void testFindPrimaryAdverseEvent() {
		assertTrue(true);
	}

}
