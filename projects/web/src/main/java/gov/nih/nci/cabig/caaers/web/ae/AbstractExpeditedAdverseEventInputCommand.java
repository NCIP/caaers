package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.dao.AdverseEventReportingPeriodDao;
import gov.nih.nci.cabig.caaers.dao.ExpeditedAdverseEventReportDao;
import gov.nih.nci.cabig.caaers.dao.report.ReportDefinitionDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.AnatomicSite;
import gov.nih.nci.cabig.caaers.domain.Attribution;
import gov.nih.nci.cabig.caaers.domain.ChemoAgent;
import gov.nih.nci.cabig.caaers.domain.CourseAgent;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.LabLoad;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Physician;
import gov.nih.nci.cabig.caaers.domain.PreExistingCondition;
import gov.nih.nci.cabig.caaers.domain.PriorTherapy;
import gov.nih.nci.cabig.caaers.domain.ReportStatus;
import gov.nih.nci.cabig.caaers.domain.Reporter;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.OutcomeType;
import gov.nih.nci.cabig.caaers.domain.StudyAgent;
import gov.nih.nci.cabig.caaers.domain.Grade;
import gov.nih.nci.cabig.caaers.domain.Outcome;
import gov.nih.nci.cabig.caaers.domain.Hospitalization;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.Term;
import gov.nih.nci.cabig.caaers.domain.TreatmentInformation;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportTree;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.TreeNode;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.domain.report.ReportMandatoryFieldDefinition;
import gov.nih.nci.cabig.caaers.domain.repository.ReportRepository;
import gov.nih.nci.cabig.caaers.web.RenderDecisionManager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Date;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections15.FactoryUtils;
import org.apache.commons.collections15.list.LazyList;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

/**
 * @author Rhett Sutphin
 */

public abstract class AbstractExpeditedAdverseEventInputCommand implements ExpeditedAdverseEventInputCommand {

    private static final Log log = LogFactory.getLog(AbstractExpeditedAdverseEventInputCommand.class);
//    private static final String REPORT_DEFN_LIST_PARAMETER ="reportDefnList";
    protected ExpeditedAdverseEventReport aeReport;

    private Map<String, List<List<Attribution>>> attributionMap;
    protected Collection<ExpeditedReportSection> mandatorySections;
    protected MandatoryProperties mandatoryProperties;

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
    private boolean workflowEnabled;
    protected RenderDecisionManager renderDecisionManager;
    
    
    private AnatomicSite metastaticDiseaseSite;
    private PreExistingCondition preExistingCondition;
    private PriorTherapy priorTherapy;
    private List<String> chemoAgents;
    private ChemoAgent chemoAgent;
    private String concomitantMedication;
    
    private Term studyTerminologyTerm;

    public AbstractExpeditedAdverseEventInputCommand(){
    		aeReport = new ExpeditedAdverseEventReport();
    }
    
    public AbstractExpeditedAdverseEventInputCommand(ExpeditedAdverseEventReportDao reportDao, 
    		ReportDefinitionDao reportDefinitionDao, AdverseEventReportingPeriodDao reportingPeriodDao, 
    		ExpeditedReportTree expeditedReportTree, RenderDecisionManager renderDecisionManager, ReportRepository reportRepository) {
    	this.reportingPeriodDao = reportingPeriodDao;
        this.reportDao = reportDao;
        this.reportDefinitionDao = reportDefinitionDao;
        this.expeditedReportTree = expeditedReportTree;
        this.renderDecisionManager = renderDecisionManager;
        this.reportRepository = reportRepository;
        
        this.outcomeOtherDetails = new ArrayList<String>();
        this.outcomes = new ArrayList<Map<Integer,Boolean>>();
        this.selectedReportDefinitions = new ArrayList<ReportDefinition>();
        this.chemoAgents = new ArrayList<String>(); // new ArrayList<ChemoAgent>();
    }

    public abstract StudyParticipantAssignment getAssignment();
    public abstract Participant getParticipant();
    public abstract Study getStudy();
    public abstract void save();
    public abstract void flush();

    public void reassociate() {
        for (ReportDefinition definition : selectedReportDefinitions) {
            reportDefinitionDao.reassociate(definition);
        }
        
        if (getAeReport().getId() != null) {
            ExpeditedAdverseEventReport merged = reportDao.merge(getAeReport());
            setAeReport(merged);
        }
    }

