package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.dao.AdverseEventReportingPeriodDao;
import gov.nih.nci.cabig.caaers.dao.ExpeditedAdverseEventReportDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.StudyParticipantAssignmentDao;
import gov.nih.nci.cabig.caaers.dao.report.ReportDefinitionDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.Ctc;
import gov.nih.nci.cabig.caaers.domain.CtcCategory;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Grade;
import gov.nih.nci.cabig.caaers.domain.Hospitalization;
import gov.nih.nci.cabig.caaers.domain.Outcome;
import gov.nih.nci.cabig.caaers.domain.OutcomeType;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.ReportStatus;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.Term;
import gov.nih.nci.cabig.caaers.domain.dto.ApplicableReportDefinitionsDTO;
import gov.nih.nci.cabig.caaers.domain.dto.EvaluationResultDTO;
import gov.nih.nci.cabig.caaers.domain.dto.ReportDefinitionWrapper;
import gov.nih.nci.cabig.caaers.domain.dto.ReportDefinitionWrapper.ActionType;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.domain.workflow.ReportingPeriodReviewComment;
import gov.nih.nci.cabig.caaers.service.EvaluationService;
import gov.nih.nci.cabig.caaers.utils.DateUtils;
import gov.nih.nci.cabig.caaers.utils.DurationUtils;
import gov.nih.nci.cabig.caaers.utils.IndexFixedList;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
/**
 * @author Sameer Sawanth
 * @author Biju Joseph
 *
 */
public class CaptureAdverseEventInputCommand implements	AdverseEventInputCommand {
	
	private StudyParticipantAssignment assignment;
	private StudyParticipantAssignmentDao assignmentDao;
	private ReportDefinitionDao reportDefinitionDao;
	private ExpeditedAdverseEventReportDao  aeReportDao;
	
	private Participant participant; 
	private Study study;
	private EvaluationService evaluationService;
	protected AdverseEventReportingPeriod adverseEventReportingPeriod;
	protected AdverseEventReportingPeriodDao adverseEventReportingPeriodDao;
	private StudyDao studyDao;
	
	private List<CtcCategory> ctcCategories;
	private Integer primaryAdverseEventId;
	
	private List<Map<Integer, Boolean>> outcomes;
    private List<String> outcomeOtherDetails;
    
    private ApplicableReportDefinitionsDTO applicableReportDefinitions;
    
    private EvaluationResultDTO evaluationResult;
    
    //aeReportId - {messages}
    private Map<Integer, List<String>> rulesMessageMap;
    
    private Map<Integer, List<ReportTableRow>> recommendedReportTableMap;
    private Map<Integer, List<ReportTableRow>> applicableReportTableMap;
    
    //aeReportId - aeReport
    private Map<Integer, ExpeditedAdverseEventReport> aeReportIndexMap;
    
    private ReviewAndReportResult reviewResult;
    
	private Ctc ctcVersion;
	
	private boolean workflowEnabled = false;
	
	private String _action;
	
	private String reportingMethod;
	protected HashMap<String, Boolean> errorsForFields;
	protected String verbatim;
	
	public CaptureAdverseEventInputCommand(){

        this.outcomes = new ArrayList<Map<Integer,Boolean>>();
        this.outcomeOtherDetails = new ArrayList<String>();
        this.rulesMessageMap = new LinkedHashMap<Integer, List<String>>();
        this.recommendedReportTableMap = new LinkedHashMap<Integer, List<ReportTableRow>>();
        this.applicableReportTableMap = new LinkedHashMap<Integer, List<ReportTableRow>>();
        aeReportIndexMap = new HashMap<Integer, ExpeditedAdverseEventReport>();
        
	}
	
	public CaptureAdverseEventInputCommand(AdverseEventReportingPeriodDao adverseEventReportingPeriodDao, 
				StudyParticipantAssignmentDao assignmentDao, EvaluationService evaluationService, ReportDefinitionDao reportDefinitionDao, StudyDao studyDao, ExpeditedAdverseEventReportDao aeReportDao){
		
		this();
		this.adverseEventReportingPeriodDao = adverseEventReportingPeriodDao;
		this.assignmentDao = assignmentDao;
		this.evaluationService = evaluationService;
		this.reportDefinitionDao = reportDefinitionDao;
		this.studyDao = studyDao;
		this.aeReportDao = aeReportDao;
		
	}
	
	/**
	 * This method will check if the study is AdEERS submittable.
	 * @return
	 */
	public boolean isNonAdeersStudy(){
		if(study == null) return false;
		return !study.getAdeersReporting();
	}
	
	/**
	 * Will return true, if the primary sponsor of this study is DCP.
	 * @return
	 */
	public boolean isDCPSponsoredStudy(){
		if(study == null) return false;
		return StringUtils.equals("DCP", study.getPrimarySponsorCode());
	}
	
	/**
	 * This method will save the {@link AdverseEventReportingPeriod}.
	 */
	public void save() {
		if(this.getAdverseEventReportingPeriod() != null){
			//initialize the graded date. 
			for(AdverseEvent ae : adverseEventReportingPeriod.getAdverseEvents()){
				ae.initailzeGradedDate();
				ae.initializePostSubmissionUpdatedDate();
			}
			adverseEventReportingPeriodDao.save(this.getAdverseEventReportingPeriod());
		}
	}
	
	/**
	 * This method returns the type of the command object (reportingPeriod)
	 *
	 */
	public String getCommandType(){
		return "reportingPeriod";
	}
	
	public void reassociate(ExpeditedAdverseEventReport aeReport){
		aeReportDao.reassociate(aeReport);
	}
	public void reassociate(){
//		//reassociate all report definitions
//		if(allReportDefinitions != null)
//		for(ReportDefinition repDef : allReportDefinitions){
//			reportDefinitionDao.reassociate(repDef);
//		}
		studyDao.lock(study);
		if(this.adverseEventReportingPeriod != null && this.adverseEventReportingPeriod.getId() != null){
			adverseEventReportingPeriodDao.reassociate(this.adverseEventReportingPeriod);
		}
		
	}



    
    public void clearSession(){
    	assignmentDao.clearSession();
    }
    
