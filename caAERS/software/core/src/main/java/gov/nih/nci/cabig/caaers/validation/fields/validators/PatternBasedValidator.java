package gov.nih.nci.cabig.caaers.validation.fields.validators;

import java.util.regex.Pattern;

public class PatternBasedValidator extends FieldValidator<PatternConstraint, Object> {

	private String cssClassName;
	private String regExPattern;
	
	
	public PatternBasedValidator(String regExPattern) {
		
		this(regExPattern, "DECIMAL");
	}
	
	public PatternBasedValidator(String regExPattern, String cssClassName) {
		this.cssClassName = cssClassName;
		this.regExPattern = regExPattern;
	}
	
	@Override
	public void initialize(PatternConstraint constraint) {
    	super.initialize(constraint);
    	this.regExPattern=constraint.regExPattern();
	}
	
	@Override
	public String getMessagePrefix() {
		return "Invalid";
	}

	@Override
	public String getValidatorCSSClassName() {
		return cssClassName;
	}

	@Override
	public boolean isValid(Object fieldValue) {
		if(fieldValue == null) return true;
		return Pattern.matches(regExPattern, fieldValue.toString());
	}

}
