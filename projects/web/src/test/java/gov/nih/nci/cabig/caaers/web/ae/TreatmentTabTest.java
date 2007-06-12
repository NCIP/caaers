package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.DelayUnits;
import static gov.nih.nci.cabig.caaers.domain.Fixtures.*;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;

import java.util.Map;
import java.util.List;

/**
 * @author Rhett Sutphin
 */
public class TreatmentTabTest extends AeTabTestCase {
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
            "aeReport.treatmentInformation.courseAgents[2].dose",
            "aeReport.treatmentInformation.courseAgents[2].durationAndSchedule",
            "aeReport.treatmentInformation.courseAgents[2]", // administration delay doesn't have a base prop
            "aeReport.treatmentInformation.courseAgents[2].modifiedDose",
            "aeReport.treatmentInformation.courseAgents[2].totalDoseAdministeredThisCourse",
            "aeReport.treatmentInformation.courseAgents[2].lastAdministeredDate"
        );
    }

    @SuppressWarnings({ "unchecked" })
    public void testDoseSubfields() throws Exception {
        InputField doseField = getFieldGroup("courseAgent7").getFields().get(1); // dose
        assertTrue("Field 1 isn't dose (probably a test setup issue): " + doseField.getPropertyName(),
            doseField.getPropertyName().endsWith("dose"));
        List<InputField> subfields
            = (List<InputField>) doseField.getAttributes().get(InputField.SUBFIELDS);
        assertNotNull("Dose isn't a composite field", subfields);
        assertEquals("Wrong number of subfields", 3, subfields.size());
        assertEquals("Wrong subfield 0",
            "aeReport.treatmentInformation.courseAgents[7].dose.amount",
            subfields.get(0).getPropertyName());
        assertEquals("Wrong subfield 1",
            "aeReport.treatmentInformation.courseAgents[7].dose.units",
            subfields.get(1).getPropertyName());
        assertEquals("Wrong subfield 2",
            "aeReport.treatmentInformation.courseAgents[7].dose.route",
            subfields.get(2).getPropertyName());
    }

    @SuppressWarnings({ "unchecked" })
    public void testDoseSubfieldsWhenAllPreviousGroupsLoaded() throws Exception {
        Map<String, InputFieldGroup> map = getTab().createFieldGroups(command);
        for (int i = 0; i <= 6; i++) map.get("courseAgent" + i);
        
        InputField doseField = map.get("courseAgent7").getFields().get(1); // dose
        assertTrue("Field 1 isn't dose (probably a test setup issue): " + doseField.getPropertyName(),
            doseField.getPropertyName().endsWith("dose"));
        List<InputField> subfields
            = (List<InputField>) doseField.getAttributes().get(InputField.SUBFIELDS);
        assertNotNull("Dose isn't a composite field", subfields);
        assertEquals("Wrong number of subfields", 3, subfields.size());
        assertEquals("Wrong subfield 0",
            "aeReport.treatmentInformation.courseAgents[7].dose.amount",
            subfields.get(0).getPropertyName());
        assertEquals("Wrong subfield 1",
            "aeReport.treatmentInformation.courseAgents[7].dose.units",
            subfields.get(1).getPropertyName());
        assertEquals("Wrong subfield 2",
            "aeReport.treatmentInformation.courseAgents[7].dose.route",
            subfields.get(2).getPropertyName());
    }

    @SuppressWarnings({ "unchecked" })
    public void testModDoseSubfields() throws Exception {
        InputField modDoseField = getFieldGroup("courseAgent7").getFields().get(4); // modifiedDose
        assertTrue("Field 4 isn't dose (probably a test setup issue): " + modDoseField.getPropertyName(),
            modDoseField.getPropertyName().endsWith("modifiedDose"));
        List<InputField> subfields
            = (List<InputField>) modDoseField.getAttributes().get(InputField.SUBFIELDS);
        assertNotNull("Dose isn't a composite field", subfields);
        assertEquals("Wrong number of subfields", 3, subfields.size());
        assertEquals("Wrong subfield 0",
            "aeReport.treatmentInformation.courseAgents[7].modifiedDose.amount",
            subfields.get(0).getPropertyName());
        assertEquals("Wrong subfield 1",
            "aeReport.treatmentInformation.courseAgents[7].modifiedDose.units",
            subfields.get(1).getPropertyName());
        assertEquals("Wrong subfield 2",
            "aeReport.treatmentInformation.courseAgents[7].modifiedDose.route",
            subfields.get(2).getPropertyName());
    }

    @SuppressWarnings({ "unchecked" })
    public void testAdminDelaySubfields() throws Exception {
        InputField delayField = getFieldGroup("courseAgent7").getFields().get(3); // modifiedDose
        List<InputField> subfields
            = (List<InputField>) delayField.getAttributes().get(InputField.SUBFIELDS);
        assertNotNull("Dose isn't a composite field", subfields);
        assertEquals("Wrong number of subfields", 2, subfields.size());
        assertEquals("Wrong subfield 0",
            "aeReport.treatmentInformation.courseAgents[7].administrationDelayAmount",
            subfields.get(0).getPropertyName());
        assertEquals("Wrong subfield 1",
            "aeReport.treatmentInformation.courseAgents[7].administrationDelayUnits",
            subfields.get(1).getPropertyName());
    }

    @SuppressWarnings({ "unchecked" })
    private Map<Object, Object> getActualSelectFieldOptions(List<InputField> fields, String propertyName) {
        InputField field = null;
        for (InputField candidate : fields) {
            if (candidate.getPropertyName().equals(propertyName)) {
                field = candidate;
                break;
            }
        }
        assertNotNull("No field for " + propertyName + ": " + fields, field);
        Map<Object, Object> options = (Map<Object, Object>) field.getAttributes().get(InputField.OPTIONS);
        assertNotNull("Field for " + propertyName + " is not a select", options);
        return options;
    }

    @SuppressWarnings({ "unchecked" })
    public void testDelayUnitsInField() throws Exception {
        InputField delayComposite = getFieldGroup("courseAgent0").getFields().get(3);

        Map<Object, Object> actualOptions = getActualSelectFieldOptions(
            (List<InputField>) delayComposite.getAttributes().get(InputField.SUBFIELDS),
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

        InputFieldGroup group = getFieldGroup("courseAgent0");
        Map<Object, Object> actualOptions = getActualSelectFieldOptions(group.getFields(),
            "aeReport.treatmentInformation.courseAgents[0].studyAgent");
        assertEquals("Wrong number of options: " + actualOptions, 2, actualOptions.size());
        assertEquals("Missing option for sa 0", "Agent 1", actualOptions.get(166));
        assertEquals("Missing option for sa 1", "Agent 2", actualOptions.get(267));
    }
}
