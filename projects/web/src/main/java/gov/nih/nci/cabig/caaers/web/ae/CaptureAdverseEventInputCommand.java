package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.dao.AdverseEventReportingPeriodDao;
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
import gov.nih.nci.cabig.caaers.domain.LabLoad;
import gov.nih.nci.cabig.caaers.domain.Outcome;
import gov.nih.nci.cabig.caaers.domain.OutcomeType;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.Term;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.domain.workflow.ReportingPeriodReviewComment;
import gov.nih.nci.cabig.caaers.service.EvaluationService;
import gov.nih.nci.cabig.caaers.tools.configuration.Configuration;
import gov.nih.nci.cabig.caaers.utils.IndexFixedList;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.Transient;

public class CaptureAdverseEventInputCommand implements	AdverseEventInputCommand {
	
	private StudyParticipantAssignment assignment;
	private StudyParticipantAssignmentDao assignmentDao;
	private ReportDefinitionDao reportDefinitionDao;
	
	private Participant participant; 
	private Study study;
	private EvaluationService evaluationService;
	protected AdverseEventReportingPeriod adverseEventReportingPeriod;
	protected AdverseEventReportingPeriodDao adverseEventReportingPeriodDao;
	private StudyDao studyDao;
	
	private List<CtcCategory> ctcCategories;
	private Integer primaryAdverseEventId;
	
	private IndexFixedList<AdverseEvent> adverseEvents;
	
	// Added for Post processing in Confirmation page
	private List<ReportDefinition> allReportDefinitions;
	private Map<ReportDefinition, List<AdverseEvent>> requiredReportDefinitionsMap;

	private Map<Integer, String> reportStatusMap;
	private Map<Integer, Boolean> requiredReportDefinitionIndicatorMap;
	
	private Map<Integer, Boolean> reportDefinitionMap;//will store user selection
	
	//this map is used for internal purpouses
	private Map<Integer, ReportDefinition> reportDefinitionIndexMap;
	
	private Map<Integer, Boolean> selectedAesMap;
	
	private Ctc ctcVersion;
	
	private Set<AdverseEvent> seriousAdverseEvents;
	
	private boolean workflowEnabled = false;
	
	public CaptureAdverseEventInputCommand(){
	}
	
	public CaptureAdverseEventInputCommand(AdverseEventReportingPeriodDao adverseEventReportingPeriodDao, 
				StudyParticipantAssignmentDao assignmentDao, EvaluationService evaluationService, ReportDefinitionDao reportDefinitionDao, StudyDao studyDao){
		this.adverseEventReportingPeriodDao = adverseEventReportingPeriodDao;
		this.assignmentDao = assignmentDao;
		this.evaluationService = evaluationService;
		this.reportDefinitionDao = reportDefinitionDao;
		this.studyDao = studyDao;
		
		
		this.selectedAesMap = new HashMap<Integer, Boolean>();
        this.allReportDefinitions = new ArrayList<ReportDefinition>();
        this.requiredReportDefinitionsMap = new HashMap<ReportDefinition, List<AdverseEvent>>();
        this.reportStatusMap = new HashMap<Integer, String>();
        this.requiredReportDefinitionIndicatorMap = new HashMap<Integer, Boolean>();
        this.reportDefinitionMap = new HashMap<Integer, Boolean>();
        this.reportDefinitionIndexMap = new HashMap<Integer, ReportDefinition>();
        
        
        this.seriousAdverseEvents = new TreeSet<AdverseEvent>(new Comparator<AdverseEvent>(){
        	public int compare(AdverseEvent o1, AdverseEvent o2) {
        		if(o1 == null && o2 == null) return 0;
        		if(o1 == null || o1.getGrade() == null) return 1;
        		if(o2 == null || o2.getGrade() == null) return -1;
        		
        		if(o1.getGrade().getCode() > o2.getGrade().getCode()) return -1;
        		if(o1.getGrade().getCode() < o2.getGrade().getCode()) return 1;
        		
        		return 0;
        		
        	}
        });
	}
	
