package gov.nih.nci.cabig.caaers.web.fields;

import gov.nih.nci.cabig.ctms.web.tabs.Tab;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.validation.Errors;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * The central class in the <code>fields</code> package.  Subclasses
 * may provide a map of {@link InputFieldGroup}s.  This map will be provided to the view
 * (in the request attribute <code>fieldGroups</code>).  It will also be used to to
 * basic (required/not required) field validation.
 *
 * @see InputField
 * @see InputFieldGroup
 * @author Rhett Sutphin
 */
public abstract class TabWithFields<C> extends Tab<C> {
    public TabWithFields(String longTitle, String shortTitle, String viewName) {
        super(longTitle, shortTitle, viewName);
    }

    /**
     * Return the field groups needed for the given command.
     * {@link InputFieldGroupMap} can be helpful for this, but is not required.
     *
     * @param command
     * @see RepeatingFieldGroupFactory
     */
    public abstract Map<String, InputFieldGroup> createFieldGroups(C command);

    @Override
    public Map<String, Object> referenceData(C command) {
        Map<String, Object> refdata = referenceData();
        refdata.put("fieldGroups", createFieldGroups(command));
        return refdata;
    }

    /** Helper which subclasses can use to implement #createFieldGroupMap(command) if they are using a simple list */
    protected static Map<String, InputFieldGroup> createFieldGroupMap(List<InputFieldGroup> groups) {
        Map<String, InputFieldGroup> groupMap = new LinkedHashMap<String, InputFieldGroup>();
        for (InputFieldGroup group : groups) groupMap.put(group.getName(), group);
        return groupMap;
    }

    @Override
    public final void validate(C command, Errors errors) {
        super.validate(command, errors);
        BeanWrapper commandBean = new BeanWrapperImpl(command);
        Map<String, InputFieldGroup> fieldGroups = createFieldGroups(command);
        for (InputFieldGroup fieldGroup : fieldGroups.values()) {
            for (InputField field : fieldGroup.getFields()) {
                Object propValue = commandBean.getPropertyValue(field.getPropertyName());
                if (field.isRequired() && propValue == null) {
                    errors.rejectValue(field.getPropertyName(),
                        "REQUIRED", "Missing " + field.getDisplayName());
                }
            }
        }
        validate(command, commandBean, fieldGroups, errors);
    }

    /**
     * Template method for subclasses to provide additional self-validation.
     */
    protected void validate(
        C command, BeanWrapper commandBean,
        Map<String, InputFieldGroup> fieldGroups, Errors errors
    ) {
    }
}
