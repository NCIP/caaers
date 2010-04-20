package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.dao.AgentDao;
import gov.nih.nci.cabig.caaers.dao.AgentSpecificTermDao;
import gov.nih.nci.cabig.caaers.service.AgentSpecificAdverseEventListService;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;
import gov.nih.nci.cabig.caaers.web.search.SearchController;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;
import java.util.Map;

/*
* @author Ion C. Olaru
* 
* */

public class AgentSearchController extends SearchController {

    private AgentDao agentDao;
    private AgentSpecificTermDao agentSpecificTermDao;
    private AgentSpecificAdverseEventListService service;

    private static final Log log = LogFactory.getLog(AgentSearchController.class);

    public AgentSearchController() {
        setFormView("admin/agentSearchForm");
        setSuccessView("admin/agentSearchForm");
        setCommandClass(AgentCommand.class);
    }

    @Override
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        super.initBinder(request, binder);
        log.debug(" In initBinder " + isFormSubmission(request));
        if (!isFormSubmission(request)) {
            super.buildSearchResultTable(request, null, null, SEARCH_AGENT);
        }
    }

    @Override
    protected void onBind(HttpServletRequest request, Object command) throws Exception {
        log.debug(" onBind ");
        String prop = request.getParameter("_prop");
        String value = request.getParameter("_value");
        log.debug(prop + "||" + value);
        super.buildSearchResultTable(request, prop, value, SEARCH_AGENT);

    }
}