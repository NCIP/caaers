package gov.nih.nci.cabig.caaers.web.ae;

import static gov.nih.nci.cabig.caaers.web.fields.BaseSelectField.collectOptions;
import gov.nih.nci.cabig.caaers.web.fields.DefaultTextField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.DefaultInputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.DefaultDateField;
import gov.nih.nci.cabig.caaers.web.fields.DefaultSelectField;
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
        
        allFields.getFields().add(new DefaultTextField(baseProp + ".brandName", "Brand name", false));
        allFields.getFields().add(new DefaultTextField(baseProp + ".commonName", "Common name", false));
        allFields.getFields().add(new DefaultTextField(baseProp + ".deviceType", "Device type", false));
        
        allFields.getFields().add(new DefaultTextField(baseProp + ".manufacturerName", "Manufacturer name", false));
        allFields.getFields().add(new DefaultTextField(baseProp + ".manufacturerCity", "Manufacturer city", false));
        allFields.getFields().add(new DefaultTextField(baseProp + ".manufacturerState", "Manufacturer state", false));
        
        allFields.getFields().add(new DefaultTextField(baseProp + ".modelNumber", "Model number", false));
        allFields.getFields().add(new DefaultTextField(baseProp + ".lotNumber", "Lot number", false));
        allFields.getFields().add(new DefaultTextField(baseProp + ".catalogNumber", "Catalog number", false));
        
        allFields.getFields().add(new DefaultDateField(
                baseProp + ".expirationDate", "Expiration date",  false));
        allFields.getFields().add(new DefaultTextField(baseProp + ".serialNumber", "Serial number", false));
        allFields.getFields().add(new DefaultTextField(baseProp + ".otherNumber", "Other number", false));
        
        allFields.getFields().add(new DefaultSelectField(baseProp + ".deviceOperator", "Device operator", false,
                collectOptions(Arrays.asList(DeviceOperator.values()), null, "displayName")));
        
        allFields.getFields().add(new DefaultTextField(baseProp + ".otherDeviceOperator", "Other device operator", false));
        
        allFields.getFields().add(new DefaultDateField(
                baseProp + ".implantedDate", "If implanted give a date",  false));
        allFields.getFields().add(new DefaultDateField(
                baseProp + ".explantedDate", "IF explanted give a date",  false));
        
        allFields.getFields().add(new DefaultSelectField(baseProp + ".deviceReprocessed", "Device reprocessed", false,
                collectOptions(Arrays.asList(Availability.values()), null, "displayName")));
        
        allFields.getFields().add(new DefaultTextField(baseProp + ".reprocessorName", " Reprocessor name", false));
        allFields.getFields().add(new DefaultTextField(baseProp + ".reprocessorAddress", " Reprocessor address", false));
        
        allFields.getFields().add(new DefaultSelectField(baseProp + ".evaluationAvailability", "Evaluation availability", false,
                collectOptions(Arrays.asList(Availability.values()), null, "displayName")));
        
        allFields.getFields().add(new DefaultDateField(
                baseProp + ".returnedDate", "Returned date",  false));

    }

    @Override
    public Map<String, InputFieldGroup> createFieldGroups(ExpeditedAdverseEventInputCommand command) {
        return createFieldGroupMap(Arrays.asList(allFields));
    }
}
