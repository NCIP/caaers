package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.web.fields.AutocompleterField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.DefaultTextArea;
import gov.nih.nci.cabig.caaers.web.fields.DefaultInputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.DefaultTextField;
import gov.nih.nci.cabig.caaers.web.fields.DefaultDateField;

import java.util.Map;
import java.util.Arrays;


/**
 * @author Krikor Krumlian
 */
public class SurgeryInterventionTab extends AeTab {
    private InputFieldGroup allFields;

    public SurgeryInterventionTab() {
        super("Surgery Intervention", "Surgery Intervention", "ae/surgeryIntervention");
        allFields = new DefaultInputFieldGroup("desc");
        String baseProp = "aeReport.surgeryIntervention";

        
        allFields.getFields().add(new DefaultTextField(baseProp + ".treatmentArm", "Treatment Arm", false));
        allFields.getFields().add(new DefaultTextArea(baseProp + ".description", "Treatment Arm Description", false));
        allFields.getFields().add(new AutocompleterField(baseProp + ".anatomicSite", "Intervention Site", false));
        allFields.getFields().add(new DefaultDateField(
                baseProp + ".interventionDate", "Date of Intervention",  false));
    }

    @Override
    public Map<String, InputFieldGroup> createFieldGroups(ExpeditedAdverseEventInputCommand command) {
        return createFieldGroupMap(Arrays.asList(allFields));
    }
}
