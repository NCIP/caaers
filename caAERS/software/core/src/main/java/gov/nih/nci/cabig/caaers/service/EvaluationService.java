package gov.nih.nci.cabig.caaers.service;

import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.dto.ApplicableReportDefinitionsDTO;
import gov.nih.nci.cabig.caaers.domain.dto.EvaluationResultDTO;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.validation.ValidationErrors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;

/**
 * This service interface is used to implement various caaers business rules.
 * 
 * @author Rhett Sutphin
 */
public interface EvaluationService {

    /**
     * Will return the ReportDefinition that are marked required at rules engine.
     * 
     * @param expeditedData -
     *                The expedited adverse event report
     * @return - A list of {@link ReportDefinition} objects.
     */
    List<ReportDefinition> findRequiredReportDefinitions(ExpeditedAdverseEventReport expeditedData, List<AdverseEvent> aeList,Study study);
    
    /**
     * 
     * @param expeditedData
     * @param reportDefinitions
     * @return All the mandatory sections for a given expedited report.
     */
    Map<Integer, Collection<ExpeditedReportSection>> mandatorySections(ExpeditedAdverseEventReport expeditedData, ReportDefinition... reportDefinitions);

    /**
     * This method will find all the report definitions applicable to the Study
     */
    ApplicableReportDefinitionsDTO applicableReportDefinitions(Study study, StudyParticipantAssignment assignment);

    /**
     * Runs through the Business rules set at "FundingSponsor" level, for the section.
     * 
     * @param aeReport
     * @param sectionName
     * @return - {@link ValidationErrors}, that contains the errors.
     */
    ValidationErrors validateReportingBusinessRules(ExpeditedAdverseEventReport aeReport,ExpeditedReportSection... sections);

    /**
     * This method evaluates the SAE reporting rules on the reporting period.
     * @param reportingPeriod
     * @return
     */
	EvaluationResultDTO evaluateSAERules(AdverseEventReportingPeriod reportingPeriod);

}
