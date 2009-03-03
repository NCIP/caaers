package gov.nih.nci.cabig.caaers.web.fields.validators;

import org.apache.commons.lang.StringUtils;

public class PhoneNumberValidator extends FieldValidator {

    @Override
    public boolean isValid(Object fieldValue) {
        String strVal = stringValue(fieldValue);

        if (StringUtils.isEmpty(strVal)) return true;
        // valid.

        // strip off the symbols
        String strPhNo = StringUtils.replaceChars(strVal, "+()-. ", "");
        if (!StringUtils.isNumeric(strPhNo)) return false;
        if (strPhNo.length() != 10 && strPhNo.length() != 11) return false;

        return true;
    }

    @Override
    public String getMessagePrefix() {
        return "Invalid";
    }

    public String getValidatorCSSClassName() {
        return "US_PHONE_NO";  
    }

}