    public void evictUnwantedObjects(){
    	assignmentDao.evict(getAssignment());
    }
    
    
/*
    public void cleanEmptyAdverseEvents(){
    	List<AdverseEvent> adverseEvents = adverseEventReportingPeriod.getAdverseEvents();
    	List<AdverseEvent> unwantedAdverseEvents = new ArrayList<AdverseEvent>();
    	for(AdverseEvent ae : adverseEvents){
    		if(ae.getAdverseEventTerm() == null  || ae.getAdverseEventTerm().getTerm() == null) unwantedAdverseEvents.add(ae);
    	}
    	
    	
    	if(unwantedAdverseEvents.isEmpty()) return;
    	
    	for(AdverseEvent ae : unwantedAdverseEvents){
    		adverseEvents.remove(ae);
    	}
    	save();
    }
    
*/
    /**
     * This method will take care of initializing the lazy associations
     * This method will take care of
     *  - Updating the index fixed list for AdverseEvents, associated to the reporting period 
     *  - initializing the lazy associations
     */
    public void initialize(){
    	//set the assignment into the command.
    	if(this.adverseEventReportingPeriod != null){
    		this.assignment = this.adverseEventReportingPeriod.getAssignment();
    		// cleanEmptyAdverseEvents();
    	}
    	if(adverseEventReportingPeriod != null){
    		Study study = adverseEventReportingPeriod.getStudy();
			if(study.getStudyOrganizations() != null) study.getStudyOrganizations().size();
			if(study.getExpectedAECtcTerms() != null)	 study.getExpectedAECtcTerms().size();
			boolean isCTCStudy = study.getAeTerminology().getTerm() == Term.CTC;
			if(isCTCStudy){
				getCtcCategories();
				for(AdverseEvent ae : getAdverseEvents()){
					if(ae.getAdverseEventTerm() == null) continue;
					
					ae.getAdverseEventTerm().isOtherRequired();
					if(ae.getAdverseEventCtcTerm().getCtcTerm() != null){
						ae.getAdverseEventCtcTerm().getCtcTerm().isOtherRequired();
	                    ae.getAdverseEventCtcTerm().getCtcTerm().getContextualGrades().size();
					}
				}
			}
			this.adverseEventReportingPeriod.getAdverseEvents().size();
			this.adverseEventReportingPeriod.getAeReports().size();
			for(ExpeditedAdverseEventReport aeReport : this.adverseEventReportingPeriod.getAeReports()){
				aeReport.getReports().size();
				aeReport.getAdverseEvents().size();
				for(Report report : aeReport.getReports()){
					report.getReportDefinition().getParent();
					report.getLastVersion().getReportedAdversEvents().size();
				}
			}
			List<ReportingPeriodReviewComment> reviewCommentList = this.adverseEventReportingPeriod.getReviewComments();
			if(reviewCommentList != null)
				this.adverseEventReportingPeriod.getReviewComments().size();
			if(this.assignment != null){
				if(assignment.getParticipant() != null)	this.assignment.getParticipant().getIdentifiers();
			}
			
			this.adverseEventReportingPeriod.getAssignment().getStudySite().getStudySiteWorkflowConfigs().size();
			
			this.adverseEventReportingPeriod.isBaselineReportingType();
			
		}
    }
    
   
    
    /**
     * This method will find all avaliable report definitions for all the StudyOrganizations. 
     */
    public List<ReportDefinition> findAllReportDefintionNames(){
//    	// evalutate available report definitions per session.
//    	if(this.allReportDefinitions.isEmpty()){
//    		this.allReportDefinitions.addAll(evaluationService.applicableReportDefinitions(this.assignment));
//    		//upate the index map
//    		for(ReportDefinition repDef : allReportDefinitions){
//    			reportDefinitionIndexMap.put(repDef.getId(), repDef);
//    		}
//    	}
//    	return this.allReportDefinitions;
    	return null;
    }
    
    public List<ReportDefinition> findRequiredReportDefinitions(){
//    	//if already available return that, as we will take care of clearing it when we quit this tab.
//    	if(requiredReportDefinitionsMap.isEmpty() && !adverseEventReportingPeriod.isBaselineReportingType()){
//    		this.requiredReportDefinitionsMap = evaluationService.findRequiredReportDefinitions(this.adverseEventReportingPeriod);
//    	}
//    	return new ArrayList<ReportDefinition>(requiredReportDefinitionsMap.keySet());
    	return null;
    }
    
    /**
     * Will figureout the report definitions required for modified adverse events.
     * @return
     */
    public List<ReportDefinition> findRequiredReportDefinitionsForModifiedAdverseEvents(){
    	List<AdverseEvent> modifiedAdverseEvents = adverseEventReportingPeriod.getModifiedExpeditedAdverseEvents();
    	if(modifiedAdverseEvents == null || modifiedAdverseEvents.isEmpty()) return new ArrayList<ReportDefinition>();
    	
    	return evaluationService.findRequiredReportDefinitions(null, modifiedAdverseEvents, adverseEventReportingPeriod.getStudy());
    }
    /**
     * This method will find the report definitions required for non expedited adverse events.
     * @return
     */
    public List<ReportDefinition> findRequiredReportDefinitionsForNonExpeditedAdverseEvents(){
    	
    	List<AdverseEvent> nonExpeditedAdverseEvents = adverseEventReportingPeriod.getNonExpeditedAdverseEvents();
    	if(nonExpeditedAdverseEvents == null || nonExpeditedAdverseEvents.isEmpty()) return new ArrayList<ReportDefinition>();
    	
    	return evaluationService.findRequiredReportDefinitions(null, nonExpeditedAdverseEvents, adverseEventReportingPeriod.getStudy());
    }


    /**
     * This method will return the ReportDefinition which are selected by user
     * page.
     */
    public List<ReportDefinition> getSelectedReportDefinitions() {
        List<ReportDefinition> reportDefs = new ArrayList<ReportDefinition>();
//        
//        for (Map.Entry<Integer, Boolean> entry : reportDefinitionMap.entrySet()) {
//            if (entry.getValue() != null && entry.getValue()){
//            	ReportDefinition reportDef = reportDefinitionIndexMap.get(entry.getKey());
//            	if(reportDef != null) reportDefs.add(reportDef);
//            }
//        }
        return reportDefs;
   }
    
