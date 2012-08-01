package gov.nih.nci.cabig.caaers.web.search;

import gov.nih.nci.cabig.caaers.web.study.SearchStudyCommand;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.mvc.SimpleFormController;

public class SearchINDController extends SimpleFormController {
    private static final Log log = LogFactory.getLog(SearchINDController.class);

    public SearchINDController() {
        setCommandClass(SearchStudyCommand.class);
        setFormView("search/ind_search");
        setSuccessView("search/ind_search");
    }

}