    public void setAeReport(ExpeditedAdverseEventReport aeReport) {
        if (aeReport.getTreatmentInformation() == null) {
            aeReport.setTreatmentInformation(new TreatmentInformation());
        }
        if(aeReport.getReporter() == null) aeReport.setReporter(new Reporter());
        if(aeReport.getPhysician() == null) aeReport.setPhysician(new Physician());
        this.aeReport = aeReport;
        this.attributionMap = new AttributionMap(aeReport);
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
        return mandatorySections;
    }

    public void setMandatorySections(Collection<ExpeditedReportSection> sections) {
        this.mandatorySections = sections;
    }

    public void refreshMandatoryProperties() {
        if (aeReport.getReports() == null) return;
        mandatoryProperties = new MandatoryProperties(expeditedReportTree);
        for (Report report : aeReport.getReports()) {
        	if (report.getReportDefinition().getMandatoryFields() == null) continue;
        	for (ReportMandatoryFieldDefinition field : report.getReportDefinition()
                           .getMandatoryFields()) {
        		mandatoryProperties.add(field);
        	}
        }
    }

    /**
     * The repeating fields available in the mandatory sections will be pre-initialized here.
     */

    @SuppressWarnings("unchecked")
    public void initializeMandatorySectionFields() {
        if (mandatorySections == null || mandatorySections.isEmpty()) {
            log.info("No mandatory sections available, so no fields will be pre initialized");
            return;
        }

        // pre-initialize lazy fields in mandatory sections.
        BeanWrapper wrapper = new BeanWrapperImpl(getAeReport());
        for (ExpeditedReportSection section : getMandatorySections()) {
            assert (section != null) : "A section is null in command.getManatorySections()";

            TreeNode sectionNode = expeditedReportTree.getNodeForSection(section);
            if (sectionNode == null) log.warn("Unable to fetch TreeNode for section"
                            + section.name());

            assert (sectionNode != null) : section.toString()
                            + ", is not available in ExpeditedReportTree.";
            if (sectionNode.getChildren() == null) continue;

            for (TreeNode node : sectionNode.getChildren()) {
                if (node.isList()) {
                    log.info("Initialized '" + node.getPropertyName() + "' in section "
                                    + section.name());
                    wrapper.getPropertyValue(node.getPropertyName() + "[0]");
                }
            }

            // special case, when TreatmentInformation (course&agents tab) is mandatory.
            // All StudyAgents associated with lead IND should be pre-initialized.
            if (ExpeditedReportSection.TREATMENT_INFO_SECTION.equals(section)) {
                List<CourseAgent> courseAgents = (List<CourseAgent>) wrapper.getPropertyValue(sectionNode.getChildren().get(0).getPropertyName() + ".courseAgents");
                if (courseAgents.size() <= 0) {
                    // first time, the user did not override system pre selection.
                    int i = 0;

                    for (StudyAgent agent : getAeReport().getStudy().getStudyAgents()) {
                    	if (agent.getPartOfLeadIND() != null && agent.getPartOfLeadIND()) {
                    		CourseAgent courseAgent = courseAgents.get(i);
                    		courseAgent.setStudyAgent(agent);
                    		i++;
                    	}
                    }
                    
                }
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

    public Collection<ReportDefinition> getSubmittedReportDefinitions() {
        Set<ReportDefinition> defs = new HashSet<ReportDefinition>();
        if (getAeReport().getReports() != null) {
            for (Report report : getAeReport().getReports()) {
                if (report.getLastVersion().getReportStatus().equals(ReportStatus.COMPLETED)) {
                    defs.add(report.getReportDefinition());
                }
            }
        }
        return null;
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
    
    /**
     * Returns the value associated with the <code>attributeName</code>, if present in
     * HttpRequest parameter, if not available, will check in HttpRequest attribute map.
     */
    protected Object findInRequest(final ServletRequest request, final String attributName) {

        Object attr = request.getParameter(attributName);
        if (attr == null) {
            attr = request.getAttribute(attributName);
        }
        return attr;
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
    	return getWorkflowEnabled() && getAeReport().getWorkflowId() != null;
    }
    
    /** By default addition of AEs is not allowed in expedited flow */
    public boolean isAdditionAllowed() {
    	return false;
    }
    
    public boolean getWorkflowEnabled() {
		return workflowEnabled;
	}
	public void setWorkflowEnabled(boolean workflowEnabled) {
		this.workflowEnabled = workflowEnabled;
	}
	
	 /**
     * This method will intialize the render decision manager, with the field display status.
     * @param reportDefs
     */
    public void initializeNotApplicableFields(Collection<ReportDefinition> reportDefs) {
    	//find the list of report definitions associated to the existing AE report, and the ones that are newly selected.
    	//Note:- Since there is a potential to throw LazyInit exception, we will use HashMap based logic to find the unique ReportDefinition.
    	HashMap<Integer , ReportDefinition> map = new HashMap<Integer, ReportDefinition>();
    	
    	if(getSelectedReportDefinitions() != null){
    		for(ReportDefinition rd : reportDefs){
    			map.put(rd.getId(), rd);
    		}
    	}
    	
    	for(Report r : getAeReport().getReports()){
    		ReportDefinition rd = r.getReportDefinition();
    		map.put(rd.getId(), rd);
    	}
    	
    	//reassociate them with current running session
    	for(ReportDefinition rd : map.values()){
    		reportDefinitionDao.reassociate(rd);
    	}
    	
    	renderDecisionManager.updateRenderDecision(map.values());
    	
	}
    
    public void initializeNotApplicableFields(){
    	initializeNotApplicableFields(this.getSelectedReportDefinitions());
    }
    
    
    
    public void initializeTreatmentInformation(){
    	ExpeditedAdverseEventReport aeReport = getAeReport();
    	TreatmentInformation treatmentInformation = aeReport.getTreatmentInformation();
    	treatmentInformation.setTreatmentAssignment(aeReport.getReportingPeriod().getTreatmentAssignment());
    	treatmentInformation.setFirstCourseDate(aeReport.getAssignment().getStartDateOfFirstCourse());
    	treatmentInformation.getAdverseEventCourse().setDate(aeReport.getReportingPeriod().getStartDate());
    	treatmentInformation.getAdverseEventCourse().setNumber(aeReport.getReportingPeriod().getCycleNumber());
    	treatmentInformation.setTotalCourses(aeReport.getAssignment().getMaxCycleNumber());
    }
    
    
    public AnatomicSite getMetastaticDiseaseSite() {
		return metastaticDiseaseSite;
	}
    public void setMetastaticDiseaseSite(AnatomicSite metastaticDiseaseSite) {
		this.metastaticDiseaseSite = metastaticDiseaseSite;
	}
    public PreExistingCondition getPreExistingCondition() {
		return preExistingCondition;
	}
    public void setPreExistingCondition(PreExistingCondition preExistingCondition) {
		this.preExistingCondition = preExistingCondition;
	}
    public PriorTherapy getPriorTherapy() {
		return priorTherapy;
	}
    public void setPriorTherapy(PriorTherapy priorTherapy) {
		this.priorTherapy = priorTherapy;
	}
    public List<String> getPriorTherapyAgents() {
		return LazyList.decorate(chemoAgents, FactoryUtils.nullFactory());
	}
    public void setPriorTherapyAgents(List<String> chemoAgents) {
		this.chemoAgents = chemoAgents;
	}
    
    public String getConcomitantMedication() {
		return concomitantMedication;
	}
    public void setConcomitantMedication(String concomitantMedication) {
		this.concomitantMedication = concomitantMedication;
	}
    
    public void setPriorTherapyAgent(ChemoAgent chemoAgent) {
		this.chemoAgent = chemoAgent;
	}
    public ChemoAgent getPriorTherapyAgent() {
		return chemoAgent;
	}
    
    public Term getStudyTerminologyTerm() {
		if(studyTerminologyTerm == null){
			studyTerminologyTerm = getStudy().getAeTerminology().getTerm();
		}
		return studyTerminologyTerm;
	}
    
    public boolean isSectionMandatory(ExpeditedReportSection section) {
    	if(mandatorySections == null || mandatorySections.isEmpty()) return false;
    	return mandatorySections.contains(section);
    }
		
       
}
