package gov.nih.nci.cabig.caaers.service;

import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.validation.ValidationErrors;

import java.util.List;
import java.util.Collection;
import java.util.Map;

/**
 * This service interface is used to implement various caaers business rules.
 * 
 * @author Rhett Sutphin
 */
public interface EvaluationService {
    /**
     * @return true if the given adverse event is severe in the context of the provided study, site,
     *         and participant
     */
    boolean isSevere(StudyParticipantAssignment assignment, AdverseEvent adverseEvent);

    /**
     * Checks whether all the mandatory fields, are duly filled. If the report is complete, the
     * ErrorMessages will be empty
     * 
     * @param report -
     *                {@link Report}
     * @return {@link ReportSubmittability}
     */
    // return type based on the method name, is misleading,need to find a better name.
    ReportSubmittability isSubmittable(Report report);
    
    /**
     * Will return a Map consisting of {@link ReportDefinition} as key and List of {@link AdverseEvent} caused as values. 
     * @param reportingPeriod
     * @return
     */
    public Map<ReportDefinition, List<AdverseEvent>> findRequiredReportDefinitions(AdverseEventReportingPeriod reportingPeriod);
    /**
     * Will return the ReportDefinition that are marked required at rules engine.
     * 
     * @param expeditedData -
     *                The expedited adverse event report
     * @return - A list of {@link ReportDefinition} objects.
     */
    List<ReportDefinition> findRequiredReportDefinitions(ExpeditedAdverseEventReport expeditedData, List<AdverseEvent> aeList,Study study);
    
    /**
     * Will return a list of ReportDefinition retaining just the earliest amenable Report Definition
     * 
     * @param Map<String, List<String>> - Where the values are list of reportDefinitionNames
     * @return - A list of {@link ReportDefinition} objects which has just one amenable ReportDefinition (the earliest)
     */
    List<ReportDefinition> filterAmenableReportDefinitions(Map<String, List<String>> map);

    /**
     * Evaluates the provided data and associates new {@link Report} instances with the given
     * {@link ExpeditedAdverseEventReport}.
     * <p>
     * This method may be called multiple times for the same expedited data. Implementors must be
     * sure not to add multiple {@link Report}s for the same {@link ReportDefinition}.
     * Implementors must also <em>not</em> remove
     * {@link gov.nih.nci.cabig.caaers.domain.report.Report}s if they don't evaluate as required
     * (e.g., some reports may have been directly selected by the user). Instead, implementors
     * should update the {@link Report#setRequired} flag.
     * 
     * @param expeditedData
     * @return the report definitions which the evaluation indicated were required.
     */
    // @Deprecated
    // void addRequiredReports(ExpeditedAdverseEventReport expeditedData);
    /**
     * This method will instantiate and saves the optional reports.
     * 
     * @param expeditedData
     * @param reportDefs -
     *                A list of ReportDefinitions
     */
    void addOptionalReports(ExpeditedAdverseEventReport expeditedData,
                    Collection<ReportDefinition> reportDefs);

    /**
     * 
     * @param expeditedData
     * @return All the mandatory sections for a given expedited report.
     */
    Collection<ExpeditedReportSection> mandatorySections(ExpeditedAdverseEventReport expeditedData);

    /**
     * @return All the report definitions which might apply to the given study, site, and
     *         participant
     */
    // TODO: it might more sense for this to go in ReportService
    List<ReportDefinition> applicableReportDefinitions(StudyParticipantAssignment assignment);

    /**
     * Runs through the Business rules set at "FundingSponsor" level, for the section.
     * 
     * @param aeReport
     * @param sectionName
     * @return - {@link ValidationErrors}, that contains the errors.
     */
    ValidationErrors validateReportingBusinessRules(ExpeditedAdverseEventReport aeReport,ExpeditedReportSection... sections);

}
