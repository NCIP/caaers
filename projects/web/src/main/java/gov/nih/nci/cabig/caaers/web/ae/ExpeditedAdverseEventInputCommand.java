package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Attribution;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Collection;

/**
 * @author Rhett Sutphin
 * @author <a href="mailto:biju.joseph@semanticbits.com">Biju Joseph</a>
 */
public interface ExpeditedAdverseEventInputCommand extends AdverseEventInputCommand {
    String COURSE_AGENT_ATTRIBUTION_KEY = "courseAgent";
    String CONCOMITANT_MEDICATIONS_ATTRIBUTION_KEY = "conMed";
    String OTHER_CAUSES_ATTRIBUTION_KEY = "other";
    String DISEASE_ATTRIBUTION_KEY = "disease";
    String SURGERY_ATTRIBUTION_KEY = "surgery";
    String RADIATION_ATTRIBUTION_KEY = "radiation";

    ExpeditedAdverseEventReport getAeReport();

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
}
