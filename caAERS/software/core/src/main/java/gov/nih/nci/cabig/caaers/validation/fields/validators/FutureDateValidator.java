/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.validation.fields.validators;

import gov.nih.nci.cabig.caaers.domain.DateValue;

import java.util.Date;

public class FutureDateValidator extends FieldValidator<FutureDateConstraint, Object> {

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
