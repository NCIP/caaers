package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.dao.AdverseEventReportingPeriodDao;
import gov.nih.nci.cabig.caaers.dao.ExpeditedAdverseEventReportDao;
import gov.nih.nci.cabig.caaers.dao.StudyParticipantAssignmentDao;
import gov.nih.nci.cabig.caaers.dao.report.ReportDefinitionDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportTree;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.domain.repository.ReportRepository;
import gov.nih.nci.cabig.caaers.web.RenderDecisionManager;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Rhett Sutphin
 * @author Biju Joseph
 */
public class EditExpeditedAdverseEventCommand extends AbstractExpeditedAdverseEventInputCommand {
	
    private String currentItem; //currentItem - corresponds to the item that we are working on now (eg: conmed, priorTherapy). 
    private String task; // will tell the action we perform on the current item.
   
    
   
    
    private List<ReportDefinition> newlySelectedSponsorReports = new ArrayList<ReportDefinition>();
    private List<ReportDefinition> otherSelectedReports = new ArrayList<ReportDefinition>();
    List<ReportDefinition> newlySelectedDefs = new ArrayList<ReportDefinition>();
    

    // //// LOGIC


    public EditExpeditedAdverseEventCommand(ExpeditedAdverseEventReportDao expeditedAeReportDao,
            ReportDefinitionDao reportDefinitionDao,
            StudyParticipantAssignmentDao assignmentDao,
            AdverseEventReportingPeriodDao reportingPeriodDao,
            ExpeditedReportTree expeditedReportTree, 
            RenderDecisionManager renderDecisionManager, ReportRepository reportRepository) {
    	super(expeditedAeReportDao, reportDefinitionDao, reportingPeriodDao, expeditedReportTree , renderDecisionManager, reportRepository, assignmentDao);
    }

    @Override
    public void save() {
        reportDao.save(getAeReport());
    }
    
    public void saveReportingPeriod() {
    	// TODO Auto-generated method stub
    	throw new UnsupportedOperationException("Should not call reporting period save from edit expedited flow");
    }
    
    @Override
    public void reassociate() {
        super.reassociate();
        assignmentDao.reassociate(getAssignment());
    }
    
    @Override
    public void flush() {
    	reportDao.flush();
    }
    
   
    
    /**
     * This method classifies the newly selected reportDefinitions into 2 lists.
     * list1  - newlySelectedSponsorReports ( that has all the report definitions defined for the sponsor and are amendable and expedited)
     * list2 - otherSelectedReports (remaining reportDefinitions)
     * @param newlySelectedDefs
     * @param command
     */
    public void classifyNewlySelectedReportsDefinitons(){
    	String nciInstituteCode = getAeReport().getStudy().getPrimaryFundingSponsorOrganization().getNciInstituteCode();
    	for(ReportDefinition reportDefinition: newlySelectedDefs){
    		if(reportDefinition.getOrganization().getNciInstituteCode().equals(nciInstituteCode) && reportDefinition.getAmendable() && reportDefinition.getExpedited())
    			newlySelectedSponsorReports.add(reportDefinition);
    		else
    			otherSelectedReports.add(reportDefinition);
    	}
    }
    
    /**
     * This method returns a boolean. Its true if the earliest sponsor/amendable report selected is expected to be scheduled before the 
     * earliest pending sponsor-amendable-expedited report and returns false otherwise.
     * @param command
     * @return
     */
    public Boolean isNewlySelectedReportEarlier(){
    	Boolean isSelectedReportEarlier = false;
    	Report earliestPendingSponsorReport = getAeReport().getEarliestPendingSponsorReport();
    	for(ReportDefinition reportDefinition: newlySelectedSponsorReports){
    		if(reportDefinition.getExpectedDueDate().compareTo(earliestPendingSponsorReport.getDueOn()) < 0){
    			isSelectedReportEarlier = true;
    			break;
    		}
    	}
    		
    	return isSelectedReportEarlier;
    }

    
    /**
     * This method amends the reports in the list passed as a parameter to this method.
     */
    public void amendReports(List<Report> amendReportList){
    	// Set useDefaultVersion to false so that for the first report the reportVersionId is correctly incremented by 1.
    	Boolean useDefaultVersion = false;
    	for(Report report: amendReportList){
    		reportRepository.amendReport(report, useDefaultVersion);
    		// Set useDefaultVersion to true so that the reportVersionId is retained for all the reports 
    		// and just incremented for the 1st one in the list.
    		useDefaultVersion = true;
    	}
    }
    
    /**
     * This method withdraws the reports in the list passed as a parameter to this method.
     */
    public void withdrawReports(List<Report> withdrawReportList){
    	for(Report report: withdrawReportList){
    		reportRepository.deleteReport(report);
    	}
    }
	/**
	 * This method will check if the study selected is a DCP sponsored study and is AdEERS submittable.
	 * @return
	 */
	public boolean isDCPNonAdeersStudy(){
		if(getStudy() == null) return false;
		return (!getStudy().getAdeersReporting()) && getStudy().getPrimaryFundingSponsorOrganization().getNciInstituteCode().equals("DCP");
	}
	
	
	///BEAN METHODS

    @Override
    public StudyParticipantAssignment getAssignment() {
        return getAeReport().getAssignment();
    }

    @Override
    public Participant getParticipant() {
        return getAssignment().getParticipant();
    }

    @Override
    public Study getStudy() {
        return getAssignment().getStudySite().getStudy();
    }
    
    /**
     * Will tell which subitem that we are dealing with. 
     * @return
     */
    public String getCurrentItem() {
		return currentItem;
	}
    /**
     * Which tell which subitem that we are dealing with. 
     * @param currentItem
     */
    public void setCurrentItem(String currentItem) {
		this.currentItem = currentItem;
	}
    
    public String getTask() {
		return task;
	}
    public void setTask(String task) {
		this.task = task;
	}
    
   
	public void setNewlySelectedSponsorReports(
			ArrayList<ReportDefinition> newlySelectedSponsorReports) {
		this.newlySelectedSponsorReports = newlySelectedSponsorReports;
	}
	
	public List<ReportDefinition> getNewlySelectedSponsorReports() {
		return newlySelectedSponsorReports;
	}
	
	public void setOtherSelectedReports(
			ArrayList<ReportDefinition> otherSelectedReports) {
		this.otherSelectedReports = otherSelectedReports;
	}
	
	public List<ReportDefinition> getOtherSelectedReports() {
		return otherSelectedReports;
	}
	
	public List<ReportDefinition> getNewlySelectedDefs() {
		return newlySelectedDefs;
	}
	
	public void setNewlySelectedDefs(List<ReportDefinition> newlySelectedDefs) {
		this.newlySelectedDefs = newlySelectedDefs;
	}
	
	
	public AdverseEventReportingPeriod getAdverseEventReportingPeriod(){
		if(getAeReport() != null)
			return getAeReport().getReportingPeriod();
		return null;
	}
	
}
