package gov.nih.nci.cabig.caaers.web.ae;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.AdverseEventCtcTerm;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.Arm;
import gov.nih.nci.cabig.caaers.domain.Attribution;
import gov.nih.nci.cabig.caaers.domain.Epoch;
import gov.nih.nci.cabig.caaers.domain.Hospitalization;
import gov.nih.nci.cabig.caaers.domain.SolicitedAdverseEvent;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.Term;
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
		addHelpKeyExclusion("ctcVersion");
	}
	
	@Override
	public Map<String, InputFieldGroup> createFieldGroups(CaptureAdverseEventInputCommand cmd) {
		
		InputFieldGroupMap map = new InputFieldGroupMap();
		MultipleFieldGroupFactory mainFieldFactory;
		List<SolicitedAdverseEvent> saeList;

		// Creating the field groups for the first section of the page
		// which collects the general information from the user (eg., TAC, TAC Description, Start date of first course etc.
		InputFieldGroup reportingPeriodFieldGroup = new DefaultInputFieldGroup("reportingPeriodFG");
		List<InputField> fields = reportingPeriodFieldGroup.getFields();
		InputField reportingPeriodsField = InputFieldFactory.createSelectField("adverseEventReportingPeriod", "Reporting period", true, fetchReportingPeriodsOptions(cmd));
		fields.add(reportingPeriodsField);
		map.addInputFieldGroup(reportingPeriodFieldGroup);
		
		if(cmd.getAdverseEventReportingPeriod() != null){
			InputFieldGroup treatmentAssignmentFieldGroup = new DefaultInputFieldGroup("treatmentAssignmentFG"); 
			InputFieldGroup reportingPeriodDetailsFieldGroup = new DefaultInputFieldGroup("reportingPeriodDetailsFG");
			
		
			
			InputField treatmentAssignmentField = InputFieldFactory.createLabelField("adverseEventReportingPeriod.treatmentAssignment.code", "Treatment assignment");
			InputField treatmentAssignmentDescField = InputFieldFactory.createLabelField("adverseEventReportingPeriod.treatmentAssignment.description", "Treatement description");
			//startDateOfFirstCourse - TextField, if it is empty in assignment
			InputField firstCourseDateField = null;
			if(cmd.getAssignment().getStartDateOfFirstCourse() == null){
				firstCourseDateField = InputFieldFactory.createDateField("assignment.startDateOfFirstCourse", "Start date of first course", true);
			}else {
				firstCourseDateField = InputFieldFactory.createLabelField("assignment.startDateOfFirstCourse", "Start date of first course");
			}

			treatmentAssignmentFieldGroup.getFields().add(treatmentAssignmentField);
			treatmentAssignmentFieldGroup.getFields().add(treatmentAssignmentDescField);
			treatmentAssignmentFieldGroup.getFields().add(firstCourseDateField);
		
			// add reportingPeriod details group
			reportingPeriodDetailsFieldGroup.getFields().add(InputFieldFactory.createLabelField("adverseEventReportingPeriod.startDate", "Start Date:"));
			reportingPeriodDetailsFieldGroup.getFields().add(InputFieldFactory.createLabelField("adverseEventReportingPeriod.endDate", "End Date:"));
			reportingPeriodDetailsFieldGroup.getFields().add(InputFieldFactory.createLabelField("adverseEventReportingPeriod.epoch.name", "Type:"));
			reportingPeriodDetailsFieldGroup.getFields().add(InputFieldFactory.createLabelField("adverseEventReportingPeriod.cycleNumber", "Cycle Number:"));
			// add the reportingPeriodFieldGroup to the map.
			
			map.addInputFieldGroup(treatmentAssignmentFieldGroup);
			map.addInputFieldGroup(reportingPeriodDetailsFieldGroup);
		
		
			
			mainFieldFactory = new MultipleFieldGroupFactory(MAIN_FIELD_GROUP, "adverseEventReportingPeriod.adverseEvents");
			// Check if the adverseEventReportingPeriod has any adverseEvents.
			// If yes then display the solicited adverseEvents in the second section (Solicited Adverse Events)
			if(cmd.getAdverseEventReportingPeriod().getAdverseEvents() != null){
				for(int i = 0; i < cmd.getAdverseEventReportingPeriod().getAdverseEvents().size(); i++){
					//check if the adverse
					AdverseEvent ae = cmd.getAdverseEventReportingPeriod().getAdverseEvents().get(i);
					
						mainFieldFactory.addField(InputFieldFactory.createSelectField("grade", "Grade", false,
								createGradeOptions(ae)));
						mainFieldFactory.addField(InputFieldFactory.createSelectField("attributionSummary",
								"Attribution to study", false, createAttributionOptions()));
						mainFieldFactory.addField(InputFieldFactory.createSelectField("hospitalization",
								"Hospitalization", false, createHospitalizationOptions()));
						mainFieldFactory.addField(InputFieldFactory.createSelectField("expected", "Expected", false,
								createExpectedOptions()));
						InputFieldGroup fieldGroup = mainFieldFactory.createGroup(i);
						mainFieldFactory.addFieldGroup(fieldGroup);
						mainFieldFactory.clearFields();
					
				}
				map.addMultipleFieldGroupFactory(mainFieldFactory);
			}
		}
		
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
	
	private Map<Object, Object> createGradeOptions(AdverseEvent sae) {
        Map<Object, Object> gradeOptions = new LinkedHashMap<Object, Object>();
        gradeOptions.put("", "Please select");
        gradeOptions.putAll(InputFieldFactory.collectOptions(sae.getAdverseEventCtcTerm().getCtcTerm().getGrades(), "displayName", "code"));
        return gradeOptions;
    }
	
	@Override
    public Map<String, Object> referenceData(HttpServletRequest request, CaptureAdverseEventInputCommand command) {
		
		if(command.getAdverseEventReportingPeriod() != null && command.getAdverseEventReportingPeriod().getAdverseEvents().size() == 0){
			for(SolicitedAdverseEvent sae: command.getAdverseEventReportingPeriod().getEpoch().getArms().get(0).getSolicitedAdverseEvents()){
				AdverseEvent adverseEvent = new AdverseEvent();
				if(command.getStudy().getAeTerminology().getTerm() == Term.MEDDRA)
					adverseEvent.setLowLevelTerm(sae.getLowLevelTerm());
				else{
					AdverseEventCtcTerm aeCtcTerm = new AdverseEventCtcTerm();
					aeCtcTerm.setCtcTerm(sae.getCtcterm());
					adverseEvent.setAdverseEventTerm(aeCtcTerm);
					adverseEvent.setSolicitedAdverseEvent(true);
				}
				command.getAdverseEventReportingPeriod().addAdverseEvent(adverseEvent);	
			}
			
			// Setup the categories list for aeTermQuery tag.
			if(command.getCtcCategories().size() == 0)
				command.setCtcCategories(command.getStudy().getAeTerminology().getCtcVersion().getCategories());
		}
		
		Map<String, Object> refdata = super.referenceData(request, command);
		
		if(command.getAdverseEventReportingPeriod() != null && command.getAdverseEventReportingPeriod().getAdverseEvents().size() > 0){
			// Put a flag in the map "hasObservedEvents". This will be used to determine whether the table headers should be displayed
			// for observed events and displaying the existing observed events.
			
			boolean hasObservedEvents = false;
			for(AdverseEvent ae: command.getAdverseEventReportingPeriod().getAdverseEvents()){
				if(!ae.isSolicitedAdverseEvent())
					hasObservedEvents = true;
			}
			refdata.put("hasObservedEvent", hasObservedEvents);
		}
		
		return refdata;
	}
}

