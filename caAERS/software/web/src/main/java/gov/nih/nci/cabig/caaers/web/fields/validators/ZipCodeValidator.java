package gov.nih.nci.cabig.caaers.web.fields.validators;

import org.apache.commons.lang.StringUtils;

public class ZipCodeValidator extends FieldValidator {

    @Override
    public boolean isValid(Object fieldValue) {
    	if(fieldValue == null || StringUtils.isEmpty(fieldValue.toString())) return true;
    	return fieldValue.toString().matches( "\\d{5}" );
    }

    @Override
    public String getMessagePrefix() {
        return "Missing";
    }

    public String getValidatorCSSClassName() {
        return "ZIPCODE";
    }
}