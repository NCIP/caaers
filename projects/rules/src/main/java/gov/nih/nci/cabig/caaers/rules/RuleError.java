package gov.nih.nci.cabig.caaers.rules;

/**
 * @author Rhett Sutphin
 */
public class RuleError extends Error {
    public RuleError(String message) {
        super(message);
    }

    public RuleError(String message, Throwable cause) {
        super(message, cause);
    }
}
