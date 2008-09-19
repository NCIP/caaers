package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.Grade;
import gov.nih.nci.cabig.caaers.domain.SolicitedAdverseEvent;
import gov.nih.nci.cabig.caaers.domain.Term;
import gov.nih.nci.cabig.caaers.web.fields.*;
import org.drools.util.StringUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.validation.Errors;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Biju Joseph
 */
public class AdverseEventCaptureTab extends AdverseEventTab {

    private static final String MAIN_FIELD_GROUP = "main";

    public AdverseEventCaptureTab() {
        super("Enter Adverse Events", "Adverse events", "ae/captureAdverseEvents");
        addHelpKeyExclusion("ctcVersion");
    }


    /**
     * This method will create the fields to be displayed on the screen.
     * Notes<br>
     * 1. For solicited adverse events, the "Notes/Verbatim", "Other Meddra" will added to the fields.
     * 2. If Study is MedDRA, the "Other MedDRA", will not be added in the fields.
     * 3. We should run the adverse events against the index fixed list, since that list will have null items in it, we should skip if 'AdverseEvent' is null.
     */

    @Override
    public Map<String, InputFieldGroup> createFieldGroups(CaptureAdverseEventInputCommand cmd) {

        InputFieldGroupMap map = new InputFieldGroupMap();
        MultipleFieldGroupFactory mainFieldFactory;
        List<SolicitedAdverseEvent> saeList;

        // Creating the field groups for the first section of the page
        // which collects the general information from the user (eg., TAC, TAC Description, Start date of first course etc.
        InputFieldGroup reportingPeriodFieldGroup = new DefaultInputFieldGroup("reportingPeriodFG");
        List<InputField> fields = reportingPeriodFieldGroup.getFields();
        InputField reportingPeriodsField = InputFieldFactory.createSelectField("adverseEventReportingPeriod", "Evaluation period", true, fetchReportingPeriodsOptions(cmd));
        fields.add(reportingPeriodsField);
        map.addInputFieldGroup(reportingPeriodFieldGroup);

        //create the fields, consisting of reporting period details.
        if (cmd.getAdverseEventReportingPeriod() != null) {

            InputFieldGroup treatmentAssignmentFieldGroup = new DefaultInputFieldGroup("treatmentAssignmentFG");
            InputFieldGroup reportingPeriodDetailsFieldGroup = new DefaultInputFieldGroup("reportingPeriodDetailsFG");

            //TAC fields groups
            InputField treatmentAssignmentField = InputFieldFactory.createLabelField("adverseEventReportingPeriod.treatmentAssignment.code", "Treatment assignment");
            InputField treatmentAssignmentDescField = InputFieldFactory.createLabelField("adverseEventReportingPeriod.treatmentAssignment.description", "Treatment description");

            //startDateOfFirstCourse - TextField, if it is empty in assignment
            InputField firstCourseDateField = null;
            if (cmd.getAssignment().getStartDateOfFirstCourse() == null) {
                firstCourseDateField = InputFieldFactory.createPastDateField("assignment.startDateOfFirstCourse", "Start date of first course", false);
            } else {
                firstCourseDateField = InputFieldFactory.createLabelField("assignment.startDateOfFirstCourse", "Start date of first course");
            }

            treatmentAssignmentFieldGroup.getFields().add(treatmentAssignmentField);
            treatmentAssignmentFieldGroup.getFields().add(treatmentAssignmentDescField);
            treatmentAssignmentFieldGroup.getFields().add(firstCourseDateField);

            // add reportingPeriod details group
            reportingPeriodDetailsFieldGroup.getFields().add(InputFieldFactory.createLabelField("adverseEventReportingPeriod.startDate", "Start date"));
            reportingPeriodDetailsFieldGroup.getFields().add(InputFieldFactory.createLabelField("adverseEventReportingPeriod.endDate", "End date"));
            reportingPeriodDetailsFieldGroup.getFields().add(InputFieldFactory.createLabelField("adverseEventReportingPeriod.epoch.name", "Type"));
            reportingPeriodDetailsFieldGroup.getFields().add(InputFieldFactory.createLabelField("adverseEventReportingPeriod.cycleNumber", "Cycle number"));
            reportingPeriodDetailsFieldGroup.getFields().add(InputFieldFactory.createLabelField("adverseEventReportingPeriod.description", "Description"));

            // add the reportingPeriodFieldGroup to the map.
            map.addInputFieldGroup(treatmentAssignmentFieldGroup);
            map.addInputFieldGroup(reportingPeriodDetailsFieldGroup);


            /*
                * AdversEvent related field groups,
                *  the fields are different for Meddra study, Ctc study and Observed AEs
                */
            mainFieldFactory = new MultipleFieldGroupFactory(MAIN_FIELD_GROUP, "adverseEvents");
            boolean isMeddraStudy = cmd.getStudy().getAeTerminology().getTerm() == Term.MEDDRA;

            int size = cmd.getAdverseEvents().size();
            for (int i = 0; i < size; i++) {
                AdverseEvent ae = cmd.getAdverseEvents().get(i);
                if (ae == null) continue;
                if (isMeddraStudy)
                    mainFieldFactory.addField(InputFieldFactory.createLabelField("lowLevelTerm.meddraTerm", "Term", true));
                else
                    mainFieldFactory.addField(InputFieldFactory.createLabelField("adverseEventTerm.universalTerm", "Term", true)); //Term

                if (!ae.getSolicited()) {
                    if (!isMeddraStudy && ae.getAdverseEventTerm().isOtherRequired()) { //only if other is requrired
                        mainFieldFactory.addField(InputFieldFactory.createAutocompleterField("lowLevelTerm", "Other(MedDRA)", false));
                    }
                    InputField notesField = InputFieldFactory.createTextField("detailsForOther", "Notes/Verbatim");
                    InputFieldAttributes.setSize(notesField, 50);
                    mainFieldFactory.addField(notesField); //Notes
                }
                //grade
                if (isMeddraStudy) {
                    // Make grade mandatory only for observed aes.
                    if (ae.getSolicited())
                        mainFieldFactory.addField(InputFieldFactory.createSelectField("grade", "Grade", false, createGradeOptions(ae, "Meddra")));
                    else
                        mainFieldFactory.addField(InputFieldFactory.createSelectField("grade", "Grade", true, createGradeOptions(ae, "Meddra")));
                } else {
                    if (ae.getSolicited())
                        mainFieldFactory.addField(InputFieldFactory.createSelectField("grade", "Grade", false, createGradeOptions(ae, "Ctc")));
                    else
                        mainFieldFactory.addField(InputFieldFactory.createSelectField("grade", "Grade", true, createGradeOptions(ae, "Ctc")));
                }

                if (ae.getSolicited())
                    mainFieldFactory.addField(InputFieldFactory.createSelectField("attributionSummary", "Attribution to study", false, createAttributionOptions()));
                else
                    mainFieldFactory.addField(InputFieldFactory.createSelectField("attributionSummary", "Attribution to study", true, createAttributionOptions()));
                mainFieldFactory.addField(InputFieldFactory.createSelectField("hospitalization",
                        "Hospitalization", false, createHospitalizationOptions()));
                mainFieldFactory.addField(InputFieldFactory.createSelectField("expected", "Expected", false,
                        createExpectedOptions()));
                mainFieldFactory.addField(InputFieldFactory.createSelectField("serious", "Serious", false, createSeriousOptions()));
                InputFieldGroup fieldGroup = mainFieldFactory.createGroup(i);
                mainFieldFactory.addFieldGroup(fieldGroup);
                mainFieldFactory.clearFields();

            }
            map.addMultipleFieldGroupFactory(mainFieldFactory);
        }

        return map;
    }

