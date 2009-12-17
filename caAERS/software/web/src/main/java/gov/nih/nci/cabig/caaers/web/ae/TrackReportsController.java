package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.dao.report.ReportVersionDao;
import gov.nih.nci.cabig.caaers.domain.Attribution;
import gov.nih.nci.cabig.caaers.domain.Grade;
import gov.nih.nci.cabig.caaers.domain.Hospitalization;
import gov.nih.nci.cabig.caaers.domain.OutcomeType;
import gov.nih.nci.cabig.caaers.domain.dto.ReportVersionSearchResultsDTO;
import gov.nih.nci.cabig.caaers.domain.report.ReportVersion;
import gov.nih.nci.cabig.caaers.web.ControllerTools;
import gov.nih.nci.cabig.caaers.web.fields.DefaultInputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;
import gov.nih.nci.cabig.caaers.web.utils.WebUtils;
import gov.nih.nci.security.util.StringUtilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
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

public class TrackReportsController extends SimpleFormController {
	
	private ReportVersionDao reportVersionDao;
	
    private static final String PAGINATION_ACTION = "paginationAction";
    
    private static final String CURRENT_PAGE_NUMBER = "currentPageNumber";
    
    private InputFieldGroupMap fieldMap;
    
	
	public TrackReportsController() {
		setCommandClass(TrackReportsCommand.class);
		
		setBindOnNewForm(true);
		setFormView("admin/trackReports");
        setSuccessView("admin/trackReports");
        
        fieldMap = new InputFieldGroupMap();
        InputFieldGroup fieldGroup = new DefaultInputFieldGroup("main");

        //0
        InputField attributionField = InputFieldFactory.createSelectField("actions", "Filter on ", false, createFilterOptions());
        fieldGroup.getFields().add(attributionField);
        //1
        InputField reportIdField = InputFieldFactory.createNumberField("reportId", "Report ID ", false);
        fieldGroup.getFields().add(reportIdField);
        
        //2
        InputField startDateField = InputFieldFactory.createPastDateField("startDate", "From ", false);
        fieldGroup.getFields().add(startDateField);
        
        //3
        InputField endDateField = InputFieldFactory.createPastDateField("endDate", "To ", false);
        fieldGroup.getFields().add(endDateField);
        
        fieldMap.addInputFieldGroup(fieldGroup);
	}
	
	protected Map<Object, Object> createFilterOptions() {
        Map<Object, Object> filterOptions = new LinkedHashMap<Object, Object>();
        filterOptions.put("month", "Reports submitted in last 30 days");
        filterOptions.put("none", "All Reports");
        filterOptions.put("failed", "Failed Reports");
        filterOptions.put("report", "Report ID");
        filterOptions.put("daterange", "Date Range");
        return filterOptions;
    }

    @Override
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
    	TrackReportsCommand cmd =  new TrackReportsCommand();
    	
    	if (!isFormSubmission(request)) {
    		//cmd.setReportVersions(reportVersionDao.getAllWithTracking());
    		List<ReportVersion> rvs = reportVersionDao.getAllSubmittedReportsInLastGivenNumberOfDays(30);
    		ReportVersionSearchResultsDTO searchResultsDTO = new ReportVersionSearchResultsDTO(rvs);
    		searchResultsDTO.setFilteredResultDto(searchResultsDTO.getResultDto());
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
    	TrackReportsCommand trackReportsCommand = (TrackReportsCommand)command;
    	//ModelAndView modelAndView = super.processFormSubmission(request, response, command, errors);
    	
    		//String actions = request.getParameter("actions");

        	if(trackReportsCommand.getActions() == null || trackReportsCommand.getActions().equals("none")){
        		List<ReportVersion> rvs = reportVersionDao.getAllWithTracking();
        		ReportVersionSearchResultsDTO searchResultsDTO = new ReportVersionSearchResultsDTO(rvs);
        		trackReportsCommand.setSearchResultsDTO(searchResultsDTO);
        	} else if (trackReportsCommand.getActions().equals("failed")){
                //cmd.setReportVersions(reportVersionDao.getAllFailedReportsWithTracking());
        		List<ReportVersion> rvs = reportVersionDao.getAllFailedReportsWithTracking();
        		ReportVersionSearchResultsDTO searchResultsDTO = new ReportVersionSearchResultsDTO(rvs);
        		trackReportsCommand.setSearchResultsDTO(searchResultsDTO);                
        	} else if (trackReportsCommand.getActions().equals("month")) {
        		//cmd.setReportVersions(reportVersionDao.getAllSubmittedReportsInLastGivenNumberOfDays(30));
        		List<ReportVersion> rvs = reportVersionDao.getAllSubmittedReportsInLastGivenNumberOfDays(30);
        		ReportVersionSearchResultsDTO searchResultsDTO = new ReportVersionSearchResultsDTO(rvs);
        		trackReportsCommand.setSearchResultsDTO(searchResultsDTO);         		
        	} else if (trackReportsCommand.getActions().equals("report")) {
        		Integer reportId = trackReportsCommand.getReportId();
        		List<ReportVersion> rvs = new ArrayList<ReportVersion>();
        		if (reportId != null) {
        			ReportVersion rv = reportVersionDao.getById(reportId);
        			if (rv != null) {
        				rvs.add(rv);
        			}
        		} else {
        			errors.rejectValue("reportId", "ADM_IND_001", "reportId must not be empty");
        		}
        		//cmd.setReportVersions(rvs);
        		ReportVersionSearchResultsDTO searchResultsDTO = new ReportVersionSearchResultsDTO(rvs);
        		trackReportsCommand.setSearchResultsDTO(searchResultsDTO);          		
        	} else if (trackReportsCommand.getActions().equals("daterange")) {
        		List<ReportVersion> rvs = reportVersionDao.getAllSubmittedReportsByDateRange(trackReportsCommand.getStartDate(),trackReportsCommand.getEndDate());
        		ReportVersionSearchResultsDTO searchResultsDTO = new ReportVersionSearchResultsDTO(rvs);
        		trackReportsCommand.setSearchResultsDTO(searchResultsDTO);            		
        	}
        	request.getSession().setAttribute(getFormSessionAttributeName(), trackReportsCommand);
        	Map map = this.referenceData(request, command, errors);
            map.putAll(errors.getModel());
        	ModelAndView modelAndView = new ModelAndView(getSuccessView(), map);
        	processPagination(request, trackReportsCommand, modelAndView);
        	    		
    		//modelAndView.getModel().put("actions", actions);
    		//modelAndView.getModel().put("reportId", reportId);

    	return modelAndView;
    }
    
    private void processPagination(HttpServletRequest request, TrackReportsCommand cmd, ModelAndView modelAndView) {
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
    
    protected void processPaginationSubmission(HttpServletRequest request, TrackReportsCommand command, ModelAndView modelAndView){
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
    
    protected boolean isLastPage(HttpServletRequest request, TrackReportsCommand command){
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
    
    
    private boolean checkServiceMixStatus(){
    	//TODO
    	return true;
    }

    private boolean checkSMTPStatus(){
    	//TODO
    	return true;
    }


	public void setReportVersionDao(ReportVersionDao reportVersionDao) {
		this.reportVersionDao = reportVersionDao;
	}

}
