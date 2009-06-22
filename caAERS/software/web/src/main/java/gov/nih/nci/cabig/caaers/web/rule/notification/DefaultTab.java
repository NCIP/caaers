package gov.nih.nci.cabig.caaers.web.rule.notification;

import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.rule.RuleInputCommand;
import gov.nih.nci.cabig.ctms.web.tabs.Tab;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * This class will be removed after the demo
 * 
 * @author Biju Joseph Created-on : May 23, 2007
 * @version %I%, %G%
 * @since 1.0
 */
public class DefaultTab extends Tab<RuleInputCommand> {

    private Map<String, InputFieldGroup> fieldGroups = new LinkedHashMap<String, InputFieldGroup>();

    public DefaultTab(String longTitle, String shortTitle, String viewName) {
        super(longTitle, shortTitle, viewName);
        initFields();
    }

    @Override
    public Map<String, Object> referenceData(RuleInputCommand command) {
        Map<String, Object> refdata = referenceData();
        refdata.put("fieldGroups", createFieldGroups(command));
        return refdata;
    }

    /**
     * Template method allowing subclasses to generate their own field group maps. This may be
     * necessary if the fields are dependent on the actual data in the command. The default
     * implementation just returns the statically configured fieldGroups map.
     * 
     * @param command
     * @return
     * @see gov.nih.nci.cabig.caaers.web.fields.RepeatingFieldGroupFactory
     * @see #getFieldGroups
     */
    protected Map<String, InputFieldGroup> createFieldGroups(RuleInputCommand command) {
        return getFieldGroups();
    }

    /**
     * Callback allowing subclasses to initialize the static fieldGroups collection.
     * 
     * @see #createFieldGroups
     */
    protected void initFields() {
    }

    /**
     * Convenience method to add a field to the named group. If the group does not already exist, it
     * will be created as a {@link DefaultInputFieldGroup}
     * 
     * @param group
     * @param field
     */
    protected void addField(String group, gov.nih.nci.cabig.caaers.web.fields.InputField field) {
        if (!getFieldGroups().containsKey(group)) {
            fieldGroups.put(group, new gov.nih.nci.cabig.caaers.web.fields.DefaultInputFieldGroup(
                            group));
        }
        fieldGroups.get(group).getFields().add(field);
    }

    protected Map<String, InputFieldGroup> getFieldGroups() {
        return fieldGroups;
    }

}