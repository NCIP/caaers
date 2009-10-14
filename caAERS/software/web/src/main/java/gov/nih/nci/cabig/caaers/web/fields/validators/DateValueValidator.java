package gov.nih.nci.cabig.caaers.web.fields.validators;

import gov.nih.nci.cabig.caaers.domain.DateValue;

/**
 * This validator is used by the fields to validate the DateValue objects.
 *
 * @author Biju Joseph
 */
public class DateValueValidator extends FieldValidator {

    @Override
    public String getMessagePrefix() {
        return "Invalid date value";
    }

    public String getValidatorCSSClassName() {
        return "NUMERIC";  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * Common validations performed are <br />
     * Year cannot be less than 1900<br />
     * Month if specified cannot be greater than 12
     * Day if specified cannot be greater than 31
     */
    @Override
    public boolean isValid(Object fieldValue) {
        if (fieldValue == null) return true;

        if (fieldValue instanceof DateValue) {
            DateValue dateValue = (DateValue) fieldValue;
            Integer year = dateValue.getYear();
            Integer month = dateValue.getMonth();
            Integer day = dateValue.getDay();

            if (year != null && year < 1900) return false;
            if (month != null) {
                if (month < 1 || month > 12) return false;
                if (year == null) return false; //year is needed when month is mentioned
            }
            if (day != null) {
                if (day < 1 || day > 31) return false;
                if (month == null || year == null) return false; //month and year needed when day is mentioned
            }

        }
        return true;
    }

}
