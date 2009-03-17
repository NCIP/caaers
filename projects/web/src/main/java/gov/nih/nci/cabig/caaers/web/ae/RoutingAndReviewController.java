package gov.nih.nci.cabig.caaers.web.ae;


import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.StudyParticipantAssignmentDao;
import gov.nih.nci.cabig.caaers.dao.StudySiteDao;
import gov.nih.nci.cabig.caaers.domain.ReviewStatus;
import gov.nih.nci.cabig.caaers.domain.User;
import gov.nih.nci.cabig.caaers.domain.UserGroupType;
import gov.nih.nci.cabig.caaers.domain.dto.AdverseEventReportingPeriodDTO;
import gov.nih.nci.cabig.caaers.domain.dto.RoutingAndReviewSearchResultsDTO;
import gov.nih.nci.cabig.caaers.domain.repository.AdverseEventRoutingAndReviewRepository;
import gov.nih.nci.cabig.caaers.domain.repository.CSMUserRepository;
import gov.nih.nci.cabig.caaers.service.workflow.WorkflowService;
import gov.nih.nci.cabig.caaers.tools.editors.EnumByNameEditor;
import gov.nih.nci.cabig.caaers.web.ControllerTools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.acegisecurity.context.SecurityContext;
import org.springframework.beans.factory.annotation.Required;
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
    
    private StudyParticipantAssignmentDao assignmentDao;
    
    private WorkflowService workflowService;
    
    private AdverseEventRoutingAndReviewRepository adverseEventRoutingAndReviewRepository;
    
    private CSMUserRepository csmUserRepository;

    protected static final Collection<ReviewStatus> REVIEW_STATUS = new ArrayList<ReviewStatus>(7);
    
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
        return new RoutingAndReviewCommand();
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
     * It is a form submission, if participant, or (study & study site) is available 
     */
    @Override
    @SuppressWarnings("unchecked")
    protected boolean isFormSubmission(HttpServletRequest request) {
    	
    	Set<String> paramNames = request.getParameterMap().keySet();
        
    	boolean hasParticipant = paramNames.contains("participant");
        boolean hasStudy = paramNames.contains("study");
        boolean hasStudySite = paramNames.contains("studySite");
        return (hasParticipant) || (hasParticipant && hasStudy) || (hasStudy && hasStudySite);
    }
    
   
    @Override
    protected ModelAndView processFormSubmission(HttpServletRequest request,HttpServletResponse response, Object command, BindException errors)	throws Exception {
    	RoutingAndReviewCommand cmd = (RoutingAndReviewCommand)command;

    	SecurityContext context = (SecurityContext)request.getSession().getAttribute("ACEGI_SECURITY_CONTEXT");
		String userId = ((org.acegisecurity.userdetails.User)context.getAuthentication().getPrincipal()).getUsername();
    	if(!errors.hasErrors()){
    		List<AdverseEventReportingPeriodDTO> rpDtos = adverseEventRoutingAndReviewRepository.findAdverseEventReportingPeriods(cmd.getParticipant(), cmd.getStudy(), cmd.getStudySite(), cmd.getReviewStatus(), userId);
        	RoutingAndReviewSearchResultsDTO searchResultsDTO = new RoutingAndReviewSearchResultsDTO(cmd.isSearchCriteriaStudyCentric(), cmd.getParticipant(), cmd.getStudy(), rpDtos);
        	cmd.setSearchResultsDTO(searchResultsDTO);
    	}
    	
    	ModelAndView modelAndView = super.processFormSubmission(request, response, command, errors);
    	modelAndView.getModel().put("enableReportLink", Boolean.TRUE);
		if(!csmUserRepository.isSuperUser(userId)){
			User user = csmUserRepository.getUserByName(userId);
			if(user.getUserGroupTypes().contains(UserGroupType.caaers_ae_cd)){
				modelAndView.getModel().put("enableReportLink", Boolean.TRUE);
			}
		}
    	
    	return modelAndView;
    	
    }
    
    @Override
    protected void onBindAndValidate(HttpServletRequest request, Object command, BindException errors) throws Exception {
    	RoutingAndReviewCommand cmd = (RoutingAndReviewCommand)command;
    	if(!cmd.criteriaHasParticipant() && !cmd.criteriaHasStudy() ){
    		errors.reject("RAR_001", "Missing study and participant information");
    		return;
    	}
    	
    	if(!cmd.criteriaHasParticipant() && cmd.criteriaHasStudy() && !cmd.criteriaHasSite()){
    		errors.reject("RAR_002", "Missing study site information");
    		return;
    	}
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
    
    public void setAssignmentDao(StudyParticipantAssignmentDao assignmentDao){
    	this.assignmentDao = assignmentDao;
    }
    
    public void setWorkflowService(WorkflowService workflowService){
    	this.workflowService = workflowService;
    }
    
    public AdverseEventRoutingAndReviewRepository getAdverseEventRoutingAndReviewRepository() {
		return adverseEventRoutingAndReviewRepository;
	}
    public void setAdverseEventRoutingAndReviewRepository(
			AdverseEventRoutingAndReviewRepository adverseEventRoutingAndReviewRepository) {
		this.adverseEventRoutingAndReviewRepository = adverseEventRoutingAndReviewRepository;
	}
    
    @Required
    public void setCsmUserRepository(final CSMUserRepository csmUserRepository) {
        this.csmUserRepository = csmUserRepository;
    }
}
