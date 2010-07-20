package gov.nih.nci.cabig.caaers.web.ae;

import java.util.ArrayList;
import java.util.List;

import gov.nih.nci.cabig.caaers.dao.AdverseEventReportingPeriodDao;
import gov.nih.nci.cabig.caaers.dao.ExpeditedAdverseEventReportDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.StudyParticipantAssignmentDao;
import gov.nih.nci.cabig.caaers.dao.report.ReportDefinitionDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.ReportStatus;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportTree;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.report.ReportDelivery;
import gov.nih.nci.cabig.caaers.domain.report.ReportVersion;
import gov.nih.nci.cabig.caaers.domain.repository.AdverseEventRoutingAndReviewRepository;
import gov.nih.nci.cabig.caaers.domain.repository.ReportRepository;
import gov.nih.nci.cabig.caaers.web.RenderDecisionManager;

/**
 * @author Rhett Sutphin
 */
public class SubmitExpeditedAdverseEventCommand extends EditExpeditedAdverseEventCommand {
    // Used in SubmitReport
    private String reportIndex;

    private String reportId;

    private String from;
    
    private Boolean reportSubmitted = false;
    
    private ReportVersion lastVersion;
    
    private List<ReportDelivery> reportDeliveries;
    
    // //// LOGIC

    public SubmitExpeditedAdverseEventCommand(ExpeditedAdverseEventReportDao expeditedAeReportDao, StudyDao studyDao,
                    ReportDefinitionDao reportDefinitionDao,
                    StudyParticipantAssignmentDao assignmentDao,
                    AdverseEventReportingPeriodDao reportingPeriodDao,
                    ExpeditedReportTree expeditedReportTree, RenderDecisionManager renderDecisionManager, ReportRepository reportRepository,
                    AdverseEventRoutingAndReviewRepository adverseEventRoutingAndReviewRepository) {
        super(expeditedAeReportDao,studyDao, reportDefinitionDao, assignmentDao, reportingPeriodDao, expeditedReportTree, renderDecisionManager, reportRepository, adverseEventRoutingAndReviewRepository, null);
        reportDeliveries = new ArrayList<ReportDelivery>();
    }
    
    public void refreshReportDeliveries(Report report){
    	reportDeliveries.clear();
    	reportDeliveries.addAll(reportRepository.findReportDeliveries(report));
    }

    @Override
    public void setAeReport(ExpeditedAdverseEventReport aeReport) {
        super.setAeReport(aeReport);
        if(aeReport != null){
        	getReportIndex();
        	aeReport.getReports().get(((int) (Integer.parseInt(reportIndex)))).getLastVersion().addSubmitter();
        }
    }
    
    public Report findReportToSubmit(){
    	Report report = null;
    	if(reportId != null){
    		for(Report r : getAeReport().getReports()){
        		if(r.getId().toString().equals(reportId)) report = r;
        	}
    	}
    	
    	return report;
    }

    public String getReportIndex() {
    	reportIndex = "";
        for (int i = 0; i < getAeReport().getReports().size(); i++) {
            if (getAeReport().getReports().get(i).getId().toString().equals(reportId)) {
                this.reportIndex = String.valueOf(i);
            }
        }
        return reportIndex;
    }
    
    public void refreshAeReport(Integer aeReportId){
    	assignmentDao.reassociate(getAssignment());
    	//reset the adverse event report in the command
    	for(ExpeditedAdverseEventReport aeReport: getAssignment().getAeReports()){
    		if(aeReport.getId().equals(aeReportId)){
    			setAeReport(aeReport);
    			break;
    		}
    	}
    }
    
    @Override
    public void save(){
    	reportDao.modifyOrSaveReviewStatusAndComments(aeReport);
    }

    public void setReportIndex(String reportIndex) {
        this.reportIndex = reportIndex;
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

    public Boolean getReportSubmitted(){
    	return reportSubmitted;
    }
    
    public void setReportSubmitted(Boolean reportSubmitted){
    	this.reportSubmitted = reportSubmitted;
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
}
