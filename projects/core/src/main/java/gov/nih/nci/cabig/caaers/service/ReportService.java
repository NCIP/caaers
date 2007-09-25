package gov.nih.nci.cabig.caaers.service;

import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.TreeNode;
import gov.nih.nci.cabig.caaers.service.ErrorMessages;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;

import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanWrapper;


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
    * Will mark the report as deleted (ReportStatus = WITHDRAWN).
    * At present it will unschedule the pending scheduled notifications present in the scheduler, by
    * delegating the call to SchedulerService.
    */
   public void deleteReport(Report report);

   /**
    * This method will return a map consisting of the <code>aeReport</code> property (key) and boolean representing whether
    * the property is mandatory.
    * @param aeReport - An {@link ExpeditedAdverseEventReport}
    * @return map
    */
   public Map<String, Boolean> fetchMandatoryFieldMap(ExpeditedAdverseEventReport aeReport);


   /**
    * Will test if the mandatory field is empty. If found empty, then will populate the error details in
    * the <code>messages</code> object.
    * @param bean - The bean to validate
    * @param mandatoryMap - A map, with property name as key and associated boolean
    * value will tell if the field is mandatory
    * @param node - The node, based on which the evaluation is to be performed.
    * @param messages - An error message object
    */
   // TODO: clean this interface up to make it more clear
   public void validate(BeanWrapper bean, Map<String, Boolean> mandatoryMap, TreeNode node, ErrorMessages messages);

   /**
    * Will tell whether all the mandatory field for this report is duly filled.
    * @param aeReport
    * @return ErrorMessages, if any.
    */
   public ErrorMessages isSubmitable(ExpeditedAdverseEventReport aeReport, List<String> mandatorySectionNames);

   /**
    * Will withdraw the latest version of the report.
    * @param report
    */
   public void withdrawLastReportVersion(Report report);
   
}