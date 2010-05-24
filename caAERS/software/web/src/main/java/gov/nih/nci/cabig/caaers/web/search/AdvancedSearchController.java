package gov.nih.nci.cabig.caaers.web.search;

import java.io.InputStream;
import java.io.StringReader;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

import gov.nih.nci.cabig.caaers.dao.SearchDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.Search;
import gov.nih.nci.cabig.caaers.security.SecurityUtils;
import gov.nih.nci.cabig.caaers.tools.spring.tabbedflow.AutomaticSaveAjaxableFormController;
import gov.nih.nci.cabig.caaers.web.ControllerTools;
import gov.nih.nci.cabig.caaers.web.search.ui.AdvancedSearchUi;
import gov.nih.nci.cabig.caaers.web.search.ui.CriteriaParameter;
import gov.nih.nci.cabig.caaers.web.search.ui.DependentObject;
import gov.nih.nci.cabig.caaers.web.search.ui.SaveSearch;
import gov.nih.nci.cabig.caaers.web.search.ui.SearchTargetObject;
import gov.nih.nci.cabig.caaers.web.search.ui.SelectedColumn;
import gov.nih.nci.cabig.caaers.web.search.ui.ViewColumn;
import gov.nih.nci.cabig.ctms.web.tabs.Flow;
import gov.nih.nci.cabig.ctms.web.tabs.FlowFactory;
import gov.nih.nci.cabig.ctms.web.tabs.Tab;


/**
 * This is the controller for the advanced search page.
 * @author Sameer Sawant
 */
public class AdvancedSearchController extends AutomaticSaveAjaxableFormController<AdvancedSearchCommand, Search, SearchDao>{
	
