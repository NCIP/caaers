package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Attribution;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;

import java.util.List;
import java.util.Map;

/**
 * @author Rhett Sutphin
 */
public interface ExpeditedAdverseEventInputCommand extends AdverseEventInputCommand {
    String COURSE_AGENT_ATTRIBUTION_KEY = "courseAgent";
    String CONCOMITANT_MEDICATIONS_ATTRIBUTION_KEY = "conMed";
    String OTHER_CAUSES_ATTRIBUTION_KEY = "other";

    ExpeditedAdverseEventReport getAeReport();

    /* attributionMap[attributionKey][ae index][cause index]; indexes are the same as the equivs
     * in AdverseEventReport and AdverseEvent */
    Map<String, List<List<Attribution>>> getAttributionMap();

    Map<ReportDefinition, Boolean> getOptionalReportDefinitionsMap();

    void setOptionalReportDefinitions(List<ReportDefinition> defs);

    void reassociate();
}
