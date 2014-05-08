/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.service;

import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.domain.dto.ApplicableReportDefinitionsDTO;
import gov.nih.nci.cabig.caaers.domain.dto.EvaluationResultDTO;
import gov.nih.nci.cabig.caaers.domain.dto.SafetyRuleEvaluationResultDTO;
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
 * @author Biju Joseph
 */
public interface EvaluationService {


    /**
     * Will find the mandatory sections associated with the ExpeditedAdverseEventReport for the specific ReportDefinition
     * @param expeditedData - ExpeditedAdverseEventReport in context
     * @param reportDefinitions - The ReportDefinition whose mandatory fields to be evaluated
     * @return
     */
    Map<Integer, Collection<ExpeditedReportSection>> mandatorySections(ExpeditedAdverseEventReport expeditedData, ReportDefinition... reportDefinitions);

    /**
     * This method will find all the report definitions applicable to the Study
     * @param study - The study in context
     * @param assignment - The StudyParticipantAssignment in context
     * @return
     */
    ApplicableReportDefinitionsDTO applicableReportDefinitions(Study study, StudyParticipantAssignment assignment);

    /**
     * Runs through the Business rules set at "FundingSponsor" level, for the section.
     * 
     * @param aeReport - The ExpeditedAdverseEventReport  in context
     * @param sections  - The ExpeditedReportSection to included in the validation
     * @return - {@link ValidationErrors}, that contains the errors.
     */
    ValidationErrors validateReportingBusinessRules(ExpeditedAdverseEventReport aeReport,ExpeditedReportSection... sections);

    /**
     * This method evaluates the SAE reporting rules on the reporting period.
     * @param reportingPeriod
     * @return
     */
	EvaluationResultDTO evaluateSAERules(AdverseEventReportingPeriod reportingPeriod);

   
    /**
     * Evaluate the mandatoryness of a specific report, the {@link gov.nih.nci.cabig.caaers.domain.report.ReportMandatoryField} will be populated in the Report. 
     * @param report
     */
    void evaluateMandatoryness(ExpeditedAdverseEventReport aeReport, Report report);

    /**
     * Will evaluate the safety monitoring rules for the study
     * @param observedAEProfile
     * @return
     */
    SafetyRuleEvaluationResultDTO evaluateSafetySignallingRules(ObservedAdverseEventProfile observedAEProfile);
    
    Map<AdverseEvent,List<ReportDefinition>> getAdverseEventRecommendedReportsMap();
}
