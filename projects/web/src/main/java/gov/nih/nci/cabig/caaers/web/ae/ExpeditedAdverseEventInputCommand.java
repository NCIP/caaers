package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.Attribution;
import gov.nih.nci.cabig.caaers.domain.DiseaseCodeTerm;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Term;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;

import java.util.Collection;
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
    List<Map<Integer, Boolean>> getOutcomes();
    List<String> getOutcomeOtherDetails();
    void updateOutcomes();
    
    /*
     * attributionMap[attributionKey][ae index][cause index]; indexes are the same as the equivs in
     * AdverseEventReport and AdverseEvent
     */
    Map<String, List<List<Attribution>>> getAttributionMap();
    void reassociate();
    Collection<ExpeditedReportSection> getMandatorySections();
    void setMandatorySections(Collection<ExpeditedReportSection> sections);
    boolean isSectionMandatory(ExpeditedReportSection section);
    
    // TODO: the caller should use the equivalent method in report service.
    @Deprecated
    void refreshMandatoryProperties();

    /**
     * Pre-initalize the mandatory lazy added fields in mandatory sections. (This is a biz-rule)
     */
    void initializeMandatorySectionFields();
    MandatoryProperties getMandatoryProperties();
    List<ReportDefinition> getInstantiatedReportDefinitions();
    void setSelectedReportDefinitions(List<ReportDefinition> selectedReportDefinitions);
    List<ReportDefinition> getSelectedReportDefinitions();
    void setNextPage(int page);
    int getNextPage();
    Map<Object, Object> getStudyDiseasesOptions(DiseaseCodeTerm diseaseCodingTerm);
    Term getStudyTerminologyTerm();
    /**
     * If true, the add button on AdverseEvents page in expedited flow will be enabled.
     * @return
     */
    boolean isAdditionAllowed();
    
    void saveReportingPeriod();
}
