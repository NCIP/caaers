package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.dao.AdverseEventReportingPeriodDao;
import gov.nih.nci.cabig.caaers.dao.StudyParticipantAssignmentDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.Ctc;
import gov.nih.nci.cabig.caaers.domain.CtcCategory;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.ReportStatus;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.service.EvaluationService;
import gov.nih.nci.cabig.caaers.utils.IndexFixedList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Transient;

public class CaptureAdverseEventInputCommand implements	AdverseEventInputCommand {
	
	private StudyParticipantAssignment assignment;
	private StudyParticipantAssignmentDao assignmentDao;
	private Participant participant; 
	private Study study;
	private EvaluationService evaluationService;
	private AdverseEventReportingPeriod adverseEventReportingPeriod;
	private AdverseEventReportingPeriodDao adverseEventReportingPeriodDao;
	private List<CtcCategory> ctcCategories;
	private Map<Integer, Boolean> selectedAesMap;
	
	private IndexFixedList<AdverseEvent> adverseEvents;
	
	// Added for Reporting Page.
	private List<ReportDefinition> allReportDefinitions;
	private List<ReportDefinition> requiredReportDefinitions;
	private Map<ReportDefinition, Boolean> optionalReportDefinitionsMap;
	
	

	// Need to verify..
	// Added to make the aeTermQuery.tag work.
	//this method is added to satisfy the UI requirements, so to be moved to command classs

	private Ctc ctcVersion;
	
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
	
	// Till here.
	
	
	public CaptureAdverseEventInputCommand(AdverseEventReportingPeriodDao adverseEventReportingPeriodDao, 
				StudyParticipantAssignmentDao assignmentDao, EvaluationService evaluationService){
		this.adverseEventReportingPeriodDao = adverseEventReportingPeriodDao;
		this.assignmentDao = assignmentDao;
		this.evaluationService = evaluationService;
		this.selectedAesMap = new HashMap<Integer, Boolean>();
		this.optionalReportDefinitionsMap = new LinkedHashMap<ReportDefinition, Boolean>();
        this.requiredReportDefinitions = new ArrayList<ReportDefinition>();
	}
	
	public void save() {
		AdverseEventReportingPeriod mergedReportingPeriod;
		if(this.getAdverseEventReportingPeriod() != null){
			mergedReportingPeriod = adverseEventReportingPeriodDao.merge(this.getAdverseEventReportingPeriod());
			this.setAdverseEventReportingPeriod(mergedReportingPeriod);
		}
	}
	
	public void reassociate(){
		if(this.adverseEventReportingPeriod != null)
			adverseEventReportingPeriodDao.reassociate(this.adverseEventReportingPeriod);
	}
	
	public Map<ReportDefinition, List<AdverseEvent>> applyRules(){
		// check if the event reported is an SAE.
		Map<ReportDefinition, List<AdverseEvent>> repDefnIdToAeListMap = new HashMap<ReportDefinition, List<AdverseEvent>>();
		List<ReportDefinition> reportDefs = new ArrayList<ReportDefinition>();
		for(AdverseEvent ae: this.adverseEventReportingPeriod.getAdverseEvents()){
			List<AdverseEvent> aeList = new ArrayList<AdverseEvent>();
			aeList.add(ae);
			reportDefs = evaluationService.findRequiredReportDefinitions(null, aeList, this.getAdverseEventReportingPeriod().getStudy());
			for(ReportDefinition reportDefn: reportDefs){
				if(repDefnIdToAeListMap.containsKey(reportDefn)){
					Map<AdverseEvent, Integer> aeMap = new HashMap<AdverseEvent, Integer>();
					for(AdverseEvent aEvent: repDefnIdToAeListMap.get(reportDefn)){
						if(!aeMap.containsKey(aEvent))
							aeMap.put(aEvent, 1);
					}
					if(!aeMap.containsKey(ae))
						repDefnIdToAeListMap.get(reportDefn).add(ae);
				}else{
					repDefnIdToAeListMap.put(reportDefn, aeList);
				}
			}
		}
		
		// Now we will filter out the amenable ReportDefinitions. 
		// Prepare the Map needed for filterAmenableReportDefinitions method in EvaluationServiceImpl
		Map<String, List<String>> repDefinitionNamesMap = new HashMap<String,List<String>>();
		List<String> repDefnNames = new ArrayList<String>();
		for(ReportDefinition rDef: repDefnIdToAeListMap.keySet()){
			if(rDef != null)
				repDefnNames.add(rDef.getName());
		}
		repDefinitionNamesMap.put("RepDefnNames", repDefnNames);
		
		// Call filterAmenableReportDefinitions in EvaluationServiceImpl passing repDefinitionNamesMap
		// The List returned is a list of ReportDefinitions with the earliest amenable reportDefinition
		List<ReportDefinition> amenableFilterRepDefn = evaluationService.filterAmenableReportDefinitions(repDefinitionNamesMap);
		Map<ReportDefinition, List<AdverseEvent>> filteredRepDefnToAeMap = filterAmenableReportDefinition(repDefnIdToAeListMap, 
				amenableFilterRepDefn);
		System.out.println("Sameer : repDefnIdToAeListMap = " + repDefnIdToAeListMap);
		return repDefnIdToAeListMap;
	}
	
