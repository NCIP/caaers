package gov.nih.nci.cabig.caaers.web.search;

import org.springframework.web.servlet.mvc.SimpleFormController;

/**
 * 
 * @author Monish
 *
 */
public class SearchUserController extends SimpleFormController {
    
	public SearchUserController() {
        setCommandClass(SearchUserCommand.class);
        setFormView("search/user_search");
        setSuccessView("search/user_search");
    }
}
