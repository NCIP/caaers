package gov.nih.nci.cabig.caaers.web.search;

import gov.nih.nci.cabig.caaers.web.study.SearchStudyCommand;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.ServletRequestDataBinder;

public class SearchINDController extends SearchController {
    private static final Log log = LogFactory.getLog(SearchINDController.class);

    public SearchINDController() {
        setCommandClass(SearchStudyCommand.class);
        setFormView("search/ind_search");
        setSuccessView("search/ind_search");
    }

    @Override
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        super.initBinder(request, binder);
        log.debug(" In initBinder " + isFormSubmission(request));
        if (!isFormSubmission(request)) {
            super.buildSearchResultTable(request, null, null, 5);
        }
    }

    @Override
    protected void onBind(HttpServletRequest request, Object command) throws Exception {
        log.debug(" onBind ");
        String prop = request.getParameter("_prop");
        String value = request.getParameter("_value");
        log.debug(prop + "||" + value);
        super.buildSearchResultTable(request, prop, value, 5);

    }
}
