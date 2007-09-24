package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.utils.ConfigProperty;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldAttributes;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.RepeatingFieldGroupFactory;
import gov.nih.nci.cabig.caaers.web.fields.DefaultInputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;
import gov.nih.nci.cabig.caaers.web.fields.CompositeField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory;
import gov.nih.nci.cabig.caaers.domain.DelayUnits;
import gov.nih.nci.cabig.caaers.domain.TreatmentAssignment;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Arrays;

import org.springframework.beans.BeanWrapper;
import org.springframework.validation.Errors;

/**
 * @author Rhett Sutphin
 */
public class TreatmentTab extends AeTab {
	private ConfigProperty configurationProperty;
    public TreatmentTab() {
        super("Treatment Information", "Course and Agent", "ae/treatment");
    }

    @Override
    public Map<String, InputFieldGroup> createFieldGroups(ExpeditedAdverseEventInputCommand command) {
    	//-
    	  InputFieldGroup treatmentFields = new DefaultInputFieldGroup("treatmentInfo");
    	/*InputField assignmentField = InputFieldFactory.createAutocompleterField(
    			"aeReport.treatmentInformation.treatmentAssignment",
                "Treatment Assignment Code", false);*/
    	  InputField assignmentField = InputFieldFactory.createSelectField(
    			"aeReport.treatmentInformation.treatmentAssignment",
                "Treatment assignment code", collectTreatmentAssignmentCodes(command));
    	  InputFieldAttributes.setSize(assignmentField, 20);
    	  treatmentFields.getFields().add(assignmentField);
    	  InputField descField = InputFieldFactory.createTextArea("aeReport.treatmentInformation.treatmentAssignmentDescription",
		  "Description of treatment assignment or dose level");
    	  InputFieldAttributes.setColumns(descField,45);
    	  InputFieldAttributes.setRows(descField, 4);
    	  treatmentFields.getFields().add(descField);
    	  InputField newDescField = InputFieldFactory.createTextArea("aeReport.treatmentInformation.treatmentDescription",
		  "Enter a description of treatment assignment or dose level");
    	  InputFieldAttributes.setColumns(newDescField, 45);
    	  InputFieldAttributes.setRows(newDescField, 4);

    	  treatmentFields.getFields().add(newDescField);
    	  treatmentFields.getFields().add(InputFieldFactory.createDateField(
              "aeReport.treatmentInformation.firstCourseDate",
              "Start date of first course", false
          ));
    	  treatmentFields.getFields().add(InputFieldFactory.createDateField(
                  "aeReport.treatmentInformation.adverseEventCourse.date",
                  "Start date of course associated with expedited report", false
          ));
    	  InputField eventCourseField = InputFieldFactory.createTextField(
                  "aeReport.treatmentInformation.adverseEventCourse.number",
                  "Course number on which event occurred", false
              );
    	  InputFieldAttributes.setSize(eventCourseField, 4);
          treatmentFields.getFields().add(eventCourseField);

          InputField totalCourseField = InputFieldFactory.createTextField(
                  "aeReport.treatmentInformation.totalCourses",
                  "Total number of courses to date", false
              );
    	  InputFieldAttributes.setSize(totalCourseField, 4);
          treatmentFields.getFields().add(totalCourseField);



    	//-
        RepeatingFieldGroupFactory caFields = new RepeatingFieldGroupFactory("courseAgent",
            "aeReport.treatmentInformation.courseAgents");
        caFields.setDisplayNameCreator(new RepeatingFieldGroupFactory.DisplayNameCreator() {
            public String createDisplayName(int index) {
                return "Study agent " + (index + 1);
            }
        });

        caFields.addField(InputFieldFactory.createSelectField(
            "studyAgent", "Study Agent", false,
            InputFieldFactory.collectOptions(command.getStudy().getStudyAgents(), "id", "agentName")));

        InputField totalDoseField = InputFieldFactory.createTextField("dose.amount", "Total dose administered this course", false);
        InputFieldAttributes.setSize(totalDoseField, 4);
        caFields.addField(totalDoseField);

        InputField totalUOMField = InputFieldFactory.createSelectField("dose.units",
        		"Unit of measure",
        		false,
        		InputFieldFactory.collectOptions(configurationProperty.getMap().get("doseUMORefData"), "code", "desc", "Please Select"));

        caFields.addField(totalUOMField);
        caFields.addField(InputFieldFactory.createDateField(
                "lastAdministeredDate", "Date last administered", false));

        caFields.addField(new CompositeField(null,
            new DefaultInputFieldGroup(null, "Administration delay")
                .addField(InputFieldFactory.createTextField("administrationDelayAmount", "", false))
                .addField(InputFieldFactory.createSelectField(
                    "administrationDelayUnits", "", false,
                    InputFieldFactory.collectOptions(Arrays.asList(DelayUnits.values()), null, "displayName")))
        ));

       InputField commentsField =InputFieldFactory.createTextArea(
    		      "comments", "Comments", false);
       InputFieldAttributes.setColumns(commentsField, 45);
      caFields.addField(commentsField);

        caFields.addField(createDoseField("modifiedDose", "Modified dose", false, false));



        InputFieldGroupMap map = new InputFieldGroupMap();
        map.addInputFieldGroup(treatmentFields);
        map.addRepeatingFieldGroupFactory(caFields,
            command.getAeReport().getTreatmentInformation().getCourseAgents().size());

        return map;
    }

