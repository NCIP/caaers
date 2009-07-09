package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.dao.report.ReportVersionDao;
import gov.nih.nci.cabig.caaers.domain.dto.ReportVersionSearchResultsDTO;
import gov.nih.nci.cabig.caaers.domain.report.ReportVersion;
import gov.nih.nci.security.util.StringUtilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

public class TrackReportsController extends SimpleFormController {
	
	private ReportVersionDao reportVersionDao;
	
    private static final String PAGINATION_ACTION = "paginationAction";
    
    private static final String CURRENT_PAGE_NUMBER = "currentPageNumber";
    
	
	public TrackReportsController() {
		setCommandClass(TrackReportsCommand.class);
		setBindOnNewForm(true);
		setFormView("admin/trackReports");
        setSuccessView("admin/trackReports");
	}

    @Override
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
    	TrackReportsCommand cmd =  new TrackReportsCommand();
    	
    	if (!isFormSubmission(request)) {
    		//cmd.setReportVersions(reportVersionDao.getAllWithTracking());
    		List<ReportVersion> rvs = reportVersionDao.getAllWithTracking();
    		ReportVersionSearchResultsDTO searchResultsDTO = new ReportVersionSearchResultsDTO(rvs);
    		searchResultsDTO.setFilteredResultDto(searchResultsDTO.getResultDto());
    		cmd.setSearchResultsDTO(searchResultsDTO);
    	}
    	
    	return cmd;
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
    protected ModelAndView processFormSubmission(HttpServletRequest request,HttpServletResponse response, Object command, BindException errors)	throws Exception {
    	TrackReportsCommand cmd = (TrackReportsCommand)command;
    	ModelAndView modelAndView = super.processFormSubmission(request, response, command, errors);
    	if(!errors.hasErrors()){
    		String actions = request.getParameter("actions");
    		String reportId = "";
        	if(actions == null || actions.equals("none")){
        		List<ReportVersion> rvs = reportVersionDao.getAllWithTracking();
        		ReportVersionSearchResultsDTO searchResultsDTO = new ReportVersionSearchResultsDTO(rvs);
        		cmd.setSearchResultsDTO(searchResultsDTO);
        	} else if (actions.equals("failed")){
                //cmd.setReportVersions(reportVersionDao.getAllFailedReportsWithTracking());
        		List<ReportVersion> rvs = reportVersionDao.getAllFailedReportsWithTracking();
        		ReportVersionSearchResultsDTO searchResultsDTO = new ReportVersionSearchResultsDTO(rvs);
        		cmd.setSearchResultsDTO(searchResultsDTO);                
        	} else if (actions.equals("month")) {
        		//cmd.setReportVersions(reportVersionDao.getAllSubmittedReportsInLastGivenNumberOfDays(30));
        		List<ReportVersion> rvs = reportVersionDao.getAllSubmittedReportsInLastGivenNumberOfDays(30);
        		ReportVersionSearchResultsDTO searchResultsDTO = new ReportVersionSearchResultsDTO(rvs);
        		cmd.setSearchResultsDTO(searchResultsDTO);         		
        	} else if (actions.equals("report")) {
        		reportId = request.getParameter("reportId");
        		List<ReportVersion> rvs = new ArrayList<ReportVersion>();
        		if (!StringUtilities.isBlank(reportId)) {
        			ReportVersion rv = reportVersionDao.getById(Integer.parseInt(reportId));
        			rvs.add(rv);
        		}
        		//cmd.setReportVersions(rvs);
        		ReportVersionSearchResultsDTO searchResultsDTO = new ReportVersionSearchResultsDTO(rvs);
        		cmd.setSearchResultsDTO(searchResultsDTO);          		
        	}
        	processPagination(request, cmd, modelAndView);
        	    		
    		modelAndView.getModel().put("actions", actions);
    		modelAndView.getModel().put("reportId", reportId);

    	}
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
    	//AdvancedSearchCommand cmd = (AdvancedSearchCommand) command;
        Map<String, Object> refdata = new HashMap<String, Object>();
        


        return refdata;
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
