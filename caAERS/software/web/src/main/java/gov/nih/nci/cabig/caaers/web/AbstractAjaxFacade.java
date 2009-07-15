package gov.nih.nci.cabig.caaers.web;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.web.ae.AbstractAdverseEventInputController;
import gov.nih.nci.cabig.caaers.web.participant.AssignParticipantController;
import gov.nih.nci.cabig.caaers.web.participant.AssignParticipantStudyCommand;
import gov.nih.nci.cabig.caaers.web.participant.AssignParticipantAjaxFacade;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;

public abstract class AbstractAjaxFacade {
    public static final String AJAX_REQUEST_PARAMETER = "_isAjax";
    private static final Log log = LogFactory.getLog(AbstractAjaxFacade.class);
    private Class<?>[] CONTROLLERS;

    public static final String AJAX_SUBVIEW_PARAMETER = "_subview";
    public static final String AJAX_INDEX_PARAMETER = "index";

    private String getOutputFromJsp(final String jspResource) {
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

    private Object extractCommand() {
        WebContext webContext = WebContextFactory.get();
        Object command = null;
        for (Class<?> controllerClass : CONTROLLERS) {
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

    private String getCurrentPageContextRelative(WebContext webContext) {
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

    public Class<?>[] getCONTROLLERS() {
        return CONTROLLERS;
    }

    public abstract void setCONTROLLERS(Class<?>[] CONTROLLERS);

    protected HttpServletRequest getHttpServletRequest() {
        return WebContextFactory.get().getHttpServletRequest();
    }

}