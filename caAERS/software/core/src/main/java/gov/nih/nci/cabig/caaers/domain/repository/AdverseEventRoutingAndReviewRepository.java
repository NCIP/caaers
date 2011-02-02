package gov.nih.nci.cabig.caaers.domain.repository;

import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.ReportStatus;
import gov.nih.nci.cabig.caaers.domain.ReviewStatus;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.domain.dto.AdverseEventReportingPeriodDTO;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.workflow.ReviewComment;

import java.util.List;
 

/**
 * This interface has methods to save and retrieve the {@link ReviewComment}s, and to change the review state of {@link Report} and {@link AdverseEventReportingPeriod}.
 *
 * @author Biju Joseph
 */
public interface AdverseEventRoutingAndReviewRepository {
	
	/**
	 * Will query the {@link AdverseEventReportingPeriodDTO} objects, matching the criteria.
	 *
	 * @param participant the participant
	 * @param study the study
	 * @param organization the organization
	 * @param reviewStatus the review status
	 * @param reportStatus the report status
	 * @param userId the user id
	 * @param courseWorkflowEnabled the course workflow enabled
	 * @return the list
	 */
	public List<AdverseEventReportingPeriodDTO> findAdverseEventReportingPeriods(Participant participant, Study study, 
			Organization organization, ReviewStatus reviewStatus, ReportStatus reportStatus, String userId, Boolean courseWorkflowEnabled);
	
	/**
	 * Will list the review comments associated to a report.
	 *
	 * @param reportId the report id
	 * @return the list<? extends review comment>
	 */
	public List<? extends ReviewComment> fetchReviewCommentsForReport(Integer reportId);
	
	/**
	 * Will list the review comments associated to reporting period.
	 *
	 * @param rpId the rp id
	 * @return the list<? extends review comment>
	 */
	public List<? extends ReviewComment> fetchReviewCommentsForReportingPeriod(Integer rpId);
	
	/**
	 * Will add a review comment against the {@link Report}.
	 *
	 * @param reportId the report id
	 * @param comment the comment
	 * @param userId the user id
	 */
	//TODO This has to change to accept aeReport instead of a report.
	public void addReportReviewComment(Integer reportId, String comment, String userId);
	
	/**
	 * Adds the report review comment.
	 *
	 * @param report the report
	 * @param comment the comment
	 * @param userId the user id
	 */
	public void addReportReviewComment(Report report, String comment, String userId);
	
	/**
	 * Will add a review comment against the reporting period.
	 *
	 * @param reportingPeriodId the reporting period id
	 * @param comment the comment
	 * @param userId the user id
	 */
	public void addReportingPeriodReviewComment(Integer reportingPeriodId, String comment, String userId);
	
	/**
	 * Adds the reporting period review comment.
	 *
	 * @param reportingPeriod the reporting period
	 * @param comment the comment
	 * @param userId the user id
	 */
	public void addReportingPeriodReviewComment(AdverseEventReportingPeriod reportingPeriod, String comment, String userId);
	
	/**
	 * Will edit a review comment against the {@link aeReport}.
	 *
	 * @param reportId the report id
	 * @param comment the comment
	 * @param userId the user id
	 * @param commentId the comment id
	 */
	//TODO This method needs an implementation once the data model changes have been made.
	public void editReportReviewComment(Integer reportId, String comment, String userId, Integer commentId);
	
	/**
	 * Will edit a review comment against the {@link aeReport}.
	 *
	 * @param report the report
	 * @param comment the comment
	 * @param userId the user id
	 * @param commentId the comment id
	 */
	//TODO This method needs an implementation once the data model changes have been made.
	public void editReportReviewComment(Report report, String comment, String userId, Integer commentId);
	
	
	/**
	 * Will edit a review comment against the {@link reportingPeriod}.
	 *
	 * @param reportingPeriodId the reporting period id
	 * @param comment the comment
	 * @param userId the user id
	 * @param commentId the comment id
	 */
	public void editReportingPeriodReviewComment(Integer reportingPeriodId, String comment, String userId, Integer commentId);
	
