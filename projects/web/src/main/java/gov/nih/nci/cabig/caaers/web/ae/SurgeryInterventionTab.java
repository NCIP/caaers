package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.DefaultInputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory;

import java.util.Map;
import java.util.Arrays;


/**
 * @author Krikor Krumlian
 */
public class SurgeryInterventionTab extends AeTab {

    public SurgeryInterventionTab() {
        super("Surgery Intervention", "Surgery", "ae/surgeryIntervention");

    }

    @Override
    public Map<String, InputFieldGroup> createFieldGroups(ExpeditedAdverseEventInputCommand command) {
    	//-
    	InputFieldGroup allFields = new DefaultInputFieldGroup("desc");
        String baseProp = "aeReport.surgeryIntervention";


        allFields.getFields().add(InputFieldFactory.createTextField(baseProp + ".treatmentArm", "Treatment arm", false));
        allFields.getFields().add(InputFieldFactory.createTextArea(baseProp + ".description", "Treatment arm description", false));
        allFields.getFields().add(InputFieldFactory.createAutocompleterField(baseProp + ".anatomicSite", "Intervention site", false));
        allFields.getFields().add(InputFieldFactory.createDateField(
                baseProp + ".interventionDate", "Date of intervention",  false));
    	//-
        return createFieldGroupMap(Arrays.asList(allFields));
    }

    @Override
    public ExpeditedReportSection section() {
    	return ExpeditedReportSection.SURGERY_INTERVENTION_SECTION;
    }
}
