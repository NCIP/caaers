package gov.nih.nci.cabig.caaers.web.ae;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.TreatmentAssignment;
import gov.nih.nci.cabig.caaers.web.fields.DefaultInputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;
import gov.nih.nci.cabig.caaers.web.fields.TabWithFields;

/**
 * 
 * @author Biju Joseph
 *
 */
public class AdverseEventCaptureTab extends TabWithFields<CaptureAdverseEventInputCommand>{
	
	public AdverseEventCaptureTab() {
		super("Enter Adverse Events", "Adverse events", "ae/captureAdverseEvents");
	}
	
	@Override
	public Map<String, InputFieldGroup> createFieldGroups(CaptureAdverseEventInputCommand cmd) {
		InputFieldGroup reportingPeriodFieldGroup = new DefaultInputFieldGroup("reportingPeriod");
		List<InputField> fields = reportingPeriodFieldGroup.getFields();
		
		InputField reportingPeriodsField = InputFieldFactory.createSelectField("reportingPeriod", "Reporting period", true, fetchReportingPeriodsOptions(cmd));
		InputField treatmentAssignmentField = InputFieldFactory.createSelectField("reportingPeriod.treatmentAssignment", "Treatment assignment", true, fetchTreatmentAssignmentOptions(cmd));
		InputField treatmentAssignmentDescField = InputFieldFactory.createTextArea("reportingPeriod.treatmentAssignment", "Treatement description");
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

		
		InputFieldGroupMap map = new InputFieldGroupMap();
		map.addInputFieldGroup(reportingPeriodFieldGroup);
		return map;
	}
	
	public Map<Object, Object> fetchReportingPeriodsOptions(CaptureAdverseEventInputCommand cmd){
		return new HashMap<Object, Object>();
		//return InputFieldFactory.collectOptions(, itemValueProperty, itemLabelProperty, blankValue);
	}
	
	public Map<Object, Object> fetchTreatmentAssignmentOptions(CaptureAdverseEventInputCommand cmd) {
		return InputFieldFactory.collectOptions(cmd.getStudy().getTreatmentAssignments(), "id", "code", "Please select");
	}

}
