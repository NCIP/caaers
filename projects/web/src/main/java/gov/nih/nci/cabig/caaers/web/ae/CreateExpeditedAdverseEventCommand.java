package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.dao.AdverseEventReportingPeriodDao;
import gov.nih.nci.cabig.caaers.dao.ExpeditedAdverseEventReportDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.StudyParticipantAssignmentDao;
import gov.nih.nci.cabig.caaers.dao.report.ReportDefinitionDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Physician;
import gov.nih.nci.cabig.caaers.domain.Reporter;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportTree;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.domain.repository.ReportRepository;
import gov.nih.nci.cabig.caaers.service.EvaluationService;
import gov.nih.nci.cabig.caaers.web.RenderDecisionManager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Sameer Sawant
 * @author Biju Joseph
 */
public class CreateExpeditedAdverseEventCommand extends AbstractExpeditedAdverseEventInputCommand {
	
	private Study study;
	private AdverseEventReportingPeriod adverseEventReportingPeriod;
	private Participant participant;
	
	// Added for Post processing in Confirmation page
	private List<ReportDefinition> allReportDefinitions;

	private Map<Integer, String> reportStatusMap;
	private Map<Integer, Boolean> requiredReportDefinitionIndicatorMap;
	private List<ReportDefinition> requiredReportDefinitions;
	
	private Map<Integer, Boolean> reportDefinitionMap;//will store user selection
	
	//this map is used for internal purpouses
	private Map<Integer, ReportDefinition> reportDefinitionIndexMap;
	
	private EvaluationService evaluationService;
	
	private StudyDao studyDao;
	
	
	
	public CreateExpeditedAdverseEventCommand(ExpeditedAdverseEventReportDao reportDao, ReportDefinitionDao reportDefinitionDao, 
			AdverseEventReportingPeriodDao reportingPeriodDao, ExpeditedReportTree expeditedReportTree, RenderDecisionManager renderDecisionManager, 
			EvaluationService evaluationService, ReportRepository reportRepository, StudyDao studyDao, StudyParticipantAssignmentDao assignmentDao) {
		
		super(reportDao, reportDefinitionDao, reportingPeriodDao, expeditedReportTree, renderDecisionManager, reportRepository, assignmentDao);

        this.setAeReport(new ExpeditedAdverseEventReport());
        this.evaluationService = evaluationService;
		this.studyDao = studyDao;
        
        this.allReportDefinitions = new ArrayList<ReportDefinition>();
        this.reportStatusMap = new HashMap<Integer, String>();
        this.requiredReportDefinitionIndicatorMap = new HashMap<Integer, Boolean>();
        this.reportDefinitionMap = new HashMap<Integer, Boolean>();
        this.reportDefinitionIndexMap = new HashMap<Integer, ReportDefinition>();
        this.requiredReportDefinitions = new ArrayList<ReportDefinition>();
        
	}
	
	//================ business methods =====================
	
	@Override
	public boolean isAdditionAllowed() {
		return true;
	}
	
	@Override
    public void reassociate() {
		
		//always reassociate the study
		if(aeReport.getReportingPeriod() != null && aeReport.getStudy() != null){
			studyDao.reassociate(aeReport.getStudy());
		}
		
		//reassociated report definitions
//		reassociateSelectedReportDefinitions();
		
		//first reassociate the reporting period
		if(aeReport.getReportingPeriod() != null){
			reportingPeriodDao.reassociate(aeReport.getReportingPeriod());
		}
		
		
		//now reassociate the report only if it is persistent.
		if(aeReport.getId() != null){
			reportDao.reassociate(aeReport);
		}
    }
	
	public void reassociateSelectedReportDefinitions(){
		//always reassociate the report definitions	
		for (ReportDefinition definition : selectedReportDefinitions) {
	            reportDefinitionDao.reassociate(definition);
	    }
	}
	
	public void saveReportingPeriod() {
		if(adverseEventReportingPeriod != null)
			reportingPeriodDao.save(adverseEventReportingPeriod);
		
	}
	

	@Override
    public void save() {
        reportDao.save(aeReport);
    }
	

	@Override
    public void flush() {
    	reportDao.flush();
    }
	