   /**
    * Will clear first the report definitions, then
    *  add all report definitions, with value false, and updates the the value to true for selected ones.  
    */
   public void refreshReportDefinitionMap(){
//	   reportDefinitionMap.clear();
//	   for(ReportDefinition rpDef : allReportDefinitions){
//		   reportDefinitionMap.put(rpDef.getId(), false);
//	   }
//	   
//	   //rules engine said reports should be selected
//	   for(ReportDefinition rpDef : requiredReportDefinitionsMap.keySet()){
//		   reportDefinitionMap.put(rpDef.getId(), true);
//	   }
   }
   
   /**
    * Will populate the map, containg the ID and ReportDefinition, that are associated to 
    * active ExpeditedReports of this reporting period. 
    */
   public void refreshInstantiatedReportDefinitionMap(){
//	   instantiatedReportDefinitionMap.clear();
//	   for(ExpeditedAdverseEventReport aeReport : getAdverseEventReportingPeriod().getActiveAeReports()){
//		   for(Report report : aeReport.getReports()){
//			   if(!report.isHavingStatus(ReportStatus.WITHDRAWN , ReportStatus.AMENDED, ReportStatus.REPLACED)){
//				   instantiatedReportDefinitionMap.put(report.getReportDefinition().getId(), report.getReportDefinition());
//			   }
//			   
//		   }
//	   }
   }
    
    /**
     * This method will return the adverse events that are selected (checked)
     * @return
     */
    public List<AdverseEvent> findSelectedAdverseEvents(){
    	List<AdverseEvent> adverseEvents = new ArrayList<AdverseEvent>();
//    	for(AdverseEvent ae : adverseEventReportingPeriod.getAdverseEvents()){
//    		Boolean value = selectedAesMap.get(ae.getId());
//    		if(value != null && value){
//    			adverseEvents.add(ae);
//    		}
//    	}
    	return adverseEvents;
    }
    
    public void refreshReportStatusMap(){
//    	reportStatusMap.clear();
//        Date earliestGradedDate = adverseEventReportingPeriod.getEarliestAdverseEventGradedDate();
//        if(earliestGradedDate == null) earliestGradedDate = new Date();
//        
//    	//initialize every thing with empty
//    	for(ReportDefinition rpDef : allReportDefinitions){
//    		reportStatusMap.put(rpDef.getId(), rpDef.getExpectedDisplayDueDate(earliestGradedDate));
//    	}
    }
    
	public void refreshReportDefinitionRequiredIndicatorMap(){
//		this.requiredReportDefinitionIndicatorMap.clear();
//		for(ReportDefinition rpDef : allReportDefinitions){
//			requiredReportDefinitionIndicatorMap.put(rpDef.getId(), false);
//		}
//		for(ReportDefinition rpDef : this.requiredReportDefinitionsMap.keySet()){
//			requiredReportDefinitionIndicatorMap.put(rpDef.getId(), true);
//		}
	}
	
	
	/**
	 * All AEs associated with <b>selectedReportDefinitions</b> must be selected.
	 * 
	 * This method populates the SelectedAesMap member of the command object. The adverse events that were serious on triggering of the rules are set to true in this
	 * Map. All the adverse events in the reporting period are keys in this map.
	 */
	public void refreshSelectedAesMap(){
//		selectedAesMap.clear();
//		//initialize all selected aes to false
//		for(AdverseEvent ae: adverseEventReportingPeriod.getAdverseEvents()){
//			Integer id = ae.getId();
//			selectedAesMap.put(id, Boolean.FALSE);
//		}
//		for(AdverseEvent ae: adverseEventReportingPeriod.getModifiedReportableAdverseEvents()){
//			ae.setRequiresReporting(false);
//		}
//		
//		//reportingRequired boolean is set in the case when requiredReportDefinitionsMap is not empty (ie there are reports
//		//suggested by caAERS. In this case all the aes in the selectedAesMap will be set to true, so that all of them are checked
//		//on the review report page.
//		Boolean reportingRequired = false;
//		//reset the ones that are available below with true
//		for(Map.Entry<ReportDefinition, List<AdverseEvent>> entry : requiredReportDefinitionsMap.entrySet()){
//			reportingRequired = true;
//			for(AdverseEvent ae : entry.getValue()){
//				ae.setRequiresReporting(Boolean.TRUE);
//			}
//		}
//		
//		//Set all the aes in the reportingRequired map to true incase reportingRequired == true
//		if(reportingRequired){
//			for(AdverseEvent ae: adverseEventReportingPeriod.getReportableAdverseEvents()){
//				selectedAesMap.put(ae.getId(), true);
//			}
//		}
	}
    
	
	

	/**
	 * This method will initialize the outcomes and outcomeOtherDetails, in the command. 
	 */
	public void initializeOutcomes() {
		outcomeOtherDetails.clear();
    	outcomes.clear();
    	int i = 0;
    	//This method will populate the outcome map and the outcomeSerious details map.
    	for(AdverseEvent ae : getAdverseEvents()){
    	
    		//update the command bounded variables with default values
    		outcomeOtherDetails.add("");
    		LinkedHashMap<Integer, Boolean> oneOutcomeMap = new LinkedHashMap<Integer, Boolean>();
    		outcomes.add(oneOutcomeMap);
    	
        
    		//in this pass we will update the outcome details based on the OUTCOME db values
    		if(ae != null){

        		//in this pass we will initialize all the outcomes to default 'FALSE' and other details to empty string.
        		for(OutcomeType outcomeType : OutcomeType.values()){
        			oneOutcomeMap.put(outcomeType.getCode(), Boolean.FALSE);
        		}
    			for(Outcome outcome : ae.getOutcomes()){
        			oneOutcomeMap.put(outcome.getOutcomeType().getCode(), Boolean.TRUE);
        			if(outcome.getOutcomeType().equals(OutcomeType.OTHER_SERIOUS)){
        				outcomeOtherDetails.set(i, outcome.getOther());
        			}
        		}
    		}
    		        
    		i++;
    	}
	}
	
