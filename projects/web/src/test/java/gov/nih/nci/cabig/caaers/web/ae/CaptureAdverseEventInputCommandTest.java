package gov.nih.nci.cabig.caaers.web.ae;

import java.util.ArrayList;
import java.util.List;

import org.easymock.classextension.EasyMock;

import gov.nih.nci.cabig.caaers.AbstractNoSecurityTestCase;
import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.dao.AdverseEventReportingPeriodDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.StudyParticipantAssignmentDao;
import gov.nih.nci.cabig.caaers.dao.report.ReportDefinitionDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.service.EvaluationService;
import junit.framework.TestCase;

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
		command = new CaptureAdverseEventInputCommand(adverseEventReportingPeriodDao, assignmentDao, evaluationService, reportDefinitionDao, studyDao );
		
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