    private CompositeField createDoseField(String doseProperty, String displayName, boolean required, boolean hideRoute) {
    	DefaultInputFieldGroup group = new DefaultInputFieldGroup(null, displayName)
            .addField(InputFieldFactory.createTextField("amount", "", required))
            .addField(InputFieldFactory.createSelectField("units",
            		"units",
            		true,
            		InputFieldFactory.collectOptions(configurationProperty.getMap().get("doseUMORefData"), "code", "desc", "Please Select")));
        if(!hideRoute){
            group.addField(InputFieldFactory.createTextField("route", "route", false /* never required */));
        }
        return new CompositeField(doseProperty, group);
    }

    private Map<Object, Object> collectTreatmentAssignmentCodes(ExpeditedAdverseEventInputCommand command){
    	LinkedHashMap<Object, Object> map = new LinkedHashMap<Object, Object>();
    	map.put("", "Please select");
    	List<TreatmentAssignment> taList = command.getAeReport().getStudy().getTreatmentAssignments();
    	if(taList != null){
    		for(TreatmentAssignment ta : taList ){
    			map.put(ta.getId(), ta.getCode());
    		}
    	}
    	return map;
    }

    @Override
    protected void validate(ExpeditedAdverseEventInputCommand command,
    		BeanWrapper commandBean, Map<String, InputFieldGroup> fieldGroups,
    		Errors errors) {
    	super.validate(command, commandBean, fieldGroups, errors);
    	if("other".equals(command.getTreatmentDescriptionType())){
    		if(command.getAeReport().getTreatmentInformation().getTreatmentDescription() == null){
    			errors.rejectValue("aeReport.treatmentInformation.treatmentDescription", "REQUIRED",
    					"Missing description of treatment assignment or dose level");
    		}
    	}else{
    		if(command.getAeReport().getTreatmentInformation().getTreatmentAssignment() == null){
    			errors.rejectValue("aeReport.treatmentInformation.treatmentAssignment", "REQUIRED",
    					"Missing description of treatment assignment or dose level");
    		}
    	}
    }

    public ConfigProperty getConfigurationProperty() {
		return configurationProperty;
	}

	public void setConfigurationProperty(ConfigProperty configurationProperty) {
		this.configurationProperty = configurationProperty;
	}

    @Override
    public ExpeditedReportSection section() {
    	return ExpeditedReportSection.TREATMENT_INFO_SECTION;
    }
}
