package gov.nih.nci.cabig.caaers.rules;

import java.lang.reflect.InvocationTargetException;

/**
 * @author un-ascribed
 */
public class RuleException extends RuntimeException {
    public RuleException(String message, Throwable cause) {
        super(message, cause);
    }

    public RuleException(Throwable cause) {
        super(cause);
    }
}