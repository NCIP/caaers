package gov.nih.nci.cabig.caaers.web.fields.validators;

import java.util.regex.Pattern;

public class PatternBasedValidator extends FieldValidator{

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
