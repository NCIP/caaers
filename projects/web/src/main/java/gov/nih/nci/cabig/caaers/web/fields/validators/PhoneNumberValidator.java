package gov.nih.nci.cabig.caaers.web.fields.validators;

import org.apache.commons.lang.StringUtils;

public class PhoneNumberValidator extends FieldValidator {

    @Override
    public boolean isValid(Object fieldValue) {
        String strVal = stringValue(fieldValue);

        if (StringUtils.isEmpty(strVal)) return true; // empty phone number is considered as
        // valid.

        int expectedSize = 10;
        if (strVal.startsWith("+")) expectedSize = 12;

        // strip off the + symbol, - symbol and barackets.
        String strPhNo = StringUtils.replaceChars(strVal, "+()-", "");
        if (!StringUtils.isNumeric(strPhNo)) return false;
        return strPhNo.length() == expectedSize;

    }

    @Override
    public String getMessagePrefix() {
        return "Invalid";
    }

    public String getValidatorCSSClassName() {
        return "US_PHONE_NO";  //To change body of implemented methods use File | Settings | File Templates.
    }

}
