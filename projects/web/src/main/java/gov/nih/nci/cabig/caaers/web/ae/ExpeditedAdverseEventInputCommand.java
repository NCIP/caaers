package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Attribution;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;

import java.util.List;
import java.util.Map;

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

    void reassociate();

    List<String> getMandatorySections();
    void setMandatorySections(List<String> sections);

    void refreshMandatoryFieldMap();
    public Map<String, Boolean> getMandatoryFieldMap();
}
