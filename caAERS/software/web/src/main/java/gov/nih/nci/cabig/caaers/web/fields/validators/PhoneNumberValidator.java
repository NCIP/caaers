package gov.nih.nci.cabig.caaers.web.fields.validators;

import org.apache.commons.lang.StringUtils;

public class PhoneNumberValidator extends FieldValidator {

    @Override
    public boolean isValid(Object fieldValue) {
        String strVal = stringValue(fieldValue);

        if (StringUtils.isEmpty(strVal)) return true;
        // valid.

        // check the extension number
        byte _x = (byte)strVal.indexOf("x");
        String _ext = "";
        if (_x >= 0) {
            _ext = strVal.substring(_x + 1);
            strVal = strVal.substring(0, _x);
            _ext = StringUtils.replaceChars(_ext, "+()-. ", "");
        }

        // strip off the symbols
        String strPhNo = StringUtils.replaceChars(strVal, "+()-. ", "");
        if (!StringUtils.isNumeric(strPhNo)) return false;
        if (strPhNo.length() != 10 && strPhNo.length() != 11) return false;
        if (!StringUtils.isNumeric(_ext)) return false;

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
