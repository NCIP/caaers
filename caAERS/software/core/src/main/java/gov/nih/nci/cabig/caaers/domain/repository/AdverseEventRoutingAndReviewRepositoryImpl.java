package gov.nih.nci.cabig.caaers.domain.repository;

import gov.nih.nci.cabig.caaers.dao.AdverseEventReportingPeriodDao;
import gov.nih.nci.cabig.caaers.dao.ExpeditedAdverseEventReportDao;
import gov.nih.nci.cabig.caaers.dao.query.AdverseEventReportingPeriodForReviewQuery;
import gov.nih.nci.cabig.caaers.dao.report.ReportDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.ReportStatus;
import gov.nih.nci.cabig.caaers.domain.ReviewStatus;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.domain.dto.AdverseEventReportingPeriodDTO;
import gov.nih.nci.cabig.caaers.domain.dto.ExpeditedAdverseEventReportDTO;
import gov.nih.nci.cabig.caaers.domain.factory.AERoutingAndReviewDTOFactory;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.workflow.ReportReviewComment;
import gov.nih.nci.cabig.caaers.domain.workflow.ReportingPeriodReviewComment;
import gov.nih.nci.cabig.caaers.domain.workflow.ReviewComment;
import gov.nih.nci.cabig.caaers.domain.workflow.WorkflowAware;
import gov.nih.nci.cabig.caaers.domain.workflow.WorkflowConfig;
import gov.nih.nci.cabig.caaers.service.EvaluationService;
import gov.nih.nci.cabig.caaers.service.ReportSubmittability;
import gov.nih.nci.cabig.caaers.service.workflow.WorkflowService;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jbpm.graph.exe.ProcessInstance;
import org.springframework.transaction.annotation.Transactional;
/**
 * 
 * @author Biju Joseph
 *
 */
@Transactional
public class AdverseEventRoutingAndReviewRepositoryImpl implements AdverseEventRoutingAndReviewRepository {
	
	private AERoutingAndReviewDTOFactory routingAndReviewFactory;
	private AdverseEventReportingPeriodDao adverseEventReportingPeriodDao;
	private ExpeditedAdverseEventReportDao expeditedAdverseEventReportDao;
	private ReportValidationService reportValidationService;
	private ReportDao reportDao;
	
	private WorkflowService workflowService;
	
	private static final String SUBMIT_TO_CENTRAL_OFFICE_SAE_COORDINATOR = "Submit to Central Office Report Reviewer";
	
	
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
	
	
	public List<? extends ReviewComment> fetchReviewCommentsForReport(Integer reportId) {
		return reportDao.getById(reportId).getReviewComments();
	}
	
	public List<? extends ReviewComment> fetchReviewCommentsForReportingPeriod(Integer rpId) {
		return adverseEventReportingPeriodDao.getById(rpId).getReviewComments();
	}
	
	//TODO These methods needs to be uddated to add/edit comments to aeReport instead of a report.
	public void addReportReviewComment(Integer reportId, String comment, String userId){
		Report report = reportDao.getById(reportId);
		this.addReportReviewComment(report, comment, userId);
	}

	
	public void addReportReviewComment(Report report, String comment, String userId) {
		
		ReportReviewComment reviewComment = new ReportReviewComment();
		reviewComment.setUserComment(comment);
		reviewComment.setCreatedDate(new Date());
		reviewComment.setAutoGeneratedText("Added by " + userId);
		report.getReviewComments().add(reviewComment);
		reviewComment.setEditable(true);
		reviewComment.setUserId(userId);
		
		reportDao.save(report);
	}
	
	public void editReportReviewComment(Integer reportId, String comment, String userId, Integer commentId){
		Report report = reportDao.getById(reportId);
		this.editReportReviewComment(report, comment, userId, commentId);
	}
	
