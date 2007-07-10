package gov.nih.nci.cabig.caaers.web.search;

import gov.nih.nci.cabig.caaers.service.StudyService;
import gov.nih.nci.cabig.caaers.web.search.SearchStudyAjaxFacade;
import gov.nih.nci.cabig.caaers.web.study.SearchStudyCommand;
import gov.nih.nci.cabig.caaers.web.study.SearchCommand;
import gov.nih.nci.cabig.caaers.utils.ConfigProperty;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.extremecomponents.table.context.Context;
import org.extremecomponents.table.context.HttpServletRequestContext;
import org.extremecomponents.table.core.TableModel;
import org.extremecomponents.table.core.TableModelImpl;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

/**
 * @author Kulasekaran
 * @author Priyatam
 */
public class AdverseEventController extends SearchController {
		    		
	
	public AdverseEventController() {		             
		setCommandClass(SearchStudyCommand.class);    
		setFormView("search/ae_search");
		setSuccessView("search/ae_search");
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
			super.buildSearchResultTable(request,2);
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