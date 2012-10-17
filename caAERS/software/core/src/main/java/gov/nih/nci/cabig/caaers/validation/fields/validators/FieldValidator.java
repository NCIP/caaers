package gov.nih.nci.cabig.caaers.validation.fields.validators;

import gov.nih.nci.cabig.caaers.validation.AbstractConstraintFieldValidator;

import java.lang.annotation.Annotation;

/**
 * 
 * @author Biju Joseph
 *
 */
public abstract class FieldValidator<A extends Annotation, T> extends AbstractConstraintFieldValidator<A, T> {
	
	/**
	 * These validators can be used directly to validate values 
	 * or as part of the validation api as ConstraintValidator for a constraint
	 */
    public static final FieldValidator NOT_NULL_VALIDATOR;
    public static final FieldValidator EMAIL_VALIDATOR;
    public static final FieldValidator PHONE_VALIDATOR;
    public static final FieldValidator PAST_DATE_VALIDATOR;
    public static final FieldValidator FUTURE_DATE_VALIDATOR;
    public static final FieldValidator DATE_VALUE_VALIDATOR;
    public static final FieldValidator DATE_VALIDATOR;
    public static final FieldValidator NUMBER_VALIDATOR;
    public static final FieldValidator HOUR_VALIDATOR;
    public static final FieldValidator MINUTE_VALIDATOR;
    public static final FieldValidator ZIP_CODE_VALIDATOR;
    public static final FieldValidator DECIMAL_VALIDATOR;
    public static final FieldValidator ALPHANUMERIC_VALIDATOR;
    public static final FieldValidator SIGN_VALIDATOR;
    public static final FieldValidator IDENTIFIER_VALIDATOR;
    public static final FieldValidator TEXTSIZE_VALIDATOR;

    private boolean positive;

    static {
        NOT_NULL_VALIDATOR = new NotNullValidator();
        EMAIL_VALIDATOR = new EmailValidator();
        PHONE_VALIDATOR = new PhoneNumberValidator();
        PAST_DATE_VALIDATOR = new PastDateValidator();
        DATE_VALUE_VALIDATOR = new DateValueValidator();
        NUMBER_VALIDATOR = new NumberValidator();
        HOUR_VALIDATOR = createNumberRangeValidator(1, 12);
        MINUTE_VALIDATOR = createNumberRangeValidator(0, 59);
        ZIP_CODE_VALIDATOR = new ZipCodeValidator();
        FUTURE_DATE_VALIDATOR = new FutureDateValidator();
        DATE_VALIDATOR = new DateValidator();
        DECIMAL_VALIDATOR = new DecimalValidator();
        ALPHANUMERIC_VALIDATOR = new AlphanumericValidator();
        SIGN_VALIDATOR = new SignValidator(true);
        IDENTIFIER_VALIDATOR = new IdentifierValidator();
        TEXTSIZE_VALIDATOR = new TextSizeValidator();
    }

    public static NumberRangeValidator createNumberRangeValidator(int begin, int end) {
        return new NumberRangeValidator(begin, end);
    }
   
    public static TextSizeValidator createTextSizeValidator(int size) {
        return new TextSizeValidator(size);
    }
    
    public static PatternBasedValidator createPatternBasedValidator(String pattern, String cssClassName){
    	return new PatternBasedValidator(pattern, cssClassName);
    }
    
    /**
     * This will return the error message prefix. eg: in case of NotNullValidator - it should be
     * "Missing" where as in the case of a PhoneNumberValidator it should be "Invalid"
     *
     * @return
     */
    public abstract String getMessagePrefix();

    public abstract String getValidatorCSSClassName();

    public String stringValue(Object fieldValue) {
        if (fieldValue == null) return null;
        return fieldValue.toString();
    }

}
