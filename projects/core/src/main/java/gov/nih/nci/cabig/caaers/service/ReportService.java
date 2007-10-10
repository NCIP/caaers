package gov.nih.nci.cabig.caaers.service;

import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;

import java.util.Collection;

/**
 * This is an service class, which is used to obtain the correct address (toAddress) of a
 * recipient.
 *
 * @author <a href="mailto:biju.joseph@semanticbits.com">Biju Joseph</a>
 * Created-on : May 31, 2007
 * @version     %I%, %G%
 * @since       1.0
 */
public interface ReportService {


   /**
    * Applies runtime variable replacements, with the values availabe in report.
    * Note: Uses freemarker based text replacements
    */
   String applyRuntimeReplacements(String rawContent, Report report);

   /**
    * Creates a report from the given definition and associates it with the
    * given aeReport and saves it in the database.
    *
    * Also it will schedule the report.
    */
   Report createReport(ReportDefinition repDef, ExpeditedAdverseEventReport aeReport);

   /**
    * Will mark the report as deleted (ReportStatus = WITHDRAWN).
    * At present it will unschedule the pending scheduled notifications present in the scheduler, by
    * delegating the call to SchedulerService.
    */
   void deleteReport(Report report);

    /**
    * Will tell whether all the mandatory field for this report is duly filled.
    * @return ErrorMessages, if any.
    */
   ReportSubmittability validate(Report report, Collection<ExpeditedReportSection> mandatorySections);

   /**
    * Will withdraw the latest version of the report.
    * @param report
    */
   void withdrawLastReportVersion(Report report);
   
}