	/**
	 * This method will check if the study selected is a DCP sponsored study and is AdEERS submittable.
	 * @return
	 */
	public boolean isDCPNonAdeersStudy(){
		if(study == null) return false;
		return (!study.getAdeersReporting()) && study.getPrimaryFundingSponsorOrganization().getNciInstituteCode().equals("DCP");
	}
	
	public void save() {
//		AdverseEventReportingPeriod mergedReportingPeriod;
//	
//		if(this.getAdverseEventReportingPeriod() != null){
//			mergedReportingPeriod = adverseEventReportingPeriodDao.merge(this.getAdverseEventReportingPeriod());
//			this.setAdverseEventReportingPeriod(mergedReportingPeriod);
//		}
		if(this.getAdverseEventReportingPeriod() != null) adverseEventReportingPeriodDao.save(this.getAdverseEventReportingPeriod());
	}
	
	public void reassociate(){
		//reassociate all report definitions
		if(allReportDefinitions != null)
		for(ReportDefinition repDef : allReportDefinitions){
			reportDefinitionDao.reassociate(repDef);
		}
		studyDao.lock(study);
		if(this.adverseEventReportingPeriod != null && this.adverseEventReportingPeriod.getId() != null){
			adverseEventReportingPeriodDao.reassociate(this.adverseEventReportingPeriod);
		}
		
	}



    /**
     * This method will refresh the StudyParticipantAssignment and updates the {@link AdverseEventReportingPeriod}.
     */
    public void refreshAssignment(Integer reportingPeriodId){
    	//reload assignmet
    	assignmentDao.refresh(getAssignment());
    	refreshReportingPeriod(reportingPeriodId);
    }
    
    
    public void refreshReportingPeriod(Integer reportingPeriodId){
    	studyDao.lock(study);
    	assignmentDao.reassociate(getAssignment());
    	//reset the adverse event report in the command
    	setAdverseEventReportingPeriod(null);
    	for(AdverseEventReportingPeriod reportingPeriod : assignment.getReportingPeriods()){
    		if(reportingPeriod.getId().equals(reportingPeriodId)){
    			setAdverseEventReportingPeriod(reportingPeriod);
    			break;
    		}
    	}
    }

    
    public void clearSession(){
    	assignmentDao.clearSession();
    }
    
    public void evictUnwantedObjects(){
    	assignmentDao.evict(getAssignment());
    }
  
    /**
     * This method will take care of initializing the lazy associations
     * This method will take care of
     *  - Updating the index fixed list for AdverseEvents, associated to the reporting period 
     *  - initializing the lazy associations
     */
    public void initialize(){
    	adverseEvents = new IndexFixedList<AdverseEvent>(new ArrayList<AdverseEvent>());
    	if(adverseEventReportingPeriod != null){
    		adverseEvents = new IndexFixedList<AdverseEvent>(adverseEventReportingPeriod.getAdverseEvents());
    		Study study = adverseEventReportingPeriod.getStudy();
			if(study.getStudyOrganizations() != null) study.getStudyOrganizations().size();
			if(study.getExpectedAECtcTerms() != null)	 study.getExpectedAECtcTerms().size();
			boolean isCTCStudy = study.getAeTerminology().getTerm() == Term.CTC;
			if(isCTCStudy){
				for(AdverseEvent ae : getAdverseEvents()){
					ae.getAdverseEventTerm().isOtherRequired();
                    ae.getAdverseEventCtcTerm().getCtcTerm().isOtherRequired();
                    ae.getAdverseEventCtcTerm().getCtcTerm().getContextualGrades();
				}
			}
			this.adverseEventReportingPeriod.getAdverseEvents().size();
			this.adverseEventReportingPeriod.getAeReports();
			for(ExpeditedAdverseEventReport aeReport : this.adverseEventReportingPeriod.getAeReports()){
				aeReport.getAllSponsorReportsCompleted();
				aeReport.getHasAmendableReport();
				aeReport.getHasSubmittedReport();
				aeReport.getNumberOfAes();
			}
			List<ReportingPeriodReviewComment> reviewCommentList = this.adverseEventReportingPeriod.getReviewComments();
			if(reviewCommentList != null)
				this.adverseEventReportingPeriod.getReviewComments().size();
			if(this.assignment != null)
				this.assignment.getParticipant().getIdentifiers();
		}
    }
    
//    public void initializeObjectsInCommand(){
//    	if(study == null) return;
//    	 if(study.getExpectedAECtcTerms() != null)	 study.getExpectedAECtcTerms().size();
//         boolean isCTCStudy = study.getAeTerminology().getTerm() == Term.CTC;
//         if (isCTCStudy){
//        	 List<AdverseEvent> aes = getAdverseEvents();
//        	 if(aes != null){
//        		 for (AdverseEvent ae: aes) {
//                     
//                 }
//        	 }
//         }
//             
//    }
    
