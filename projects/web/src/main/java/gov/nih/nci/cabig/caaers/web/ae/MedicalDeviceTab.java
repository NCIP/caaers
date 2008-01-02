package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.utils.ConfigProperty;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldAttributes;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory;
import gov.nih.nci.cabig.caaers.domain.Availability;
import gov.nih.nci.cabig.caaers.domain.DeviceOperator;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;

import java.util.Arrays;

/**
 * @author Krikor Krumlian
 */
public class MedicalDeviceTab extends AeTab {

	private ConfigProperty configurationProperty;
	
    public MedicalDeviceTab() {
        super("Medical Device", ExpeditedReportSection.MEDICAL_DEVICE_SECTION.getDisplayName(), "ae/medicalDevice");
    }

    @Override
    protected void createFieldGroups(AeInputFieldCreator creator, ExpeditedAdverseEventInputCommand command) {
        
    	InputField brandName = InputFieldFactory.createTextField("brandName", "Brand name", false);
    	InputFieldAttributes.setColumns(brandName, 30);
    	
    	InputField commonName = InputFieldFactory.createTextField("commonName", "Common name", false);
    	InputFieldAttributes.setColumns(commonName, 30);
    	
    	InputField deviceType = InputFieldFactory.createTextField("deviceType", "Device type", false);
    	InputFieldAttributes.setColumns(deviceType, 30);
    	
    	InputField manName = InputFieldFactory.createTextField("manufacturerName", "Manufacturer name", false);
    	InputFieldAttributes.setColumns(manName, 30);
    	
    	InputField manCity = InputFieldFactory.createTextField("manufacturerCity", "Manufacturer city", false);
    	InputFieldAttributes.setColumns(manCity, 30);
    	
    	//InputField manState = InputFieldFactory.createTextField("manufacturerState", "Manufacturer state", false);
    	InputField manState = InputFieldFactory.createSelectField("manufacturerState", "Manufacturer state", false,
    			InputFieldFactory.collectOptions(configurationProperty.getMap().get("stateRefData"), "code", "desc", "Please Select"));
    	InputFieldAttributes.setColumns(manState, 30);
    	
    	InputField modelNumber = InputFieldFactory.createTextField("modelNumber", "Model number", false);
    	
    	InputField otherDeviceOperator =  InputFieldFactory.createTextField("otherDeviceOperator", "Other device operator", false);
    	InputFieldAttributes.setColumns(otherDeviceOperator, 30);
    	
    	InputField reprocessorName = InputFieldFactory.createTextField("reprocessorName", " Reprocessor name", false);
    	InputFieldAttributes.setColumns(reprocessorName, 30);
    	
    	InputField reprocessorAddress = InputFieldFactory.createTextField("reprocessorAddress", " Reprocessor address", false);
    	InputFieldAttributes.setColumns(reprocessorAddress, 50);
    	
    	
    	creator.createRepeatingFieldGroup("medicalDevice", "medicalDevices",
            new SimpleNumericDisplayNameCreator("Medical device"),
            brandName,
            commonName,
            deviceType,
            manName,
            manCity,
            manState,
            modelNumber,
            InputFieldFactory.createTextField("lotNumber", "Lot number", false),
            InputFieldFactory.createTextField("catalogNumber", "Catalog number", false),

            InputFieldFactory.createDateField("expirationDate", "Expiration date",  false),
            InputFieldFactory.createTextField("serialNumber", "Serial number", false),
            InputFieldFactory.createTextField("otherNumber", "Other number", false),

            InputFieldFactory.createSelectField("deviceOperator", "Device operator", false, InputFieldFactory.collectOptions(Arrays.asList(DeviceOperator.values()), null, "displayName")),

            otherDeviceOperator,

            InputFieldFactory.createDateField("implantedDate", "If implanted give a date",  false),
            InputFieldFactory.createDateField("explantedDate", "IF explanted give a date",  false),

            InputFieldFactory.createSelectField("deviceReprocessed", "Device reprocessed", false, InputFieldFactory.collectOptions(Arrays.asList(Availability.values()), null, "displayName")),

            reprocessorName,
            reprocessorAddress,
            
            InputFieldFactory.createSelectField("evaluationAvailability", "Evaluation availability", false, InputFieldFactory.collectOptions(Arrays.asList(Availability.values()), null, "displayName")),

            InputFieldFactory.createDateField("returnedDate", "Returned date",  false)
        );
    }

    @Override
    public ExpeditedReportSection section() {
        return ExpeditedReportSection.MEDICAL_DEVICE_SECTION;
    }
	public void setConfigurationProperty(ConfigProperty configurationProperty) {
		this.configurationProperty = configurationProperty;
	}
}
