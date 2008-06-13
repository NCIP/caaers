package gov.nih.nci.cabig.caaers.web.ae;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.Arm;
import gov.nih.nci.cabig.caaers.domain.Attribution;
import gov.nih.nci.cabig.caaers.domain.Epoch;
import gov.nih.nci.cabig.caaers.domain.Hospitalization;
import gov.nih.nci.cabig.caaers.domain.SolicitedAdverseEvent;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.TreatmentAssignment;
import gov.nih.nci.cabig.caaers.web.fields.DefaultInputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldAttributes;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;
import gov.nih.nci.cabig.caaers.web.fields.MultipleFieldGroupFactory;
import gov.nih.nci.cabig.caaers.web.fields.RepeatingFieldGroupFactory;
import gov.nih.nci.cabig.caaers.web.fields.TabWithFields;
import gov.nih.nci.cabig.caaers.web.participant.AssignParticipantStudyCommand;
import gov.nih.nci.cabig.caaers.web.participant.NewParticipantCommand;

/**
 * 
 * @author Biju Joseph
 *
 */
public class AdverseEventCaptureTab extends TabWithFields<CaptureAdverseEventInputCommand>{
	
	private static final String MAIN_FIELD_GROUP = "main";
	
	public AdverseEventCaptureTab() {
		super("Enter Adverse Events", "Adverse events", "ae/captureAdverseEvents");
	}
	
	@Override
	public Map<String, InputFieldGroup> createFieldGroups(CaptureAdverseEventInputCommand cmd) {
		
		InputFieldGroupMap map = new InputFieldGroupMap();
		
		// Creating the field groups for the first section of the page
		// which collects the general information from the user (eg., TAC, TAC Description, Start date of first course etc.
		InputFieldGroup reportingPeriodFieldGroup = new DefaultInputFieldGroup("reportingPeriod");
		List<InputField> fields = reportingPeriodFieldGroup.getFields();
		
		InputField reportingPeriodsField = InputFieldFactory.createSelectField("adverseEventReportingPeriod", "Reporting period", true, fetchReportingPeriodsOptions(cmd));
		InputField treatmentAssignmentField = InputFieldFactory.createSelectField("adverseEventReportingPeriod.treatmentAssignment", "Treatment assignment", true, fetchTreatmentAssignmentOptions(cmd));
		InputField treatmentAssignmentDescField = InputFieldFactory.createTextArea("tacDescription", "Treatement description");
		//startDateOfFirstCourse - TextField, if it is empty in assignment
		InputField firstCourseDateField = null;
		if(cmd.getAssignment().getStartDateOfFirstCourse() == null){
			firstCourseDateField = InputFieldFactory.createDateField("assignment.startDateOfFirstCourse", "Start date of first course", true);
		}else {
			firstCourseDateField = InputFieldFactory.createLabelField("assignment.startDateOfFirstCourse", "Start date of first course");
		}
		fields.add(reportingPeriodsField);
		fields.add(treatmentAssignmentField);
		fields.add(treatmentAssignmentDescField);
		fields.add(firstCourseDateField);
		// add the reportingPeriodFieldGroup to the map.
		map.addInputFieldGroup(reportingPeriodFieldGroup);
		
		// Creating the field groups for the solicited ae section of the page.
		// This needs to be closely looked into.. needs to be modified.
		// Am populating the reportingPeriod of command here .. 
		// In practice it will be populated automatically once the user selects one in the dropdown.
		// For the first time it will be autopopulated with the latest reportingPeriod.
		List<AdverseEventReportingPeriod> rPeriodsList = cmd.getAssignment().getReportingPeriods();
		cmd.setAdverseEventReportingPeriod(rPeriodsList.get(0));
		List<Arm> armsList = cmd.getAdverseEventReportingPeriod().getEpoch().getArms();
		Arm arm = armsList.get(0);
		List<SolicitedAdverseEvent> saeList = arm.getSolicitedAdverseEvents();
		cmd.setSaeList(saeList);
		
		List<AdverseEvent> aeList = new ArrayList<AdverseEvent>(4);
		AdverseEvent ae = new AdverseEvent();
		aeList.add(ae);
		aeList.add(ae);
		aeList.add(ae);
		aeList.add(ae);
		aeList.add(ae);
		cmd.getAdverseEventReportingPeriod().setAdverseEvents(aeList);
		
		// Till here the command object was created to help mocking up the screen. It needs to be refactored. 
		
		MultipleFieldGroupFactory mainFieldFactory = new MultipleFieldGroupFactory(MAIN_FIELD_GROUP, "adverseEventReportingPeriod.adverseEvents");
		for(int i = 0; i < saeList.size(); i++){
			mainFieldFactory.addField(InputFieldFactory.createSelectField("grade", "Grade", true,
                    createGradeOptions(saeList.get(i))));
			mainFieldFactory.addField(InputFieldFactory.createSelectField("attributionSummary",
                    "Attribution to study", true, createAttributionOptions()));
			mainFieldFactory.addField(InputFieldFactory.createSelectField("hospitalization",
                    "Hospitalization", true, createHospitalizationOptions()));
			mainFieldFactory.addField(InputFieldFactory.createSelectField("expected", "Expected", true,
                    createExpectedOptions()));
			InputFieldGroup fieldGroup = mainFieldFactory.createGroup(i);
			mainFieldFactory.addFieldGroup(fieldGroup);
			mainFieldFactory.clearFields();
		
		}
		map.addMultipleFieldGroupFactory(mainFieldFactory);
		return map;
	}
	
