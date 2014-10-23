package gov.nih.nci.cabig.caaers.validation.fields.validators;

import org.apache.commons.validator.GenericValidator;

public class MultiEmailValidator extends FieldValidator<EmailConstraint, Object> {

    @Override
    public boolean isValid(Object fieldValue) {
        if (fieldValue == null) return true; // null email is considered as valid
        String str = fieldValue.toString();
        if(str.endsWith(",") || str.startsWith(",")) {
        	return false;
        }
        
        final String[] emails = str.split(",");
        boolean valid = true;
        for (String email : emails) {
        	valid = valid && GenericValidator.isEmail(email);
        	if(!valid) {
        		return false;
        	}
        }
        
        return valid;
    }

    @Override
    public String getMessagePrefix() {
        return "Invalid";
    }

    public String getValidatorCSSClassName() {
        return "MULTI_EMAIL";  //To change body of implemented methods use File | Settings | File Templates.
    }
}
