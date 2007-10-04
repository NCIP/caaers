package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.DefaultInputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;
import gov.nih.nci.cabig.caaers.web.fields.RepeatingFieldGroupFactory;
import gov.nih.nci.cabig.caaers.domain.Availability;
import gov.nih.nci.cabig.caaers.domain.DeviceOperator;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;

import java.util.Arrays;

/**
 * @author Krikor Krumlian
 */
public class MedicalDeviceTab extends AeTab {

    public MedicalDeviceTab() {
        super("Medical Device", "Device", "ae/medicalDevice");


    }

    @Override
    public InputFieldGroupMap createFieldGroups(ExpeditedAdverseEventInputCommand command) {
    	//-
    	InputFieldGroup allFields = new DefaultInputFieldGroup("desc");
    	RepeatingFieldGroupFactory fieldFactory = new RepeatingFieldGroupFactory("medicalDevice", "aeReport.medicalDevices");
        //String baseProp = "aeReport.medicalDevice";
    	
    	fieldFactory.setDisplayNameCreator(new RepeatingFieldGroupFactory.DisplayNameCreator() {
            public String createDisplayName(int index) {
                return "Medical device " + (index + 1);
            }
        });

        fieldFactory.addField(InputFieldFactory.createTextField("brandName", "Brand name", false));
        fieldFactory.addField(InputFieldFactory.createTextField("commonName", "Common name", false));
        fieldFactory.addField(InputFieldFactory.createTextField("deviceType", "Device type", false));

        fieldFactory.addField(InputFieldFactory.createTextField("manufacturerName", "Manufacturer name", false));
        fieldFactory.addField(InputFieldFactory.createTextField("manufacturerCity", "Manufacturer city", false));
        fieldFactory.addField(InputFieldFactory.createTextField("manufacturerState", "Manufacturer state", false));

        fieldFactory.addField(InputFieldFactory.createTextField("modelNumber", "Model number", false));
        fieldFactory.addField(InputFieldFactory.createTextField("lotNumber", "Lot number", false));
        fieldFactory.addField(InputFieldFactory.createTextField("catalogNumber", "Catalog number", false));

        fieldFactory.addField(InputFieldFactory.createDateField(
                "expirationDate", "Expiration date",  false));
        fieldFactory.addField(InputFieldFactory.createTextField("serialNumber", "Serial number", false));
        fieldFactory.addField(InputFieldFactory.createTextField("otherNumber", "Other number", false));

        fieldFactory.addField(InputFieldFactory.createSelectField("deviceOperator", "Device operator", false,
                InputFieldFactory.collectOptions(Arrays.asList(DeviceOperator.values()), null, "displayName")));

        fieldFactory.addField(InputFieldFactory.createTextField("otherDeviceOperator", "Other device operator", false));

        fieldFactory.addField(InputFieldFactory.createDateField(
                "implantedDate", "If implanted give a date",  false));
        fieldFactory.addField(InputFieldFactory.createDateField(
                "explantedDate", "IF explanted give a date",  false));

        fieldFactory.addField(InputFieldFactory.createSelectField("deviceReprocessed", "Device reprocessed", false,
                InputFieldFactory.collectOptions(Arrays.asList(Availability.values()), null, "displayName")));

        fieldFactory.addField(InputFieldFactory.createTextField("reprocessorName", " Reprocessor name", false));
        fieldFactory.addField(InputFieldFactory.createTextField("reprocessorAddress", " Reprocessor address", false));

        fieldFactory.addField(InputFieldFactory.createSelectField("evaluationAvailability", "Evaluation availability", false,
                InputFieldFactory.collectOptions(Arrays.asList(Availability.values()), null, "displayName")));

        fieldFactory.addField(InputFieldFactory.createDateField(
                "returnedDate", "Returned date",  false));
    	//-
        InputFieldGroupMap map = new InputFieldGroupMap();
        int mdCount = command.getAeReport().getMedicalDevices().size();
        map.addRepeatingFieldGroupFactory(fieldFactory, mdCount);
        return map;
        
    }

    @Override
    public ExpeditedReportSection section() {
    	return ExpeditedReportSection.MEDICAL_DEVICE_SECTION;
    }
}