	/**
	 * This method will synchronize the outcomes list associated with the adverse event. 
	 *   If grade is 5, the outcome of type death is added, else removed(if present)
	 *   If hospitalization is 'yes', the outcome of type hospitalization is added, else removed (if present)
	 *   If the serious(outcome) not available in the outcomes list, it will be added.
	 *   Remove all the other outcomes present in the list (means user deselected a previously selected one)
	 */
	public void synchronizeOutcome() {
		int size = (getAdverseEvents() == null) ? 0 : getAdverseEvents().size();
		
		for(int i = 0; i < size; i++){
			AdverseEvent ae = getAdverseEvents().get(i);
			if(ae == null) continue;
			
			//update the other outcomes based on the user selection
			Map<Integer, Boolean> outcomeMap = getOutcomes().get(i);
            for (Map.Entry<Integer, Boolean> entry : outcomeMap.entrySet()) {
                if (entry.getValue()) {
                	OutcomeType outcomeType = OutcomeType.getByCode(entry.getKey());
                	if(!isOutcomePresent(OutcomeType.getByCode(entry.getKey()), ae.getOutcomes())){
                		Outcome newOutcome = new Outcome();
    					newOutcome.setOutcomeType(outcomeType);
    					if(outcomeType == OutcomeType.OTHER_SERIOUS) newOutcome.setOther(getOutcomeOtherDetails().get(i));
    					ae.addOutcome(newOutcome);
                	}
                } else {
                	OutcomeType outcomeType = OutcomeType.getByCode(entry.getKey());
                	removeOutcomeIfPresent(outcomeType, ae.getOutcomes());
                }
            }

            //special cases, add DEATH and HOSPITALIZATION
            //if grade is 5 make sure we have OutcomeType.DEATH
            if (ae.getGrade() != null && ae.getGrade().equals(Grade.DEATH)) {
                if (!isOutcomePresent(OutcomeType.DEATH, ae.getOutcomes())) {
                    Outcome newOutcome = new Outcome();
                    newOutcome.setOutcomeType(OutcomeType.DEATH);
                    ae.addOutcome(newOutcome);
                }
            } else {
                removeOutcomeIfPresent(OutcomeType.DEATH, ae.getOutcomes());
            }

            //if hospitalized, make sure we have OutcomeType.HOSPITALIZATION
            if (ae.getHospitalization() != null && ae.getHospitalization().equals(Hospitalization.YES)) {
                if (!isOutcomePresent(OutcomeType.HOSPITALIZATION, ae.getOutcomes())) {
                    Outcome newOutcome = new Outcome();
                    newOutcome.setOutcomeType(OutcomeType.HOSPITALIZATION);
                    ae.addOutcome(newOutcome);
                }
            } else {
                removeOutcomeIfPresent(OutcomeType.HOSPITALIZATION, ae.getOutcomes());
            }
		}
		
	}
	
	
	/**
	 * Returns true, if an Outcome of a specific type is present in the list of outcomes
	 */
	public boolean isOutcomePresent(OutcomeType type, List<Outcome> outcomes){
		if(outcomes == null || outcomes.isEmpty()) return false;
		for(Outcome o : outcomes){
			if(o.getOutcomeType() == type) return true;
		}
		return false;
	}
	
	/**
	 * Removes an outcome type, if it is present.  
	 */
	private boolean removeOutcomeIfPresent(OutcomeType type, List<Outcome> outcomes){
		if(outcomes == null || outcomes.isEmpty()) return false;
		Outcome obj = null;
		for(Outcome o : outcomes){
			if(o.getOutcomeType() == type){ 
				obj =  o;
				break;
			}
		}
		if(obj == null) return false;
		return outcomes.remove(obj);
	}
    
    public List<AdverseEvent> getAdverseEvents() {
		return adverseEventReportingPeriod.getAdverseEvents();
	}

    //    /**
//     * Returns all the {@link ReportDefinition} available to this AE
//     */
//    public List<ReportDefinition> getAllReportDefinitions() {
//        return allReportDefinitions;
//    }
//
//    public void setAllReportDefinitions(List<ReportDefinition> allReportDefinitions) {
//        this.allReportDefinitions = allReportDefinitions;
//    }
    
    public void setAdverseEventReportingPeriodDao(
			AdverseEventReportingPeriodDao adverseEventReportingPeriodDao) {
		this.adverseEventReportingPeriodDao = adverseEventReportingPeriodDao;
	}
    
	public Ctc getCtcVersion() {
		return ctcVersion;
	}
	
	public void setCtcVersion(Ctc ctcVersion) {
		this.ctcVersion = ctcVersion;
	}
	
	public Integer getTermCode(){
		return null;
	}
	//this method is added to satisfy the UI requirements, so to be moved to the command class
	public void setTermCode(Integer ignore){}
	
    public StudyParticipantAssignment getAssignment() {
        if(assignment != null) return assignment;
        
        //fetch the assignment from DB.
    	if (getParticipant() != null && getStudy() != null) {
            this.assignment =  assignmentDao.getAssignment(getParticipant(), getStudy());
        }
    	
    	return this.assignment;
    }

	public void setAssignment(StudyParticipantAssignment assignment) {
		this.assignment = assignment;
	}

	public boolean getIgnoreCompletedStudy() {
		// TODO Auto-generated method stub
		return false;
	}


	public Participant getParticipant() {
		return participant;
	}

	public Study getStudy() {
		return study;
	}

	public Participant getParticipantID() {
		return participant;
	}

	public Study getStudyID() {
		return study;
	}

	public String getTreatmentDescriptionType() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setTreatmentDescriptionType(String type) {
		// TODO Auto-generated method stub
		
	}

	public void setParticipant(Participant participant) {
		this.participant = participant;
	}

	public void setStudy(Study study) {
		this.study = study;
	}

	public void setParticipantID(Participant participant) {
		this.participant = participant;
	}

	public void setStudyID(Study study) {
		this.study = study;
	}

	public AdverseEventReportingPeriod getAdverseEventReportingPeriod() {
		return adverseEventReportingPeriod;
	}

	public void setAdverseEventReportingPeriod(AdverseEventReportingPeriod adverseEventReportingPeriod) {
		this.adverseEventReportingPeriod = adverseEventReportingPeriod;
		initialize();
		
	}
	
