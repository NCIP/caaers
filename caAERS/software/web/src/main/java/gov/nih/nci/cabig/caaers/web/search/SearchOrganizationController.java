package gov.nih.nci.cabig.caaers.web.search;

import gov.nih.nci.cabig.caaers.web.study.SearchStudyCommand;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.mvc.SimpleFormController;

public class SearchOrganizationController extends SimpleFormController {
    private static final Log log = LogFactory.getLog(SearchOrganizationController.class);

    public SearchOrganizationController() {
        setCommandClass(SearchStudyCommand.class);
        setFormView("search/organization_search");
        setSuccessView("search/organization_search");
    }

}
