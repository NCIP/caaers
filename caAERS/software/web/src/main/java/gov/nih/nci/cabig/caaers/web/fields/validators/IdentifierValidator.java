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
public class IdentifierValidator extends FieldValidator {

    @Override
    public String getMessagePrefix() {
        return "Invalid identifier";
    }

    @Override
    public String getValidatorCSSClassName() {
        return "IDENTIFIER";
    }

    @Override
    public boolean isValid(Object fieldValue) {
        if (fieldValue != null) return fieldValue.toString().matches("^[a-zA-Z0-9#,*()_\\-'\":\\.{}\\[\\]]*$");
        return true;
    }

}