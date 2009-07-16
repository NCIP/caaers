package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.dao.ResearchStaffDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.StudyParticipantAssignmentDao;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.domain.SiteResearchStaff;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.UserGroupType;
import gov.nih.nci.cabig.caaers.security.SecurityUtils;
import gov.nih.nci.cabig.caaers.service.EvaluationService;
import gov.nih.nci.cabig.caaers.tools.configuration.Configuration;
import gov.nih.nci.cabig.caaers.web.ControllerTools;
import gov.nih.nci.cabig.caaers.web.security.RoleCheck;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.acegisecurity.Authentication;
import org.acegisecurity.context.SecurityContextHolder;
import org.acegisecurity.userdetails.User;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.mvc.SimpleFormController;

/**
 * @author Rhett Sutphin
 * @author Biju Joseph
 */
public class ListAdverseEventsController extends SimpleFormController {
	
	private static final String SELECTED_STUDY_ID = "pre_selected_study_id";
	private static final String SELECTED_PARTICIPANT_ID = "pre_selected_participant_id";
	
	private static final String ACTION_PARAMETER = "action";
    
	private StudyParticipantAssignmentDao assignmentDao;

    private ParticipantDao participantDao;

    private StudyDao studyDao;

    protected EvaluationService evaluationService;
    
    
    private Configuration configuration;
    
    private ResearchStaffDao researchStaffDao;
    
    public ListAdverseEventsController() {
        setCommandClass(ListAdverseEventsCommand.class);
        setBindOnNewForm(true);
        setFormView("ae/selectAssignmentForList");
        setSuccessView("ae/list");
    }
    
   
    
    @Override
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
    	request.getSession().removeAttribute(ACTION_PARAMETER);
    	ListAdverseEventsCommand command = new ListAdverseEventsCommand(evaluationService);
    	command.setWorkflowEnabled(configuration.get(Configuration.ENABLE_WORKFLOW));
    	

		//restore the values from session if they are available. 
		HttpSession session = request.getSession();

		Integer studyId = (Integer) session.getAttribute(SELECTED_STUDY_ID);
		if(studyId != null){
			command.setStudy(studyDao.getById(studyId));
		}
		
