package gov.nih.nci.cabig.caaers.web.ae;

import static gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory.collectOptions;
import static gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory.createDateField;
import static gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory.createSelectField;
import static gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory.createTextArea;
import static gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory.createTextField;
import gov.nih.nci.cabig.caaers.domain.RadiationAdministration;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;
import gov.nih.nci.cabig.caaers.utils.ConfigProperty;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldAttributes;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * @author Krikor Krumlian
 */
public class RadiationInterventionTab extends AeTab {
	
	private ConfigProperty configurationProperty;
	
    public RadiationInterventionTab() {
        super("Radiation Intervention", ExpeditedReportSection.RADIATION_INTERVENTION_SECTION.getDisplayName(), "ae/radiationIntervention");
    }

    @Override
    protected void createFieldGroups(AeInputFieldCreator creator, ExpeditedAdverseEventInputCommand command) {
    	
    	String code = command.getAeReport().getTreatmentInformation().getTreatmentAssignment() != null ?
    			command.getAeReport().getTreatmentInformation().getTreatmentAssignment().getCode() : null;
    			
    	String description = code != null ? command.getAeReport().getTreatmentInformation().getTreatmentAssignmentDescription() :
    		command.getAeReport().getTreatmentInformation().getTreatmentDescription();
    			
        InputField descField = createTextArea("description", "Treatment arm description", false);
        InputFieldAttributes.setDetails(descField, description);
        InputFieldAttributes.setSize(descField, 45);
        Map<Object, Object> statusOpts = new LinkedHashMap<Object, Object>();
        statusOpts.put("", "Please select");
        statusOpts.putAll(collectOptions(
            Arrays.asList(RadiationAdministration.values()), null, "displayName"));
        
        InputField codeField = createTextField("treatmentArm", "Treatment arm", false);
        InputFieldAttributes.setDetails(codeField, code);
        InputField doseUOMField = InputFieldFactory.createSelectField("dosageUnit","Unit of measure",false,
                InputFieldFactory.collectOptions(configurationProperty.getMap().get("radiationDoseUMORefData"), "code", "desc", "Please Select"));

        creator.createRepeatingFieldGroup("radiationIntervention", "radiationInterventions",
            new SimpleNumericDisplayNameCreator("Radiation"),
            codeField,
            descField,
            createSelectField("administration", "Type of radiation administration", false, statusOpts),
            createTextField("dosage", "Dosage", false),
            doseUOMField,
            createDateField("lastTreatmentDate", "Date of last treatment",  false),
            createTextField("fractionNumber", "Schedule number of fractions", false),
            createTextField("daysElapsed", " Number of elapsed days", false),
            //createTextField("adjustment", "Adjustment", false)
            createSelectField("adjustment", "Adjustment", false, 
            		InputFieldFactory.collectOptions(configurationProperty.getMap().get("radiationAdjustmentRefData"), "code", "desc", "Please Select"))
        );
    }
    
    public ConfigProperty getConfigurationProperty() {
		return configurationProperty;
	}

	public void setConfigurationProperty(ConfigProperty configurationProperty) {
		this.configurationProperty = configurationProperty;
	}
    
    @Override
    public ExpeditedReportSection section() {
        return ExpeditedReportSection.RADIATION_INTERVENTION_SECTION;
    }
}
