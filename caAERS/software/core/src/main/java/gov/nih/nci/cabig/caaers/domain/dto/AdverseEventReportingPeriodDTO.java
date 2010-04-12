package gov.nih.nci.cabig.caaers.domain.dto;

import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.ReportStatus;
import gov.nih.nci.cabig.caaers.domain.ReviewStatus;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.report.Report;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Transient;

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
	
	private List<ExpeditedAdverseEventReportDTO> aeReports;
	private List<ExpeditedAdverseEventReportDTO> activeAeReports;
	
	
	
	private Study study;
	private Participant participant;
	private AdverseEventReportingPeriod adverseEventReportingPeriod;
	
	
	public void addAdverseEventAeReportDTO(ExpeditedAdverseEventReportDTO aeReport){
		if(aeReports == null) aeReports = new ArrayList<ExpeditedAdverseEventReportDTO>();
		aeReports.add(aeReport);
	}
	
	public void addReviewCommentsDTO(ReviewCommentsDTO reviewComment){
		if(reviewComments == null) reviewComments = new ArrayList<ReviewCommentsDTO>();
		reviewComments.add(reviewComment);
	}
	
	public void addPossibleActions(String transition){
		if(possibleActions == null) possibleActions = new ArrayList<String>();
		possibleActions.add(transition);
	}
	
	
	 /**
     * This method returns a list of expedited aeReports that are active. An Expedited AeReport
     * is active if it has atleast on report in non-withdrawn state.
     * @return
     */
    public List<ExpeditedAdverseEventReportDTO> getActiveAeReports() {
    	activeAeReports = new ArrayList<ExpeditedAdverseEventReportDTO>();
    	if(aeReports != null)
    	{
    		for(ExpeditedAdverseEventReportDTO aeReport: aeReports){
    			if(isAeReportActive(aeReport))
    				activeAeReports.add(aeReport);
    		}
    	}
    	return activeAeReports;
    }
    
    /**
     * If any report associated to expedited AeReport is in non-withdrawn state,
     * the expedited aeReport is considered to be an active report.
     * @param aeReport
     * @return
     */
    private Boolean isAeReportActive(ExpeditedAdverseEventReportDTO aeReport){
    	for(ReportDTO report: aeReport.getReports()){
    		if(!report.getStatus().equals(ReportStatus.WITHDRAWN) && 
    				!report.getStatus().equals(ReportStatus.REPLACED) && 
    				!report.getStatus().equals(ReportStatus.AMENDED))
    			return true;
    	}
    	return false;
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
	
	public List<ExpeditedAdverseEventReportDTO> getAeReports() {
		return aeReports;
	}
	
	public void setReports(List<ExpeditedAdverseEventReportDTO> aeReports){
		this.aeReports = aeReports;
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
	
	public boolean hasActionsToDo(){
		boolean hasActions = possibleActions != null && possibleActions.size() > 0;
		if(!hasActions && aeReports != null){
			for(ExpeditedAdverseEventReportDTO aeReportDTO : aeReports){
				hasActions |= aeReportDTO.hasActionsToDo();
			}
		}
		return hasActions;
	}
	
	public boolean hasReportWorkflowEnded(){
		boolean reportWorkflowEnded = false;
		if(aeReports != null && !aeReports.isEmpty()){
			for(ExpeditedAdverseEventReportDTO aeReportDTO : aeReports){
				reportWorkflowEnded |= aeReportDTO.hasWorkflowEnded();
			}
		}
		return reportWorkflowEnded;
	}
}
