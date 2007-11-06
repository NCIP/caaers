package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.dao.ExpeditedAdverseEventReportDao;
import gov.nih.nci.cabig.caaers.dao.report.ReportDefinitionDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.Attribution;
import gov.nih.nci.cabig.caaers.domain.CourseAgent;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.ReportStatus;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyAgent;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.TreatmentInformation;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportTree;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.TreeNode;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.domain.report.ReportMandatoryFieldDefinition;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

    private ExpeditedAdverseEventReport aeReport;
    private Map<String, List<List<Attribution>>> attributionMap;
    private Map<ReportDefinition, Boolean> optionalReportDefinitionsMap;
    
    protected Collection<ExpeditedReportSection> mandatorySections;
    protected MandatoryProperties mandatoryProperties;

    protected ExpeditedAdverseEventReportDao reportDao;
    protected ReportDefinitionDao reportDefinitionDao;
    protected ExpeditedReportTree expeditedReportTree;

    protected Map<String, Boolean> mandatoryFieldMap = new HashMap<String, Boolean>();

    private String treatmentDescriptionType;
    private List<ReportDefinition> allReportDefinitions;
    private List<ReportDefinition> requiredReportDefinitions; //report definitions identified by rules engine

    public AbstractExpeditedAdverseEventInputCommand(ExpeditedAdverseEventReportDao reportDao, ReportDefinitionDao reportDefinitionDao, ExpeditedReportTree expeditedReportTree) {
        this.reportDao = reportDao;
        this.reportDefinitionDao = reportDefinitionDao;
        this.expeditedReportTree = expeditedReportTree;
        this.optionalReportDefinitionsMap = new LinkedHashMap<ReportDefinition, Boolean>();
        this.requiredReportDefinitions = new ArrayList<ReportDefinition>();
    }

    public abstract StudyParticipantAssignment getAssignment();

    public abstract Participant getParticipant();

    public abstract Study getStudy();

    public abstract void save();

    public void reassociate() {
        for (ReportDefinition definition : getOptionalReportDefinitionsMap().keySet()) {
            reportDefinitionDao.reassociate(definition);
        }
        if (getAeReport().getId() != null) {
            ExpeditedAdverseEventReport merged = reportDao.merge(getAeReport());
            setAeReport(merged);
        }
    }
    
    public void refreshSelectedReportDefinitionsMap(List<ReportDefinition> defs){
    	//deselect all previously selected report
    	for(Map.Entry<ReportDefinition, Boolean>  entry: optionalReportDefinitionsMap.entrySet()){
			entry.setValue(false);
		}
    	setSelectedReportDefinitions(defs);
    }
    
    public void setOptionalReportDefinitions(List<ReportDefinition> defs) {
    	if(defs == null || defs.isEmpty()) return;
    	for (ReportDefinition def : defs) {
                optionalReportDefinitionsMap.put(def, false);
        }
        // Deliberately not removing entries from the map that aren't in defs.
        // This is so that the user may still remove Reports whose ReportDefinitions
        // are no longer associated with the study.
    }

    public Map<ReportDefinition, Boolean> getOptionalReportDefinitionsMap() {
        return optionalReportDefinitionsMap;
    }
    

    public void setAeReport(ExpeditedAdverseEventReport aeReport) {
        if (aeReport.getAdverseEvents().size() == 0) {
            aeReport.addAdverseEvent(new AdverseEvent());
        }
        if (aeReport.getTreatmentInformation() == null) {
            aeReport.setTreatmentInformation(new TreatmentInformation());
        }
        this.aeReport = aeReport;
        this.attributionMap = new AttributionMap(aeReport);
        updateOptionalReportDefinitionsMapFromAeReport();
    }

    private void updateOptionalReportDefinitionsMapFromAeReport() {
        Set<ReportDefinition> defsInAeReport = new HashSet<ReportDefinition>();
        for (Report report : this.getAeReport().getReports()) {
            log.debug("Found Report in new aeReport: "
                + report.getReportDefinition().getName() + ' ' + report.getId());
            log.debug("Report def hashCode is " + Integer.toHexString(report.getReportDefinition().hashCode()));
            defsInAeReport.add(report.getReportDefinition());
            optionalReportDefinitionsMap.put(report.getReportDefinition(), true);
            log.debug("Report is not required, so added to optional reports map: " + optionalReportDefinitionsMap);
        }
        // deselect any definitions which were already in the map but not in the aeReport
        for (ReportDefinition inMap : optionalReportDefinitionsMap.keySet()) {
            if (!defsInAeReport.contains(inMap)) optionalReportDefinitionsMap.put(inMap, false);
        }
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
            for (ReportMandatoryFieldDefinition field : report.getReportDefinition().getMandatoryFields()) {
                mandatoryProperties.add(field);
            }
        }
    }
    
    /** 
     * The repeating fields available in the mandatory sections will be pre-initialized here.
     */
    public void initializeMandatorySectionFields(ExpeditedReportTree tree) {
    	if(mandatorySections == null || mandatorySections.isEmpty()){
    		log.info("No mandatory sections available, so no fields will be pre initialized");
    		return;
    	}
    	
    	//pre-initialize lazy fields in mandatory sections.
    	BeanWrapper wrapper = new BeanWrapperImpl(getAeReport());
        for(ExpeditedReportSection section :getMandatorySections()){
        	assert (section != null) : "A section is null in command.getManatorySections()";
        	
        	TreeNode sectionNode = tree.getNodeForSection(section);
        	if(sectionNode == null) log.warn("Unable to fetch TreeNode for section" + section.name());
        	
        	assert (sectionNode != null) : section.toString() + ", is not available in ExpeditedReportTree.";
        	if(sectionNode.getChildren() == null) continue;
        	
        	for(TreeNode node : sectionNode.getChildren()){
        		if(node.isList()){
        			log.info("Initialized '" + node.getPropertyName() + "' in section " + section.name());
        			wrapper.getPropertyValue(node.getPropertyName()+"[0]");
        		}
        	}
        	
        	
        	//special case, when TreatmentInformation (course&agents tab) is mandatory.
        	//All StudyAgents associated with lead IND should be pre-initialized.
        	if(ExpeditedReportSection.TREATMENT_INFO_SECTION.equals(section)){
        		List<CourseAgent> courseAgents = (List<CourseAgent>)wrapper.getPropertyValue(sectionNode.getChildren().get(0).getPropertyName() + ".courseAgents");
        		if(courseAgents.size() <= 0){
        			//first time, the user did not override system pre selection.
        			int i = 0; 
        			for(StudyAgent agent : getAeReport().getStudy().getStudyAgents()){
        				if(agent.getPartOfLeadIND()!= null && agent.getPartOfLeadIND()){
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
	 * Returns all the {@link ReportDefinition} available to this AE
	 */
	public List<ReportDefinition> getAllReportDefinitions() {
		return allReportDefinitions;
	}
	public void setAllReportDefinitions(
			List<ReportDefinition> allReportDefinitions) {
		this.allReportDefinitions = allReportDefinitions;
	}
	
	/**
	 * This method will return the {@link ReportDefinition} that are instantiated
	 */
	public List<ReportDefinition> getInstantiatedReportDefinitions(){
		List<ReportDefinition> reportDefs = new ArrayList<ReportDefinition>();
		for(Report report : aeReport.getReports()){
			if(!report.getStatus().equals(ReportStatus.WITHDRAWN))
				reportDefs.add(report.getReportDefinition());
		}
		
		return reportDefs;

	}
	/**
	 * This method will return the ReportDefinition which are selected by user 
	 * in the checkpoint page.
	 */
	public List<ReportDefinition> getSelectedReportDefinitions() {
		List<ReportDefinition> reportDefs = new ArrayList<ReportDefinition>();	
		for(Map.Entry<ReportDefinition, Boolean>  entry: optionalReportDefinitionsMap.entrySet()){
			if(entry.getValue() != null && entry.getValue()) reportDefs.add(entry.getKey());
		}
		return reportDefs;
	}
	
	public void setSelectedReportDefinitions(List<ReportDefinition> defs) {
		if(defs == null || defs.isEmpty()) return;
    	for (ReportDefinition def : defs) {
    		optionalReportDefinitionsMap.put(def, true);
        }
	}

	public void setSelectedReportDefinitionNames(String selectedNames){
		if(selectedNames == null || selectedNames.length() < 1) return;
		String[] names = selectedNames.split(",");
		String name = null;
		Map<ReportDefinition, Boolean> map = new LinkedHashMap<ReportDefinition, Boolean>();
		for(Map.Entry<ReportDefinition, Boolean> entry: optionalReportDefinitionsMap.entrySet()){
			name = "optionalReportDefinitionsMap[" + entry.getKey().getId() + "]";
			if(ArrayUtils.contains(names, name)){
				map.put(entry.getKey(), true);
			}else{
				map.put(entry.getKey(), false);
			}
		}
		//add all
		optionalReportDefinitionsMap.putAll(map);
	}
	
	public String getRequiredReportDefinitionNames(){
		return getReportDefinitionNames(getRequiredReportDeifnitions());
	}
	
	public String getSelectedReportDefinitionNames() {
		return getReportDefinitionNames(getSelectedReportDefinitions());
	}
	
	public List<ReportDefinition> getRequiredReportDeifnitions() {
		return requiredReportDefinitions;
	}
	
	public void setRequiredReportDefinition(List<ReportDefinition> defs) {
		if(defs != null) requiredReportDefinitions.addAll(defs);
	}
	
	public Collection<ReportDefinition> getSubmittedReportDefinitions() {
		Set<ReportDefinition> defs = new HashSet<ReportDefinition>();
		if(getAeReport().getReports() != null){
			for(Report report : getAeReport().getReports()){
				if(report.getLastVersion().getReportStatus().equals(ReportStatus.COMPLETED)){
					defs.add(report.getReportDefinition());
				}
			}
		}
		return null;
	}
	
	public boolean getIgnoreCompletedStudy() {
		return true;
	}
	
    @Override
    public String toString() {
        return new StringBuilder(getClass().getName())
            .append("[\n    aeReport: ").append(getAeReport())
            .append("\n    optionalReportDefinitionsMap: ").append(getOptionalReportDefinitionsMap())
            // TODO: This line is throwing an NPE sometimes.  Figure out why.
            // .append("\n    attributionMap: ").append(getAttributionMap())
            .append("\n]").toString();
    }
    
    private String getReportDefinitionNames(List<ReportDefinition> defs){
    	if(defs == null || defs.isEmpty()) return "" ;
		StringBuilder sb = new StringBuilder();
		for(ReportDefinition def : defs){
			sb.append("optionalReportDefinitionsMap[" + def.getId() + "]").append(",");
		}
		return sb.toString();
	}
}
