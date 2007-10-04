package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.RadiationAdministration;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldAttributes;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;
import gov.nih.nci.cabig.caaers.web.fields.RepeatingFieldGroupFactory;

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
    public InputFieldGroupMap createFieldGroups(ExpeditedAdverseEventInputCommand command) {
    	//-
    	//InputFieldGroup allFields = new DefaultInputFieldGroup("desc");
        //String baseProp = "aeReport.radiationIntervention";
        
        RepeatingFieldGroupFactory fieldFactory = new RepeatingFieldGroupFactory("radiationIntervention", "aeReport.radiationInterventions");
        //String baseProp = "aeReport.medicalDevice";
    	
    	fieldFactory.setDisplayNameCreator(new RepeatingFieldGroupFactory.DisplayNameCreator() {
            public String createDisplayName(int index) {
                return "Radiation " + (index + 1);
            }
        });


    	fieldFactory.addField(InputFieldFactory.createTextField("treatmentArm", "Treatment arm", false));
        InputField descField = InputFieldFactory.createTextArea("description", "Treatment arm description", false);
        InputFieldAttributes.setSize(descField, 45);
        fieldFactory.addField(descField);
        Map<Object, Object> statusOpts = new LinkedHashMap<Object, Object>();
        statusOpts.put("", "Please select");
        statusOpts.putAll(InputFieldFactory.collectOptions(
            Arrays.asList(RadiationAdministration.values()), null, "displayName"));
        fieldFactory.addField(InputFieldFactory.createSelectField(
            "administration", "Type of radiation administration", false,
            statusOpts));

        fieldFactory.addField(InputFieldFactory.createTextField("dosage", "Dosage", false));
        fieldFactory.addField(InputFieldFactory.createTextField("dosageUnit", "Dosage unit", false));
        fieldFactory.addField(InputFieldFactory.createDateField(
                "lastTreatmentDate", "Date of last treatment",  false));
        fieldFactory.addField(InputFieldFactory.createTextField("fractionNumber", "Schedule number of fractions", false));
        fieldFactory.addField(InputFieldFactory.createTextField("daysElapsed", " Number of elapsed days", false));
        fieldFactory.addField(InputFieldFactory.createTextField("adjustment", "Adjustment", false));
    	//-
        InputFieldGroupMap map = new InputFieldGroupMap();
        int riCount = command.getAeReport().getRadiationInterventions().size();
        map.addRepeatingFieldGroupFactory(fieldFactory, riCount);
        return map;
    }

    @Override
    public ExpeditedReportSection section() {
    	return ExpeditedReportSection.RADIATION_INTERVENTION_SECTION;
    }
}
