package gov.nih.nci.cabig.caaers.domain.dto;

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
}
