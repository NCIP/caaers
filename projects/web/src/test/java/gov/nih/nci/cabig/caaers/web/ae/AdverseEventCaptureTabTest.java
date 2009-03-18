package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.AbstractNoSecurityTestCase;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.Grade;
import gov.nih.nci.cabig.caaers.domain.Outcome;
import gov.nih.nci.cabig.caaers.domain.OutcomeType;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;
import static gov.nih.nci.cabig.caaers.domain.OutcomeType.*;
/**
 * 
 * @author Biju Joseph
 *
 */
public class AdverseEventCaptureTabTest extends AbstractNoSecurityTestCase {
	
	AdverseEventCaptureTab tab;
	CaptureAdverseEventInputCommand command;
	
	protected void setUp() throws Exception {
		super.setUp();
		tab = new AdverseEventCaptureTab();
		
		AdverseEventReportingPeriod reportingPeriod = Fixtures.createReportingPeriod();
		List<AdverseEvent> aeList = createAdverseEventList();
		reportingPeriod.setAdverseEvents(aeList);
		Fixtures.createCtcV3Terminology(reportingPeriod.getStudy());
		
		command = new CaptureAdverseEventInputCommand();
		command.setAdverseEventReportingPeriod(reportingPeriod);
	}
	
	public List<AdverseEvent> createAdverseEventList(){
		List<AdverseEvent> aeList = new ArrayList<AdverseEvent>();
		for(int i =0; i < 3; i++){
			AdverseEvent ae = Fixtures.createAdverseEvent(i+1, Grade.NORMAL);
			//add couple of outcomes
			Outcome o1 = Fixtures.createOutcome(i*3 +10,OutcomeType.DEATH);
			Outcome o2 = Fixtures.createOutcome(i*3 +11,OutcomeType.HOSPITALIZATION);
			Outcome o3 = Fixtures.createOutcome(i*3 +12,OutcomeType.OTHER_SERIOUS);
			ae.addOutcome(o1);
			ae.addOutcome(o2);
			ae.addOutcome(o3);
			
			aeList.add(ae);
		}
		return aeList;
	}

	public void testCreateFieldGroupsCaptureAdverseEventInputCommand() {
		command.initializeOutcomes();
		Map<String, InputFieldGroup> fieldMap = tab.createFieldGroups(command);
		System.out.println(fieldMap);
		assertCorrectOutcomeFieldNames(fieldMap.get("outcomes0"), 
				"outcomes[0][" + DEATH.getCode() +"]",
				"outcomes[0][" + HOSPITALIZATION.getCode() +"]",
				"outcomes[0][" + LIFE_THREATENING.getCode() +"]",
				"outcomes[0][" + DISABILITY.getCode() +"]",
				"outcomes[0][" + CONGENITAL_ANOMALY.getCode() +"]",
				"outcomes[0][" + REQUIRED_INTERVENTION.getCode() +"]",
				"outcomes[0][" + OTHER_SERIOUS.getCode() +"]",
				"outcomeOtherDetails[0]" );
		assertCorrectOutcomeFieldNames(fieldMap.get("outcomes1"), 
				"outcomes[1][" + DEATH.getCode() +"]",
				"outcomes[1][" + HOSPITALIZATION.getCode() +"]",
				"outcomes[1][" + LIFE_THREATENING.getCode() +"]",
				"outcomes[1][" + DISABILITY.getCode() +"]",
				"outcomes[1][" + CONGENITAL_ANOMALY.getCode() +"]",
				"outcomes[1][" + REQUIRED_INTERVENTION.getCode() +"]",
				"outcomes[1][" + OTHER_SERIOUS.getCode() +"]",
				"outcomeOtherDetails[1]" );
		assertCorrectOutcomeFieldNames(fieldMap.get("outcomes2"), 
				"outcomes[2][" + DEATH.getCode() +"]",
				"outcomes[2][" + HOSPITALIZATION.getCode() +"]",
				"outcomes[2][" + LIFE_THREATENING.getCode() +"]",
				"outcomes[2][" + DISABILITY.getCode() +"]",
				"outcomes[2][" + CONGENITAL_ANOMALY.getCode() +"]",
				"outcomes[2][" + REQUIRED_INTERVENTION.getCode() +"]",
				"outcomes[2][" + OTHER_SERIOUS.getCode() +"]",
				"outcomeOtherDetails[2]" );
		
		correctMainGroupFields(fieldMap.get("main0"), "adverseEvents[0].", "detailsForOther",
				"grade",
				"startDate",
				"endDate",
				"attributionSummary",
				"eventApproximateTime",
				"eventLocation",
				"hospitalization",
				"expected");
		
		correctMainGroupFields(fieldMap.get("main1"), "adverseEvents[1].", "detailsForOther",
				"grade",
				"startDate",
				"endDate",
				"attributionSummary",
				"eventApproximateTime",
				"eventLocation",
				"hospitalization",
				"expected");
		
		correctMainGroupFields(fieldMap.get("main2"), "adverseEvents[2].", "detailsForOther",
				"grade",
				"startDate",
				"endDate",
				"attributionSummary",
				"eventApproximateTime",
				"eventLocation",
				"hospitalization",
				"expected");
	}
	
	public void assertCorrectOutcomeFieldNames(InputFieldGroup fieldGrp, String...propertyNames){
		int i = 0;
		for(InputField field : fieldGrp.getFields()){
			assertEquals(propertyNames[i], field.getPropertyName());
			if(i< (propertyNames.length -1)){
				assertTrue(field.getCategoryName().equals("checkbox"));
			}
			i++;
		}
	}
	
	public void  correctMainGroupFields(InputFieldGroup fieldGrp, String prefix, String... propertySuffixes){
		int i = 0;
		for(InputField field : fieldGrp.getFields()){
			assertEquals(prefix + propertySuffixes[i], field.getPropertyName());
			i++;
		}
	}

}
