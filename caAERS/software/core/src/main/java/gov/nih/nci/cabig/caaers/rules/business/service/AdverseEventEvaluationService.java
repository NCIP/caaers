package gov.nih.nci.cabig.caaers.rules.business.service;

import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.domain.report.ReportMandatoryField;
import gov.nih.nci.cabig.caaers.domain.report.ReportMandatoryFieldDefinition;
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
    /**
     * Assess a particular adverse event, by evaluating the rules defined.
     * @param ae - The AdverseEvent  to evaluate
     * @param study - The Study in context
     * @return
     * @throws Exception
     */
    String assesAdverseEvent(AdverseEvent ae, Study study) throws Exception;


    /**
     * Evaluate the Rules defined in the context of the report and for the specified adverse events. 
     *
     * @param aeReport  - The ExpeditedAdverseEventReport  in context
     * @param aeList    - The list of AdverseEvent  to evaluate
     * @param study     - The Study in context
     * @return
     * @throws Exception
     */
    Map<AdverseEvent, List<String>> evaluateSAEReportSchedule(ExpeditedAdverseEventReport aeReport, List<AdverseEvent> aeList,
    		Study study) throws Exception;

    /**
     * The mandatory sections associated with the report. 
     * @param expeditedData  - The ExpeditedAdverseEventReport in context
     * @param reportDefinitions  - The ReportDefinitions whose mandatory sections to be considered. 
     * @return
     * @throws Exception
     */
    Collection<ExpeditedReportSection> mandatorySections(ExpeditedAdverseEventReport expeditedData, ReportDefinition... reportDefinitions)
                    throws Exception;

    /**
     * The mandatory sections associated with the report.
     * @param aeReport  - The ExpeditedAdverseEventReport in context
     * @param reportDefinition  - The ReportDefinitions whose mandatory sections to be considered. 
     * @return
     * @throws Exception
     */
    Collection<ExpeditedReportSection> mandatorySectionsForReport(ExpeditedAdverseEventReport aeReport, ReportDefinition reportDefinition) throws Exception;

    /**
     * Validates the business rules associated with the Report. 
     * @param aeReport - The ExpeditedAdverseEventReport  in context
     * @param sections - The ExpeditedReportSection to evaluate
     * @return
     */
    ValidationErrors validateReportingBusinessRules(ExpeditedAdverseEventReport aeReport,ExpeditedReportSection... sections);

    /**
     * Evaluate the field level rules, and identify the mandatory fields associated with the report. 
     * @param aeReport  - The ExpeditedAdverseEventReport in context
     * @param report    - The Report in context
     * @param mandatoryFieldDefinition  - The mandatory sections to consider.
     * @return
     */
    String evaluateFieldLevelRules(ExpeditedAdverseEventReport aeReport, Report report, ReportMandatoryFieldDefinition mandatoryFieldDefinition);

    /**
     * Will evaluate the field level rule. 
     * @param bindURL  - The url where the field level rule is registered
     * @param ruleName  - The rule name
     * @param inputObjects - The input objects to evaluate. 
     * @return
     */
    String evaluateFieldLevelRules(String bindURL, String ruleName, List<Object> inputObjects);

}
