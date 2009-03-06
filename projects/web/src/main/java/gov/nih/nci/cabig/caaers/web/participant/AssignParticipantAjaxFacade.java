package gov.nih.nci.cabig.caaers.web.participant;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.web.ae.AbstractAdverseEventInputController;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.springframework.beans.factory.annotation.Required;

public class AssignParticipantAjaxFacade {
    public static final String AJAX_REQUEST_PARAMETER = "_isAjax";
    private static final Log log = LogFactory.getLog(AssignParticipantAjaxFacade.class);
    private static Class<?>[] CONTROLLERS = {
            AssignParticipantController.class
    };

    public static final String AJAX_SUBVIEW_PARAMETER = "_subview";
    public static final String AJAX_INDEX_PARAMETER = "index";
    private ParticipantDao participantDao;

    /**
     *
     * @param
     * @return
     */
    public String addPriorTherapy() {
/*


        HttpServletRequest request = getHttpServletRequest();
        AssignParticipantStudyCommand assignParticipantStudyCommand = extractCommand();

      //  Integer index = assignParticipantStudyCommand.getStudyParticipantPriorTherapies().size();
      //  assignParticipantStudyCommand.addStudyParticipanttPriorTherapies(new StudyParticipantPriorTherapy());

        request.setAttribute(AJAX_SUBVIEW_PARAMETER, "newPriorTherapy");
        request.setAttribute(AJAX_REQUEST_PARAMETER, "AJAX");
      //  request.setAttribute(AJAX_INDEX_PARAMETER, index);

        String url = getCurrentPageContextRelative(WebContextFactory.get());
        String html = getOutputFromJsp(url);

        return html;
*/
        return null;
    }

    public String addConcomitantMedication()
    {
/*
    	HttpServletRequest request = getHttpServletRequest();
        AssignParticipantStudyCommand assignParticipantStudyCommand = extractCommand();
        
        List<ConcomitantMedication> listOfConMeds = assignParticipantStudyCommand.getListOfConcomitantMedications();
        
        listOfConMeds.add( new ConcomitantMedication() );
        request.setAttribute(AJAX_SUBVIEW_PARAMETER, "addMedicationFields");
        request.setAttribute(AJAX_REQUEST_PARAMETER, "AJAX");
        String url = getCurrentPageContextRelative(WebContextFactory.get());
        String html = getOutputFromJsp(url);
        return html;
*/
    	return null;
    	
    }

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

    private String renderIndexedAjaxView(String viewName, int index, Integer aeReportId) {
        return renderIndexedAjaxView(viewName, index, null, aeReportId);
    }

    private String renderIndexedAjaxView(String viewName, int index, Integer parentIndex, Integer aeReportId) {
        Map<String, String> params = new LinkedHashMap<String, String>(); 
        params.put("index", Integer.toString(index));
        if (parentIndex != null) params.put("parentIndex", Integer.toString(parentIndex));
        return renderAjaxView(viewName, aeReportId, params);
    }

    private String renderAjaxView(String viewName, Integer aeReportId, Map<String, String> params) {
        WebContext webContext = WebContextFactory.get();

        if (aeReportId != null) params.put("aeReport", aeReportId.toString());
        params.put(AbstractAdverseEventInputController.AJAX_SUBVIEW_PARAMETER, viewName);

        String url = String.format("%s?%s",
                getCurrentPageContextRelative(webContext), createQueryString(params));
        log.debug("Attempting to return contents of " + url);
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

    private AssignParticipantStudyCommand extractCommand() {
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
            return (AssignParticipantStudyCommand) command;
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


    // TODO: there's got to be a library version of this somewhere
    private String createQueryString(Map<String, String> params) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            sb.append(entry.getKey()).append('=').append(entry.getValue())
                    .append('&');
        }
        return sb.toString().substring(0, sb.length() - 1);
    }

    @Required
    public void setParticipantDao(final ParticipantDao participantDao) {
        this.participantDao = participantDao;
    }

    private HttpServletRequest getHttpServletRequest() {
        return WebContextFactory.get().getHttpServletRequest();
    }


}