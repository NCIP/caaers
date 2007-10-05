package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.RadiationAdministration;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldAttributes;
import static gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory.*;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Arrays;


/**
 * @author Krikor Krumlian
 */
public class RadiationInterventionTab extends AeTab {
    public RadiationInterventionTab() {
        super("Radiation Intervention", "Radiation", "ae/radiationIntervention");
    }

    @Override
    protected void createFieldGroups(AeInputFieldCreator creator, ExpeditedAdverseEventInputCommand command) {
        InputField descField = createTextArea("description", "Treatment arm description", false);
        InputFieldAttributes.setSize(descField, 45);
        Map<Object, Object> statusOpts = new LinkedHashMap<Object, Object>();
        statusOpts.put("", "Please select");
        statusOpts.putAll(collectOptions(
            Arrays.asList(RadiationAdministration.values()), null, "displayName"));

        creator.createRepeatingFieldGroup("radiationIntervention", "radiationInterventions",
            new SimpleNumericDisplayNameCreator("Radiation"),
            createTextField("treatmentArm", "Treatment arm", false),
            descField,
            createSelectField("administration", "Type of radiation administration", false, statusOpts),

            createTextField("dosage", "Dosage", false),
            createTextField("dosageUnit", "Dosage unit", false),
            createDateField("lastTreatmentDate", "Date of last treatment",  false),
            createTextField("fractionNumber", "Schedule number of fractions", false),
            createTextField("daysElapsed", " Number of elapsed days", false),
            createTextField("adjustment", "Adjustment", false)
        );
    }

    @Override
    public ExpeditedReportSection section() {
        return ExpeditedReportSection.RADIATION_INTERVENTION_SECTION;
    }
}
