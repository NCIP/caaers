package gov.nih.nci.cabig.caaers.tools.accesscontrol;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author Padmaja Vedula
 * @author Rhett Sutphin
 */
public class ApplicationSecurityManager {
    public static final String USER = ApplicationSecurityManager.class.getName() + ".USER";

    public static void setUser(HttpServletRequest request, String user) {
        request.getSession(true).setAttribute(USER, user);
    }

    public static String getUser(HttpServletRequest request) {
        String user = null;
        HttpSession session = request.getSession(false);
        if (session != null) {
            user = (String) session.getAttribute(USER);
        }
        return user;
    }

    public static void removeUserSession(HttpServletRequest request) {
        if (request.getSession(false) != null)
            request.getSession().removeAttribute(USER);
    }

    // All-static class
    private ApplicationSecurityManager() { }
}