	/**
	 * Will edit a review comment against the {@link reportingPeriod}.
	 *
	 * @param reportingPeriod the reporting period
	 * @param comment the comment
	 * @param userId the user id
	 * @param commentId the comment id
	 */
	public void editReportingPeriodReviewComment(AdverseEventReportingPeriod reportingPeriod, String comment, String userId, Integer commentId);
	
	/**
	 * Will delete a review comment with commentId passed to the method.
	 *
	 * @param reportId the report id
	 * @param commentId the comment id
	 */
	public void deleteReportReviewComment(Integer reportId, Integer commentId);
	
	/**
	 * Will delete a review comment with commentId passed to the method.
	 *
	 * @param report the report
	 * @param commentId the comment id
	 */
	public void deleteReportReviewComment(Report report, Integer commentId);
	
	/**
	 * Will delete a review comment with commentId passed to the method.
	 *
	 * @param reportingPeriodId the reporting period id
	 * @param commentId the comment id
	 */
	public void deleteReportingPeriodReviewComment(Integer reportingPeriodId, Integer commentId);
	
	/**
	 * Will delete a review comment with commentId passed to the method.
	 *
	 * @param reportingPeriod the reporting period
	 * @param commentId the comment id
	 */
	public void deleteReportingPeriodReviewComment(AdverseEventReportingPeriod reportingPeriod, Integer commentId);
	
	/**
	 * Will advance the workflow to its next step, for the Report.
	 *
	 * @param workflowId the workflow id
	 * @param transition the transition
	 * @param id the id
	 * @param userId the user id
	 * @return the list
	 */
	public List<String> advanceReportWorkflow(Integer workflowId, String transition, Integer id, String userId);
	
	/**
	 * Advance report workflow.
	 *
	 * @param workflowId the workflow id
	 * @param transition the transition
	 * @param report the report
	 * @param userId the user id
	 * @return the list
	 */
	public List<String> advanceReportWorkflow(Integer workflowId, String transition, Report report, String userId);
	
	/**
	 * Will advance the workflow to its next step, for the reporting period.
	 *
	 * @param workflowId the workflow id
	 * @param transition the transition
	 * @param id the id
	 * @param userId the user id
	 * @return the list
	 */
	public List<String> advanceReportingPeriodWorkflow(Integer workflowId, String transition, Integer id, String userId);
	
	/**
	 * Advance reporting period workflow.
	 *
	 * @param workflowId the workflow id
	 * @param transition the transition
	 * @param reportingPeriod the reporting period
	 * @param userId the user id
	 * @return the list
	 */
	public List<String> advanceReportingPeriodWorkflow(Integer workflowId, String transition, AdverseEventReportingPeriod reportingPeriod, String userId);
	
	/**
	 * This method will enact a new workflow, for the expedited report.
	 *
	 * @param report the report
	 * @return the long
	 */
	public Long enactReportWorkflow(Report report);
	
	
	/**
	 * This method will enact a new workflow, for the evaluation period.
	 *
	 * @param reportingPeriod the reporting period
	 * @return the long
	 */
	public Long enactReportingPeriodWorkflow(AdverseEventReportingPeriod reportingPeriod);
	
	/**
	 * This method will return the next workflow transitions, available for the user.
	 *
	 * @param workflowId the workflow id
	 * @param userId the user id
	 * @return the list
	 */
	public List<String> nextTransitionNames(Integer workflowId, String userId);

	/**
	 * This method will return the next workflow transitions, available for the user for aeReport workflow.
	 * It does the filtering of the next transitions depending upon whether the reports are submittable or not.
	 *
	 * @param report the report
	 * @param loginId the login id
	 * @return the list
	 */
	public List<String> nextTransitionNamesForReportWorkflow(Report report, String loginId);
}