    @Override
    public void beforeBind(HttpServletRequest request, CaptureAdverseEventInputCommand command) {
        super.beforeBind(request, command);
    }


    @Override
    public Map<String, Object> referenceData(HttpServletRequest request, CaptureAdverseEventInputCommand command) {
        // Setup the categories list for aeTermQuery tag.
        boolean isCTCStudy = command.getStudy().getAeTerminology().getTerm() == Term.CTC;
        if (isCTCStudy)
            command.getCtcCategories();

        //initalize the seriousness outcome indicators
        command.initializeOutcome();

        return super.referenceData(request, command);

    }

    @Override
    public void postProcess(HttpServletRequest request, CaptureAdverseEventInputCommand command, Errors errors) {
        if (findInRequest(request, CaptureAdverseEventController.AJAX_SUBVIEW_PARAMETER) != null)
            return; //ignore if this is an ajax request
        //sync the seriousness outcomes
        command.synchronizeOutcome();
    }

    @Override
    public void onBind(HttpServletRequest request, CaptureAdverseEventInputCommand command, Errors errors) {
        String rpId = request.getParameter("adverseEventReportingPeriod");
        if (StringUtils.isEmpty(rpId)) {
            command.setAdverseEventReportingPeriod(null);
        }
        Set<String> paramNames = request.getParameterMap().keySet();
        boolean fromListPage = false;
        fromListPage = paramNames.contains("displayReportingPeriod");
        if (fromListPage)
            command.refreshAssignment(Integer.parseInt(rpId));
    }

