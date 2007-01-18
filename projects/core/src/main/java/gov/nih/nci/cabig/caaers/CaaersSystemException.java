package gov.nih.nci.cabig.caaers;

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

    public CaaersSystemException(String message) {
        super(message);
    }
}
