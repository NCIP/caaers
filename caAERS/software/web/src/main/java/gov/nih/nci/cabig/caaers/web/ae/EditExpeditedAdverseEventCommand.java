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
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Rhett Sutphin
 * @author Biju Joseph
 */
public class EditExpeditedAdverseEventCommand extends AbstractExpeditedAdverseEventInputCommand {
	
    private String currentItem; //currentItem - corresponds to the item that we are working on now (eg: conmed, priorTherapy). 
    private String task; // will tell the action we perform on the current item.

    
    //BJ----------------- remove variables
    private List<ReportDefinition> newlySelectedSponsorReports = new ArrayList<ReportDefinition>();
    private List<ReportDefinition> otherSelectedReports = new ArrayList<ReportDefinition>();
    
    List<ReportDefinition> reportDefinitionListForCreation = new ArrayList<ReportDefinition>();
    List<Report> reportListForWithdrawal = new ArrayList<Report>();
    Map<ReportDefinition, Report> amendedReportsMap = new HashMap<ReportDefinition, Report>();
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

    
    public void saveReportingPeriod() {
    	reportingPeriodDao.save(aeReport.getReportingPeriod());
    }
    
    @Override
    public void reassociate() {
        super.reassociate();
        assignmentDao.reassociate(getAssignment());
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
    	throw new UnsupportedOperationException("to be removed");
//    	String nciInstituteCode = getAeReport().getStudy().getPrimaryFundingSponsorOrganization().getNciInstituteCode();
//    	for(ReportDefinition reportDefinition: newlySelectedReportDefinitions){
//    		if(reportDefinition.getOrganization().getNciInstituteCode().equals(nciInstituteCode) && reportDefinition.getAmendable() && reportDefinition.getExpedited())
//    			newlySelectedSponsorReports.add(reportDefinition);
//    		else
//    			otherSelectedReports.add(reportDefinition);
//    	}
    }
    
    /**
     * This method returns the type of the command object (aeReport)
     */
    public String getCommandType(){
    	return "aeReport";
    }
    
    
    /**
     * This method will populate the reports to amend/create/replace. 
     * 
     * Note :- This method works on the base assumption that there will only be one amendable sponsor report completed at given point in time.
     */
    public void populateCreationAmendmentAndWithdrawlList(){
    	
    	throw new UnsupportedOperationException("to be removed");
//    	BJ: dont delete this comment.
//    	// For Sponsor/amendable newlySelectedReport take the following action
//    	//        - create New report if it doesnt exist
//    	//        - amend if it exists (also instantiate Notifications)
//
//    	// For other reports take the following action
//    	//         - create New report if it doesnt exist
//    	//         - amend if it exists and has status = SUBMITTED/WITHDRAWN
//    	//         - ignore if it exists and the status = PENDING.
//    	for (ReportDefinition reportDefinition : getNewlySelectedDefs()) {
//    		if (!existingReportMap.containsKey(reportDefinition))
//    			reportDefinitionListForCreation.add(reportDefinition);
//    		else {
//    			if (existingReportMap.get(reportDefinition).equals(ReportStatus.COMPLETED) ||
//    					existingReportMap.get(reportDefinition).equals(ReportStatus.WITHDRAWN))
//    				for (Report report : getAeReport().getReports()) {
//    					if (report.getReportDefinition().equals(reportDefinition))
//    						reportListForAmendment.add(report);
//    				}
//    		}
//    	}
    	
    	/*
    	======== BJ remove ========
    	Set<Report> masterSetOfReportToAmmend = new HashSet<Report>();//to prevent double amending reports.
    	if(newlySelectedReportDefinitions != null && !newlySelectedReportDefinitions.isEmpty()){
    		
    		//find the reports to be amended.
    		for(ReportDefinition reportDef : newlySelectedReportDefinitions){
    			List<Report> toAmmendReports = new ArrayList<Report>();
    			
    			if(reportDef.getAmendable()){
    				String nciInstituteCode = reportDef.getOrganization().getNciInstituteCode();
    				List<Report> completedReportsOfAnOrg = getAeReport().findCompletedAmendableReports(nciInstituteCode);
    				//this is to filter double amending when multiple report defs of the same org is present in newly selected report defs
    				for(Report report : completedReportsOfAnOrg){
    					if(masterSetOfReportToAmmend.add(report)){
    						toAmmendReports.add(report);
    					}
    				}
    			}
    			
    			
    			
    			if(toAmmendReports.isEmpty() && !aeReport.isAnActiveReportPresent(reportDef)){
    			
    				//there are no reports to amend, so newly selected one is to be added new. So add this to create list.
    				reportDefinitionListForCreation.add(reportDef);
    			
    			}else{
    				
    				//there are a reports to amend. The first one should be amended. Others should be replaced. 
    				//for time being this is fine, as there won't be more that one sponsor report to amend at a given point in time. 
    				amendedReportsMap.put(reportDef, toAmmendReports.get(0));
    				
    				//add the rest, to the withdraw list. (Note:- Ideally they should also be AMENDED, but its okay for time being).
    				toAmmendReports.remove(0);
    				reportListForWithdrawal.addAll(toAmmendReports);
    			}
    		}
    	}
    	*/
    }
    
    
    
