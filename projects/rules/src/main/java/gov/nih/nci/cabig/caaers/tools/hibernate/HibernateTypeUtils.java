package gov.nih.nci.cabig.caaers.tools.hibernate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Utilities to assist {@link org.hibernate.usertype.UserType}s in behaving more like
 * built in types.
 *
 * @author Rhett Sutphin
 */
/* TODO: much of this class is shared with PSC.  Refactor into a shared library. */
public final class HibernateTypeUtils {
    private HibernateTypeUtils() { }

    public static Log getLog(Class<?> clazz) {
        return LogFactory.getLog(getLogCategory(clazz));
    }

    /**
     * Provide a log category for the given class that is similar to that used by hibernate for
     * its own type classes.  This makes it easier to filter the log entries to show all type
     * bindings together.
     * @param clazz the type class
     * @return a log category starting with org.hibernate.type
     */
    public static String getLogCategory(Class<?> clazz) {
        return new StringBuffer("org.hibernate.type.studycalendar.")
            .append(clazz.getSimpleName())
            .toString();
    }

    public static void logBind(Log log, int index, Object value) {
        if (log.isDebugEnabled()) {
            StringBuffer msg = new StringBuffer("binding ");
            appendBoundValue(value, msg);
            msg.append(" to parameter: ").append(index);
            log.debug(msg);
        }
    }

    private static StringBuffer appendBoundValue(Object value, StringBuffer msg) {
        if (value == null) {
            msg.append("null");
        } else {
            msg.append("'").append(value).append("'");
        }
        return msg;
    }

    public static void logReturn(Log log, String column, Object value) {
        if (log.isDebugEnabled()) {
            StringBuffer msg = new StringBuffer("returning ");
            appendBoundValue(value, msg);
            msg.append(" as column: ").append(column);
            log.debug(msg);
        }
    }
}
