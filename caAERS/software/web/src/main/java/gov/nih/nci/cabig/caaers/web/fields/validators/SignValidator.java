package gov.nih.nci.cabig.caaers.web.fields.validators;

import org.apache.commons.lang.math.NumberUtils;

import java.text.DecimalFormatSymbols;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * @author Ion C. Olaru
 *
 */
public class SignValidator extends FieldValidator {

    boolean positive;

    /*
    * #param positive   if this is true then the fieldValue will be checked if it is positive, otherwise negative
    * */
    public SignValidator(boolean positive) {
        this.positive = positive;
    }

    @Override
    public String getMessagePrefix() {
        return "Incorrect sign";
    }

    @Override
    public String getValidatorCSSClassName() {
        if (!positive) return "NEGATIVE";
        else return "POSITIVE";
    }

    @Override
    public boolean isValid(Object fieldValue) {
        if (fieldValue == null) return true;
        String s = fieldValue.toString();
        
        try {
            if (positive) return (NumberUtils.isNumber(s) && Double.parseDouble(s) >= 0);
            else return (NumberUtils.isNumber(s) && Double.parseDouble(s) < 0);
        } catch (NumberFormatException e) {
            return false;
        }
    }
}