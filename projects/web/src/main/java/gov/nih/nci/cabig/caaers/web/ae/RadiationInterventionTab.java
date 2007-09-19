package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.RadiationAdministration;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.DefaultInputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory;

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
    public Map<String, InputFieldGroup> createFieldGroups(ExpeditedAdverseEventInputCommand command) {
    	//-
    	InputFieldGroup allFields = new DefaultInputFieldGroup("desc");
        String baseProp = "aeReport.radiationIntervention";


        allFields.getFields().add(InputFieldFactory.createTextField(baseProp + ".treatmentArm", "Treatment arm", false));
        allFields.getFields().add(InputFieldFactory.createTextArea(baseProp + ".description", "Treatment arm description", false));
        Map<Object, Object> statusOpts = new LinkedHashMap<Object, Object>();
        statusOpts.put("", "Please select");
        statusOpts.putAll(InputFieldFactory.collectOptions(
            Arrays.asList(RadiationAdministration.values()), null, "displayName"));
        allFields.getFields().add(InputFieldFactory.createSelectField(
            baseProp + ".administration", "Type of radiation administration", false,
            statusOpts));

        allFields.getFields().add(InputFieldFactory.createTextField(baseProp + ".dosage", "Dosage", false));
        allFields.getFields().add(InputFieldFactory.createTextField(baseProp + ".dosageUnit", "Dosage unit", false));
        allFields.getFields().add(InputFieldFactory.createDateField(
                baseProp + ".lastTreatmentDate", "Date of last treatment",  false));
        allFields.getFields().add(InputFieldFactory.createTextField(baseProp + ".fractionNumber", "Schedule number of fractions", false));
        allFields.getFields().add(InputFieldFactory.createTextField(baseProp + ".daysElapsed", " Number of elapsed days", false));
        allFields.getFields().add(InputFieldFactory.createTextField(baseProp + ".adjustment", "Adjustment", false));
    	//-
        return createFieldGroupMap(Arrays.asList(allFields));
    }

    @Override
    public ExpeditedReportSection section() {
    	return ExpeditedReportSection.RADIATION_INTERVENTION_SECTION;
    }
}
