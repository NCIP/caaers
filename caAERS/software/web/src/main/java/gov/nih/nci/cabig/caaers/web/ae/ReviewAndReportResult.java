package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.BooleanUtils;
/**
 * This object, will store the review results.
 * @author Biju Joseph
 *
 */
public class ReviewAndReportResult {
	Integer aeReportId;
	
	//reportdefinition-id
	List<ReportDefinition> createList = new ArrayList<ReportDefinition>();
	List<ReportDefinition> editList = new ArrayList<ReportDefinition>();
	List<ReportDefinition> withdrawList = new ArrayList<ReportDefinition>();
	List<ReportDefinition> amendList = new ArrayList<ReportDefinition>();
	
	//ae-id
	List<Integer> aeList = new ArrayList<Integer>();
	List<Integer> unwantedAEList = new ArrayList<Integer>();
	Integer primaryAdverseEventId;
	
	Integer reportingPeriodId;
	
	
	//report-id
	List<Report> reportsToAmmendList = new ArrayList<Report>();
	List<Report> reportsToWithdraw = new ArrayList<Report>();
	List<Report> reportsToUnAmendList = new ArrayList<Report>();
	
	
	//reportdefinition-id , baseDate
	Map<Integer, Date> baseDateMap;
	
	Map<Integer, Boolean> manualSelectionIndicatorMap = new HashMap<Integer, Boolean>();
	
	public List<ReportDefinition> getCreateList() {
		return createList;
	}
	public void setCreateList(List<ReportDefinition> createList) {
		this.createList = createList;
	}
	public List<ReportDefinition> getEditList() {
		return editList;
	}
	public void setEditList(List<ReportDefinition> editList) {
		this.editList = editList;
	}
	public List<ReportDefinition> getWithdrawList() {
		return withdrawList;
	}
	public void setWithdrawList(List<ReportDefinition> withdrawList) {
		this.withdrawList = withdrawList;
	}
	public List<ReportDefinition> getAmendList() {
		return amendList;
	}
	public void setAmendList(List<ReportDefinition> amendList) {
		this.amendList = amendList;
	}
	public List<Integer> getAeList() {
		return aeList;
	}
	public void setAeList(List<Integer> aeList) {
		this.aeList = aeList;
	}
	public void setAeReportId(Integer aeReportId) {
		this.aeReportId = aeReportId;
	}
	public Integer getAeReportId() {
		return aeReportId;
	}
	
	public Map<Integer, Date> getBaseDateMap() {
		return baseDateMap;
	}
	public void setBaseDateMap(Map<Integer, Date> baseDateMap) {
		this.baseDateMap = baseDateMap;
	}
	
	public List<Report> getReportsToAmmendList() {
		return reportsToAmmendList;
	}
	public void setReportsToAmmendList(List<Report> reportsToAmmendList) {
		this.reportsToAmmendList = reportsToAmmendList;
	}
	public List<Report> getReportsToWithdraw() {
		return reportsToWithdraw;
	}
	public void setReportsToWithdraw(List<Report> reportsToWithdraw) {
		this.reportsToWithdraw = reportsToWithdraw;
	}
	
	public List<Report> getReportsToUnAmendList() {
		return reportsToUnAmendList;
	}
	public void setReportsToUnAmendList(List<Report> reportsToUnAmendList) {
		this.reportsToUnAmendList = reportsToUnAmendList;
	}
	
	public List<Integer> getUnwantedAEList() {
		return unwantedAEList;
	}
	public void setUnwantedAEList(List<Integer> unwantedAEList) {
		this.unwantedAEList = unwantedAEList;
	}
	
	public Integer getPrimaryAdverseEventId() {
		return primaryAdverseEventId;
	}
	public void setPrimaryAdverseEventId(Integer primaryAdverseEventId) {
		this.primaryAdverseEventId = primaryAdverseEventId;
	}
	
	public void setReportingPeriodId(Integer reportingPeriodId) {
		this.reportingPeriodId = reportingPeriodId;
	}
	public Integer getReportingPeriodId() {
		return reportingPeriodId;
	}
	
	public Map<Integer, Boolean> getManualSelectionIndicatorMap() {
		return manualSelectionIndicatorMap;
	}
	
	public void setManualSelectionIndicatorMap(Map<Integer, Boolean> manualSelectionIndicatorMap) {
		this.manualSelectionIndicatorMap = manualSelectionIndicatorMap;
	}
	
	public boolean isManuallySelected(ReportDefinition rd){
		Boolean b = manualSelectionIndicatorMap.get(rd.getId());
		if(b == null) return false;
		return b.booleanValue();
	}
	
	public boolean isOnlyActionWithdraw(){
		return amendList.isEmpty() && editList.isEmpty() && createList.isEmpty() && (!withdrawList.isEmpty());
	}
	
	public void updateBaseDateOnCreateList(List<ReportDefinition> newlySelectedReportDefinitions){
		if(baseDateMap == null) return;
		for(ReportDefinition rd : newlySelectedReportDefinitions){
			rd.setBaseDate(baseDateMap.get(rd.getId()));
			rd.setManuallySelected(BooleanUtils.isTrue(manualSelectionIndicatorMap.get(rd.getId())));
		}
	}
	
}
