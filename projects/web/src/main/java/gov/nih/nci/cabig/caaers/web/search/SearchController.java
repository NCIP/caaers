package gov.nih.nci.cabig.caaers.web.search;


import gov.nih.nci.cabig.caaers.service.StudyService;
import gov.nih.nci.cabig.caaers.dao.CtcCategoryDao;
import gov.nih.nci.cabig.caaers.web.ListValues;
import gov.nih.nci.cabig.caaers.web.search.SearchStudyAjaxFacade;
import gov.nih.nci.cabig.caaers.web.study.SearchCommand;
import gov.nih.nci.cabig.caaers.web.study.SearchStudyCommand;
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
 * @author Krikor Krumlian
 */
public abstract class SearchController extends SimpleFormController {
		    	
	private StudyService studyService;
	private ConfigProperty configurationProperty;
	private ListValues listValues;
	private CtcCategoryDao ctcCategoryDao;
	
	public SearchController() {		             
		setCommandClass(SearchStudyCommand.class);    
		setFormView("search/study_search");
		setSuccessView("search/study_search");
	}
	
	 protected Map<String, Object> referenceData(HttpServletRequest request) throws Exception {
	    	Map<String, Object> refdata = new HashMap<String, Object>();
	        refdata.put("genders", listValues.getParticipantGender());
	        refdata.put("ethnicity", listValues.getParticipantEthnicity());
	        refdata.put("ctcCategories", ctcCategoryDao.getAll());
		  	return refdata;
	    }
	
	/*
    protected Map<String, Object> referenceData(HttpServletRequest request) throws Exception {
    	Map<String, Object> refdata = new HashMap<String, Object>();
        refdata.put("studySearchType", getConfigurationProperty().getMap().get("studySearchType"));               
	  	return refdata;
    }
		
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
		super.initBinder(request, binder);	
		System.out.println("I am in onSubmit ");
		buildSearchResultTable(request);
	}
	*/

	// TODO: I really do not like the way I am implementing this I need to find a better way  
	protected void buildSearchResultTable(HttpServletRequest request, int x)throws Exception{
		
			SearchStudyAjaxFacade searchFacade = new SearchStudyAjaxFacade();
			Context context = null;
			context = new HttpServletRequestContext(request);
        
			TableModel model = new TableModelImpl(context);
			Object viewData = null;
			try {
				 switch (x) {
				 	case 0:
				 		//viewData = searchFacade.build(model, new ArrayList());
				 		viewData = searchFacade.getTable(null, "prop0,", "n,", request);
				        break; 
		            case 1:  
		            	viewData = searchFacade.buildParticipant(model, new ArrayList()); 
		            	break;
		            case 2:  
		            	viewData = searchFacade.buildAdverseEvent(model, new ArrayList()); 
		            	break;
		            case 3:  
		            	viewData = searchFacade.buildExpeditedReport(model, new ArrayList()); 
		            	break;	
		            case 4:  
		            	viewData = searchFacade.buildRoutineReport(model, new ArrayList()); 
		            	break;		
		            default: 
		            	viewData = searchFacade.build(model, new ArrayList());
				        break; 
				 }
			} catch (Exception e) {
				e.printStackTrace();
			} 			
			
			request.setAttribute("assembler", viewData);		
	}
	 
	
	/*
	@Override
	protected Object formBackingObject(HttpServletRequest request) throws ServletException {	
		SearchStudyCommand sCommand = new SearchStudyCommand();
		sCommand.addSearchCriterion(new SearchCommand());
		return sCommand;
	}	

	@Override
	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object oCommand,
			BindException errors) throws Exception
	{
		System.out.println("I am in onSubmit ");
		SearchStudyAjaxFacade studyFacade = new SearchStudyAjaxFacade();
		Context context = null;
		context = new HttpServletRequestContext(request);
    
		TableModel model = new TableModelImpl(context);
		Object viewData = null;
		try {
			viewData = studyFacade.build(model, new ArrayList());	          
		} catch (Exception e) {
			e.printStackTrace();
		} 			
		request.setAttribute("assembler", viewData);
	
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
    	
    	return modelAndView;
	}
	*/			
	
	public StudyService getStudyService() {
		return studyService;
	}

	public void setStudyService(StudyService studyService) {
		this.studyService = studyService;
	}

	public ConfigProperty getConfigurationProperty() {
		return configurationProperty;
	}

	public void setConfigurationProperty(ConfigProperty configurationProperty) {
		this.configurationProperty = configurationProperty;
	}
	
	public ListValues getListValues() {
		return listValues;
	}

	public void setListValues(ListValues listValues) {
		this.listValues = listValues;
	}

	public void setCtcCategoryDao(CtcCategoryDao ctcCategoryDao) {
		this.ctcCategoryDao = ctcCategoryDao;
	}

	public CtcCategoryDao getCtcCategoryDao() {
		return ctcCategoryDao;
	}
	
	

}