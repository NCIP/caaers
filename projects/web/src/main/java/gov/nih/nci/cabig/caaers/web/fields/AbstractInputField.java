package gov.nih.nci.cabig.caaers.web.fields;

import gov.nih.nci.cabig.caaers.web.fields.validators.FieldValidator;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.validation.Errors;

/**
 * @author Rhett Sutphin
 */
public abstract class AbstractInputField implements InputField {
    private String displayName;

    private String propertyName;

    private boolean required;

    private boolean mandatory;

    private Map<String, Object> attributes;

    private FieldValidator[] validators;

    protected AbstractInputField() {
        this.attributes = new LinkedHashMap<String, Object>();
    }

    protected AbstractInputField(String propertyName, String displayName, boolean required) {
        this();
        this.displayName = displayName;
        this.propertyName = propertyName;
        if (required) this.validators = new FieldValidator[]{FieldValidator.NOT_NULL_VALIDATOR};
    }

    protected AbstractInputField(String propertyName, String displayName,
                                 FieldValidator... validators) {
        this();
        this.displayName = displayName;
        this.propertyName = propertyName;
        this.validators = validators;
    }

    /**
     * This base implementation does a simple not-null check if the field is required.
     */
    public void validate(BeanWrapper commandBean, Errors errors) {
        if (validators == null) return;
        for (FieldValidator validator : validators) {
            if (!validator.isValid(commandBean.getPropertyValue(this.getPropertyName()))) {
                errors.rejectValue(this.getPropertyName(), "REQUIRED", validator.getMessagePrefix() + " " + this.getDisplayName());
                return;
            }
        }
    }

    /**
     * Helper so that other InputField implementations can easily implement requiredness validation
     * just like this class.
     */
    public static void validateRequired(InputField field, BeanWrapper commandBean, Errors errors) {
        if (field.isRequired() && isEmpty(field, commandBean)) {
            errors.rejectValue(field.getPropertyName(), "REQUIRED", "Missing "
                    + field.getDisplayName());
        }
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

                    validatorClassNameBuffer.append(String.format("&&%s", validator.getValidatorCSSClassName()));

                }
            }
        }
        if (getCategory() != null && (getCategory().equals(Category.TEXT) || getCategory().equals(Category.TEXTAREA))) {
            if (getValidators() == null || getValidators().length == 0) {
                validatorClassNameBuffer.append("validate-MAXLENGTH2000");
            } else {
                validatorClassNameBuffer.append("&&MAXLENGTH2000");
            }


        }


        return validatorClassNameBuffer.toString();
    }


    // ////OBJECT METHODS

    @Override
    public String toString() {
        return new StringBuilder(getClass().getSimpleName()).append("[propertyName=").append(
                getPropertyName()).append("; category=").append(getCategoryName()).append(
                ']').toString();
    }
}
