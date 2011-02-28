package gov.nih.nci.cabig.caaers.domain.repository;

import gov.nih.nci.cabig.caaers.dao.AdverseEventReportingPeriodDao;
import gov.nih.nci.cabig.caaers.dao.ExpeditedAdverseEventReportDao;
import gov.nih.nci.cabig.caaers.dao.query.AdverseEventReportingPeriodForReviewQuery;
import gov.nih.nci.cabig.caaers.dao.report.ReportDao;
import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.domain.dto.AdverseEventReportingPeriodDTO;
import gov.nih.nci.cabig.caaers.domain.dto.ExpeditedAdverseEventReportDTO;
import gov.nih.nci.cabig.caaers.domain.dto.TaskNotificationDTO;
import gov.nih.nci.cabig.caaers.domain.factory.AERoutingAndReviewDTOFactory;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.workflow.ReportReviewComment;
import gov.nih.nci.cabig.caaers.domain.workflow.ReportingPeriodReviewComment;
import gov.nih.nci.cabig.caaers.domain.workflow.ReviewComment;
import gov.nih.nci.cabig.caaers.domain.workflow.WorkflowAware;
import gov.nih.nci.cabig.caaers.domain.workflow.WorkflowConfig;
import gov.nih.nci.cabig.caaers.security.SecurityUtils;
import gov.nih.nci.cabig.caaers.service.ReportSubmittability;
import gov.nih.nci.cabig.caaers.service.workflow.WorkflowService;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.jbpm.context.exe.ContextInstance;
import org.jbpm.graph.exe.ProcessInstance;
import org.jbpm.taskmgmt.exe.TaskInstance;
import org.springframework.transaction.annotation.Transactional;
 

/**
 * The Class AdverseEventRoutingAndReviewRepositoryImpl.
 *
 * @author Biju Joseph
 */
@Transactional
public class AdverseEventRoutingAndReviewRepositoryImpl implements AdverseEventRoutingAndReviewRepository {
	
	/** The routing and review factory. */
	private AERoutingAndReviewDTOFactory routingAndReviewFactory;
	
	/** The adverse event reporting period dao. */
	private AdverseEventReportingPeriodDao adverseEventReportingPeriodDao;
	
	/** The expedited adverse event report dao. */
	private ExpeditedAdverseEventReportDao expeditedAdverseEventReportDao;
	
	/** The report validation service. */
	private ReportValidationService reportValidationService;
	
	/** The report dao. */
	private ReportDao reportDao;

	/** The workflow service. */
	private WorkflowService workflowService;
	
	/** The Constant SUBMIT_TO_CENTRAL_OFFICE_SAE_COORDINATOR. */
	private static final String SUBMIT_TO_CENTRAL_OFFICE_SAE_COORDINATOR = "Submit to Central Office Report Reviewer";
    
    /** The Constant SUBMIT_TO_PHYSICIAN. */
    private static final String SUBMIT_TO_PHYSICIAN = "Send to Physician for Review";
	
	
	/**
	 * Instantiates a new adverse event routing and review repository impl.
	 */
	public AdverseEventRoutingAndReviewRepositoryImpl() {
		routingAndReviewFactory = new AERoutingAndReviewDTOFactory();
		routingAndReviewFactory.setAdverseEventRoutingAndReviewRepository(this);
	}
	
	/*
	 * (non-Javadoc)
	 * @see gov.nih.nci.cabig.caaers.domain.repository.AdverseEventRoutingAndReviewRepository#enactReportingPeriodWorkflow(gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod)
	 * 
	 * Algorithm :-
	 * 1. Find the site from assignment
	 * 2. Find the workflow from site (configured)
	 * 3. Enact the workflow
	 */
	public Long enactReportingPeriodWorkflow(AdverseEventReportingPeriod reportingPeriod) {
		assert reportingPeriod.getId() != null;
		StudyParticipantAssignment assignment = reportingPeriod.getAssignment();
		StudySite studySite = assignment.getStudySite();
		WorkflowConfig wfConfig = studySite.getReportingPeriodWorkflowConfig();
		
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put(WorkflowService.VAR_STUDY_ID, studySite.getStudy().getId());
		variables.put(WorkflowService.VAR_WF_TYPE, AdverseEventReportingPeriod.class.getName());
		variables.put(WorkflowService.VAR_REPORTING_PERIOD_ID, reportingPeriod.getId());
		
		ProcessInstance pInstance = workflowService.createProcessInstance(wfConfig.getWorkflowDefinitionName(), variables);
		
		reportingPeriod.setWorkflowId((int) pInstance.getId());
		reportingPeriod.setReviewStatus(ReviewStatus.DRAFT_INCOMPLETE);
		
		adverseEventReportingPeriodDao.modifyOrSaveReviewStatusAndComments(reportingPeriod);
		
		return pInstance.getId();
	}
	
