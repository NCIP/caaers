package gov.nih.nci.cabig.caaers.domain.dto;

import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.ReportStatus;
import gov.nih.nci.cabig.caaers.domain.ReviewStatus;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.report.Report;

import java.util.ArrayList;
import java.util.List;

 
/**
 * The Class ExpeditedAdverseEventReportDTO.
 *
 * @author Biju Joseph
 */
public class ExpeditedAdverseEventReportDTO {
	
	/** The id. */
	private Integer id;
	
	/** The no of ae. */
	private Integer noOfAe;
	
	/** The name. */
	private String name;
	
	/** The status. */
	private ReportStatus status;
	
	/** The report version id. */
	private String reportVersionId;
	
	/** The study. */
	private Study study;
	
	/** The participant. */
	private Participant participant;
	
	/** The ae report. */
	private ExpeditedAdverseEventReport aeReport;
	
	/** The reports. */
	private List<ReportDTO> reports = new ArrayList<ReportDTO>();
	
	
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
	 * Gets the no of ae.
	 *
	 * @return the no of ae
	 */
	public Integer getNoOfAe() {
		return noOfAe;
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
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
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
	 * Gets the ae report.
	 *
	 * @return the ae report
	 */
	public ExpeditedAdverseEventReport getAeReport() {
		return aeReport;
	}
	
	/**
	 * Sets the ae report.
	 *
	 * @param aeReport the new ae report
	 */
	public void setAeReport(ExpeditedAdverseEventReport aeReport) {
		this.aeReport = aeReport;
	}
	
	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public ReportStatus getStatus() {
		return status;
	}
	
	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(ReportStatus status) {
		this.status = status;
	}
	
	/**
	 * Gets the report version id.
	 *
	 * @return the report version id
	 */
	public String getReportVersionId() {
		return reportVersionId;
	}
	
	/**
	 * Sets the report version id.
	 *
	 * @param reportVersionId the new report version id
	 */
	public void setReportVersionId(String reportVersionId) {
		this.reportVersionId = reportVersionId;
	}
	
	/**
	 * Adds the report dto.
	 *
	 * @param reportDTO the report dto
	 */
	public void addReportDTO(ReportDTO reportDTO){
		reports.add(reportDTO);
	}
	
	/**
	 * Gets the reports.
	 *
	 * @return the reports
	 */
	public List<ReportDTO> getReports() {
		return reports;
	}
	
	/**
	 * Sets the reports.
	 *
	 * @param reports the new reports
	 */
	public void setReports(List<ReportDTO> reports) {
		this.reports = reports;
	}
	
	/**
	 * Checks for actions to do.
	 *
	 * @return true, if successful
	 */
	public boolean hasActionsToDo(){
		boolean hasActionsToDo = false;
		if(reports != null && !reports.isEmpty())
			for(ReportDTO reportDTO: reports){
				if(reportDTO.hasActionsToDo())
					hasActionsToDo = true;
			}
		return hasActionsToDo;
	}
	
	/**
	 * Checks for workflow ended.
	 *
	 * @return true, if successful
	 */
	public boolean hasWorkflowEnded(){
		boolean hasWorkflowEnded = false;
		if(reports != null && !reports.isEmpty())
			for(ReportDTO reportDTO: reports){
				if(reportDTO.hasWorkflowEnded())
					hasWorkflowEnded = true;
			}
		return hasWorkflowEnded;
	}
	
}