	/**
	 * This method will take care of setting up the command object.
	 */
	public void initialize(){
		//set the same to Expedited Report
		this.aeReport.setReportingPeriod(adverseEventReportingPeriod);
		
		//create one AE by default.
		if(aeReport.getId() == null && aeReport.getReports().size() < 1){
			//create a new adverse event. 
			AdverseEvent ae = new AdverseEvent();
			ae.setReport(aeReport);
			aeReport.getReportingPeriod().addAdverseEvent(ae);
			aeReport.addAdverseEvent(ae);
			//save the reporting period.
			saveReportingPeriod();
			
			// Initialize the treatment assignment & start date of course
	        initializeTreatmentInformation();
	        
	        //update the outcomes map
	        updateOutcomes();
		}
		
		initializeNotApplicableFields();
	}
	
	
	@Override
	public void initializeNotApplicableFields() {
		
		super.initializeNotApplicableFields();
		
		//special case, for non DCP studies, dont show DCP specific fields
		if(!isDCPNonAdeersStudy()){
			renderDecisionManager.conceal("aeReport.adverseEvents[].eventApproximateTime", 
					"aeReport.adverseEvents[].outcomes", "aeReport.adverseEvents[].eventLocation"); //also no outcomes
		}else{
			renderDecisionManager.reveal("aeReport.adverseEvents[].eventApproximateTime",
					"aeReport.adverseEvents[].eventLocation","aeReport.adverseEvents[].outcomes"); //event time not to be shown
		}
		
	}
	
	
	/**
	 * This method will check if the study selected is a DCP sponsored study and is AdEERS submittable.
	 * @return
	 */
	public boolean isDCPNonAdeersStudy(){
		if(study == null) return false;
		return (!study.getAdeersReporting()) && study.getPrimaryFundingSponsorOrganization().getNciInstituteCode().equals("DCP");
	}
	
	
	
	  /**
     * This method will find all avaliable report definitions for all the StudyOrganizations. 
     */
    public List<ReportDefinition> findAllReportDefintionNames(){
    	// evalutate available report definitions per session.
    	if(this.allReportDefinitions.isEmpty()){
    		this.allReportDefinitions.addAll(evaluationService.applicableReportDefinitions(this.aeReport.getAssignment()));
    		//upate the index map
    		for(ReportDefinition repDef : allReportDefinitions){
    			reportDefinitionIndexMap.put(repDef.getId(), repDef);
    		}
    	}
    	return this.allReportDefinitions;
    }
    
    public List<ReportDefinition> findRequiredReportDefinitions(){
    	requiredReportDefinitions.clear();
    	//if already available return that, as we will take care of clearing it when we quit this tab.
    	if(!aeReport.getReportingPeriod().isBaselineReportingType()){
    		requiredReportDefinitions.addAll(evaluationService.findRequiredReportDefinitions(aeReport, aeReport.getAdverseEvents(), aeReport.getStudy()));
    	}
    	return requiredReportDefinitions;
    }


    /**
     * This method will return the ReportDefinition which are selected by user
     * page. + the report definitions that are already instantiated
     */
    public List<ReportDefinition> getSelectedReportDefinitions() {
    	selectedReportDefinitions.clear();
    	
        for (Map.Entry<Integer, Boolean> entry : reportDefinitionMap.entrySet()) {
            if (entry.getValue() != null && entry.getValue()) selectedReportDefinitions.add(reportDefinitionIndexMap.get(entry.getKey()));
        }
        
        
        return selectedReportDefinitions;
   }
    
    /**
     * This method will find the newly selected reports.
     * @return
     */
    public  Collection<ReportDefinition> getNewlySelectedReportDefinitions(){
    	List<ReportDefinition> selectedReportDefs = getSelectedReportDefinitions();
    	List<ReportDefinition> instantiatedReportDefs = getInstantiatedReportDefinitions();
    	
    	//find difference  (selected - instantiated)
    	Map<Integer, ReportDefinition> selectedMap = new HashMap<Integer, ReportDefinition>();
    	for(ReportDefinition reportDef :selectedReportDefs){
    		selectedMap.put(reportDef.getId(), reportDef);
    	}
    	for(ReportDefinition reportDef : instantiatedReportDefs){
    		selectedMap.remove(reportDef.getId());
    	}
    	
    	Collection<ReportDefinition> difference =  selectedMap.values();
    	return difference;
    }
    /**
     * This method will create new reports by calling evaluation service.
     */
    public void instantiateNewlySelectedReports(){
    	Collection<ReportDefinition> newlySelectedReports = getNewlySelectedReportDefinitions();
    	if(newlySelectedReports != null){
    		evaluationService.addOptionalReports(aeReport, newlySelectedReports, false);
    	}
    }
    