	public void editReportReviewComment(Report report, String comment, String userId, Integer commentId){
		for(ReportReviewComment reviewComment: report.getReviewComments()){
			if(reviewComment.getId().equals(commentId)){
				reviewComment.setUserComment(comment);
				reviewComment.setCreatedDate(new Date());
				break;
			}
		}
		
		reportDao.save(report);
	}
	
	public void deleteReportReviewComment(Integer reportId, Integer commentId){
		Report report = reportDao.getById(reportId);
		deleteReportReviewComment(report, commentId);
	}
	
	public void deleteReportReviewComment(Report report, Integer commentId){
		for(ReportReviewComment reviewComment: report.getReviewComments()){
			if(reviewComment.getId().equals(commentId)){
				report.getReviewComments().remove(reviewComment);
				break;
			}
		}
		reportDao.save(report);
	}
	
	public void deleteReportingPeriodReviewComment(Integer reportingPeriodId, Integer commentId){
		AdverseEventReportingPeriod reportingPeriod = adverseEventReportingPeriodDao.getById(reportingPeriodId);
		deleteReportingPeriodReviewComment(reportingPeriod, commentId);
	}
	
	public void deleteReportingPeriodReviewComment(AdverseEventReportingPeriod reportingPeriod, Integer commentId){
		for(ReportingPeriodReviewComment reviewComment: reportingPeriod.getReviewComments()){
			if(reviewComment.getId().equals(commentId)){
				reportingPeriod.getReviewComments().remove(reviewComment);
				break;
			}
		}
		adverseEventReportingPeriodDao.modifyOrSaveReviewStatusAndComments(reportingPeriod);
	}
	
	public void addReportingPeriodReviewComment(Integer reportingPeriodId, String comment, String userId){
		AdverseEventReportingPeriod reportingPeriod = adverseEventReportingPeriodDao.getById(reportingPeriodId);
		this.addReportingPeriodReviewComment(reportingPeriod, comment, userId);
	}
	
	public void addReportingPeriodReviewComment(AdverseEventReportingPeriod reportingPeriod, String comment, String userId){
		ReportingPeriodReviewComment reviewComment = new ReportingPeriodReviewComment();
		reviewComment.setUserComment(comment);
		reviewComment.setCreatedDate(new Date());
		reviewComment.setAutoGeneratedText("Added by " + userId);
		reviewComment.setEditable(true);
		reviewComment.setUserId(userId);
		
		reportingPeriod.getReviewComments().add(reviewComment);
		adverseEventReportingPeriodDao.modifyOrSaveReviewStatusAndComments(reportingPeriod);
	}
	
	public void editReportingPeriodReviewComment(Integer reportingPeriodId, String comment, String userId, Integer commentId){
		AdverseEventReportingPeriod reportingPeriod = adverseEventReportingPeriodDao.getById(reportingPeriodId);
		this.editReportingPeriodReviewComment(reportingPeriod, comment, userId, commentId);
	}
	
	public void editReportingPeriodReviewComment(AdverseEventReportingPeriod reportingPeriod, String comment, String userId, Integer commentId){
		for(ReportingPeriodReviewComment reviewComment: reportingPeriod.getReviewComments()){
			if(reviewComment.getId().equals(commentId)){
				reviewComment.setUserComment(comment);
				reviewComment.setCreatedDate(new Date());
			}
		}
		
		adverseEventReportingPeriodDao.modifyOrSaveReviewStatusAndComments(reportingPeriod);
	}
	
	public List<String> advanceReportingPeriodWorkflow(Integer workflowId, String toTransition, Integer id, String userId) {
		AdverseEventReportingPeriod reportingPeriod = adverseEventReportingPeriodDao.getById(id);
		return this.advanceReportingPeriodWorkflow(workflowId, toTransition, reportingPeriod, userId);
	}
	
