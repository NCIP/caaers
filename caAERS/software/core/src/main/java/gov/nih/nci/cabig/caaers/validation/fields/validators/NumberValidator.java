package gov.nih.nci.cabig.caaers.validation.fields.validators;

import org.apache.commons.lang.math.NumberUtils;



public class NumberValidator extends FieldValidator<NumberConstraint, Object> {

    @Override
    public String getMessagePrefix() {
        return "Invalid";
    }

    @Override
    public String getValidatorCSSClassName() {

        return "NUMERIC";
    }

    @Override
    public boolean isValid(Object fieldValue) {
        if (fieldValue != null) return NumberUtils.isNumber(fieldValue.toString());
        return true;
    }


}
