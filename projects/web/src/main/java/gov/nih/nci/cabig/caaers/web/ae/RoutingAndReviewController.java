package gov.nih.nci.cabig.caaers.web.ae;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

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
import gov.nih.nci.cabig.caaers.service.workflow.WorkflowService;
import gov.nih.nci.cabig.caaers.tools.editors.EnumByNameEditor;
import gov.nih.nci.cabig.caaers.web.ControllerTools;
import gov.nih.nci.cabig.caaers.web.utils.WebUtils;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.mvc.SimpleFormController;

/**
 * @author Sameer Sawant
 */
public class RoutingAndReviewController extends SimpleFormController{
    private ParticipantDao participantDao;

    private StudyDao studyDao;
    
    private StudySiteDao studySiteDao;
    
    private StudyParticipantAssignmentDao assignmentDao;
    
    Map<Object,Object> reviewStatusOptionsMap;
    private WorkflowService workflowService;
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
        binder.registerCustomEditor(ReviewStatus.class, new EnumByNameEditor(
        		ReviewStatus.class));
    }
    
    @Override
    @SuppressWarnings("unchecked")
    protected boolean isFormSubmission(HttpServletRequest request) {
    	Set<String> paramNames = request.getParameterMap().keySet();
        boolean hasParticipant = paramNames.contains("participant");
        boolean hasStudy = paramNames.contains("study");
        boolean hasReviewStatus = paramNames.contains("reviewStatus");
        boolean hasStudySite = paramNames.contains("studySite");

        return hasParticipant || hasStudy && hasStudySite;
    }
    
    
    @Override
    protected void onBind(HttpServletRequest request, Object cmd, BindException errors) throws Exception{
    	super.onBind(request, cmd, errors);
    	
    	RoutingAndReviewCommand command = (RoutingAndReviewCommand) cmd;
    	Participant participant = command.getParticipant();
    	Study study = command.getStudy();
    	StudySite site = command.getStudySite();
    	if(command.getAssignmentList() != null)
    		command.getAssignmentList().clear();
    	
    	if(study != null && participant != null){
    		command.getAssignmentList().add(assignmentDao.getAssignment(participant, study));
    	}else if(study != null && site != null){
    		command.getAssignmentList().addAll(site.getStudyParticipantAssignments());
    	}else if(participant != null){
    		command.getAssignmentList().addAll(participant.getAssignments());
    	}
    	
    	// reset attributes of command
    	command.setStudy(study);
    	command.setParticipant(participant);
    	command.setStudySite(site);
    }
    
    @Override
    protected void onBindAndValidate(HttpServletRequest request, Object cmd,
    				BindException errors) throws Exception {
    	/*super.onBindAndValidate(request, cmd, errors);
    	RoutingAndReviewCommand command = (RoutingAndReviewCommand)cmd;
    	boolean noStudy = command.getStudy() == null;
    	boolean noParticipant = command.getParticipant() == null;
    	boolean noSite = command.getStudySite() == null;
    	
    	if(!noSite && noStudy)
    		errors.reject("REQUIRED", "Study is required.");
    	if(noSite && noStudy && noParticipant)
    		errors.reject("REQUIRED", "Insuffient data entered.");
    	if(!noStudy && noSite)
    		errors.reject("REQUIRED", "Site is required.");*/
    }
    
    /*@Override
    protected void onBindAndValidate(HttpServletRequest request, Object command,
                    BindException errors) throws Exception {
        super.onBindAndValidate(request, command, errors);
        ListAdverseEventsCommand listAECmd = (ListAdverseEventsCommand) command;
        boolean noStudy = listAECmd.getStudy() == null;
        boolean noParticipant = listAECmd.getParticipant() == null;
        if (noStudy) errors.rejectValue("study", "REQUIRED", "Missing study");
        if (noParticipant) errors.rejectValue("participant", "REQUIRED", "Missing subject");
        if (!(noStudy || noParticipant) && listAECmd.getAssignment() == null) {
            errors.reject("REQUIRED", "The subject is not assigned to the provided study");
        }
        
        if(!errors.hasErrors()){
        	//if there is no validation error, update the report submitability
        	listAECmd.updateSubmittability();
        }
    }
*/

    @Override
    @SuppressWarnings("unchecked")
    protected Map referenceData(HttpServletRequest request, Object cmd, Errors errors)
                    throws Exception {
    	RoutingAndReviewCommand command = (RoutingAndReviewCommand) cmd;
        Map<String, Object> refdata = new HashMap<String, Object>();
        Map<Integer, List<String>> reportingPeriodMap = new HashMap<Integer, List<String>>();
        Map<Integer, List<String>> reportMap = new HashMap<Integer, List<String>>();
        if(command.getParticipant() != null && command.getStudy() != null)
        	refdata.put("singleAssignment", Boolean.TRUE);
        else if(command.getParticipant() != null)
        	refdata.put("singleParticipant", Boolean.TRUE);
        else if(command.getStudy() != null)
        	refdata.put("singleStudy", Boolean.TRUE);
        if(!command.getAssignmentList().isEmpty()){
        	for(StudyParticipantAssignment assignment: command.getAssignmentList()){
        		if(!assignment.getReportingPeriods().isEmpty()){
        			for(AdverseEventReportingPeriod reportingPeriod: assignment.getReportingPeriods()){
        				if(reportingPeriod.getWorkflowId() != null){
        					List<String> states = workflowService.nextTransitions("routineFlow", reportingPeriod.getWorkflowId());
        					if(!reportingPeriodMap.containsKey(reportingPeriod.getId()))
        						reportingPeriodMap.put(reportingPeriod.getId(), states);
        				}
        			}
        		}
        	}
        }
        refdata.put("reviewStatusOptions", initializeReviewStatusOptions());
        refdata.put("pageTitle", "Routing & Review || Enter Search Parameters");
        refdata.put("bodyTitle", "Routing & Review: Enter Search Parameters");
        refdata.put("instructions","Please enter the search parameters (Update this instruction).");
        return refdata;
    }
    
    
    
    public Map<Object,Object> initializeReviewStatusOptions(){
    	reviewStatusOptionsMap = new HashMap<Object,Object>();
    	reviewStatusOptionsMap.put("", "Please Select..");
    	reviewStatusOptionsMap.putAll(WebUtils.collectCustomOptions(REVIEW_STATUS, "name", "code", "displayName", ":  "));
    	reviewStatusOptionsMap.putAll(WebUtils.collectOptions(REVIEW_STATUS, "name", "displayName"));
    	return reviewStatusOptionsMap;
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
}
