/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.TreatmentAssignment;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;
import gov.nih.nci.cabig.caaers.utils.ConfigProperty;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldAttributes;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.Errors;

/**
 * @author Rhett Sutphin
 */
public class TreatmentTab extends AeTab {
    private ConfigProperty configurationProperty;

    public TreatmentTab() {
        super("Treatment Information", ExpeditedReportSection.TREATMENT_INFO_SECTION.getDisplayName(), "ae/treatment");
    }

    @Override
    protected void createFieldGroups(AeInputFieldCreator creator, ExpeditedAdverseEventInputCommand command) {
        InputField assignmentField = InputFieldFactory.createSelectField("treatmentAssignment", "Treatment assignment code", false, collectTreatmentAssignmentCodes(command));
        InputFieldAttributes.setSize(assignmentField, 20);
        assignmentField.getAttributes().put(InputField.HELP, "ae.treatment.aeReport.treatmentInformation.treatmentAssignment");
        InputField descField = InputFieldFactory.createTextArea("treatmentAssignmentDescription", "Description of treatment assignment or dose level");
        InputFieldAttributes.setColumns(descField, 70);
        InputFieldAttributes.setRows(descField, 4);
        InputField newDescField = InputFieldFactory.createTextArea("treatmentDescription", "Enter a description of treatment assignment or dose level");
        InputFieldAttributes.setColumns(newDescField, 70);
        InputFieldAttributes.setRows(newDescField, 4);
        InputField eventCourseField = InputFieldFactory.createNumberField("adverseEventCourse.number", "Course number on which event occurred", false);
        InputFieldAttributes.setSize(eventCourseField, 4);
        InputField totalCourseField = InputFieldFactory.createNumberField("totalCourses", "Total number of courses to date", false);
        InputFieldAttributes.setSize(totalCourseField, 4);
        //InputField investigationalAgentAdministeredField = InputFieldFactory.createSelectField("investigationalAgentAdministered", "Was an investigational agent administered on this protocol?" , false, createInvestigationalAgentAdministeredOptions());
        //investigationalAgentAdministeredField.getAttributes().put(InputField.HELP, "ae.treatment.aeReport.treatmentInformation.investigationalAgentAdministered");
 

        InputField firstCourseDateField = InputFieldFactory.createPastDateField("firstCourseDate", "Start date of first course", false);
        firstCourseDateField.getAttributes().put(InputField.HELP, "ae.treatment.aeReport.treatmentInformation.firstCourseDate");
       
        InputField adverseEventCourse_dateField = InputFieldFactory.createPastDateField("adverseEventCourse.date", "Start date of course associated with expedited report", false);
        adverseEventCourse_dateField.getAttributes().put(InputField.HELP, "ae.treatment.aeReport.treatmentInformation.adverseEventCourse.date");

        creator.createFieldGroup("treatmentInfo", null, "treatmentInformation", assignmentField, descField, newDescField, firstCourseDateField, adverseEventCourse_dateField,  eventCourseField, totalCourseField);

    }
    
    @Override
    public Map<String, Object> referenceData(HttpServletRequest request,ExpeditedAdverseEventInputCommand command) {
    	Map<String, Object> refData =  super.referenceData(request, command);
    
    	//Determines whether Other should be rendered and selected when the page is loaded. 
    	refData.put("validOtherTreatmentDescription", command.getAeReport().getTreatmentInformation().isOther());
    	
    	return refData;
    }
	
    private Map<Object, Object> collectTreatmentAssignmentCodes(ExpeditedAdverseEventInputCommand command) {
        LinkedHashMap<Object, Object> map = new LinkedHashMap<Object, Object>();
        map.put("", "Please select");
        List<TreatmentAssignment> taList = command.getAeReport().getStudy().getActiveTreatmentAssignments();
        if (taList != null) {
            for (TreatmentAssignment ta : taList) {
                map.put("" + ta.getId(), ta.getCode());
            }
        }
        return map;
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