	private static final Log log = LogFactory.getLog(AdvancedSearchController.class);
	public static final String AJAX_SUBVIEW_PARAMETER = "subview";
	private SearchDao searchDao;
	private AdvancedSearchUi advancedSearchUi;
	
	
	public AdvancedSearchController(){
		setBindOnNewForm(true);
		setCommandClass(AdvancedSearchCommand.class);
		
		InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("advancedSearch-ui.xml");
        Unmarshaller unmarshaller;
		try {
			unmarshaller = JAXBContext.newInstance("gov.nih.nci.cabig.caaers.web.search.ui").createUnmarshaller();
			advancedSearchUi = (AdvancedSearchUi) unmarshaller.unmarshal(inputStream);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
    @SuppressWarnings("unchecked")
    protected boolean isFormSubmission(HttpServletRequest request) {
        Set<String> paramNames = request.getParameterMap().keySet();
        boolean fromSearchListPage = false;
        fromSearchListPage = paramNames.contains("runSavedQuery");
        String action = (String) findInRequest(request, "_action");
        if(fromSearchListPage) 
        	return true;
        else if(action != null && (action.equals("flatView")))
        	return true;
        else
        	return super.isFormSubmission(request);
    }
	
	@Override
	protected SearchDao getDao() {
		return null;
	}
	
	@Override
	protected void onBindOnNewForm(HttpServletRequest request, Object command,BindException errors) throws Exception {
		super.onBindOnNewForm(request, command, errors);
	}
	
	/**
	 * Will return the {@link AdverseEventReportingPeriod} 
	 */
	@Override
	protected Search getPrimaryDomainObject(AdvancedSearchCommand cmd) {
		return new Search();
	}
	
	@Override
	protected ModelAndView processFinish(HttpServletRequest request, HttpServletResponse response, Object oCommand, BindException errors) throws Exception {
		return null;
	}
	
	@Override
    protected void initBinder(final HttpServletRequest request, final ServletRequestDataBinder binder) throws Exception {
        super.initBinder(request, binder);
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
        binder.registerCustomEditor(Date.class, ControllerTools.getDateEditor(false));
    }
	
	@Override
	protected Object formBackingObject(HttpServletRequest request)	throws Exception {
		AdvancedSearchCommand command = new AdvancedSearchCommand(advancedSearchUi);
		String searchName = request.getParameter("searchName");
		if(searchName != null){
			// This is when the user clicks on one of the saved searches.
			String loginId = SecurityUtils.getUserLoginName();
			List<Search> searchList = searchDao.getByLoginAndName(loginId, searchName);
			Unmarshaller unmarshaller;
			SaveSearch savedSearch = null;
			try {
				unmarshaller = JAXBContext.newInstance("gov.nih.nci.cabig.caaers.web.search.ui").createUnmarshaller();
				StringReader reader = new StringReader(searchList.get(0).getCriteriaXml()); 
				savedSearch = (SaveSearch) unmarshaller.unmarshal(reader);
				for(SearchTargetObject targetObject: advancedSearchUi.getSearchTargetObject()){
					if(targetObject.getClassName().equals(savedSearch.getTargetClassName()))
						command.setSearchTargetObject(targetObject);
				}
				for(CriteriaParameter criteriaParameter: savedSearch.getCriteriaParameter()){
					AdvancedSearchCriteriaParameter parameter = new AdvancedSearchCriteriaParameter();
					parameter.setObjectName(criteriaParameter.getObjectName());
					parameter.setDependentObjectName(criteriaParameter.getDependentObjectName());
					parameter.setAttributeName(criteriaParameter.getAttributeName());
					parameter.setPredicate(criteriaParameter.getPredicate());
					parameter.setValue(criteriaParameter.getValue());
					parameter.setDisplayValue(criteriaParameter.getDisplayValue());
					command.getCriteriaParameters().add(parameter);
				}
				// Setup the view selected.
				for(SelectedColumn selectedColumn: savedSearch.getSelectedColumn()){
					DependentObject dObject = AdvancedSearchUiUtil.getDependentObjectByName(command.getSearchTargetObject(), 
							selectedColumn.getDependentObjectClassName());
					dObject.setInView(true);
					for(ViewColumn vCol: dObject.getViewColumn()){
						if(selectedColumn.getColumnAttribute().equals(vCol.getColumnAttribute()))
							vCol.setSelected(true);
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return command;
	}
	
	@Override
	public FlowFactory<AdvancedSearchCommand> getFlowFactory() {
		return new FlowFactory<AdvancedSearchCommand>() {
			public Flow<AdvancedSearchCommand> createFlow(AdvancedSearchCommand cmd) {
				
            	/**
            	 * Third level tabs are secured now , Any changes in this flow needs to reflect in 
            	 * applicationContext-web-security.xml <util:map id="tabObjectPrivilegeMap"> 
            	 */
				Flow<AdvancedSearchCommand> flow = new Flow<AdvancedSearchCommand>("Advanced Search || Enter criteria");
				flow.addTab(new AdvancedSearchCriteriaTab<AdvancedSearchCommand>());
				flow.addTab(new AdvancedSearchViewTab<AdvancedSearchCommand>());
				flow.addTab(new AdvancedSearchResultsTab<AdvancedSearchCommand>("Search results", "Search results", "search/advancedSearchResults"));
				return flow;
			}
		};
	}
	
	/**
	 * Supress the validation in the following cases.
	 *   1 - When we go back
	 *   2 - When it is an Ajax request, which dont has form submission
	 */
	
	@Override
    protected boolean suppressValidation(final HttpServletRequest request) {

        Object isAjax = findInRequest(request, AJAX_SUBVIEW_PARAMETER);
        if (isAjax != null) return true;
        
        //check current page and next page
        int currPage = getCurrentPage(request);
    	int targetPage = getTargetPage(request, currPage);
        return targetPage < currPage;

    }
	
	/**
     * Returns the value associated with the <code>attributeName</code>, if present in
     * HttpRequest parameter, if not available, will check in HttpRequest attribute map.
     */
    protected Object findInRequest(final ServletRequest request, final String attributName) {

        Object attr = request.getParameter(attributName);
        if (attr == null) {
            attr = request.getAttribute(attributName);
        }
        return attr;
    }
    
    
    /**
     * Adds ajax sub-page view capability. TODO: factor this into main tabbed flow controller.
     */
    @Override
    protected String getViewName(HttpServletRequest request, Object command, int page) {
        String subviewName = request.getParameter(AJAX_SUBVIEW_PARAMETER);
        if (subviewName != null) {
            // for side-effects:
            super.getViewName(request, command, page);
            return "search/ajax/" + subviewName;
        }else {
            return super.getViewName(request, command, page);
        }
    }
    
    @Override
	protected boolean shouldSave(HttpServletRequest request,AdvancedSearchCommand command,Tab<AdvancedSearchCommand> tab) {
		return false;
	}
    
    public void setSearchDao(SearchDao searchDao){
    	this.searchDao = searchDao;
    }
}