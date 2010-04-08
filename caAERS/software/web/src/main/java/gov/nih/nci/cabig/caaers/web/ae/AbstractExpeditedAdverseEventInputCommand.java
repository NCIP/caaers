package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.dao.AdverseEventReportingPeriodDao;
import gov.nih.nci.cabig.caaers.dao.ExpeditedAdverseEventReportDao;
import gov.nih.nci.cabig.caaers.dao.StudyParticipantAssignmentDao;
import gov.nih.nci.cabig.caaers.dao.report.ReportDefinitionDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.Attribution;
import gov.nih.nci.cabig.caaers.domain.CourseAgent;
import gov.nih.nci.cabig.caaers.domain.DiseaseCodeTerm;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.LabLoad;
import gov.nih.nci.cabig.caaers.domain.Outcome;
import gov.nih.nci.cabig.caaers.domain.OutcomeType;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Physician;
import gov.nih.nci.cabig.caaers.domain.ReportStatus;
import gov.nih.nci.cabig.caaers.domain.Reporter;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyAgent;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.Term;
import gov.nih.nci.cabig.caaers.domain.TreatmentInformation;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportTree;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.TreeNode;
import gov.nih.nci.cabig.caaers.domain.report.*;
import gov.nih.nci.cabig.caaers.domain.repository.AdverseEventRoutingAndReviewRepository;
import gov.nih.nci.cabig.caaers.domain.repository.ReportRepository;
import gov.nih.nci.cabig.caaers.service.EvaluationService;
import gov.nih.nci.cabig.caaers.web.RenderDecisionManager;
import gov.nih.nci.cabig.caaers.web.utils.WebUtils;
import gov.nih.nci.cabig.ctms.domain.DomainObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletRequest;

import org.apache.commons.collections15.FactoryUtils;
import org.apache.commons.collections15.list.LazyList;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

/**
 * @author Rhett Sutphin
 * @author Biju Joseph
 */

public abstract class AbstractExpeditedAdverseEventInputCommand implements ExpeditedAdverseEventInputCommand {

    private static final Log log = LogFactory.getLog(AbstractExpeditedAdverseEventInputCommand.class);
//    private static final String REPORT_DEFN_LIST_PARAMETER ="reportDefnList";
    protected ExpeditedAdverseEventReport aeReport;

    private Map<String, List<List<Attribution>>> attributionMap;
    protected Map<Integer, Collection<ExpeditedReportSection>> mandatorySectionMap;
    protected MandatoryProperties mandatoryProperties;
    
    protected StudyParticipantAssignmentDao assignmentDao;
    protected ExpeditedAdverseEventReportDao reportDao;
    protected AdverseEventReportingPeriodDao reportingPeriodDao;
    protected ReportDefinitionDao reportDefinitionDao;
    protected ExpeditedReportTree expeditedReportTree;
    protected Map<String, Boolean> mandatoryFieldMap = new HashMap<String, Boolean>();
    protected ReportRepository reportRepository;

    private String treatmentDescriptionType;

    private int nextPage;
    
    protected List<Map<Integer, Boolean>> outcomes;
    protected List<String> outcomeOtherDetails; 
    protected List<ReportDefinition> selectedReportDefinitions;
    protected List<Report> selectedReportsAssociatedToWorkflow; 

    private List<ReportDefinition> newlySelectedReportDefinitions;
    private  List<ReportDefinition> applicableReportDefinitions;

    private boolean workflowEnabled;
    protected RenderDecisionManager renderDecisionManager;
    protected AdverseEventRoutingAndReviewRepository adverseEventRoutingAndReviewRepository;
    
    private List<String> chemoAgents;

    private Term studyTerminologyTerm;
    
    private Map<Object, Object> studyDiseasesMap;
    private Integer index; //corresponds to the index of the item (eg: conmed[3])
    private Integer parentIndex; // corresponds to the index of the parent item (eg: priorTherapy[parentIndex].agents[index])

