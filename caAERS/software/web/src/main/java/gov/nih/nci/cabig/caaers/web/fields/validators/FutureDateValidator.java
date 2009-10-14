package gov.nih.nci.cabig.caaers.web.fields.validators;

import gov.nih.nci.cabig.caaers.domain.DateValue;

import java.util.Date;

public class FutureDateValidator extends FieldValidator {

    @Override
    public boolean isValid(Object fieldValue) {
        Date now = new Date();
        if (fieldValue instanceof Date) {
            Date date = (Date) fieldValue;
            if (date.compareTo(now) < 0) return false;
        }
        if (fieldValue instanceof DateValue) {
            if (new DateValue(now).compareTo((DateValue) fieldValue) > 0) return false;
        }
        return true;
    }

    @Override
    public String getMessagePrefix() {
        return "Invalid date value";
    }

    public String getValidatorCSSClassName() {
        return "DATE";
    }

}