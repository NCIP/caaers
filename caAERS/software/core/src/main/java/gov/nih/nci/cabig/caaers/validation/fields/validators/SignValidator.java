/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.validation.fields.validators;

import org.apache.commons.lang.math.NumberUtils;


/**
 * @author Ion C. Olaru
 *
 */
public class SignValidator extends FieldValidator<SignConstraint, Object> {

    boolean positive;

    /*
    * #param positive   if this is true then the fieldValue will be checked if it is positive, otherwise negative
    * */
    public SignValidator(boolean positive) {
        this.positive = positive;
    }
    
    @Override
	public void initialize(SignConstraint constraint) {
    	super.initialize(constraint);
    	this.positive=constraint.positive();
	}
    
    @Override
    public String getMessagePrefix() {
        return "Invalid sign";
    }

    @Override
    public String getValidatorCSSClassName() {
        if (!positive) return "NEGATIVE";
        else return "POSITIVE";
    }

    @Override
    public boolean isValid(Object fieldValue) {
        if (fieldValue == null) return true;
        String s = fieldValue.toString();
        
        try {
            if (positive) return (NumberUtils.isNumber(s) && Double.parseDouble(s) >= 0);
            else return (NumberUtils.isNumber(s) && Double.parseDouble(s) < 0);
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
