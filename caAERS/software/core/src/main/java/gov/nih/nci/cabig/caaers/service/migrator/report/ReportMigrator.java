package gov.nih.nci.cabig.caaers.service.migrator.report;

import java.util.List;

import gov.nih.nci.cabig.caaers.dao.report.ReportDefinitionDao;
import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.domain.factory.ReportFactory;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.service.migrator.Migrator;

/**
 * User:medaV
 * Date: 1/28/13
 */
public class ReportMigrator implements Migrator<ExpeditedAdverseEventReport> {
	
	  /** The report factory. **/
    private ReportFactory reportFactory;
    
    /** Report Definition DAO **/
    ReportDefinitionDao rdDao;
    
    public ReportFactory getReportFactory() {
		return reportFactory;
	}

	public void setReportFactory(ReportFactory reportFactory) {
		this.reportFactory = reportFactory;
	}

	
	public ReportDefinitionDao getRdDao() {
		return rdDao;
	}

	public void setRdDao(ReportDefinitionDao rdDao) {
		this.rdDao = rdDao;
	}

	public void migrate(ExpeditedAdverseEventReport aeReportSrc, ExpeditedAdverseEventReport aeReportDest, 
			DomainObjectImportOutcome<ExpeditedAdverseEventReport> outcome) {
    	
		List<Report> srcReports = aeReportSrc.getReports();
    	
    	if ( aeReportDest.getPhysician() == null ) aeReportDest.setReporter(new Reporter());
    	
    	 if(srcReports == null || srcReports.size() == 0 )  {
             outcome.addError("ER-RM-1", "Report Definitions are missing from the Source.");
             return;
         }
    	 // Load site to which the study belongs. 
    	 StudySite site = null;
     	
         if ( aeReportDest.getReportingPeriod() != null && aeReportDest.getReportingPeriod().getAssignment() != null ) {
         	site = aeReportDest.getReportingPeriod().getAssignment().getStudySite();
         }
         
         if ( site == null ) {
         	 outcome.addError("ER-RM-2", "Study Site is missing in the source.");
              return;
         }
         
         Organization org = site.getOrganization();
         
         if ( org == null ) {
        	 outcome.addError("ER-RM-3", "Organization is missing in the Study Site.");
        	 return;
         }
    	 
    	 // Iterate through the Source Reports.
    	 for ( Report rpt : srcReports ) {
    		 
    		ReportDefinition repDef = loadReportDefinition(rpt, org.getId());
    		if ( repDef != null ) {
    			Report newReport = reportFactory.createReport(repDef, aeReportDest, repDef.getBaseDate());
    			 if ( newReport != null ) {
    				 aeReportDest.addReport(newReport);
    				 copyReportDetailsFromInput(rpt, newReport);
    			 } else {
    				outcome.addError("ER-RM-4", "Unable to Create the Report Definition for ");
    	    		break; 
    			 }
    			
    		} else {
    			outcome.addError("ER-RM-5", "Unable to find Report Definition for " +  rpt.getReportDefinition().getName()  + " Org Id : " + org.getId());
    			break;
    		}
    	 }
    	 
    	 if ( outcome.hasErrors()) { // If the outcome has any errors during previous steps then return.
    		 return;
    	 }
    }
	/**
	 *  load existing report definition from database.
	 * @param report
	 * @param orgId
	 * @return
	 */
	private ReportDefinition loadReportDefinition(Report report, int orgId) {
		  ReportDefinition reportDefinition =  rdDao.getByName(report.getReportDefinition().getName(), orgId);
		  return reportDefinition;
	}
	
	/**
	 * Copy the Report Details from Input to Output.
	 * @param src
	 * @param dest
	 */
	private void copyReportDetailsFromInput(Report src, Report dest) {
			if ( src.getSubmissionMessage() != null )
		   dest.setSubmissionMessage(src.getSubmissionMessage());
			if ( src.getSubmittedOn() != null )
           dest.setSubmittedOn(src.getSubmittedOn());
			if ( src.getStatus() != null )
           dest.setStatus(src.getStatus());
			if ( src.deriveAdeersReportTypeIndicator() != null )
		   dest.setAdeersReportTypeIndicator(src.deriveAdeersReportTypeIndicator());
			if ( src.getAssignedIdentifer() != null )
		   dest.setAssignedIdentifer(src.getAssignedIdentifer());
			if ( src.getEmailRecipients() != null)
		   dest.setEmailAddresses(src.getEmailRecipients());		
	}
	
}
