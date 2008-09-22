package gov.nih.nci.cabig.caaers.web.fields.validators;

import gov.nih.nci.cabig.caaers.domain.DateValue;

import java.util.Date;

public class DateValidator extends FieldValidator {

    @Override
    public boolean isValid(Object fieldValue) {
        return fieldValue instanceof Date || fieldValue instanceof DateValue ? true : false;
    }

    @Override
    public String getMessagePrefix() {
        return "Incorrect date value";
    }

    public String getValidatorCSSClassName() {
        return "DATE";
    }

}