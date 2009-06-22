package gov.nih.nci.cabig.caaers.validation;

import gov.nih.nci.cabig.caaers.CaaersSystemException;

/**
 * @author Jared Flatow
 */
public class CaaersValidationException extends CaaersSystemException {
    public CaaersValidationException(String message) {
        super(message);
    }
}