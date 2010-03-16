package gov.nih.nci.cabig.caaers.rules.business.service;

import static org.easymock.EasyMock.expect;
import gov.nih.nci.cabig.caaers.AbstractNoSecurityTestCase;
import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.dao.ExpeditedAdverseEventReportDao;
import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.dao.report.ReportDefinitionDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.ConfigProperty;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.Grade;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.OrganizationAssignedIdentifier;
import gov.nih.nci.cabig.caaers.domain.ReportStatus;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyCoordinatingCenter;
import gov.nih.nci.cabig.caaers.domain.StudyFundingSponsor;
import gov.nih.nci.cabig.caaers.domain.StudyOrganization;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.domain.dto.ApplicableReportDefinitionsDTO;
import gov.nih.nci.cabig.caaers.domain.dto.EvaluationResultDTO;
import gov.nih.nci.cabig.caaers.domain.dto.ReportDefinitionWrapper;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;
import gov.nih.nci.cabig.caaers.domain.report.*;
import gov.nih.nci.cabig.caaers.domain.repository.ReportRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.easymock.classextension.EasyMock;

/**
 * @author Biju Joseph
 */
public class EvaluationServiceTest extends AbstractNoSecurityTestCase {
	
	public static Integer ZERO = new Integer(0);
	public static Integer ONE = new Integer(1);
	public static List<AdverseEvent> EMPTY_AE_LIST = new ArrayList<AdverseEvent>();
	public static List<Report> EMPTY_REPORT_LIST = new ArrayList<Report>();

	AdverseEventEvaluationService adverseEventEvaluationService;

	ReportDefinitionDao reportDefinitionDao;

	ExpeditedAdverseEventReportDao expeditedAdverseEventReportDao;

	ReportRepository reportRepository;

	OrganizationDao organizationDao;

	EvaluationServiceImpl service;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		reportDefinitionDao = registerDaoMockFor(ReportDefinitionDao.class);
		expeditedAdverseEventReportDao = registerDaoMockFor(ExpeditedAdverseEventReportDao.class);
		organizationDao = registerDaoMockFor(OrganizationDao.class);

		adverseEventEvaluationService = registerMockFor(AdverseEventEvaluationService.class);
		reportRepository = registerMockFor(ReportRepository.class);

		service = new EvaluationServiceImpl();

		service.setReportDefinitionDao(reportDefinitionDao);

