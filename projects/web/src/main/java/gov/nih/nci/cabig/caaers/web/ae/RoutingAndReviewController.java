package gov.nih.nci.cabig.caaers.web.ae;


import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.StudySiteDao;
import gov.nih.nci.cabig.caaers.domain.ReviewStatus;
import gov.nih.nci.cabig.caaers.domain.dto.AdverseEventReportingPeriodDTO;
import gov.nih.nci.cabig.caaers.domain.dto.RoutingAndReviewSearchResultsDTO;
import gov.nih.nci.cabig.caaers.domain.repository.AdverseEventRoutingAndReviewRepository;
import gov.nih.nci.cabig.caaers.security.SecurityUtils;
import gov.nih.nci.cabig.caaers.tools.configuration.Configuration;
import gov.nih.nci.cabig.caaers.tools.editors.EnumByNameEditor;
import gov.nih.nci.cabig.caaers.web.ControllerTools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
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

/**
 * @author Sameer Sawant
 * @author Biju Joseph
 */
public class RoutingAndReviewController extends SimpleFormController{
    private ParticipantDao participantDao;

    private StudyDao studyDao;
    
    private StudySiteDao studySiteDao;
    
    private Configuration configuration;
    
    private AdverseEventRoutingAndReviewRepository adverseEventRoutingAndReviewRepository;
    
    protected static final Collection<ReviewStatus> REVIEW_STATUS = new ArrayList<ReviewStatus>(7);
    
    private static final String PAGINATION_ACTION = "paginationAction";
    
    private static final String CURRENT_PAGE_NUMBER = "currentPageNumber";
    
    static{
    	REVIEW_STATUS.addAll(Arrays.asList(ReviewStatus.values()));
    }
    
    public RoutingAndReviewController() {
        setCommandClass(RoutingAndReviewCommand.class);
        setBindOnNewForm(true);
        setFormView("ae/selectRoutingAndReview");
        setSuccessView("ae/routingAndReviewResult");
    }
    
