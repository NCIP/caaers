package gov.nih.nci.cabig.caaers.domain.repository;

import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.ReviewStatus;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.domain.dto.AdverseEventReportingPeriodDTO;
import gov.nih.nci.cabig.caaers.domain.workflow.ReviewComment;

import java.util.List;

import org.jbpm.graph.exe.ProcessInstance;
/**
 * This interface has methods to save and retrieve the {@link ReviewComment}s, and to change the review state of {@link Report} and {@link AdverseEventReportingPeriod} 
 * 
 * 
 * @author Biju Joseph
 *
 */
public interface AdverseEventRoutingAndReviewRepository {
	/**
	 * Will query the {@link AdverseEventReportingPeriodDTO} objects, matching the criteria. 
	 * @param participant
	 * @param study
	 * @param studySite
	 * @param reviewStatus
	 * @return
	 */
	public List<AdverseEventReportingPeriodDTO> findAdverseEventReportingPeriods(Participant participant, Study study, StudySite studySite, ReviewStatus reviewStatus);
	
	/**
	 * Will list the review comments associated to a report
	 * @param reportId
	 * @return
	 */
	public List<? extends ReviewComment> fetchReviewCommentsForReport(Integer reportId);
	
	/**
	 * Will list the review comments associated to reporting period
	 * @param rpId
	 * @return
	 */
	public List<? extends ReviewComment> fetchReviewCommentsForReportingPeriod(Integer rpId);
	
	/**
	 * Will add a review comment against the {@link Report}
	 * @param reportId
	 * @param comment
	 * @param userId
	 */
	//TODO This has to change to accept aeReport instead of a report.
	public void addReportReviewComment(Integer reportId, String comment, String userId);
	public void addReportReviewComment(ExpeditedAdverseEventReport report, String comment, String userId);
	
	/**
	 * Will add a review comment against the reporting period
	 * @param reportingPeriodId
	 * @param comment
	 * @param userId
	 */
	public void addReportingPeriodReviewComment(Integer reportingPeriodId, String comment, String userId);
	public void addReportingPeriodReviewComment(AdverseEventReportingPeriod reportingPeriod, String comment, String userId);
	
	/**
	 * Will edit a review comment against the {@link aeReport}
	 * @param reportId
	 * @param comment
	 * @param userId
	 * @param commentId
	 */
	//TODO This method needs an implementation once the data model changes have been made.
	public void editReportReviewComment(Integer reportId, String comment, String userId, Integer commentId);
	
	/**
	 * Will edit a review comment against the {@link aeReport}
	 * @param aeReport
	 * @param comment
	 * @param userId
	 * @param commentId
	 */
	//TODO This method needs an implementation once the data model changes have been made.
	public void editReportReviewComment(ExpeditedAdverseEventReport aeReport, String comment, String userId, Integer commentId);
	
	
	/**
	 * Will edit a review comment against the {@link reportingPeriod}
	 * @param reportingPeriodId
	 * @param comment
	 * @param userId
	 * @param commentId
	 */
	public void editReportingPeriodReviewComment(Integer reportingPeriodId, String comment, String userId, Integer commentId);
	
	/**
	 * Will edit a review comment against the {@link reportingPeriod}
	 * @param reportingPeriod
	 * @param comment
	 * @param userId
	 * @param commentId
	 */
	public void editReportingPeriodReviewComment(AdverseEventReportingPeriod reportingPeriod, String comment, String userId, Integer commentId);
	
	/**
	 * Will advance the workflow to its next step, for the Report
	 * @param workflowId
	 * @param transition
	 * @param id
	 * @return
	 */
	public List<String> advanceReportWorkflow(Integer workflowId, String transition, Integer id);
	public List<String> advanceReportWorkflow(Integer workflowId, String transition, ExpeditedAdverseEventReport report);
	
	/**
	 * Will advance the workflow to its next step, for the reporting period
	 * @param workflowId
	 * @param transition
	 * @param id
	 * @return
	 */
	public List<String> advanceReportingPeriodWorkflow(Integer workflowId, String transition, Integer id);
	public List<String> advanceReportingPeriodWorkflow(Integer workflowId, String transition, AdverseEventReportingPeriod reportingPeriod);
	
	/**
	 * This method will enact a new workflow, for the expedited report
	 * @param aeReport
	 * @return
	 */
	public ProcessInstance enactReportWorkflow(ExpeditedAdverseEventReport report);
	
	
	/**
	 * This method will enact a new workflow, for the evaluation period
	 * @param reportingPeriod
	 * @return
	 */
	public ProcessInstance enactReportingPeriodWorkflow(AdverseEventReportingPeriod reportingPeriod);
}
