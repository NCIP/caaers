/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.validation.fields.validators;

import org.apache.commons.lang.math.NumberUtils;



public class NumberValidator extends FieldValidator<NumberConstraint, Object> {

    @Override
    public String getMessagePrefix() {
        return "Invalid";
    }

    @Override
    public String getValidatorCSSClassName() {

        return "NUMERIC";
    }

    @Override
    public boolean isValid(Object fieldValue) {
        if (fieldValue != null) return NumberUtils.isNumber(fieldValue.toString());
        return true;
    }


}