	/*
	 * (non-Javadoc)
	 * @see gov.nih.nci.cabig.caaers.domain.repository.AdverseEventRoutingAndReviewRepository#enactReportWorkflow(gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport)
	 * 
	 * Algorithm :-
	 *  1. Find the Site from assignment
	 *  2. Find the workflow from site (configured)
	 *  3. Enact the workflow, 
	 */
	public Long enactReportWorkflow(Report report) {
		
		assert report.getId() != null;
		
		StudyParticipantAssignment assignment = report.getAeReport().getAssignment();
		StudySite studySite = assignment.getStudySite();
		WorkflowConfig wfConfig = studySite.getReportWorkflowConfig();

		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put(WorkflowService.VAR_STUDY_ID, studySite.getStudy().getId());
		variables.put(WorkflowService.VAR_WF_TYPE, Report.class.getName());
		variables.put(WorkflowService.VAR_REPORT_ID, report.getId());
		variables.put(WorkflowService.VAR_EXPEDITED_REPORT_ID, report.getAeReport().getId());
		
		ProcessInstance pInstance = workflowService.createProcessInstance(wfConfig.getWorkflowDefinitionName(), variables);
		

		report.setWorkflowId((int)pInstance.getId());
		report.setReviewStatus(ReviewStatus.DRAFT_INCOMPLETE);
		
		reportDao.save(report);
		
		return pInstance.getId();
	}
	
	
	/* (non-Javadoc)
	 * @see gov.nih.nci.cabig.caaers.domain.repository.AdverseEventRoutingAndReviewRepository#fetchReviewCommentsForReport(java.lang.Integer)
	 */
	public List<? extends ReviewComment> fetchReviewCommentsForReport(Integer reportId) {
		return reportDao.getById(reportId).getReviewComments();
	}
	
	/* (non-Javadoc)
	 * @see gov.nih.nci.cabig.caaers.domain.repository.AdverseEventRoutingAndReviewRepository#fetchReviewCommentsForReportingPeriod(java.lang.Integer)
	 */
	public List<? extends ReviewComment> fetchReviewCommentsForReportingPeriod(Integer rpId) {
		return adverseEventReportingPeriodDao.getById(rpId).getReviewComments();
	}
	
	//TODO These methods needs to be uddated to add/edit comments to aeReport instead of a report.
	/* (non-Javadoc)
	 * @see gov.nih.nci.cabig.caaers.domain.repository.AdverseEventRoutingAndReviewRepository#addReportReviewComment(java.lang.Integer, java.lang.String, java.lang.String)
	 */
	public void addReportReviewComment(Integer reportId, String comment, String userId){
		Report report = reportDao.getById(reportId);
		this.addReportReviewComment(report, comment, userId);
	}

	
	/* (non-Javadoc)
	 * @see gov.nih.nci.cabig.caaers.domain.repository.AdverseEventRoutingAndReviewRepository#addReportReviewComment(gov.nih.nci.cabig.caaers.domain.report.Report, java.lang.String, java.lang.String)
	 */
	public void addReportReviewComment(Report report, String comment, String userId) {
		
		ReportReviewComment reviewComment = new ReportReviewComment();
		reviewComment.setUserComment(comment);
		reviewComment.setCreatedDate(new Date());
		reviewComment.setAutoGeneratedText("Added by " + userId);
		report.getReviewCommentsInternal().add(reviewComment);
		reviewComment.setEditable(true);
		reviewComment.setUserId(userId);
		
		reportDao.save(report);
	}
	
	/* (non-Javadoc)
	 * @see gov.nih.nci.cabig.caaers.domain.repository.AdverseEventRoutingAndReviewRepository#editReportReviewComment(java.lang.Integer, java.lang.String, java.lang.String, java.lang.Integer)
	 */
	public void editReportReviewComment(Integer reportId, String comment, String userId, Integer commentId){
		Report report = reportDao.getById(reportId);
		this.editReportReviewComment(report, comment, userId, commentId);
	}
	
