package gov.nih.nci.cabig.caaers.web;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.web.study.StudyController;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

public abstract class AbstractAjaxFacade {
    private static final Log log = LogFactory.getLog(AbstractAjaxFacade.class);

    public static final String AJAX_SUBVIEW_PARAMETER = "_subview";
    public static final String AJAX_REQUEST = "_isAjax";

    protected String getOutputFromJsp(final String jspResource) {
        String html = "Error in rendering...";
        try {
            html = WebContextFactory.get().forwardToString(jspResource);
        } catch (ServletException e) {
            throw new CaaersSystemException(e.getMessage(), e);
        } catch (IOException e) {
            throw new CaaersSystemException(e.getMessage(), e);
        }
        return html;
    }

    protected Object extractCommand() {
        WebContext webContext = WebContextFactory.get();
        Object command = null;
        for (Class<?> controllerClass : controllers()) {
            String formSessionAttributeName = controllerClass.getName() + ".FORM.command";
            command = webContext.getSession().getAttribute(formSessionAttributeName);
            if (command == null) {
                log.debug("Command not found using name " + formSessionAttributeName);
            } else {
                log.debug("Command found using name " + formSessionAttributeName);
                break;
            }
        }
        if (command == null) {
            throw new CaaersSystemException("Could not find command in session");
        } else {
            return command;
        }
    }

    protected String getCurrentPageContextRelative(WebContext webContext) {
        String contextPath = webContext.getHttpServletRequest().getContextPath();
        String page = webContext.getCurrentPage();
        if (contextPath == null) {
            log.debug("context path not set");
            return page;
        } else if (!page.startsWith(contextPath)) {
            log.debug(page + " does not start with context path " + contextPath);
            return page;
        } else {
            return page.substring(contextPath.length());
        }
    }

    protected String createQueryString(Map<String, String> params) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            sb.append(entry.getKey()).append('=').append(entry.getValue()).append('&');
        }
        return sb.toString().substring(0, sb.length() - 1);
    }

    protected String renderAjaxView(String viewName, Map<String, String> params) {
        WebContext webContext = WebContextFactory.get();

        params.put(AbstractAjaxFacade.AJAX_SUBVIEW_PARAMETER, viewName);
        params.put(AbstractAjaxFacade.AJAX_REQUEST, "true");

        String url = String.format("%s?%s", getCurrentPageContextRelative(webContext), createQueryString(params));
        log.debug("Attempting to return contents of " + url);
        System.out.println(url);
        
        try {
            String html = webContext.forwardToString(url);
            if (log.isDebugEnabled()) log.debug("Retrieved HTML:\n" + html);
            return html;
        } catch (ServletException e) {
            throw new CaaersSystemException(e);
        } catch (IOException e) {
            throw new CaaersSystemException(e);
        }
    }

    protected HttpServletRequest getHttpServletRequest() {
        return WebContextFactory.get().getHttpServletRequest();
    }

    public abstract Class<?>[] controllers();

}