    @Override
    protected void validate(CaptureAdverseEventInputCommand command, BeanWrapper commandBean,
                            Map<String, InputFieldGroup> fieldGroups, Errors errors) {

//		// firstStartDateField should be present for non-baseline reporting periods.
//		if((command.getAdverseEventReportingPeriod() != null) && (command.getAdverseEventReportingPeriod().getEpoch().getName().equals("Baseline"))){
//			InputField firstStartDateField = fieldGroups.get("main0").getFields().get(1);
//			errors.rejectValue(firstStartDateField.getPropertyName(), "REQUIRED",
//                            firstStartDateField.getDisplayName() + " required for primary AE");
//		}
//		
//		// test: if(grade == not evaluated), other fields shouldnt be entered.
//		if(command.getAdverseEventReportingPeriod().getAdverseEvents() != null && command.getAdverseEventReportingPeriod().getAdverseEvents().size() > 0){
//			for(AdverseEvent ae: command.getAdverseEventReportingPeriod().getAdverseEvents()){
//				if(ae.getGrade() != null && ae.getGrade().getName().equals("Not Evaluated")){
//					// Check if other field values are entered. Incase they are, an error should be displayed.
//				}
//			}
//		}

        // If grade is greater than 2 then hospitalization cannot be null.
        for (AdverseEvent ae : command.getAdverseEventReportingPeriod().getAdverseEvents()) {
            if (!ae.getSolicited()) {
                if (ae.getGrade().getCode() > 2 && ae.getHospitalization() == null)
                    errors.reject("HOSPITALIZATION_NEEDED", "Hospitalization must be entered if grade is greater than 2");
            }
        }

        // If grade = "Please Select" then other attributes cannot be modified. They should be "Please Select"
        for (AdverseEvent ae : command.getAdverseEventReportingPeriod().getAdverseEvents()) {
            if (ae.getSolicited()) {
                if (ae.getGrade() == null || ae.getGrade().equals(Grade.NOT_EVALUATED)) {
                    if (ae.getAttributionSummary() != null || ae.getHospitalization() != null || ae.getExpected() != null) {
                        if (ae.getGrade() == null)
                            errors.reject("GRADE_NEEDED", "Attribution, Hospitalization or Expectedness cannot be selected when Grade is not selected.");
                        else
                            errors.reject("GRADE_NEEDED", "Attribution, Hospitalization or Expectedness cannot be selected when Grade is not evaluated.");
                    }


                }
            }
        }
    }
}