	/* (non-Javadoc)
	 * @see gov.nih.nci.cabig.caaers.domain.repository.AdverseEventRoutingAndReviewRepository#editReportReviewComment(gov.nih.nci.cabig.caaers.domain.report.Report, java.lang.String, java.lang.String, java.lang.Integer)
	 */
	public void editReportReviewComment(Report report, String comment, String userId, Integer commentId){
		for(ReportReviewComment reviewComment: report.getReviewCommentsInternal()){
			if(reviewComment.getId().equals(commentId)){
				reviewComment.setUserComment(comment);
				reviewComment.setCreatedDate(new Date());
				break;
			}
		}
		
		reportDao.save(report);
	}
	
	/* (non-Javadoc)
	 * @see gov.nih.nci.cabig.caaers.domain.repository.AdverseEventRoutingAndReviewRepository#deleteReportReviewComment(java.lang.Integer, java.lang.Integer)
	 */
	public void deleteReportReviewComment(Integer reportId, Integer commentId){
		Report report = reportDao.getById(reportId);
		deleteReportReviewComment(report, commentId);
	}
	
	/* (non-Javadoc)
	 * @see gov.nih.nci.cabig.caaers.domain.repository.AdverseEventRoutingAndReviewRepository#deleteReportReviewComment(gov.nih.nci.cabig.caaers.domain.report.Report, java.lang.Integer)
	 */
	public void deleteReportReviewComment(Report report, Integer commentId){
		for(ReportReviewComment reviewComment: report.getReviewCommentsInternal()){
			if(reviewComment.getId().equals(commentId)){
				report.getReviewCommentsInternal().remove(reviewComment);
				break;
			}
		}
		reportDao.save(report);
	}
	
	/* (non-Javadoc)
	 * @see gov.nih.nci.cabig.caaers.domain.repository.AdverseEventRoutingAndReviewRepository#deleteReportingPeriodReviewComment(java.lang.Integer, java.lang.Integer)
	 */
	public void deleteReportingPeriodReviewComment(Integer reportingPeriodId, Integer commentId){
		AdverseEventReportingPeriod reportingPeriod = adverseEventReportingPeriodDao.getById(reportingPeriodId);
		deleteReportingPeriodReviewComment(reportingPeriod, commentId);
	}
	
	/* (non-Javadoc)
	 * @see gov.nih.nci.cabig.caaers.domain.repository.AdverseEventRoutingAndReviewRepository#deleteReportingPeriodReviewComment(gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod, java.lang.Integer)
	 */
	public void deleteReportingPeriodReviewComment(AdverseEventReportingPeriod reportingPeriod, Integer commentId){
		for(ReportingPeriodReviewComment reviewComment: reportingPeriod.getReviewCommentsInternal()){
			if(reviewComment.getId().equals(commentId)){
				reportingPeriod.getReviewCommentsInternal().remove(reviewComment);
				break;
			}
		}
		adverseEventReportingPeriodDao.modifyOrSaveReviewStatusAndComments(reportingPeriod);
	}
	
	/* (non-Javadoc)
	 * @see gov.nih.nci.cabig.caaers.domain.repository.AdverseEventRoutingAndReviewRepository#addReportingPeriodReviewComment(java.lang.Integer, java.lang.String, java.lang.String)
	 */
	public void addReportingPeriodReviewComment(Integer reportingPeriodId, String comment, String userId){
		AdverseEventReportingPeriod reportingPeriod = adverseEventReportingPeriodDao.getById(reportingPeriodId);
		this.addReportingPeriodReviewComment(reportingPeriod, comment, userId);
	}
	
	/* (non-Javadoc)
	 * @see gov.nih.nci.cabig.caaers.domain.repository.AdverseEventRoutingAndReviewRepository#addReportingPeriodReviewComment(gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod, java.lang.String, java.lang.String)
	 */
	public void addReportingPeriodReviewComment(AdverseEventReportingPeriod reportingPeriod, String comment, String userId){
		ReportingPeriodReviewComment reviewComment = new ReportingPeriodReviewComment();
		reviewComment.setUserComment(comment);
		reviewComment.setCreatedDate(new Date());
		reviewComment.setAutoGeneratedText("Added by " + userId);
		reviewComment.setEditable(true);
		reviewComment.setUserId(userId);
		
		reportingPeriod.getReviewCommentsInternal().add(reviewComment);
		adverseEventReportingPeriodDao.modifyOrSaveReviewStatusAndComments(reportingPeriod);
	}
	
