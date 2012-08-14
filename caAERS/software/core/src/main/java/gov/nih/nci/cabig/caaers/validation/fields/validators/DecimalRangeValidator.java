package gov.nih.nci.cabig.caaers.validation.fields.validators;

import org.apache.commons.lang.math.NumberUtils;

/**
 * Will check whether the decimal number is within range
 * @author Biju Joseph
 *
 */
public class DecimalRangeValidator extends FieldValidator<DecimalRangeConstraint, Object> {

	double begin;
    double end;

    public DecimalRangeValidator(double begin, double end) {
        this.begin = begin;
        this.end = end;
    }
    
    @Override
	public void initialize(DecimalRangeConstraint constraint) {
    	super.initialize(constraint);
    	this.begin=constraint.begin();
    	this.end=constraint.end();
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
