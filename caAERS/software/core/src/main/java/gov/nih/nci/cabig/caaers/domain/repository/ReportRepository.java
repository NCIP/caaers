package gov.nih.nci.cabig.caaers.domain.repository;

import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.ReportStatus;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.domain.report.ReportDelivery;
import gov.nih.nci.cabig.caaers.service.ReportSubmittability;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * This is an service class, which is used to obtain the correct address (toAddress) of a recipient.
 *
 * @author <a href="mailto:biju.joseph@semanticbits.com">Biju Joseph</a> Created-on : May 31, 2007
 * @version Biju Joseph
 * @since 1.0
 */
public interface ReportRepository {
	
	/**
	 * This method will amend/unamend/withdraw/create the reports. 
	 * @param aeReport- The expedited report
	 * @param toAmendList - The list of reports to amend
	 * @param toUnAmendList - The list of reports to unamend
	 * @param toWithdrawList - The list of reports to withdraw
	 * @param toCreateList - The list of reports to create
	 */
	void processReports(ExpeditedAdverseEventReport aeReport, List<Report> toAmendList, List<Report> toUnAmendList, 
			List<Report> toWithdrawList,  List<ReportDefinition> toCreateList );
   
	/**
     * Will mark the report as deleted (ReportStatus = WITHDRAWN). At present it will unschedule the
     * pending scheduled notifications present in the scheduler, by delegating the call to
     * SchedulerService.
     */
    void withdrawReport(Report report);
    
    /**
     * Will find the external report to be withdrawn, and will withdraw that report from the system.
     * @param aeReport
     * @param report
     */
    void withdrawExternalReport(ExpeditedAdverseEventReport aeReport,Report report);
    
    /**
     * Creates a report from the given definition and associates it with the given aeReport and
     * saves it in the database.
     * <p/>
     * Also it will schedule the report.
     */
    Report createReport(ReportDefinition repDef, ExpeditedAdverseEventReport aeReport);
    
    /**
     * This method will create all the child reports of the given report.
     * 
     * @param report
     * @return
     */
    List<Report> createChildReports(Report report);
    
    /**
     * This method amends the report passed to it, by changing the status to {@link ReportStatus#AMENDED}
     */
    
    void amendReport(Report report);
    
    /**
     * This method will change the amended report to completed.  
     * @param report
     */
    
    void unAmendReport(Report report);
    
    /**
     * This method will
     *   - Amend existing reports, by creating a new report instance. 
     */
    void createAndAmendReport(ReportDefinition repDef, Report toAmend, Boolean useDefaultVersion);
    
    /**
     * Will find the report deliveries for this report. 
     * Note:- This will directly calculate the current deliveries, instead of what is available in report. 
     * @param report
     * @return
     */
    List<ReportDelivery> findReportDeliveries(Report report);
    
}