	/* (non-Javadoc)
	 * @see gov.nih.nci.cabig.caaers.domain.repository.AdverseEventRoutingAndReviewRepository#editReportingPeriodReviewComment(java.lang.Integer, java.lang.String, java.lang.String, java.lang.Integer)
	 */
	public void editReportingPeriodReviewComment(Integer reportingPeriodId, String comment, String userId, Integer commentId){
		AdverseEventReportingPeriod reportingPeriod = adverseEventReportingPeriodDao.getById(reportingPeriodId);
		this.editReportingPeriodReviewComment(reportingPeriod, comment, userId, commentId);
	}
	
	/* (non-Javadoc)
	 * @see gov.nih.nci.cabig.caaers.domain.repository.AdverseEventRoutingAndReviewRepository#editReportingPeriodReviewComment(gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod, java.lang.String, java.lang.String, java.lang.Integer)
	 */
	public void editReportingPeriodReviewComment(AdverseEventReportingPeriod reportingPeriod, String comment, String userId, Integer commentId){
		for(ReportingPeriodReviewComment reviewComment: reportingPeriod.getReviewCommentsInternal()){
			if(reviewComment.getId().equals(commentId)){
				reviewComment.setUserComment(comment);
				reviewComment.setCreatedDate(new Date());
			}
		}
		
		adverseEventReportingPeriodDao.modifyOrSaveReviewStatusAndComments(reportingPeriod);
	}
	
	/* (non-Javadoc)
	 * @see gov.nih.nci.cabig.caaers.domain.repository.AdverseEventRoutingAndReviewRepository#advanceReportingPeriodWorkflow(java.lang.Integer, java.lang.String, java.lang.Integer, java.lang.String)
	 */
	public List<String> advanceReportingPeriodWorkflow(Integer workflowId, String toTransition, Integer id, String userId) {
		AdverseEventReportingPeriod reportingPeriod = adverseEventReportingPeriodDao.getById(id);
		return this.advanceReportingPeriodWorkflow(workflowId, toTransition, reportingPeriod, userId);
	}
	
	/* (non-Javadoc)
	 * @see gov.nih.nci.cabig.caaers.domain.repository.AdverseEventRoutingAndReviewRepository#advanceReportingPeriodWorkflow(java.lang.Integer, java.lang.String, gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod, java.lang.String)
	 */
	public List<String> advanceReportingPeriodWorkflow(Integer workflowId, String toTransition, AdverseEventReportingPeriod reportingPeriod, String userId){
		ReviewStatus nextStatus = workflowService.advanceWorkflow(workflowId, toTransition);
		reportingPeriod.setReviewStatus(nextStatus);
		
		// Make all the existing reviewComments as uneditable
		for(ReviewComment reviewComment: reportingPeriod.getReviewCommentsInternal()){
			reviewComment.setEditable(false);
		}
		
		// Add a comment describing the action taken by the user-
		ReviewComment reviewComment = createAdvanceWorkflowComment(toTransition, userId, "reportingPeriod");
		reportingPeriod.getReviewCommentsInternal().add((ReportingPeriodReviewComment)reviewComment);
		adverseEventReportingPeriodDao.modifyOrSaveReviewStatusAndComments(reportingPeriod);
		return nextTransitionNames(workflowId, userId);
	}
	
	/**
	 * This method creates a comment to show the transition history.
	 *
	 * @param toTransition the to transition
	 * @param userId the user id
	 * @param domainObjectType the domain object type
	 * @return the review comment
	 */
	public ReviewComment createAdvanceWorkflowComment(String toTransition, String userId, String domainObjectType){
		ReviewComment reviewComment;
		if(domainObjectType.equals("reportingPeriod"))
			reviewComment = new ReportingPeriodReviewComment();
		else
			reviewComment = new ReportReviewComment();
		reviewComment.setUserId(userId);
		reviewComment.setEditable(false);
		reviewComment.setCreatedDate(new Date());
		reviewComment.setAutoGeneratedText(userId + " took an action of \"" + toTransition + "\"");
		return reviewComment;
	}
	
