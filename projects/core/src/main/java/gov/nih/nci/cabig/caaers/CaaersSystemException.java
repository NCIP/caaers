package gov.nih.nci.cabig.caaers;

import java.lang.reflect.InvocationTargetException;

/**
 * @author Rhett Sutphin
 */
public class CaaersSystemException extends RuntimeException {
    public CaaersSystemException(String message, Throwable cause) {
        super(message, cause);
    }

    public CaaersSystemException(Throwable cause) {
        super(cause);
    }
}
