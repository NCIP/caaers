package gov.nih.nci.cabig.caaers.web.ae;

import static org.easymock.EasyMock.expect;
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
import gov.nih.nci.cabig.caaers.domain.TreatmentAssignment;
import gov.nih.nci.cabig.caaers.domain.repository.AdverseEventRoutingAndReviewRepository;
import gov.nih.nci.cabig.caaers.tools.configuration.Configuration;
import gov.nih.nci.cabig.caaers.utils.DateUtils;
import gov.nih.nci.cabig.caaers.web.WebTestCase;
import gov.nih.nci.cabig.caaers.web.fields.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
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

    protected BindException errors;
    protected ReportingPeriodCommand command;

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
		
		
		errors = new BindException(new Object(){
			public AdverseEventReportingPeriod getReportingPeriod(){
				return new AdverseEventReportingPeriod();
			}
		},"command");
	}
	
	
	/**
	 * Tests the {@link CreateReportingPeriodController#formBackingObject(javax.servlet.http.HttpServletRequest)}, with workflow enabled and edit mode
	 * @throws Exception
	 */
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
		expect(reportingPeriod.getId()).andReturn(5).anyTimes();
		replayMocks();
		
		ReportingPeriodCommand command  = (ReportingPeriodCommand)controller.formBackingObject(request);
		assertEquals("edit", command.getMode());
		assertSame(reportingPeriod, command.getReportingPeriod());
		assertSame(assignment, command.getAssignment());
		assertTrue(command.isEditFlow());
		verifyMocks();
	}
	/**
	 * Tests the {@link CreateReportingPeriodController#formBackingObject(javax.servlet.http.HttpServletRequest)}, with workflow disabled and create mode
	 * @throws Exception
	 */
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
		expect(assignment.getReportingPeriods()).andReturn(null);
		
		replayMocks();
		
		ReportingPeriodCommand command  = (ReportingPeriodCommand)controller.formBackingObject(request);
		assertEquals("create", command.getMode());
		assertFalse(command.isWorkflowEnabled());
		assertNotNull(command.getReportingPeriod());
		assertNotNull(command.getReportingPeriod().getTreatmentAssignment());
		
		verifyMocks();
		
	}

	/**
	 * Tests the {@link CreateReportingPeriodController#createFieldGroups(Object)}
	 */
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
		
		Map<String, InputFieldGroup> fieldGrpMap = controller.createFieldGroups(command);
		assertTrue(fieldGrpMap.containsKey("ReportingPeriod"));
		InputFieldGroup fieldGroup = fieldGrpMap.get("ReportingPeriod");
		int size = fieldGroup.getFields().size();
		assertEquals(5, size);
		assertEquals("assignment.startDateOfFirstCourse", fieldGroup.getFields().get(0).getPropertyName());
		assertEquals("reportingPeriod.startDate", fieldGroup.getFields().get(1).getPropertyName());
		assertEquals("reportingPeriod.endDate", fieldGroup.getFields().get(2).getPropertyName());
		assertEquals("reportingPeriod.epoch", fieldGroup.getFields().get(3).getPropertyName());
		assertEquals("reportingPeriod.cycleNumber", fieldGroup.getFields().get(4).getPropertyName());
		
	}
	
	/**
	 * Tests {@link CreateReportingPeriodController#onBindAndValidate(javax.servlet.http.HttpServletRequest, Object, BindException)},
	 * should not throw any error as everyting is fine.
	 * @throws Exception
	 */
	public void testOnBindAndValidateHttpServletRequestObjectBindException() throws Exception{
		List<AdverseEventReportingPeriod> rpList = createReportingPeriodList();
		
		List<Epoch> epochList = new ArrayList<Epoch>();
		Epoch e1 = Fixtures.createEpoch(5, "Baseline");
		Epoch e2 = Fixtures.createEpoch(4, "Treatment");
		Epoch e3 = Fixtures.createEpoch(3, "Test");
		epochList.add(e1);
		epochList.add(e2);
		epochList.add(e3);
		
		expect(study.getEpochs()).andReturn(epochList);
		expect(assignment.getStudySite()).andReturn(studySite).anyTimes();
		expect(studySite.getStudy()).andReturn(study).anyTimes();
		expect(assignment.getParticipant()).andReturn(participant).anyTimes();
		expect(assignment.getReportingPeriods()).andReturn(rpList).anyTimes();
		expect(assignment.getStartDateOfFirstCourse()).andReturn(new Date()).anyTimes();
		replayMocks();
		
		ReportingPeriodCommand command = new ReportingPeriodCommand(assignment, null, "create");
		command.getReportingPeriod().setEpoch(e3);
		command.getReportingPeriod().setStartDate(DateUtils.parseDateString("09/09/2009").toDate());
		command.getReportingPeriod().setEndDate(null);
		command.getReportingPeriod().getTreatmentAssignment().setId(6);
		
		controller.onBindAndValidate(request, command, errors);
		assertFalse(errors.hasErrors());
	}

	/**
	 * Tests {@link CreateReportingPeriodController#onBindAndValidate(javax.servlet.http.HttpServletRequest, Object, BindException)},
	 * should throw error associated to {@link TreatmentAssignment}
	 * @throws Exception
	 */
	public void testOnBindAndValidateHttpServletRequestObjectBindException_IncorrectTreatmentAssignment() throws Exception{
		List<AdverseEventReportingPeriod> rpList = createReportingPeriodList();
		
		List<Epoch> epochList = new ArrayList<Epoch>();
		Epoch e1 = Fixtures.createEpoch(5, "Baseline");
		Epoch e2 = Fixtures.createEpoch(4, "Treatment");
		Epoch e3 = Fixtures.createEpoch(3, "Test");
		epochList.add(e1);
		epochList.add(e2);
		epochList.add(e3);
		
		expect(study.getEpochs()).andReturn(epochList);
		expect(assignment.getStudySite()).andReturn(studySite).anyTimes();
		expect(studySite.getStudy()).andReturn(study).anyTimes();
		expect(assignment.getParticipant()).andReturn(participant).anyTimes();
		expect(assignment.getReportingPeriods()).andReturn(rpList).anyTimes();
		expect(assignment.getStartDateOfFirstCourse()).andReturn(new Date()).anyTimes();
		replayMocks();
		
		ReportingPeriodCommand command = new ReportingPeriodCommand(assignment, null, "create");
		command.getReportingPeriod().setEpoch(e3);
		command.getReportingPeriod().setStartDate(DateUtils.parseDateString("09/09/2009").toDate());
		command.getReportingPeriod().setEndDate(null);
		
		controller.onBindAndValidate(request, command, errors);
		assertTrue(errors.hasErrors());
		assertEquals(1, errors.getErrorCount());
		assertCorrectErrorMessage("Select the Treatment Assignment.",errors.getAllErrors().get(0));
	}
	
	/**
	 * Tests {@link CreateReportingPeriodController#onBindAndValidate(javax.servlet.http.HttpSetvletRequest, Object, BindException)},
	 * should pass even with no treatment type
	 * @throws Exception
	 */
	public void testOnBindAndValidateHttpServletRequestObjectBindException_NoTreatmentType() throws Exception{
		List<AdverseEventReportingPeriod> rpList = createReportingPeriodList();
		
		List<Epoch> epochList = new ArrayList<Epoch>();
		Epoch e1 = Fixtures.createEpoch(5, "Baseline");
		Epoch e2 = Fixtures.createEpoch(4, "Treatment");
		Epoch e3 = Fixtures.createEpoch(3, "Test");
		epochList.add(e1);
		epochList.add(e2);
		epochList.add(e3);
		
		expect(study.getEpochs()).andReturn(epochList);
		expect(assignment.getStudySite()).andReturn(studySite).anyTimes();
		expect(studySite.getStudy()).andReturn(study).anyTimes();
		expect(assignment.getParticipant()).andReturn(participant).anyTimes();
		expect(assignment.getReportingPeriods()).andReturn(rpList).anyTimes();
		expect(assignment.getStartDateOfFirstCourse()).andReturn(new Date()).anyTimes();
		replayMocks();
		
		ReportingPeriodCommand command = new ReportingPeriodCommand(assignment, null, "create");
		command.getReportingPeriod().setEpoch(null);
		command.getReportingPeriod().setStartDate(DateUtils.parseDateString("09/09/2009").toDate());
		command.getReportingPeriod().setEndDate(null);
		command.getReportingPeriod().getTreatmentAssignment().setId(5);
		
		controller.onBindAndValidate(request, command, errors);
		assertEquals(0, errors.getErrorCount());
	}
	
	
	/**
	 * Tests {@link CreateReportingPeriodController#onBindAndValidate(javax.servlet.http.HttpServletRequest, Object, BindException)},
	 * should throw error related to duplicate {@link Epoch} (Baseline)
	 * @throws Exception
	 */
	public void testOnBindAndValidateHttpServletRequestObjectBindException_DuplicateBaseline() throws Exception{
		List<AdverseEventReportingPeriod> rpList = createReportingPeriodList();
		
		List<Epoch> epochList = new ArrayList<Epoch>();
		Epoch e1 = Fixtures.createEpoch(5, "Baseline");
		Epoch e2 = Fixtures.createEpoch(4, "Treatment");
		Epoch e3 = Fixtures.createEpoch(3, "Test");
		epochList.add(e1);
		epochList.add(e2);
		epochList.add(e3);
		
		expect(study.getEpochs()).andReturn(epochList);
		expect(assignment.getStudySite()).andReturn(studySite).anyTimes();
		expect(studySite.getStudy()).andReturn(study).anyTimes();
		expect(assignment.getParticipant()).andReturn(participant).anyTimes();
		expect(assignment.getReportingPeriods()).andReturn(rpList).anyTimes();
		expect(assignment.getStartDateOfFirstCourse()).andReturn(new Date()).anyTimes();
		replayMocks();
		
		ReportingPeriodCommand command = new ReportingPeriodCommand(assignment, null, "create");
		command.getReportingPeriod().setEpoch(e3);
		command.getReportingPeriod().setStartDate(DateUtils.parseDateString("09/09/2009").toDate());
		command.getReportingPeriod().setEndDate(null);
		command.getReportingPeriod().setEpoch(e1);
		command.getReportingPeriod().getTreatmentAssignment().setId(5);
		
		controller.onBindAndValidate(request, command, errors);
		assertTrue(errors.hasErrors());
		assertEquals(1, errors.getErrorCount());
		assertCorrectErrorMessage("A Baseline treatment type already exists",errors.getAllErrors().get(0));
	}
	
	/**
	 * Tests the {@link CreateReportingPeriodController#validateRepPeriodDates(AdverseEventReportingPeriod, List, Errors)}
	 */
	public void testValidateReportingPeriodDates() {
	
		List<AdverseEventReportingPeriod> rpList = createReportingPeriodList();
		
		//valid reporting period
		AdverseEventReportingPeriod reportingPeriod = Fixtures.createReportingPeriod(5, "02/04/2009","02/07/2009");
		Epoch epoch = Fixtures.createEpoch(5, "Test");
		reportingPeriod.setEpoch(epoch);
		rpList.add(reportingPeriod);
		
		controller.validateRepPeriodDates(reportingPeriod, rpList, errors);
		
		assertFalse(errors.hasErrors());
	}
	
	/**
	 * Tests the {@link CreateReportingPeriodController#validateRepPeriodDates(AdverseEventReportingPeriod, List, Errors)}
	 */
	public void testValidateReportingPeriodDates_NewNonBaselineBeforeExistingBaseline() {
		
		List<AdverseEventReportingPeriod> rpList = createReportingPeriodList();
		
		//valid reporting period
		AdverseEventReportingPeriod reportingPeriod = Fixtures.createReportingPeriod(5, "01/04/2009","01/07/2009");
		Epoch epoch = Fixtures.createEpoch(5, "Test");
		reportingPeriod.setEpoch(epoch);
		rpList.add(reportingPeriod);
		
		
		controller.validateRepPeriodDates(reportingPeriod, rpList, errors);
		
		assertTrue(errors.hasErrors());
		assertEquals(1, errors.getErrorCount());
		assertCorrectErrorMessage("Non-Baseline treatment type cannot start before an existing Baseline treatment type.",errors.getAllErrors().get(0));
	}
	/**
	 * Tests the {@link CreateReportingPeriodController#validateRepPeriodDates(AdverseEventReportingPeriod, List, Errors)}
	 */
	public void testValidateReportingPeriodDates_NewBaselineBeforeExistingBaseline() {
		
		List<AdverseEventReportingPeriod> rpList = createReportingPeriodList();
		
		//valid reporting period
		AdverseEventReportingPeriod reportingPeriod = Fixtures.createReportingPeriod(5, "01/04/2009","01/07/2009");
		Epoch epoch = Fixtures.createEpoch(5, "Baseline");
		reportingPeriod.setEpoch(epoch);
		rpList.add(reportingPeriod);
		controller.validateRepPeriodDates(reportingPeriod, rpList, errors);
		
		assertFalse(errors.hasErrors());
	}
	/**
	 * Tests the {@link CreateReportingPeriodController#validateRepPeriodDates(AdverseEventReportingPeriod, List, Errors)}
	 */
	public void testValidateReportingPeriodDates_EditExistingBaselineToBeAfterTreatment() {
		
		List<AdverseEventReportingPeriod> rpList = createReportingPeriodList();
		
		//valid reporting period
		AdverseEventReportingPeriod reportingPeriod = rpList.get(0); //existing baseline
		reportingPeriod.setStartDate(DateUtils.parseDateString("04/01/2009").toDate());
		reportingPeriod.setEndDate(DateUtils.parseDateString("04/03/2009").toDate());
		controller.validateRepPeriodDates(reportingPeriod, rpList, errors);
		
		assertTrue(errors.hasErrors());
		assertEquals(1, errors.getErrorCount());
		assertCorrectErrorMessage("Baseline treatment type cannot start after an existing Non-Baseline treatment type.",errors.getAllErrors().get(0));
	}
	/**
	 * Tests the {@link CreateReportingPeriodController#validateRepPeriodDates(AdverseEventReportingPeriod, List, Errors)}
	 */
	public void testValidateReportingPeriodDates_WithOverlapingStartDates() {
		
		List<AdverseEventReportingPeriod> rpList = createReportingPeriodList();
		
		//valid reporting period
		AdverseEventReportingPeriod reportingPeriod = Fixtures.createReportingPeriod(5, "02/03/2009","02/07/2009");
		Epoch epoch = Fixtures.createEpoch(5, "Test");
		reportingPeriod.setEpoch(epoch);
		rpList.add(reportingPeriod);
		
		controller.validateRepPeriodDates(reportingPeriod, rpList, errors);
		
		assertTrue(errors.hasErrors());
		assertEquals(1, errors.getErrorCount());
		assertCorrectErrorMessage("Course/cycle cannot overlap with an existing course/cycle.", errors.getAllErrors().get(0) );
	}
	/**
	 * Tests the {@link CreateReportingPeriodController#validateRepPeriodDates(AdverseEventReportingPeriod, List, Errors)}
	 */
	public void testValidateReportingPeriodDates_WithOverlapingEndDates() {
		
		List<AdverseEventReportingPeriod> rpList = createReportingPeriodList();
		
		//valid reporting period
		AdverseEventReportingPeriod reportingPeriod = Fixtures.createReportingPeriod(5, "02/05/2009","02/12/2009");
		Epoch epoch = Fixtures.createEpoch(5, "Test");
		reportingPeriod.setEpoch(epoch);
		rpList.add(reportingPeriod);
		
		controller.validateRepPeriodDates(reportingPeriod, rpList, errors);
		
		assertTrue(errors.hasErrors());
		assertEquals(1, errors.getErrorCount());
		assertCorrectErrorMessage("Course/cycle cannot overlap with an existing course/cycle.", errors.getAllErrors().get(0) );
	}
	/**
	 * Tests the {@link CreateReportingPeriodController#validateRepPeriodDates(AdverseEventReportingPeriod, List, Errors)}
	 */
	public void testValidateReportingPeriodDates_NewReportingPeriodHavingOnlyStartDateButNotOverlaping() {
		
		List<AdverseEventReportingPeriod> rpList = createReportingPeriodList();
		
		//valid reporting period
		AdverseEventReportingPeriod reportingPeriod = Fixtures.createReportingPeriod(5, "02/05/2009",null);
		Epoch epoch = Fixtures.createEpoch(5, "Test");
		reportingPeriod.setEpoch(epoch);
		rpList.add(reportingPeriod);
		
		controller.validateRepPeriodDates(reportingPeriod, rpList, errors);
		
		assertFalse(errors.hasErrors());
	}
	/**
	 * Tests the {@link CreateReportingPeriodController#validateRepPeriodDates(AdverseEventReportingPeriod, List, Errors)}
	 */
	public void testValidateReportingPeriodDates_NewReportingPeriodHavingOnlyStartDateAndOverlaping() {
		
		List<AdverseEventReportingPeriod> rpList = createReportingPeriodList();
		
		//valid reporting period
		AdverseEventReportingPeriod reportingPeriod = Fixtures.createReportingPeriod(5, "02/12/2009",null);
		Epoch epoch = Fixtures.createEpoch(5, "Test");
		reportingPeriod.setEpoch(epoch);
		rpList.add(reportingPeriod);
		
		controller.validateRepPeriodDates(reportingPeriod, rpList, errors);
		
		assertTrue(errors.hasErrors());
		assertEquals(1, errors.getErrorCount());
		assertCorrectErrorMessage("Course/cycle cannot overlap with an existing course/cycle.", errors.getAllErrors().get(0) );
	}
	/**
	 * Tests the {@link CreateReportingPeriodController#validateRepPeriodDates(AdverseEventReportingPeriod, List, Errors)}
	 */
	public void testValidateReportingPeriodDates_ExistingHavingOnlyStartDate_NewReportingPeriodHavingOnlyStartDateAndOverlaping() {
		
		List<AdverseEventReportingPeriod> rpList = createReportingPeriodList();
		rpList.get(0).setEndDate(null);
		rpList.get(1).setEndDate(null);
		
		//valid reporting period
		AdverseEventReportingPeriod reportingPeriod = Fixtures.createReportingPeriod(5, "03/06/2009",null);
		Epoch epoch = Fixtures.createEpoch(5, "Test");
		reportingPeriod.setEpoch(epoch);
		rpList.add(reportingPeriod);
		
		controller.validateRepPeriodDates(reportingPeriod, rpList, errors);
		
		assertTrue(errors.hasErrors());
		assertEquals(1, errors.getErrorCount());
		assertCorrectErrorMessage("Course/cycle cannot overlap with an existing course/cycle.", errors.getAllErrors().get(0) );
	}
	
	/**
	 * Tests the {@link CreateReportingPeriodController#validateRepPeriodDates(AdverseEventReportingPeriod, List, Errors)}
	 */
	public void testValidateReportingPeriodDates_EditWithOverlapingEndDates() {
		
		List<AdverseEventReportingPeriod> rpList = createReportingPeriodList();
		
		//valid reporting period
		AdverseEventReportingPeriod reportingPeriod = rpList.get(1);
		reportingPeriod.setEndDate(DateUtils.parseDateString("01/10/2010").toDate());
		controller.validateRepPeriodDates(reportingPeriod, rpList, errors);
		
		assertTrue(errors.hasErrors());
		assertEquals(1, errors.getErrorCount());
		assertCorrectErrorMessage("Course/cycle cannot overlap with an existing course/cycle.", errors.getAllErrors().get(0) );
	}
	/**
	 * Tests the {@link CreateReportingPeriodController#validateRepPeriodDates(AdverseEventReportingPeriod, List, Errors)}
	 */
	public void testValidateReportingPeriodDates_EditBaslineToStartAfterTreatment() {
		
		List<AdverseEventReportingPeriod> rpList = createReportingPeriodList();
		
		//valid reporting period
		AdverseEventReportingPeriod reportingPeriod = rpList.get(0);
		reportingPeriod.setStartDate(DateUtils.parseDateString("01/10/2010").toDate());
		reportingPeriod.setEndDate(DateUtils.parseDateString("01/12/2010").toDate());
		controller.validateRepPeriodDates(reportingPeriod, rpList, errors);
		
		assertTrue(errors.hasErrors());
		assertEquals(1, errors.getErrorCount());
		assertCorrectErrorMessage("Baseline treatment type cannot start after an existing Non-Baseline treatment type.", errors.getAllErrors().get(0) );
	}
	/**
	 * Tests the {@link CreateReportingPeriodController#validateRepPeriodDates(AdverseEventReportingPeriod, List, Errors)}
	 */
	public void testValidateReportingPeriodDates_EditTreatmentToStartBeforeBaseline() {
		
		List<AdverseEventReportingPeriod> rpList = createReportingPeriodList();
		
		//valid reporting period
		AdverseEventReportingPeriod reportingPeriod = rpList.get(1);
		reportingPeriod.setStartDate(DateUtils.parseDateString("01/10/2008").toDate());
		reportingPeriod.setEndDate(DateUtils.parseDateString("01/12/2008").toDate());
		controller.validateRepPeriodDates(reportingPeriod, rpList, errors);
		
		assertTrue(errors.hasErrors());
		assertEquals(1, errors.getErrorCount());
		assertCorrectErrorMessage("Non-Baseline treatment type cannot start before an existing Baseline treatment type.", errors.getAllErrors().get(0) );
	}
	/**
	 * Tests the {@link CreateReportingPeriodController#validateRepPeriodDates(AdverseEventReportingPeriod, List, Errors)}
	 */
	public void testValidateReportingPeriodDates_EditReportingPeriodMakeStartDateSameAsAnotherReportingPeriod() {
		
		List<AdverseEventReportingPeriod> rpList = createReportingPeriodList();
		
		//valid reporting period
		AdverseEventReportingPeriod reportingPeriod = rpList.get(2);
		reportingPeriod.setEndDate(null);
		reportingPeriod.setStartDate(DateUtils.parseDateString("03/05/2009").toDate());
		
		
		controller.validateRepPeriodDates(reportingPeriod, rpList, errors);
		
		assertTrue(errors.hasErrors());
		assertEquals(1, errors.getErrorCount());
		assertCorrectErrorMessage("Course/cycle cannot overlap with an existing course/cycle.", errors.getAllErrors().get(0) );
	}
	
	/**
	 * Will create a list of reporting periods, which will be the base test data.
	 * @return
	 */
	public List<AdverseEventReportingPeriod> createReportingPeriodList(){
		AdverseEventReportingPeriod reportingPeriod1 = Fixtures.createReportingPeriod(1,"02/01/2009", "02/04/2009");
		Epoch epoch1 = Fixtures.createEpoch(1, "Baseline");
		reportingPeriod1.setEpoch(epoch1);
		
		AdverseEventReportingPeriod reportingPeriod2 = Fixtures.createReportingPeriod(2,"02/11/2009", "02/25/2009");
		Epoch epoch2 = Fixtures.createEpoch(2, "Treatment");
		reportingPeriod2.setEpoch(epoch2);
		
		AdverseEventReportingPeriod reportingPeriod3 = Fixtures.createReportingPeriod(3,"03/01/2009", "03/04/2009");
		Epoch epoch3 = Fixtures.createEpoch(3, "Treatment");
		reportingPeriod3.setEpoch(epoch3);
		
		AdverseEventReportingPeriod reportingPeriod4 = Fixtures.createReportingPeriod(4, "03/05/2009", "03/09/2009");
		Epoch epoch4 = Fixtures.createEpoch(4, "Treatment");
		reportingPeriod4.setEpoch(epoch4);
		
		List<AdverseEventReportingPeriod> rpList = new ArrayList<AdverseEventReportingPeriod>();
		rpList.add(reportingPeriod1);
		rpList.add(reportingPeriod2);
		rpList.add(reportingPeriod3);
		rpList.add(reportingPeriod4);
		return rpList;
	}
	
	
	public void assertCorrectErrorMessage(String msg, Object objError){
		ObjectError error = (ObjectError)objError;
		assertEquals(msg, error.getDefaultMessage());
	}

    public void testRefferenceData() throws Exception {

        List<Epoch> epochs = new ArrayList<Epoch>();
        List<TreatmentAssignment> tas = new ArrayList<TreatmentAssignment>();

        expect(assignment.getStudySite()).andReturn(studySite).anyTimes();
        expect(studySite.getStudy()).andReturn(study).anyTimes();
        expect(assignment.getParticipant()).andReturn(participant).anyTimes();
        expect(study.getEpochs()).andReturn(epochs).anyTimes();
        expect(study.getTreatmentAssignments()).andReturn(tas).anyTimes();

        replayMocks();

        command = new ReportingPeriodCommand(assignment, reportingPeriod, "");
        errors = new BindException(command, "command");

        Map refData  = controller.referenceData(request, command, errors);

        verifyMocks();

        assertNotNull(refData);
        assertEquals(1, refData.size());
        Map<String, DefaultInputFieldGroup> map = (Map)refData.get("fieldGroups");
        DefaultInputFieldGroup fg = map.get("ReportingPeriod");

        assertEquals(1, map.size());
        assertEquals(5, fg.getFields().size());
        assertEquals("assignment.startDateOfFirstCourse", fg.getFields().get(0).getPropertyName());
    }

    public void testSetHelpKeyAttribute() throws Exception {
        InputField _if = InputFieldFactory.createEmailField("email", "emailName", true);
        controller.setHelpKeyAttribute(_if);
        assertEquals("ae.createReportingPeriod.email", _if.getAttributes().get(InputField.HELP));
    }

    public void testPopulateHelpAttributeOnFields() {
        InputFieldGroupMap fieldMap = new InputFieldGroupMap();
        InputFieldGroup g1 = new DefaultInputFieldGroup("groupOne");

        g1.getFields().add(InputFieldFactory.createDateField("f1", "name one", true));
        g1.getFields().add(InputFieldFactory.createDateField("f2", "name two", true));

        fieldMap.addInputFieldGroup(g1);
        controller.populateHelpAttributeOnFields(fieldMap);

        assertEquals("ae.createReportingPeriod.f1", fieldMap.get("groupOne").getFields().get(0).getAttributes().get(InputField.HELP));
        assertEquals("ae.createReportingPeriod.f2", fieldMap.get("groupOne").getFields().get(1).getAttributes().get(InputField.HELP));
    }
}