    /**
     * This method returns a boolean. Its true if the earliest sponsor/amendable report selected is expected to be scheduled before the 
     * earliest pending sponsor-amendable-expedited report and returns false otherwise.
     * @param command
     * @return
     */
    public Boolean isNewlySelectedSponsorReportEarlier(){
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
     * This method will amend the reports
     */
    public void amendReports(List<Report> toAmendList){
    	for(Report report : toAmendList){
    		reportRepository.amendReport(report);
    	}
    	
  /*  	BJ=========================== to be removed
    	//to capture the AE reports on which workflow should be enacted.
    	Map<Integer, ExpeditedAdverseEventReport> aeReportMap = new HashMap<Integer, ExpeditedAdverseEventReport>();
    	
    	// Set useDefaultVersion to false so that for the first report the reportVersionId is correctly incremented by 1.
    	Boolean useDefaultVersion = false;
    	for(ReportDefinition repDef : amendedReportsMap.keySet()){
    		Report report = amendedReportsMap.get(repDef);
    		reportRepository.createAndAmendReport(repDef, report, useDefaultVersion);
    		// Set useDefaultVersion to true so that the reportVersionId is retained for all the reports 
    		// and just incremented for the 1st one in the list.
    		useDefaultVersion = true;
    		
    		//add the aeReport to the map, so that next we could enact workflow
    		aeReportMap.put(report.getAeReport().getId(), report.getAeReport());
    	}
    
    	//logic to restart the workflow on amending.
    	if(this.getWorkflowEnabled() && this.isAssociatedToWorkflow()){
    		if(!aeReportMap.isEmpty()){
    			for(ExpeditedAdverseEventReport aeReport : aeReportMap.values()){
    				enactWorkflow(aeReport);
    			}
    		}
    			
    	}*/
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
     * This method creates the reports based on the reports mentioned in newlySelectedDef list.
     */
    public void createReports(List<ReportDefinition> toCreateList, Map<Integer, Date> baseDateMap, Map<Integer, Boolean> manualSelectionIndicatorMap){
    	for(ReportDefinition rd : toCreateList){
    		Report report = reportRepository.createReport(rd, aeReport, baseDateMap.get(rd.getId()));
    		report.setManuallySelected(manualSelectionIndicatorMap.get(rd.getId()));
    	}
    	
//    	BJ -------- remove
//    	if(reportDefinitionListForCreation != null && reportDefinitionListForCreation.size() > 0){
//    		List<Report> newlyCreatedReports = evaluationService.addOptionalReports(getAeReport(), reportDefinitionListForCreation, useDefaultVersion);
//    	}
    }
    
    /**
     * This method will withdraw the reports
     */
    public void withdrawReports(List<Report> toWithdrawList){
    	for(Report report: toWithdrawList){
    		reportRepository.withdrawReport(report);
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
    
    public Map<ReportDefinition, Report> getAmendedReportsMap() {
		return amendedReportsMap;
	}
    public void setAmendedReportsMap(
			Map<ReportDefinition, Report> amendedReportsMap) {
		this.amendedReportsMap = amendedReportsMap;
	}
}
