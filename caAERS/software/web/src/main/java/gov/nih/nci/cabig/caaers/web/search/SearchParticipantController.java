package gov.nih.nci.cabig.caaers.web.search;

import gov.nih.nci.cabig.caaers.web.study.SearchStudyCommand;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.mvc.SimpleFormController;

/**
 * @author Saurabh Agrawal
 * 
 */
public class SearchParticipantController extends SimpleFormController {

    private static final Log log = LogFactory.getLog(SearchParticipantController.class);

    public SearchParticipantController() {
        setCommandClass(SearchStudyCommand.class);
        setFormView("search/participant_search_and_edit");
        setSuccessView("search/participant_search_and_edit");
    }



}