package gov.nih.nci.cabig.caaers.web.ae;

import static org.easymock.EasyMock.expect;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import gov.nih.nci.cabig.caaers.dao.AdverseEventReportingPeriodDao;
import gov.nih.nci.cabig.caaers.dao.EpochDao;
import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.StudyParticipantAssignmentDao;
import gov.nih.nci.cabig.caaers.dao.TreatmentAssignmentDao;
import gov.nih.nci.cabig.caaers.dao.UserDao;
import gov.nih.nci.cabig.caaers.dao.workflow.WorkflowConfigDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.Epoch;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.domain.repository.AdverseEventRoutingAndReviewRepository;
import gov.nih.nci.cabig.caaers.tools.configuration.Configuration;
import gov.nih.nci.cabig.caaers.web.WebTestCase;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
/**
 * 
 * @author Biju Joseph
 *
 */
public class CreateReportingPeriodControllerTest extends WebTestCase {
	
	 AdverseEventReportingPeriodDao adverseEventReportingPeriodDao;
     StudyParticipantAssignmentDao assignmentDao;
     ParticipantDao participantDao;
     TreatmentAssignmentDao treatmentAssignmentDao;
     StudyDao studyDao;
     EpochDao epochDao;
     AdverseEventRoutingAndReviewRepository adverseEventRoutingAndReviewRepository;
     WorkflowConfigDao workflowConfigDao;
	 UserDao userDao;
	 
	 CreateReportingPeriodController controller;
	 Study study;
	 Participant participant;
	 StudyParticipantAssignment assignment;
	 AdverseEventReportingPeriod reportingPeriod;
	 Configuration configuration;
	 StudySite studySite;
	 
	 
	protected void setUp() throws Exception {
		super.setUp();
		
		adverseEventReportingPeriodDao = registerDaoMockFor(AdverseEventReportingPeriodDao.class);
		assignmentDao = registerDaoMockFor(StudyParticipantAssignmentDao.class);
		participantDao = registerDaoMockFor(ParticipantDao.class);
		treatmentAssignmentDao = registerDaoMockFor(TreatmentAssignmentDao.class);
		studyDao = registerDaoMockFor(StudyDao.class);
		epochDao = registerDaoMockFor(EpochDao.class);
		adverseEventRoutingAndReviewRepository = registerMockFor(AdverseEventRoutingAndReviewRepository.class);
		workflowConfigDao = registerDaoMockFor(WorkflowConfigDao.class);
		userDao = registerDaoMockFor(UserDao.class);
		configuration = registerMockFor(Configuration.class);
		study = registerMockFor(Study.class);
		participant = registerMockFor(Participant.class);
		assignment = registerMockFor(StudyParticipantAssignment.class);
		reportingPeriod = registerMockFor(AdverseEventReportingPeriod.class);
		studySite = registerMockFor(StudySite.class);
		
		controller = new CreateReportingPeriodController();
		controller.setAdverseEventReportingPeriodDao(adverseEventReportingPeriodDao);
		controller.setAssignmentDao(assignmentDao);
		controller.setParticipantDao(participantDao);
		controller.setTreatmentAssignmentDao(treatmentAssignmentDao);
		controller.setStudyDao(studyDao);
		controller.setEpochDao(epochDao);
		controller.setAdverseEventRoutingAndReviewRepository(adverseEventRoutingAndReviewRepository);
		controller.setWorkflowConfigDao(workflowConfigDao);
		controller.setUserDao(userDao);
		controller.setConfiguration(configuration);
		
	}
	

	public void testFormBackingObjectHttpServletRequest() throws Exception{
		
		request.setParameter("id", "5");
		request.setParameter("studyId", "5");
		request.setParameter("participantId","5");
		
		expect(studyDao.getById(5)).andReturn(study);
		expect(assignmentDao.getAssignment(participant, study)).andReturn(assignment);
		expect(adverseEventReportingPeriodDao.getById(5)).andReturn(reportingPeriod);
		expect(participantDao.getById(5)).andReturn(participant);
		expect(configuration.get(Configuration.ENABLE_WORKFLOW)).andReturn(true);
		expect(assignment.getStudySite()).andReturn(studySite);
		expect(studySite.getStudy()).andReturn(study);
		expect(assignment.getParticipant()).andReturn(participant);
		replayMocks();
		
		ReportingPeriodCommand command  = (ReportingPeriodCommand)controller.formBackingObject(request);
		assertEquals("edit", command.getMode());
		assertSame(reportingPeriod, command.getReportingPeriod());
		assertSame(assignment, command.getAssignment());
		assertTrue(command.isEditFlow());
		verifyMocks();
	}
	public void testFormBackingObjectHttpServletRequest_CreateModeAndWorkflowDisabled() throws Exception {
		
		request.setParameter("id", "0");
		request.setParameter("studyId", "5");
		request.setParameter("participantId","5");
		
		expect(studyDao.getById(5)).andReturn(study);
		expect(assignmentDao.getAssignment(participant, study)).andReturn(assignment);
		expect(participantDao.getById(5)).andReturn(participant);
		expect(configuration.get(Configuration.ENABLE_WORKFLOW)).andReturn(false);
		expect(assignment.getStudySite()).andReturn(studySite);
		expect(studySite.getStudy()).andReturn(study);
		expect(assignment.getParticipant()).andReturn(participant);
		replayMocks();
		
		ReportingPeriodCommand command  = (ReportingPeriodCommand)controller.formBackingObject(request);
		assertEquals("create", command.getMode());
		assertFalse(command.isWorkflowEnabled());
		assertNotNull(command.getReportingPeriod());
		assertNotNull(command.getReportingPeriod().getTreatmentAssignment());
		
		verifyMocks();
		
	}


	public void testCreateFieldGroups() {
		List<Epoch> epochList = new ArrayList<Epoch>();
		Epoch e1 = Fixtures.createEpoch(5, "abc");
		Epoch e2 = Fixtures.createEpoch(4, "xyz");
		epochList.add(e1);
		epochList.add(e2);
		
		expect(study.getEpochs()).andReturn(epochList);
		expect(assignment.getStudySite()).andReturn(studySite).anyTimes();
		expect(studySite.getStudy()).andReturn(study).anyTimes();
		expect(assignment.getParticipant()).andReturn(participant);
		replayMocks();
		
		ReportingPeriodCommand command = new ReportingPeriodCommand(assignment, reportingPeriod, "edit");
		command.setWorkflowEnabled(true);
		
		Map<String, InputFieldGroup> fieldGrp = controller.createFieldGroups(command);
		assertTrue(fieldGrp.containsKey("ReportingPeriod"));
		
		System.out.println(fieldGrp);
	}

//	public void testOnBindAndValidateHttpServletRequestObjectBindException() {
//		fail("Not yet implemented");
//	}
//
//	public void testValidateRepPeriodDates() {
//		fail("Not yet implemented");
//	}

}
