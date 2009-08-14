package gov.nih.nci.cabig.caaers.web.fields.validators;

import org.apache.commons.lang.math.NumberUtils;

import java.text.DecimalFormatSymbols;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Will check if the number is decimal
 * @author Biju Joseph
 * @author Ion C. Olaru
 *
 */
public class DecimalValidator extends FieldValidator {

    int integerLength = 0;
    int fractionalLength = 0;
    NumberFormat nf;

    public DecimalValidator() {
        nf = new DecimalFormat("##############.######");
    }

    public DecimalValidator(int integerLength, int fractionalLength) {
        this();
        this.integerLength = integerLength;
        this.fractionalLength = fractionalLength;
        nf.setMaximumFractionDigits(fractionalLength);
        nf.setMaximumIntegerDigits(integerLength);
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
        if (fieldValue == null) return true;
        
        String s = fieldValue.toString();
        try {
            if (fieldValue != null)
                if (integerLength == 0 && fractionalLength == 0) return NumberUtils.isNumber(s);
            else return nf.format(Double.parseDouble(s)).equals(s);
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }
}