    protected HashMap<String, Boolean> rulesErrors;
    
    //from which screen flow, we reached expedited flow
    private String screenFlowSource;
    
    protected EvaluationService evaluationService;

    // cache for ruleable fields
    List<String> ruleableFields = null;

    public AbstractExpeditedAdverseEventInputCommand(){
    		aeReport = new ExpeditedAdverseEventReport();
    }
    
    public AbstractExpeditedAdverseEventInputCommand(ExpeditedAdverseEventReportDao reportDao, 
    		ReportDefinitionDao reportDefinitionDao, AdverseEventReportingPeriodDao reportingPeriodDao, 
    		ExpeditedReportTree expeditedReportTree, RenderDecisionManager renderDecisionManager, ReportRepository reportRepository,
    		StudyParticipantAssignmentDao assignmentDao, AdverseEventRoutingAndReviewRepository adverseEventRoutingAndReviewRepository) {
    	this.assignmentDao = assignmentDao;
    	this.reportingPeriodDao = reportingPeriodDao;
        this.reportDao = reportDao;
        this.reportDefinitionDao = reportDefinitionDao;
        this.expeditedReportTree = expeditedReportTree;
        this.renderDecisionManager = renderDecisionManager;
        this.reportRepository = reportRepository;
        this.adverseEventRoutingAndReviewRepository = adverseEventRoutingAndReviewRepository;
        
        this.outcomeOtherDetails = new ArrayList<String>();
        this.outcomes = new ArrayList<Map<Integer,Boolean>>();
        this.selectedReportDefinitions = new ArrayList<ReportDefinition>();
        this.newlySelectedReportDefinitions = new ArrayList<ReportDefinition>();
        this.applicableReportDefinitions = new ArrayList<ReportDefinition>();
        this.chemoAgents = new ArrayList<String>(); // new ArrayList<ChemoAgent>();
    }

    public abstract StudyParticipantAssignment getAssignment();
    public abstract Participant getParticipant();
    public abstract Study getStudy();
    
    public void flush() {
    	reportDao.flush();
    }
    
    public void save(){
    	reportDao.save(aeReport);
    }
    
    
    public void synchronizeAndSaveAssignment(){
    	ExpeditedAdverseEventReport aeReport = getAeReport();
    	StudyParticipantAssignment assignment = aeReport.getAssignment();
    	assignment.synchronizeMedicalHistoryFromReportToAssignment(aeReport);
    	assignmentDao.save(assignment);
    }
    
    public void reassociate() {
    	if (getAeReport().getId() != null) {
            ExpeditedAdverseEventReport merged = reportDao.merge(getAeReport());
            setAeReport(merged);
        }
    }

    public void setAeReport(ExpeditedAdverseEventReport aeReport) {
    	if(aeReport != null){
    		if (aeReport.getTreatmentInformation() == null) {
    			aeReport.setTreatmentInformation(new TreatmentInformation());
    		}
    		if(aeReport.getReporter() == null) aeReport.setReporter(new Reporter());
    		if(aeReport.getPhysician() == null) aeReport.setPhysician(new Physician());
    		this.attributionMap = new AttributionMap(aeReport);
    	}
        this.aeReport = aeReport;
    }


    public ExpeditedAdverseEventReport getAeReport() {
        return aeReport;
    }

    public Map<String, List<List<Attribution>>> getAttributionMap() {
        return attributionMap;
    }

    public void setAttributionMap(AttributionMap attributionMap) {
        this.attributionMap = attributionMap;
    }

    public Collection<ExpeditedReportSection> getMandatorySections() {
        Set<ExpeditedReportSection> mandatorySections = new HashSet<ExpeditedReportSection>();
        if(mandatorySectionMap != null && !mandatorySectionMap.isEmpty()){
            for(Integer i : mandatorySectionMap.keySet()){
                mandatorySections.addAll(mandatorySectionMap.get(i));
            }
        }

        return mandatorySections;
    }

