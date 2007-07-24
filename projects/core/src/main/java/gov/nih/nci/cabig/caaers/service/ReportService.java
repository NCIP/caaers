package gov.nih.nci.cabig.caaers.service;

import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.report.PlannedNotification;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;

import java.util.List;


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
    public String applyRuntimeReplacements(String rawContent, Report report);
    
   /**
    * Creates a report from the given definition and associates it with the
    * given aeReport and saves it in the database.
    * 
    * Also it will schedule the report. 
    */
   public Report createReport(ReportDefinition repDef, ExpeditedAdverseEventReport aeReport);
   
   /**
    * Will mark the report as deleted. 
    * At present it will unschedule the pending scheduled notifications present in the scheduler, by 
    * delegating the call to SchedulerService.
    */
   public void deleteReport(Report report);
}