    /**
     * This method will find all avaliable report definitions for all the StudyOrganizations. 
     */
    public List<ReportDefinition> findAllReportDefintionNames(){
    	// evalutate available report definitions per session.
    	if(this.allReportDefinitions.isEmpty()){
    		this.allReportDefinitions.addAll(evaluationService.applicableReportDefinitions(this.assignment));
    		//upate the index map
    		for(ReportDefinition repDef : allReportDefinitions){
    			reportDefinitionIndexMap.put(repDef.getId(), repDef);
    		}
    	}
    	return this.allReportDefinitions;
    }
    
    public List<ReportDefinition> findRequiredReportDefinitions(){
    	//if already available return that, as we will take care of clearing it when we quit this tab.
    	if(requiredReportDefinitionsMap.isEmpty() && !adverseEventReportingPeriod.isBaselineReportingType()){
    		this.requiredReportDefinitionsMap = evaluationService.findRequiredReportDefinitions(this.adverseEventReportingPeriod);
    		//refresh the serious AE list here
    		refreshSeriousAdverseEvents();
    	}
    	return new ArrayList<ReportDefinition>(requiredReportDefinitionsMap.keySet());
    }


    /**
     * This method will return the ReportDefinition which are selected by user
     * page.
     */
    public List<ReportDefinition> getSelectedReportDefinitions() {
        List<ReportDefinition> reportDefs = new ArrayList<ReportDefinition>();
        
        for (Map.Entry<Integer, Boolean> entry : reportDefinitionMap.entrySet()) {
            if (entry.getValue() != null && entry.getValue()) reportDefs.add(reportDefinitionIndexMap.get(entry.getKey()));
        }
        return reportDefs;
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
	   for(ReportDefinition rpDef : requiredReportDefinitionsMap.keySet()){
		   reportDefinitionMap.put(rpDef.getId(), true);
	   }
   }
    
    /**
     * This method will return the adverse events that are selected (checked)
     * @return
     */
    public List<AdverseEvent> findSelectedAdverseEvents(){
    	List<AdverseEvent> adverseEvents = new ArrayList<AdverseEvent>();
    	for(AdverseEvent ae : adverseEventReportingPeriod.getAdverseEvents()){
    		Boolean value = selectedAesMap.get(ae.getId());
    		if(value != null && value){
    			adverseEvents.add(ae);
    		}
    	}
    	return adverseEvents;
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
		for(ReportDefinition rpDef : this.requiredReportDefinitionsMap.keySet()){
			requiredReportDefinitionIndicatorMap.put(rpDef.getId(), true);
		}
	}
	
	
	/**
	 * All AEs associated with <b>selectedReportDefinitions</b> must be selected.
	 * 
	 * This method populates the SelectedAesMap member of the command object. The adverse events that were serious on triggering of the rules are set to true in this
	 * Map. All the adverse events in the reporting period are keys in this map.
	 */
	public void refreshSelectedAesMap(){
		selectedAesMap.clear();
		//initialize all selected aes to false
		for(AdverseEvent ae: adverseEventReportingPeriod.getAdverseEvents()){
			Integer id = ae.getId();
			selectedAesMap.put(id, Boolean.FALSE);
		}
		for(AdverseEvent ae: adverseEventReportingPeriod.getReportableAdverseEvents()){
			ae.setRequiresReporting(false);
		}
		//reset the ones that are available below with true
		for(Map.Entry<ReportDefinition, List<AdverseEvent>> entry : requiredReportDefinitionsMap.entrySet()){
			for(AdverseEvent ae : entry.getValue()){
				selectedAesMap.put(ae.getId(), true);
				ae.setRequiresReporting(Boolean.TRUE);
			}
		}
	}
    
