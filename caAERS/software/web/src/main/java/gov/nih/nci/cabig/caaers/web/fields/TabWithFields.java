/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.fields;

import gov.nih.nci.cabig.caaers.security.CaaersSecurityFacade;
import gov.nih.nci.cabig.caaers.tools.spring.tabbedflow.InPlaceEditableTab;

import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.context.MessageSource;
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
 * @author Biju Joseph
 */
public abstract class TabWithFields<C> extends InPlaceEditableTab<C> {
    private boolean autoPopulateHelpKey;
    protected MessageSource messageSource;
    protected FieldDecorator[] fieldDecorators;

    public TabWithFields(String longTitle, String shortTitle, String viewName) {
        super(longTitle, shortTitle, viewName);
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
     * Will obtain the field groups for this tab. 
     * @param command
     * @return
     */
    public final Map<String, InputFieldGroup> fetchFieldGroups(C command){
       Map<String, InputFieldGroup> groupMap = createFieldGroups(command);
       if (isAutoPopulateHelpKey()) populateHelpAttributeOnFields(groupMap); // to populate the help keys
       decorateFieldGroups(groupMap);
       return groupMap;
    }
    
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
        Map<String, InputFieldGroup> groupMap = fetchFieldGroups(command);

        refdata.put("fieldGroups", groupMap);
        return refdata;
    };
    
    @Override
    public Map<String, Object> referenceData(HttpServletRequest request, C command) {
        return this.referenceData(command);
    }

    /**
     * @author Ion C. Olaru
     * This will decorate every field in the FieldGroups using decorators passed to the Tab.
     *
     * */
    public void decorateFieldGroups(Map<String, InputFieldGroup> map) {
        if (fieldDecorators == null || fieldDecorators.length == 0) return;
        for (InputFieldGroup fieldGroup : map.values()) {
            for (InputField field : fieldGroup.getFields()) {
                for (FieldDecorator fd : fieldDecorators) {
                    fd.decorate(field);
                }
            }
        }
    }

    /**
     *
     * Validate the FieldGroups
     * decoration should happen prior to actual validation,
     * since validation may be skipped if the field is readonly decorated
     *  
     * */
    @Override
    public final void validate(C command, Errors errors) {
        super.validate(command, errors);
        BeanWrapper commandBean = new BeanWrapperImpl(command);
        Map<String, InputFieldGroup> fieldGroups = fetchFieldGroups(command);
        for (InputFieldGroup fieldGroup : fieldGroups.values()) {
            for (InputField field : fieldGroup.getFields()) {
                if (field.isValidateable()) field.validate(commandBean, errors);
            }
        }
        validate(command, commandBean, fieldGroups, errors);
    }

    /**
     * Template method for subclasses to provide additional non-field self-validation.
     */
    protected void validate(C command, BeanWrapper commandBean, Map<String, InputFieldGroup> fieldGroups, Errors errors) {
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
        field.getAttributes().put(InputField.HELP, helpKeyPrefix + "."+ field.getPropertyName().replaceAll("(\\[\\d+\\])", ""));
    }

    // /BEAN PROPERTIES

    public boolean isAutoPopulateHelpKey() {
        return autoPopulateHelpKey;
    }

    public void setAutoPopulateHelpKey(boolean autoPopulateHelpKey) {
        this.autoPopulateHelpKey = autoPopulateHelpKey;
    }

    public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
    public MessageSource getMessageSource() {
		return messageSource;
	}
    
    protected String getMessage(String code, String defaultMsg, Object...objects){
    	return messageSource.getMessage(code, objects, defaultMsg, Locale.getDefault());
    }

    /**
     * 
     * Define the decorators list taht has to be applied to Tab's fields.
     * This method will be called from the constructor of the Tab
     *  
     * */
    public void addFieldDecorators(FieldDecorator ... fd) {
        this.fieldDecorators = fd;
    }

}