	/**
	 * Filter the ReportDefinition - Ae Map so that only the earliest Amendabe ReportDefinition is retained in the map.
	 * 
	 * @param map, the initial ReportDefinition to AdverseEvent List Map.
	 * @param repDefnList, the list containing the ReportDefinition to be retained.
	 * @return map, the final ReportDefinition to AdverseEvent List Map which has only one (earliest) amendable ReportDefinition.
	 */
	public Map<ReportDefinition, List<AdverseEvent>>filterAmenableReportDefinition(Map<ReportDefinition, List<AdverseEvent>> map 
						, List<ReportDefinition> repDefnList){
		Map<ReportDefinition, Boolean> retainedDefns = new HashMap<ReportDefinition, Boolean>();
		for(ReportDefinition rd: repDefnList){
			retainedDefns.put(rd, Boolean.TRUE);
		}
		// Create a list of AdverseEvents that will be migrated to the only amenable reportDefinition retained in the result map
		List<AdverseEvent> aeList = new ArrayList<AdverseEvent>();
		for(ReportDefinition rd: map.keySet()){
			if(!retainedDefns.containsKey(rd)){
				for(AdverseEvent ae: map.get(rd))
					aeList.add(ae);
				map.remove(rd);  // The ReportDefinition is removed once its aes are added to the aeList to be migrated to 
								 // the retained amenable Report Definition.
			}
		}
		
		// Now the adverseEvents in aeList are appended to the list of adverseEvents associated to the only amenable ReportDefinition
		// in the filteredMap 
		Map<AdverseEvent, Boolean> existingAeMap = new HashMap<AdverseEvent, Boolean>();
		for(ReportDefinition rd: map.keySet()){
			if(rd.getAmendable()){
				for(AdverseEvent ae: map.get(rd))
					existingAeMap.put(ae, Boolean.TRUE);
				// Now add the AdverseEvents from aeList to this list
				for(AdverseEvent ae: aeList)
					if(!existingAeMap.containsKey(ae))
						map.get(rd).add(ae);
			}
		}
		return map;
	}
	
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
		if(ctcCategories == null)
			setCtcCategories(adverseEventReportingPeriod.getAssignment().getStudySite().getStudy().getAeTerminology().getCtcVersion().getCategories());
		return ctcCategories;
	}
	
    public EvaluationService getEvaluationService() {
        return evaluationService;
    }

    public void setEvaluationService(EvaluationService evaluationService) {
        this.evaluationService = evaluationService;
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
     * This method will return the {@link ReportDefinition} that are instantiated
     */
    public List<ReportDefinition> getInstantiatedReportDefinitions() {
        List<ReportDefinition> reportDefs = new ArrayList<ReportDefinition>();
        for (Report report : adverseEventReportingPeriod.getAeReport().getReports()) {
            if (!report.getStatus().equals(ReportStatus.WITHDRAWN)) reportDefs.add(report
                            .getReportDefinition());
        }

        return reportDefs;

    }
    public void setSelectedAesMap(Map<Integer, Boolean> selectedAesMap) {
		this.selectedAesMap = selectedAesMap;
	}
    
    public void refreshSelectedReportDefinitionsMap(List<ReportDefinition> defs) {
        // deselect all previously selected report
        for (Map.Entry<ReportDefinition, Boolean> entry : optionalReportDefinitionsMap.entrySet()) {
            entry.setValue(false);
        }
        setSelectedReportDefinitions(defs);
    }
    
    public void setSelectedReportDefinitions(List<ReportDefinition> defs) {
        if (defs == null || defs.isEmpty()) return;
        for (ReportDefinition def : defs) {
            optionalReportDefinitionsMap.put(def, true);
        }
    }
    
    private String getReportDefinitionNames(List<ReportDefinition> defs) {
        if (defs == null || defs.isEmpty()) return "";
        StringBuilder sb = new StringBuilder();
        for (ReportDefinition def : defs) {
            sb.append("optionalReportDefinitionsMap[" + def.getId() + "]").append(",");
        }
        return sb.toString();
    }
    
    public String getRequiredReportDefinitionNames() {
        return getReportDefinitionNames(getRequiredReportDefinitions());
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
     * This method will return the ReportDefinition which are selected by user in the checkpoint
     * page.
     */
    public List<ReportDefinition> getSelectedReportDefinitions() {
        List<ReportDefinition> reportDefs = new ArrayList<ReportDefinition>();
        for (Map.Entry<ReportDefinition, Boolean> entry : optionalReportDefinitionsMap.entrySet()) {
            if (entry.getValue() != null && entry.getValue()) reportDefs.add(entry.getKey());
        }
        return reportDefs;
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
     * Returns all the {@link ReportDefinition} available to this AE
     */
    public List<ReportDefinition> getAllReportDefinitions() {
        return allReportDefinitions;
    }

    public void setAllReportDefinitions(List<ReportDefinition> allReportDefinitions) {
        this.allReportDefinitions = allReportDefinitions;
    }
    
    public List<ReportDefinition> getRequiredReportDefinitions() {
        return requiredReportDefinitions;
    }

    public void setRequiredReportDefinition(List<ReportDefinition> defs) {
        if (defs != null) requiredReportDefinitions.addAll(defs);
    }
    
    public Map<ReportDefinition, Boolean> getOptionalReportDefinitionsMap() {
        return optionalReportDefinitionsMap;
    }
    
    public void setOptionalReportDefinitions(List<ReportDefinition> defs) {
        if (defs == null || defs.isEmpty()) return;
        for (ReportDefinition def : defs) {
            optionalReportDefinitionsMap.put(def, false);
        }
        // Deliberately not removing entries from the map that aren't in defs.
        // This is so that the user may still remove Reports whose ReportDefinitions
        // are no longer associated with the study.
    }
    
    public void setAdverseEventReportingPeriodDao(
			AdverseEventReportingPeriodDao adverseEventReportingPeriodDao) {
		this.adverseEventReportingPeriodDao = adverseEventReportingPeriodDao;
	}
}
