/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.ae;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import gov.nih.nci.cabig.caaers.dao.report.ReportDao;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Submitter;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.report.ReportDelivery;
import gov.nih.nci.cabig.caaers.domain.report.ReportVersion;
import gov.nih.nci.cabig.caaers.domain.repository.ReportRepository;

/**
 * @author Rhett Sutphin
 */
public class SubmitExpeditedAdverseEventCommand{
    private String reportId;
    private String from;
    
    private Boolean submissionInprogress = false;
    private ReportVersion lastVersion;
    private List<ReportDelivery> reportDeliveries;
    
    private boolean unidentifiedMode;
    private boolean workflowEnabled;
    protected ReportRepository reportRepository;
    
    private Report report;
    private ReportDao reportDao;

    private Map<String, String> summary;
    
    // //// LOGIC

    public SubmitExpeditedAdverseEventCommand(Report report, boolean unidentifiedMode, ReportDao reportDao, ReportRepository reportRepository) {
        this.report = report;
        this.reportRepository = reportRepository;
        this.reportDao = reportDao;
        this.unidentifiedMode = unidentifiedMode;
        reportDeliveries = new ArrayList<ReportDelivery>();
        summary = report.getAeReport().getSummary(unidentifiedMode);
        summary.put("Report Name", report.getLabel());
    }

    public Map<String,String> getSummary(){
        return summary;
    }
    public void refreshReportDeliveries(Report report){
    	reportDeliveries.clear();
    	reportDeliveries.addAll(reportRepository.findReportDeliveries(report));
    }

    public Report getReport() {
        return report;
    }
    public void setReport(Report report) {
        this.report = report;
    }

    public ExpeditedAdverseEventReport getAeReport(){
        return report.getAeReport();
    }

    public void setAeReport(ExpeditedAdverseEventReport ignore){

    }
    
    public void save(){
    	reportDao.save(report);
    }

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public Boolean isSubmissionInprogress(){
    	return submissionInprogress;
    }
    
    public void setSubmissionInprogress(Boolean submissionInprogress){
    	this.submissionInprogress = submissionInprogress;
    }
    
    public void setLastVersion(ReportVersion lastVersion){
    	this.lastVersion = lastVersion;
    }
    
    public ReportVersion getLastVersion(){
    	return lastVersion;
    }
    public List<ReportDelivery> getReportDeliveries() {
		return reportDeliveries;
	}

    public boolean isUnidentifiedMode() {
        return unidentifiedMode;
    }

    public void setUnidentifiedMode(boolean unidentifiedMode) {
        this.unidentifiedMode = unidentifiedMode;
    }

    public ReportRepository getReportRepository() {
        return reportRepository;
    }

    public void setReportRepository(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    public ReportDao getReportDao() {
        return reportDao;
    }

    public void setReportDao(ReportDao reportDao) {
        this.reportDao = reportDao;
    }


    public boolean getWorkflowEnabled() {
        return workflowEnabled;
    }
    public void setWorkflowEnabled(boolean workflowEnabled) {
        this.workflowEnabled = workflowEnabled;
    }
}
