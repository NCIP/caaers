package gov.nih.nci.cabig.caaers.web.ae;

import java.util.Date;

import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.dto.ReportDefinitionWrapper;
import gov.nih.nci.cabig.caaers.domain.dto.ReportDefinitionWrapper.ActionType;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;

public class ReportTableRow {
	
	private ExpeditedAdverseEventReport aeReport;
	private Integer aeReportId;
	private ReportDefinition reportDefinition;
	
	private String status; //current
	private String grpStatus; //status when another report of same group is slected
	private String otherStatus; //status when a report from different group is selected
	
	private String due;
	private String grpDue;
	private String otherDue;
	
	private ActionType action;
	private ActionType grpAction;
	private ActionType otherAction;
	
	private boolean preSelected;
	
	private Date baseDate;
	
	
	public ExpeditedAdverseEventReport getAeReport() {
		return aeReport;
	}
	public void setAeReport(ExpeditedAdverseEventReport aeReport) {
		this.aeReport = aeReport;
	}
	public Integer getAeReportId() {
		return aeReportId;
	}
	public void setAeReportId(Integer aeReportId) {
		this.aeReportId = aeReportId;
	}
	public ReportDefinition getReportDefinition() {
		return reportDefinition;
	}
	public void setReportDefinition(ReportDefinition reportDefinition) {
		this.reportDefinition = reportDefinition;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDue() {
		return due;
	}
	public void setDue(String due) {
		this.due = due;
	}
	public ActionType getAction() {
		return action;
	}
	public void setAction(ActionType action) {
		this.action = action;
	}
	
	
	public void setOtherStatus(String otherStatus) {
		this.otherStatus = otherStatus;
	}
	public String getOtherStatus() {
		return otherStatus;
	}
	
	public void setOtherAction(ActionType otherAction) {
		this.otherAction = otherAction;
	}
	public ActionType getOtherAction() {
		return otherAction;
	}
	
	public String getGrpStatus() {
		return grpStatus;
	}
	public void setGrpStatus(String grpStatus) {
		this.grpStatus = grpStatus;
	}
	public String getGrpDue() {
		return grpDue;
	}
	public void setGrpDue(String grpDue) {
		this.grpDue = grpDue;
	}
	public String getOtherDue() {
		return otherDue;
	}
	public void setOtherDue(String otherDue) {
		this.otherDue = otherDue;
	}
	public ActionType getGrpAction() {
		return grpAction;
	}
	public void setGrpAction(ActionType grpAction) {
		this.grpAction = grpAction;
	}
	public boolean isPreSelected() {
		return preSelected;
	}
	public void setPreSelected(boolean preSelected) {
		this.preSelected = preSelected;
	}
	public Date getBaseDate() {
		return baseDate;
	}
	public void setBaseDate(Date baseDate) {
		this.baseDate = baseDate;
	}
	
	public String getGroup(){
		return "grp-" + reportDefinition.getOrganization().getId() + "-"+reportDefinition.getReportType().getId();
	}
	
	public static ReportTableRow createReportTableRow(ReportDefinition rd, Date baseDate, ActionType action){
		ReportTableRow row = new ReportTableRow();
		row.setReportDefinition(rd);
		row.setAction(action);
		row.setBaseDate(baseDate);
		row.setDue(baseDate != null ?  rd.getExpectedDisplayDueDate(baseDate) :  rd.getExpectedDisplayDueDate());
		return row;
	}
	
}