	public List<String> advanceReportingPeriodWorkflow(Integer workflowId, String toTransition, AdverseEventReportingPeriod reportingPeriod, String userId){
		ReviewStatus nextStatus = workflowService.advanceWorkflow(workflowId, toTransition);
		reportingPeriod.setReviewStatus(nextStatus);
		
		// Make all the existing reviewComments as uneditable
		for(ReviewComment reviewComment: reportingPeriod.getReviewComments()){
			reviewComment.setEditable(false);
		}
		
		// Add a comment describing the action taken by the user-
		ReviewComment reviewComment = createAdvanceWorkflowComment(toTransition, userId, "reportingPeriod");
		reportingPeriod.getReviewComments().add((ReportingPeriodReviewComment)reviewComment);
		adverseEventReportingPeriodDao.modifyOrSaveReviewStatusAndComments(reportingPeriod);
		return nextTransitionNames(workflowId, userId);
	}
	
	/**
	 * This method creates a comment to show the transition history. 
	 * @param toTransition
	 * @param userId
	 * @param domainObjectType
	 * @return
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
	
	public List<String> advanceReportWorkflow(Integer workflowId,String toTransition, Integer id, String userId) {
		Report report = reportDao.getById(id);
		return this.advanceReportWorkflow(workflowId, toTransition, report, userId);
	}
	
	public List<String> advanceReportWorkflow(Integer workflowId,String toTransition, Report report, String userId) {

		ReviewStatus nextStatus = workflowService.advanceWorkflow(workflowId, toTransition);
		report.setReviewStatus(nextStatus);
		for(ReviewComment reviewComment: report.getReviewComments()){
			reviewComment.setEditable(false);
		}
		// Add a comment describing action taken by the user
		ReviewComment reviewComment = createAdvanceWorkflowComment(toTransition, userId, "report");
		report.getReviewComments().add((ReportReviewComment)reviewComment);
		reportDao.save(report);
		return nextTransitionNamesForReportWorkflow(report, userId);
	}
	
	public List<String> nextTransitionNames(Integer workflowId, String loginId) {
		return workflowService.nextTransitionNames(workflowId, loginId);
	}
	
	/**
	 * This method is specifically called for aeReport workflow. This was needed to do an extra step of filtering the transtions.
	 * If all the reports in the aeReport are not in submittable state then, the CRA cannot submit the aeReport to the SAE coordinator
	 * for review. This filtering is applied to the list of transition names returned from workflowService in this method.
	 * First of all a check is made if such a filtering is needed, and if needed then the EvaulationService is called to check the 
	 * submittablity of all the reports (in the aeReport in context)
	 * @param workflowId
	 * @param loginId
	 * @return
	 */
	public List<String> nextTransitionNamesForReportWorkflow(Report report, String loginId){
		List<String> nextTransitionNames = workflowService.nextTransitionNames(report.getWorkflowId(), loginId);
		
		Map<Integer, Boolean> reportsSubmittable = new HashMap<Integer, Boolean>();
		reportsSubmittable.clear();
        
		if(report.getStatus().equals(ReportStatus.PENDING) || report.getStatus().equals(ReportStatus.FAILED) || report.getStatus().equals(ReportStatus.COMPLETED)){
			ReportSubmittability errorMessages = reportValidationService.isSubmittable(report);
        	reportsSubmittable.put(report.getId(), errorMessages.isSubmittable());
        }
        
        Boolean removeSubmitToSAECoordinator = true;
        for(Integer key: reportsSubmittable.keySet()){
        	if(reportsSubmittable.get(key).equals(true))
        		removeSubmitToSAECoordinator = false;
        }
        
        List<String> filteredTransitionNames = new ArrayList<String>();
        if(removeSubmitToSAECoordinator){
        	for(String transitionName: nextTransitionNames){
        		if(!transitionName.equals(SUBMIT_TO_CENTRAL_OFFICE_SAE_COORDINATOR))
        			filteredTransitionNames.add(transitionName);
        	}
        	return filteredTransitionNames;
        }
		return nextTransitionNames;
	}

	
	
