/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.service.migrator.report;

import java.util.ArrayList;
import java.util.List;

import gov.nih.nci.cabig.caaers.dao.report.ReportDefinitionDao;
import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.domain.factory.ReportFactory;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.domain.report.ReportDelivery;
import gov.nih.nci.cabig.caaers.domain.report.ReportDeliveryDefinition;
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
    	
    	 if(srcReports == null || srcReports.size() == 0 )  {
             outcome.addError("ER-RM-1", "Report Definitions are missing from the Source.");
             return;
         }
    	 // Load site to which the study belongs. 
    	 /*StudySite site = null;
     	
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
         }*/
    	 
    	 // Iterate through the Source Reports.
    	 for ( Report rpt : srcReports ) {
    		 
    		ReportDefinition repDef = loadReportDefinition(rpt);
    		
    		if ( repDef == null ) {
                outcome.addError("ER-RM-4", "Unable to Load Report Definition for " +  rpt.getReportDefinition().getName());
                break;
    		}
    		if ( repDef != null ) {
    			Report newReport = repDef.createReport();
    			 if ( newReport != null ) {
    				 copyReportDetailsFromInput(rpt, newReport);
    				 aeReportDest.addReport(newReport);
    			 } else {
    				 outcome.addError("ER-RM-5", "Unable to create a New Report for " +  rpt.getReportDefinition().getName());
    	    		break; 
    			 }
    			
    		} 
    	 }
    	 
    	 if ( outcome.hasErrors()) { // If the outcome has any errors during previous steps then return.
    		 return;
    	 }
    }
	

	   private ReportDefinition getReportDefinition(Report report, ReportDefinition rd) throws Exception {
		   ReportDefinition reportDefinition = new ReportDefinition();
		   reportDefinition.setId(rd.getId());
		   reportDefinition.setDuration(rd.getDuration());
		   reportDefinition.setDescription(rd.getDescription());
		   reportDefinition.setLabel(rd.getLabel());
		   reportDefinition.setHeader(rd.getHeader());
		   reportDefinition.setFooter(rd.getFooter());
		   reportDefinition.setTimeScaleUnitType(rd.getTimeScaleUnitType());
		   reportDefinition.setGroup(rd.getGroup());
        reportDefinition.setDeliveryDefinitionsInternal(adjustDeliveryDefinitions(report, rd.getDeliveryDefinitions()));
		   return reportDefinition;
	   }

     /**
      * This method adds the deliveryStatus to every Delivery definition
      * The delivery status is computed from Report.deliveries.deliveryStatus
      * with delivery.reportDeliveryDefinition = current ReportDeliveryDefinition
      *
      * */
     private List<ReportDeliveryDefinition> adjustDeliveryDefinitions(Report report, List<ReportDeliveryDefinition> ddl) {
         if (report == null) return ddl;
         List<ReportDeliveryDefinition> rddList = new ArrayList<ReportDeliveryDefinition>();
         for (ReportDeliveryDefinition rdd : ddl) {
             rddList.add(ReportDeliveryDefinition.copy(rdd));
         }
         return rddList;
     }
	
	/**
	 *  load existing report definition from database.
	 * @param report
	 * @param orgId
	 * @return
	 */
	private ReportDefinition loadReportDefinition(Report report) {
		  ReportDefinition reportDefinition =  rdDao.getByName(report.getReportDefinition().getName());
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
			if ( src.getReportDefinition().getReportType() != null && src.deriveAdeersReportTypeIndicator() != null )
				dest.setAdeersReportTypeIndicator(src.deriveAdeersReportTypeIndicator());
			if ( src.getEmailRecipients() != null)
		   dest.setEmailAddresses(src.getEmailRecipients());		
	}
	
}
