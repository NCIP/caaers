package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.CtcTerm;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.Grade;
import gov.nih.nci.cabig.caaers.web.WebTestCase;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 * @author Biju Joseph
 *
 */
public class AdverseEventConfirmTabTest extends WebTestCase {
	AdverseEventConfirmTab tab;
	CaptureAdverseEventInputCommand command;
	protected void setUp() throws Exception {
		super.setUp();
		tab = new AdverseEventConfirmTab("long","short", "view");
		AdverseEventReportingPeriod reportingPeriod = Fixtures.createReportingPeriod();
		List<AdverseEvent> aeList = createAdverseEventList();
		reportingPeriod.setAdverseEvents(aeList);
		command = new CaptureAdverseEventInputCommand();
		command.setAdverseEventReportingPeriod(reportingPeriod);
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
		assertEquals(7, fields.size());
		assertFieldProperyNames(fields,"selectedAesMap[5]",
				"adverseEventReportingPeriod.evaluatedAdverseEvents[0].adverseEventTerm.universalTerm",
				"adverseEventReportingPeriod.evaluatedAdverseEvents[0].detailsForOther",
				"adverseEventReportingPeriod.evaluatedAdverseEvents[0].displayGrade",
				"adverseEventReportingPeriod.evaluatedAdverseEvents[0].displaySerious",
				"adverseEventReportingPeriod.evaluatedAdverseEvents[0].startDate",
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
		assertEquals(7, fields.size());
		assertFieldProperyNames(fields,"selectedAesMap[5]",
				"adverseEventReportingPeriod.evaluatedAdverseEvents[0].adverseEventTerm.universalTerm",
				"adverseEventReportingPeriod.evaluatedAdverseEvents[0].detailsForOther",
				"adverseEventReportingPeriod.evaluatedAdverseEvents[0].displayGrade",
				"adverseEventReportingPeriod.evaluatedAdverseEvents[0].displaySerious",
				"adverseEventReportingPeriod.evaluatedAdverseEvents[0].startDate",
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
		assertFieldProperyNames(fields, "adverseEventReportingPeriod.evaluatedAdverseEvents[0].adverseEventTerm.universalTerm",
				"adverseEventReportingPeriod.evaluatedAdverseEvents[0].detailsForOther",
				"adverseEventReportingPeriod.evaluatedAdverseEvents[0].displayGrade",
				"adverseEventReportingPeriod.evaluatedAdverseEvents[0].displaySerious",
				"adverseEventReportingPeriod.evaluatedAdverseEvents[0].startDate",
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
				"adverseEventReportingPeriod.evaluatedAdverseEvents[0].adverseEventTerm.universalTerm",
				"adverseEventReportingPeriod.evaluatedAdverseEvents[0].detailsForOther",
				"adverseEventReportingPeriod.evaluatedAdverseEvents[0].displayGrade",
				"adverseEventReportingPeriod.evaluatedAdverseEvents[0].displaySerious",
				"adverseEventReportingPeriod.evaluatedAdverseEvents[0].startDate",
				"primaryAdverseEventId");
		group = fieldGrpMap.get("main1");
		assertFieldProperyNames(group.getFields(), "selectedAesMap[2]",
				"adverseEventReportingPeriod.evaluatedAdverseEvents[1].adverseEventTerm.universalTerm",
				"adverseEventReportingPeriod.evaluatedAdverseEvents[1].detailsForOther",
				"adverseEventReportingPeriod.evaluatedAdverseEvents[1].displayGrade",
				"adverseEventReportingPeriod.evaluatedAdverseEvents[1].displaySerious",
				"adverseEventReportingPeriod.evaluatedAdverseEvents[1].startDate",
				"primaryAdverseEventId");
		
		
	}
	
	public List<AdverseEvent> createAdverseEventList(){
		List<AdverseEvent> aeList = new ArrayList<AdverseEvent>();
		for(int i =0; i < 3; i++){
			aeList.add(Fixtures.createAdverseEvent(i+1, Grade.NORMAL));
		}
		return aeList;
	}
	
	public void assertFieldProperyNames(List<InputField> fields , String... propNames){
		for(int i = 0; i < propNames.length; i++){
			assertEquals(propNames[i], fields.get(i).getPropertyName());
		}
	}
}
