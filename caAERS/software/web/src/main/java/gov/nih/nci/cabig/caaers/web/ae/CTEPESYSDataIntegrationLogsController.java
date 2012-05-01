package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.dao.IntegrationLogDao;
import gov.nih.nci.cabig.caaers.dao.query.IntegrationLogQuery;
import gov.nih.nci.cabig.caaers.domain.IntegrationLog;
import gov.nih.nci.cabig.caaers.domain.IntegrationLogDetail;
import gov.nih.nci.cabig.caaers.domain.dto.CTEPESYSIntegrationLogSearchResultsDTO;
import gov.nih.nci.cabig.caaers.web.ControllerTools;
import gov.nih.nci.cabig.caaers.web.fields.DefaultInputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;
import gov.nih.nci.security.util.StringUtilities;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

public class CTEPESYSDataIntegrationLogsController extends SimpleFormController {
	
	private IntegrationLogDao integrationLogDao;
	
	public void setIntegrationLogDao(IntegrationLogDao integrationLogDao) {
		this.integrationLogDao = integrationLogDao;
	}

	private static final String PAGINATION_ACTION = "paginationAction";
    
    private static final String CURRENT_PAGE_NUMBER = "currentPageNumber";
    
    private InputFieldGroupMap fieldMap;
    
    private Map<Object, Object> statusMap = new LinkedHashMap<Object, Object>();
    
    private Map<Object, Object> serviceMap = new LinkedHashMap<Object, Object>();
    
	
	public CTEPESYSDataIntegrationLogsController() {
		setCommandClass(CTEPESYSDataIntegrationLogsCommand.class);
		
		setBindOnNewForm(true);
		setFormView("admin/ctepesys_integration_logs");
        setSuccessView("admin/ctepesys_integration_logs");
        
        
        statusMap.put("", "Please select");
        statusMap.put("Success", "Success");
        statusMap.put("In Progress", "In Progress");
        statusMap.put("Failure", "Failure");
        
        serviceMap.put("", "Please select");
        serviceMap.put("SearchStudy", "SearchStudy");
        serviceMap.put("GetStudyDetails", "GetStudyDetails");
        serviceMap.put("GetStudyDiseases", "GetStudyDiseases");
        serviceMap.put("GetStudyTreatmentAssignments", "GetStudyTreatmentAssignments");
        serviceMap.put("GetStudyAgents", "GetStudyAgents");
        serviceMap.put("GetStudyOrganizations", "GetStudyOrganizations");
        serviceMap.put("GetASAEL", "GetASAEL");
        serviceMap.put("GetOrganizationLOV", "GetOrganizationLOV");
        serviceMap.put("GetAgentsLOV", "GetAgentsLOV");
        serviceMap.put("GetCTCAELOV", "GetCTCAELOV");
        serviceMap.put("GetDeviceLOV", "GetDeviceLOV");
        serviceMap.put("GetPre-ExistingConditionsLOV", "GetPre-ExistingConditionsLOV");
        serviceMap.put("GetTherapiesLOV", "GetTherapiesLOV");
        serviceMap.put("GetAgentDoseUOMLOV", "GetAgentDoseUOMLOV");
        serviceMap.put("GetLabLOV", "GetLabLOV");
        
        fieldMap = new InputFieldGroupMap();
        InputFieldGroup fieldGroup = new DefaultInputFieldGroup("main");

        //0
        InputField attributionField = InputFieldFactory.createSelectField("actions", "Filter on", false, createFilterOptions());
        fieldGroup.getFields().add(attributionField);
        
        //1
        InputField entityField = InputFieldFactory.createSelectField("service", "Service", true, serviceMap);
        fieldGroup.getFields().add(entityField);
        
        //2
        InputField synchStatusField = InputFieldFactory.createSelectField("status", "Status", true, statusMap);
        fieldGroup.getFields().add(synchStatusField);
        
        //3
        InputField startDateField = InputFieldFactory.createPastDateField("startDate", "From", false);
        fieldGroup.getFields().add(startDateField);
        
        //4
        InputField endDateField = InputFieldFactory.createPastDateField("endDate", "To", false);
        fieldGroup.getFields().add(endDateField);
        
        fieldMap.addInputFieldGroup(fieldGroup);
	}
	
