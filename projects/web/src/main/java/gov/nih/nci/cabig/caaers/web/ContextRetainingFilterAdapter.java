package gov.nih.nci.cabig.caaers.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.Filter;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * This base class provides two features to assist in implementing {@link Filter}s:
 * <ul>
 * <li>It stores the servletContext provided during {@link #init} so that it may be accessed
 *      during filter processing.  It also exposes the spring application context associated
 *      with the servlet context (if any).</li>
 * <li>It provides blank implementations of the other methods in {@link Filter} so that the
 *      subclass need only implement the ones it is going to use.</li>
 * </ul>
 * @see #getApplicationContext, #getServletContext
 * @author Rhett Sutphin
 */
/* TODO: much of this class is shared with PSC.  Refactor into a shared library. */
public abstract class ContextRetainingFilterAdapter extends FilterAdapter {
    protected final Log log = LogFactory.getLog(getClass());

    private ServletContext servletContext;

    protected ServletContext getServletContext() {
        return servletContext;
    }

    protected WebApplicationContext getApplicationContext() {
        return WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
    }

    public void init(FilterConfig filterConfig) throws ServletException {
        servletContext = filterConfig.getServletContext();
    }
}
