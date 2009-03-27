package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.CtcTerm;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.Grade;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.domain.report.TimeScaleUnit;
import gov.nih.nci.cabig.caaers.service.EvaluationService;
import gov.nih.nci.cabig.caaers.web.WebTestCase;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.easymock.classextension.EasyMock;
/**
 * @author Biju Joseph
 *
 */
public class AdverseEventConfirmTabTest extends WebTestCase {
	AdverseEventConfirmTab tab;
	CaptureAdverseEventInputCommand command;
	EvaluationService evaluationService;
	
	protected void setUp() throws Exception {
		super.setUp();
		
		
		tab = new AdverseEventConfirmTab("long","short", "view");
		AdverseEventReportingPeriod reportingPeriod = Fixtures.createReportingPeriod();
		List<AdverseEvent> aeList = createAdverseEventList();
		reportingPeriod.setAdverseEvents(aeList);
		command = new CaptureAdverseEventInputCommand();
		evaluationService = registerMockFor(EvaluationService.class);
		
		command.setAdverseEventReportingPeriod(reportingPeriod);
		command.setEvaluationService(evaluationService);
		
	}
	/**
	 * This method tests the {@link AdverseEventConfirmTab#createCustomFieldGroup(gov.nih.nci.cabig.caaers.domain.AdverseEvent, int, boolean, boolean)}
	 */
	public void testCreateCustomFieldGroup() {
		
		CtcTerm ctcTerm = Fixtures.createCtcTerm("abcd", "123");
		AdverseEvent ae = new AdverseEvent();
		ae.setId(5);
		ae.setGrade(Grade.NORMAL);
		Fixtures.createAdverseEventCtcTerm(ae, ctcTerm);
		List<InputField> fields = tab.createCustomFieldGroup(ae, 0, true);
		assertNotNull(fields);
		assertEquals(6, fields.size());
		assertFieldProperyNames(fields,"selectedAesMap[5]",
				"adverseEventReportingPeriod.allReportedAndReportableAdverseEvents[0].adverseEventTerm.universalTerm",
				"adverseEventReportingPeriod.allReportedAndReportableAdverseEvents[0].detailsForOther",
				"adverseEventReportingPeriod.allReportedAndReportableAdverseEvents[0].displayGrade",
				"adverseEventReportingPeriod.allReportedAndReportableAdverseEvents[0].startDate",
				"primaryAdverseEventId");
	}
	
	/**
	 * This method tests the {@link AdverseEventConfirmTab#createCustomFieldGroup(gov.nih.nci.cabig.caaers.domain.AdverseEvent, int, boolean, boolean)}
	 */
	public void testCreateCustomFieldGroupNonBaselineNotGraded() {
		
		CtcTerm ctcTerm = Fixtures.createCtcTerm("abcd", "123");
		AdverseEvent ae = new AdverseEvent();
		ae.setId(5);
		ae.setGrade(Grade.NOT_EVALUATED);
		Fixtures.createAdverseEventCtcTerm(ae, ctcTerm);
		List<InputField> fields = tab.createCustomFieldGroup(ae, 0, false);
		assertEquals(InputField.Category.IMAGE, fields.get(0).getCategory());
		assertNotNull(fields);
		assertEquals(6, fields.size());
		assertFieldProperyNames(fields,"selectedAesMap[5]",
				"adverseEventReportingPeriod.allReportedAndReportableAdverseEvents[0].adverseEventTerm.universalTerm",
				"adverseEventReportingPeriod.allReportedAndReportableAdverseEvents[0].detailsForOther",
				"adverseEventReportingPeriod.allReportedAndReportableAdverseEvents[0].displayGrade",
				"adverseEventReportingPeriod.allReportedAndReportableAdverseEvents[0].startDate",
				"primaryAdverseEventId");
	}
	
	/**
	 * This method tests the {@link AdverseEventConfirmTab#createCustomFieldGroup(gov.nih.nci.cabig.caaers.domain.AdverseEvent, int, boolean, boolean)}
	 */
	public void testCreateCustomFieldGroup_NotGradedAE() {
		
		CtcTerm ctcTerm = Fixtures.createCtcTerm("abcd", "123");
		AdverseEvent ae = new AdverseEvent();
		ae.setId(5);
		ae.setGrade(Grade.NOT_EVALUATED);
		Fixtures.createAdverseEventCtcTerm(ae, ctcTerm);
		
		List<InputField> fields = tab.createCustomFieldGroup(ae, 0, true);
		assertNotNull(fields);
		assertEquals(6, fields.size());
		assertFieldProperyNames(fields, "selectedAesMap[5]","adverseEventReportingPeriod.allReportedAndReportableAdverseEvents[0].adverseEventTerm.universalTerm",
				"adverseEventReportingPeriod.allReportedAndReportableAdverseEvents[0].detailsForOther",
				"adverseEventReportingPeriod.allReportedAndReportableAdverseEvents[0].displayGrade",
				"adverseEventReportingPeriod.allReportedAndReportableAdverseEvents[0].startDate",
				"primaryAdverseEventId");
	}
	
