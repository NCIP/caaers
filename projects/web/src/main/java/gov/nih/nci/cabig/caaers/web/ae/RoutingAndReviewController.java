package gov.nih.nci.cabig.caaers.web.ae;


import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.StudyParticipantAssignmentDao;
import gov.nih.nci.cabig.caaers.dao.StudySiteDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.ReviewStatus;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.domain.dto.AdverseEventReportingPeriodDTO;
import gov.nih.nci.cabig.caaers.domain.dto.RoutingAndReviewSearchResultsDTO;
import gov.nih.nci.cabig.caaers.domain.repository.AdverseEventRoutingAndReviewRepository;
import gov.nih.nci.cabig.caaers.service.workflow.WorkflowService;
import gov.nih.nci.cabig.caaers.tools.editors.EnumByNameEditor;
import gov.nih.nci.cabig.caaers.web.ControllerTools;
import gov.nih.nci.cabig.caaers.web.utils.WebUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
    
    private StudyParticipantAssignmentDao assignmentDao;
    
    private WorkflowService workflowService;
    
    private AdverseEventRoutingAndReviewRepository adverseEventRoutingAndReviewRepository;

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
    	
    	if(!errors.hasErrors()){
    		List<AdverseEventReportingPeriodDTO> rpDtos = adverseEventRoutingAndReviewRepository.findAdverseEventReportingPeriods(cmd.getParticipant(), cmd.getStudy(), cmd.getStudySite(), cmd.getReviewStatus());
        	RoutingAndReviewSearchResultsDTO searchResultsDTO = new RoutingAndReviewSearchResultsDTO(cmd.isSearchCriteriaStudyCentric(), cmd.getParticipant(), cmd.getStudy(), rpDtos);
        	cmd.setSearchResultsDTO(searchResultsDTO);
    	}
    	
    	ModelAndView modelAndView = super.processFormSubmission(request, response, command, errors);
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
//    	RoutingAndReviewCommand command = (RoutingAndReviewCommand) cmd;
//        Map<String, Object> refdata = new HashMap<String, Object>();
//        Map<Integer, List<String>> reportingPeriodMap = new HashMap<Integer, List<String>>();
//        Map<Integer, List<String>> reportMap = new HashMap<Integer, List<String>>();
//        if(command.getParticipant() != null && command.getStudy() != null)
//        	refdata.put("singleAssignment", Boolean.TRUE);
//        else if(command.getParticipant() != null)
//        	refdata.put("singleParticipant", Boolean.TRUE);
//        else if(command.getStudy() != null)
//        	refdata.put("singleStudy", Boolean.TRUE);
//        if(!command.getAssignmentList().isEmpty()){
//        	for(StudyParticipantAssignment assignment: command.getAssignmentList()){
//        		if(!assignment.getReportingPeriods().isEmpty()){
//        			for(AdverseEventReportingPeriod reportingPeriod: assignment.getReportingPeriods()){
//        				if(reportingPeriod.getWorkflowId() != null){
//        					List<String> states = workflowService.nextTransitions( reportingPeriod.getWorkflowId());
//        					if(!reportingPeriodMap.containsKey(reportingPeriod.getId()))
//        						reportingPeriodMap.put(reportingPeriod.getId(), states);
//        				}
//        			}
//        		}
//        	}
//        }
//        refdata.put("reviewStatusOptions", initializeReviewStatusOptions());
//        refdata.put("pageTitle", "Routing & Review || Enter Search Parameters");
//        refdata.put("bodyTitle", "Routing & Review: Enter Search Parameters");
//        refdata.put("instructions","Please enter the search parameters (Update this instruction).");
//        return refdata;
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
}
