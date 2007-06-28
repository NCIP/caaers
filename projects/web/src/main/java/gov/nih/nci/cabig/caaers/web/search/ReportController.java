package gov.nih.nci.cabig.caaers.web.search;

import gov.nih.nci.cabig.caaers.web.study.SearchStudyCommand;
import gov.nih.nci.cabig.caaers.web.study.SearchCommand;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.ServletRequestDataBinder;

/**
 * @author Krikor Krumlian
 * 
 */
public class ReportController extends SearchController {
		    		
	
	public ReportController() {		             
		setCommandClass(SearchStudyCommand.class);    
		setFormView("search/report_search");
		setSuccessView("search/report_search");
	}
	
    protected Map<String, Object> referenceData(HttpServletRequest request) throws Exception {
    	Map<String, Object> refdata = new HashMap<String, Object>();
        refdata.put("studySearchType", getConfigurationProperty().getMap().get("studySearchType"));               
	  	return refdata;
    }
		
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
		super.initBinder(request, binder);	
		System.out.println("I am in onSubmit ");
		Enumeration en=request.getParameterNames();
		
		if(request.getMethod().equals(METHOD_GET))
		{
			super.buildSearchResultTable(request,3);
		}
	}
	
	@Override
	protected Object formBackingObject(HttpServletRequest request) throws ServletException {	
		SearchStudyCommand sCommand = new SearchStudyCommand();
		sCommand.addSearchCriterion(new SearchCommand());
		return sCommand;
	}	

	/*
	@Override
	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object oCommand,
			BindException errors) throws Exception
	{
		super.buildSearchResultTable(request,2);
		
		int index = Integer.parseInt(request.getParameter("_selected"));
		String action = request.getParameter("_action");
		
		if("addCriteria".equals(action))
		{
			((SearchStudyCommand)oCommand).getSearchCriteria().add(new SearchCommand());
		}
		else if ("removeCriteria".equals(action))
		{
			((SearchStudyCommand)oCommand).getSearchCriteria().remove(index);
		}		
		
		Map map = errors.getModel();
		map.put("studySearchType",getConfigurationProperty().getMap().get("studySearchType"));  
    	ModelAndView modelAndView= new ModelAndView(getSuccessView(), map);
     	
    	// needed for saving session state
    	request.getSession().setAttribute(getFormSessionAttributeName(), oCommand);
    	
		ModelAndView modelAndView= new ModelAndView(getSuccessView());
    	return modelAndView;
	}
	*/
    
}