package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.utils.ConfigProperty;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldAttributes;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory;
import gov.nih.nci.cabig.caaers.web.utils.WebUtils;
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
        super("Medical Device", ExpeditedReportSection.MEDICAL_DEVICE_SECTION.getDisplayName(),
                        "ae/medicalDevice");
    }

    @Override
    protected void createFieldGroups(AeInputFieldCreator creator,
                    ExpeditedAdverseEventInputCommand command) {

        InputField brandName = InputFieldFactory.createTextField("brandName", "Brand name", false);
        InputFieldAttributes.setSize(brandName, 45);

        InputField commonName = InputFieldFactory.createTextField("commonName", "Common name",
                        false);
        InputFieldAttributes.setSize(commonName, 45);

        InputField deviceType = InputFieldFactory.createTextField("deviceType", "Device type",
                        false);
        InputFieldAttributes.setSize(deviceType, 45);

        InputField manName = InputFieldFactory.createTextField("manufacturerName",
                        "Manufacturer name", false);
        InputFieldAttributes.setSize(manName, 45);

        InputField manCity = InputFieldFactory.createTextField("manufacturerCity",
                        "Manufacturer city", false);
        InputFieldAttributes.setSize(manCity, 45);

        InputField manState = InputFieldFactory.createSelectField("manufacturerState",
                        "Manufacturer state", false, WebUtils.collectOptions(
                                        configurationProperty.getMap().get("stateRefData"), "code",
                                        "desc", "Please Select"));
        InputFieldAttributes.setSize(manState, 45);

        InputField modelNumber = InputFieldFactory.createTextField("modelNumber", "Model number",
                        false);

        InputField otherDeviceOperator = InputFieldFactory.createTextField("otherDeviceOperator",
                        "Other device operator", false);
        InputFieldAttributes.setSize(otherDeviceOperator, 45);

        InputField reprocessorName = InputFieldFactory.createTextField("reprocessorName",
                        " Reprocessor name", false);
        InputFieldAttributes.setSize(reprocessorName, 45);
        reprocessorName.getAttributes().put(InputField.HELP,
                        "ae.medicalDevice.aeReport.medicalDevices.reprocessorName");

        InputField reprocessorAddress = InputFieldFactory.createTextField("reprocessorAddress",
                        " Reprocessor address", false);
        InputFieldAttributes.setSize(reprocessorAddress, 45);

        InputField deviceReprocessedField = InputFieldFactory.createSelectField(
                        "deviceReprocessed", "Device reprocessed", false, WebUtils
                                        .collectOptions(Arrays.asList(Availability.values()), null,
                                                        "displayName"));
        deviceReprocessedField.getAttributes().put(InputField.HELP,
                        "ae.medicalDevice.aeReport.medicalDevices.deviceReprocessed");

        InputField evaluationAvailabilityField = InputFieldFactory.createSelectField(
                        "evaluationAvailability", "Evaluation availability", false,
                        WebUtils.collectOptions(Arrays.asList(Availability.values()),
                                        null, "displayName"));
        evaluationAvailabilityField.getAttributes().put(InputField.HELP,
                        "ae.medicalDevice.aeReport.medicalDevices.evaluationAvailability");

        creator
                        .createRepeatingFieldGroup("medicalDevice", "medicalDevices",
                                        new SimpleNumericDisplayNameCreator("Medical device"),
                                        brandName, commonName, deviceType, manName, manCity,
                                        manState, modelNumber, InputFieldFactory.createTextField(
                                                        "lotNumber", "Lot number", false),
                                        InputFieldFactory.createTextField("catalogNumber",
                                                        "Catalog number", false),

                                        InputFieldFactory.createDateField("expirationDate",
                                                        "Expiration date", false),
                                        InputFieldFactory.createTextField("serialNumber",
                                                        "Serial number", false), InputFieldFactory
                                                        .createTextField("otherNumber",
                                                                        "Other number", false),

                                        InputFieldFactory.createSelectField("deviceOperator",
                                                        "Device operator", false,
                                                        WebUtils.collectOptions(Arrays
                                                                        .asList(DeviceOperator
                                                                                        .values()),
                                                                        null, "displayName")),

                                        otherDeviceOperator,

                                        InputFieldFactory.createDateField("implantedDate",
                                                        "If implanted, enter a date", false),
                                        InputFieldFactory.createDateField("explantedDate",
                                                        "IF explanted, enter a date", false),

                                        deviceReprocessedField,

                                        reprocessorName, reprocessorAddress,

                                        evaluationAvailabilityField,

                                        InputFieldFactory.createDateField("returnedDate",
                                                        "Returned date", false));
    }

    @Override
    public ExpeditedReportSection[] section() {
        return new ExpeditedReportSection[] {ExpeditedReportSection.MEDICAL_DEVICE_SECTION};
    }

    public void setConfigurationProperty(ConfigProperty configurationProperty) {
        this.configurationProperty = configurationProperty;
    }
}
