package gov.nih.nci.cabig.caaers.validation;

import java.util.ArrayList;
import java.util.List;

public class ValidationErrors {
    List<ValidationError> errors;

    public ValidationErrors() {
        errors = new ArrayList<ValidationError>();
    }

    public void addValidationError(String code, String msg, Object... r1) {
        errors.add(new ValidationError(code, msg, r1));
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
}