	/**
	 * The list of serious adverse events is refreshed here.
	 */
	public void refreshSeriousAdverseEvents(){
		seriousAdverseEvents.clear();
		for(List<AdverseEvent> aes : requiredReportDefinitionsMap.values()){
			for(AdverseEvent ae : aes){
				seriousAdverseEvents.add(ae);
			}
		}
	}
	
	/**
	 * Find primary adverse event.
	 */
	//public void findPrimaryAdverseEvent(){
	//	if(adverseEventReportingPeriod.getAeReports().size() == 0)
	//		if(!seriousAdverseEvents.isEmpty()) this.primaryAdverseEventId = new ArrayList<AdverseEvent>(seriousAdverseEvents).get(0).getId();
	//}
	
	
	/**
	 * Updates the requires reporting indicator of this AE.
	 * @param ae
	 */
	public void updateRequiresReportingFlag(AdverseEvent ae){
		if(seriousAdverseEvents == null || seriousAdverseEvents.isEmpty())return;
		ae.setRequiresReporting(seriousAdverseEvents.contains(ae));
		
	}
	
	/**
	 * This method will set the seriousness (outcome) on every adverse event, by reading the non-hospitalization and non-death outcome indicators,
	 * preference is given to the first satisfied one. (Note:- In Expedited flow, the user may select multiple outcome(s). 
	 */
	public void initializeOutcome(){
		if(this.adverseEvents == null) return;
		
		for(AdverseEvent ae : adverseEvents.getInternalList()){
			ae.setSerious(null);
			for(Outcome o: ae.getOutcomes()){
				if(o.getOutcomeType().equals(OutcomeType.HOSPITALIZATION) || o.getOutcomeType().equals(OutcomeType.DEATH)) continue;
				ae.setSerious(o.getOutcomeType());
				break;
			}
		}
	}
	
	/**
	 * This method will synchronize the outcomes list associated with the adverse event. 
	 *   If grade is 5, the outcome of type death is added, else removed(if present)
	 *   If hospitalization is 'yes', the outcome of type hospitalization is added, else removed (if present)
	 *   If the serious(outcome) not available in the outcomes list, it will be added.
	 *   Remove all the other outcomes present in the list (means user deselected a previously selected one)
	 */
	public void synchronizeOutcome(){
		for(AdverseEvent ae : adverseEvents.getInternalList()){
			//if grade is 5 make sure we have OutcomeType.DEATH
			if(ae.getGrade() != null && ae.getGrade().equals(Grade.DEATH)){
				if(!isOutcomePresent(OutcomeType.DEATH, ae.getOutcomes())){
					Outcome newOutcome = new Outcome();
					newOutcome.setOutcomeType(OutcomeType.DEATH);
					ae.addOutcome(newOutcome);
				}
			}else{
				removeOutcomeIfPresent(OutcomeType.DEATH, ae.getOutcomes());
			}
			
			//if hospitalized, make sure we have OutcomeType.HOSPITALIZATION 
			if(ae.getHospitalization() != null && ae.getHospitalization().equals(Hospitalization.YES)){
				if(!isOutcomePresent(OutcomeType.HOSPITALIZATION, ae.getOutcomes())){
					Outcome newOutcome = new  Outcome();
					newOutcome.setOutcomeType(OutcomeType.HOSPITALIZATION);
					ae.addOutcome(newOutcome);
				}
			}else {
				removeOutcomeIfPresent(OutcomeType.HOSPITALIZATION, ae.getOutcomes());
			}
			
			//update the selected seriousness outcome 
			if(ae.getSerious() != null){
				if(!isOutcomePresent(ae.getSerious(), ae.getOutcomes())){
					Outcome newOutcome = new Outcome();
					newOutcome.setOutcomeType(ae.getSerious());
					ae.addOutcome(newOutcome);
				}
			}
			
			//remove the rest of the outcomes
			for(OutcomeType outcomeType : OutcomeType.values()){
				if(outcomeType.equals(OutcomeType.HOSPITALIZATION) || outcomeType.equals(OutcomeType.DEATH)) continue;
				if(ae.getSerious() != null && outcomeType.equals(ae.getSerious()) ) continue;
				removeOutcomeIfPresent(outcomeType, ae.getOutcomes());
			}
			
		}
	}
	
