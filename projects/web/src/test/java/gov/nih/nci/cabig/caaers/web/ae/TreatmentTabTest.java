package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.DelayUnits;
import static gov.nih.nci.cabig.caaers.domain.Fixtures.*;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;

import java.util.Map;

/**
 * @author Rhett Sutphin
 */
public class TreatmentTabTest extends AeTabTestCase<TreatmentTab> {
    @Override
    protected TreatmentTab createTab() {
        return new TreatmentTab();
    }

    public void testTreatmentInfoFields() throws Exception {
        assertFieldProperties("treatmentInfo",
            "aeReport.treatmentInformation.firstCourseDate",
            "aeReport.treatmentInformation.adverseEventCourse.number",
            "aeReport.treatmentInformation.adverseEventCourse.date"
        );
    }

    public void testCourseAgentFields() throws Exception {
        assertFieldProperties("courseAgent2",
            "aeReport.treatmentInformation.courseAgents[2].studyAgent",
            "aeReport.treatmentInformation.courseAgents[2].dose.amount",
            "aeReport.treatmentInformation.courseAgents[2].dose.units",
            "aeReport.treatmentInformation.courseAgents[2].dose.route",
            "aeReport.treatmentInformation.courseAgents[2].durationAndSchedule",
            "aeReport.treatmentInformation.courseAgents[2].administrationDelayAmount",
            "aeReport.treatmentInformation.courseAgents[2].administrationDelayUnits",
            "aeReport.treatmentInformation.courseAgents[2].modifiedDose.amount",
            "aeReport.treatmentInformation.courseAgents[2].modifiedDose.units",
            "aeReport.treatmentInformation.courseAgents[2].modifiedDose.route",
            "aeReport.treatmentInformation.courseAgents[2].totalDoseAdministeredThisCourse",
            "aeReport.treatmentInformation.courseAgents[2].lastAdministeredDate"
        );
    }

    private Map<Object, Object> getActualSelectFieldOptions(String groupName, String propertyName) {
        InputFieldGroup group = getFieldGroup(groupName);
        InputField field = null;
        for (InputField candidate : group.getFields()) {
            if (candidate.getPropertyName().equals(propertyName)) {
                field = candidate;
                break;
            }
        }
        assertNotNull("No field for " + propertyName, field);
        Map<Object, Object> options = (Map<Object, Object>) field.getAttributes().get(InputField.OPTIONS);
        assertNotNull("Field for " + propertyName + " is not a select", options);
        return options;
    }

    public void testDelayUnitsInField() throws Exception {
        Map<Object, Object> actualOptions = getActualSelectFieldOptions("courseAgent0",
            "aeReport.treatmentInformation.courseAgents[0].administrationDelayUnits");
        assertEquals("Wrong number of options: " + actualOptions,
            DelayUnits.values().length, actualOptions.size());
        for (DelayUnits units : DelayUnits.values()) {
            assertEquals("Missing option for " + units, units.getDisplayName(),
                actualOptions.get(units.toString()));
        }
    }

    public void testStudyAgentsOptionsInField() throws Exception {
        command.getStudy().addStudyAgent(setId(166, createStudyAgent("Agent 1")));
        command.getStudy().addStudyAgent(setId(267, createStudyAgent("Agent 2")));

        Map<Object, Object> actualOptions = getActualSelectFieldOptions("courseAgent0",
            "aeReport.treatmentInformation.courseAgents[0].studyAgent");
        assertEquals("Wrong number of options: " + actualOptions, 2, actualOptions.size());
        assertEquals("Missing option for sa 0", "Agent 1", actualOptions.get(166));
        assertEquals("Missing option for sa 1", "Agent 2", actualOptions.get(267));
    }
}
