package gov.nih.nci.cabig.caaers.domain.dto;

import java.util.List;

import gov.nih.nci.cabig.caaers.domain.ReportStatus;
import gov.nih.nci.cabig.caaers.domain.ReviewStatus;
import gov.nih.nci.cabig.caaers.domain.report.Report;

 
/**
 * The Class ReportDTO.
 *
 * @author Biju Joseph
 */
public class ReportDTO {
	
	/** The name. */
	private String name;
	
	/** The id. */
	private Integer id;
	
	/** The report version id. */
	private Integer reportVersionId;
	
	/** The no of ae. */
	private Integer noOfAe;
	
	/** The status. */
	private ReportStatus status;
	
	/** The report. */
	private Report report;
	
	/** The workflow id. */
	private Integer workflowId;
	
	/** The review status. */
	private ReviewStatus reviewStatus;
	
	/** The possible actions. */
	private List<String> possibleActions;
	
	/** The review comments. */
	private List<ReviewCommentsDTO> reviewComments;
	
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the report version id.
	 *
	 * @param reportVersionId the new report version id
	 */
	public void setReportVersionId(Integer reportVersionId) {
		this.reportVersionId = reportVersionId;
	}
	
	/**
	 * Gets the report version id.
	 *
	 * @return the report version id
	 */
	public Integer getReportVersionId() {
		return reportVersionId;
	}
	
	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public ReportStatus getStatus(){
		return status;
	}
	
	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(ReportStatus status){
		this.status = status;
	}
	
	/**
	 * Sets the report.
	 *
	 * @param report the new report
	 */
	public void setReport(Report report){
		this.report = report;
	}
	
	/**
	 * Gets the report.
	 *
	 * @return the report
	 */
	public Report getReport(){
		return report;
	}
	
	/**
	 * Gets the no of ae.
	 *
	 * @return the no of ae
	 */
	public Integer getNoOfAe(){
		return noOfAe;
	}
	
	/**
	 * Sets the no of ae.
	 *
	 * @param noOfAe the new no of ae
	 */
	public void setNoOfAe(Integer noOfAe){
		this.noOfAe = noOfAe;
	}
	
	/**
	 * Gets the review status.
	 *
	 * @return the review status
	 */
	public ReviewStatus getReviewStatus() {
		return reviewStatus;
	}
	
	/**
	 * Sets the review status.
	 *
	 * @param reviewStatus the new review status
	 */
	public void setReviewStatus(ReviewStatus reviewStatus) {
		this.reviewStatus = reviewStatus;
	}

	/**
	 * Gets the review comments.
	 *
	 * @return the review comments
	 */
	public List<ReviewCommentsDTO> getReviewComments() {
		return reviewComments;
	}
	
	/**
	 * Sets the review comments.
	 *
	 * @param reviewComments the new review comments
	 */
	public void setReviewComments(List<ReviewCommentsDTO> reviewComments) {
		this.reviewComments = reviewComments;
	}
	
	/**
	 * Gets the workflow id.
	 *
	 * @return the workflow id
	 */
	public Integer getWorkflowId() {
		return workflowId;
	}
	
	/**
	 * Sets the workflow id.
	 *
	 * @param workflowId the new workflow id
	 */
	public void setWorkflowId(Integer workflowId) {
		this.workflowId = workflowId;
	}
	
	/**
	 * Gets the possible actions.
	 *
	 * @return the possible actions
	 */
	public List<String> getPossibleActions() {
		return possibleActions;
	}
	
	/**
	 * Sets the possible actions.
	 *
	 * @param possibleActions the new possible actions
	 */
	public void setPossibleActions(List<String> possibleActions) {
		this.possibleActions = possibleActions;
	}
	
	/**
	 * Checks for actions to do.
	 *
	 * @return true, if successful
	 */
	public boolean hasActionsToDo(){
		return possibleActions != null && possibleActions.size() > 0;
	}
	
	/**
	 * Checks for workflow ended.
	 *
	 * @return true, if successful
	 */
	public boolean hasWorkflowEnded(){
		return this.reviewStatus.equals(ReviewStatus.SUBMIT_TO_SPONSOR);
	}
}
