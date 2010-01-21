package gov.nih.nci.cabig.caaers.web.security;

import org.acegisecurity.Authentication;
import org.acegisecurity.ui.logout.LogoutHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;

/**
 *
 * This class is added to address the following:-
 *  <li>Tomcat is not properly invalidating the HttpSession.</li>
 *  <li>Log out and re-login to the application shows the incorrect tabs due to incorrect Authentication object in session</li>
 * @author Biju Joseph
 *
 */
public class HttpSessionPurgeLogoutHandler implements LogoutHandler {

    private static final Log logger = LogFactory.getLog(HttpSessionPurgeLogoutHandler.class);

    /**
     * This method will obtain the session and will explicitly set all the attributes to NULL and then remove it from session.
     *
     * @param request
     * @param response
     * @param authentication
     */
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        try{

            HttpSession session = request.getSession(false);
            
		    if (session != null) {

                if(logger.isDebugEnabled()) logger.debug("Explicitly going to remove session attributes");

			    for(Enumeration<Object> e = session.getAttributeNames(); e.hasMoreElements(); ){
                    String aName = String.valueOf(e.nextElement());
                    session.setAttribute(aName , null);
                    session.removeAttribute(aName);
                }
		    }

        }catch(Exception e){
            //ignore
           if(logger.isDebugEnabled()) logger.debug("Ignore this error",e);
        }

    }
}