	/**
	 * This method test the field group creation. {@link AdverseEventConfirmTab#createFieldGroups(CaptureAdverseEventInputCommand)}
	 */
	public void testCreateFieldGroups(){
		
		assertEquals(3, command.getAdverseEventReportingPeriod().getReportableAdverseEvents().size());
		Map<String, InputFieldGroup> fieldGrpMap = tab.createFieldGroups(command);
		assertTrue(fieldGrpMap.containsKey("main0"));
		assertTrue(fieldGrpMap.containsKey("main1"));
		assertTrue(fieldGrpMap.containsKey("main2"));
		
		InputFieldGroup group = fieldGrpMap.get("main0");
		assertFieldProperyNames(group.getFields(), "selectedAesMap[1]",
				"adverseEventReportingPeriod.allReportedAndReportableAdverseEvents[0].adverseEventTerm.universalTerm",
				"adverseEventReportingPeriod.allReportedAndReportableAdverseEvents[0].detailsForOther",
				"adverseEventReportingPeriod.allReportedAndReportableAdverseEvents[0].displayGrade",
				"adverseEventReportingPeriod.allReportedAndReportableAdverseEvents[0].startDate",
				"primaryAdverseEventId");
		group = fieldGrpMap.get("main1");
		assertFieldProperyNames(group.getFields(), "selectedAesMap[2]",
				"adverseEventReportingPeriod.allReportedAndReportableAdverseEvents[1].adverseEventTerm.universalTerm",
				"adverseEventReportingPeriod.allReportedAndReportableAdverseEvents[1].detailsForOther",
				"adverseEventReportingPeriod.allReportedAndReportableAdverseEvents[1].displayGrade",
				"adverseEventReportingPeriod.allReportedAndReportableAdverseEvents[1].startDate",
				"primaryAdverseEventId");
		
		
	}
	/**
	 * This method tests the {@link AdverseEventConfirmTab#referenceData(javax.servlet.http.HttpServletRequest, CaptureAdverseEventInputCommand)}
	 */
	public void testReferenceData(){
		EasyMock.expect(evaluationService.applicableReportDefinitions(command.getAssignment())).andReturn(new ArrayList<ReportDefinition>());
		EasyMock.expect(evaluationService.findRequiredReportDefinitions(command.getAdverseEventReportingPeriod())).andReturn(new HashMap<ReportDefinition, List<AdverseEvent>>());
		replayMocks();
		Map<String, Object> refdata = tab.referenceData(request, command);
		assertNotNull(refdata.get("fieldGroups"));
		assertNotNull(refdata.get("rpdSelectedTable"));
		assertNotNull(refdata.get("rpdAllTable"));
		verifyMocks();
	}
	
	/**
	 * This method tests the {@link AdverseEventConfirmTab#referenceData(javax.servlet.http.HttpServletRequest, CaptureAdverseEventInputCommand)}
	 */
	public void testReferenceDataWhenThereAreReportDefs(){
		ReportDefinition rd1 = Fixtures.createReportDefinition("test", "test");
		rd1.setId(4);
		rd1.setTimeScaleUnitType(TimeScaleUnit.DAY);
		rd1.setDuration(4);
		List<ReportDefinition> rdList = new ArrayList<ReportDefinition>();
		rdList.add(rd1);
		EasyMock.expect(evaluationService.applicableReportDefinitions(command.getAssignment())).andReturn(rdList);
		EasyMock.expect(evaluationService.findRequiredReportDefinitions(command.getAdverseEventReportingPeriod())).andReturn(new HashMap<ReportDefinition, List<AdverseEvent>>());
		replayMocks();
		Map<String, Object> refdata = tab.referenceData(request, command);
		assertNotNull(refdata.get("fieldGroups"));
		assertNotNull(refdata.get("rpdSelectedTable"));
		assertNotNull(refdata.get("rpdAllTable"));
		Map rpdAllTable = (Map)refdata.get("rpdAllTable");
		assertNotNull(rpdAllTable.get("rpd0"));
		verifyMocks();
	}
	
	/**
	 * This method tests the {@link AdverseEventConfirmTab#referenceData(javax.servlet.http.HttpServletRequest, CaptureAdverseEventInputCommand)}
	 */
	public void testReferenceDataWhenThereAreReportDefsAndReportingIsRequired(){
		ReportDefinition rd1 = Fixtures.createReportDefinition("test", "test");
		rd1.setId(4);
		rd1.setTimeScaleUnitType(TimeScaleUnit.DAY);
		rd1.setDuration(4);
		List<ReportDefinition> rdList = new ArrayList<ReportDefinition>();
		rdList.add(rd1);
		
		Map<ReportDefinition, List<AdverseEvent>> reqMap = new HashMap<ReportDefinition, List<AdverseEvent>>();
		System.out.println(command.getAdverseEvents());
		reqMap.put(rd1, command.getAdverseEvents());
		
		EasyMock.expect(evaluationService.applicableReportDefinitions(command.getAssignment())).andReturn(rdList);
		EasyMock.expect(evaluationService.findRequiredReportDefinitions(command.getAdverseEventReportingPeriod())).andReturn(reqMap);
		replayMocks();
		Map<String, Object> refdata = tab.referenceData(request, command);
		assertNotNull(refdata.get("fieldGroups"));
		assertNotNull(refdata.get("rpdSelectedTable"));
		assertNotNull(refdata.get("rpdAllTable"));
		Map rpdAllTable = (Map)refdata.get("rpdAllTable");
		assertNotNull(rpdAllTable.get("rpd0"));
		
		Map rpdSelectedTable = (Map)refdata.get("rpdSelectedTable");
		assertNotNull(rpdAllTable.get("rpd0"));
		verifyMocks();
	}
	
	
	public List<AdverseEvent> createAdverseEventList(){
		List<AdverseEvent> aeList = new ArrayList<AdverseEvent>();
		for(int i =0; i < 3; i++){
			aeList.add(Fixtures.createAdverseEvent(i+1, Grade.MILD));
		}
		return aeList;
	}
	
	public void assertFieldProperyNames(List<InputField> fields , String... propNames){
		for(int i = 0; i < propNames.length; i++){
			assertEquals(propNames[i], fields.get(i).getPropertyName());
		}
	}
}