    public Map<Integer, Collection<ExpeditedReportSection>> getMandatorySectionMap(){
        return mandatorySectionMap;
    }

    public void setMandatorySectionMap(Map<Integer, Collection<ExpeditedReportSection>> mandatorySectionMap){
       this.mandatorySectionMap = mandatorySectionMap;
    }
   
    /**
     * This method will find the mandatory sections associated to expedited report, and the report definitions selected.
     */
    public void refreshMandatorySections(){
    	ReportDefinition[] selected = this.selectedReportDefinitions.toArray(new ReportDefinition[]{});
        Map<Integer, Collection<ExpeditedReportSection>> map = evaluationService.mandatorySections(aeReport, selected);
        setMandatorySectionMap(map);
    }



    /**
     * The repeating fields available in the mandatory sections will be pre-initialized here.
     */

    @SuppressWarnings("unchecked")
    public void initializeMandatorySectionFields() {
        Collection<ExpeditedReportSection> mandatorySections = getMandatorySections();
        if (mandatorySections == null || mandatorySections.isEmpty()) {
            log.info("No mandatory sections available, so no fields will be pre initialized");
            return;
        }

        // pre-initialize lazy fields in mandatory sections.
        BeanWrapper wrapper = new BeanWrapperImpl(getAeReport());
        for (ExpeditedReportSection section : getMandatorySections()) {
            assert (section != null) : "A section is null in command.getManatorySections()";

            TreeNode sectionNode = expeditedReportTree.getNodeForSection(section);
            if (sectionNode == null) log.warn("Unable to fetch TreeNode for section" + section.name());

            assert (sectionNode != null) : section.toString()+ ", is not available in ExpeditedReportTree.";
            if (sectionNode.getChildren() == null) continue;

            for (TreeNode node : sectionNode.getChildren()) {
                if (node.isList()) {
                    log.info("Initialized '" + node.getPropertyName() + "' in section " + section.name());
                    wrapper.getPropertyValue(node.getPropertyName() + "[0]");
                }
            }
            

            // special case, when Agent section is mandatory, at least one course agent should be ther.
            if (ExpeditedReportSection.AGENTS_INTERVENTION_SECTION.equals(section)) {
            	TreeNode agentSectionNode = expeditedReportTree.getNodeForSection(ExpeditedReportSection.AGENTS_INTERVENTION_SECTION);
                List<CourseAgent> courseAgents = (List<CourseAgent>) wrapper.getPropertyValue(agentSectionNode.getChildren().get(0).getPropertyName() + ".courseAgents");
                courseAgents.get(0);
            }
        }
        


    }

    public MandatoryProperties getMandatoryProperties() {
        return mandatoryProperties;
    }

    public String getTreatmentDescriptionType() {
        return treatmentDescriptionType;
    }

    public void setTreatmentDescriptionType(String type) {
        this.treatmentDescriptionType = type;
    }

    public Map<String, Boolean> getMandatoryFieldMap() {
        return mandatoryFieldMap;
    }


    /**
     * This method will return the {@link ReportDefinition} that are instantiated
     */
    public List<ReportDefinition> getInstantiatedReportDefinitions() {
        List<ReportDefinition> reportDefs = new ArrayList<ReportDefinition>();

        for (Report report : aeReport.getReports()) {
        	if (!report.getStatus().equals(ReportStatus.WITHDRAWN)) reportDefs.add(report
        			.getReportDefinition());
        }
        return reportDefs;

    }


    public boolean getIgnoreCompletedStudy() {
        return true;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int page) {
        this.nextPage = page;
    }

    public List<String> getOutcomeOtherDetails() {
    	return outcomeOtherDetails;
    }
    
    public List<Map<Integer, Boolean>> getOutcomes() {
    	return outcomes;
    }
    
