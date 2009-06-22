package gov.nih.nci.cabig.caaers.web.fields.validators;

import org.apache.commons.lang.math.NumberUtils;
/**
 * Will check whether the decimal number is within range
 * @author Biju Joseph
 *
 */
public class DecimalRangeValidator extends FieldValidator {

	double begin;
    double end;

    public DecimalRangeValidator(double begin, double end) {
        this.begin = begin;
        this.end = end;
    }

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
        double d = Double.parseDouble(fieldValue.toString());
        return d <= end && d >= begin;
    }

}
