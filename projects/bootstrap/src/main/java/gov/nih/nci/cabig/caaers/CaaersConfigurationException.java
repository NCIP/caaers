package gov.nih.nci.cabig.caaers;

/**
 * @author Rhett Sutphin
 */
public class CaaersConfigurationException extends RuntimeException {

    public CaaersConfigurationException(String message, Throwable cause) {
        super(message, cause);
    }

    public CaaersConfigurationException(Throwable cause) {
        super(cause);
    }

    public CaaersConfigurationException(String message) {
        super(message);
    }
}