	/**
	 * Returns true, if an Outcome of a specific type is present in the list of outcomes
	 */
	private boolean isOutcomePresent(OutcomeType type, List<Outcome> outcomes){
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
	
    public void setSelectedAesMap(Map<Integer, Boolean> selectedAesMap) {
		this.selectedAesMap = selectedAesMap;
	}
    
    
    public Map<Integer, Boolean> getSelectedAesMap(){
   		return selectedAesMap;
    }
    
    public IndexFixedList<AdverseEvent> getAdverseEvents() {
		return adverseEvents;
	}
    public void setAdverseEvents(IndexFixedList<AdverseEvent> adverseEvents) {
		this.adverseEvents = adverseEvents;
	}
    /**
     * Returns all the {@link ReportDefinition} available to this AE
     */
    public List<ReportDefinition> getAllReportDefinitions() {
        return allReportDefinitions;
    }

    public void setAllReportDefinitions(List<ReportDefinition> allReportDefinitions) {
        this.allReportDefinitions = allReportDefinitions;
    }
    
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
	
	@Transient
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
    
    public Map<ReportDefinition, List<AdverseEvent>> getRequiredReportDefinitionsMap() {
		return requiredReportDefinitionsMap;
	}
    public Map<Integer, String> getReportStatusMap() {
		return reportStatusMap;
	}
    public Map<Integer, Boolean> getRequiredReportDefinitionIndicatorMap() {
		return requiredReportDefinitionIndicatorMap;
	}
    public Map<Integer, Boolean> getReportDefinitionMap() {
		return reportDefinitionMap;
	}
    public void setReportDefinitionMap(Map<Integer, Boolean> reportDefinitionMap) {
		this.reportDefinitionMap = reportDefinitionMap;
	}

	public Integer getPrimaryAdverseEventId() {
		return primaryAdverseEventId;
	}

	public void setPrimaryAdverseEventId(Integer primaryAdverseEventId) {
		this.primaryAdverseEventId = primaryAdverseEventId;
	}
    
    public List<AdverseEvent> getSelectedAesList() {
		List selectedAesList = new ArrayList<AdverseEvent>();
    	Map<Integer, AdverseEvent> aeObjectMap = new HashMap<Integer, AdverseEvent>();
    	for(AdverseEvent ae: adverseEvents){
    		if(!aeObjectMap.containsKey(ae.getId()))
    			aeObjectMap.put(ae.getId(), ae);
    	}
    	for(Integer id: getSelectedAesMap().keySet()){
			if(getSelectedAesMap().get(id).equals(Boolean.TRUE))
				selectedAesList.add(aeObjectMap.get(id));
		}
    	return selectedAesList;
	}
    
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
}
