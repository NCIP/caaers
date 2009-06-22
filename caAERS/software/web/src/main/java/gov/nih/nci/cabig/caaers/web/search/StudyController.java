package gov.nih.nci.cabig.caaers.web.search;

import gov.nih.nci.cabig.caaers.web.study.SearchStudyCommand;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.ServletRequestDataBinder;

/**
 * @author Krikor Krumlian
 */
public class StudyController extends SearchController {

    private static final Log log = LogFactory.getLog(StudyController.class);

    public StudyController() {
        setCommandClass(SearchStudyCommand.class);
        setFormView("search/study_search");
        setSuccessView("search/study_search");
    }

    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        super.initBinder(request, binder);
        log.debug(" In initBinder " + isFormSubmission(request));
        if (!isFormSubmission(request)) {
            super.buildSearchResultTable(request, null, null, 0);
        }
    }

    protected void onBind(HttpServletRequest request, Object command) throws Exception {
        log.debug(" onBind ");
        String prop = request.getParameter("_prop");
        String value = request.getParameter("_value");
        super.buildSearchResultTable(request, prop, value, 0);
        log.debug(prop);
    }

}