    /**
	 * This will remove all unselected report definitions from the report, by calling delete on the repository 
	 * @param aeReport
	 * @param removedDefinitions
	 */
	public void removeUnselectedReports() {
		 List<Report> nonWitdrawnReports = aeReport.getNonWithdrawnReports();
		 List<ReportDefinition> selectedReportDefs = getSelectedReportDefinitions();
		 Map<Integer, Report> unselectedMap = new HashMap<Integer, Report>();
		 //find difference (nonwithdrawn - selected)
		 for(Report report : nonWitdrawnReports){
			unselectedMap.put(report.getReportDefinition().getId(), report);
		 }
		 for(ReportDefinition reportDef : selectedReportDefs){
			 unselectedMap.remove(reportDef.getId());
		 }
		 Collection<Report> reportsToDelete = unselectedMap.values();
		 for(Report report : reportsToDelete){
			 reportRepository.deleteReport(report);
		 }
		 
	}
    
    
   /**
    * Will clear first the report definitions, then
    *  add all report definitions, with value false, and updates the the value to true for selected ones.  
    */
   public void refreshReportDefinitionMap(){
	   reportDefinitionMap.clear();
	   for(ReportDefinition rpDef : allReportDefinitions){
		   reportDefinitionMap.put(rpDef.getId(), false);
	   }
	   
	   //rules engine said reports should be selected
	   for(ReportDefinition rpDef : requiredReportDefinitions){
		   reportDefinitionMap.put(rpDef.getId(), true);
	   }
	   
	   //also the user selected ones should be checked. 
	   // add the reports that are already instantiated
       if(aeReport.getId() != null){
       	List<Report> nonWithdrawnReports = aeReport.getNonWithdrawnReports();
       	for(Report report : nonWithdrawnReports){
       		ReportDefinition repDef = report.getReportDefinition();
       		reportDefinitionMap.put(repDef.getId(), true);
       	}
       		
       }
   }
    
   
    
    public void refreshReportStatusMap(){
    	reportStatusMap.clear();
        
    	//initialize every thing with empty
    	for(ReportDefinition rpDef : allReportDefinitions){
    		reportStatusMap.put(rpDef.getId(), rpDef.getExpectedDisplayDueDate());
    	}
    }
    
	public void refreshReportDefinitionRequiredIndicatorMap(){
		this.requiredReportDefinitionIndicatorMap.clear();
		for(ReportDefinition rpDef : allReportDefinitions){
			requiredReportDefinitionIndicatorMap.put(rpDef.getId(), false);
		}
		for(ReportDefinition rpDef : requiredReportDefinitions){
			requiredReportDefinitionIndicatorMap.put(rpDef.getId(), true);
		}
	}
	
	
	//=============== mutators ==============================
	
	@Override
    public Participant getParticipant() {
		return participant;
    }
	
	@Override
    public Study getStudy() {
		return study;
    }

	@Override
    public StudyParticipantAssignment getAssignment() {
		if(getAeReport() != null)
			return getAeReport().getAssignment();
		return null;
    }
	
	public void setStudy(Study study){
		this.study = study;
	}
	
	public void setParticipant(Participant participant){
		this.participant = participant;
	}
	
	public AdverseEventReportingPeriod getAdverseEventReportingPeriod(){
		return adverseEventReportingPeriod;
	}
	
	/**
	 * We should set the reporting period back to the expedited report in context.
	 * @param adverseEventReportingPeriod
	 */
	
	public void setAdverseEventReportingPeriod(AdverseEventReportingPeriod adverseEventReportingPeriod){
		this.adverseEventReportingPeriod = adverseEventReportingPeriod;
		
	}

	public List<ReportDefinition> getAllReportDefinitions() {
		return allReportDefinitions;
	}

	public void setAllReportDefinitions(List<ReportDefinition> allReportDefinitions) {
		this.allReportDefinitions = allReportDefinitions;
	}

	public Map<Integer, String> getReportStatusMap() {
		return reportStatusMap;
	}

	public void setReportStatusMap(Map<Integer, String> reportStatusMap) {
		this.reportStatusMap = reportStatusMap;
	}

	public Map<Integer, Boolean> getRequiredReportDefinitionIndicatorMap() {
		return requiredReportDefinitionIndicatorMap;
	}

	public void setRequiredReportDefinitionIndicatorMap(
			Map<Integer, Boolean> requiredReportDefinitionIndicatorMap) {
		this.requiredReportDefinitionIndicatorMap = requiredReportDefinitionIndicatorMap;
	}

	public Map<Integer, Boolean> getReportDefinitionMap() {
		return reportDefinitionMap;
	}

	public void setReportDefinitionMap(Map<Integer, Boolean> reportDefinitionMap) {
		this.reportDefinitionMap = reportDefinitionMap;
	}
	
	
	

}