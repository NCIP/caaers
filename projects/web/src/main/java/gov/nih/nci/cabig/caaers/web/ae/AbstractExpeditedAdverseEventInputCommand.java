package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.dao.AdverseEventReportingPeriodDao;
import gov.nih.nci.cabig.caaers.dao.ExpeditedAdverseEventReportDao;
import gov.nih.nci.cabig.caaers.dao.report.ReportDefinitionDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.Attribution;
import gov.nih.nci.cabig.caaers.domain.CourseAgent;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Physician;
import gov.nih.nci.cabig.caaers.domain.ReportStatus;
import gov.nih.nci.cabig.caaers.domain.Reporter;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.OutcomeType;
import gov.nih.nci.cabig.caaers.domain.StudyAgent;
import gov.nih.nci.cabig.caaers.domain.Grade;
import gov.nih.nci.cabig.caaers.domain.Outcome;
import gov.nih.nci.cabig.caaers.domain.Hospitalization;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.TreatmentInformation;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportTree;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.TreeNode;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.domain.report.ReportMandatoryFieldDefinition;
import gov.nih.nci.cabig.caaers.domain.repository.ReportRepository;

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
    private ExpeditedAdverseEventReport aeReport;

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
    
    private List<Map<Integer, Boolean>> outcomes;
    private List<String> outcomeOtherDetails; 
    protected List<ReportDefinition> selectedReportDefinitions;

    public AbstractExpeditedAdverseEventInputCommand(ExpeditedAdverseEventReportDao reportDao, ReportDefinitionDao reportDefinitionDao, AdverseEventReportingPeriodDao reportingPeriodDao, ExpeditedReportTree expeditedReportTree) {
    	this.reportingPeriodDao = reportingPeriodDao;
        this.reportDao = reportDao;
        this.reportDefinitionDao = reportDefinitionDao;
        this.expeditedReportTree = expeditedReportTree;
        this.outcomeOtherDetails = new ArrayList<String>();
        this.outcomes = new ArrayList<Map<Integer,Boolean>>();
        this.selectedReportDefinitions = new ArrayList<ReportDefinition>();
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
        //reportingPeriodDao.reassociate(getAeReport().getReportingPeriod());
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
    public void initializeMandatorySectionFields(ExpeditedReportTree tree) {
        if (mandatorySections == null || mandatorySections.isEmpty()) {
            log.info("No mandatory sections available, so no fields will be pre initialized");
            return;
        }

        // pre-initialize lazy fields in mandatory sections.
        BeanWrapper wrapper = new BeanWrapperImpl(getAeReport());
        for (ExpeditedReportSection section : getMandatorySections()) {
            assert (section != null) : "A section is null in command.getManatorySections()";

            TreeNode sectionNode = tree.getNodeForSection(section);
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

}
