package gov.nih.nci.cabig.caaers.domain.repository;

import java.util.List;

import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.ReviewStatus;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.domain.dto.AdverseEventReportingPeriodDTO;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.workflow.ReviewComment;
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
	public void addReportReviewComment(Integer reportId, String comment, String userId);
	
	/**
	 * Will add a review comment against the reporting period
	 * @param reportingPeriodId
	 * @param comment
	 * @param userId
	 */
	public void addReportingPeriodReviewComment(Integer reportingPeriodId, String comment, String userId);
	
	/**
	 * Will advance the workflow to its next step, for the Report
	 * @param workflowId
	 * @param transition
	 * @param id
	 * @return
	 */
	public List<String> advanceReportWorkflow(Integer workflowId, String transition, Integer id);
	
	/**
	 * Will advance the workflow to its next step, for the reporting period
	 * @param workflowId
	 * @param transition
	 * @param id
	 * @return
	 */
	public List<String> advanceReportingPeriodWorkflow(Integer workflowId, String transition, Integer id);
}
