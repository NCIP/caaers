package gov.nih.nci.cabig.caaers.web.fields.validators;

import org.apache.commons.validator.GenericValidator;

public class EmailValidator extends FieldValidator {

    @Override
    public boolean isValid(Object fieldValue) {
        if (fieldValue == null) return true; // null email is considered as valid
        return GenericValidator.isEmail(fieldValue.toString());
    }

    @Override
    public String getMessagePrefix() {
        return "Incorrect";
    }

    public String getValidatorCSSClassName() {
        return "EMAIL";  //To change body of implemented methods use File | Settings | File Templates.
    }
}
