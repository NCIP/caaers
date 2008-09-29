package gov.nih.nci.cabig.caaers.domain.repository;

import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.service.ReportSubmittability;

import java.util.Collection;

/**
 * This is an service class, which is used to obtain the correct address (toAddress) of a recipient.
 *
 * @author <a href="mailto:biju.joseph@semanticbits.com">Biju Joseph</a> Created-on : May 31, 2007
 * @version Biju Joseph
 * @since 1.0
 */
public interface ReportRepository {
    /**
     * Will mark the report as deleted (ReportStatus = WITHDRAWN). At present it will unschedule the
     * pending scheduled notifications present in the scheduler, by delegating the call to
     * SchedulerService.
     */
    void deleteReport(Report report);

    /**
     * Creates a report from the given definition and associates it with the given aeReport and
     * saves it in the database.
     * <p/>
     * Also it will schedule the report.
     */
    Report createReport(ReportDefinition repDef, ExpeditedAdverseEventReport aeReport, Boolean useDefaultVersion);

    /**
     * Will withdraw the latest version of the report.
     *
     * @param report
     */
    void withdrawLastReportVersion(Report report);

    /**
     * Will tell whether all the mandatory field for this report is duly filled. This method will
     * validate against all the sections defined in {@link gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection}
     *
     * @return ErrorMessages, if any.
     */
    ReportSubmittability validate(Report report);

    /**
     * This method amends the report passed to it. It initiates the associated notifications and increments
     * the versionId too.
     */
    
    void amendReport(Report report, Boolean useDefaultVersion);
    
    /**
     * Will tell whether all the mandatory field for this report is duly filled.
     *
     * @return ErrorMessages, if any.
     */
    ReportSubmittability validate(Report report, Collection<ExpeditedReportSection> mandatorySections);
    
    /**
     * Will intialize the mandatory fields
     * @param aeReport
     * @param mandatorySections
     */
    public void initializeMandatorySectionFields(ExpeditedAdverseEventReport aeReport, Collection<ExpeditedReportSection> mandatorySections);

}
