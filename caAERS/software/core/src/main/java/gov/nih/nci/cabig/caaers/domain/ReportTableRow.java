/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.domain.dto.ReportDefinitionWrapper.ActionType;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;

import java.util.Date;

/**
 * This is a ValueObject used for UI rendering easynes.
 * @author Biju Joseph
 */
public class ReportTableRow {
	
	private ExpeditedAdverseEventReport aeReport;
	private Integer aeReportId;
	private ReportDefinition reportDefinition;
	
	private String status; //current
	private String grpStatus; //status when another report of same group is selected
	private String otherStatus; //status when a report from different group is selected
	
	private String due;
	private String grpDue;
	private String otherDue;
	
	private ActionType action;
	private ActionType grpAction;
	private ActionType otherAction;
	
	private boolean preSelected;
	
	private Date baseDate;

    private boolean stringent;
    private boolean includeNonSeriousAes;
	
	
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
		return "grp-" + reportDefinition.getOrganization().getId() + "-"+reportDefinition.getGroup().getId();
	}

    public boolean isStringent(){
        return stringent;
    }
    public void setStringent(boolean stringent){
        this.stringent = stringent;
    }

    public boolean isIncludeNonSeriousAes() {
        return includeNonSeriousAes;
    }

    public void setIncludeNonSeriousAes(boolean includeNonSeriousAes) {
        this.includeNonSeriousAes = includeNonSeriousAes;
    }

    public static ReportTableRow createReportTableRow(ReportDefinition rd, Date baseDate, ActionType action){
		ReportTableRow row = new ReportTableRow();
		row.setReportDefinition(rd);
		row.setAction(action);
		row.setBaseDate(baseDate);
        row.setStringent(true);
		row.setDue(baseDate != null ?  rd.getExpectedDisplayDueDate(baseDate) :  rd.getExpectedDisplayDueDate());
        row.setIncludeNonSeriousAes(rd.getIncludeNonSeriousAes());
		return row;
	}
	
}
