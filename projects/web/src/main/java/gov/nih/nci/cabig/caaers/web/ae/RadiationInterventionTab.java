package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.RadiationAdministration;
import gov.nih.nci.cabig.caaers.web.fields.BaseSelectField;
import gov.nih.nci.cabig.caaers.web.fields.DefaultSelectField;
import gov.nih.nci.cabig.caaers.web.fields.DefaultTextArea;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.DefaultInputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.DefaultTextField;
import gov.nih.nci.cabig.caaers.web.fields.DefaultDateField;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Arrays;


/**
 * @author Krikor Krumlian
 */
public class RadiationInterventionTab extends AeTab {
    private InputFieldGroup allFields;
    
    public RadiationInterventionTab() {
        super("Radiation Intervention", "Radiation Intervention", "ae/radiationIntervention");
        allFields = new DefaultInputFieldGroup("desc");
        String baseProp = "aeReport.radiationIntervention";

        
        allFields.getFields().add(new DefaultTextField(baseProp + ".treatmentArm", "Treatment Arm", false));
        allFields.getFields().add(new DefaultTextArea(baseProp + ".description", "Treatment Arm Description", false));
        Map<Object, Object> statusOpts = new LinkedHashMap<Object, Object>();
        statusOpts.put("", "Please select");
        statusOpts.putAll(BaseSelectField.collectOptions(
            Arrays.asList(RadiationAdministration.values()), null, "displayName"));
        allFields.getFields().add(new DefaultSelectField(
            baseProp + ".administration", "Type of Radiation Administration", false,
            statusOpts));
        
        allFields.getFields().add(new DefaultTextField(baseProp + ".dosage", "Dosage", false));
        allFields.getFields().add(new DefaultTextField(baseProp + ".dosageUnit", "Dosage Unit", false));
        allFields.getFields().add(new DefaultDateField(
                baseProp + ".lastTreatmentDate", "Date of Last Treatment",  false));
        allFields.getFields().add(new DefaultTextField(baseProp + ".fractionNumber", "Schedule Number of Fractions", false));
        allFields.getFields().add(new DefaultTextField(baseProp + ".daysElapsed", " Number of Elapsed Days", false));
        allFields.getFields().add(new DefaultTextField(baseProp + ".adjustment", "Adjustment", false));
    }

    @Override
    public Map<String, InputFieldGroup> createFieldGroups(ExpeditedAdverseEventInputCommand command) {
        return createFieldGroupMap(Arrays.asList(allFields));
    }
}
