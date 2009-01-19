package gov.nih.nci.cabig.caaers.domain.dto;

import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.ReviewStatus;
import gov.nih.nci.cabig.caaers.domain.Study;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdverseEventReportingPeriodDTO {
	
	private Integer id;
	private Integer workflowId;
	
	private String evaluationPeriodName;
	private String evaluationPeriodTypeName;
	private Integer noOfAe;
	private Integer noOfReport;
	private boolean dcpSponsoredStudy;
	
	private ReviewStatus reviewStatus;
	private List<String> possibleActions;
	private List<ReviewCommentsDTO> reviewComments;
	
	private List<AdverseEventReportDTO> reports;
	
	
	
	private Study study;
	private Participant participant;
	private AdverseEventReportingPeriod adverseEventReportingPeriod;
	
	
	public void addAdverseEventReportDTO(AdverseEventReportDTO report){
		if(reports == null) reports = new ArrayList<AdverseEventReportDTO>();
		reports.add(report);
	}
	
	public void addReviewCommentsDTO(ReviewCommentsDTO reviewComment){
		if(reviewComments == null) reviewComments = new ArrayList<ReviewCommentsDTO>();
		reviewComments.add(reviewComment);
	}
	
	public void addPossibleActions(String transition){
		if(possibleActions == null) possibleActions = new ArrayList<String>();
		possibleActions.add(transition);
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEvaluationPeriodName() {
		return evaluationPeriodName;
	}
	public void setEvaluationPeriodName(String evaluationPeriodName) {
		this.evaluationPeriodName = evaluationPeriodName;
	}
	public String getEvaluationPeriodTypeName() {
		return evaluationPeriodTypeName;
	}
	public void setEvaluationPeriodTypeName(String evaluationPeriodTypeName) {
		this.evaluationPeriodTypeName = evaluationPeriodTypeName;
	}
	public Integer getNoOfAe() {
		return noOfAe;
	}
	public void setNoOfAe(Integer noOfAe) {
		this.noOfAe = noOfAe;
	}
	public Integer getNoOfReport() {
		return noOfReport;
	}
	public void setNoOfReport(Integer noOfReport) {
		this.noOfReport = noOfReport;
	}
	public List<String> getPossibleActions() {
		return possibleActions;
	}
	public void setPossibleActions(List<String> possibleActions) {
		this.possibleActions = possibleActions;
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
	
	public List<AdverseEventReportDTO> getReports() {
		return reports;
	}
	
	public ReviewStatus getReviewStatus() {
		return reviewStatus;
	}
	public void setReviewStatus(ReviewStatus reviewStatus) {
		this.reviewStatus = reviewStatus;
	}
	
	public AdverseEventReportingPeriod getAdverseEventReportingPeriod() {
		return adverseEventReportingPeriod;
	}
	public void setAdverseEventReportingPeriod(
			AdverseEventReportingPeriod adverseEventReportingPeriod) {
		this.adverseEventReportingPeriod = adverseEventReportingPeriod;
	}
	public Integer getWorkflowId() {
		return workflowId;
	}
	public void setWorkflowId(Integer workflowId) {
		this.workflowId = workflowId;
	}
	public boolean isDcpSponsoredStudy() {
		return dcpSponsoredStudy;
	}
	public void setDcpSponsoredStudy(boolean dcpSponsoredStudy) {
		this.dcpSponsoredStudy = dcpSponsoredStudy;
	}
}