	public Map<Object, Object> fetchReportingPeriodsOptions(CaptureAdverseEventInputCommand cmd){
		Map<Object,Object> map = new LinkedHashMap<Object, Object>();
		map.put("", "Please select");
		List<AdverseEventReportingPeriod> reportingPeriodList = cmd.getAssignment().getReportingPeriods();
		for(AdverseEventReportingPeriod adverseEventReportingPeriod: reportingPeriodList){
			map.put(adverseEventReportingPeriod.getId(), adverseEventReportingPeriod.fetchName());
		}
		return map;
	}
	
	public Map<Object, Object> fetchTreatmentAssignmentOptions(CaptureAdverseEventInputCommand cmd) {
		return InputFieldFactory.collectOptions(cmd.getStudy().getTreatmentAssignments(), "id", "code", "Please select");
	}
	
	private Map<Object, Object> createExpectedOptions() {
        Map<Object, Object> expectedOptions = new LinkedHashMap<Object, Object>();
        expectedOptions.put("", "Please select");
        expectedOptions.put(Boolean.TRUE, "Yes");
        expectedOptions.put(Boolean.FALSE, "No");
        return expectedOptions;
    }
	
	private Map<Object, Object> createHospitalizationOptions() {
        Map<Object, Object> hospitalizationOptions = new LinkedHashMap<Object, Object>();
        hospitalizationOptions.put("", "Please select");
        hospitalizationOptions.putAll(InputFieldFactory.collectOptions(Arrays
                        .asList(Hospitalization.values()), "name", "displayName"));
        return hospitalizationOptions;
    }
	
	private Map<Object, Object> createAttributionOptions() {
        Map<Object, Object> attributionOptions = new LinkedHashMap<Object, Object>();
        attributionOptions.put("", "Please select");
        attributionOptions.putAll(InputFieldFactory.collectOptions(Arrays.asList(Attribution
                        .values()), "name", "displayName"));
        return attributionOptions;
    }
	
	private Map<Object, Object> createGradeOptions(SolicitedAdverseEvent sae) {
        Map<Object, Object> gradeOptions = new LinkedHashMap<Object, Object>();
        gradeOptions.put("", "Please select");
        gradeOptions.putAll(InputFieldFactory.collectOptions(sae.getCtcterm().getGrades(), "displayName", "code"));
        return gradeOptions;
    }
}