	/* (non-Javadoc)
	 * @see gov.nih.nci.cabig.caaers.domain.repository.AdverseEventRoutingAndReviewRepository#advanceReportWorkflow(java.lang.Integer, java.lang.String, java.lang.Integer, java.lang.String)
	 */
	public List<String> advanceReportWorkflow(Integer workflowId,String toTransition, Integer id, String userId) {
		Report report = reportDao.getById(id);
		return this.advanceReportWorkflow(workflowId, toTransition, report, userId);
	}
	
	/* (non-Javadoc)
	 * @see gov.nih.nci.cabig.caaers.domain.repository.AdverseEventRoutingAndReviewRepository#advanceReportWorkflow(java.lang.Integer, java.lang.String, gov.nih.nci.cabig.caaers.domain.report.Report, java.lang.String)
	 */
	public List<String> advanceReportWorkflow(Integer workflowId,String toTransition, Report report, String userId) {

		ReviewStatus nextStatus = workflowService.advanceWorkflow(workflowId, toTransition);
		report.setReviewStatus(nextStatus);
		for(ReviewComment reviewComment: report.getReviewCommentsInternal()){
			reviewComment.setEditable(false);
		}
		// Add a comment describing action taken by the user
		ReviewComment reviewComment = createAdvanceWorkflowComment(toTransition, userId, "report");
		report.getReviewCommentsInternal().add((ReportReviewComment)reviewComment);
		reportDao.save(report);
		return nextTransitionNamesForReportWorkflow(report, userId);
	}
	
	/* (non-Javadoc)
	 * @see gov.nih.nci.cabig.caaers.domain.repository.AdverseEventRoutingAndReviewRepository#nextTransitionNames(java.lang.Integer, java.lang.String)
	 */
	public List<String> nextTransitionNames(Integer workflowId, String loginId) {
		return workflowService.nextTransitionNames(workflowId, loginId);
	}
	
	/**
	 * This method is specifically called for Report workflow.
	 * Only submittable reports should be allowed to send to Central Office SAE Coordinator review.
	 *
	 * @param report - An active @{link Report}
	 * @param loginId - The login name of the user
	 * @return the list
	 */
	public List<String> nextTransitionNamesForReportWorkflow(Report report, String loginId){
		List<String> nextTransitionNames = workflowService.nextTransitionNames(report.getWorkflowId(), loginId);

        boolean physicianHasLogin = false;

        if(report.isActive()){
           ReportSubmittability submittability = reportValidationService.isSubmittable(report);
           if(!submittability.isSubmittable()){
              nextTransitionNames.remove(SUBMIT_TO_CENTRAL_OFFICE_SAE_COORDINATOR);
           }

           Physician physician = report.getPhysician();
           if(physician != null){
              Person investigator = physician.getPerson();
              physicianHasLogin = ( (investigator != null) && StringUtils.isNotEmpty(investigator.getLoginId()));
           }
        }

        if(!physicianHasLogin){
            nextTransitionNames.remove(SUBMIT_TO_PHYSICIAN);
        }

	    return nextTransitionNames;
	}

	
	
