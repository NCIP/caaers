/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
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

 
/**
 * The Class AdverseEventReportingPeriodDTO.
 */
public class AdverseEventReportingPeriodDTO {
	
	/** The id. */
	private Integer id;
	
	/** The workflow id. */
	private Integer workflowId;
	
	/** The evaluation period name. */
	private String evaluationPeriodName;
	
	/** The evaluation period type name. */
	private String evaluationPeriodTypeName;
	
	/** The no of ae. */
	private Integer noOfAe;
	
	/** The no of report. */
	private Integer noOfReport;
	
	/** The dcp sponsored study. */
	private boolean dcpSponsoredStudy;
	
	/** The review status. */
	private ReviewStatus reviewStatus;
	
	/** The possible actions. */
	private List<String> possibleActions;
	
	/** The review comments. */
	private List<ReviewCommentsDTO> reviewComments;
	
	/** The ae reports. */
	private List<ExpeditedAdverseEventReportDTO> aeReports;
	
	/** The active ae reports. */
	private List<ExpeditedAdverseEventReportDTO> activeAeReports;
	
	
	
	/** The study. */
	private Study study;
	
	/** The participant. */
	private Participant participant;
	
	/** The adverse event reporting period. */
	private AdverseEventReportingPeriod adverseEventReportingPeriod;
	
	
	/**
	 * Adds the adverse event ae report dto.
	 *
	 * @param aeReport the ae report
	 */
	public void addAdverseEventAeReportDTO(ExpeditedAdverseEventReportDTO aeReport){
		if(aeReports == null) aeReports = new ArrayList<ExpeditedAdverseEventReportDTO>();
		aeReports.add(aeReport);
	}
	
	/**
	 * Adds the review comments dto.
	 *
	 * @param reviewComment the review comment
	 */
	public void addReviewCommentsDTO(ReviewCommentsDTO reviewComment){
		if(reviewComments == null) reviewComments = new ArrayList<ReviewCommentsDTO>();
		reviewComments.add(reviewComment);
	}
	
	/**
	 * Adds the possible actions.
	 *
	 * @param transition the transition
	 */
	public void addPossibleActions(String transition){
		if(possibleActions == null) possibleActions = new ArrayList<String>();
		possibleActions.add(transition);
	}
	
	
	 /**
 	 * This method returns a list of expedited aeReports that are active. An Expedited AeReport
 	 * is active if it has atleast on report in non-withdrawn state.
 	 *
 	 * @return the active ae reports
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
     *
     * @param aeReport the ae report
     * @return the boolean
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
	 * Gets the evaluation period name.
	 *
	 * @return the evaluation period name
	 */
	public String getEvaluationPeriodName() {
		return evaluationPeriodName;
	}
	
	/**
	 * Sets the evaluation period name.
	 *
	 * @param evaluationPeriodName the new evaluation period name
	 */
	public void setEvaluationPeriodName(String evaluationPeriodName) {
		this.evaluationPeriodName = evaluationPeriodName;
	}
	
	/**
	 * Gets the evaluation period type name.
	 *
	 * @return the evaluation period type name
	 */
	public String getEvaluationPeriodTypeName() {
		return evaluationPeriodTypeName;
	}
	
	/**
	 * Sets the evaluation period type name.
	 *
	 * @param evaluationPeriodTypeName the new evaluation period type name
	 */
	public void setEvaluationPeriodTypeName(String evaluationPeriodTypeName) {
		this.evaluationPeriodTypeName = evaluationPeriodTypeName;
	}
	
	/**
	 * Gets the no of ae.
	 *
	 * @return the no of ae
	 */
	public Integer getNoOfAe() {
		return noOfAe;
	}
	
	/**
	 * Sets the no of ae.
	 *
	 * @param noOfAe the new no of ae
	 */
	public void setNoOfAe(Integer noOfAe) {
		this.noOfAe = noOfAe;
	}
	
	/**
	 * Gets the no of report.
	 *
	 * @return the no of report
	 */
	public Integer getNoOfReport() {
		return noOfReport;
	}
	
	/**
	 * Sets the no of report.
	 *
	 * @param noOfReport the new no of report
	 */
	public void setNoOfReport(Integer noOfReport) {
		this.noOfReport = noOfReport;
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
	 * Gets the study.
	 *
	 * @return the study
	 */
	public Study getStudy() {
		return study;
	}
	
	/**
	 * Sets the study.
	 *
	 * @param study the new study
	 */
	public void setStudy(Study study) {
		this.study = study;
	}
	
	/**
	 * Gets the participant.
	 *
	 * @return the participant
	 */
	public Participant getParticipant() {
		return participant;
	}
	
	/**
	 * Sets the participant.
	 *
	 * @param participant the new participant
	 */
	public void setParticipant(Participant participant) {
		this.participant = participant;
	}
	
	/**
	 * Gets the ae reports.
	 *
	 * @return the ae reports
	 */
	public List<ExpeditedAdverseEventReportDTO> getAeReports() {
		return aeReports;
	}
	
	/**
	 * Sets the reports.
	 *
	 * @param aeReports the new reports
	 */
	public void setReports(List<ExpeditedAdverseEventReportDTO> aeReports){
		this.aeReports = aeReports;
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
	 * Gets the adverse event reporting period.
	 *
	 * @return the adverse event reporting period
	 */
	public AdverseEventReportingPeriod getAdverseEventReportingPeriod() {
		return adverseEventReportingPeriod;
	}
	
	/**
	 * Sets the adverse event reporting period.
	 *
	 * @param adverseEventReportingPeriod the new adverse event reporting period
	 */
	public void setAdverseEventReportingPeriod(
			AdverseEventReportingPeriod adverseEventReportingPeriod) {
		this.adverseEventReportingPeriod = adverseEventReportingPeriod;
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
	 * Checks if is dcp sponsored study.
	 *
	 * @return true, if is dcp sponsored study
	 */
	public boolean isDcpSponsoredStudy() {
		return dcpSponsoredStudy;
	}
	
	/**
	 * Sets the dcp sponsored study.
	 *
	 * @param dcpSponsoredStudy the new dcp sponsored study
	 */
	public void setDcpSponsoredStudy(boolean dcpSponsoredStudy) {
		this.dcpSponsoredStudy = dcpSponsoredStudy;
	}
	
	/**
	 * Checks for actions to do.
	 *
	 * @return true, if successful
	 */
	public boolean hasActionsToDo(){
		boolean hasActions = possibleActions != null && possibleActions.size() > 0;
		if(!hasActions && aeReports != null){
			for(ExpeditedAdverseEventReportDTO aeReportDTO : aeReports){
				hasActions |= aeReportDTO.hasActionsToDo();
			}
		}
		return hasActions;
	}
	
	/**
	 * Checks for report workflow ended.
	 *
	 * @return true, if successful
	 */
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
