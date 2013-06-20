/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.service.migrator.report;

import java.util.ArrayList;
import java.util.List;

import com.aparzev.util.CollectionUtils;
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

	public void migrate(ExpeditedAdverseEventReport aeReportSrc, ExpeditedAdverseEventReport aeReportDest, DomainObjectImportOutcome<ExpeditedAdverseEventReport> outcome) {
    	
		List<Report> srcReports = aeReportSrc.getReports();
    	
    	 if(CollectionUtils.isEmpty(srcReports) )  {
             outcome.addError("ER-RM-1", "Report Definitions are missing from the Source.");
             return;
         }
    	 
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
	

	/**
	 *  load existing report definition from database.
	 * @param report
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
            dest.setReviewStatus(src.getReviewStatus());
            dest.setManuallySelected(src.isManuallySelected());
            dest.setPhysicianSignoff(src.getPhysicianSignoff());
            dest.setCaseNumber(src.getCaseNumber());
            dest.setEmailAddresses(src.getEmailAddresses());
            dest.setAssignedIdentifer(src.getAssignedIdentifer());
            dest.getLastVersion().setCcEmails(src.getLastVersion().getCcEmails());
	}

    private void copySubmitterDetails(Report src, Report dest){

    }
	
}