	public void setCtcCategories(List<CtcCategory> ctcCategories) {
		this.ctcCategories = ctcCategories;
	}
	
	public List<CtcCategory> getCtcCategories() {
		if(ctcCategories == null){
			if(adverseEventReportingPeriod != null)
			setCtcCategories(adverseEventReportingPeriod.getStudy().getAeTerminology().getCtcVersion().getCategories());
		}
			
		return ctcCategories;
	}
	
    public EvaluationService getEvaluationService() {
        return evaluationService;
    }

    public void setEvaluationService(EvaluationService evaluationService) {
        this.evaluationService = evaluationService;
    }
//    
//    public Map<ReportDefinition, List<AdverseEvent>> getRequiredReportDefinitionsMap() {
//		return requiredReportDefinitionsMap;
//	}
//    public void setRequiredReportDefinitionsMap(Map<ReportDefinition, List<AdverseEvent>> map){
//    	this.requiredReportDefinitionsMap = map;
//    }
//    
//    public Map<Integer, String> getReportStatusMap() {
//		return reportStatusMap;
//	}
//    public void setReportStatusMap(Map<Integer, String> map) {
//		reportStatusMap = map;
//	}
//    public Map<Integer, Boolean> getRequiredReportDefinitionIndicatorMap() {
//		return requiredReportDefinitionIndicatorMap;
//	}
//    public void setRequiredReportDefinitionIndicatorMap(Map<Integer, Boolean> map) {
//		requiredReportDefinitionIndicatorMap = map;
//	}
//    public Map<Integer, Boolean> getReportDefinitionMap() {
//		return reportDefinitionMap;
//	}
//    public void setReportDefinitionMap(Map<Integer, Boolean> reportDefinitionMap) {
//		this.reportDefinitionMap = reportDefinitionMap;
//	}

	public Integer getPrimaryAdverseEventId() {
		return primaryAdverseEventId;
	}

	public void setPrimaryAdverseEventId(Integer primaryAdverseEventId) {
		this.primaryAdverseEventId = primaryAdverseEventId;
	}
    
	
	public ReviewAndReportResult getReviewResult() {
		return reviewResult;
	}
	public void setReviewResult(ReviewAndReportResult reviewResult) {
		this.reviewResult = reviewResult;
	}
	
//	/**
//	 * Returns the {@link AdverseEvent}s selected in the page. With primary being the first one.
//	 * @return
//	 */
//    public List<AdverseEvent> getSelectedAesList() {
//		List<AdverseEvent> selectedAesList = new ArrayList<AdverseEvent>();
//    	for(AdverseEvent ae: adverseEvents){
//    		if(ae.getReport() != null) continue;
//    		if(BooleanUtils.isTrue(getSelectedAesMap().get(ae.getId()))){
//    			if(primaryAdverseEventId != null && ae.getId().equals(primaryAdverseEventId)){
//    				selectedAesList.add(0, ae);
//    			}else{
//    				selectedAesList.add(ae);
//    			}
//    		}
//    	}
//    	
//    	return selectedAesList;
//	}
    
    public boolean getWorkflowEnabled() {
		return workflowEnabled;
	}

	public void setWorkflowEnabled(boolean workflowEnabled) {
		this.workflowEnabled = workflowEnabled;
	}
    
    //hasLabs
    public boolean isAssociatedToLabAlerts(){
    	 return false;
    }
    
    public boolean isAssociatedToWorkflow(){
    	if(getAdverseEventReportingPeriod() == null) return false;
    	return getAdverseEventReportingPeriod().getWorkflowId() != null;
    }
    
    public boolean isHavingSolicitedAEs(){
    	if(adverseEventReportingPeriod == null) return false;
    	if(adverseEventReportingPeriod.getAdverseEvents() == null) return false;
    	for(AdverseEvent ae : adverseEventReportingPeriod.getAdverseEvents())
    		if(ae.getSolicited()) return true;
    	return false;
    }
    
    public String get_action() {
		return _action;
	}
    public void set_action(String _action) {
		this._action = _action;
	}
    
    public String getReportingMethod() {
		return reportingMethod;
	}
    public void setReportingMethod(String reportingMethod) {
		this.reportingMethod = reportingMethod;
	}
    
    public List<Map<Integer, Boolean>> getOutcomes() {
		return outcomes;
	}
    public void setOutcomes(List<Map<Integer, Boolean>> outcomes) {
		this.outcomes = outcomes;
	}
    public List<String> getOutcomeOtherDetails() {
		return outcomeOtherDetails;
	}
    public void setOutcomeOtherDetails(List<String> outcomeOtherDetails) {
		this.outcomeOtherDetails = outcomeOtherDetails;
	}
    
    public ReportDefinition reassociateReportDefinition(ReportDefinition reportDefinition){
    	return reportDefinitionDao.merge(reportDefinition);
    }
    
//    public Map<Integer, ReportDefinition> getInstantiatedReportDefinitionMap() {
//		return instantiatedReportDefinitionMap;
//	}
//    public void setInstantiatedReportDefinitionMap(
//			Map<Integer, ReportDefinition> instantiatedReportDefinitionMap) {
//		this.instantiatedReportDefinitionMap = instantiatedReportDefinitionMap;
//	}
	
    public HashMap<String, Boolean> getErrorsForFields() {
        return errorsForFields;
    }

    public void setErrorsForFields(HashMap<String, Boolean> errorsForFields) {
        this.errorsForFields = errorsForFields;
    }
    
    public EvaluationResultDTO getEvaluationResult() {
		return evaluationResult;
	}
    public void setEvaluationResult(EvaluationResultDTO evaluationResult) {
		this.evaluationResult = evaluationResult;
	}
    public ApplicableReportDefinitionsDTO getApplicableReportDefinitions() {
		return applicableReportDefinitions;
	}
    public void setApplicableReportDefinitions(
			ApplicableReportDefinitionsDTO applicableReportDefinitions) {
		this.applicableReportDefinitions = applicableReportDefinitions;
	}
    
    public Map<Integer, List<String>> getRulesEngineMessageMap() {
		return rulesMessageMap;
	}
    public void setRulesEngineMessageMap(Map<Integer, List<String>> rulesMessageMap) {
		this.rulesMessageMap = rulesMessageMap;
	}
    
