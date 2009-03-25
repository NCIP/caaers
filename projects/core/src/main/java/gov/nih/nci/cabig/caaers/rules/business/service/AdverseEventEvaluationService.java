package gov.nih.nci.cabig.caaers.rules.business.service;

import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.validation.ValidationErrors;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author vinaykumar The RulesEngineService is a facade for authoring, deploying and executing the
 *         rules
 */
public interface AdverseEventEvaluationService {
    String assesAdverseEvent(AdverseEvent ae, Study study) throws Exception;

    // public String identifyAdverseEventType()

    Map<String, List<String>> evaluateSAEReportSchedule(ExpeditedAdverseEventReport aeReport, List<AdverseEvent> aeList,
    		Study study) throws Exception;

    Collection<ExpeditedReportSection> mandatorySections(ExpeditedAdverseEventReport expeditedData, ReportDefinition... reportDefinitions)
                    throws Exception;

    /**
     * Will return the mandatory sections available for a specific report
     * 
     * @param report
     * @return
     * @throws Exception
     */
    Collection<ExpeditedReportSection> mandatorySectionsForReport(ExpeditedAdverseEventReport aeReport, ReportDefinition reportDefinition) throws Exception;

    /**
     * This method will execute the business rules set for SAE flow, at funding sponsor level.
     * 
     * @param aeReport
     * @param section
     * @return
     * @throws Exception
     */
    ValidationErrors validateReportingBusinessRules(ExpeditedAdverseEventReport aeReport,ExpeditedReportSection... sections);
}
