package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.DelayUnits;
import gov.nih.nci.cabig.caaers.domain.TreatmentAssignment;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;
import gov.nih.nci.cabig.caaers.utils.ConfigProperty;
import gov.nih.nci.cabig.caaers.web.fields.CompositeField;
import gov.nih.nci.cabig.caaers.web.fields.DefaultInputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldAttributes;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.validators.FieldValidator;
import gov.nih.nci.cabig.caaers.web.utils.WebUtils;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.validation.Errors;

/**
 * @author Rhett Sutphin
 */
public class TreatmentTab extends AeTab {
    private ConfigProperty configurationProperty;

    public TreatmentTab() {
        super("Treatment Information", ExpeditedReportSection.TREATMENT_INFO_SECTION
                        .getDisplayName(), "ae/treatment");
    }

    @Override
    protected void createFieldGroups(AeInputFieldCreator creator,
                    ExpeditedAdverseEventInputCommand command) {
        InputField assignmentField = InputFieldFactory.createSelectField("treatmentAssignment",
                        "Treatment assignment code", false,
                        collectTreatmentAssignmentCodes(command));
        InputFieldAttributes.setSize(assignmentField, 20);
        assignmentField.getAttributes().put(InputField.HELP,
                        "ae.treatment.aeReport.treatmentInformation.treatmentAssignment");
        InputField descField = InputFieldFactory.createTextArea("treatmentAssignmentDescription",
                        "Description of treatment assignment or dose level");
        InputFieldAttributes.setColumns(descField, 70);
        InputFieldAttributes.setRows(descField, 4);
        InputField newDescField = InputFieldFactory.createTextArea("treatmentDescription",
                        "Enter a description of treatment assignment or dose level");
        InputFieldAttributes.setColumns(newDescField, 70);
        InputFieldAttributes.setRows(newDescField, 4);
        InputField eventCourseField = InputFieldFactory
                        .createNumberField("adverseEventCourse.number",
                                        "Course number on which event occurred", false);
        InputFieldAttributes.setSize(eventCourseField, 4);
        InputField totalCourseField = InputFieldFactory.createNumberField("totalCourses",
                        "Total number of courses to date", false);
        InputFieldAttributes.setSize(totalCourseField, 4);

        InputField firstCourseDateField = InputFieldFactory.createPastDateField("firstCourseDate",
                        "Start date of first course", false);
        firstCourseDateField.getAttributes().put(InputField.HELP,
                        "ae.treatment.aeReport.treatmentInformation.firstCourseDate");
       
        InputField adverseEventCourse_dateField = InputFieldFactory.createPastDateField("adverseEventCourse.date",
                        "Start date of course associated with expedited report", false);
        adverseEventCourse_dateField.getAttributes().put(InputField.HELP,
                        "ae.treatment.aeReport.treatmentInformation.adverseEventCourse.date");

        creator.createFieldGroup("treatmentInfo", null, "treatmentInformation",
        		assignmentField, descField, newDescField, firstCourseDateField,
                        adverseEventCourse_dateField,  eventCourseField, totalCourseField);

        InputField agentField = InputFieldFactory.createSelectField("studyAgent", "Study Agent",
                        false, WebUtils.collectOptions(
                                        command.getStudy().getStudyAgents(), "id", "agentName", "Please select"));
        InputField totalDoseField = InputFieldFactory.createTextField("dose.amount",
                        "Total dose administered this course", false);
        InputFieldAttributes.setSize(totalDoseField, 4);
        InputField totalUOMField = InputFieldFactory.createSelectField("dose.units","Unit of measure", false, 
        		WebUtils.collectOptions(configurationProperty.getMap().get("agentDoseUMORefData"),"code", "desc", "Please Select"));
        CompositeField adminDelayField = new CompositeField(null, new DefaultInputFieldGroup(null,"Administration delay")
        	.addField(InputFieldFactory.createTextField("administrationDelayAmount", "", false))
        	.addField(InputFieldFactory.createSelectField("administrationDelayUnits", "", false,
        			WebUtils.collectOptions(Arrays.asList(DelayUnits.values()), null, "displayName")))
        );
        InputField commentsField = InputFieldFactory.createTextArea("comments", "Comments", false);
        InputFieldAttributes.setColumns(commentsField, 70);
        InputFieldAttributes.setRows(commentsField, 4);
        InputField modifiedDoseField = createDoseField("modifiedDose", "Modified dose", false, true);
        modifiedDoseField.getAttributes().put(InputField.HELP,"ae.treatment.aeReport.treatmentInformation.courseAgents.modifiedDose");
        
        creator.createRepeatingFieldGroup("courseAgent", 
        		"treatmentInformation.courseAgents", 
        		new SimpleNumericDisplayNameCreator("Study Agent"), 
        		agentField,
        		InputFieldFactory.createTextField("formulation", "Formulation"),
        		InputFieldFactory.createTextField("lotNumber", "Lot # (if known)"),
                totalDoseField, 
                totalUOMField, 
                InputFieldFactory.createPastDateField("lastAdministeredDate", "Date last administered", false),
                adminDelayField, 
                commentsField, 
                modifiedDoseField);
    }

    private CompositeField createDoseField(String doseProperty, String displayName,
                    boolean required, boolean hideRoute) {
    	FieldValidator[] validators = (required) ? new FieldValidator[]{FieldValidator.NOT_NULL_VALIDATOR, FieldValidator.NUMBER_VALIDATOR} : new FieldValidator[]{FieldValidator.NUMBER_VALIDATOR};
        DefaultInputFieldGroup group = new DefaultInputFieldGroup(null, displayName).addField(
                        InputFieldFactory.createTextField("amount", "", validators)).addField(
                        InputFieldFactory.createSelectField("units", "units", false,
                        		WebUtils.collectOptions(configurationProperty
                                                        .getMap().get("agentDoseUMORefData"),
                                                        "code", "desc", "Please Select")));
        if (!hideRoute) {
            group.addField(InputFieldFactory
                            .createTextField("route", "route", false /* never required */));
        }
        return new CompositeField(doseProperty, group);
    }

    private Map<Object, Object> collectTreatmentAssignmentCodes(
                    ExpeditedAdverseEventInputCommand command) {
        LinkedHashMap<Object, Object> map = new LinkedHashMap<Object, Object>();
        map.put("", "Please select");
        List<TreatmentAssignment> taList = command.getAeReport().getStudy()
                        .getTreatmentAssignments();
        if (taList != null) {
            for (TreatmentAssignment ta : taList) {
                map.put(ta.getId(), ta.getCode());
            }
        }
        return map;
    }

    @Override
    public void onBind(HttpServletRequest request, ExpeditedAdverseEventInputCommand command,
                    Errors errors) {
        super.onBind(request, command, errors);
        if (StringUtils.equals("other", request.getParameter("treatmentDescriptionType"))) {
            command.getAeReport().getTreatmentInformation().setTreatmentAssignment(null);
        } else {
            command.getAeReport().getTreatmentInformation().setTreatmentDescription(null);
        }

    }


    public ConfigProperty getConfigurationProperty() {
        return configurationProperty;
    }

    public void setConfigurationProperty(ConfigProperty configurationProperty) {
        this.configurationProperty = configurationProperty;
    }

    @Override
    public ExpeditedReportSection[] section() {
        return new ExpeditedReportSection[] {ExpeditedReportSection.TREATMENT_INFO_SECTION};
    }
}
