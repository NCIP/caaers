/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.dao.AdverseEventReportingPeriodDao;
import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.dao.PersonDao;
import gov.nih.nci.cabig.caaers.dao.ResearchStaffDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.StudyParticipantAssignmentDao;
import gov.nih.nci.cabig.caaers.dao.report.ReportDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.Investigator;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Person;
import gov.nih.nci.cabig.caaers.domain.ReportStatus;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.repository.AdverseEventRoutingAndReviewRepository;
import gov.nih.nci.cabig.caaers.domain.repository.ReportValidationService;
import gov.nih.nci.cabig.caaers.security.SecurityUtils;
import gov.nih.nci.cabig.caaers.tools.configuration.Configuration;
import gov.nih.nci.cabig.caaers.tools.editors.EnumByNameEditor;
import gov.nih.nci.cabig.caaers.web.ControllerTools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
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

    private ReportValidationService reportValidationService;
    
    private Configuration configuration;
    
    private ResearchStaffDao researchStaffDao;
    
    private PersonDao personDao;
    
    private AdverseEventReportingPeriodDao adverseEventReportingPeriodDao;
    
    public void setAdverseEventReportingPeriodDao(
			AdverseEventReportingPeriodDao adverseEventReportingPeriodDao) {
		this.adverseEventReportingPeriodDao = adverseEventReportingPeriodDao;
	}

	public PersonDao getPersonDao() {
		return personDao;
	}

	public void setPersonDao(PersonDao personDao) {
		this.personDao = personDao;
	}
	private AdverseEventRoutingAndReviewRepository adverseEventRoutingAndReviewRepository;
    
    public AdverseEventRoutingAndReviewRepository getAdverseEventRoutingAndReviewRepository() {
		return adverseEventRoutingAndReviewRepository;
	}

	public void setAdverseEventRoutingAndReviewRepository(
			AdverseEventRoutingAndReviewRepository adverseEventRoutingAndReviewRepository) {
		this.adverseEventRoutingAndReviewRepository = adverseEventRoutingAndReviewRepository;
	}
	private ReportDao reportDao;
    
    public ReportDao getReportDao() {
		return reportDao;
	}

	public void setReportDao(ReportDao reportDao) {
		this.reportDao = reportDao;
	}

	public ListAdverseEventsController() {
        setCommandClass(ListAdverseEventsCommand.class);
        setBindOnNewForm(true);
        setFormView("ae/selectAssignmentAndList");
        setSuccessView("ae/selectAssignmentAndList");
        
    }
    
    @Override
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
    	request.getSession().removeAttribute(ACTION_PARAMETER);
    	ListAdverseEventsCommand command = new ListAdverseEventsCommand(reportValidationService, researchStaffDao);
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
        binder.registerCustomEditor(ReportStatus.class, new EnumByNameEditor(ReportStatus.class));
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
        String searchIdentifier = command.getSearchIdentifier();
        if(StringUtils.isBlank(searchIdentifier)){
        	command.setSearchIdentifier(null);
        }
        command.setMaxResults(15);
        ReportStatus reportStatus = command.getReportStatus();
        List<Report> reports = reportDao.search(study, participant, reportStatus, command.getSearchIdentifier(), command.getMaxResults());
        command.setReports(reports);
    }

    @Override
    protected void onBindAndValidate(HttpServletRequest request, Object command, BindException errors) throws Exception {
        super.onBindAndValidate(request, command, errors);
        ListAdverseEventsCommand listAECmd = (ListAdverseEventsCommand) command;
        boolean noStudy = listAECmd.getStudy() == null;
        boolean noParticipant = listAECmd.getParticipant() == null;
        if (noStudy) errors.rejectValue("study", "SAE_001", "Missing study");
        if (noParticipant) errors.rejectValue("participant", "SAE_002", "Missing subject");
      /*  if (!(noStudy || noParticipant) && listAECmd.getAssignment() == null) {
            errors.reject("SAE_006", "The subject is not assigned to the provided study");
        }*/
        
        if(!errors.hasErrors()){
        	//if there is no validation error, update the report submitability
        	listAECmd.updateSubmittability();
        	listAECmd.updateSubmittabilityBasedOnReportStatus();
        	listAECmd.updateOptions();
        	
        	//save the study/subject in session for future pre-selection
        	HttpSession session = request.getSession();
			session.setAttribute(SELECTED_STUDY_ID, listAECmd.getStudy().getId());
			session.setAttribute(SELECTED_PARTICIPANT_ID, listAECmd.getParticipant().getId());
			
        }
        
    	String userId = SecurityUtils.getUserLoginName();
   	 	Boolean isStaff = true;
        Person loggedInPerson = personDao.getByLoginId(userId);
        if(loggedInPerson instanceof Investigator){
        	isStaff = false;
        }
        request.setAttribute("isStaff", isStaff);
        listAECmd.setUserId(userId);
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
    	listAECmd.updateSubmittabilityBasedOnWorkflow();
    	Set<AdverseEventReportingPeriod> reportingPeriods = new HashSet<AdverseEventReportingPeriod>();
    	for(Report report : listAECmd.getReports()){
    		reportingPeriods.add(report.getAeReport().getReportingPeriod());
    	}
		List<AdverseEventReportingPeriod> reportingPeriodsList = new ArrayList<AdverseEventReportingPeriod>();
		reportingPeriodsList.addAll(reportingPeriods);
		listAECmd.populateResults(reportingPeriodsList);
		
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

    public void setReportValidationService(ReportValidationService reportValidationService){
    	this.reportValidationService = reportValidationService;
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
