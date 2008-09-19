package gov.nih.nci.cabig.caaers.web.fields.validators;

import org.apache.commons.lang.math.NumberUtils;

/**
 * Will validate wheter a number is in a give range or not.
 *
 * @author Biju Joseph
 */
public class NumberRangeValidator extends FieldValidator {
    int begin;
    int end;

    public NumberRangeValidator(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }

    @Override
    public String getMessagePrefix() {
        return "Incorrect";
    }

    public String getValidatorCSSClassName() {
        return "NUMERIC";
    }

    @Override
    public boolean isValid(Object fieldValue) {
        if (fieldValue == null) return true;
        if (!NumberUtils.isNumber(fieldValue.toString())) return false;
        int n = Integer.parseInt(fieldValue.toString());
        return (n < begin && n > end);
    }

}
