/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.fields.validators;

import org.apache.commons.lang.math.NumberUtils;
/**
 * Will check whether the decimal number is within range
 * @author Biju Joseph
 *
 */
public class DecimalRangeValidator extends FieldValidator {

	double begin;
    double end;

    public DecimalRangeValidator(double begin, double end) {
        this.begin = begin;
        this.end = end;
    }

	@Override
    public String getMessagePrefix() {
        return "Incorrect";
    }

    @Override
    public String getValidatorCSSClassName() {
        return "DECIMAL";
    }

    @Override
    public boolean isValid(Object fieldValue) {
    	if(fieldValue == null) return true;
        if ( NumberUtils.isNumber(fieldValue.toString()))
            return Double.parseDouble(fieldValue.toString()) <= end && Double.parseDouble(fieldValue.toString()) >= begin;
        return false;
    }

}
