package gov.nih.nci.cabig.caaers;

/**
 * @author Rhett Sutphin
 */
public class CommonsConfigurationException extends RuntimeException {

    public CommonsConfigurationException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommonsConfigurationException(Throwable cause) {
        super(cause);
    }

    public CommonsConfigurationException(String message) {
        super(message);
    }
}
