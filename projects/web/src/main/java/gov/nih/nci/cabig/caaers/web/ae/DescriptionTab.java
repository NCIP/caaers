package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.DefaultInputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.DefaultTextArea;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.DefaultSelectField;
import gov.nih.nci.cabig.caaers.web.fields.BaseSelectField;
import gov.nih.nci.cabig.caaers.web.fields.DefaultDateField;
import gov.nih.nci.cabig.caaers.web.fields.BooleanSelectField;
import gov.nih.nci.cabig.caaers.domain.PostAdverseEventStatus;

import java.util.Map;
import java.util.Arrays;
import java.util.LinkedHashMap;

import com.sun.tools.javac.tree.Tree;

/**
 * @author Rhett Sutphin
 */
public class DescriptionTab<C extends AdverseEventInputCommand> extends AeTab<C> {
    private InputFieldGroup allFields;

    public DescriptionTab() {
        super("Event and response description", "Description", "ae/description");
        allFields = new DefaultInputFieldGroup("desc");
        String baseProp = "aeReport.responseDescription";

        DefaultTextArea desc = new DefaultTextArea(baseProp + ".eventDescription",
            "Description", true);
        desc.getAttributes().put(InputField.DETAILS,
            "Describe the adverse event(s) and any action prompted by them");
        allFields.getFields().add(desc);

        Map<Object, Object> statusOpts = new LinkedHashMap<Object, Object>();
        statusOpts.put("", "Please select");
        statusOpts.putAll(BaseSelectField.collectOptions(
            Arrays.asList(PostAdverseEventStatus.values()), null, "displayName"));
        allFields.getFields().add(new DefaultSelectField(
            baseProp + ".presentStatus", "Present status", false,
            statusOpts));

        allFields.getFields().add(new DefaultDateField(
            baseProp + ".recoveryDate", "Date of recovery or death",  false));
        allFields.getFields().add(new BooleanSelectField(baseProp + ".retreated",
            "Has the particpant been re-treated?", false));
        DefaultDateField removedDateField = new DefaultDateField(baseProp + ".dateRemovedFromProtocol",
            "Date removed from protocol", false);
        removedDateField.getAttributes().put(InputField.DETAILS,
            "If the participant was removed from the protocol, enter the date here.  Otherwise, leave it blank.");
        allFields.getFields().add(removedDateField);
    }

    @Override
    public Map<String, InputFieldGroup> createFieldGroups(C command) {
        return createFieldGroupMap(Arrays.asList(allFields));
    }
}
