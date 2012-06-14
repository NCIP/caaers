package gov.nih.nci.cabig.caaers.web.filters;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

public class SessionFixationFilter implements Filter {

	private static Logger logger = Logger.getLogger(SessionFixationFilter.class
			.getName());

//	public static final String NEW_SESSION_INDICATOR = "filter.NewSessionFilter";

	public void destroy() {
	}

	@SuppressWarnings("unchecked")
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		if (request instanceof HttpServletRequest) {
			HttpServletRequest httpRequest = (HttpServletRequest) request;
			HttpSession session = httpRequest.getSession(false);

	        if (session == null) {
	            return;
	        }
	        
	        String originalSessionId = session.getId();

	        logger.debug("Invalidating session with Id '" + originalSessionId +"' and migrating attributes.");
	        
	        //storing session attributes in hash
	        HashMap attributesToMigrate = new HashMap();
            Enumeration enumer = session.getAttributeNames();
            while (enumer.hasMoreElements()) {
                String key = (String) enumer.nextElement();
                attributesToMigrate.put(key, session.getAttribute(key));
            }
	        
	        session.invalidate(); //invalidate session
	        session = httpRequest.getSession(true); // we now have a new session
	        
	        // copy invalidated session attributes to the new session
            Iterator iter = attributesToMigrate.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry entry = (Map.Entry) iter.next();
                session.setAttribute((String) entry.getKey(), entry.getValue());
            }

	        if (logger.isDebugEnabled()) {
	            logger.debug("Started new session: " + session.getId());
	        }
		}
		chain.doFilter(request, response);
	}

	public void init(FilterConfig filterconfig) {
	}
}