package gov.nih.nci.cabig.caaers.web.fields.validators;



public abstract class FieldValidator {
  
  
  public static final FieldValidator NOT_NULL_VALIDATOR;
  public static final FieldValidator EMAIL_VALIDATOR;
  public static final FieldValidator PHONE_VALIDATOR;
  static{
	  NOT_NULL_VALIDATOR = new NotNullValidator();
	  EMAIL_VALIDATOR = new EmailValidator();
	  PHONE_VALIDATOR = new PhoneNumberValidator();
  }
  /**
   * Will validate the input, based on the specific validation rules.
   * @return
   */
  public abstract boolean isValid(Object fieldValue);
  
  /**
   * This will return the error message prefix.
   * eg: in case of NotNullValidator - it should be "Missing"
   * where as in the case of a PhoneNumberValidator it should be "Invalid"
   * @return
   */
  public abstract String getMessagePrefix();
  
  
 
  public String stringValue(Object fieldValue){
	  if(fieldValue == null) return null;
	  return fieldValue.toString();
  }
  
  
}
