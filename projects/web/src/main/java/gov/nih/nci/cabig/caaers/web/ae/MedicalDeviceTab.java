package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory;
import gov.nih.nci.cabig.caaers.domain.Availability;
import gov.nih.nci.cabig.caaers.domain.DeviceOperator;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;

import java.util.Arrays;

/**
 * @author Krikor Krumlian
 */
public class MedicalDeviceTab extends AeTab {

    public MedicalDeviceTab() {
        super("Medical Device", ExpeditedReportSection.MEDICAL_DEVICE_SECTION.getDisplayName(), "ae/medicalDevice");
    }

    @Override
    protected void createFieldGroups(AeInputFieldCreator creator, ExpeditedAdverseEventInputCommand command) {
        creator.createRepeatingFieldGroup("medicalDevice", "medicalDevices",
            new SimpleNumericDisplayNameCreator("Medical device"),

            InputFieldFactory.createTextField("brandName", "Brand name", false),
            InputFieldFactory.createTextField("commonName", "Common name", false),
            InputFieldFactory.createTextField("deviceType", "Device type", false),

            InputFieldFactory.createTextField("manufacturerName", "Manufacturer name", false),
            InputFieldFactory.createTextField("manufacturerCity", "Manufacturer city", false),
            InputFieldFactory.createTextField("manufacturerState", "Manufacturer state", false),

            InputFieldFactory.createTextField("modelNumber", "Model number", false),
            InputFieldFactory.createTextField("lotNumber", "Lot number", false),
            InputFieldFactory.createTextField("catalogNumber", "Catalog number", false),

            InputFieldFactory.createDateField("expirationDate", "Expiration date",  false),
            InputFieldFactory.createTextField("serialNumber", "Serial number", false),
            InputFieldFactory.createTextField("otherNumber", "Other number", false),

            InputFieldFactory.createSelectField("deviceOperator", "Device operator", false, InputFieldFactory.collectOptions(Arrays.asList(DeviceOperator.values()), null, "displayName")),

            InputFieldFactory.createTextField("otherDeviceOperator", "Other device operator", false),

            InputFieldFactory.createDateField("implantedDate", "If implanted give a date",  false),
            InputFieldFactory.createDateField("explantedDate", "IF explanted give a date",  false),

            InputFieldFactory.createSelectField("deviceReprocessed", "Device reprocessed", false, InputFieldFactory.collectOptions(Arrays.asList(Availability.values()), null, "displayName")),

            InputFieldFactory.createTextField("reprocessorName", " Reprocessor name", false),
            InputFieldFactory.createTextField("reprocessorAddress", " Reprocessor address", false),

            InputFieldFactory.createSelectField("evaluationAvailability", "Evaluation availability", false, InputFieldFactory.collectOptions(Arrays.asList(Availability.values()), null, "displayName")),

            InputFieldFactory.createDateField("returnedDate", "Returned date",  false)
        );
    }

    @Override
    public ExpeditedReportSection section() {
        return ExpeditedReportSection.MEDICAL_DEVICE_SECTION;
    }
}
