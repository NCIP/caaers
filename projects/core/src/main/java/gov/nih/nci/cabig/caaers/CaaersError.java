package gov.nih.nci.cabig.caaers;

/**
 * @author Rhett Sutphin
 */
public class CaaersError extends Error {
    public CaaersError(String message) {
        super(message);
    }

    public CaaersError(String message, Throwable cause) {
        super(message, cause);
    }
}
