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
public class RoutineReportController extends SearchController {
		    		
	
	public RoutineReportController() {		             
		setCommandClass(SearchStudyCommand.class);    
		setFormView("search/routine_report_search");
		setSuccessView("search/routine_report_search");
	}
	
	/*
    protected Map<String, Object> referenceData(HttpServletRequest request) throws Exception {
    	Map<String, Object> refdata = new HashMap<String, Object>();
        refdata.put("studySearchType", getConfigurationProperty().getMap().get("studySearchType"));               
	  	return refdata;
    }
	*/
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
		super.initBinder(request, binder);	
		System.out.println("I am in onSubmit ");
		Enumeration en=request.getParameterNames();
		
		if(request.getMethod().equals(METHOD_GET))
		{
			super.buildSearchResultTable(request,4);
		}
	}
	
	@Override
	protected Object formBackingObject(HttpServletRequest request) throws ServletException {	
		SearchStudyCommand sCommand = new SearchStudyCommand();
		sCommand.addSearchCriterion(new SearchCommand());
		return sCommand;
	}	

    
}