    public void updateOutcomes() {
    	outcomeOtherDetails.clear();
    	outcomes.clear();
    	int i = 0;
    	//This method will populate the outcome map and the outcomeSerious details map.
    	for(AdverseEvent ae : getAeReport().getAdverseEvents()){
    	
    		//update the command bounded variables with default values
    		outcomeOtherDetails.add("");
    		LinkedHashMap<Integer, Boolean> oneOutcomeMap = new LinkedHashMap<Integer, Boolean>();
    		outcomes.add(oneOutcomeMap);
    	
    		//in this pass we will initialize all the outcomes to default 'FALSE' and other details to empty string.
    		for(OutcomeType outcomeType : OutcomeType.values()){
    			oneOutcomeMap.put(outcomeType.getCode(), Boolean.FALSE);
    		}
        
    		//in this pass we will update the outcome details based on the OUTCOME db values
    		for(Outcome outcome : ae.getOutcomes()){
    			oneOutcomeMap.put(outcome.getOutcomeType().getCode(), Boolean.TRUE);
    			if(outcome.getOutcomeType().equals(OutcomeType.OTHER_SERIOUS)){
    				outcomeOtherDetails.set(i, outcome.getOther());
    			}
    		}
        
    		i++;
    	}
    }

    public Integer getZERO() {
        return ExpeditedAdverseEventInputCommand.ZERO;
    }
    
    public void setSelectedReportDefinitions(List<ReportDefinition> selectedReportDefinitions) {
    	this.selectedReportDefinitions.clear();
    	if(selectedReportDefinitions != null) this.selectedReportDefinitions.addAll(selectedReportDefinitions);
    }
    
    public List<ReportDefinition> getSelectedReportDefinitions() {
    	return selectedReportDefinitions;
    }
    
    //hasLabs
    public boolean isAssociatedToLabAlerts(){
    	 List<LabLoad> labs = getAssignment().getLabLoads();
    	 return (labs != null) && !labs.isEmpty();
    }
    
    public boolean isAssociatedToWorkflow(){
    	//return getWorkflowEnabled() && getAeReport().getWorkflowId() != null;
    	if(getWorkflowEnabled()){
    		Map<Integer, Boolean> selectedReportDefinitionsMap = new HashMap<Integer, Boolean>();
    		for(ReportDefinition rd: selectedReportDefinitions)
    			if(! selectedReportDefinitionsMap.containsKey(rd.getId()))
    				selectedReportDefinitionsMap.put(rd.getId(), Boolean.TRUE);
    		for(Report r: aeReport.getActiveReports())
    			if(selectedReportDefinitionsMap.containsKey(r.getReportDefinition().getId()) && r.getWorkflowId() != null)
    				return true;
    	}else
    		return false;
    	return false;
    }
    
    /** By default addition of AEs is allowed in expedited flow */
    public boolean isAdditionAllowed() {
    	return true;
    }
    
    public boolean getWorkflowEnabled() {
		return workflowEnabled;
	}
	public void setWorkflowEnabled(boolean workflowEnabled) {
		this.workflowEnabled = workflowEnabled;
	}
	

    
    boolean isAdverseEventPresent(AdverseEvent ae){
    	return reportingPeriodDao.isAdverseEventPresent(ae);
    }
    
   

    /**
     * Will update the mandatory fields details.
     *  1. Evaluate the mandatory-ness via EvaluationService
     *  2. Update the mandatory properties.
     *  3. Update the rendering decisions
     */
    public void updateFieldMandatoryness(){

       //figureout the reports
       List<Report> reportsToEvaluate = new ArrayList<Report>();
       for(ReportDefinition rd : getSelectedReportDefinitions()){
           reportsToEvaluate.add(rd.createReport());
       }

       mandatoryProperties = new MandatoryProperties(expeditedReportTree);

       //evaluate the mandatoryness
       for(Report reportToEvaluate : reportsToEvaluate){
           evaluationService.evaluateMandatoryness(aeReport, reportToEvaluate);
           for(ReportMandatoryField mf : reportToEvaluate.getMandatoryFields()){
               if(mf.getMandatory() == Mandatory.MANDATORY){
                   mandatoryProperties.addNode(mf.getFieldPath());
               }
           }
       }

       //update the render decision
       renderDecisionManager.updateRenderDecision(reportsToEvaluate);

    }
    
