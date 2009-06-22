package gov.nih.nci.cabig.caaers.web.fields.validators;

import org.apache.commons.lang.math.NumberUtils;
/**
 * Will check if the number is decimal
 * @author Biju Joseph
 *
 */
public class DecimalValidator extends FieldValidator {

	@Override
    public String getMessagePrefix() {
        return "Incorrect";
    }

    @Override
    public String getValidatorCSSClassName() {

        return "DECIMAL";
    }

    @Override
    public boolean isValid(Object fieldValue) {
        if (fieldValue != null) return NumberUtils.isNumber(fieldValue.toString());
        return true;
    }
}