   public Map<Integer, List<ReportTableRow>> getRecommendedReportTableMap() {
	return recommendedReportTableMap;
   }
   
   public void setRecommendedReportTableMap(
		Map<Integer, List<ReportTableRow>> recomendedReportTableMap) {
	this.recommendedReportTableMap = recomendedReportTableMap;
   }
   
   public Map<Integer, List<ReportTableRow>> getApplicableReportTableMap() {
	return applicableReportTableMap;
   }
   
   public void setApplicableReportTableMap(
		Map<Integer, List<ReportTableRow>> applicableReportTableMap) {
	this.applicableReportTableMap = applicableReportTableMap;
   }
    
    public Integer getZero(){
    	return ZERO;
    }
    public void findApplicableReportDefinitions(){
    	//only once per page flow
    	if(applicableReportDefinitions == null){
    		applicableReportDefinitions = evaluationService.applicableReportDefinitions(getAdverseEventReportingPeriod().getStudy(), getAssignment());
    	}
    }
    
    public void evaluateSAERules(){
    	evaluationResult = evaluationService.evaluateSAERules(getAdverseEventReportingPeriod());
    }
    
    public void generateReadableRulesMessage(){
    	rulesMessageMap.clear();
    	
    	//for default(new data collection)
    	rulesMessageMap.put(ZERO, generateReadableRulesMessage(ZERO));
    	
    	//for each aeReport, find the rules message
    	for(ExpeditedAdverseEventReport aeReport : getAdverseEventReportingPeriod().getAeReports()){
    		rulesMessageMap.put(aeReport.getId(), generateReadableRulesMessage(aeReport.getId()));
    	}
    }
   
    public List<String> generateReadableRulesMessage(Integer id){
    	
    	//checklist will hold the report defs, for which message is already printed.
    	List<ReportDefinition> checklist = new ArrayList<ReportDefinition>();
    	
    	ArrayList<String> messages = new ArrayList<String>();
    	
    	//for amendments.
    	Set<ReportDefinitionWrapper> amendWrappers = evaluationResult.getAmendmentMap().get(id);
    	if(amendWrappers != null && !amendWrappers.isEmpty()){
    		for(ReportDefinitionWrapper wrapper : amendWrappers){
    			messages.add(wrapper.getReadableMessage());
    			if(wrapper.getSubstitute() != null){
    				checklist.add(wrapper.getSubstitute());
    			}
    		}
    	}
    	
    	//for withdraw
    	Set<ReportDefinitionWrapper> withdrawWrappers = evaluationResult.getWithdrawalMap().get(id);
    	if(withdrawWrappers != null && !withdrawWrappers.isEmpty()){
    		for(ReportDefinitionWrapper wrapper : withdrawWrappers){
    			messages.add(wrapper.getReadableMessage());
    			if(wrapper.getSubstitute() != null){
    				checklist.add(wrapper.getSubstitute());
    			}
    		}
    	}
    	
    	//for edit
    	Set<ReportDefinitionWrapper> editWrappers = evaluationResult.getEditMap().get(id);
    	if(editWrappers != null && !editWrappers.isEmpty()){
    		for(ReportDefinitionWrapper wrapper : editWrappers){
    			messages.add(wrapper.getReadableMessage());
    			if(wrapper.getSubstitute() != null){
    				checklist.add(wrapper.getSubstitute());
    			}
    		}
    	}
    	
    	//for create (only add it is not in checklist
    	Set<ReportDefinitionWrapper> createWrappers = evaluationResult.getCreateMap().get(id);
    	if(createWrappers != null && !createWrappers.isEmpty()){
    		for(ReportDefinitionWrapper wrapper : createWrappers){
    			if(checklist.contains(wrapper.getDef())) continue; //do not add
    			messages.add(wrapper.getReadableMessage());
    		}
    	}
    	
    	return messages;
    	
    }
    
    //will create a map with aeReportID as key, and ExpeditedAdverseEventReport as value
    public void refreshAeReportIdIndex(){
    	aeReportIndexMap.clear();
    	aeReportIndexMap.put(ZERO, null); //for new one
		for(ExpeditedAdverseEventReport aeReport : getAdverseEventReportingPeriod().getAeReports()){
			aeReportIndexMap.put(aeReport.getId(), aeReport);
		}
    }
    
