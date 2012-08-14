package gov.nih.nci.cabig.caaers.validation.fields.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * @author Biju Joseph
 * @author Ion
 * @author Karthik Iyer
 */
public class PhoneNumberValidator extends FieldValidator<PhoneNumberConstraint, Object> {

	@Override
	public boolean isValid(Object fieldValue) {
		String strVal = stringValue(fieldValue);
		if (StringUtils.isEmpty(strVal)) return true; // valid.
		String regex = "^(\\+?\\d{10,11}|((\\+)?\\d[-\\.])?(((\\d{3}[-\\.]){2}\\d{4}))|((\\+)?\\d)?((\\(\\d{3}[\\)][-\\.]?\\d{3}[-\\.]?\\d{4})))(x\\d+)?$";
		 Pattern p = Pattern.compile(regex);
		 Matcher m = p.matcher(strVal);
		 return m.matches();
	}

	@Override
	public String getMessagePrefix() {
		return "Invalid";
	}

	public String getValidatorCSSClassName() {
		return "US_PHONE_NO";
	}

}
// http://tools.netshiftmedia.com/regexlibrary/#
// \d{10}|\d{11}|((\+)?\d[-\.])?((\d{3}[-\.]\d{3}[-\.]\d{4}))|((\+)?\d)?((\d{3}[-\.]\d{3}[-\.]\d{4}))
// ^(\d{10,11}|((\+)?\d[-\.])?(((\d{3}[-\.]){2}\d{4}))|((\+)?\d)?((\(\d{3}[\)]\d{3}[-\.]?\d{4})))x[\d]+$
// ^(\d{10,11}|((\+)?\d[-\.])?(((\d{3}[-\.]){2}\d{4}))|((\+)?\d)?((\(\d{3}[\)]\d{3}[-\.]?\d{4})))(x\d+)?$

