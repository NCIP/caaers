package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.RepeatingFieldGroupFactory;
import gov.nih.nci.cabig.caaers.web.fields.DefaultInputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;
import gov.nih.nci.cabig.caaers.web.fields.CompositeField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory;
import gov.nih.nci.cabig.caaers.domain.DelayUnits;

import java.util.Map;
import java.util.Arrays;

/**
 * @author Rhett Sutphin
 */
public class TreatmentTab extends AeTab {
    private InputFieldGroup treatmentFields;

    public TreatmentTab() {
        super("Treatment Information", "Treatment", "ae/treatment");

        treatmentFields = new DefaultInputFieldGroup("treatmentInfo");
        treatmentFields.getFields().add(InputFieldFactory.createDateField(
            "aeReport.treatmentInformation.firstCourseDate",
            "First course start date", false
        ));
        treatmentFields.getFields().add(InputFieldFactory.createTextField(
            "aeReport.treatmentInformation.adverseEventCourse.number",
            "Adverse event course number", false
        ));
        treatmentFields.getFields().add(InputFieldFactory.createDateField(
            "aeReport.treatmentInformation.adverseEventCourse.date",
            "Adverse event course start date", false
        ));
        treatmentFields.getFields().add(InputFieldFactory.createTextField(
            "aeReport.treatmentInformation.treatmentAssignmentCode",
            "Assignment code", false
        ));
    }

    @Override
    public Map<String, InputFieldGroup> createFieldGroups(ExpeditedAdverseEventInputCommand command) {
        RepeatingFieldGroupFactory caFields = new RepeatingFieldGroupFactory("courseAgent",
            "aeReport.treatmentInformation.courseAgents");
        caFields.setDisplayNameCreator(new RepeatingFieldGroupFactory.DisplayNameCreator() {
            public String createDisplayName(int index) {
                return "Course agent " + (index + 1);
            }
        });

        caFields.addField(InputFieldFactory.createSelectField(
            "studyAgent", "Study Agent", true,
            InputFieldFactory.collectOptions(command.getStudy().getStudyAgents(), "id", "agent.name")));
        caFields.addField(createDoseField("dose", "Dose", true));
        caFields.addField(InputFieldFactory.createTextArea(
            "durationAndSchedule", "Duration and schedule", false));

        caFields.addField(new CompositeField(null,
            new DefaultInputFieldGroup(null, "Administration delay")
                .addField(InputFieldFactory.createTextField("administrationDelayAmount", "", false))
                .addField(InputFieldFactory.createSelectField(
                    "administrationDelayUnits", "", false,
                    InputFieldFactory.collectOptions(Arrays.asList(DelayUnits.values()), null, "displayName")))
        ));

        caFields.addField(createDoseField("modifiedDose", "Modified dose", false));

        caFields.addField(InputFieldFactory.createTextField(
            "totalDoseAdministeredThisCourse", "Total dose administered this course", false));
        caFields.addField(InputFieldFactory.createDateField(
            "lastAdministeredDate", "Date last administered", false));

        InputFieldGroupMap map = new InputFieldGroupMap();
        map.addInputFieldGroup(treatmentFields);
        map.addRepeatingFieldGroupFactory(caFields,
            command.getAeReport().getTreatmentInformation().getCourseAgents().size());

        return map;
    }

    private CompositeField createDoseField(String doseProperty, String displayName, boolean required) {
        InputFieldGroup group = new DefaultInputFieldGroup(null, displayName)
            .addField(InputFieldFactory.createTextField("amount", "", required))
            .addField(InputFieldFactory.createTextField("units", "units", required))
            .addField(InputFieldFactory.createTextField("route", "route", false /* never required */));
        return new CompositeField(doseProperty, group);
    }
}