    /**
     * This method will create the value objects that needs to be displayed on the UI for recommended options.
     */
    public void refreshRecommendedReportTable(){

    	recommendedReportTableMap.clear();
    	
    	//for every report id (including ZERO)
    	for(Integer aeReportId : aeReportIndexMap.keySet()){

        	//do for the default (new data collection).
        	List<ReportTableRow> tableRows = new ArrayList<ReportTableRow>();
        	
        	//for the default data collection (which will be new)
        	List<AdverseEvent> seriousAdverseEvents = evaluationResult.getSeriousAdverseEvents(aeReportId);
        	Date updatedDate = null;
        	Date gradedDate = null;
        	if(CollectionUtils.isNotEmpty(seriousAdverseEvents)){
        		updatedDate = AdverseEventReportingPeriod.findEarliestPostSubmissionUpdatedDate(seriousAdverseEvents);
        		gradedDate = AdverseEventReportingPeriod.findEarliestGradedDate(seriousAdverseEvents);
            	
        	}
        		
        	if(updatedDate == null) updatedDate = new Date();
        	if(gradedDate == null) gradedDate = new Date();
        	
        	//join the amend, withdraw, edit and create maps. 
    		List<ReportDefinitionWrapper> wrappers = new ArrayList<ReportDefinitionWrapper>();
    		
    		Set<ReportDefinitionWrapper> ammendWrappers = evaluationResult.getAmendmentMap().get(aeReportId);
    		if(ammendWrappers != null) wrappers.addAll(ammendWrappers);
    		
    		Set<ReportDefinitionWrapper> withdrawWrappers = evaluationResult.getWithdrawalMap().get(aeReportId);
    		if(withdrawWrappers != null) wrappers.addAll(withdrawWrappers);
    		
    		Set<ReportDefinitionWrapper> editWrappers = evaluationResult.getEditMap().get(aeReportId);
    		if(editWrappers != null) wrappers.addAll(editWrappers);
    		
    		Set<ReportDefinitionWrapper> createWrappers = evaluationResult.getCreateMap().get(aeReportId);
    		if(createWrappers != null) wrappers.addAll(createWrappers);
    		
    		
    		for(ReportDefinitionWrapper wrapper: wrappers){
    			
    			//if there is already a report created from the same group. use updated date.
    			Date baseDate =  gradedDate;
    			if(wrapper.getAction() == ActionType.CREATE){
    				ExpeditedAdverseEventReport aeReport = aeReportIndexMap.get(aeReportId);
    				if(aeReport != null){
    					if(aeReport.hasExistingReportsOfSameOrganizationAndType(wrapper.getDef())){
    						baseDate = updatedDate;
    					}
    				}
    			}
    			
        		ReportTableRow row  = ReportTableRow.createReportTableRow(wrapper.getDef(), baseDate, wrapper.getAction());
        		row.setAeReportId(aeReportId);
        		
        		if(wrapper.getAction() == ActionType.AMEND){
        			row.setStatus(wrapper.getStatus());
        			row.setDue("");
        		}else if(wrapper.getAction() == ActionType.WITHDRAW || wrapper.getAction() == ActionType.EDIT) {
        			row.setDue(DurationUtils.formatDuration(wrapper.getDueOn().getTime() - new Date().getTime(), wrapper.getDef().getTimeScaleUnitType().getFormat()));
        			row.setStatus(wrapper.getStatus());
        		}else {
        			row.setStatus(wrapper.getStatus());
        		}
        		
        		tableRows.add(row);
        	}
    		recommendedReportTableMap.put(aeReportId, tableRows);
    	}
    	
    }
    
    
    /**
     * This method will create the value objects, that are to be displayed on UI for override options. 
     */
    public void refreshApplicableReportTable(){

    	applicableReportTableMap.clear();
    	
    	//for every report id (including ZERO)
    	for(Integer aeReportId : aeReportIndexMap.keySet()){
    		
    		//find the earliest graded date, used while evaluating the aes. 
    		//for the default data collection (which will be new)
        	List<AdverseEvent> seriousAdverseEvents = evaluationResult.getSeriousAdverseEvents(aeReportId);
        	Date updatedDate = null;
        	Date gradedDate = null;
        	if(CollectionUtils.isNotEmpty(seriousAdverseEvents)){
        		updatedDate = AdverseEventReportingPeriod.findEarliestPostSubmissionUpdatedDate(seriousAdverseEvents);
        		gradedDate = AdverseEventReportingPeriod.findEarliestGradedDate(seriousAdverseEvents);
        	}else{
        		List<AdverseEvent> applicableAdverseEvents = evaluationResult.getAllAeMap().get(aeReportId);
        		updatedDate = AdverseEventReportingPeriod.findEarliestPostSubmissionUpdatedDate(applicableAdverseEvents);
        		gradedDate = AdverseEventReportingPeriod.findEarliestGradedDate(applicableAdverseEvents);
        	}
        		
        	if(updatedDate == null) updatedDate = new Date();
        	if(gradedDate == null) gradedDate = new Date();
        	
        	
    		//all report defs (load them as default)
    		List<ReportDefinition> allReportDefs = applicableReportDefinitions.getReportDefinitions();
    		Map<Integer, ReportTableRow> rowMap = new LinkedHashMap<Integer, ReportTableRow>();
    		
    		
    		ExpeditedAdverseEventReport aeReport = aeReportIndexMap.get(aeReportId);
    		Date baseDate =  gradedDate;
    		
    		List<Report> reportsToAmendList = new ArrayList<Report>();
    		
    		//create a map, consisting of report definitions
    		for(ReportDefinition rd : allReportDefs){
    			if(aeReport != null && aeReport.hasExistingReportsOfSameOrganizationAndType(rd)) {
    				baseDate = updatedDate;
    				reportsToAmendList.addAll(aeReport.findReportsToAmmend(rd));
    				
    			}
    			ReportTableRow row  = ReportTableRow.createReportTableRow(rd, baseDate, ActionType.CREATE);
    			row.setAeReportId(aeReportId);
    			
    			row.setStatus("Not started");
    			row.setOtherStatus("");
    			row.setOtherDue("");
    			row.setGrpStatus("");
    			row.setGrpDue("");
    			
    			rowMap.put(rd.getId(), row);
    		}
    		
    		//for each reports to amend, make sure, we have their group actions set to amend. 
    		for(Report report : reportsToAmendList){
    			ReportTableRow row = rowMap.get(report.getReportDefinition().getId());
    			if(row != null && row.getGrpAction() != ActionType.AMEND){
    				row.setAction(ActionType.AMEND);
    				row.setGrpAction(ActionType.AMEND);
    				row.setStatus("Being amended");
        			row.setGrpStatus("Being amended");
        			row.setGrpDue("Submitted on " + DateUtils.formatDate(report.getSubmittedOn()));
    			}
    		}
    		
    		Set<ReportDefinitionWrapper> createWrappers = evaluationResult.getCreateMap().get(aeReportId);
    		if(createWrappers != null){
    			for(ReportDefinitionWrapper wrapper: createWrappers){
    				ReportTableRow row  = rowMap.get(wrapper.getDef().getId());
    				row.setPreSelected(true);
        			row.setGrpAction(null);
        			row.setOtherAction(null);
    			}
    		}
    		
    		Set<ReportDefinitionWrapper> editWrappers = evaluationResult.getEditMap().get(aeReportId);
    		if(editWrappers != null){
    			for(ReportDefinitionWrapper wrapper: editWrappers){
    				ReportTableRow row  = rowMap.get(wrapper.getDef().getId());
    				row.setPreSelected(true);
    				
    				row.setAction(ActionType.EDIT);
    				row.setGrpAction(ActionType.WITHDRAW);
    				row.setOtherAction(ActionType.WITHDRAW);
    				
    				row.setStatus(wrapper.getStatus());
        			row.setGrpStatus("Being withdrawn");
        			row.setOtherStatus("Being withdrawn");
        			
        			row.setDue(DurationUtils.formatDuration(wrapper.getDueOn().getTime() - new Date().getTime(), wrapper.getDef().getTimeScaleUnitType().getFormat()));
        			row.setGrpDue("");
        			row.setOtherDue("");
    			}
    		}
    		
    		Set<ReportDefinitionWrapper> withdrawWrappers = evaluationResult.getWithdrawalMap().get(aeReportId);
    		if(withdrawWrappers != null){
    			for(ReportDefinitionWrapper wrapper: withdrawWrappers){
    				ReportTableRow row  = rowMap.get(wrapper.getDef().getId());
    				
    				row.setAction(ActionType.EDIT);
    				row.setGrpAction(ActionType.WITHDRAW);
    				row.setOtherAction(ActionType.WITHDRAW);
    				
    				row.setStatus(wrapper.getStatus());
        			row.setGrpStatus("Being withdrawn");
        			row.setOtherStatus("Being withdrawn");
        			
        			row.setDue(DurationUtils.formatDuration(wrapper.getDueOn().getTime() - new Date().getTime(), wrapper.getDef().getTimeScaleUnitType().getFormat()));
        			row.setGrpDue("");
        			row.setOtherDue("");
    			}
    		}
    		
    		Set<ReportDefinitionWrapper> ammendWrappers = evaluationResult.getAmendmentMap().get(aeReportId);
    		if(ammendWrappers != null){
    			for(ReportDefinitionWrapper wrapper: ammendWrappers){
    				ReportTableRow row  = rowMap.get(wrapper.getDef().getId());
    				
    				row.setAction(ActionType.AMEND);
    				row.setGrpAction(ActionType.AMEND);
    				
    				row.setStatus("Being amended");
        			row.setGrpStatus("Being amended");
        			row.setOtherStatus("");
        			
        			row.setGrpDue("Submitted on " + DateUtils.formatDate(wrapper.getSubmittedOn()));
        			row.setOtherDue("");
    			}

    		}
    		
    	
    		
    		applicableReportTableMap.put(aeReportId, new ArrayList<ReportTableRow>(rowMap.values()));
    	}
    }
    
