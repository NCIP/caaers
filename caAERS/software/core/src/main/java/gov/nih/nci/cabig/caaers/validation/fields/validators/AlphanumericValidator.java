/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.validation.fields.validators;



/**
 * Validating whether the value is an alphanumeric one or not
 * @author Ion C. Olaru
 *
 */
public class AlphanumericValidator extends FieldValidator<AlphaNumericConstraint, Object> {

    private int minLength;
    private int maxLength;
    private boolean minLengthOK = true;
    private boolean maxLengthOK = true;

    public AlphanumericValidator() {
    }

    public AlphanumericValidator(int minLength, int maxLength) {
        this.minLength = minLength;
        this.maxLength = maxLength;
    }

    public AlphanumericValidator(int maxLength) {
        this.maxLength = maxLength;
    }

    @Override
	public void initialize(AlphaNumericConstraint constraint) {
    	super.initialize(constraint);
    	this.minLength=constraint.min();
    	this.maxLength=constraint.max();
	}

	@Override
    public String getMessagePrefix() {
        if (!minLengthOK) return "The value's length is below the minimum of " + minLength + ".";
        if (!maxLengthOK) return "The value's length is above the maximum of " + maxLength + ".";
        return "Invalid alphanumeric value";
    }

    @Override
    public String getValidatorCSSClassName() {
        return "ALPHANUMERIC";
    }

    /*
    * The input string is evaluated as valid if it contains only Alphanumeric symbols and spaces
    * @return boolean
    * @input Object - usually this would be a String 
    * */
    @Override
    public boolean isValid(Object fieldValue) {
        if (fieldValue != null) {
            if (minLength > 0 && fieldValue.toString().length() < minLength) minLengthOK = false; else minLengthOK = true;
            if (maxLength > 0 && fieldValue.toString().length() > maxLength) maxLengthOK = false; else maxLengthOK = true;
            return (minLengthOK && maxLengthOK && fieldValue.toString().matches("^[a-zA-Z0-9_\\.'\\- ]*$"));
        }
        return true;
    }
}