	protected Map<Object, Object> createFilterOptions() {
        Map<Object, Object> filterOptions = new LinkedHashMap<Object, Object>();
        filterOptions.put("week", "Data synched in last 7 days");
        filterOptions.put("service", "Service");
        filterOptions.put("status", "Status");
        filterOptions.put("daterange", "Date Range");
        return filterOptions;
    }

    @Override
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
    	CTEPESYSDataIntegrationLogsCommand cmd =  new CTEPESYSDataIntegrationLogsCommand();
    	
    	if (!isFormSubmission(request)) {
    		List<IntegrationLog> rvs = integrationLogDao.searchIntegrationLogsInLastGivenNumberOfDays(7);
    		CTEPESYSIntegrationLogSearchResultsDTO searchResultsDTO = new CTEPESYSIntegrationLogSearchResultsDTO(rvs);
    		searchResultsDTO.setFilteredResults(searchResultsDTO.getResults());
    		cmd.setSearchResultsDTO(searchResultsDTO);
    	}
    	
    	return cmd;
    }
    
    @Override
	protected void initBinder(final HttpServletRequest request,final ServletRequestDataBinder binder) throws Exception {
		super.initBinder(request, binder);
		binder.registerCustomEditor(Date.class, ControllerTools.getDateEditor(false));
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
		//binder.registerCustomEditor(Integer.class, ControllerTools.getDateEditor(false));
	}

    
    /**
     * It is a form submission, if participant, or study is available 
     */
    @Override
    @SuppressWarnings("unchecked")
    protected boolean isFormSubmission(HttpServletRequest request) {

    	Set<String> paramNames = request.getParameterMap().keySet();
    	boolean hasActions = paramNames.contains("actions");
    	String paginationAction = (String)findInRequest(request, PAGINATION_ACTION);
    	
    	return hasActions || paginationAction != null;
    }
    
    @Override
    protected ModelAndView onSubmit(HttpServletRequest request,HttpServletResponse response, Object command, BindException errors)	throws Exception {
    	CTEPESYSDataIntegrationLogsCommand cmd = (CTEPESYSDataIntegrationLogsCommand)command;

        	if (cmd.getActions().equals("week")) {
        		List<IntegrationLog> rvs = integrationLogDao.searchIntegrationLogsInLastGivenNumberOfDays(7);
        		CTEPESYSIntegrationLogSearchResultsDTO searchResults = new CTEPESYSIntegrationLogSearchResultsDTO(rvs);
        		cmd.setSearchResultsDTO(searchResults);          		
        	} else if (cmd.getActions().equals("daterange")) {
        		List<IntegrationLog> rvs = integrationLogDao.searchIntegrationLogsForDateRange(cmd.getStartDate(),cmd.getEndDate());
        		CTEPESYSIntegrationLogSearchResultsDTO searchResults = new CTEPESYSIntegrationLogSearchResultsDTO(rvs);
        		cmd.setSearchResultsDTO(searchResults);          		
        	} else if (cmd.getActions().equals("status")) {
        		IntegrationLogQuery query = new IntegrationLogQuery();
        		query.filterBySynchStatus(cmd.getStatus());
        		List<IntegrationLog> rvs = integrationLogDao.searchIntegrationLogs(query);
        		CTEPESYSIntegrationLogSearchResultsDTO searchResults = new CTEPESYSIntegrationLogSearchResultsDTO(rvs);
        		cmd.setSearchResultsDTO(searchResults);          		
        	}else if (cmd.getActions().equals("service")) {
        		IntegrationLogQuery query = new IntegrationLogQuery();
        		query.filterByEntity(cmd.getService());
        		List<IntegrationLog> rvs = integrationLogDao.searchIntegrationLogs(query);
        		CTEPESYSIntegrationLogSearchResultsDTO searchResults = new CTEPESYSIntegrationLogSearchResultsDTO(rvs);
        		cmd.setSearchResultsDTO(searchResults);      		
        	}
        	request.getSession().setAttribute(getFormSessionAttributeName(), cmd);
        	Map map = this.referenceData(request, command, errors);
            map.putAll(errors.getModel());
        	ModelAndView modelAndView = new ModelAndView(getSuccessView(), map);
        	processPagination(request, cmd, modelAndView);
        	    		
    	return modelAndView;
    }
    
    private void processPagination(HttpServletRequest request, CTEPESYSDataIntegrationLogsCommand cmd, ModelAndView modelAndView) {
    	processPaginationSubmission(request, cmd, modelAndView);
    	String numberOfResultsPerPage = (String) findInRequest(request, "numberOfResultsPerPage");
		if(StringUtilities.isBlank(numberOfResultsPerPage))
			modelAndView.getModel().put("numberOfResultsPerPage", 15);
		else
			modelAndView.getModel().put("numberOfResultsPerPage", Integer.parseInt(numberOfResultsPerPage));
		
		Integer currentPageNumber = (Integer) request.getSession().getAttribute(CURRENT_PAGE_NUMBER);
		
    	if(currentPageNumber == null)
    		currentPageNumber = 1;
    	
		if(currentPageNumber.equals(1))
			modelAndView.getModel().put("isFirstPage", true);
		else
			modelAndView.getModel().put("isFirstPage", false);
		if(isLastPage(request, cmd))
			modelAndView.getModel().put("isLastPage", true);
		else
			modelAndView.getModel().put("isLastPage", false);

    }
    
    protected void processPaginationSubmission(HttpServletRequest request, CTEPESYSDataIntegrationLogsCommand command, ModelAndView modelAndView){
    	String action = (String) findInRequest(request, PAGINATION_ACTION);
    	String numberOfResultsPerPage = (String) findInRequest(request, "numberOfResultsPerPage");
    	Integer currPageNumber = (Integer)request.getSession().getAttribute(CURRENT_PAGE_NUMBER);
    	if (StringUtilities.isBlank(numberOfResultsPerPage)) {
    		numberOfResultsPerPage = "15";
    	}
    	if (StringUtilities.isBlank(action)) {
    		action = "firstPage";
    	}    	
    	if(currPageNumber == null)
    		currPageNumber = 1;
    	Integer newPageNumber = 0;
    	if(action.equals("nextPage")){
    		newPageNumber = ++currPageNumber;
    	}else if(action.equals("prevPage")){
    		newPageNumber = --currPageNumber;
    	}else if(action.equals("lastPage")){
    		Float newPageNumberFloat = command.getSearchResultsDTO().getResultCount() / Float.parseFloat(numberOfResultsPerPage);
    		newPageNumber = newPageNumberFloat.intValue();
    		if(command.getSearchResultsDTO().getResultCount() % Integer.parseInt(numberOfResultsPerPage) > 0)
    			newPageNumber++;
    	}else if(action.equals("firstPage") || action.equals("numberOfResultsPerPage")){
    		newPageNumber = 1;
    	}
    	
    	
    	Integer startIndex = (newPageNumber - 1) * Integer.parseInt(numberOfResultsPerPage);
		Integer endIndex = newPageNumber * Integer.parseInt(numberOfResultsPerPage) - 1;
		if(endIndex > command.getSearchResultsDTO().getResultCount())
			endIndex = command.getSearchResultsDTO().getResultCount() - 1;
		command.getSearchResultsDTO().filterResult(startIndex, endIndex);
		request.getSession().setAttribute(CURRENT_PAGE_NUMBER, newPageNumber);
		modelAndView.getModel().put("totalResults", command.getSearchResultsDTO().getResultCount());
		modelAndView.getModel().put("startIndex", startIndex + 1);
		modelAndView.getModel().put("endIndex", endIndex + 1);
    }
    
    protected boolean isLastPage(HttpServletRequest request, CTEPESYSDataIntegrationLogsCommand command){
    	String action = (String) findInRequest(request, PAGINATION_ACTION);
    	if(action != null && action.equals("lastPage"))
    		return true;
    	String numberOfResultsPerPage = (String) findInRequest(request, "numberOfResultsPerPage");
    	Integer currentPageNumber = (Integer)request.getSession().getAttribute(CURRENT_PAGE_NUMBER);
    	
    	if (StringUtilities.isBlank(numberOfResultsPerPage)) {
    		numberOfResultsPerPage = "15";
    	}
    	if (StringUtilities.isBlank(action)) {
    		action = "firstPage";
    	}    	
    	if(currentPageNumber == null) {
    		currentPageNumber = 1;
    	}
    	
    	if(currentPageNumber * Integer.parseInt(numberOfResultsPerPage) > command.getSearchResultsDTO().getResultCount())
    		return true;
    	
    	return false;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    protected Map referenceData(final HttpServletRequest request, final Object command,
                    final Errors errors) throws Exception {

        Map<Object, Object> refDataMap = new LinkedHashMap<Object, Object>();
        refDataMap.put("fieldGroups", fieldMap);

        return refDataMap;

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

}