    @Override
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
    	RoutingAndReviewCommand command = new RoutingAndReviewCommand();
    	command.setWorkflowEnabled(configuration.get(Configuration.ENABLE_WORKFLOW));
        return command;
    }
    
    @Override
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder)
                    throws Exception {
        ControllerTools.registerGridDomainObjectEditor(binder, "participant", participantDao);
        ControllerTools.registerGridDomainObjectEditor(binder, "study", studyDao);
        ControllerTools.registerDomainObjectEditor(binder, studySiteDao);
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
        binder.registerCustomEditor(ReviewStatus.class, new EnumByNameEditor(ReviewStatus.class));
    }
    
    /**
     * It is a form submission, if participant, or study is available 
     */
    @Override
    @SuppressWarnings("unchecked")
    protected boolean isFormSubmission(HttpServletRequest request) {
    	
    	Set<String> paramNames = request.getParameterMap().keySet();
        
    	boolean hasParticipant = paramNames.contains("participant");
        boolean hasStudy = paramNames.contains("study");
        String paginationAction = (String)findInRequest(request, PAGINATION_ACTION);
        
        return hasParticipant || hasStudy || paginationAction != null;
    }
    
   
    @Override
    protected ModelAndView processFormSubmission(HttpServletRequest request,HttpServletResponse response, Object command, BindException errors)	throws Exception {
    	RoutingAndReviewCommand cmd = (RoutingAndReviewCommand)command;

		String userId = SecurityUtils.getUserLoginName();
		ModelAndView modelAndView = super.processFormSubmission(request, response, command, errors);
    	if(!errors.hasErrors()){
    		List<AdverseEventReportingPeriodDTO> rpDtos = adverseEventRoutingAndReviewRepository.findAdverseEventReportingPeriods(cmd.getParticipant(), cmd.getStudy(), cmd.getStudySite(), cmd.getReviewStatus(), userId);
        	RoutingAndReviewSearchResultsDTO searchResultsDTO = new RoutingAndReviewSearchResultsDTO(cmd.isSearchCriteriaStudyCentric(), cmd.getParticipant(), cmd.getStudy(), rpDtos);
        	cmd.setSearchResultsDTO(searchResultsDTO);
        	processPaginationSubmission(request, cmd, modelAndView);
        	
        	String numberOfResultsPerPage = (String) findInRequest(request, "numberOfResultsPerPage");
    		if(numberOfResultsPerPage == null)
    			modelAndView.getModel().put("numberOfResultsPerPage", 5);
    		else
    			modelAndView.getModel().put("numberOfResultsPerPage", Integer.parseInt(numberOfResultsPerPage));
    		
    		Integer currentPageNumber = (Integer) request.getSession().getAttribute(CURRENT_PAGE_NUMBER);
    		if(currentPageNumber.equals(1))
    			modelAndView.getModel().put("isFirstPage", true);
    		else
    			modelAndView.getModel().put("isFirstPage", false);
    		if(isLastPage(request, cmd))
    			modelAndView.getModel().put("isLastPage", true);
    		else
    			modelAndView.getModel().put("isLastPage", false);
    	}
    	
		
    	return modelAndView;
    }
    
    protected void processPaginationSubmission(HttpServletRequest request, RoutingAndReviewCommand command, ModelAndView modelAndView){
    	String action = (String) findInRequest(request, PAGINATION_ACTION);
    	String numberOfResultsPerPage = (String) findInRequest(request, "numberOfResultsPerPage");
    	Integer currPageNumber = (Integer)request.getSession().getAttribute(CURRENT_PAGE_NUMBER);
    	if(currPageNumber == null)
    		currPageNumber = 1;
    	Integer newPageNumber = 0;
    	if(action.equals("nextPage")){
    		newPageNumber = ++currPageNumber;
    	}else if(action.equals("prevPage")){
    		newPageNumber = --currPageNumber;
    	}else if(action.equals("lastPage")){
    		Float newPageNumberFloat = command.getSearchResultsDTO().getTotalResultCount() / Float.parseFloat(numberOfResultsPerPage);
    		newPageNumber = newPageNumberFloat.intValue();
    		if(command.getSearchResultsDTO().getTotalResultCount() % Integer.parseInt(numberOfResultsPerPage) > 0)
    			newPageNumber++;
    	}else if(action.equals("firstPage") || action.equals("numberOfResultsPerPage")){
    		newPageNumber = 1;
    	}
    	
    	
    	Integer startIndex = (newPageNumber - 1) * Integer.parseInt(numberOfResultsPerPage);
		Integer endIndex = newPageNumber * Integer.parseInt(numberOfResultsPerPage) - 1;
		if(endIndex > command.getSearchResultsDTO().getTotalResultCount())
			endIndex = command.getSearchResultsDTO().getTotalResultCount() - 1;
		command.getSearchResultsDTO().filterResultMap(startIndex, endIndex);
		request.getSession().setAttribute(CURRENT_PAGE_NUMBER, newPageNumber);
		modelAndView.getModel().put("totalResults", command.getSearchResultsDTO().getTotalResultCount());
		modelAndView.getModel().put("startIndex", startIndex + 1);
		modelAndView.getModel().put("endIndex", endIndex + 1);
    }
    
    protected boolean isLastPage(HttpServletRequest request, RoutingAndReviewCommand command){
    	String action = (String) findInRequest(request, PAGINATION_ACTION);
    	if(action != null && action.equals("lastPage"))
    		return true;
    	String numberOfResultsPerPage = (String) findInRequest(request, "numberOfResultsPerPage");
    	Integer currentPageNumber = (Integer)request.getSession().getAttribute(CURRENT_PAGE_NUMBER);
    	if(currentPageNumber * Integer.parseInt(numberOfResultsPerPage) > command.getSearchResultsDTO().getTotalResultCount())
    		return true;
    	
    	return false;
    }
    
    @Override
    protected void onBindAndValidate(HttpServletRequest request, Object command, BindException errors) throws Exception {
    	RoutingAndReviewCommand cmd = (RoutingAndReviewCommand)command;
    	
    	if(cmd.criteriaHasParticipant() && !cmd.criteriaHasStudy() && !cmd.criteriaHasSite()){
    		errors.reject("RAR_004", "Missing study or study site information");
    		return;
    	}
    	
    	if(cmd.criteriaHasSite() && !cmd.criteriaHasParticipant() && !cmd.criteriaHasStudy()){
    		errors.reject("RAR_005", "Missing study or subject information");
    	}
    	
    	if(!cmd.criteriaHasParticipant() && !cmd.criteriaHasSite() && !cmd.criteriaHasStudy()){
    		errors.reject("RAR_006", "Missing study or subject or study site information");
    		return;
    	}
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
    
    @Override
    @SuppressWarnings("unchecked")
    protected Map referenceData(HttpServletRequest request, Object cmd, Errors errors)
                    throws Exception {
    	return null;
    }
    
    public void setParticipantDao(ParticipantDao participantDao) {
        this.participantDao = participantDao;
    }

    public void setStudyDao(StudyDao studyDao) {
        this.studyDao = studyDao;
    }
    
    public void setStudySiteDao(StudySiteDao studySiteDao){
    	this.studySiteDao = studySiteDao;
    }
    
    
    
    public AdverseEventRoutingAndReviewRepository getAdverseEventRoutingAndReviewRepository() {
		return adverseEventRoutingAndReviewRepository;
	}
    public void setAdverseEventRoutingAndReviewRepository(
			AdverseEventRoutingAndReviewRepository adverseEventRoutingAndReviewRepository) {
		this.adverseEventRoutingAndReviewRepository = adverseEventRoutingAndReviewRepository;
	}
    
    
    public void setConfiguration(Configuration configuration){
    	this.configuration = configuration;
    }
    
    public Configuration getConfiguration(){
    	return configuration;
    }
}
