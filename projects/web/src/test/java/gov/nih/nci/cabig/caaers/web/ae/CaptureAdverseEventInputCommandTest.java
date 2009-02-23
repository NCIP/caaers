package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.AbstractNoSecurityTestCase;
import gov.nih.nci.cabig.caaers.dao.AdverseEventReportingPeriodDao;
import gov.nih.nci.cabig.caaers.dao.ExpeditedAdverseEventReportDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.StudyParticipantAssignmentDao;
import gov.nih.nci.cabig.caaers.dao.report.ReportDefinitionDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.service.EvaluationService;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author biju
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
			AdverseEvent ae = new AdverseEvent();
			ae.setId(i + 1);
			aes.add(ae);
		}
		reportingPeriod.setId(5);
		reportingPeriod.setAdverseEvents(aes);
		command.setAdverseEventReportingPeriod(reportingPeriod);
		command.setAdverseEventReportingPeriod(reportingPeriod);
	}
	
	public void testIsHavingSolicitedAEs(){
		AdverseEvent ae1 = new AdverseEvent();
		ae1.setSolicited(false);
		reportingPeriod.addAdverseEvent(ae1);
		command = new CaptureAdverseEventInputCommand();
		assertFalse(command.isHavingSolicitedAEs());
		
	}
	public void testIsHavingSolicitedAEsYesOneSolicited(){
		AdverseEvent ae1 = new AdverseEvent();
		ae1.setSolicited(false);
		reportingPeriod.addAdverseEvent(ae1);
		AdverseEvent ae2 = new AdverseEvent();
		ae2.setSolicited(true);
		reportingPeriod.addAdverseEvent(ae2);
		command = new CaptureAdverseEventInputCommand();
		command.setAdverseEventReportingPeriod(reportingPeriod);
		assertTrue(command.isHavingSolicitedAEs());
		
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