    public void initializeTreatmentInformation(){
    	ExpeditedAdverseEventReport aeReport = getAeReport();
    	TreatmentInformation treatmentInformation = aeReport.getTreatmentInformation();
    	treatmentInformation.setTreatmentAssignment(aeReport.getReportingPeriod().getTreatmentAssignment());
    	treatmentInformation.setTreatmentDescription(StringUtils.trimToNull(aeReport.getReportingPeriod().getTreatmentAssignmentDescription()));
    	treatmentInformation.setFirstCourseDate(aeReport.getAssignment().getStartDateOfFirstCourse());
    	treatmentInformation.getAdverseEventCourse().setDate(aeReport.getReportingPeriod().getStartDate());
    	treatmentInformation.getAdverseEventCourse().setNumber(aeReport.getReportingPeriod().getCycleNumber());
    	treatmentInformation.setTotalCourses(aeReport.getAssignment().getMaxCycleNumber());
    	
    }
    
    public List<String> getPriorTherapyAgents() {
		return LazyList.decorate(chemoAgents, FactoryUtils.nullFactory());
	}

    public void setPriorTherapyAgents(List<String> chemoAgents) {
		this.chemoAgents = chemoAgents;
	}
    
    public Term getStudyTerminologyTerm() {
		if(studyTerminologyTerm == null){
			if(getStudy() != null){
				studyTerminologyTerm = getStudy().getAeTerminology().getTerm();
			}
			
		}
		return studyTerminologyTerm;
	}
    
    public boolean isSectionMandatory(ExpeditedReportSection section) {
        Collection<ExpeditedReportSection> mandatorySections = getMandatorySections();
    	if(mandatorySections == null || mandatorySections.isEmpty()) return false;
    	return mandatorySections.contains(section);
    }
    
    public Map<Object, Object> getStudyDiseasesOptions(DiseaseCodeTerm diseaseCodingTerm){
        if (studyDiseasesMap == null) {
            if (diseaseCodingTerm.equals(DiseaseCodeTerm.MEDDRA)) {
                studyDiseasesMap = WebUtils.collectOptions(getStudy().getActiveStudyDiseases(), "id", "term.meddraTerm", "Please select");
            } else if (diseaseCodingTerm.equals(DiseaseCodeTerm.OTHER)) {
                studyDiseasesMap = WebUtils.collectOptions(getStudy().getActiveStudyDiseases(), "id", "term.conditionName", "Please select");
            } else {
                studyDiseasesMap = WebUtils.collectOptions(getStudy().getActiveStudyDiseases(), "id", "term.term", "Please select");
            }
        }
        return studyDiseasesMap;
    }
    
    public Map<Object, Object> getStudyDiseasesMap() {
		return studyDiseasesMap;
	}
    public void setStudyDiseasesMap(Map<Object, Object> studyDiseasesMap) {
		this.studyDiseasesMap = studyDiseasesMap;
	}
    
    public Integer getIndex() {
		return index;
	}
    public void setIndex(Integer index) {
		this.index = index;
	}
    public void setParentIndex(Integer parentIndex) {
		this.parentIndex = parentIndex;
	}
    public Integer getParentIndex() {
		return parentIndex;
	}
    public void deleteAttribution(DomainObject o){
    	reportDao.cascaeDeleteToAttributions(o, getAeReport());
    }
    
    public AdverseEventRoutingAndReviewRepository getAdverseEventRoutingAndReviewRepository(){
    	return adverseEventRoutingAndReviewRepository;
    }
    
    public void setAdverseEventRoutingAndReviewRepository(AdverseEventRoutingAndReviewRepository adverseEventRoutingAndReviewRepository){
    	this.adverseEventRoutingAndReviewRepository = adverseEventRoutingAndReviewRepository;
    }
    
