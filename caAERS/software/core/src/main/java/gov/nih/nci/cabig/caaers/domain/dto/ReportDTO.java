package gov.nih.nci.cabig.caaers.domain.dto;

import java.util.List;

import gov.nih.nci.cabig.caaers.domain.ReportStatus;
import gov.nih.nci.cabig.caaers.domain.ReviewStatus;
import gov.nih.nci.cabig.caaers.domain.report.Report;

/**
 * 
 * @author Biju Joseph
 *
 */
public class ReportDTO {
	private String name;
	private Integer id;
	private Integer reportVersionId;
	private Integer noOfAe;
	private ReportStatus status;
	private Report report;
	
	private Integer workflowId;
	private ReviewStatus reviewStatus;
	private List<String> possibleActions;
	private List<ReviewCommentsDTO> reviewComments;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setReportVersionId(Integer reportVersionId) {
		this.reportVersionId = reportVersionId;
	}
	public Integer getReportVersionId() {
		return reportVersionId;
	}
	
	public ReportStatus getStatus(){
		return status;
	}
	
	public void setStatus(ReportStatus status){
		this.status = status;
	}
	
	public void setReport(Report report){
		this.report = report;
	}
	
	public Report getReport(){
		return report;
	}
	
	public Integer getNoOfAe(){
		return noOfAe;
	}
	
	public void setNoOfAe(Integer noOfAe){
		this.noOfAe = noOfAe;
	}
	
	public ReviewStatus getReviewStatus() {
		return reviewStatus;
	}
	
	public void setReviewStatus(ReviewStatus reviewStatus) {
		this.reviewStatus = reviewStatus;
	}

	public List<ReviewCommentsDTO> getReviewComments() {
		return reviewComments;
	}
	
	public void setReviewComments(List<ReviewCommentsDTO> reviewComments) {
		this.reviewComments = reviewComments;
	}
	
	public Integer getWorkflowId() {
		return workflowId;
	}
	
	public void setWorkflowId(Integer workflowId) {
		this.workflowId = workflowId;
	}
	
	public List<String> getPossibleActions() {
		return possibleActions;
	}
	
	public void setPossibleActions(List<String> possibleActions) {
		this.possibleActions = possibleActions;
	}
	
	public boolean hasActionsToDo(){
		return possibleActions != null && possibleActions.size() > 0;
	}
	
	public boolean hasWorkflowEnded(){
		return this.reviewStatus.equals(ReviewStatus.SUBMIT_TO_SPONSOR);
	}
}
