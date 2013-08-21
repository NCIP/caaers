/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.validation.fields.validators;

import gov.nih.nci.cabig.caaers.domain.DateTimeValue;
import gov.nih.nci.cabig.caaers.domain.DateValue;

/**
 * This validator is used by the fields to validate the DateTime Value objects.
 *
 * @author Vijendhar Meda
 */
public class DateTimeValueValidator extends FieldValidator<DateConstraint, Object> {

    @Override
    public String getMessagePrefix() {
        return "Invalid date value";
    }

    public String getValidatorCSSClassName() {
        return "DTIME";  //To change body of implemented methods use File | Settings | File Templates.
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
        //TODO: shall we remove DateValidator and add check for instanceof Date here?
        if (fieldValue instanceof DateTimeValue) {
            DateTimeValue dateTimeValue = (DateTimeValue) fieldValue;
            Integer year = dateTimeValue.getYear();
            Integer month = dateTimeValue.getMonth();
            Integer day = dateTimeValue.getDay();

            if (year != null && year < 1900) return false;
            if (month != null) {
                if (month < 1 || month > 12) return false;
                if (year == null) return false; //year is needed when month is mentioned
            }
            if (day != null) {
                if (day < 1 || day > 31) return false;
                if (month == null || year == null) return false; //month and year needed when day is mentioned
            }


            Integer hour = dateTimeValue.getHour() ;
            Integer minute = dateTimeValue.getMinute();
            Integer second = dateTimeValue.getSecond();

            if (hour != null) {
                if ( hour < 0 || hour > 12 ) return false;
            }

            if (minute != null) {
                if ( minute < 0 || minute > 59) return false;
            }

            if (second != null) {
                if ( second < 0 || second > 59) return false;
            }
        }
        return true;
    }

}
