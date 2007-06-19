package gov.nih.nci.cabig.caaers.web.ae;

import static gov.nih.nci.cabig.caaers.web.fields.BaseSelectField.collectOptions;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.RepeatingFieldGroupFactory;
import gov.nih.nci.cabig.caaers.web.fields.DefaultInputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.DefaultDateField;
import gov.nih.nci.cabig.caaers.web.fields.DefaultTextField;
import gov.nih.nci.cabig.caaers.web.fields.DefaultTextArea;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;
import gov.nih.nci.cabig.caaers.web.fields.CompositeField;
import gov.nih.nci.cabig.caaers.web.fields.DefaultSelectField;
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
        treatmentFields.getFields().add(new DefaultDateField(
            "aeReport.treatmentInformation.firstCourseDate",
            "First course start date", true
        ));
        treatmentFields.getFields().add(new DefaultTextField(
            "aeReport.treatmentInformation.adverseEventCourse.number",
            "Adverse event course number", true
        ));
        treatmentFields.getFields().add(new DefaultDateField(
            "aeReport.treatmentInformation.adverseEventCourse.date",
            "Adverse event course start date", true
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

        caFields.addField(new DefaultSelectField(
            "studyAgent", "Study Agent", true,
            collectOptions(command.getStudy().getStudyAgents(), "id", "agent.name")));
        caFields.addField(createDoseField("dose", "Dose", true));
        caFields.addField(new DefaultTextArea(
            "durationAndSchedule", "Duration and schedule", false));

        caFields.addField(new CompositeField(null,
            new DefaultInputFieldGroup(null, "Administration delay")
                .addField(new DefaultTextField("administrationDelayAmount", "", false))
                .addField(new DefaultSelectField(
                    "administrationDelayUnits", "", false,
                    collectOptions(Arrays.asList(DelayUnits.values()), null, "displayName")))
        ));

        caFields.addField(createDoseField("modifiedDose", "Modified dose", false));

        caFields.addField(new DefaultTextField(
            "totalDoseAdministeredThisCourse", "Total dose administered this course", false));
        caFields.addField(new DefaultDateField(
            "lastAdministeredDate", "Date last administered", false));

        InputFieldGroupMap map = new InputFieldGroupMap();
        map.addInputFieldGroup(treatmentFields);
        map.addRepeatingFieldGroupFactory(caFields,
            command.getAeReport().getTreatmentInformation().getCourseAgents().size());

        return map;
    }

    private CompositeField createDoseField(String doseProperty, String displayName, boolean required) {
        InputFieldGroup group = new DefaultInputFieldGroup(null, displayName)
            .addField(new DefaultTextField("amount", "", required))
            .addField(new DefaultTextField("units", "units", required))
            .addField(new DefaultTextField("route", "route", false /* never required */));
        return new CompositeField(doseProperty, group);
    }
}