    /**
     * Will return a map, containing the report definition Id as key and the base date (to calculate due date)
     * as value. 
     * 
     * @param aeReportId
     * @return
     */
    public Map<Integer, Date> findBaseDateMap(Integer aeReportId){
    	List<ReportTableRow> applicableReportDefinitionRows = applicableReportTableMap.get(aeReportId);
    	Map<Integer, Date > dateMap = new HashMap<Integer, Date>();
    	for(ReportTableRow row : applicableReportDefinitionRows){
    		dateMap.put(row.getReportDefinition().getId(), row.getBaseDate());
    	}
    	return dateMap;
    }
    
    /**
     * Will populate the reportIds to get amended, in the review result.
     */
    public void populateReportsToAmend(){
    	ExpeditedAdverseEventReport aeReport = aeReportIndexMap.get(reviewResult.getAeReportId());
    	if(aeReport != null){
    		List<Report> completedReports = aeReport.listReportsHavingStatus(ReportStatus.COMPLETED);
    		for(ReportDefinition rd : reviewResult.getAmendList()){
    			for(Report report : completedReports){
    				if(report.getReportDefinition().getId().equals(rd.getId())){
    					reviewResult.getReportsToAmmendList().add(report);
    				}
    			}
    		}
    	}
    }
    
    /**
     * This method will populate the report-Ids to be withdrawn.
     */
    public void populateReportsToWithdraw(){
    	ExpeditedAdverseEventReport aeReport = aeReportIndexMap.get(reviewResult.getAeReportId());
    	if(aeReport != null){
    		List<Report> activeReports = aeReport.getActiveReports();
    		for(ReportDefinition rd : reviewResult.getWithdrawList()){
    			for(Report report : activeReports){
    				if(report.getReportDefinition().getId().equals(rd.getId())){
    					reviewResult.getReportsToWithdraw().add(report);
    				}
    			}
    		}
    	}
    }
    
    /**
     * This method will populate the reports to un-amend.
     */
    public void populateReportsToUnAmend(){
    	ExpeditedAdverseEventReport aeReport = aeReportIndexMap.get(reviewResult.getAeReportId());
    	List<ReportDefinition> tentativeList = new ArrayList<ReportDefinition>();
    	List<ReportDefinition> potentialList = new ArrayList<ReportDefinition>();
    	
    	if(aeReport != null){
    		
    		//throw away, if this one is getting replaced
    		for(ReportDefinition rdWithdraw : reviewResult.getWithdrawList()){
    			boolean potentialCandidate = true;
    			for(ReportDefinition rdCreate : reviewResult.getCreateList()){
    				if(rdCreate.isOfSameReportTypeAndOrganization(rdWithdraw)){
    					potentialCandidate = false;
    					break;
    				}
    			}
    			
    			if(potentialCandidate){
    				//may be chance for unamend.
    				tentativeList.add(rdWithdraw);
    			}
    			
    		}//rdWithdraw
    		
    		//check if the potential ones are the only active reports, of the group.
    		List<Report> activeReports = aeReport.getActiveReports();
    		for(ReportDefinition rd : tentativeList){
    			boolean hasOtherFromSameOrg = true;
    			for(Report report :activeReports){
        			if(rd.getId().equals(report.getId())){
        				hasOtherFromSameOrg = false;
        				continue; //same so ignore (withdrawing exiting rd).
        			}
        			
        			if(report.getReportDefinition().isOfSameReportTypeAndOrganization(rd)){
        				hasOtherFromSameOrg = true;
        			}
        		}
    		
    			if(!hasOtherFromSameOrg){
    				potentialList.add(rd);
    			}
    			
    		}//potentialCandidateList
    		
    		
    		//okay, now find the report associated to each. 
    		for(ReportDefinition rd : potentialList){
    			Report report  = aeReport.findLastAmendedReport(rd);
    			if(report != null){
    				reviewResult.getReportsToUnAmendList().add(report);
    			}
    		}
    		
    	}//aeReport 
    }

    public String getVerbatim() {
        return verbatim;
    }

    public void setVerbatim(String verbatim) {
        this.verbatim = verbatim;
    }
}
