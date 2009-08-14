package gov.nih.nci.cabig.caaers.web.fields.validators;

import org.apache.commons.lang.math.NumberUtils;

/**
 * Validating whether the value is an alphanumeric one or not
 * @author Ion C. Olaru
 *
 */
public class AlphanumericValidator extends FieldValidator {

	@Override
    public String getMessagePrefix() {
        return "Incorrect alphanumeric value";
    }

    @Override
    public String getValidatorCSSClassName() {
        return "ALPHANUMERIC";
    }

    /*
    * The input string is evaluated as valid if it contains only Alphanumeric symbols and spaces
    * @return boolean
    * @input Object - usually this would be a String 
    * */
    @Override
    public boolean isValid(Object fieldValue) {
        if (fieldValue != null) return fieldValue.toString().matches("^[a-zA-Z0-9_ ]*$");
        return true; 
    }
}