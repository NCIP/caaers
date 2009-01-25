package gov.nih.nci.cabig.caaers.domain.dto;

import gov.nih.nci.cabig.caaers.domain.ReviewStatus;

/**
 * 
 * @author Biju Joseph
 *
 */
public class ReportDTO {
	private String name;
	private Integer id;
	private Integer reportVersionId;
	private Long workflowId;
	private ReviewStatus reviewStatus;
	
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
	public ReviewStatus getReviewStatus() {
		return reviewStatus;
	}
	public void setReviewStatus(ReviewStatus reviewStatus) {
		this.reviewStatus = reviewStatus;
	}
	public void setWorkflowId(Long workflowId) {
		this.workflowId = workflowId;
	}
	public Long getWorkflowId() {
		return workflowId;
	}
}
