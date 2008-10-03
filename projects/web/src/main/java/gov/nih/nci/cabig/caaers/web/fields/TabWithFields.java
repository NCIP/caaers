package gov.nih.nci.cabig.caaers.web.fields;

import gov.nih.nci.cabig.caaers.tools.spring.tabbedflow.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.validation.Errors;

/**
 * The central class in the <code>fields</code> package. Subclasses may provide a map of
 * {@link InputFieldGroup}s. This map will be provided to the view (in the request attribute
 * <code>fieldGroups</code>). It will also be used to to basic (required/not required) field
 * validation.
 * 
 * @see InputField
 * @see InputFieldGroup
 * @author Rhett Sutphin
 * @author Ion C. Olaru
 */
public abstract class TabWithFields<C> extends InPlaceEditableTab<C> {
    private boolean autoPopulateHelpKey;

    private List<String> helpExclusionList;

    public TabWithFields(String longTitle, String shortTitle, String viewName) {
        super(longTitle, shortTitle, viewName);
        helpExclusionList = new ArrayList<String>();
    }

    /**
     * Return the field groups needed for the given command. {@link InputFieldGroupMap} can be
     * helpful for this, but is not required.
     * 
     * @param command
     * @see RepeatingFieldGroupFactory
     */
    public abstract Map<String, InputFieldGroup> createFieldGroups(C command);
    
    /**
     * Tabs should not override this anymore.
     */
    @Override
    public final Map<String, Object> referenceData() {
    	return super.referenceData();
    }
    
    /**
     * Tabs should not override this anymore.
     */
    @Override
    public Map<String,Object> referenceData(C command) {
        Map<String, Object> refdata = super.referenceData(command);
        Map<String, InputFieldGroup> groupMap = createFieldGroups(command);
        if (isAutoPopulateHelpKey()) populateHelpAttributeOnFields(groupMap); // to populate the help keys
        refdata.put("fieldGroups", groupMap);
        return refdata;
    };
    
    @Override
    public Map<String, Object> referenceData(HttpServletRequest request, C command) {
        return this.referenceData(command);
    }

    @Override
    public final void validate(C command, Errors errors) {
        super.validate(command, errors);
        BeanWrapper commandBean = new BeanWrapperImpl(command);
        Map<String, InputFieldGroup> fieldGroups = createFieldGroups(command);
        for (InputFieldGroup fieldGroup : fieldGroups.values()) {
            for (InputField field : fieldGroup.getFields()) {
                field.validate(commandBean, errors);
            }
        }
        validate(command, commandBean, fieldGroups, errors);
    }

    /**
     * Template method for subclasses to provide additional non-field self-validation.
     */
    protected void validate(C command, BeanWrapper commandBean,
                    Map<String, InputFieldGroup> fieldGroups, Errors errors) {
    }

    /**
     * This functions sets the HELP key in the attribute map associated to an InputField. The
     * default logic of deriving HELP key is "viewName" + "." + propertyName, where <code>/</code>
     * character in viewName is replaced with <code>. (dot) </code> and array notations<code>[x]</code>
     * in propertyName removed .
     */
    protected void populateHelpAttributeOnFields(Map<String, InputFieldGroup> groupMap) {

        if (groupMap == null || groupMap.isEmpty()) return;
        for (InputFieldGroup group : groupMap.values()) {
            for (InputField field : group.getFields()) {
                setHelpKeyAttribute(field);
            }
        }
    }

    final protected void setHelpKeyAttribute(InputField field) {
        if (!autoPopulateHelpKey) return;
        String helpKeyPrefix = (getViewName() != null) ? getViewName().replaceAll("/", ".") : "";
        String[] nameSubset = null;
        nameSubset = field.getPropertyName().split("\\.");
        if (helpExclusionList.contains(nameSubset[nameSubset.length - 1])) {
            return;
        }
        field.getAttributes().put(InputField.HELP, helpKeyPrefix + "."+ field.getPropertyName().replaceAll("(\\[\\d+\\])", ""));
    }

    // /BEAN PROPERTIES

    public boolean isAutoPopulateHelpKey() {
        return autoPopulateHelpKey;
    }

    public void setAutoPopulateHelpKey(boolean autoPopulateHelpKey) {
        this.autoPopulateHelpKey = autoPopulateHelpKey;
    }

    public void addHelpKeyExclusion(String... properties) {
        helpExclusionList.addAll(Arrays.asList(properties));
    }

}
