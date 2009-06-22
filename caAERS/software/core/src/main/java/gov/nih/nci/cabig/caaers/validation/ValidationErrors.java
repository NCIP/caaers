package gov.nih.nci.cabig.caaers.validation;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class ValidationErrors {
    List<ValidationError> errors;

    public ValidationErrors() {
        errors = new ArrayList<ValidationError>();
    }

    public ValidationError addValidationError(String code, String msg, Object... r1) {
        ValidationError ve = new ValidationError(code, msg, r1);
        errors.add(ve);
        return ve;
    }

    public String toString() {
        return errors.toString();
    }

    public int getErrorCount() {
        return errors.size();
    }

    public boolean hasErrors() {
        return errors.size() > 0;
    }

    public List<ValidationError> getErrors() {
        return errors;
    }

    public ValidationError getErrorAt(int index) {
        return errors.get(index);
    }
    
    public boolean containsErrorWithCode(String code){
    	for(ValidationError error : errors){
    		if(StringUtils.equals(error.getCode(),code)) return true;
    	}
    	return false;
    }
}
