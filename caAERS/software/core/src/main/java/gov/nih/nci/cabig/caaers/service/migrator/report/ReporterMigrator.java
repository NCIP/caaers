package gov.nih.nci.cabig.caaers.service.migrator.report;

import org.apache.axis.utils.StringUtils;

import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.service.migrator.Migrator;

/**
 * User:medaV
 * Date: 1/17/13
 */
public class ReporterMigrator implements Migrator<ExpeditedAdverseEventReport> {
	
    public void migrate(ExpeditedAdverseEventReport aeReportSrc, ExpeditedAdverseEventReport aeReportDest, DomainObjectImportOutcome<ExpeditedAdverseEventReport> outcome) {
    	
    	Reporter srcReporter = aeReportSrc.getReporter();
    	if ( aeReportDest.getPhysician() == null ) aeReportDest.setReporter(new Reporter());
    	
    	 if(srcReporter == null ||  (StringUtils.isEmpty(srcReporter.getPrimaryIdentifierValue())  && StringUtils.isEmpty(srcReporter.getEmailAddress()) 
    			 && StringUtils.isEmpty(srcReporter.getFirstName()) && StringUtils.isEmpty(srcReporter.getLastName())))  {
             outcome.addWarning("ER-RM-1", "Physician is missing in the source");
             return;
         }
       
        	// 1. Load the study.
        Study study = aeReportDest.getStudy();
        	
        StudySite site = null;
        	
        if ( aeReportDest.getReportingPeriod() != null && aeReportDest.getReportingPeriod().getAssignment() != null ) {
        	site = aeReportDest.getReportingPeriod().getAssignment().getStudySite();
        }
        
        if ( site == null ) {
        	 outcome.addWarning("ER-PM-2", "Study Site is missing in the source");
             return;
        }
        
        	
        // 2. Retrieve the Research Staff from Study Site.
        SiteResearchStaff siteResearchStaff = null; 
    	
        siteResearchStaff = site.findSiteResearchStaffByIdentifier(srcReporter.getPrimaryIdentifierValue());
        	
        if ( siteResearchStaff == null ) {
        	siteResearchStaff = site.findSiteResearchStaffByEmail(srcReporter.getEmailAddress());
        }

        if ( siteResearchStaff == null ) {
        	siteResearchStaff = site.findSiteResearchStaffByName(srcReporter.getFirstName(), srcReporter.getLastName());
        }

       	if ( siteResearchStaff != null) {
        	// Copy the investigator details
        	copyFromSiteResearchStaffDetails(siteResearchStaff, aeReportDest.getReporter());
        		
        } else {
        		  outcome.addWarning("WR-PM-1", "Given Physician is no longer associated to the study");
        }
     	
        // 3. if investigator is not found create a new physician and do a Manual copy.
       	copyReporterDetails(srcReporter, aeReportDest.getReporter());	
    }
    
    /**
     *  Manual copy from Input object to Domain Model.
     * @param srcPhysician
     * @param destPhysician
     */
    
    public void copyReporterDetails(Reporter srcReporter, Reporter destReporter) {
         if (srcReporter.getTitle() != null) 
       	    destReporter.setTitle(srcReporter.getTitle());
       	 if (srcReporter.getVersion() != null)
            destReporter.setVersion(srcReporter.getVersion());
       	 if (srcReporter.getMiddleName() != null)
            destReporter.setMiddleName(srcReporter.getMiddleName());
       	 if (srcReporter.getLastName() != null)
            destReporter.setLastName(srcReporter.getLastName());
       	 if (srcReporter.getFirstName() != null)
            destReporter.setFirstName(srcReporter.getFirstName());
       	 if (srcReporter.getAddress() != null)
            destReporter.setAddress(srcReporter.getAddress());
       	 if (srcReporter.getEmailAddress() != null)
            destReporter.setEmailAddress(srcReporter.getEmailAddress());
       	 if (srcReporter.getPhoneNumber() != null)
            destReporter.setPhoneNumber(srcReporter.getPhoneNumber());
       	 if (srcReporter.getFaxNumber() != null)
            destReporter.setFaxNumber(srcReporter.getFaxNumber());
    }
    
    /**
     *  Manual copy from Input object to Domain Model.
     * @param srcPhysician
     * @param destPhysician
     */
    
    public void copyFromSiteResearchStaffDetails(SiteResearchStaff siteResearchStaff, Reporter destReporter) {
    	
   	 destReporter.setTitle( (siteResearchStaff.getTitle() != null ? siteResearchStaff.getTitle() : siteResearchStaff.getResearchStaff().getTitle())  );
     destReporter.setVersion((siteResearchStaff.getVersion() != null ? siteResearchStaff.getVersion() : siteResearchStaff.getResearchStaff().getVersion()));
     destReporter.setMiddleName((siteResearchStaff.getMiddleName() != null ? siteResearchStaff.getMiddleName() : siteResearchStaff.getResearchStaff().getMiddleName()));
     destReporter.setLastName((siteResearchStaff.getLastName() != null ? siteResearchStaff.getLastName() : siteResearchStaff.getResearchStaff().getLastName()));
     destReporter.setFirstName((siteResearchStaff.getFirstName() != null ? siteResearchStaff.getFirstName() : siteResearchStaff.getResearchStaff().getFirstName()));
     destReporter.setEmailAddress((siteResearchStaff.getEmailAddress() != null ? siteResearchStaff.getEmailAddress() : siteResearchStaff.getResearchStaff().getEmailAddress()));
     destReporter.setPhoneNumber((siteResearchStaff.getPhoneNumber() != null ? siteResearchStaff.getPhoneNumber() : siteResearchStaff.getResearchStaff().getPhoneNumber()));
     destReporter.setFaxNumber((siteResearchStaff.getFaxNumber() != null ? siteResearchStaff.getFaxNumber() : siteResearchStaff.getResearchStaff().getFaxNumber()));
     destReporter.setAddress(siteResearchStaff.getAddress());
     
     destReporter.setResearchStaff(siteResearchStaff.getResearchStaff());
         
    }
    
}
