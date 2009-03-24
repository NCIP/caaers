package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.dao.AdverseEventReportingPeriodDao;
import gov.nih.nci.cabig.caaers.dao.ExpeditedAdverseEventReportDao;
import gov.nih.nci.cabig.caaers.dao.StudyParticipantAssignmentDao;
import gov.nih.nci.cabig.caaers.dao.report.ReportDefinitionDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.ReportStatus;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportTree;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.domain.repository.AdverseEventRoutingAndReviewRepository;
import gov.nih.nci.cabig.caaers.domain.repository.ReportRepository;
import gov.nih.nci.cabig.caaers.service.EvaluationService;
import gov.nih.nci.cabig.caaers.web.RenderDecisionManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Rhett Sutphin
 * @author Biju Joseph
 */
public class EditExpeditedAdverseEventCommand extends AbstractExpeditedAdverseEventInputCommand {
	
    private String currentItem; //currentItem - corresponds to the item that we are working on now (eg: conmed, priorTherapy). 
    private String task; // will tell the action we perform on the current item.
   
    private EvaluationService evaluationService;
   
    
    private List<ReportDefinition> newlySelectedSponsorReports = new ArrayList<ReportDefinition>();
    private List<ReportDefinition> otherSelectedReports = new ArrayList<ReportDefinition>();
    List<ReportDefinition> newlySelectedDefs = new ArrayList<ReportDefinition>();
    
    List<ReportDefinition> reportDefinitionListForCreation = new ArrayList<ReportDefinition>();
    List<Report> reportListForAmendment = new ArrayList<Report>();
    List<Report> reportListForWithdrawal = new ArrayList<Report>();
    
    Map<ReportDefinition, ReportStatus> existingReportMap = new HashMap<ReportDefinition, ReportStatus>();
    
    // //// LOGIC

    public EditExpeditedAdverseEventCommand(ExpeditedAdverseEventReportDao reportDao){
    	this.reportDao = reportDao;
    }
    
    public EditExpeditedAdverseEventCommand(ExpeditedAdverseEventReportDao expeditedAeReportDao,
            ReportDefinitionDao reportDefinitionDao,
            StudyParticipantAssignmentDao assignmentDao,
            AdverseEventReportingPeriodDao reportingPeriodDao,
            ExpeditedReportTree expeditedReportTree, 
            RenderDecisionManager renderDecisionManager, ReportRepository reportRepository,
            AdverseEventRoutingAndReviewRepository adverseEventRoutingAndReviewRepository,
            EvaluationService evaluationService) {
    	super(expeditedAeReportDao, reportDefinitionDao, reportingPeriodDao, expeditedReportTree , renderDecisionManager, reportRepository, assignmentDao, adverseEventRoutingAndReviewRepository);
    		this.evaluationService = evaluationService;
    }

    @Override
    public void save() {
        reportDao.save(getAeReport());
    }
    
