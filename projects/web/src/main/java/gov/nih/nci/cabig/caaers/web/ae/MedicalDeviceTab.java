package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.DefaultInputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory;
import gov.nih.nci.cabig.caaers.domain.Availability;
import gov.nih.nci.cabig.caaers.domain.DeviceOperator;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;

import java.util.Map;
import java.util.Arrays;

/**
 * @author Krikor Krumlian
 */
public class MedicalDeviceTab extends AeTab {

    public MedicalDeviceTab() {
        super("Medical Device", "Medical Device", "ae/medicalDevice");


    }

    @Override
    public Map<String, InputFieldGroup> createFieldGroups(ExpeditedAdverseEventInputCommand command) {
    	//-
    	InputFieldGroup allFields = new DefaultInputFieldGroup("desc");
        String baseProp = "aeReport.medicalDevice";

        allFields.getFields().add(InputFieldFactory.createTextField(baseProp + ".brandName", "Brand name", false));
        allFields.getFields().add(InputFieldFactory.createTextField(baseProp + ".commonName", "Common name", false));
        allFields.getFields().add(InputFieldFactory.createTextField(baseProp + ".deviceType", "Device type", false));

        allFields.getFields().add(InputFieldFactory.createTextField(baseProp + ".manufacturerName", "Manufacturer name", false));
        allFields.getFields().add(InputFieldFactory.createTextField(baseProp + ".manufacturerCity", "Manufacturer city", false));
        allFields.getFields().add(InputFieldFactory.createTextField(baseProp + ".manufacturerState", "Manufacturer state", false));

        allFields.getFields().add(InputFieldFactory.createTextField(baseProp + ".modelNumber", "Model number", false));
        allFields.getFields().add(InputFieldFactory.createTextField(baseProp + ".lotNumber", "Lot number", false));
        allFields.getFields().add(InputFieldFactory.createTextField(baseProp + ".catalogNumber", "Catalog number", false));

        allFields.getFields().add(InputFieldFactory.createDateField(
                baseProp + ".expirationDate", "Expiration date",  false));
        allFields.getFields().add(InputFieldFactory.createTextField(baseProp + ".serialNumber", "Serial number", false));
        allFields.getFields().add(InputFieldFactory.createTextField(baseProp + ".otherNumber", "Other number", false));

        allFields.getFields().add(InputFieldFactory.createSelectField(baseProp + ".deviceOperator", "Device operator", false,
                InputFieldFactory.collectOptions(Arrays.asList(DeviceOperator.values()), null, "displayName")));

        allFields.getFields().add(InputFieldFactory.createTextField(baseProp + ".otherDeviceOperator", "Other device operator", false));

        allFields.getFields().add(InputFieldFactory.createDateField(
                baseProp + ".implantedDate", "If implanted give a date",  false));
        allFields.getFields().add(InputFieldFactory.createDateField(
                baseProp + ".explantedDate", "IF explanted give a date",  false));

        allFields.getFields().add(InputFieldFactory.createSelectField(baseProp + ".deviceReprocessed", "Device reprocessed", false,
                InputFieldFactory.collectOptions(Arrays.asList(Availability.values()), null, "displayName")));

        allFields.getFields().add(InputFieldFactory.createTextField(baseProp + ".reprocessorName", " Reprocessor name", false));
        allFields.getFields().add(InputFieldFactory.createTextField(baseProp + ".reprocessorAddress", " Reprocessor address", false));

        allFields.getFields().add(InputFieldFactory.createSelectField(baseProp + ".evaluationAvailability", "Evaluation availability", false,
                InputFieldFactory.collectOptions(Arrays.asList(Availability.values()), null, "displayName")));

        allFields.getFields().add(InputFieldFactory.createDateField(
                baseProp + ".returnedDate", "Returned date",  false));
    	//-
        return createFieldGroupMap(Arrays.asList(allFields));
    }

    @Override
    public ExpeditedReportSection section() {
    	return ExpeditedReportSection.MEDICAL_DEVICE_SECTION;
    }
}