	public List<AdverseEventReportingPeriodDTO> findAdverseEventReportingPeriods(Participant participant, Study study, StudySite studySite, ReviewStatus reviewStatus, String userId){
		/*AdverseEventReportingPeriodForReviewQuery query = new AdverseEventReportingPeriodForReviewQuery();
		
		if(participant != null){
			query.filterByParticipant(participant.getId());
		}
		
		if(study != null){
			query.filterByStudy(study.getId());
		}
		
		if(studySite != null){
			query.filterByStudySite(studySite.getId());
		}
		
		List<AdverseEventReportingPeriod> reportingPeriods = adverseEventReportingPeriodDao.findAdverseEventReportingPeriods(query);
		

		List<AdverseEventReportingPeriodDTO> reportingPeriodDTOs = new ArrayList<AdverseEventReportingPeriodDTO>();
		for(AdverseEventReportingPeriod reportingPeriod : reportingPeriods){
			if(reportingPeriod.getWorkflowId() != null && isReportingPeriodHavingSpecifiedReviewStatus(reportingPeriod, reviewStatus)){
				AdverseEventReportingPeriodDTO reportingPeriodDTO = routingAndReviewFactory.createAdverseEventEvalutionPeriodDTO(reportingPeriod, userId);
				
				//check the Reports
				if(reportingPeriod.getAeReports() != null){
					for(ExpeditedAdverseEventReport aeReport : reportingPeriod.getAeReports()){
						if(aeReport.getWorkflowId() == null) continue; //this report is prior to workflow integration
						ExpeditedAdverseEventReportDTO reportDTO = routingAndReviewFactory.createAdverseEventReportDTO(aeReport, userId);
						if(reportDTO.hasActionsToDo()) reportingPeriodDTO.addAdverseEventAeReportDTO(reportDTO);
					}//aereport
				}
				
				//only add the dto, if there is action to do.
				if(reportingPeriodDTO.hasActionsToDo()) reportingPeriodDTOs.add(reportingPeriodDTO);	
			}
			
		}
		
		return reportingPeriodDTOs;*/
		return null;
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
	 * Will return true, if the entity has the given workflow status
	 * @param rs
	 * @param wfAwareEntity
	 * @return
	 */
	protected boolean isEntityHavingSpecifiedReviewStatus( ReviewStatus rs,WorkflowAware wfAwareEntity){
		if(rs == null) return true; //return true if review status is null
		ReviewStatus entityRS = wfAwareEntity.getReviewStatus();
		if(entityRS == null) return false; //not participating in workflow
		return entityRS.equals(rs);
	}
	
	public void setRoutingAndReviewFactory(
			AERoutingAndReviewDTOFactory routingAndReviewFactory) {
		this.routingAndReviewFactory = routingAndReviewFactory;
	}
	
	public AERoutingAndReviewDTOFactory getRoutingAndReviewFactory() {
		return routingAndReviewFactory;
	}
	
	public void setAdverseEventReportingPeriodDao(
			AdverseEventReportingPeriodDao adverseEventReportingPeriodDao) {
		this.adverseEventReportingPeriodDao = adverseEventReportingPeriodDao;
	}
	public AdverseEventReportingPeriodDao getAdverseEventReportingPeriodDao() {
		return adverseEventReportingPeriodDao;
	}
	
	public ExpeditedAdverseEventReportDao getExpeditedAdverseEventReportDao() {
		return expeditedAdverseEventReportDao;
	}
	public void setExpeditedAdverseEventReportDao(
			ExpeditedAdverseEventReportDao expeditedAdverseEventReportDao) {
		this.expeditedAdverseEventReportDao = expeditedAdverseEventReportDao;
	}
	
	public WorkflowService getWorkflowService() {
		return workflowService;
	}
	public void setWorkflowService(WorkflowService workflowService) {
		this.workflowService = workflowService;
	}

	public void setReportValidationService(ReportValidationService reportValidationService){
		this.reportValidationService = reportValidationService;
	}
	
	public void setReportDao(ReportDao reportDao){
		this.reportDao = reportDao;
	}
	
	public ReportDao getReportDao(){
		return reportDao;
	}
	
}
