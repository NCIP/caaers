/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.dao.AdverseEventReportingPeriodDao;
import gov.nih.nci.cabig.caaers.dao.ExpeditedAdverseEventReportDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.report.ReportDefinitionDao;
import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.domain.dto.ApplicableReportDefinitionsDTO;
import gov.nih.nci.cabig.caaers.domain.dto.EvaluationResultDTO;
import gov.nih.nci.cabig.caaers.domain.dto.ReportDefinitionWrapper;
import gov.nih.nci.cabig.caaers.domain.dto.ReportDefinitionWrapper.ActionType;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.domain.repository.PersonRepository;
import gov.nih.nci.cabig.caaers.domain.repository.UserRepository;
import gov.nih.nci.cabig.caaers.security.SecurityUtils;
import gov.nih.nci.cabig.caaers.service.EvaluationService;
import gov.nih.nci.cabig.caaers.service.RecommendedActionService;
import gov.nih.nci.cabig.caaers.utils.DateUtils;
import gov.nih.nci.cabig.caaers.utils.DurationUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
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

    private RecommendedActionService recommendedActionService;
    
    private String loggedInUserEmail;
    
    public void setLoggedInUserEmail(String loggedInUserEmail) {
		this.loggedInUserEmail = loggedInUserEmail;
	}

	public String getLoggedInUserEmail() {
		return fetchLoggedInUserEmail();
	}

	protected PersonRepository personRepository;
    
    protected UserRepository userRepository;
	
	public UserRepository getUserRepository() {
		return userRepository;
	}

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public PersonRepository getPersonRepository() {
		return personRepository;
	}

	public void setPersonRepository(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

	public CaptureAdverseEventInputCommand(){

        this.outcomes = new ArrayList<Map<Integer,Boolean>>();
        this.outcomeOtherDetails = new ArrayList<String>();
        this.rulesMessageMap = new LinkedHashMap<Integer, List<String>>();
        this.recommendedReportTableMap = new LinkedHashMap<Integer, List<ReportTableRow>>();
        this.applicableReportTableMap = new LinkedHashMap<Integer, List<ReportTableRow>>();
        aeReportIndexMap = new HashMap<Integer, ExpeditedAdverseEventReport>();
        
	}
	
	public CaptureAdverseEventInputCommand(AdverseEventReportingPeriodDao adverseEventReportingPeriodDao, 
				 EvaluationService evaluationService, RecommendedActionService recommendedActionService, ReportDefinitionDao reportDefinitionDao, StudyDao studyDao, ExpeditedAdverseEventReportDao aeReportDao){
		
		this();
		this.adverseEventReportingPeriodDao = adverseEventReportingPeriodDao;
		this.evaluationService = evaluationService;
		this.reportDefinitionDao = reportDefinitionDao;
		this.studyDao = studyDao;
		this.aeReportDao = aeReportDao;
        this.recommendedActionService = recommendedActionService;
		
	}
	
	
	public CaptureAdverseEventInputCommand(AdverseEventReportingPeriodDao adverseEventReportingPeriodDao, 
			 EvaluationService evaluationService, RecommendedActionService recommendedActionService, ReportDefinitionDao reportDefinitionDao, StudyDao studyDao, ExpeditedAdverseEventReportDao aeReportDao,
			 PersonRepository personRepository, UserRepository userRepository){

		this(adverseEventReportingPeriodDao,evaluationService,recommendedActionService, reportDefinitionDao, studyDao, aeReportDao);
		this.personRepository = personRepository;
		this.userRepository = userRepository;
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
        save(true);
	}


    public void save(boolean incrementAeTermVersion){
    	Set<AdverseEvent> allAEs = new HashSet<AdverseEvent>();
    	//create a set of all AEs in all suggested reports
    	if(getEvaluationResult() != null) {
    		Map<Integer, List<AdverseEvent>> allAeMap = getEvaluationResult().getAllAeMap();
    		for (List<AdverseEvent> listAes : allAeMap.values()) {
				allAEs.addAll(listAes);
			}
    	}
        if(this.getAdverseEventReportingPeriod() != null){
			//initialize the graded date.
			for(AdverseEvent ae : adverseEventReportingPeriod.getAdverseEvents()){
				ae.initailzeGradedDate();
				ae.initializePostSubmissionUpdatedDate();

                //increment the version of AETerm
                if(incrementAeTermVersion && ae.getAdverseEventTerm() != null){
                    Integer version = ae.getAdverseEventTerm().getVersion();
                    version = (version == null) ? 0 : version + 1;
                    ae.getAdverseEventTerm().setVersion(version);

                }
                
                //match the ae in the reporting period to the one in the evaluated AE results
                //and copy over the requires reporting flag status
                for (AdverseEvent adverseEvent : allAEs) {
					if(ae.getId().equals(adverseEvent.getId())) {
						ae.setRequiresReporting(adverseEvent.getRequiresReporting());
						break;
					}
				}
			}

            // ToDo - delegate to gov.nih.nci.cabig.caaers.domain.repository.AdverseEventReportingPeriodRepository
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
	
	

   

//
//    /**
//     * This method will return the ReportDefinition which are selected by user
//     * page.
//     */
//    public List<ReportDefinition> getSelectedReportDefinitions() {
//        List<ReportDefinition> reportDefs = new ArrayList<ReportDefinition>();
////
////        for (Map.Entry<Integer, Boolean> entry : reportDefinitionMap.entrySet()) {
////            if (entry.getValue() != null && entry.getValue()){
////            	ReportDefinition reportDef = reportDefinitionIndexMap.get(entry.getKey());
////            	if(reportDef != null) reportDefs.add(reportDef);
////            }
////        }
//        return reportDefs;
//   }

	/**
	 * This method will initialize the outcomes and outcomeOtherDetails, in the command. 
	 */
	public void initializeOutcomes() {
		//CAAERS-6244 removed the clearing of outcomes to retain the user selection 
		//for error page
    	
    	//This method will populate the outcome map and the outcomeSerious details map
    	//only if it is not already populated, like, enter capture AE flow
		if(!outcomes.isEmpty() && outcomes.size() == getAdverseEvents().size()) {
			return;
		}
		
		// Fix for CAAERS-6704 : the current outcomes in the command have to be cleared before initializing,
		// otherwise the outcomes list keeps on growing
		outcomes.clear();
		int i = 0;
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
	 *   Death and hospitalization are sync'd on the UI by JS.
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
    	if(adverseEventReportingPeriod == null) {
    		return null;
    	} else {
    		return adverseEventReportingPeriod.getAssignment();
    	}
    }
	public boolean getIgnoreCompletedStudy() {
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

    public boolean getWorkflowEnabled() {
		return workflowEnabled;
	}

	public void setWorkflowEnabled(boolean workflowEnabled) {
		this.workflowEnabled = workflowEnabled;
	}
    
	//hasLabs
    public boolean isAssociatedToLabAlerts(){
    	 List<LabLoad> labs = getAssignment().getLabLoads();
    	 return (labs != null) && !labs.isEmpty();
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
        recommendedActionService.generateRecommendedReportTable(evaluationResult, aeReportIndexMap, recommendedReportTableMap);
    }
    
    
    /**
     * This method will create the value objects, that are to be displayed on UI for override options.
     * Note:- Will update the stringent flag, for those reports which fall after the recomended report. 
     */
    public void refreshApplicableReportTable(){

    	applicableReportTableMap.clear();

        recommendedActionService.refreshApplicableReportTable(evaluationResult, aeReportIndexMap,applicableReportTableMap,applicableReportDefinitions);
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

    public RecommendedActionService getRecommendedActionService() {
        return recommendedActionService;
    }

    public void setRecommendedActionService(RecommendedActionService recommendedActionService) {
        this.recommendedActionService = recommendedActionService;
    }

	public String fetchLoggedInUserEmail() {
		
		if ( !StringUtils.isBlank(loggedInUserEmail)){
				return loggedInUserEmail;
		}
		//set the default reporter as the logged-in person
		String loginId = SecurityUtils.getUserLoginName();
        if(loginId != null){
           Person loggedInPerson = getPersonRepository().getByLoginId(loginId);
           if(loggedInPerson != null && !StringUtils.isBlank(loggedInPerson.getEmailAddress())){
        	   loggedInUserEmail = loggedInPerson.getEmailAddress();
           } else {
               User loggedInUser = getUserRepository().getUserByLoginName(loginId);
               if(loggedInUser != null){
            	   loggedInUserEmail = loggedInUser.getEmailAddress();
               }
           }
        }
        
        return loggedInUserEmail;
	}

}
