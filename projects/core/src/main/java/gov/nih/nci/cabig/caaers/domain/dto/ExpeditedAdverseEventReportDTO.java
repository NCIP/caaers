package gov.nih.nci.cabig.caaers.domain.dto;

import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.ReportStatus;
import gov.nih.nci.cabig.caaers.domain.ReviewStatus;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.report.ReportVersion;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author Biju Joseph
 *
 */
public class ExpeditedAdverseEventReportDTO {
	private Integer id;
	private Integer workflowId;
	private Integer noOfAe;
	private String name;
	private ReportStatus status;
	private String reportVersionId;
	
	private ReviewStatus reviewStatus;
	private List<String> possibleActions;
	private List<ReviewCommentsDTO> reviewComments;
	
	private Study study;
	private Participant participant;
	private ExpeditedAdverseEventReport aeReport;
	private List<ReportDTO> reports = new ArrayList<ReportDTO>();
	
	
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
	public ExpeditedAdverseEventReport getAeReport() {
		return aeReport;
	}
	public void setAeReport(ExpeditedAdverseEventReport aeReport) {
		this.aeReport = aeReport;
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
	
	public List<String> getPossibleActions() {
		return possibleActions;
	}
	public void setPossibleActions(List<String> possibleActions) {
		this.possibleActions = possibleActions;
	}
	public void addReportDTO(ReportDTO reportDTO){
		reports.add(reportDTO);
	}
	public List<ReportDTO> getReports() {
		return reports;
	}
	public void setReports(List<ReportDTO> reports) {
		this.reports = reports;
	}
	
	public boolean hasActionsToDo(){
		return possibleActions != null && possibleActions.size() > 0;
	}
}
