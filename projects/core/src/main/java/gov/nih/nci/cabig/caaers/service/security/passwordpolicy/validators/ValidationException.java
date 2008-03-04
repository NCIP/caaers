package gov.nih.nci.cabig.caaers.service.security.passwordpolicy.validators;

import gov.nih.nci.cabig.caaers.CaaersSystemException;

public class ValidationException extends CaaersSystemException {
    private static final long serialVersionUID = 8625024692592257767L;

    public ValidationException(String message) {
        super(message);
    }
}