	/* (non-Javadoc)
	 * @see gov.nih.nci.cabig.caaers.domain.repository.AdverseEventRoutingAndReviewRepository#findAdverseEventReportingPeriods(gov.nih.nci.cabig.caaers.domain.Participant, gov.nih.nci.cabig.caaers.domain.Study, gov.nih.nci.cabig.caaers.domain.Organization, gov.nih.nci.cabig.caaers.domain.ReviewStatus, gov.nih.nci.cabig.caaers.domain.ReportStatus, java.lang.String, java.lang.Boolean)
	 */
	public List<AdverseEventReportingPeriodDTO> findAdverseEventReportingPeriods(Participant participant, Study study, 
			Organization organization, ReviewStatus reviewStatus, ReportStatus reportStatus, String userId, Boolean courseWorkflowEnabled){
		AdverseEventReportingPeriodForReviewQuery query = new AdverseEventReportingPeriodForReviewQuery();
		boolean isReportReviewer = SecurityUtils.checkAuthorization(UserGroupType.ae_expedited_report_reviewer); 
		
		if(participant != null){
			query.filterByParticipant(participant.getId());
		}
		
		if(study != null){
			query.filterByStudy(study.getId());
		}
		
		if(organization != null){
			query.filterByOrganization(organization.getId());
		}
		
		List<AdverseEventReportingPeriod> reportingPeriods = adverseEventReportingPeriodDao.findAdverseEventReportingPeriods(query);
		

		List<AdverseEventReportingPeriodDTO> reportingPeriodDTOs = new ArrayList<AdverseEventReportingPeriodDTO>();
		for(AdverseEventReportingPeriod reportingPeriod : reportingPeriods){
			if(isReportingPeriodHavingWorkflowOrReportsWithWorkflow(reportingPeriod, courseWorkflowEnabled) && 
					isReportingPeriodHavingSpecifiedReviewStatus(reportingPeriod, reviewStatus) && isReportingPeriodHavingReportsWithSpecifiedStatus(reportingPeriod, reportStatus)){
			//if(reportingPeriod.getWorkflowId() != null &&
			//		isReportingPeriodHavingSpecifiedReviewStatus(reportingPeriod, reviewStatus) && isReportingPeriodHavingReportsWithSpecifiedStatus(reportingPeriod, reportStatus)){
				AdverseEventReportingPeriodDTO reportingPeriodDTO = routingAndReviewFactory.createAdverseEventEvalutionPeriodDTO(reportingPeriod, userId, courseWorkflowEnabled);
				
				//check the Reports
				if(reportingPeriod.getAeReports() != null){
					for(ExpeditedAdverseEventReport aeReport : reportingPeriod.getAeReports()){
						if(!aeReportHasWorkflowOnActiveReports(aeReport)) continue; //this report is prior to workflow integration
						ExpeditedAdverseEventReportDTO reportDTO = routingAndReviewFactory.createAdverseEventReportDTO(aeReport, userId);
						if(reportDTO.hasActionsToDo()) reportingPeriodDTO.addAdverseEventAeReportDTO(reportDTO);
						if(isReportReviewer && reportDTO.hasWorkflowEnded()) reportingPeriodDTO.addAdverseEventAeReportDTO(reportDTO);
					}//aereport
				}
				
				if(reportStatus != null){
					// The search criteria involves reportStatus. So a check needs to be made incase if there are any reports
					// in the reportingPeriodDTO. Otherwise the reportingPeriod shouldnt be displayed.
					// Incase we dont do so and the person logged in has some routing actions to take for the course workflow,
					// the reportingPeriod will be displayed in the results.
					if(reportingPeriodDTO.getActiveAeReports() == null || reportingPeriodDTO.getActiveAeReports().isEmpty())
						continue;
				}
				//only add the dto, if there is action to do.
				if(reportingPeriodDTO.hasActionsToDo()) reportingPeriodDTOs.add(reportingPeriodDTO);
				if(isReportReviewer && reportingPeriodDTO.hasReportWorkflowEnded()) reportingPeriodDTOs.add(reportingPeriodDTO);
			}
			
		}
		
		return reportingPeriodDTOs;
	}
	
	/**
	 * This method will return true if the dataCollection (aeReport) has workflow enabled on any active reports. Reports with status
	 * other than (Withdrawn, Replaced or Amended) are considered to be active reports. In caAERS context even reports with status
	 * 'Completed' is considered to be inactive reports.
	 *
	 * @param aeReport the ae report
	 * @return true, if successful
	 */
	public boolean aeReportHasWorkflowOnActiveReports(ExpeditedAdverseEventReport aeReport){
		boolean hasWorkflowOnActiveReports = false;
    	for(Report r: getActiveReportsForAeReport(aeReport))
    		if(r.getWorkflowId() != null)
    			hasWorkflowOnActiveReports = true;
    	return hasWorkflowOnActiveReports;
	}
	
	/**
	 * This method returns the list of active reports. Reports with status
	 * other than (Withdrawn, Replaced or Amended) are considered to be active reports. In caAERS context even reports with status
	 * 'Completed' is considered to be inactive reports.
	 *
	 * @param aeReport the ae report
	 * @return the active reports for ae report
	 */
	private List<Report> getActiveReportsForAeReport(ExpeditedAdverseEventReport aeReport){
		List<Report> reports = aeReport.getReports();
        if (reports.isEmpty()) return reports;
        List<Report> activeReports = new ArrayList<Report>();
        for (Report report : reports) {
            if (!report.isHavingStatus(ReportStatus.AMENDED, ReportStatus.WITHDRAWN, ReportStatus.REPLACED)) activeReports.add(report);
        }
        return activeReports;
	}
	
