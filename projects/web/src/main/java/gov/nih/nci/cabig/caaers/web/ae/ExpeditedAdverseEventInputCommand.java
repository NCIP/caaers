package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.Attribution;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportTree;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Rhett Sutphin
 * @author <a href="mailto:biju.joseph@semanticbits.com">Biju Joseph</a>
 */
public interface ExpeditedAdverseEventInputCommand extends AdverseEventInputCommand {
	Integer ZERO = new Integer(0);
    String COURSE_AGENT_ATTRIBUTION_KEY = "courseAgent";
    String CONCOMITANT_MEDICATIONS_ATTRIBUTION_KEY = "conMed";
    String OTHER_CAUSES_ATTRIBUTION_KEY = "other";
    String DISEASE_ATTRIBUTION_KEY = "disease";
    String SURGERY_ATTRIBUTION_KEY = "surgery";
    String RADIATION_ATTRIBUTION_KEY = "radiation";
    String DEVICE_ATTRIBUTION_KEY = "device";

    ExpeditedAdverseEventReport getAeReport();
    
    public Map<String, Boolean> getOutcomes();
    public void setOutcomes(Map<String, Boolean> outcomes);
    public void updateOutcomes();
    public Date getOutcomeDate();
	//public void setOutcomeDate(Date outcomeDate); 
	public String getOtherOutcome();

    /* attributionMap[attributionKey][ae index][cause index]; indexes are the same as the equivs
     * in AdverseEventReport and AdverseEvent */
    Map<String, List<List<Attribution>>> getAttributionMap();

    Map<ReportDefinition, Boolean> getOptionalReportDefinitionsMap();
    
    void setOptionalReportDefinitions(List<ReportDefinition> defs);
    void refreshSelectedReportDefinitionsMap(List<ReportDefinition> defs);

    void reassociate();

    Collection<ExpeditedReportSection> getMandatorySections();
    void setMandatorySections(Collection<ExpeditedReportSection> sections);

    //TODO: the caller should use the equivalent method in report service.
    @Deprecated
    void refreshMandatoryProperties();
    
    /**
     * Pre-initalize the mandatory lazy added fields in mandatory sections. 
     * (This is a biz-rule)
     */
    void initializeMandatorySectionFields(ExpeditedReportTree tree);
    
    MandatoryProperties getMandatoryProperties();
    
	 List<ReportDefinition> getAllReportDefinitions();
	 void setAllReportDefinitions(List<ReportDefinition> allReportDefinitions); 
	 List<ReportDefinition> getSelectedReportDefinitions();
	 void setSelectedReportDefinitions(List<ReportDefinition> selectedReportDefinitions);
	 
	 List<ReportDefinition> getInstantiatedReportDefinitions();
	 void setSelectedReportDefinitionNames(String selectedNames);
	 
	 List<ReportDefinition> getRequiredReportDeifnitions();
	 void setRequiredReportDefinition(List<ReportDefinition> def);
	 String getRequiredReportDefinitionNames();
	 String getSelectedReportDefinitionNames();
	 
	 /**
	  * Reports which are already submitted.
	  * @return
	  */
	 Collection<ReportDefinition> getSubmittedReportDefinitions(); 
	 
	 void setNextPage(int page);
	 int getNextPage();
	 
}
