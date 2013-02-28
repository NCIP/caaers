/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.fields;

import gov.nih.nci.cabig.caaers.validation.fields.validators.FieldValidator;
import gov.nih.nci.cabig.caaers.validation.fields.validators.TextSizeValidator;
import gov.nih.nci.cabig.caaers.web.utils.WebUtils;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.validation.Errors;

/**
 * @author Rhett Sutphin
 * @author Ion C. Olaru
 * 
 */
public abstract class AbstractInputField implements InputField {

    private String displayName;
    private String propertyName;
    private Map<String, Object> attributes;
    private FieldValidator[] validators;

    protected boolean readable;
    protected boolean modifiable;
    protected String displayTextProperty;

    protected String updatePrivilege;
    protected String readPrivilege;

   

    protected AbstractInputField() {
        this.attributes = new LinkedHashMap<String, Object>();
        this.readable = true;
        this.modifiable = true;
    }

    protected AbstractInputField(String propertyName, String displayName, boolean required) {
        this(propertyName, displayName, null, required, (FieldValidator[])null);
    }

    protected AbstractInputField(String propertyName, String displayName, boolean required, String labelProperty) {
        this(propertyName, displayName, labelProperty, required, null);
    }

    protected AbstractInputField(String propertyName, String displayName, FieldValidator... validators) {
        this(propertyName, displayName, null, null, validators);
    }

    protected AbstractInputField(String propertyName, String displayName, String labelProperty, FieldValidator... validators) {
        this(propertyName, displayName, labelProperty, null, validators);
    }

    protected AbstractInputField(String propertyName, String displayName, String labelProperty, Boolean required, FieldValidator... validators) {
        this();
        this.displayName = displayName;
        this.propertyName = propertyName;
        this.displayTextProperty = propertyName;
        
        if (validators != null && validators.length > 0) this.validators = validators;
        if (required != null && required) {
            if (this.validators == null) this.validators = new FieldValidator[] {FieldValidator.NOT_NULL_VALIDATOR};
            else {
                FieldValidator[] fv = new FieldValidator[this.validators.length + 1];
                for (byte i=0; i < this.validators.length; i++) {
                    fv[i] = this.validators[i];
                }
                fv[validators.length] = FieldValidator.NOT_NULL_VALIDATOR;
                this.validators = fv;
            }
        }
        if (labelProperty != null) InputFieldAttributes.setI18NLabelProperty(this, labelProperty);
    }

    /**
     * This base implementation does a simple not-null check if the field is required.
     */
    public void validate(BeanWrapper commandBean, Errors errors) {
        AbstractInputField.validate(this, commandBean, errors);
    }

    public static void validate(InputField field , BeanWrapper commandBean, Errors errors){
         if(field.getValidators() == null || !field.isValidateable()) return ;
        for (FieldValidator validator : field.getValidators()) {
            if (!validator.isValid(commandBean.getPropertyValue(field.getPropertyName()))) {
                errors.rejectValue(field.getPropertyName(), "REQUIRED", "<b>" + validator.getMessagePrefix() + ":</b> &quot;" + field.getDisplayName() +"&quot;");
                return;
            }
        }
    }

    /**
     * Helper so that other InputField implementations can easily implement requiredness validation
     * just like this class.
     */
    public static void validateRequired(InputField field, BeanWrapper commandBean, Errors errors) {
        if(!field.isValidateable()) return;
        
        if (field.isRequired() && isEmpty(field, commandBean)) {
            errors.rejectValue(field.getPropertyName(), "REQUIRED", "Missing "
                    + field.getDisplayName());
        }
    }

    public FieldValidator  getValidatorOfType(Class<? extends FieldValidator> klass) {
        if(validators == null) return null;
        for(FieldValidator v : validators){
            if(v.getClass().equals(klass)) return v;
        }
        return null;
    }

    public static boolean isEmpty(InputField field, BeanWrapper commandBean) {
        return commandBean.getPropertyValue(field.getPropertyName()) == null;
    }

    public abstract Category getCategory();

    public String getCategoryName() {
        return getCategory().name().toLowerCase();
    }

    public String getDisplayName() {
        return displayName == null ? propertyName : displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public boolean isRequired() {
        return ArrayUtils.contains(validators, FieldValidator.NOT_NULL_VALIDATOR);
    }

    @Deprecated
    public void setRequired(boolean required) {
        if (!required) return;
        if (!ArrayUtils.contains(validators, FieldValidator.NOT_NULL_VALIDATOR)) {
            if (validators != null && validators.length > 0) {
                FieldValidator[] oldValidators = validators;
                validators = new FieldValidator[oldValidators.length + 1];
                System.arraycopy(oldValidators, 0, validators, 1, oldValidators.length);
            } else {
                validators = new FieldValidator[1];
            }
            validators[0] = FieldValidator.NOT_NULL_VALIDATOR; // this should be the first
        }
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public FieldValidator[] getValidators() {
        return validators;
    }
    
    public String getValidatorClassName() {
        StringBuffer validatorClassNameBuffer = new StringBuffer("");


        if (getValidators() != null) {
            for (int i = 0; i < getValidators().length; i++) {
                FieldValidator validator = getValidators()[i];
                if (i == 0) {
                    validatorClassNameBuffer.append(String.format("validate-%s", validator.getValidatorCSSClassName()));

                } else {

                    validatorClassNameBuffer.append(String.format("$$%s", validator.getValidatorCSSClassName()));

                }
            }
        }
		if (getCategory() != null && (getCategory().equals(Category.TEXT) || getCategory().equals(Category.TEXTAREA))) {
            TextSizeValidator sizeValidator = (TextSizeValidator) getValidatorOfType(TextSizeValidator.class);
            validatorClassNameBuffer.append(validatorClassNameBuffer.length() > 0 ? "$$MAXLENGTH" : "validate-MAXLENGTH") ;
            validatorClassNameBuffer.append(sizeValidator == null ? 2000 : sizeValidator.getTextSize());
		}
        return validatorClassNameBuffer.toString();
    }

    

    public String getDisplayTextProperty() {
        return displayTextProperty;
    }

    public void setDisplayTextProperty(String displayTextProperty) {
        this.displayTextProperty = displayTextProperty;
    }

    /**
     * Will be true, if the property represented by this field can be modified.
     *
     * @return
     */
    public boolean isModifiable() {
        return modifiable;
    }

    public void setModifiable(boolean modifiable){
        this.modifiable = modifiable;
    }

    /**
     * Will be true if the property represented by this field can be read
     *
     * @return
     */
    public boolean isReadable() {
        return readable;
    }

    public void setReadable(boolean readable){
        this.readable = readable;
    }

    /**
     * Will return true, if the property represented by this field can be validated.
     *
     * @return
     */
    public boolean isValidateable() {
        return isModifiable() && isReadable(); 
    }

    /**
     * The privilege required to read this field.
     *
     * @return
     */
    public String getPrivilegeToRead() {
        return readPrivilege;
    }

    public void setPrivilegeToRead(String privilege) {
        readPrivilege = privilege;
    }


    /**
    * The security privilege needed to Modify this field
    *
    * @return
    */
    public String getPrivilegeToModify() {
        return updatePrivilege;
    }

    public void setPrivilegeToModify(String privilege) {
       this.updatePrivilege = privilege;
    }


    // ////OBJECT METHODS

    @Override
    public String toString() {
        return new StringBuilder(getClass().getSimpleName()).append("[propertyName=").append(
                getPropertyName()).append("; category=").append(getCategoryName()).append(
                ']').toString();
    }


}