    public void saveReportingPeriod() {
    	reportingPeriodDao.save(aeReport.getReportingPeriod());
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
     * It creates a map with the reportDefinitons of the "command.aeReport.reports" as the key
     * and "command.aeReport.report.lastVersion.reportStatus" as the value.
     * It is needed as helper data structure to implement the create/amend-report logic.
     *
     * @param command
     * @return
     */
    public void initializeExistingReportMap() {
        for (Report report : getAeReport().getReports()) {
            if (!existingReportMap.containsKey(report.getReportDefinition()))
                existingReportMap.put(report.getReportDefinition(), report.getLastVersion().getReportStatus());
        }
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
    
    public void populateCreationAndAmendmentList(){
    	// For Sponsor/amendable newlySelectedReport take the following action
    	//        - create New report if it doesnt exist
    	//        - amend if it exists (also instantiate Notifications)

    	// For other reports take the following action
    	//         - create New report if it doesnt exist
    	//         - amend if it exists and has status = SUBMITTED/WITHDRAWN
    	//         - ignore if it exists and the status = PENDING.
    	for (ReportDefinition reportDefinition : getNewlySelectedDefs()) {
    		if (!existingReportMap.containsKey(reportDefinition))
    			reportDefinitionListForCreation.add(reportDefinition);
    		else {
    			if (existingReportMap.get(reportDefinition).equals(ReportStatus.COMPLETED) ||
    					existingReportMap.get(reportDefinition).equals(ReportStatus.WITHDRAWN))
    				for (Report report : getAeReport().getReports()) {
    					if (report.getReportDefinition().equals(reportDefinition))
    						reportListForAmendment.add(report);
    				}
    		}
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
    		
    		if(reportDefinition.getExpectedDueDate() == null || earliestPendingSponsorReport.getDueOn() == null) continue;
    		
    		if(reportDefinition.getExpectedDueDate().compareTo(earliestPendingSponsorReport.getDueOn()) < 0){
    			isSelectedReportEarlier = true;
    			break;
    		}
    	}
    		
    	return isSelectedReportEarlier;
    }

    
    /**
     * This method amends the reports in the list reportListForAmendment.
     */
    public void amendReports(){
    	amendReports(reportListForAmendment);
    }
    
    public void amendReports(List<Report> amendReportList){
    	// Set useDefaultVersion to false so that for the first report the reportVersionId is correctly incremented by 1.
    	Boolean useDefaultVersion = false;
    	for(Report report: amendReportList){
    		reportRepository.amendReport(report, useDefaultVersion);
    		// Set useDefaultVersion to true so that the reportVersionId is retained for all the reports 
    		// and just incremented for the 1st one in the list.
    		useDefaultVersion = true;
    	}
    	
    	if(this.getWorkflowEnabled() && this.isAssociatedToWorkflow()){
    		if(amendReportList.size() > 0)
    			enactWorkflow(amendReportList.get(0).getAeReport());
    	}
    }

    /**
     * This method sets the MandatorySections and refreshes the ManadatoryProperties of the command.
     */
    public void refreshMandatorySectionsAndProperties() {
    	// find the new mandatory sections
        setMandatorySections(evaluationService.mandatorySections(getAeReport()));

        // refresh the mandatory fields
        refreshMandatoryProperties();
    }
    
    
    /**
     * This method will spawn the workflow for newly created reports
     * @param newlyCreatedReports
     */
    public void enactWorkflow(ExpeditedAdverseEventReport aeReport){
    	adverseEventRoutingAndReviewRepository.enactReportWorkflow(aeReport);
    	
    }
    
    /**
     * This method creates the reports in the list - reportDefinitionListForCreation.
     * @param - boolean (useDefaultVersion)
     */
    public void createReports(Boolean useDefaultVersion){
    	if(reportDefinitionListForCreation != null && reportDefinitionListForCreation.size() > 0){
    		List<Report> newlyCreatedReports = evaluationService.addOptionalReports(getAeReport(), reportDefinitionListForCreation, useDefaultVersion);
    	}
    }
    
    /**
     * This method withdraws the reports in the list reportListForWithdrawal.
     */
    public void withdrawReports(){
    	for(Report report: reportListForWithdrawal){
    		reportRepository.replaceReport(report);
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
	
    public List<ReportDefinition> getReportDefinitionListForCreation(){
    	return reportDefinitionListForCreation;
    }
    
    public void setReportDefinitionListForCreation(List<ReportDefinition> reportDefinitionListForCreation){
    	this.reportDefinitionListForCreation = reportDefinitionListForCreation;
    }
    
    public List<Report> getReportListForAmendment(){
    	return reportListForAmendment;
    }
    
    public void setReportListForAmendment(List<Report> reportListForAmendment){
    	this.reportListForAmendment = reportListForAmendment;
    }
    
    public List<Report> getReportListForWithdrawal(){
    	return reportListForWithdrawal;
    }
    
    public void setReportListForWithdrawal(List<Report> reportListForWithdralwal){
    	this.reportListForWithdrawal = reportListForWithdralwal;
    }
    
    public Map<ReportDefinition, ReportStatus> getExistingReportMap(){
    	return existingReportMap;
    }
    
    public void setExistingReportMap(Map<ReportDefinition, ReportStatus> existingReportMap){
    	this.existingReportMap = existingReportMap;
    }

    public EvaluationService getEvaluationService(){
    	return evaluationService;
    }
}
