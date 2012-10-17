package gov.nih.nci.cabig.caaers.validation.fields.validators;

import org.apache.commons.lang.StringUtils;


public class TextSizeValidator extends FieldValidator<TextSizeConstraint, Object> {

	private int size;
	
	public TextSizeValidator() {
		this.size = 0;
	}
	public TextSizeValidator(int size) {
		this.size = size;
	}
    @Override
    public boolean isValid(Object fieldValue) {
    	if(fieldValue == null || StringUtils.isEmpty(fieldValue.toString())) return true;
    	return (fieldValue.toString().length() < this.size);
    }

    @Override
    public String getMessagePrefix() {
        return "Incorrect size of the field ";
    }

    public String getValidatorCSSClassName() {
        return "TEXT";
    }
    
    public  int getTextSize() {
    	return size;
    }
}