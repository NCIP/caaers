package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.web.tabbedflow.Tab;

import java.util.Map;
import java.util.LinkedHashMap;

import org.springframework.validation.Errors;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

/**
 * @author Rhett Sutphin
*/
public class AeTab extends Tab<AdverseEventInputCommand> {
    private Map<String, InputFieldGroup> fieldGroups = new LinkedHashMap<String, InputFieldGroup>();

    public AeTab(String longTitle, String shortTitle, String viewName) {
        super(longTitle, shortTitle, viewName);
        initFields();
    }

    /**
     * Callback allowing subclasses to initialize the static fieldGroups collection.
     * @see #createFieldGroups
     */
    protected void initFields() {
    }

    /**
     * Template method allowing subclasses to generate their own field group maps.  This may be
     * necessary if the fields are dependent on the actual data in the command.  The default
     * implementation just returns the statically configured fieldGroups map.
     *
     * @param command
     * @return
     * @see gov.nih.nci.cabig.caaers.web.ae.RepeatingFieldGroupFactory
     * @see #getFieldGroups
     */
    protected Map<String, InputFieldGroup> createFieldGroups(AdverseEventInputCommand command) {
        return getFieldGroups();
    }

    @Override
    public Map<String, Object> referenceData(AdverseEventInputCommand command) {
        Map<String, Object> refdata = referenceData();
        refdata.put("fieldGroups", createFieldGroups(command));
        return refdata;
    }

    @Override
    public void validate(AdverseEventInputCommand command, Errors errors) {
        super.validate(command, errors);
        BeanWrapper commandBean = new BeanWrapperImpl(command);
        for (InputFieldGroup fieldGroup : getFieldGroups().values()) {
            for (InputField field : fieldGroup.getFields()) {
                Object propValue = commandBean.getPropertyValue(field.getPropertyName());
                if (field.isRequired() && propValue == null) {
                    errors.rejectValue(field.getPropertyName(), "REQUIRED", "Missing " + field.getDisplayName());
                }
            }
        }
    }

    /**
     * Convenience method to add a field to the named group.  If the
     * group does not already exist, it will be created as a {@link DefaultInputFieldGroup}
     * @param group
     * @param field
     */
    protected void addField(String group, InputField field) {
        if (!getFieldGroups().containsKey(group)) {
            fieldGroups.put(group, new DefaultInputFieldGroup(group));
        }
        fieldGroups.get(group).getFields().add(field);
    }

    protected Map<String, InputFieldGroup> getFieldGroups() {
        return fieldGroups;
    }
}
