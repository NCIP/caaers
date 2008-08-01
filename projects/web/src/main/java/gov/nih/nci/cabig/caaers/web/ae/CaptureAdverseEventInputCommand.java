package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.dao.AdverseEventReportingPeriodDao;
import gov.nih.nci.cabig.caaers.dao.StudyParticipantAssignmentDao;
import gov.nih.nci.cabig.caaers.dao.report.ReportDefinitionDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.Ctc;
import gov.nih.nci.cabig.caaers.domain.CtcCategory;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.ReportStatus;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.service.EvaluationService;
import gov.nih.nci.cabig.caaers.utils.IndexFixedList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.Transient;

import org.apache.commons.collections15.CollectionUtils;
import org.apache.commons.collections15.ListUtils;

public class CaptureAdverseEventInputCommand implements	AdverseEventInputCommand {
	
	private StudyParticipantAssignment assignment;
	private StudyParticipantAssignmentDao assignmentDao;
	private ReportDefinitionDao reportDefinitionDao;
	
	private Participant participant; 
	private Study study;
	private EvaluationService evaluationService;
	private AdverseEventReportingPeriod adverseEventReportingPeriod;
	private AdverseEventReportingPeriodDao adverseEventReportingPeriodDao;
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
		
	
	public CaptureAdverseEventInputCommand(AdverseEventReportingPeriodDao adverseEventReportingPeriodDao, 
				StudyParticipantAssignmentDao assignmentDao, EvaluationService evaluationService, ReportDefinitionDao reportDefinitionDao){
		this.adverseEventReportingPeriodDao = adverseEventReportingPeriodDao;
		this.assignmentDao = assignmentDao;
		this.evaluationService = evaluationService;
		this.reportDefinitionDao = reportDefinitionDao;
		
		
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
	
	public void save() {
		AdverseEventReportingPeriod mergedReportingPeriod;
	
		if(this.getAdverseEventReportingPeriod() != null){
			mergedReportingPeriod = adverseEventReportingPeriodDao.merge(this.getAdverseEventReportingPeriod());
			this.setAdverseEventReportingPeriod(mergedReportingPeriod);
		}
	}
	
	public void reassociate(){

		//reassociate all report definitions
		if(allReportDefinitions != null)
		for(ReportDefinition repDef : allReportDefinitions){
			reportDefinitionDao.reassociate(repDef);
		}
		
		if(this.adverseEventReportingPeriod != null && this.adverseEventReportingPeriod.getId() != null)
			adverseEventReportingPeriodDao.reassociate(this.adverseEventReportingPeriod);
		
	}



    /**
     * This method will refresh the StudyParticipantAssignment and updates the {@link AdverseEventReportingPeriod}.
     */
    public void refreshAssignment(Integer reportingPeriodId){
    	//reload assignmet
    	assignmentDao.refresh(assignment);
    	
    	//reset the adverse event report in the command
    	setAdverseEventReportingPeriod(null);
    	for(AdverseEventReportingPeriod reportingPeriod : assignment.getReportingPeriods()){
    		if(reportingPeriod.getId().equals(reportingPeriodId)){
    			setAdverseEventReportingPeriod(reportingPeriod);
    			break;
    		}
    	}
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
    		
			this.adverseEventReportingPeriod.getStudy().getStudyOrganizations().size();
			this.adverseEventReportingPeriod.getAdverseEvents().size();
			this.adverseEventReportingPeriod.getAeReport();
		}
    }
    
 
    
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
    	//if already avaliable return that, as we will take care of clearing it when we quit this tab.
    	if(requiredReportDefinitionsMap.isEmpty()){
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
     * This method will list the {@link ReportDefinition}s that are already associated to the {@link ExpeditedAdverseEventReport}
     */
    public Set<ReportDefinition> getInstantiatedReportDefinitions() {
        Set<ReportDefinition> reportDefs = new HashSet<ReportDefinition>();
        ExpeditedAdverseEventReport aeReport = adverseEventReportingPeriod.getAeReport();
        
        if(aeReport == null || aeReport.getReports() == null)	return reportDefs;
        
        for (Report report : adverseEventReportingPeriod.getAeReport().getReports()) {
            if (!report.getStatus().equals(ReportStatus.WITHDRAWN)) reportDefs.add(report.getReportDefinition());
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
	   
	   //all reports selected in reporting Expedited Report should be selected aswell. 
	   if(adverseEventReportingPeriod.getAeReport() != null){
		  for(ReportDefinition rpDef : getInstantiatedReportDefinitions()){
			  reportDefinitionMap.put(rpDef.getId(), true);
		  }
	   }else {
		   //rules engine said reports should be selected
		   for(ReportDefinition rpDef : requiredReportDefinitionsMap.keySet()){
			   reportDefinitionMap.put(rpDef.getId(), true);
		   }
	   }
	   
   }
    
    /**
     * This method will return the newly selected {@link ReportDefinition}s.
     * Note : - [Selected Report Definitions] - [Already instatiated Report Definitions]
     * @param command 
     * @return
     */
    public Collection<ReportDefinition> findNewlySelectedReportDefinitions() {
    	List<ReportDefinition> selectedReportDefs = getSelectedReportDefinitions();
    	Set<ReportDefinition> instantiatedReportDefs = getInstantiatedReportDefinitions();
    	return CollectionUtils.subtract(selectedReportDefs, instantiatedReportDefs);
    }
    
    /**
     * This method will return the report definitions that are unselected 
     * @return
     */
    public Collection<ReportDefinition> findUnselectedReportDefinitions(){
    	Set<ReportDefinition> instantiatedReportDefs = getInstantiatedReportDefinitions();
    	List<ReportDefinition> selectedReportDefs = getSelectedReportDefinitions();
    	return CollectionUtils.subtract(instantiatedReportDefs, selectedReportDefs);
    }
    
    /**
     * This method will return the selected adverse events
     * @return
     */
    public List<AdverseEvent> findNewlySelectedAdverseEvents(){
    	List<AdverseEvent> selectedAdverseEvents = findSelectedAdverseEvents();
    	if(adverseEventReportingPeriod.getAeReport() == null){
    		return selectedAdverseEvents;
    	}else {
    		return ListUtils.subtract(selectedAdverseEvents, adverseEventReportingPeriod.getAeReport().getAdverseEvents());
    	}
    }
    
    /**
     * This method will return all the report definitions that are unselected
     * @return
     */
    public List<AdverseEvent> findUnselectedAdverseEvents(){
    	List<AdverseEvent> unselectedEvents = new ArrayList<AdverseEvent>();
    	if(adverseEventReportingPeriod.getAeReport() != null){
    		for(AdverseEvent ae : adverseEventReportingPeriod.getAeReport().getAdverseEvents()){
    			Boolean value = selectedAesMap.get(ae.getId());
    			if(value == null || !value ){
    				unselectedEvents.add(ae);
    			}
    		}
    	}
    	return unselectedEvents;	
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
    	if(this.adverseEventReportingPeriod.getAeReport() == null) return;
    	
    	//initialize every thing with empty
    	for(ReportDefinition rpDef : allReportDefinitions){
    		reportStatusMap.put(rpDef.getId(), "");
    	}
    	//update the once already instantiated , with correct status.
    	List<Report> reports = adverseEventReportingPeriod.getAeReport().getNonWithdrawnReports();
    	for(Report report : reports){
    		reportStatusMap.put(report.getReportDefinition().getId(), report.getLastVersion().getStatusAsString());
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
	 * This method populates the SelectedAesMap member of the command object. The Aes that were selected when this page was accessed
	 * the last time(through Report object) and the adverse events that were serious on triggering of the rules are set to true in this
	 * Map. All the adverse events in the reporting period are keys in this map.
	 * @param command
	 */
	public void refreshSelectedAesMap(){
		selectedAesMap.clear();
		//initialize all selected aes to false
		for(AdverseEvent ae: adverseEventReportingPeriod.getAdverseEvents()){
			Integer id = ae.getId();
			selectedAesMap.put(id, Boolean.FALSE);
			ae.setSerious(Boolean.FALSE);
		}
		if(adverseEventReportingPeriod.getAeReport() != null){
			for(AdverseEvent ae: adverseEventReportingPeriod.getAeReport().getAdverseEvents()){
				Integer id = ae.getId();
				selectedAesMap.put(id, Boolean.TRUE);
			}
			for(Map.Entry<ReportDefinition, List<AdverseEvent>> entry : requiredReportDefinitionsMap.entrySet()){
				for(AdverseEvent ae : entry.getValue()){
					ae.setSerious(Boolean.TRUE);
				}
			}
		}else {
			//reset the ones that are available below with true
			for(Map.Entry<ReportDefinition, List<AdverseEvent>> entry : requiredReportDefinitionsMap.entrySet()){
				for(AdverseEvent ae : entry.getValue()){
					selectedAesMap.put(ae.getId(), true);
					ae.setSerious(Boolean.TRUE);
				}
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
	public void findPrimaryAdverseEvent(){
		if(adverseEventReportingPeriod.getAeReport() == null){
			if(!seriousAdverseEvents.isEmpty()) this.primaryAdverseEventId = new ArrayList<AdverseEvent>(seriousAdverseEvents).get(0).getId();
		}else {
			this.primaryAdverseEventId = adverseEventReportingPeriod.getAeReport().getAdverseEvents().get(0).getId();
		}
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
    
    
}