    /**
     * This method will make the adverse event identified by primaryAdverseEventId, the first one in the report.
     * @param primaryAdverseEventId
     */
    public void makeAdverseEventPrimary(Integer primaryAdverseEventId){
    	if(primaryAdverseEventId == null) return;
    	if(aeReport == null) return;
    	AdverseEvent newPrimaryAE = null;
    	List<AdverseEvent> aeList = aeReport.getAdverseEvents();
    	int size = aeList.size();
    	for(int i = 1; i < size; i++){                                                        
    		if(aeList.get(i).getId().equals(primaryAdverseEventId)){
    			newPrimaryAE = aeList.get(i);
    			break;
    		}
    	}
    	
    	if(newPrimaryAE != null){
    		aeList.remove(newPrimaryAE);
    		aeList.add(0, newPrimaryAE);
    	}
    }

    public boolean isErrorApplicable(String... fields) {
        if (fields == null) return true;
        for (byte i=0; i<fields.length; i++) {
            if (!renderDecisionManager.canRenderField(fields[i])) return false;
        }
        return true;
    }
    
    /**
     * This method checks if a previous report for this study participant indicated Was an investigational agent administered on this protocol?="Yes" 
     * @return
     */
	public boolean isInvestigationalAgentAdministeredForPreviousReports(){
		List<ExpeditedAdverseEventReport> allAeReportsForAssignment = getAssignment().getAeReports();
		for (ExpeditedAdverseEventReport aeReport:allAeReportsForAssignment) {
				Boolean flag  = aeReport.getTreatmentInformation().getInvestigationalAgentAdministered();
				if (flag != null && flag) {
					return true;
				}
		}
		return false;
	}
    

    public HashMap<String, Boolean> getRulesErrors() {
        return rulesErrors;
    }

    public void setRulesErrors(HashMap<String, Boolean> rulesErrors) {
        this.rulesErrors = rulesErrors;
    }
    
    /**
     * Tells from which screen flow, we reached into expedited flow
     * @return
     */
    public String getScreenFlowSource() {
		return screenFlowSource;
	}
    public void setScreenFlowSource(String screenFlowSource) {
		this.screenFlowSource = screenFlowSource;
	}
    
    public List<ReportDefinition> getNewlySelectedReportDefinitions() {
    	// TODO Auto-generated method stub
    	return newlySelectedReportDefinitions;
    }
    public void setNewlySelectedReportDefinitions(List<ReportDefinition> newlySelectedReportDefinitions) {
    	this.newlySelectedReportDefinitions = newlySelectedReportDefinitions;
    }
    public List<ReportDefinition> getApplicableReportDefinitions() {
    	return this.applicableReportDefinitions;
    }
    public void setApplicableReportDefinitions(List<ReportDefinition> selectedReportDefinitions) {
    	this.selectedReportDefinitions = selectedReportDefinitions;
    }
    
    public List<Report> getSelectedReportsAssociatedToWorkflow(){
    	selectedReportsAssociatedToWorkflow = new ArrayList<Report>();
    	Map<Integer, Boolean> selectedReportDefinitionsMap = new HashMap<Integer, Boolean>();
    	for(ReportDefinition rd: selectedReportDefinitions)
    		if(! selectedReportDefinitionsMap.containsKey(rd.getId()))
    			selectedReportDefinitionsMap.put(rd.getId(), Boolean.TRUE);
    	for(Report r: aeReport.getActiveReports())
    		if(selectedReportDefinitionsMap.containsKey(r.getReportDefinition().getId()) && r.getWorkflowId() != null)
    			selectedReportsAssociatedToWorkflow.add(r);
    	return selectedReportsAssociatedToWorkflow;
    }

    public List<String> getRuleableFields() {
        return ruleableFields;
    }

    public void setRuleableFields(List<String> ruleableFields) {
        this.ruleableFields = ruleableFields;
    }
}