	/**
	 * Will return true, if the entity has the specified review status. 
	 * @param rp - An {@link AdverseEventReportingPeriod}
	 * @param rs - A {@link ReviewStatus}
	 * @return - true if the reporting period or any of the {@link ExpeditedAdverseEventReport} is of the specified review status
	 */
	protected boolean isReportingPeriodHavingSpecifiedReviewStatus(AdverseEventReportingPeriod rp, ReviewStatus rs){
		if(rs == null) return true; //if reviewstatus is null, then return true
		if(rp.getReviewStatus() == null) return false; //not enabled workflow for this reporting period
		if(rp.getReviewStatus().equals(rs)) return true;
		if(rp.getAeReports() != null){
			for(ExpeditedAdverseEventReport aeReport : rp.getAeReports()){
				for(Report report: aeReport.getReports()){
					if(report.getReviewStatus() != null && report.getReviewStatus().equals(rs))
						return true;
				}
			}
		}
		return false; //nor reporting period or report(s) has the status mentioned
	}
	
	/**
	 * Checks if is reporting period having workflow or reports with workflow.
	 *
	 * @param rp the rp
	 * @param courseWorkflowEnabled the course workflow enabled
	 * @return true, if is reporting period having workflow or reports with workflow
	 */
	protected boolean isReportingPeriodHavingWorkflowOrReportsWithWorkflow(AdverseEventReportingPeriod rp, Boolean courseWorkflowEnabled){
		if(courseWorkflowEnabled && rp.getWorkflowId() != null)
			return true;
		for(ExpeditedAdverseEventReport aeReport: rp.getActiveAeReports()){
			for(Report r: aeReport.getReports())
				if(r.getReportDefinition().getWorkflowEnabled() && r.getWorkflowId() != null)
					return true;
		}
		return false;
	}
	
	/**
	 * Will return true, if the reportingPeriod has any report with the specified reportStatus.
	 *
	 * @param rp - An {@link AdverseEventReportingPeriod}
	 * @param rs - A {@link ReportStatus}
	 * @return - true if the reporting period has any report with the specified reportStatus
	 */
	protected boolean isReportingPeriodHavingReportsWithSpecifiedStatus(AdverseEventReportingPeriod rp, ReportStatus rs){
		if(rs == null) return true;
		if(rp.getActiveAeReports() != null){
			for(ExpeditedAdverseEventReport aeReport : rp.getActiveAeReports()){
				for(Report report: aeReport.getReports()){
					if(report.getStatus() != null && report.getStatus().equals(rs))
						return true; // there exists a report with the specified report status.
				}
			}
		}
		return false; // there are no reports with the specified report status
	}
	
	/**
	 * Will return true, if the entity has the given workflow status.
	 *
	 * @param rs the rs
	 * @param wfAwareEntity the wf aware entity
	 * @return true, if is entity having specified review status
	 */
	protected boolean isEntityHavingSpecifiedReviewStatus( ReviewStatus rs,WorkflowAware wfAwareEntity){
		if(rs == null) return true; //return true if review status is null
		ReviewStatus entityRS = wfAwareEntity.getReviewStatus();
		if(entityRS == null) return false; //not participating in workflow
		return entityRS.equals(rs);
	}

