package gov.nih.nci.cabig.caaers.web.ae;

import static gov.nih.nci.cabig.caaers.web.fields.BaseSelectField.collectOptions;
import gov.nih.nci.cabig.caaers.web.fields.DefaultTextField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.DefaultInputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.DefaultDateField;
import gov.nih.nci.cabig.caaers.web.fields.LongSelectField;
import gov.nih.nci.cabig.caaers.domain.Availability;
import gov.nih.nci.cabig.caaers.domain.DeviceOperator;

import java.util.Map;
import java.util.Arrays;

//import com.sun.tools.javac.tree.Tree;

/**
 * @author Krikor Krumlian
 */
public class MedicalDeviceTab extends AeTab {
    private InputFieldGroup allFields;

    public MedicalDeviceTab() {
        super("Medical Device", "Medical Device", "ae/medicalDevice");
        allFields = new DefaultInputFieldGroup("desc");
        String baseProp = "aeReport.medicalDevice";
        
        allFields.getFields().add(new DefaultTextField(baseProp + ".brandName", "Brand Name", false));
        allFields.getFields().add(new DefaultTextField(baseProp + ".commonName", "Common Name", false));
        allFields.getFields().add(new DefaultTextField(baseProp + ".deviceType", "Device Type", false));
        
        allFields.getFields().add(new DefaultTextField(baseProp + ".manufacturerName", "Manufacturer Name", false));
        allFields.getFields().add(new DefaultTextField(baseProp + ".manufacturerCity", "Manufacturer City", false));
        allFields.getFields().add(new DefaultTextField(baseProp + ".manufacturerState", "Manufacturer State", false));
        
        allFields.getFields().add(new DefaultTextField(baseProp + ".modelNumber", "Model Number", false));
        allFields.getFields().add(new DefaultTextField(baseProp + ".lotNumber", "Lot Number", false));
        allFields.getFields().add(new DefaultTextField(baseProp + ".catalogNumber", "Catalog Number", false));
        
        allFields.getFields().add(new DefaultDateField(
                baseProp + ".expirationDate", "Expiration Date",  false));
        allFields.getFields().add(new DefaultTextField(baseProp + ".serialNumber", "Serial Number", false));
        allFields.getFields().add(new DefaultTextField(baseProp + ".otherNumber", "Other Number", false));
        
        allFields.getFields().add(new LongSelectField(baseProp + ".deviceOperator", "Device Operator", false,
                collectOptions(Arrays.asList(DeviceOperator.values()), null, "displayName")));
        
        allFields.getFields().add(new DefaultTextField(baseProp + ".otherDeviceOperator", "Other Device Operator", false));
        
        allFields.getFields().add(new DefaultDateField(
                baseProp + ".implantedDate", "If Implanted Give a Date",  false));
        allFields.getFields().add(new DefaultDateField(
                baseProp + ".explantedDate", "IF Explanted Give a Date",  false));
        
        allFields.getFields().add(new LongSelectField(baseProp + ".deviceReprocessed", "Device Reprocessed", false,
                collectOptions(Arrays.asList(Availability.values()), null, "displayName")));
        
        allFields.getFields().add(new DefaultTextField(baseProp + ".reprocessorName", " Reprocessor Name", false));
        allFields.getFields().add(new DefaultTextField(baseProp + ".reprocessorAddress", " Reprocessor Address", false));
        
        allFields.getFields().add(new LongSelectField(baseProp + ".evaluationAvailability", "Evaluation Availability", false,
                collectOptions(Arrays.asList(Availability.values()), null, "displayName")));
        
        allFields.getFields().add(new DefaultDateField(
                baseProp + ".returnedDate", "Returned Date",  false));

    }

    @Override
    public Map<String, InputFieldGroup> createFieldGroups(ExpeditedAdverseEventInputCommand command) {
        return createFieldGroupMap(Arrays.asList(allFields));
    }
}
