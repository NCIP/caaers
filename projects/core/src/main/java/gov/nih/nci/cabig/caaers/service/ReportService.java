package gov.nih.nci.cabig.caaers.service;

import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.TreeNode;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;

import java.util.List;
import java.util.Map;

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
    * Will test if the mandatory field is empty. If found empty, then will populate the error details in
    * the <code>messages</code> object.
    * @param aeReport - The bean to validate
    * @param mandatoryMap - A map, with property name as key and associated boolean
    * value will tell if the field is mandatory
    * @param node - The node, based on which the evaluation is to be performed.
    */
   ErrorMessages validate(ExpeditedAdverseEventReport aeReport, TreeNode node, Map<String, Boolean> mandatoryMap);

   /**
    * Will tell whether all the mandatory field for this report is duly filled.
    * @return ErrorMessages, if any.
    */
   ErrorMessages validate(Report report, List<String> mandatorySectionNames);

   /**
    * Will withdraw the latest version of the report.
    * @param report
    */
   void withdrawLastReportVersion(Report report);
   
}