		service.setAdverseEventEvaluationService(adverseEventEvaluationService);

	}

	public void testFindRequiredReportDefinitions() throws Exception {
		String n1 = "24 Hr report";
		String n2 = "55 day report";

		ReportDefinition rd1 = new ReportDefinition();
		rd1.setName(n1);

		ReportDefinition rd2 = new ReportDefinition();
		rd2.setName(n2);

		List<String> reportNames = new ArrayList<String>();
		reportNames.add(n1);
		reportNames.add(n2);
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		map.put("junk", reportNames);

		ExpeditedAdverseEventReport aereport = new ExpeditedAdverseEventReport();
		/*
		 * expect(adverseEventEvaluationService.evaluateSAEReportSchedule(aereport
		 * )).andReturn(map);
		 * 
		 * expect(reportDefinitionDao.getByName(n1)).andReturn(rd1);
		 * expect(reportDefinitionDao.getByName(n2)).andReturn(rd2);
		 * //replayMocks(); List<ReportDefinition> actualDefList =
		 * service.findRequiredReportDefinitions(aereport); //verifyMocks();
		 * 
		 * assertEquals("incorrect number of report definitions", 2,
		 * actualDefList.size());
		 * assertEquals("report definition name is incorrect", n1,
		 * actualDefList.get(0).getName());
		 */

	}


	public void testApplicableReportDefinitions() {
		Organization ctep = Fixtures.createOrganization("CTEP", 1);
		Organization mayo = Fixtures.createOrganization("Mayo", 2);
		Organization otherSite = Fixtures.createOrganization("Other site", 3);

		ConfigProperty expedited = Fixtures.createConfigProperty("expedited");
		ConfigProperty local = Fixtures.createConfigProperty("local");

		ReportDefinition rd1 = Fixtures.createReportDefinition("ctep-rd-1",ctep, expedited);
		rd1.setTimeScaleUnitType(TimeScaleUnit.MINUTE);
		rd1.setDuration(1);
		ReportDefinition rd2 = Fixtures.createReportDefinition("ctep-rd-2",ctep, expedited);
		rd2.setTimeScaleUnitType(TimeScaleUnit.DAY);
		rd2.setDuration(2);
		ReportDefinition rd3 = Fixtures.createReportDefinition("ctep-rd-3",ctep, local);
		rd3.setTimeScaleUnitType(TimeScaleUnit.DAY);
		rd3.setDuration(3);
		ReportDefinition rd4 = Fixtures.createReportDefinition("ctep-rd-4",ctep, local);
		rd4.setTimeScaleUnitType(TimeScaleUnit.DAY);
		rd4.setDuration(1);

		ReportDefinition rd5 = Fixtures.createReportDefinition("ctep-mo-1",mayo, expedited);
		rd5.setTimeScaleUnitType(TimeScaleUnit.DAY);
		rd5.setDuration(1);
		ReportDefinition rd6 = Fixtures.createReportDefinition("ctep-mo-2",mayo, local);
		rd6.setTimeScaleUnitType(TimeScaleUnit.DAY);
		rd6.setDuration(2);
		
		ReportDefinition rd7 = Fixtures.createReportDefinition("ctep-other-1", otherSite, expedited);

		List<ReportDefinition> ctepRdList = new ArrayList<ReportDefinition>();
		List<ReportDefinition> mayoRdList = new ArrayList<ReportDefinition>();
		List<ReportDefinition> otherList = new ArrayList<ReportDefinition>();

		ctepRdList.add(rd1);
		ctepRdList.add(rd2);
		ctepRdList.add(rd3);
		ctepRdList.add(rd4);

		mayoRdList.add(rd5);
		mayoRdList.add(rd6);
		
		otherList.add(rd7);

		OrganizationAssignedIdentifier ctepIdentifier = Fixtures
				.createOrganizationAssignedIdentifier("C1", ctep);
		OrganizationAssignedIdentifier mayoIdentifier = Fixtures
				.createOrganizationAssignedIdentifier("M1", mayo);
		OrganizationAssignedIdentifier otherIdentifier = Fixtures.createOrganizationAssignedIdentifier("Other", otherSite);

		Study study = Fixtures.createStudy("Test");
		StudyFundingSponsor sponsor = Fixtures.createStudyFundingSponsor(ctep);
		StudyCoordinatingCenter cordCenter = Fixtures
				.createStudyCoordinatingCenter(mayo);
		StudySite stSite = Fixtures.createStudySite(otherSite, 1);

		study.addStudyOrganization(sponsor);
		study.addStudyOrganization(cordCenter);
		study.addStudyOrganization(stSite);
		study.addIdentifier(ctepIdentifier);
		study.addIdentifier(mayoIdentifier);
		study.addIdentifier(otherIdentifier);

		EasyMock.expect(reportDefinitionDao.getAll(1)).andReturn(ctepRdList);
		EasyMock.expect(reportDefinitionDao.getAll(2)).andReturn(mayoRdList);
		
		StudyParticipantAssignment assignment = Fixtures.createAssignment();
		StudySite studySite = Fixtures.createStudySite(ctep, 2);
		assignment.setStudySite(studySite);

		replayMocks();
		ApplicableReportDefinitionsDTO dto = service.applicableReportDefinitions(study, assignment);
		
		assertEquals(2, dto.getOrganizationTypeMap().size());
		assertEquals(2, dto.getOrganizationTypeMap().get(ctep).get("expedited").size());

		verifyMocks();
	}
	
	/**
	 * Testing the case, when rules engine throws an exception out.
	 * @throws Exception
	 */
	public void testEvaluateSAERules_ThrowingException() throws Exception {
		// tests on new reporting period.

		Map<String, List<String>> map = new HashMap<String, List<String>>();
		map.put("a1", Arrays.asList(new String[] { "one", "two" }));
		map.put("a2", Arrays.asList(new String[] { "one", "two", "three" }));
		AdverseEvent ae1 = Fixtures.createAdverseEvent(1, Grade.MILD);
		AdverseEvent ae2 = Fixtures.createAdverseEvent(2,
				Grade.LIFE_THREATENING);
		ArrayList<AdverseEvent> aeList = new ArrayList<AdverseEvent>();
		aeList.add(ae1);
		aeList.add(ae2);

		Study study = Fixtures.createStudy("test");
		AdverseEventReportingPeriod reportingPeriod = registerMockFor(AdverseEventReportingPeriod.class);
		EasyMock.expect(reportingPeriod.getAeReports()).andReturn(null);
		EasyMock.expect(reportingPeriod.getNonExpeditedAdverseEvents()).andReturn(aeList);
		EasyMock.expect(reportingPeriod.getStudy()).andReturn(study);

		EasyMock.expect(adverseEventEvaluationService.evaluateSAEReportSchedule(null,aeList, study)).andThrow(new Exception());

		replayMocks();

		EvaluationResultDTO result;
		try {
			result = service.evaluateSAERules(reportingPeriod);
			fail("must have thrown caaers system exception");
		} catch (CaaersSystemException e) {
		}

		verifyMocks();

	}

	/**
	 * There is no existing data collection available on Reporting Period, we added two new AEs, both suggesting different reports but all belonging
	 * to the different group and organization. 
	 * 
	 * Reporting Period
	 *  AE1 - suggests RD1 and RD2
	 *  AE2 - Suggests RD1 , RD2 and RD3
	 *  ------------------------------------
	 *  Alert for aeReportId -0
	 *  Amend, edit, withdraw maps empty
	 *  Create map, having only RD1, RD2, RD3.
	 *   
	 * @throws Exception
	 */
	public void testEvaluateSAERules() throws Exception {
		// tests on new reporting period.
		AdverseEvent ae1 = Fixtures.createAdverseEvent(1, Grade.MILD);
		AdverseEvent ae2 = Fixtures.createAdverseEvent(2,Grade.LIFE_THREATENING);
		ArrayList<AdverseEvent> aeList = new ArrayList<AdverseEvent>();
		aeList.add(ae1);
		aeList.add(ae2);
		
		Map<AdverseEvent, List<String>> map = new HashMap<AdverseEvent, List<String>>();
		map.put(ae1, Arrays.asList(new String[] { "rd1", "rd2" }));
		map.put(ae2, Arrays.asList(new String[] { "rd1", "rd2", "rd3"}));
		

		Study study = Fixtures.createStudy("test");

		ReportDefinition rd1 = Fixtures.createReportDefinition("rd1", "rd1", 1,TimeScaleUnit.DAY);
		rd1.setGroup(Fixtures.createConfigProperty("expedited"));
		rd1.getOrganization().setId(1);
		ReportDefinition rd2 = Fixtures.createReportDefinition("rd2", "rd2", 2,TimeScaleUnit.DAY);
		rd2.setGroup(Fixtures.createConfigProperty("expedited"));
		rd2.getOrganization().setId(2);
		ReportDefinition rd3 = Fixtures.createReportDefinition("rd3", "rd3", 3,TimeScaleUnit.DAY);
		rd3.setGroup(Fixtures.createConfigProperty("expedited"));
		rd3.getOrganization().setId(3);

		AdverseEventReportingPeriod reportingPeriod = registerMockFor(AdverseEventReportingPeriod.class);
		EasyMock.expect(reportingPeriod.getAeReports()).andReturn(null);
		EasyMock.expect(reportingPeriod.getNonExpeditedAdverseEvents()).andReturn(aeList);
		EasyMock.expect(reportingPeriod.getStudy()).andReturn(study);

		EasyMock.expect(adverseEventEvaluationService.evaluateSAEReportSchedule(null,aeList, study)).andReturn(map);
		EasyMock.expect(reportDefinitionDao.getByName("rd1")).andReturn(rd1);
		EasyMock.expect(reportDefinitionDao.getByName("rd2")).andReturn(rd2);
		EasyMock.expect(reportDefinitionDao.getByName("rd3")).andReturn(rd3);

		replayMocks();

		EvaluationResultDTO result = service.evaluateSAERules(reportingPeriod);
	
		assertEquals(3, result.getAdverseEventIndexMap().get(ZERO).get(ae2).size());
		assertTrue( result.getAdverseEventIndexMap().get(ZERO).get(ae2).containsAll(Arrays.asList(rd1, rd2, rd3)));
		
		assertEquals(2, result.getAdverseEventIndexMap().get(ZERO).get(ae1).size());
		assertTrue( result.getAdverseEventIndexMap().get(ZERO).get(ae1).containsAll(Arrays.asList(rd1, rd2)));

		assertTrue(result.getAeReportAlertMap().get(new Integer(0)));
		
		assertTrue(result.getAmendmentMap().get(new Integer(0)).isEmpty());
		assertTrue(result.getEditMap().get(new Integer(0)).isEmpty());
		assertTrue(result.getWithdrawalMap().get(new Integer(0)).isEmpty());
		assertFalse(result.getCreateMap().get(new Integer(0)).isEmpty());
		
		assertNotNull(getWrapper(result.getCreateMap().get(new Integer(0)), rd1));
		assertNotNull(getWrapper(result.getCreateMap().get(new Integer(0)), rd2));
		assertNotNull(getWrapper(result.getCreateMap().get(new Integer(0)), rd3));
		

		verifyMocks();

	}
	

	/**
	 * There is no existing data collection available on Reporting Period, we added two new AEs, both suggesting different reports but all belonging
	 * to the same group and organization. 
	 * 
	 * Reporting Period
	 *  AE1 - suggests RD1 and RD2
	 *  AE2 - Suggests RD1 , RD2 and RD3
	 *  
	 *  RD1 - 1Day, RD2 - 2Day, RD3- 3Day
	 *  ------------------------------------
	 *  Alert for aeReportId -0
	 *  Amend, edit, withdraw maps empty
	 *  Create map, having only RD1.
	 *   
	 * @throws Exception
	 */
	public void testEvaluateSAERules_CheckingFilteringOfReportDefinitions() throws Exception {
		// tests on new reporting period.
		AdverseEvent ae1 = Fixtures.createAdverseEvent(1, Grade.MILD);
		AdverseEvent ae2 = Fixtures.createAdverseEvent(2,Grade.LIFE_THREATENING);
		ArrayList<AdverseEvent> aeList = new ArrayList<AdverseEvent>();
		aeList.add(ae1);
		aeList.add(ae2);
		
		Map<AdverseEvent, List<String>> map = new HashMap<AdverseEvent, List<String>>();
		map.put(ae1, Arrays.asList(new String[] { "one", "two" }));
		map.put(ae2, Arrays.asList(new String[] { "one", "two", "three"}));
		

		Study study = Fixtures.createStudy("test");

		ReportDefinition rd1 = Fixtures.createReportDefinition("rd1", "rd1", 1,TimeScaleUnit.DAY);
		rd1.setId(1);
		rd1.setGroup(Fixtures.createConfigProperty("expedited"));
		rd1.getOrganization().setId(1);
		ReportDefinition rd2 = Fixtures.createReportDefinition("rd2", "rd2", 2,TimeScaleUnit.DAY);
		rd2.setId(2);
		rd2.setGroup(Fixtures.createConfigProperty("expedited"));
		rd2.getOrganization().setId(1);
		ReportDefinition rd3 = Fixtures.createReportDefinition("rd3", "rd3", 3,TimeScaleUnit.DAY);
		rd3.setId(3);
		rd3.setGroup(Fixtures.createConfigProperty("expedited"));
		rd3.getOrganization().setId(1);

		AdverseEventReportingPeriod reportingPeriod = registerMockFor(AdverseEventReportingPeriod.class);
		EasyMock.expect(reportingPeriod.getAeReports()).andReturn(null);
		EasyMock.expect(reportingPeriod.getNonExpeditedAdverseEvents()).andReturn(aeList);
		EasyMock.expect(reportingPeriod.getStudy()).andReturn(study);

		EasyMock.expect(adverseEventEvaluationService.evaluateSAEReportSchedule(null,aeList, study)).andReturn(map);
		EasyMock.expect(reportDefinitionDao.getByName("one")).andReturn(rd1);
		EasyMock.expect(reportDefinitionDao.getByName("two")).andReturn(rd2);
		EasyMock.expect(reportDefinitionDao.getByName("three")).andReturn(rd3);

		replayMocks();

		EvaluationResultDTO result = service.evaluateSAERules(reportingPeriod);

//		assertEquals(1, result.getAeIndexMap().get(ae1).size());
//		assertEquals(1, result.getAeIndexMap().get(ae2).size());
//		assertTrue(result.getAeIndexMap().get(ae2).containsAll(Arrays.asList(new ReportDefinition[] { rd1})));
//
//		assertEquals(2, result.getReportDefAeIndexMap().get(rd1).size());
//		assertTrue(result.getReportDefAeIndexMap().get(rd1).containsAll(aeList));

		//alert
		assertTrue(result.getAeReportAlertMap().get(new Integer(0)));
		
		//amendment , edit, withdraw, create
		assertTrue(result.getAmendmentMap().get(new Integer(0)).isEmpty());
		assertTrue(result.getEditMap().get(new Integer(0)).isEmpty());
		assertTrue(result.getWithdrawalMap().get(new Integer(0)).isEmpty());
		assertFalse(result.getCreateMap().get(new Integer(0)).isEmpty());
		
		assertNotNull(getWrapper(result.getCreateMap().get(new Integer(0)), rd1));
		assertNull(getWrapper(result.getCreateMap().get(new Integer(0)), rd2));
		assertNull(getWrapper(result.getCreateMap().get(new Integer(0)), rd3));
		
		

		verifyMocks();

	}
	
	/**
	 * There is a data collection that has a completed report. Now we add a new AE2, Rules engine suggests RD2 for
	 *  newly added AE2. caAERS should recommend, amending of RD1 with RD2.  
	 * 
	 *   Report defintion
	 *     RD1 
	 *     RD2
	 *     (Note RD1 and RD2 belongs to same group)
	 *     
	 *   DataCollection 
	 *     "RD1" completed
	 *     AE1
	 *     Rules suggested RD2 on this data collection (ie. AE1+AE2)
	 *     
	 * ---
	 * Newly added AE2
	 *   "RD2" suggested 
	 *   
	 * Expected behavior
	 * ----------------
	 * Alert for DC1 and DC2
	 * 
	 *  DATA Collection 1
	 *    Ammend map should say RD1 ammended
	 *    Create Map should say RD2 create
	 *  DATA Collection 0   
	 *    Create map should say RD2
	 *    
	 *    
	 * @throws Exception 
	 *   
	 */
	public void testEvaluateSAERules_OneReportGettingAmmended() throws Exception{
		AdverseEvent ae1 = Fixtures.createAdverseEvent(1, Grade.MILD);
		ae1.setGradedDate(new Date());
		AdverseEvent ae2 = Fixtures.createAdverseEvent(2,Grade.LIFE_THREATENING);
		ae2.setGradedDate(new Date());
		
		ArrayList<AdverseEvent> aeList = new ArrayList<AdverseEvent>();
		aeList.add(ae2);
		aeList.add(ae1);
		

		ArrayList<AdverseEvent> aeList1 = new ArrayList<AdverseEvent>();
		aeList1.add(ae1);
		
		ArrayList<AdverseEvent> aeList2 = new ArrayList<AdverseEvent>();
		aeList2.add(ae2);
		
		//rd1 and rd2 belongs to same group and org. 
		ReportDefinition rd1 = Fixtures.createReportDefinition("RD1", "001", 1, TimeScaleUnit.DAY);
		rd1.setId(1);
		rd1.setGroup(Fixtures.createConfigProperty("expedited"));
		rd1.getOrganization().setId(1);
		
		ReportDefinition rd2 = Fixtures.createReportDefinition("RD2", "001", 2, TimeScaleUnit.DAY);
		rd2.setId(2);
		rd2.setGroup(rd1.getGroup());
		rd2.setOrganization(rd1.getOrganization());
		
		Report r1 = Fixtures.createReport("test");
		r1.setReportDefinition(rd1);
		r1.getLastVersion().setSubmittedOn(new Date());
		r1.setStatus(ReportStatus.COMPLETED);
		r1.setSubmittedOn(new Date());
		r1.getLastVersion().setReportStatus(ReportStatus.COMPLETED);
		
		ExpeditedAdverseEventReport aeReport1 = registerMockFor(ExpeditedAdverseEventReport.class);
		expect(aeReport1.getId()).andReturn(new Integer(1)).anyTimes();
		expect(aeReport1.getActiveModifiedAdverseEvents()).andReturn(aeList1);
		expect(aeReport1.getModifiedAdverseEvents()).andReturn(aeList1);
		expect(aeReport1.getAdverseEvents()).andReturn(aeList1).anyTimes();
		expect(aeReport1.isActive()).andReturn(false);
		expect(aeReport1.getManuallySelectedReports()).andReturn(new ArrayList<Report>());
		expect(aeReport1.getActiveReports()).andReturn(new ArrayList<Report>());
		expect(aeReport1.listReportsHavingStatus(ReportStatus.COMPLETED)).andReturn(Arrays.asList(r1)).times(2);
		expect(aeReport1.findCompletedAmendableReports()).andReturn(EMPTY_REPORT_LIST);
		Study study = Fixtures.createStudy("test");
		
		AdverseEventReportingPeriod reportingPeriod = registerMockFor(AdverseEventReportingPeriod.class);
		expect(reportingPeriod.getAeReports()).andReturn(Arrays.asList(aeReport1));
		expect(reportingPeriod.getNonExpeditedAdverseEvents()).andReturn(aeList2);
		expect(reportingPeriod.getStudy()).andReturn(study).anyTimes();
		
		
		Map<AdverseEvent, List<String>> map0 = new HashMap<AdverseEvent, List<String>>();
		map0.put(ae2, Arrays.asList("RD2"));
		
		Map<AdverseEvent, List<String>> map1 = new HashMap<AdverseEvent, List<String>>();
		map1.put(ae1, new ArrayList<String>()); //noting returned by rules
		map1.put(ae2, Arrays.asList("RD2"));// R2 for ae2 aswell for this data collection
		
		expect(adverseEventEvaluationService.evaluateSAEReportSchedule(null, aeList2, study)).andReturn(map0);
		
		expect(adverseEventEvaluationService.evaluateSAEReportSchedule(aeReport1, aeList, study)).andReturn(map1);
		
		//expect(reportDefinitionDao.getByName("RD1")).andReturn(rd1);
		expect(reportDefinitionDao.getByName("RD2")).andReturn(rd2).anyTimes();
		expect(aeReport1.findReportsToAmmend(rd2)).andReturn(Arrays.asList(r1));
		expect(aeReport1.findReportsToEdit(rd2)).andReturn(new ArrayList<Report>()).times(2);
		expect(aeReport1.findReportsToWitdraw(rd2)).andReturn(new ArrayList<Report>());
		
		replayMocks();

		EvaluationResultDTO result = service.evaluateSAERules(reportingPeriod);
		
		//alert
		assertTrue(result.getAeReportAlertMap().get(ZERO));
		assertTrue(result.getAeReportAlertMap().get(aeReport1.getId()));
		
		//amend, create, edit, withdraw maps.
		//aeReport -0
		assertTrue(result.getAmendmentMap().get(ZERO).isEmpty());
		assertTrue(result.getWithdrawalMap().get(ZERO).isEmpty());
		assertTrue(result.getEditMap().get(ZERO).isEmpty());
		assertFalse(result.getCreateMap().get(ZERO).isEmpty());
		
		//aeReport 1
		assertFalse(result.getAmendmentMap().get(aeReport1.getId()).isEmpty());
		assertFalse(result.getCreateMap().get(aeReport1.getId()).isEmpty());
		assertTrue(result.getWithdrawalMap().get(aeReport1.getId()).isEmpty());
		assertTrue(result.getEditMap().get(aeReport1.getId()).isEmpty());
		
		assertNotNull(getWrapper(result.getAmendmentMap().get(aeReport1.getId()), rd1));
		assertEquals(rd2, getWrapper(result.getAmendmentMap().get(aeReport1.getId()), rd1).getSubstitute());
		assertNotNull(getWrapper(result.getCreateMap().get(aeReport1.getId()), rd2));
		verifyMocks();
		
	}
	
	
	/**
	 * There is a data collection that has a completed report. Now we add a new AE2, Rules engine suggests RD2 for
	 *  newly added AE2. RD1 is a manually selected one. 
	 *  -caAERS should not recommend anything for DC1.
	 *  -caAERS should recommend RD2 for aeReport-0
	 * 
	 *   Report definition
	 *     RD1  (manually selected)
	 *     RD2
	 *     (Note RD1 and RD2 belongs to same group)
	 *     
	 *   DataCollection 
	 *     "RD1" completed
	 *     AE1
	 *     Rules suggested RD2 on this data collection (ie. AE1+AE2)
	 *     
	 * ---
	 * Newly added AE2
	 *   "RD2" suggested 
	 *   
	 * Expected behavior
	 * ----------------
	 * Alert for DC1 and DC2
	 * 
	 *  DATA Collection 1
	 *    Amend map should say RD1 amended
	 *    Create Map should say RD2 create
	 *  DATA Collection 0   
	 *    Create map should say RD2
	 *    
	 *    
	 * @throws Exception 
	 *   
	 */
	public void testEvaluateSAERules_OneManuallySelectedReportGettingAmmended() throws Exception{
		AdverseEvent ae1 = Fixtures.createAdverseEvent(1, Grade.MILD);
		ae1.setGradedDate(new Date());
		AdverseEvent ae2 = Fixtures.createAdverseEvent(2,Grade.LIFE_THREATENING);
		ae2.setGradedDate(new Date());
		
		ArrayList<AdverseEvent> aeList = new ArrayList<AdverseEvent>();
		aeList.add(ae2);
		aeList.add(ae1);
		

		ArrayList<AdverseEvent> aeList1 = new ArrayList<AdverseEvent>();
		aeList1.add(ae1);
		
		ArrayList<AdverseEvent> aeList2 = new ArrayList<AdverseEvent>();
		aeList2.add(ae2);
		
		//rd1 and rd2 belongs to same group and org. 
		ReportDefinition rd1 = Fixtures.createReportDefinition("RD1", "001", 1, TimeScaleUnit.DAY);
		rd1.setId(1);
		rd1.setGroup(Fixtures.createConfigProperty("expedited"));
		rd1.getOrganization().setId(1);
		
		ReportDefinition rd2 = Fixtures.createReportDefinition("RD2", "001", 2, TimeScaleUnit.DAY);
		rd2.setId(2);
		rd2.setGroup(rd1.getGroup());
		rd2.setOrganization(rd1.getOrganization());
		
		Report r1 = Fixtures.createReport("test");
		r1.setReportDefinition(rd1);
		r1.getLastVersion().setSubmittedOn(new Date());
		r1.setStatus(ReportStatus.COMPLETED);
		r1.setManuallySelected(true);
		r1.setSubmittedOn(new Date());
		r1.getLastVersion().setReportStatus(ReportStatus.COMPLETED);
		
		ExpeditedAdverseEventReport aeReport1 = registerMockFor(ExpeditedAdverseEventReport.class);
		expect(aeReport1.getId()).andReturn(new Integer(1)).anyTimes();
		expect(aeReport1.getActiveModifiedAdverseEvents()).andReturn(EMPTY_AE_LIST);
		expect(aeReport1.getModifiedAdverseEvents()).andReturn(EMPTY_AE_LIST);
		expect(aeReport1.getAdverseEvents()).andReturn(aeList1).anyTimes();
		expect(aeReport1.isActive()).andReturn(false);
		expect(aeReport1.getManuallySelectedReports()).andReturn(EMPTY_REPORT_LIST);
		expect(aeReport1.getActiveReports()).andReturn(new ArrayList<Report>());
		expect(aeReport1.listReportsHavingStatus(ReportStatus.COMPLETED)).andReturn(Arrays.asList(r1));
		expect(aeReport1.findCompletedAmendableReports()).andReturn(Arrays.asList(r1));
		
		
		Study study = Fixtures.createStudy("test");
		
		AdverseEventReportingPeriod reportingPeriod = registerMockFor(AdverseEventReportingPeriod.class);
		expect(reportingPeriod.getAeReports()).andReturn(Arrays.asList(aeReport1));
		expect(reportingPeriod.getNonExpeditedAdverseEvents()).andReturn(aeList2);
		expect(reportingPeriod.getStudy()).andReturn(study).anyTimes();
		expect(aeReport1.findReportsToAmmend(rd2)).andReturn(Arrays.asList(r1));
		expect(aeReport1.findReportsToEdit(rd2)).andReturn(new ArrayList<Report>()).times(2);
		expect(aeReport1.findReportsToWitdraw(rd2)).andReturn(new ArrayList<Report>());
		
		Map<AdverseEvent, List<String>> map0 = new HashMap<AdverseEvent, List<String>>();
		map0.put(ae2, Arrays.asList("RD2"));
		
		Map<AdverseEvent, List<String>> map1 = new HashMap<AdverseEvent, List<String>>();
		map1.put(ae2, Arrays.asList("RD2"));// R2 for ae2 aswell for this data collection
		
		expect(adverseEventEvaluationService.evaluateSAEReportSchedule(null, aeList2, study)).andReturn(map0);
		
		expect(adverseEventEvaluationService.evaluateSAEReportSchedule(aeReport1, aeList2, study)).andReturn(map1);
		
		expect(reportDefinitionDao.getByName("RD2")).andReturn(rd2).anyTimes();
		
		
		replayMocks();

		EvaluationResultDTO result = service.evaluateSAERules(reportingPeriod);
		
		//alert
		assertTrue(result.getAeReportAlertMap().get(ZERO));
		assertTrue(result.getAeReportAlertMap().get(aeReport1.getId()));
		
		//amend, create, edit, withdraw maps.
		//aeReport -0
		assertTrue(result.getAmendmentMap().get(ZERO).isEmpty());
		assertTrue(result.getWithdrawalMap().get(ZERO).isEmpty());
		assertTrue(result.getEditMap().get(ZERO).isEmpty());
		assertFalse(result.getCreateMap().get(ZERO).isEmpty());
		assertNotNull(getWrapper(result.getCreateMap().get(ZERO), rd2).getDef());
		
		
		//aeReport 1
		assertTrue(result.getWithdrawalMap().get(aeReport1.getId()).isEmpty());
		assertTrue(result.getEditMap().get(aeReport1.getId()).isEmpty());
		assertNotNull(getWrapper(result.getAmendmentMap().get(aeReport1.getId()), rd1).getDef());
		assertEquals(rd2, getWrapper(result.getAmendmentMap().get(aeReport1.getId()), rd1).getSubstitute());
		assertNotNull(getWrapper(result.getCreateMap().get(aeReport1.getId()), rd2).getDef());
		
		verifyMocks();
		
	}
	

	/**
	 * There is a data collection that has a submitted/amended report (RD1). An in process child report RD2( parent : RD1),
	 * we add now a new AE (AE2), now rules engine suggests RD1 for the newly added AE. 
	 *  caAERS should recommend, editing of RD2 (as the child RD2 is still active). 
	 * 
	 *   Report definition
	 *     RD1 
	 *     RD2 (Note RD1 is the parent of RD2)
	 *     
	 *   DataCollection - 1
	 *     "RD1" completed
	 *     RD2 - In progress
	 *     AE1
	 *     Rules suggested RD1 on this data collection (ie. AE1+AE2)
	 *     
	 * Data collect - 0
	 *  
	 * Newly added AE2
	 *   "RD2" suggested 
	 *   
	 * Expected behavior
	 * ----------------
	 * Alert for DC1 and DC0
	 * 
	 *  DATA Collection 1
	 *    Edit RD2, Amend, Create and Withdraw should be empty. 
	 *    
	 *  DATA Collection 0   
	 *    Create map should say RD1
	 *    
	 *    
	 * @throws Exception 
	 *   
	 */
	public void testEvaluateSAERules_ChildReportTakingPrecedenceOverSuggestedParent() throws Exception{
		AdverseEvent ae1 = Fixtures.createAdverseEvent(1, Grade.MILD);
		ae1.setGradedDate(new Date());
		AdverseEvent ae2 = Fixtures.createAdverseEvent(2,Grade.LIFE_THREATENING);
		ae2.setGradedDate(new Date());
		
		ArrayList<AdverseEvent> aeList = new ArrayList<AdverseEvent>();
		aeList.add(ae2);
		aeList.add(ae1);
		

		ArrayList<AdverseEvent> aeList1 = new ArrayList<AdverseEvent>();
		aeList1.add(ae1);
		
		ArrayList<AdverseEvent> aeList2 = new ArrayList<AdverseEvent>();
		aeList2.add(ae2);
		
		//rd1 and rd2 belongs to same group and org. 
		ReportDefinition rd1 = Fixtures.createReportDefinition("RD1", "001", 1, TimeScaleUnit.DAY);
		rd1.setId(1);
		rd1.setGroup(Fixtures.createConfigProperty("expedited"));
		rd1.getOrganization().setId(1);
		
		ReportDefinition rd2 = Fixtures.createReportDefinition("RD2", "001", 2, TimeScaleUnit.DAY);
		rd2.setId(2);
		rd2.setGroup(rd1.getGroup());
		rd2.setOrganization(rd1.getOrganization());
		rd2.setParent(rd1); //set the hierarchy
		
		
		Report r1 = Fixtures.createReport("test");
		r1.setReportDefinition(rd1);
		r1.getLastVersion().setSubmittedOn(new Date());
		r1.setStatus(ReportStatus.AMENDED);
		r1.setSubmittedOn(new Date());
		r1.setAmendedOn(new Date());
		
		Report r2 = Fixtures.createReport("test");
		r2.setReportDefinition(rd2);
		r2.getLastVersion().setCreatedOn(new Date());
		r2.setStatus(ReportStatus.INPROCESS);
		
		ExpeditedAdverseEventReport aeReport1 = registerMockFor(ExpeditedAdverseEventReport.class);
		expect(aeReport1.getId()).andReturn(new Integer(1)).anyTimes();
		expect(aeReport1.getActiveAdverseEvents()).andReturn(aeList1);
		expect(aeReport1.getModifiedAdverseEvents()).andReturn(EMPTY_AE_LIST);
		expect(aeReport1.getAdverseEvents()).andReturn(aeList1).anyTimes();
		expect(aeReport1.isActive()).andReturn(true);
		expect(aeReport1.getManuallySelectedReports()).andReturn(new ArrayList<Report>());
		expect(aeReport1.getActiveReports()).andReturn(Arrays.asList(r2));
		
		Study study = Fixtures.createStudy("test");
		
		AdverseEventReportingPeriod reportingPeriod = registerMockFor(AdverseEventReportingPeriod.class);
		expect(reportingPeriod.getAeReports()).andReturn(Arrays.asList(aeReport1));
		expect(reportingPeriod.getNonExpeditedAdverseEvents()).andReturn(aeList2);
		expect(reportingPeriod.getStudy()).andReturn(study).anyTimes();
		
		
		Map<AdverseEvent, List<String>> map0 = new HashMap<AdverseEvent, List<String>>();
		map0.put(ae2, Arrays.asList("RD1"));
		
		Map<AdverseEvent, List<String>> map1 = new HashMap<AdverseEvent, List<String>>();
		map1.put(ae1, new ArrayList<String>()); //noting returned by rules
		map1.put(ae2, Arrays.asList("RD1"));// R1 for ae2 aswell for this data collection
		
		expect(adverseEventEvaluationService.evaluateSAEReportSchedule(null, aeList2, study)).andReturn(map0);
		
		expect(adverseEventEvaluationService.evaluateSAEReportSchedule(aeReport1, aeList, study)).andReturn(map1);
		
		expect(reportDefinitionDao.getByName("RD1")).andReturn(rd1).times(2);
		expect(aeReport1.findReportsToAmmend(rd2)).andReturn(new ArrayList<Report>());
		expect(aeReport1.findReportsToEdit(rd2)).andReturn(Arrays.asList(r2)).times(2);
		expect(aeReport1.findReportsToWitdraw(rd2)).andReturn(new ArrayList<Report>());
		expect(aeReport1.listReportsHavingStatus(ReportStatus.COMPLETED)).andReturn(EMPTY_REPORT_LIST);
		expect(aeReport1.findCompletedAmendableReports()).andReturn(EMPTY_REPORT_LIST);
		
		replayMocks();

		EvaluationResultDTO result = service.evaluateSAERules(reportingPeriod);
		
		//alert
		assertTrue(result.getAeReportAlertMap().get(ZERO));
		assertFalse(result.getAeReportAlertMap().get(aeReport1.getId()));
		
		//amend, create, edit, withdraw maps.
		//aeReport -0
		assertTrue(result.getAmendmentMap().get(ZERO).isEmpty());
		assertTrue(result.getWithdrawalMap().get(ZERO).isEmpty());
		assertTrue(result.getEditMap().get(ZERO).isEmpty());
		assertFalse(result.getCreateMap().get(ZERO).isEmpty());
		assertNotNull(getWrapper(result.getCreateMap().get(ZERO), rd1));
		
		//aeReport 1
		assertTrue(result.getAmendmentMap().get(aeReport1.getId()).isEmpty());
		assertTrue(result.getCreateMap().get(aeReport1.getId()).isEmpty());
		assertTrue(result.getWithdrawalMap().get(aeReport1.getId()).isEmpty());
		assertFalse(result.getEditMap().get(aeReport1.getId()).isEmpty());
		
		assertNotNull(getWrapper(result.getEditMap().get(aeReport1.getId()), rd2));
		verifyMocks();
		
	}
	
	
	/**
	 * There are 2 completed reports (RD1 and RDZ), but modification of an AE suggest amendment of RD1 (with RD2). 
	 * caAERS should, recommend RD1 amend with RD2 and RDZ amend (forced) with itself. 
	 *  
	 * Report definition
	 *  RD1 ,RD2 and RDZ
	 *  (RD1 and RD2 same group, RDZ different)
	 *  
	 * Case 
	 *   DataCollection 
	 *      RD1 completed
	 *      RD-Z completed
	 *      AE1
	 *      AE2
	 *      
	 *  Rules engine suggested RD2 (for AE1 modification)    
	 * --
	 * DataCollection 1
	 *   Alert yes
	 *   Amendment - RD1, RDZ
	 *   Create - RD2,RDZ
	 *   
	 * Newly added AE2
	 *   everything empty
	 */
	public void testEvaluateSAERules_2Completed_But_OneAmmended() throws Exception{
		AdverseEvent ae1 = Fixtures.createAdverseEvent(1, Grade.MILD);
		ae1.setGradedDate(new Date());
		ae1.setEndDate(new Date()); //force modify
		
		AdverseEvent ae2 = Fixtures.createAdverseEvent(2,Grade.LIFE_THREATENING);
		ae2.setGradedDate(new Date());
		
		ArrayList<AdverseEvent> aeList = new ArrayList<AdverseEvent>();
		aeList.add(ae2);
		aeList.add(ae1);
		

		
		ArrayList<AdverseEvent> aeList2 = new ArrayList<AdverseEvent>();
		
		ReportDefinition rdz = Fixtures.createReportDefinition("RZ", "00z", 2, TimeScaleUnit.DAY);
		rdz.getOrganization().setId(5);
		rdz.setGroup(Fixtures.createConfigProperty("zzz"));
		rdz.setId(33);
		
		ReportDefinition rd1 = Fixtures.createReportDefinition("RD1", "001", 1, TimeScaleUnit.DAY);
		rd1.setGroup(Fixtures.createConfigProperty("expedited"));
		rd1.getOrganization().setId(1);
		rd1.setId(1);
		
		ReportDefinition rd2 = Fixtures.createReportDefinition("RD2", "001", 2, TimeScaleUnit.DAY);
		rd2.setGroup(rd1.getGroup());
		rd2.setOrganization(rd1.getOrganization());
		rd2.setId(2);
		
		Report r1 = Fixtures.createReport("test");
		r1.setReportDefinition(rd1);
		r1.getLastVersion().setSubmittedOn(new Date());
		r1.setStatus(ReportStatus.COMPLETED);
		r1.setSubmittedOn(new Date());
		r1.getLastVersion().setReportStatus(ReportStatus.COMPLETED);
		
		Report rz = Fixtures.createReport("testz");
		rz.setReportDefinition(rdz);
		rz.getLastVersion().setSubmittedOn(new Date());
		rz.setStatus(ReportStatus.COMPLETED);
		rz.setSubmittedOn(new Date());
		rz.getLastVersion().setReportStatus(ReportStatus.COMPLETED);
		
		ExpeditedAdverseEventReport aeReport1 = registerMockFor(ExpeditedAdverseEventReport.class);
		expect(aeReport1.getId()).andReturn(new Integer(1)).anyTimes();
		expect(aeReport1.getActiveModifiedAdverseEvents()).andReturn(Arrays.asList(ae1)).anyTimes();
		expect(aeReport1.getModifiedAdverseEvents()).andReturn(Arrays.asList(ae1)).anyTimes();
		expect(aeReport1.listReportsHavingStatus(ReportStatus.COMPLETED)).andReturn(Arrays.asList(r1, rz)).times(2);
		expect(aeReport1.findCompletedAmendableReports()).andReturn(Arrays.asList(r1, rz));
		expect(aeReport1.getActiveReports()).andReturn(new ArrayList<Report>());
		expect(aeReport1.isActive()).andReturn(false);
		expect(aeReport1.getAdverseEvents()).andReturn(Arrays.asList(ae1, ae2)).anyTimes();
		expect(aeReport1.getManuallySelectedReports()).andReturn(new ArrayList<Report>());
		
		Study study = Fixtures.createStudy("test");
		
		AdverseEventReportingPeriod reportingPeriod = registerMockFor(AdverseEventReportingPeriod.class);
		expect(reportingPeriod.getAeReports()).andReturn(Arrays.asList(aeReport1));
		expect(reportingPeriod.getNonExpeditedAdverseEvents()).andReturn(new ArrayList<AdverseEvent>());
		expect(reportingPeriod.getStudy()).andReturn(study).anyTimes();
		
		//rules engine result
		Map<AdverseEvent, List<String>> map0 = new HashMap<AdverseEvent, List<String>>();
		Map<AdverseEvent, List<String>> map1 = new HashMap<AdverseEvent, List<String>>();
		map1.put(ae1, Arrays.asList("RD2")); 
		
		expect(adverseEventEvaluationService.evaluateSAEReportSchedule(null, aeList2, study)).andReturn(map0);
		
		expect(adverseEventEvaluationService.evaluateSAEReportSchedule(aeReport1, Arrays.asList(ae1), study)).andReturn(map1);
		
		expect(reportDefinitionDao.getByName("RD2")).andReturn(rd2).anyTimes();
		expect(aeReport1.findReportsToAmmend(rd2)).andReturn(Arrays.asList(r1));
		expect(aeReport1.findReportsToEdit(rd2)).andReturn(new ArrayList<Report>()).times(2);
		expect(aeReport1.findReportsToEdit(rdz)).andReturn(new ArrayList<Report>());
		expect(aeReport1.findReportsToWitdraw(rd2)).andReturn(new ArrayList<Report>());
		
		expect(aeReport1.findReportsToAmmend(rdz)).andReturn(Arrays.asList(rz));
		expect(aeReport1.findReportsToEdit(rdz)).andReturn(new ArrayList<Report>());
		expect(aeReport1.findReportsToWitdraw(rdz)).andReturn(new ArrayList<Report>());
		
		replayMocks();

		EvaluationResultDTO result = service.evaluateSAERules(reportingPeriod);
		
		//alert
		assertFalse(result.getAeReportAlertMap().get(ZERO));
		assertTrue(result.getAeReportAlertMap().get(aeReport1.getId()));
		
		assertTrue(result.getCreateMap().get(ZERO).isEmpty());
		assertTrue(result.getEditMap().get(ZERO).isEmpty());
		assertTrue(result.getAmendmentMap().get(ZERO).isEmpty());
		assertTrue(result.getWithdrawalMap().get(ZERO).isEmpty());
	    
		assertTrue(result.getEditMap().get(aeReport1.getId()).isEmpty());
		assertTrue(result.getWithdrawalMap().get(aeReport1.getId()).isEmpty());
		
		assertNotNull( getWrapper(result.getAmendmentMap().get(aeReport1.getId()), rd1));
		assertNotNull( getWrapper(result.getAmendmentMap().get(aeReport1.getId()), rdz));
		
		assertNotNull( getWrapper(result.getCreateMap().get(aeReport1.getId()), rd2));
		assertNotNull( getWrapper(result.getCreateMap().get(aeReport1.getId()), rdz));
		
		verifyMocks();
	}
	

	/**
	 * There is a report that is completed (RD1), we make a new AE2, that suggests RD1 again. 
	 *   DataCollection 
	 *      RD1 completed
	 *      AE1
	 * --
	 * Newly added AE2
	 *   "RD1 suggested" 
	 *   
	 *  Expected
	 *   aeRepot -0 - Create RD1
	 *   aeReport 1 - Amend RD1 create RD1      
	 */
	public void testEvaluateSAERules_CompletedReportSuggestedAgain() throws Exception{
		AdverseEvent ae1 = Fixtures.createAdverseEvent(1, Grade.MILD);
		ae1.setGradedDate(new Date());
		AdverseEvent ae2 = Fixtures.createAdverseEvent(2,Grade.LIFE_THREATENING);
		ae2.setGradedDate(new Date());
		
		ArrayList<AdverseEvent> aeList2 = new ArrayList<AdverseEvent>();
		aeList2.add(ae2);
		
		ReportDefinition rd1 = Fixtures.createReportDefinition("RD1", "001", 1, TimeScaleUnit.DAY);
		rd1.setGroup(Fixtures.createConfigProperty("expedited"));
		rd1.getOrganization().setId(1);
		rd1.setId(1);
		
		Report r1 = Fixtures.createReport("test");
		r1.setReportDefinition(rd1);
		r1.getLastVersion().setSubmittedOn(new Date());
		r1.setStatus(ReportStatus.COMPLETED);
		r1.setSubmittedOn(new Date());
		r1.getLastVersion().setReportStatus(ReportStatus.COMPLETED);
		
		ExpeditedAdverseEventReport aeReport1 = registerMockFor(ExpeditedAdverseEventReport.class);
		expect(aeReport1.getId()).andReturn(new Integer(1)).anyTimes();
		expect(aeReport1.getActiveModifiedAdverseEvents()).andReturn(EMPTY_AE_LIST).anyTimes();
		expect(aeReport1.getModifiedAdverseEvents()).andReturn(EMPTY_AE_LIST).anyTimes();
		expect(aeReport1.getActiveReports()).andReturn(EMPTY_REPORT_LIST);
		expect(aeReport1.isActive()).andReturn(false);
		expect(aeReport1.getAdverseEvents()).andReturn(Arrays.asList(ae1)).anyTimes();
		expect(aeReport1.getManuallySelectedReports()).andReturn(EMPTY_REPORT_LIST);
		expect(aeReport1.listReportsHavingStatus(ReportStatus.COMPLETED)).andReturn(Arrays.asList(r1));
		expect(aeReport1.findCompletedAmendableReports()).andReturn(Arrays.asList(r1));
		
		
		Study study = Fixtures.createStudy("test");
		
		AdverseEventReportingPeriod reportingPeriod = registerMockFor(AdverseEventReportingPeriod.class);
		expect(reportingPeriod.getAeReports()).andReturn(Arrays.asList(aeReport1));
		expect(reportingPeriod.getNonExpeditedAdverseEvents()).andReturn(aeList2);
		expect(reportingPeriod.getStudy()).andReturn(study).anyTimes();
		
		
		Map<AdverseEvent, List<String>> map0 = new HashMap<AdverseEvent, List<String>>();
		map0.put(ae2, Arrays.asList("RD1"));
		
		Map<AdverseEvent, List<String>> map1 = new HashMap<AdverseEvent, List<String>>();
		map1.put(ae1, new ArrayList<String>()); //noting returned by rules
		map1.put(ae2,Arrays.asList("RD1"));// RD2 for ae2 aswell for this data collection
		
		expect(adverseEventEvaluationService.evaluateSAEReportSchedule(null, aeList2, study)).andReturn(map0);
		
		expect(adverseEventEvaluationService.evaluateSAEReportSchedule(aeReport1, aeList2, study)).andReturn(map1);
		
		expect(reportDefinitionDao.getByName("RD1")).andReturn(rd1).anyTimes();
		
		expect(aeReport1.findReportsToAmmend(rd1)).andReturn(Arrays.asList(r1));
		expect(aeReport1.findReportsToEdit(rd1)).andReturn(new ArrayList<Report>()).times(2);
		expect(aeReport1.findReportsToWitdraw(rd1)).andReturn(new ArrayList<Report>());
		replayMocks();

		EvaluationResultDTO result = service.evaluateSAERules(reportingPeriod);
		
		Set<ReportDefinitionWrapper> wrappers = result.getAmendmentMap().get(new Integer(0));
		assertTrue(wrappers.isEmpty());
		
	    wrappers = result.getAmendmentMap().get(new Integer(1));
	    assertFalse(wrappers.isEmpty());
	    assertEquals(1, wrappers.size());
	    ArrayList<ReportDefinitionWrapper> wrapperList = new ArrayList<ReportDefinitionWrapper>(wrappers);
	    assertEquals(rd1, wrapperList.get(0).getDef());
	    assertEquals(rd1, wrapperList.get(0).getSubstitute());
	    
	    //- createMap
	    wrappers = result.getCreateMap().get(new Integer(0));
	    assertFalse(wrappers.isEmpty());
	    wrapperList = new ArrayList<ReportDefinitionWrapper>(wrappers);
	    assertEquals(1, wrapperList.size());
	    assertEquals(rd1, wrapperList.get(0).getDef());
	    assertNull(wrapperList.get(0).getSubstitute());
	    
	    wrappers = result.getCreateMap().get(new Integer(1));
	    assertFalse(wrappers.isEmpty());
	    wrapperList = new ArrayList<ReportDefinitionWrapper>(wrappers);
	    assertEquals(1, wrapperList.size());
	    assertEquals(rd1, wrapperList.get(0).getDef());
	    assertNull(wrapperList.get(0).getSubstitute());
	    
	    
		verifyMocks();
	}


    /**
     * Test : Rules engine suggest nothing for exiting AE modification which is part of submitted AE, but caAERS
     * should force amendment of that report. 
     * 
     * Report definitions
     *  RD1 1 Day
     *  RDX 1 Day (different group)
     *  
     *  Data collection 1
     *   RD1 Completed
     *   RDX Inprogress (rules suggested)
     *   AE1
     *   AE2
     * Modified AE1, but no reporting is suggested. 
     * 
     *----------- Exptected ----------------
     *  Alert not required for DEFAULT 
     *  Alert required for DC1
     *  
     *  DC1 - amendMap - RD1, editMap RDx, createMap RD1, withdrawMap - none
     *  0 - amendMap empty, editMap empty, createMap empty, withdraw empty.
     *  
     */
    public void testEvaluateSAERules_OneInprogressOneCompletedButRulesDidnotSuggestAny() throws Exception{
    	
    	AdverseEvent ae1 = Fixtures.createAdverseEvent(1, Grade.MILD);
		ae1.setGradedDate(new Date());
		ae1.setStartDate(new Date()); //force modification
		AdverseEvent ae2 = Fixtures.createAdverseEvent(2,Grade.LIFE_THREATENING);
		ae2.setGradedDate(new Date());
		
    	Organization org1 = Fixtures.createOrganization("test",1);
    	Organization org2 = Fixtures.createOrganization("org2", 2);
    	
    	ConfigProperty cp = Fixtures.createConfigProperty("abcd");
    	cp.setId(1);
    	
    	
    	ReportDefinition rd1 =  Fixtures.createReportDefinition("RD1", "test", 1, TimeScaleUnit.DAY);
    	rd1.setId(1);
    	rd1.setOrganization(org1);
    	rd1.setGroup(cp);
    	
    	ReportDefinition rdx =  Fixtures.createReportDefinition("RDX", "test", 1, TimeScaleUnit.DAY);
    	rdx.setOrganization(org2);
    	rdx.setGroup(cp);
    	rdx.setId(2);
    	
    	Report r1 = Fixtures.createReport("test");
		r1.setReportDefinition(rd1);
		r1.getLastVersion().setSubmittedOn(new Date());
		r1.setStatus(ReportStatus.COMPLETED);
		r1.setSubmittedOn(new Date());
		
		Report rx = Fixtures.createReport("testz");
		rx.setReportDefinition(rdx);
		rx.getLastVersion().setSubmittedOn(new Date());
		rx.setStatus(ReportStatus.INPROCESS);
		rx.setDueOn(new Date());
    	
    	
    	ExpeditedAdverseEventReport aeReport1 = registerMockFor(ExpeditedAdverseEventReport.class);
    	expect(aeReport1.getId()).andReturn(new Integer(1)).anyTimes();
		expect(aeReport1.getAdverseEvents()).andReturn(Arrays.asList(ae1, ae2)).anyTimes();
		expect(aeReport1.getActiveAdverseEvents()).andReturn(Arrays.asList(ae1,ae2));
		expect(aeReport1.getModifiedAdverseEvents()).andReturn(Arrays.asList(ae1));
		expect(aeReport1.isActive()).andReturn(true);
		expect(aeReport1.getManuallySelectedReports()).andReturn(new ArrayList<Report>());
		expect(aeReport1.getActiveReports()).andReturn(Arrays.asList(rx));
		
		Study study = Fixtures.createStudy("test");
		
		AdverseEventReportingPeriod reportingPeriod = registerMockFor(AdverseEventReportingPeriod.class);
		expect(reportingPeriod.getAeReports()).andReturn(Arrays.asList(aeReport1)).anyTimes();
		expect(reportingPeriod.getNonExpeditedAdverseEvents()).andReturn(new ArrayList<AdverseEvent>());
		expect(reportingPeriod.getStudy()).andReturn(study).anyTimes();
		
		//rules engine should not return anything.
		Map<AdverseEvent, List<String>> map0 = new HashMap<AdverseEvent, List<String>>();
		Map<AdverseEvent, List<String>> map1 = new HashMap<AdverseEvent, List<String>>();
		map1.put(ae1, new ArrayList<String>()); //noting returned by rules for ae1
		map1.put(ae2, Arrays.asList("RDX"));// nothing for ae2
		
		expect(reportDefinitionDao.getByName("RDX")).andReturn(rdx).anyTimes();
		
		expect(adverseEventEvaluationService.evaluateSAEReportSchedule(null, new ArrayList<AdverseEvent>(), study)).andReturn(map0);
		
		expect(adverseEventEvaluationService.evaluateSAEReportSchedule(aeReport1, Arrays.asList(ae1, ae2), study)).andReturn(map1);
		
		expect(aeReport1.listReportsHavingStatus(ReportStatus.COMPLETED)).andReturn(Arrays.asList(r1)).times(2);
		expect(aeReport1.findCompletedAmendableReports()).andReturn(Arrays.asList(r1));
		expect(aeReport1.findReportsToAmmend(rd1)).andReturn(Arrays.asList(r1));
		expect(aeReport1.findReportsToEdit(rd1)).andReturn(new ArrayList<Report>()).times(2);
		expect(aeReport1.findReportsToWitdraw(rd1)).andReturn(new ArrayList<Report>());
		
		expect(aeReport1.findReportsToAmmend(rdx)).andReturn(new ArrayList<Report>());
		expect(aeReport1.findReportsToEdit(rdx)).andReturn(Arrays.asList(rx)).times(2);
		expect(aeReport1.findReportsToWitdraw(rdx)).andReturn(new ArrayList<Report>());
    	
    	replayMocks();
    	EvaluationResultDTO result = service.evaluateSAERules(reportingPeriod);
    	assertTrue(result.isAlertRecommended());
    	assertTrue(result.getAeReportAlertMap().get(new Integer(1))); //aeReport1
    	assertFalse(result.getAeReportAlertMap().get(new Integer(0)));
    	
    	// - aeReportId - 0
    	assertTrue(result.getAmendmentMap().get(new Integer(0)).isEmpty());
    	assertTrue(result.getEditMap().get(new Integer(0)).isEmpty());
    	assertTrue(result.getWithdrawalMap().get(new Integer(0)).isEmpty());
    	assertTrue(result.getCreateMap().get(new Integer(0)).isEmpty());
    	
    	//aeReportId 1
    	assertTrue(result.getWithdrawalMap().get(aeReport1.getId()).isEmpty());
    	assertFalse(result.getEditMap().get(aeReport1.getId()).isEmpty());
    	assertFalse(result.getAmendmentMap().get(aeReport1.getId()).isEmpty());
    	assertFalse(result.getCreateMap().get(aeReport1.getId()).isEmpty());
    	
    	assertEquals(1, result.getEditMap().get(aeReport1.getId()).size());
    	assertEquals(1, result.getAmendmentMap().get(aeReport1.getId()).size());
    	assertEquals(1, result.getCreateMap().get(aeReport1.getId()).size());
    	
    	assertEquals(rdx , new ArrayList<ReportDefinitionWrapper>(result.getEditMap().get(aeReport1.getId())).get(0).getDef());
    	assertEquals(rd1 , new ArrayList<ReportDefinitionWrapper>(result.getAmendmentMap().get(aeReport1.getId())).get(0).getDef());
    	assertEquals(rd1 , new ArrayList<ReportDefinitionWrapper>(result.getAmendmentMap().get(aeReport1.getId())).get(0).getSubstitute());
    	assertEquals(rd1 , new ArrayList<ReportDefinitionWrapper>(result.getCreateMap().get(aeReport1.getId())).get(0).getDef());
    	
    	verifyMocks();
    }
	
    /**
	 * There is an in progress report, and the rules engine suggests the same, caAERS should recommend editing of RD1.
	 * 
	 * Data Collection
	 *   RD1  - In process
	 *   AE1
	 *   No suggestions
	 * Newly added 
	 *   AE2
	 *   
	 * -------
	 *    Alert required for DC1
	 *    No alert required for (aeReportId = 0)
	 *    
	 *    DC1 - Edit RD1 with RD1, createMap empty, editMap contains rd1
	 *    0 - create map empty
     * @throws Exception 
	 */
    public void testEvaluateSAERules_EditOne() throws Exception{
    	AdverseEvent ae1 = Fixtures.createAdverseEvent(1, Grade.MILD);
		ae1.setGradedDate(new Date());
		AdverseEvent ae2 = Fixtures.createAdverseEvent(2,Grade.LIFE_THREATENING);
		ae2.setGradedDate(new Date());
		
		ArrayList<AdverseEvent> aeList = new ArrayList<AdverseEvent>();
		aeList.add(ae2);
		aeList.add(ae1);
		

		ArrayList<AdverseEvent> aeList1 = new ArrayList<AdverseEvent>();
		aeList1.add(ae1);
		
		ArrayList<AdverseEvent> aeList2 = new ArrayList<AdverseEvent>();
		aeList2.add(ae2);
		
		ReportDefinition rd1 = Fixtures.createReportDefinition("RD1", "001", 1, TimeScaleUnit.DAY);
		rd1.setGroup(Fixtures.createConfigProperty("expedited"));
		rd1.getOrganization().setId(1);
		rd1.setId(1);
		
		Report r1 = Fixtures.createReport("test");
		r1.setReportDefinition(rd1);
		r1.getLastVersion().setDueOn(new Date());
		r1.setStatus(ReportStatus.INPROCESS);
		r1.setDueOn(new Date());
		r1.getLastVersion().setReportStatus(ReportStatus.INPROCESS);
		
		ExpeditedAdverseEventReport aeReport1 = registerMockFor(ExpeditedAdverseEventReport.class);
		expect(aeReport1.getId()).andReturn(new Integer(1)).anyTimes();
		expect(aeReport1.getActiveAdverseEvents()).andReturn(aeList1);
		expect(aeReport1.getModifiedAdverseEvents()).andReturn(EMPTY_AE_LIST);
		expect(aeReport1.isActive()).andReturn(true);
		expect(aeReport1.getActiveReports()).andReturn(Arrays.asList(r1));
		expect(aeReport1.getManuallySelectedReports()).andReturn(EMPTY_REPORT_LIST);
		expect(aeReport1.listReportsHavingStatus(ReportStatus.COMPLETED)).andReturn(EMPTY_REPORT_LIST);
		expect(aeReport1.findCompletedAmendableReports()).andReturn(EMPTY_REPORT_LIST);
		expect(aeReport1.getAdverseEvents()).andReturn(aeList1).anyTimes();
		
		Study study = Fixtures.createStudy("test");
		
		AdverseEventReportingPeriod reportingPeriod = registerMockFor(AdverseEventReportingPeriod.class);
		expect(reportingPeriod.getAeReports()).andReturn(Arrays.asList(aeReport1));
		expect(reportingPeriod.getNonExpeditedAdverseEvents()).andReturn(aeList2);
		expect(reportingPeriod.getStudy()).andReturn(study).anyTimes();
		
		
		Map<AdverseEvent, List<String>> map0 = new HashMap<AdverseEvent, List<String>>();
		map0.put(ae2, new ArrayList<String>());
		
		Map<AdverseEvent, List<String>> map1 = new HashMap<AdverseEvent, List<String>>();
		map1.put(ae1, Arrays.asList("RD1")); 
		map1.put(ae2, new ArrayList<String>());
		
		expect(adverseEventEvaluationService.evaluateSAEReportSchedule(null, aeList2, study)).andReturn(map0);
		
		expect(adverseEventEvaluationService.evaluateSAEReportSchedule(aeReport1, aeList, study)).andReturn(map1);
		
		expect(reportDefinitionDao.getByName("RD1")).andReturn(rd1).anyTimes();
		expect(aeReport1.findReportsToAmmend(rd1)).andReturn(new ArrayList<Report>());
		expect(aeReport1.findReportsToEdit(rd1)).andReturn(Arrays.asList(r1)).times(2);
		expect(aeReport1.findReportsToWitdraw(rd1)).andReturn(new ArrayList<Report>());
		replayMocks();

		EvaluationResultDTO result = service.evaluateSAERules(reportingPeriod);
		assertFalse(result.getAeReportAlertMap().get(new Integer(0)));
		assertFalse(result.getAeReportAlertMap().get(new Integer(1)));
		
		Set<ReportDefinitionWrapper> wrappers = result.getAmendmentMap().get(new Integer(0));
		assertTrue(wrappers.isEmpty());
		
	    wrappers = result.getAmendmentMap().get(new Integer(1));
	    assertTrue(wrappers.isEmpty());
	    
	    wrappers = result.getWithdrawalMap().get(new Integer(0));
	    assertTrue(wrappers.isEmpty());
	    wrappers = result.getWithdrawalMap().get(new Integer(1));
	    assertTrue(wrappers.isEmpty());
	    
	    wrappers = result.getEditMap().get(new Integer(0));
	    assertTrue(wrappers.isEmpty());

	    wrappers = result.getEditMap().get(new Integer(1));
	    assertFalse(wrappers.isEmpty());
	    ArrayList<ReportDefinitionWrapper> wrapperList = new ArrayList<ReportDefinitionWrapper>(wrappers);
	    assertEquals(1, wrapperList.size());
	    assertEquals(rd1, wrapperList.get(0).getDef());
	    assertEquals(rd1, wrapperList.get(0).getSubstitute());
		verifyMocks();

    }
    

    /**
	 * There is an in progress report, and the rules engine did not suggest any, caAERS should recommend withdraw of RD1.
	 * 
	 * Data Collection
	 *   RD1  - In process
	 *   AE1
	 *   No suggestions
	 * Newly added 
	 *   AE2
	 *   
	 * -------
	 *    No Alert required for DC1
	 *    No alert required for (aeReportId = 0)
	 *    
	 *    DC1 - maps empty
	 *    0 -  maps empty
     * @throws Exception 
	 */
    public void testEvaluateSAERules_WithdrawOne() throws Exception{
    	AdverseEvent ae1 = Fixtures.createAdverseEvent(1, Grade.MILD);
		ae1.setGradedDate(new Date());
		AdverseEvent ae2 = Fixtures.createAdverseEvent(2,Grade.LIFE_THREATENING);
		ae2.setGradedDate(new Date());
		
		ArrayList<AdverseEvent> aeList = new ArrayList<AdverseEvent>();
		aeList.add(ae2);
		aeList.add(ae1);
		

		ArrayList<AdverseEvent> aeList1 = new ArrayList<AdverseEvent>();
		aeList1.add(ae1);
		
		ArrayList<AdverseEvent> aeList2 = new ArrayList<AdverseEvent>();
		aeList2.add(ae2);
		
		ReportDefinition rd1 = Fixtures.createReportDefinition("RD1", "001", 1, TimeScaleUnit.DAY);
		rd1.setGroup(Fixtures.createConfigProperty("expedited"));
		rd1.getOrganization().setId(1);
		rd1.setId(1);
		
		Report r1 = Fixtures.createReport("test");
		r1.setReportDefinition(rd1);
		r1.getLastVersion().setDueOn(new Date());
		r1.setStatus(ReportStatus.INPROCESS);
		r1.setDueOn(new Date());
		r1.getLastVersion().setReportStatus(ReportStatus.INPROCESS);
		
		ExpeditedAdverseEventReport aeReport1 = registerMockFor(ExpeditedAdverseEventReport.class);
		expect(aeReport1.getId()).andReturn(new Integer(1)).anyTimes();
		expect(aeReport1.getActiveAdverseEvents()).andReturn(aeList1);
		expect(aeReport1.getModifiedAdverseEvents()).andReturn(EMPTY_AE_LIST).anyTimes();
		expect(aeReport1.isActive()).andReturn(true);
		expect(aeReport1.getActiveReports()).andReturn(Arrays.asList(r1));
		expect(aeReport1.getManuallySelectedReports()).andReturn(EMPTY_REPORT_LIST);
		expect(aeReport1.getAdverseEvents()).andReturn(aeList1).anyTimes();
		expect(aeReport1.listReportsHavingStatus(ReportStatus.COMPLETED)).andReturn(EMPTY_REPORT_LIST);
		expect(aeReport1.findCompletedAmendableReports()).andReturn(EMPTY_REPORT_LIST);
		
		
		Study study = Fixtures.createStudy("test");
		
		AdverseEventReportingPeriod reportingPeriod = registerMockFor(AdverseEventReportingPeriod.class);
		expect(reportingPeriod.getAeReports()).andReturn(Arrays.asList(aeReport1));
		expect(reportingPeriod.getNonExpeditedAdverseEvents()).andReturn(aeList2);
		expect(reportingPeriod.getStudy()).andReturn(study).anyTimes();
		
		
		Map<AdverseEvent, List<String>> map0 = new HashMap<AdverseEvent, List<String>>();
		map0.put(ae2, new ArrayList<String>());
		
		Map<AdverseEvent, List<String>> map1 = new HashMap<AdverseEvent, List<String>>();
		map1.put(ae1, new ArrayList<String>()); 
		map1.put(ae2, new ArrayList<String>());
		
		expect(adverseEventEvaluationService.evaluateSAEReportSchedule(null, aeList2, study)).andReturn(map0);
		
		expect(adverseEventEvaluationService.evaluateSAEReportSchedule(aeReport1, aeList, study)).andReturn(map1);
		
		replayMocks();

		EvaluationResultDTO result = service.evaluateSAERules(reportingPeriod);
		//alert
		assertFalse(result.getAeReportAlertMap().get(new Integer(0)));
		assertFalse(result.getAeReportAlertMap().get(new Integer(1)));
		
		assertTrue(result.getCreateMap().get(ZERO).isEmpty());
		assertTrue(result.getCreateMap().get(ONE).isEmpty());
		
		assertTrue(result.getEditMap().get(ZERO).isEmpty());
		assertTrue(result.getEditMap().get(ONE).isEmpty());
		
		assertTrue(result.getWithdrawalMap().get(ZERO).isEmpty());
		assertFalse(result.getWithdrawalMap().get(ONE).isEmpty());
		
		assertTrue(result.getAmendmentMap().get(ZERO).isEmpty());
		assertTrue(result.getAmendmentMap().get(ONE).isEmpty());
		
		verifyMocks();

    }
    

    /**
	 * There is an in progress report, and the rules engine do not suggest any,
	 *  caAERS should recommend editing of RD1, as it is manually selected.
	 * 
	 * Data Collection
	 *   RD1  - In process (manually selected)
	 *   AE1
	 *   No suggestions
	 * Newly added 
	 *   AE2
	 *   
	 * -------
	 *    Alert required for DC1
	 *    No alert required for (aeReportId = 0)
	 *    
	 *    DC1 - Edit RD1 with RD1, createMap empty, editMap contains rd1
	 *    0 - create map empty
     * @throws Exception 
	 */
    public void testEvaluateSAERules_ManyallySelectedInprogressSuggested_InsteadOfWithdraw() throws Exception{
    	AdverseEvent ae1 = Fixtures.createAdverseEvent(1, Grade.MILD);
		ae1.setGradedDate(new Date());
		AdverseEvent ae2 = Fixtures.createAdverseEvent(2,Grade.LIFE_THREATENING);
		ae2.setGradedDate(new Date());
		
		ArrayList<AdverseEvent> aeList = new ArrayList<AdverseEvent>();
		aeList.add(ae2);
		aeList.add(ae1);
		

		ArrayList<AdverseEvent> aeList1 = new ArrayList<AdverseEvent>();
		aeList1.add(ae1);
		
		ArrayList<AdverseEvent> aeList2 = new ArrayList<AdverseEvent>();
		aeList2.add(ae2);
		
		ReportDefinition rd1 = Fixtures.createReportDefinition("RD1", "001", 1, TimeScaleUnit.DAY);
		rd1.setGroup(Fixtures.createConfigProperty("expedited"));
		rd1.getOrganization().setId(1);
		rd1.setId(1);
		
		Report r1 = Fixtures.createReport("test");
		r1.setReportDefinition(rd1);
		r1.getLastVersion().setDueOn(new Date());
		r1.setStatus(ReportStatus.INPROCESS);
		r1.setDueOn(new Date());
		r1.getLastVersion().setReportStatus(ReportStatus.INPROCESS);
		
		ExpeditedAdverseEventReport aeReport1 = registerMockFor(ExpeditedAdverseEventReport.class);
		expect(aeReport1.getId()).andReturn(new Integer(1)).anyTimes();
		expect(aeReport1.getActiveAdverseEvents()).andReturn(aeList1);
		expect(aeReport1.getModifiedAdverseEvents()).andReturn(EMPTY_AE_LIST);
		expect(aeReport1.isActive()).andReturn(true);
		expect(aeReport1.getActiveReports()).andReturn(Arrays.asList(r1));
		expect(aeReport1.getManuallySelectedReports()).andReturn(Arrays.asList(r1));
		expect(aeReport1.getAdverseEvents()).andReturn(aeList1).anyTimes();
		expect(aeReport1.listReportsHavingStatus(ReportStatus.COMPLETED)).andReturn(EMPTY_REPORT_LIST);
		expect(aeReport1.findCompletedAmendableReports()).andReturn(EMPTY_REPORT_LIST);
		
		
		Study study = Fixtures.createStudy("test");
		
		AdverseEventReportingPeriod reportingPeriod = registerMockFor(AdverseEventReportingPeriod.class);
		expect(reportingPeriod.getAeReports()).andReturn(Arrays.asList(aeReport1));
		expect(reportingPeriod.getNonExpeditedAdverseEvents()).andReturn(aeList2);
		expect(reportingPeriod.getStudy()).andReturn(study).anyTimes();
		
		//nothing suggest by rules engine.
		Map<AdverseEvent, List<String>> map0 = new HashMap<AdverseEvent, List<String>>();
		map0.put(ae2, new ArrayList<String>());
		
		Map<AdverseEvent, List<String>> map1 = new HashMap<AdverseEvent, List<String>>();
		map1.put(ae1, new ArrayList<String>()); 
		map1.put(ae2, new ArrayList<String>());
		
		expect(adverseEventEvaluationService.evaluateSAEReportSchedule(null, aeList2, study)).andReturn(map0);
		
		expect(adverseEventEvaluationService.evaluateSAEReportSchedule(aeReport1, aeList, study)).andReturn(map1);
		
		expect(aeReport1.findReportsToAmmend(rd1)).andReturn(new ArrayList<Report>());
		expect(aeReport1.findReportsToEdit(rd1)).andReturn(Arrays.asList(r1)).times(2);
		expect(aeReport1.findReportsToWitdraw(rd1)).andReturn(new ArrayList<Report>());
		replayMocks();

		EvaluationResultDTO result = service.evaluateSAERules(reportingPeriod);
		assertFalse(result.getAeReportAlertMap().get(new Integer(0)));
		assertFalse(result.getAeReportAlertMap().get(new Integer(1)));
		
		Set<ReportDefinitionWrapper> wrappers = result.getAmendmentMap().get(new Integer(0));
		assertTrue(wrappers.isEmpty());
		
	    wrappers = result.getAmendmentMap().get(new Integer(1));
	    assertTrue(wrappers.isEmpty());
	    
	    wrappers = result.getWithdrawalMap().get(new Integer(0));
	    assertTrue(wrappers.isEmpty());
	    wrappers = result.getWithdrawalMap().get(new Integer(1));
	    assertTrue(wrappers.isEmpty());
	    
	    wrappers = result.getEditMap().get(new Integer(0));
	    assertTrue(wrappers.isEmpty());

	    wrappers = result.getEditMap().get(new Integer(1));
	    assertFalse(wrappers.isEmpty());
	    ArrayList<ReportDefinitionWrapper> wrapperList = new ArrayList<ReportDefinitionWrapper>(wrappers);
	    assertEquals(1, wrapperList.size());
	    assertEquals(rd1, wrapperList.get(0).getDef());
	    assertEquals(rd1, wrapperList.get(0).getSubstitute());
		verifyMocks();

    }
	
    public void testMandatorySections() throws Exception{
        ExpeditedAdverseEventReport aeReport1 = null;
        ReportDefinition rd1 = Fixtures.createReportDefinition("ctep-rd-1",null, null);
        rd1.setId(1);
	    rd1.setTimeScaleUnitType(TimeScaleUnit.MINUTE);
		rd1.setDuration(1);
		ReportDefinition rd2 = Fixtures.createReportDefinition("ctep-rd-2",null, null);
		rd2.setTimeScaleUnitType(TimeScaleUnit.DAY);
		rd2.setDuration(2);
        rd2.setId(2);

        ArrayList<ExpeditedReportSection> list1 = new ArrayList<ExpeditedReportSection>();
        list1.add(ExpeditedReportSection.ADVERSE_EVENT_SECTION);
        expect(adverseEventEvaluationService.mandatorySections(aeReport1, rd1)).andReturn(list1);

        ArrayList<ExpeditedReportSection> list2 = new ArrayList<ExpeditedReportSection>();
        list2.add(ExpeditedReportSection.ADVERSE_EVENT_SECTION);
        list2.add(ExpeditedReportSection.ADDITIONAL_INFO_SECTION);
        expect(adverseEventEvaluationService.mandatorySections(aeReport1, rd2)).andReturn(list2);

        replayMocks();

        Map<Integer, Collection<ExpeditedReportSection>> map = service.mandatorySections(aeReport1, rd1, rd2);
        assertTrue(map.containsKey(rd1.getId()));
        assertTrue(map.containsKey(rd2.getId()));
        assertTrue(map.get(rd2.getId()).contains(ExpeditedReportSection.ADVERSE_EVENT_SECTION));
        verifyMocks();

    }

	
	public  ReportDefinitionWrapper getWrapper(Collection<ReportDefinitionWrapper> c , ReportDefinition def){
		for(ReportDefinitionWrapper wrapper : c){
			if(wrapper.getDef().equals(def)) return wrapper;
		}
		return null;
	}


    public void testEvaluateMandatoryness(){

        ReportDefinition rd1 = Fixtures.createReportDefinition("ctep-rd-1",null, null);
        rd1.setId(1);
        rd1.setTimeScaleUnitType(TimeScaleUnit.MINUTE);
        rd1.setDuration(1);

        ReportMandatoryFieldDefinition rd1_m1 = Fixtures.createMandatoryField("a1", RequirednessIndicator.MANDATORY);
        ReportMandatoryFieldDefinition rd1_m2 = Fixtures.createMandatoryField("a1", RequirednessIndicator.RULE);
        rd1_m2.setRuleBindURL("xyz");
        rd1_m2.setRuleName("abc");
        rd1.addReportMandatoryFieldDefinition(rd1_m1);
        rd1.addReportMandatoryFieldDefinition(rd1_m2);

        ReportDefinition rd2 = Fixtures.createReportDefinition("ctep-rd-2",null, null);
        rd2.setTimeScaleUnitType(TimeScaleUnit.DAY);
        rd2.setDuration(2);
        rd2.setId(2);
        ReportMandatoryFieldDefinition rd2_m1 = Fixtures.createMandatoryField("a1", RequirednessIndicator.NA);
        ReportMandatoryFieldDefinition rd2_m2 = Fixtures.createMandatoryField("a1", RequirednessIndicator.OPTIONAL);

        rd2.addReportMandatoryFieldDefinition(rd2_m1);
        rd2.addReportMandatoryFieldDefinition(rd2_m2);

        Report r1 = Fixtures.createReport("test1");
        r1.setReportDefinition(rd1);

        Report r2 = Fixtures.createReport("test2");
        r2.setReportDefinition(rd2);


        ExpeditedAdverseEventReport aeReport1 = registerMockFor(ExpeditedAdverseEventReport.class);
		expect(aeReport1.getId()).andReturn(new Integer(1)).anyTimes();
        expect(aeReport1.getActiveReports()).andReturn(Arrays.asList(new Report[]{r1, r2})).anyTimes();
        expect(adverseEventEvaluationService.evaluateFieldLevelRules(aeReport1, r1, rd1_m2)).andReturn("NA");

        replayMocks();

        service.evaluateMandatoryness(aeReport1, r1);
        service.evaluateMandatoryness(aeReport1, r2);
        assertNotNull(r1.getMandatoryFields());
        assertEquals(2, r1.getMandatoryFields().size());
        assertEquals(Mandatory.MANDATORY, r1.getMandatoryFields().get(0).getMandatory());
        assertEquals(Mandatory.NA, r1.getMandatoryFields().get(1).getMandatory());

        assertNotNull(r2.getMandatoryFields());
        assertEquals(2, r2.getMandatoryFields().size());
        assertEquals(Mandatory.NA, r2.getMandatoryFields().get(0).getMandatory());
        assertEquals(Mandatory.OPTIONAL, r2.getMandatoryFields().get(1).getMandatory());


        verifyMocks();

    }

    //tests that rules engine is called only once for a specific rule. 
    public void testEvaluateMandatoryness_RulesResultIsCached(){
        ReportDefinition rd1 = Fixtures.createReportDefinition("ctep-rd-1",null, null);
        rd1.setId(1);
        rd1.setTimeScaleUnitType(TimeScaleUnit.MINUTE);
        rd1.setDuration(1);

        ReportMandatoryFieldDefinition rd1_m1 = Fixtures.createMandatoryField("a1", RequirednessIndicator.RULE);
        rd1_m1.setRuleBindURL("xyz");
        rd1_m1.setRuleName("abc");

        ReportMandatoryFieldDefinition rd1_m2 = Fixtures.createMandatoryField("a1", RequirednessIndicator.RULE);
        rd1_m2.setRuleBindURL("xyz");
        rd1_m2.setRuleName("abc");

        rd1.addReportMandatoryFieldDefinition(rd1_m1);
        rd1.addReportMandatoryFieldDefinition(rd1_m2);

        Report r1 = Fixtures.createReport("test1");
        r1.setReportDefinition(rd1);

        ExpeditedAdverseEventReport aeReport1 = registerMockFor(ExpeditedAdverseEventReport.class);
		expect(aeReport1.getId()).andReturn(new Integer(1)).anyTimes();
        expect(aeReport1.getActiveReports()).andReturn(Arrays.asList(new Report[]{r1})).anyTimes();
        expect(adverseEventEvaluationService.evaluateFieldLevelRules(aeReport1, r1, rd1_m1)).andReturn("NA");
        
        replayMocks();
        service.evaluateMandatoryness(aeReport1, r1);
        assertNotNull(r1.getMandatoryFields());
        assertEquals(2, r1.getMandatoryFields().size());
        assertEquals(Mandatory.NA, r1.getMandatoryFields().get(0).getMandatory());
        assertEquals(Mandatory.NA, r1.getMandatoryFields().get(0).getMandatory());
        verifyMocks();
    }

    public void testTranslateRulesMandatorynessResult(){
        String rules1 ="MANDATORY||MANDATORY||OPTIONAL||NA||NA";
        String rules2 ="MANDATORY";
        String rules3 ="NA||OPTIONAL";

        Mandatory m1 = service.translateRulesMandatorynessResult(rules1);
        assertEquals(Mandatory.OPTIONAL, m1);
        Mandatory m2 = service.translateRulesMandatorynessResult(rules2);
        assertEquals(Mandatory.MANDATORY, m2);
        Mandatory m3 = service.translateRulesMandatorynessResult(rules3);
        assertEquals(Mandatory.OPTIONAL, m3);
        assertEquals(Mandatory.NA, service.translateRulesMandatorynessResult("NA||NA"));
        assertEquals(Mandatory.OPTIONAL, service.translateRulesMandatorynessResult(""));
        assertEquals(Mandatory.OPTIONAL, service.translateRulesMandatorynessResult(null));
    }
}
