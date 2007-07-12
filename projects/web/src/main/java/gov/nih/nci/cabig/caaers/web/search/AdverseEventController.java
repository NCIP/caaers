package gov.nih.nci.cabig.caaers.web.search;

import gov.nih.nci.cabig.caaers.web.study.SearchStudyCommand;
import gov.nih.nci.cabig.caaers.web.study.SearchCommand;

import java.util.Enumeration;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.ServletRequestDataBinder;


/**
 * @author Krikor Krumlian
 */
public class AdverseEventController extends SearchController {
		    		
	
	public AdverseEventController() {		             
		setCommandClass(SearchStudyCommand.class);    
		setFormView("search/ae_search");
		setSuccessView("search/ae_search");
	}
		
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
		super.initBinder(request, binder);	
		System.out.println("I am in onSubmit ");
		Enumeration en=request.getParameterNames();
		
		if(request.getMethod().equals(METHOD_GET))
		{
			super.buildSearchResultTable(request,2);
		}
	}
	
	@Override
	protected Object formBackingObject(HttpServletRequest request) throws ServletException {	
		SearchStudyCommand sCommand = new SearchStudyCommand();
		sCommand.addSearchCriterion(new SearchCommand());
		return sCommand;
	}	
    
}