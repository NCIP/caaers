package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.dao.StudyParticipantAssignmentDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.Ctc;
import gov.nih.nci.cabig.caaers.domain.CtcCategory;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.service.EvaluationService;

import java.util.ArrayList;
import java.util.HashMap;
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
	private List<CtcCategory> ctcCategories;
	private Map<Integer, Boolean> selectedAesMap;
	
	

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
	
	 
	public CaptureAdverseEventInputCommand(StudyParticipantAssignmentDao assignmentDao, EvaluationService evaluationService){
		//this.adverseEventReportingPeriod = new AdverseEventReportingPeriod();
		this.assignmentDao = assignmentDao;
		this.evaluationService = evaluationService;
		selectedAesMap = new HashMap<Integer, Boolean>();
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
		return repDefnIdToAeListMap;
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

	public void save() {
		// TODO Auto-generated method stub
		
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
    	
    	for(AdverseEventReportingPeriod reportingPeriod : assignment.getReportingPeriods()){
    		if(reportingPeriod.getId().equals(reportingPeriodId)){
    			this.adverseEventReportingPeriod = reportingPeriod;
    			break;
    		}
    	}
    	initialize();
    }
    
    
    /**
     * This method will take care of initializing the lazy associations
     */
    public void initialize(){
    	if(adverseEventReportingPeriod != null){
			this.adverseEventReportingPeriod.getStudy().getStudyOrganizations().size();
			this.adverseEventReportingPeriod.getAdverseEvents().size();
			this.adverseEventReportingPeriod.getAeReport();
		}
    }
    public void setSelectedAesMap(Map<Integer, Boolean> selectedAesMap) {
		this.selectedAesMap = selectedAesMap;
	}
    
    public Map<Integer, Boolean> getSelectedAesMap(){
   		return selectedAesMap;
    }
}
