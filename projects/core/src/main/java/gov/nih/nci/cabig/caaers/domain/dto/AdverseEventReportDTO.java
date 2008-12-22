package gov.nih.nci.cabig.caaers.domain.dto;

import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.ReportStatus;
import gov.nih.nci.cabig.caaers.domain.ReviewStatus;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.report.ReportVersion;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author Biju Joseph
 *
 */
public class AdverseEventReportDTO {
	private Integer id;
	private Integer workflowId;
	private Integer noOfAe;
	private String name;
	private ReportStatus status;
	private String reportVersionId;
	
	private ReviewStatus reviewStatus;
	private List<ReviewStatus> possibleReivewStatuses;
	private List<ReviewCommentsDTO> reviewComments;
	
	private Study study;
	private Participant participant;
	private Report report;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getNoOfAe() {
		return noOfAe;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setNoOfAe(Integer noOfAe) {
		this.noOfAe = noOfAe;
	}
	public ReviewStatus getReviewStatus() {
		return reviewStatus;
	}
	public void setReviewStatus(ReviewStatus reviewStatus) {
		this.reviewStatus = reviewStatus;
	}
	public List<ReviewStatus> getPossibleReivewStatuses() {
		return possibleReivewStatuses;
	}
	public void setPossibleReivewStatuses(
			List<ReviewStatus> possibleReivewStatuses) {
		this.possibleReivewStatuses = possibleReivewStatuses;
	}
	public List<ReviewCommentsDTO> getReviewComments() {
		return reviewComments;
	}
	public void setReviewComments(List<ReviewCommentsDTO> reviewComments) {
		this.reviewComments = reviewComments;
	}
	public Study getStudy() {
		return study;
	}
	public void setStudy(Study study) {
		this.study = study;
	}
	public Participant getParticipant() {
		return participant;
	}
	public void setParticipant(Participant participant) {
		this.participant = participant;
	}
	public Report getReport() {
		return report;
	}
	public void setReport(Report report) {
		this.report = report;
	}
	public ReportStatus getStatus() {
		return status;
	}
	public void setStatus(ReportStatus status) {
		this.status = status;
	}
	public String getReportVersionId() {
		return reportVersionId;
	}
	public void setReportVersionId(String reportVersionId) {
		this.reportVersionId = reportVersionId;
	}
	
	public Integer getWorkflowId() {
		return workflowId;
	}
	public void setWorkflowId(Integer workflowId) {
		this.workflowId = workflowId;
	}
}
