package gov.nih.nci.cabig.caaers.domain.repository;

import gov.nih.nci.cabig.caaers.dao.AdverseEventReportingPeriodDao;
import gov.nih.nci.cabig.caaers.dao.ExpeditedAdverseEventReportDao;
import gov.nih.nci.cabig.caaers.dao.query.AdverseEventReportingPeriodForReviewQuery;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.ReviewStatus;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.domain.dto.ExpeditedAdverseEventReportDTO;
import gov.nih.nci.cabig.caaers.domain.dto.AdverseEventReportingPeriodDTO;
import gov.nih.nci.cabig.caaers.domain.factory.AERoutingAndReviewDTOFactory;
import gov.nih.nci.cabig.caaers.domain.workflow.ReportReviewComment;
import gov.nih.nci.cabig.caaers.domain.workflow.ReportingPeriodReviewComment;
import gov.nih.nci.cabig.caaers.domain.workflow.ReviewComment;
import gov.nih.nci.cabig.caaers.domain.workflow.WorkflowAware;
import gov.nih.nci.cabig.caaers.domain.workflow.WorkflowConfig;
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
	
	private WorkflowService workflowService;
	
	/*
	 * (non-Javadoc)
	 * @see gov.nih.nci.cabig.caaers.domain.repository.AdverseEventRoutingAndReviewRepository#enactReportingPeriodWorkflow(gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod)
	 * 
	 * Algorithm :-
	 * 1. Find the site from assignment
	 * 2. Find the workflow from site (configured)
	 * 3. Enact the workflow
	 */
	public ProcessInstance enactReportingPeriodWorkflow(AdverseEventReportingPeriod reportingPeriod) {
		assert reportingPeriod.getId() != null;
		StudyParticipantAssignment assignment = reportingPeriod.getAssignment();
		StudySite studySite = assignment.getStudySite();
		WorkflowConfig wfConfig = studySite.getWorkflowConfigs().get("reportingPeriod");
		
		ProcessInstance pInstance = workflowService.createProcessInstance(wfConfig.getWorkflowDefinitionName());
		
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put(WorkflowService.VAR_STUDY_ID, studySite.getStudy().getId());
		variables.put(WorkflowService.VAR_WF_TYPE, AdverseEventReportingPeriod.class.getName());
		variables.put(WorkflowService.VAR_REPORTING_PERIOD_ID, reportingPeriod.getId());
		pInstance.getContextInstance().addVariables(variables);
		
		workflowService.makeDefaultTransition(pInstance.getId());
		
		reportingPeriod.setWorkflowId((int) pInstance.getId());
		reportingPeriod.setReviewStatus(ReviewStatus.DRAFT_INCOMPLETE);
		
		adverseEventReportingPeriodDao.save(reportingPeriod);
		
		return pInstance;
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
	public ProcessInstance enactReportWorkflow(ExpeditedAdverseEventReport aeReport) {
		
		assert aeReport.getId() != null;
		
		StudyParticipantAssignment assignment = aeReport.getAssignment();
		StudySite studySite = assignment.getStudySite();
		WorkflowConfig wfConfig = studySite.getWorkflowConfigs().get("report");
		
		ProcessInstance pInstance = workflowService.createProcessInstance(wfConfig.getWorkflowDefinitionName());
		
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put(WorkflowService.VAR_STUDY_ID, studySite.getStudy().getId());
		variables.put(WorkflowService.VAR_WF_TYPE, ExpeditedAdverseEventReport.class.getName());
		variables.put(WorkflowService.VAR_EXPEDITED_REPORT_ID, aeReport.getId());
		pInstance.getContextInstance().addVariables(variables);
		
		//workflowService.saveProcessInstance(pInstance);
		workflowService.makeDefaultTransition(pInstance.getId());
		
		aeReport.setWorkflowId((int)pInstance.getId());
		aeReport.setReviewStatus(ReviewStatus.DRAFT_INCOMPLETE);
		
		expeditedAdverseEventReportDao.save(aeReport);
		
		return pInstance;
	}
	
	
	public List<? extends ReviewComment> fetchReviewCommentsForReport(Integer aeReportId) {
		return expeditedAdverseEventReportDao.getById(aeReportId).getReviewComments();
	}
	
	public List<? extends ReviewComment> fetchReviewCommentsForReportingPeriod(Integer rpId) {
		return adverseEventReportingPeriodDao.getById(rpId).getReviewComments();
	}
	
	//TODO These methods needs to be uddated to add/edit comments to aeReport instead of a report.
	public void addReportReviewComment(Integer reportId, String comment, String userId){
		ExpeditedAdverseEventReport aeReport = expeditedAdverseEventReportDao.getById(reportId);
		this.addReportReviewComment(aeReport, comment, userId);
	}

	
	public void addReportReviewComment(ExpeditedAdverseEventReport report, String comment, String userId) {
		
		ReportReviewComment reviewComment = new ReportReviewComment();
		reviewComment.setUserComment(comment);
		reviewComment.setCreatedDate(new Date());
		reviewComment.setAutoGeneratedText("Added by " + userId);
		report.getReviewComments().add(reviewComment);
		
		expeditedAdverseEventReportDao.save(report);
	}
	
	public void editReportReviewComment(Integer aeReportId, String comment, String userId, Integer commentId){
		ExpeditedAdverseEventReport aeReport = expeditedAdverseEventReportDao.getById(aeReportId);
		this.editReportReviewComment(aeReport, comment, userId, commentId);
	}
	
	public void editReportReviewComment(ExpeditedAdverseEventReport aeReport, String comment, String userId, Integer commentId){
		for(ReportReviewComment reviewComment: aeReport.getReviewComments()){
			if(reviewComment.getId().equals(commentId)){
				reviewComment.setUserComment(comment);
				reviewComment.setCreatedDate(new Date());
			}
		}
		
		expeditedAdverseEventReportDao.save(aeReport);
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
		
		reportingPeriod.getReviewComments().add(reviewComment);
		adverseEventReportingPeriodDao.save(reportingPeriod);
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
		
		adverseEventReportingPeriodDao.save(reportingPeriod);
	}
	
	public List<String> advanceReportingPeriodWorkflow(Integer workflowId, String toTransition, Integer id) {
		AdverseEventReportingPeriod reportingPeriod = adverseEventReportingPeriodDao.getById(id);
		return this.advanceReportingPeriodWorkflow(workflowId, toTransition, reportingPeriod);
	}
	
	public List<String> advanceReportingPeriodWorkflow(Integer workflowId, String toTransition, AdverseEventReportingPeriod reportingPeriod){
		ReviewStatus nextStatus = workflowService.advanceWorkflow(workflowId, toTransition);
		reportingPeriod.setReviewStatus(nextStatus);
		adverseEventReportingPeriodDao.save(reportingPeriod);
		return workflowService.nextTransitionNames(workflowId);
	}
	
	public List<String> advanceReportWorkflow(Integer workflowId,String toTransition, Integer id) {
		ExpeditedAdverseEventReport aeReport = expeditedAdverseEventReportDao.getById(id);
		return this.advanceReportWorkflow(workflowId, toTransition, aeReport);
	}
	
	public List<String> advanceReportWorkflow(Integer workflowId,String toTransition, ExpeditedAdverseEventReport aeReport) {

		ReviewStatus nextStatus = workflowService.advanceWorkflow(workflowId, toTransition);
		aeReport.setReviewStatus(nextStatus);
		expeditedAdverseEventReportDao.save(aeReport);
		return workflowService.nextTransitionNames(workflowId);
	}
	
	public List<AdverseEventReportingPeriodDTO> findAdverseEventReportingPeriods(Participant participant, Study study, StudySite studySite, ReviewStatus reviewStatus){
		AdverseEventReportingPeriodForReviewQuery query = new AdverseEventReportingPeriodForReviewQuery();
		
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
		
		
		List<AdverseEventReportingPeriodDTO> reportingPeriodDtos = new ArrayList<AdverseEventReportingPeriodDTO>();
		for(AdverseEventReportingPeriod reportingPeriod : reportingPeriods){
			if(reportingPeriod.getWorkflowId() != null && isReportingPeriodHavingSpecifiedReviewStatus(reportingPeriod, reviewStatus)){
				AdverseEventReportingPeriodDTO rpDto = routingAndReviewFactory.createAdverseEventEvalutionPeriodDTO(reportingPeriod);
				reportingPeriodDtos.add(rpDto);	
				
				//check the Reports
				if(reportingPeriod.getAeReports() != null){
					for(ExpeditedAdverseEventReport aeReport : reportingPeriod.getAeReports()){
						if(aeReport.getWorkflowId() == null) continue; //this report is prior to workflow integration
						ExpeditedAdverseEventReportDTO rDto = routingAndReviewFactory.createAdverseEventReportDTO(aeReport);
						rpDto.addAdverseEventReportDTO(rDto);
					}//aereport
				}
				
			}
			
		}
		
		return reportingPeriodDtos;
		
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
				if(aeReport.getReviewStatus() != null && aeReport.getReviewStatus().equals(rs)) 
					return true; 
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
}
