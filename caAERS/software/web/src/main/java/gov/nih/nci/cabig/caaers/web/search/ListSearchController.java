package gov.nih.nci.cabig.caaers.web.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import gov.nih.nci.cabig.caaers.dao.SearchDao;
import gov.nih.nci.cabig.caaers.domain.Search;
import gov.nih.nci.cabig.caaers.security.SecurityUtils;
import gov.nih.nci.cabig.caaers.web.ae.ListAdverseEventsCommand;

import org.springframework.validation.Errors;
import org.springframework.web.servlet.mvc.SimpleFormController;


/**
 * This controller is used to display the saved searches.
 * @author Sameer Sawant
 */
public class ListSearchController extends SimpleFormController{
	
	private SearchDao searchDao;
	
	public ListSearchController(){
		setCommandClass(ListSearchCommand.class);
        setBindOnNewForm(true);
        setFormView("search/listSearch");
        setSuccessView("search/listSearch");
	}
	
	@Override
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
		return new ListSearchCommand();
	}
	
	@Override
    @SuppressWarnings("unchecked")
    protected boolean isFormSubmission(HttpServletRequest request) {
		return false;
	}
	
	@Override
    @SuppressWarnings("unchecked")
    protected Map referenceData(HttpServletRequest request, Object oCommand, Errors errors) throws Exception {
    	ListSearchCommand command = (ListSearchCommand) oCommand;
        Map<String, Object> refdata = new HashMap<String, Object>();
     // Collect the list of saved searches for this user and put it in the refdata.
		String loginId = SecurityUtils.getUserLoginName();
		List<Search> searchList = new ArrayList<Search>();
		searchList = searchDao.getByLogin(loginId);
		refdata.put("savedSearchList", searchList);
    	return refdata;
    }

	/**
	 * @param searchDao the searchDao to set
	 */
	public void setSearchDao(SearchDao searchDao) {
		this.searchDao = searchDao;
	}
}