    /**
     * Gets the task notification by user login.
     *
     * @param userLogin the user login
     * @return the task notification by user login
     */
    public List<TaskNotificationDTO> getTaskNotificationByUserLogin(String userLogin) {
        List<TaskInstance> l = workflowService.fetchTaskInstances(userLogin);
        List<TaskNotificationDTO> dtos = new ArrayList<TaskNotificationDTO>();

        for (TaskInstance task : l) {
            TaskNotificationDTO dto = new TaskNotificationDTO();

            ContextInstance ci = task.getProcessInstance().getContextInstance();
            String studyID = ci.getVariable(WorkflowService.VAR_STUDY_ID) != null ? ci.getVariable(WorkflowService.VAR_STUDY_ID).toString() : null;
            String reportingPeriodID  = ci.getVariable(WorkflowService.VAR_REPORTING_PERIOD_ID) != null ? ci.getVariable(WorkflowService.VAR_REPORTING_PERIOD_ID).toString() : null;
            String aeReportID = ci.getVariable(WorkflowService.VAR_EXPEDITED_REPORT_ID) != null ? ci.getVariable(WorkflowService.VAR_EXPEDITED_REPORT_ID).toString() : null;

            AdverseEventReportingPeriod reportingPeriod = null;
            ExpeditedAdverseEventReport aeReport;
            Study s;
            Participant p;
            
            if (reportingPeriodID != null) {
                reportingPeriod = adverseEventReportingPeriodDao.getById(Integer.parseInt(reportingPeriodID));
            } else {
                if (aeReportID != null) {
                    aeReport = expeditedAdverseEventReportDao.getById(Integer.parseInt(aeReportID));
                    reportingPeriod = aeReport.getReportingPeriod();
                }
            }

            if (reportingPeriod != null) {
                s = reportingPeriod.getStudy();
                p = reportingPeriod.getParticipant();

                dto.setStudyShortTitle(s.getShortTitle());
                dto.setSubjectFullName(p.getFullName());
                dto.setTask(task.getName());
                dto.setDescription(task.getDescription());
                dto.setDate(task.getCreate());
                if (reportingPeriod.getReviewStatus() != null) dto.setStatus(reportingPeriod.getReviewStatus().getDisplayName());
                dto.setReportingPeriodId(reportingPeriod.getId());
                // dto.setPossibleActions(this.nextTransitionNames(reportingPeriod.getWorkflowId(), userLogin));
                dtos.add(dto);
            }

        }
        return dtos;
    }

    // ToDo: get task, get process, get variables, get StudyID, SubjectID, hibernate get objects send to dashboard controller
    // 

	/**
     * Sets the routing and review factory.
     *
     * @param routingAndReviewFactory the new routing and review factory
     */
    public void setRoutingAndReviewFactory(
			AERoutingAndReviewDTOFactory routingAndReviewFactory) {
		this.routingAndReviewFactory = routingAndReviewFactory;
	}
	
	/**
	 * Gets the routing and review factory.
	 *
	 * @return the routing and review factory
	 */
	public AERoutingAndReviewDTOFactory getRoutingAndReviewFactory() {
		return routingAndReviewFactory;
	}
	
	/**
	 * Sets the adverse event reporting period dao.
	 *
	 * @param adverseEventReportingPeriodDao the new adverse event reporting period dao
	 */
	public void setAdverseEventReportingPeriodDao(
			AdverseEventReportingPeriodDao adverseEventReportingPeriodDao) {
		this.adverseEventReportingPeriodDao = adverseEventReportingPeriodDao;
	}
	
	/**
	 * Gets the adverse event reporting period dao.
	 *
	 * @return the adverse event reporting period dao
	 */
	public AdverseEventReportingPeriodDao getAdverseEventReportingPeriodDao() {
		return adverseEventReportingPeriodDao;
	}
	
	/**
	 * Gets the expedited adverse event report dao.
	 *
	 * @return the expedited adverse event report dao
	 */
	public ExpeditedAdverseEventReportDao getExpeditedAdverseEventReportDao() {
		return expeditedAdverseEventReportDao;
	}
	
	/**
	 * Sets the expedited adverse event report dao.
	 *
	 * @param expeditedAdverseEventReportDao the new expedited adverse event report dao
	 */
	public void setExpeditedAdverseEventReportDao(
			ExpeditedAdverseEventReportDao expeditedAdverseEventReportDao) {
		this.expeditedAdverseEventReportDao = expeditedAdverseEventReportDao;
	}
	
	/**
	 * Gets the workflow service.
	 *
	 * @return the workflow service
	 */
	public WorkflowService getWorkflowService() {
		return workflowService;
	}
	
	/**
	 * Sets the workflow service.
	 *
	 * @param workflowService the new workflow service
	 */
	public void setWorkflowService(WorkflowService workflowService) {
		this.workflowService = workflowService;
	}

	/**
	 * Sets the report validation service.
	 *
	 * @param reportValidationService the new report validation service
	 */
	public void setReportValidationService(ReportValidationService reportValidationService){
		this.reportValidationService = reportValidationService;
	}
	
	/**
	 * Sets the report dao.
	 *
	 * @param reportDao the new report dao
	 */
	public void setReportDao(ReportDao reportDao){
		this.reportDao = reportDao;
	}
	
	/**
	 * Gets the report dao.
	 *
	 * @return the report dao
	 */
	public ReportDao getReportDao(){
		return reportDao;
	}
	
}
