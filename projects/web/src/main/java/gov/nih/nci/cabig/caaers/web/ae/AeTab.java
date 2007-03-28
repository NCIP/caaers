package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.web.tabbedflow.Tab;

import java.util.Map;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.validation.Errors;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

/**
 * @author Rhett Sutphin
*/
public abstract class AeTab extends Tab<AdverseEventInputCommand> {
    public AeTab(String longTitle, String shortTitle, String viewName) {
        super(longTitle, shortTitle, viewName);
    }

    /**
     * Return the field groups needed for the given command.
     *
     * @param command
     * @see gov.nih.nci.cabig.caaers.web.ae.RepeatingFieldGroupFactory
     */
    protected abstract List<InputFieldGroup> createFieldGroups(AdverseEventInputCommand command);

    @Override
    public Map<String, Object> referenceData(AdverseEventInputCommand command) {
        Map<String, Object> refdata = referenceData();
        refdata.put("fieldGroups", createFieldGroupMap(createFieldGroups(command)));
        return refdata;
    }

    private static Map<String, InputFieldGroup> createFieldGroupMap(List<InputFieldGroup> groups) {
        Map<String, InputFieldGroup> groupMap = new LinkedHashMap<String, InputFieldGroup>();
        for (InputFieldGroup group : groups) groupMap.put(group.getName(), group);
        return groupMap;
    }

    @Override
    public final void validate(AdverseEventInputCommand command, Errors errors) {
        super.validate(command, errors);
        BeanWrapper commandBean = new BeanWrapperImpl(command);
        List<InputFieldGroup> fieldGroups = createFieldGroups(command);
        for (InputFieldGroup fieldGroup : fieldGroups) {
            for (InputField field : fieldGroup.getFields()) {
                Object propValue = commandBean.getPropertyValue(field.getPropertyName());
                if (field.isRequired() && propValue == null) {
                    errors.rejectValue(field.getPropertyName(),
                        "REQUIRED", "Missing " + field.getDisplayName());
                }
            }
        }
        validate(command, commandBean, createFieldGroupMap(fieldGroups), errors);
    }

    /**
     * Template method for subclasses to provide additional self-validation.
     */
    protected void validate(
        AdverseEventInputCommand command, BeanWrapper commandBean,
        Map<String, InputFieldGroup> fieldGroups, Errors errors
    ) {
    }
}