		Integer subjectId = (Integer) session.getAttribute(SELECTED_PARTICIPANT_ID);
		if(subjectId != null){
			command.setParticipant(participantDao.getById(subjectId));
		}
		
    	
        return command;
    }

    @Override
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        ControllerTools.registerGridDomainObjectEditor(binder, "participant", participantDao);
        ControllerTools.registerGridDomainObjectEditor(binder, "study", studyDao);
        ControllerTools.registerGridDomainObjectEditor(binder, "assignment", assignmentDao);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected boolean isFormSubmission(HttpServletRequest request) {
        Set<String> paramNames = request.getParameterMap().keySet();
        boolean hasParticipant = paramNames.contains("participant") || paramNames.contains("mrn") || paramNames.contains("assignment");
        boolean hasStudy = paramNames.contains("study") || paramNames.contains("nciIdentifier") || paramNames.contains("assignment");
        boolean hasStudySubjectGridId = paramNames.contains("studySubjectGridId");
        return (hasParticipant && hasStudy) || hasStudySubjectGridId;
    }
    
    /**
     * One can enter this page in 3 ways
     *   1- by specifiying study - participant
     *   2 - by specifying assignment
     *   3 - by specifying gridId of assignment (eg: from LabViewer hotlink to caAERs).
     *   
     *  We will make sure, that we properly set all the relavent objects correctly. 
     *  
     */
    @Override
    protected void onBind(HttpServletRequest request, Object cmd, BindException errors) throws Exception {
        super.onBind(request, cmd, errors);
        
        ListAdverseEventsCommand command = (ListAdverseEventsCommand) cmd;
        Participant participant = command.getParticipant();
        Study study = command.getStudy();
        StudyParticipantAssignment assignment = command.getAssignment();
        String assignmentGridId = request.getParameter("studySubjectGridId");    // forwarded from external system (eg : Labviewer)
        
        if(study != null && participant != null){
        	assignment = assignmentDao.getAssignment(participant, study);
        }else if (assignmentGridId != null){
        	assignment = assignmentDao.getByGridId(assignmentGridId);
        	participant = assignment.getParticipant();
        	study = assignment.getStudySite().getStudy();
        }else if(assignment != null){
        	//the url has ?assignment=xxx
        	participant = assignment.getParticipant();
        	study = assignment.getStudySite().getStudy();
        }
        
        //reset everything in command.
        command.setAssignment(assignment);
        command.setParticipant(participant);
        command.setStudy(study);
    }

    @Override
    protected void onBindAndValidate(HttpServletRequest request, Object command, BindException errors) throws Exception {
        super.onBindAndValidate(request, command, errors);
        ListAdverseEventsCommand listAECmd = (ListAdverseEventsCommand) command;
        boolean noStudy = listAECmd.getStudy() == null;
        boolean noParticipant = listAECmd.getParticipant() == null;
        if (noStudy) errors.rejectValue("study", "SAE_001", "Missing study");
        if (noParticipant) errors.rejectValue("participant", "SAE_002", "Missing subject");
        if (!(noStudy || noParticipant) && listAECmd.getAssignment() == null) {
            errors.reject("SAE_006", "The subject is not assigned to the provided study");
        }
        
        if(!errors.hasErrors()){
        	//if there is no validation error, update the report submitability
        	listAECmd.updateSubmittability();
        	
        	//save the study/subject in session for future pre-selection
        	HttpSession session = request.getSession();
			session.setAttribute(SELECTED_STUDY_ID, listAECmd.getStudy().getId());
			session.setAttribute(SELECTED_PARTICIPANT_ID, listAECmd.getParticipant().getId());
			
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    protected Map referenceData(HttpServletRequest request, Object command, Errors errors) throws Exception {
    	ListAdverseEventsCommand listAECmd = (ListAdverseEventsCommand) command;
        Map<String, Object> refdata = new HashMap<String, Object>();
        refdata.put("pageTitle", "Manage Reports || Select Subject and Study");
        refdata.put("bodyTitle", "Manage Reports: Select Subject and Study");
        refdata.put("instructions","Select a subject and study to see all the AEs for that combination.");
    	return refdata;
    }
    
    @Override
    protected void doSubmitAction(Object command) throws Exception {
    	ListAdverseEventsCommand listAECmd = (ListAdverseEventsCommand) command;
    	String loginId = SecurityUtils.getUserLoginName();
    	
    	boolean isSuperUser = SecurityUtils.checkAuthorization(UserGroupType.caaers_super_user);
    	
    	boolean canRenderSubmitReportLink = isSuperUser;
    	
    	if(!isSuperUser){
    		
    		if(listAECmd.getWorkflowEnabled()){
    			
    			//only cenrtal office sae coordinator of the Coordinating center is allowed to submit.
    			boolean isSAECoordinator = SecurityUtils.checkAuthorization(UserGroupType.caaers_central_office_sae_cd); 

    			//now check if the sae coordinator is associated to the coordinaoting center
    			if(isSAECoordinator && listAECmd.getStudy() != null){
    				Organization ccOrg = listAECmd.getStudy().getStudyCoordinatingCenter().getOrganization();
    				ResearchStaff researchStaff = researchStaffDao.getByLoginId(loginId);
    				if(researchStaff != null && ccOrg != null){
    			    	for(SiteResearchStaff siteRs : researchStaff.getSiteResearchStaffsInternal()){
    			    		canRenderSubmitReportLink = siteRs.getOrganization().getId().equals(ccOrg.getId());
    			    	}
    				}
    			}
    			  
    			  
        	}else{
        		canRenderSubmitReportLink = SecurityUtils.checkAuthorization(UserGroupType.caaers_ae_cd,
        				UserGroupType.caaers_participant_cd,UserGroupType.caaers_central_office_sae_cd);
        	}
    	}
    	
    	listAECmd.setSubmitLinkRenderable(canRenderSubmitReportLink);
    	
    }

    // //// CONFIGURATION

    public void setAssignmentDao(StudyParticipantAssignmentDao assignmentDao) {
        this.assignmentDao = assignmentDao;
    }

    public void setParticipantDao(ParticipantDao participantDao) {
        this.participantDao = participantDao;
    }

    public void setStudyDao(StudyDao studyDao) {
        this.studyDao = studyDao;
    }

    public EvaluationService getEvaluationService() {
        return evaluationService;
    }

    public void setEvaluationService(EvaluationService evaluationService) {
        this.evaluationService = evaluationService;
    }

    public void setConfiguration(Configuration configuration){
    	this.configuration = configuration;
    }
    
    public Configuration getConfiguration(){
    	return configuration;
    }
    
    public ResearchStaffDao getResearchStaffDao() {
		return researchStaffDao;
	}
    public void setResearchStaffDao(ResearchStaffDao researchStaffDao) {
		this.researchStaffDao = researchStaffDao;
	}
}
