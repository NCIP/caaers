/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.service.migrator.report;

import gov.nih.nci.cabig.caaers.domain.report.Report;
import org.apache.axis.utils.StringUtils;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.domain.repository.UserRepository;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.service.migrator.Migrator;

/**
 * User:medaV
 * Date: 1/17/13
 */
public class SubmitterMigrator implements Migrator<ExpeditedAdverseEventReport> {
	
	public void migrate(ExpeditedAdverseEventReport aeReportSrc, ExpeditedAdverseEventReport aeReportDest, DomainObjectImportOutcome<ExpeditedAdverseEventReport> outcome) {

    	Reporter srcReporter = aeReportSrc.getReporter();
    	
    	 if(srcReporter == null ||  (StringUtils.isEmpty(srcReporter.getPrimaryIdentifierValue())  && StringUtils.isEmpty(srcReporter.getEmailAddress()) 
    			 && StringUtils.isEmpty(srcReporter.getFirstName()) && StringUtils.isEmpty(srcReporter.getLastName())))  {
             outcome.addError("ER-SM-1", "Submitter is missing in the source");
             return;
         }
       
        	// 1. Load the study.
        Study study = aeReportDest.getStudy();
        	
        StudySite site = null;
        	
        if ( aeReportDest.getReportingPeriod() != null && aeReportDest.getReportingPeriod().getAssignment() != null ) {
        	site = aeReportDest.getReportingPeriod().getAssignment().getStudySite();
        }
        
        if ( site == null ) {
        	 outcome.addError("ER-SM-2", "Study Site is missing in the source");
             return;
        }
        
        // A reporter can be Research Staff or Investigator. The ResearchStaff is considered only when he is a ae_reporter. 
        
        // 2. Retrieve the Research Staff from Study Site.
        SiteResearchStaff siteResearchStaff = null; 
    	
        if ( srcReporter.getPrimaryIdentifierValue() != null) {
        	siteResearchStaff = site.findSiteResearchStaffByIdentifier(srcReporter.getPrimaryIdentifierValue());
        }
        	
        if ( siteResearchStaff == null && srcReporter.getEmailAddress() != null) {
        	siteResearchStaff = site.findSiteResearchStaffByEmail(srcReporter.getEmailAddress());
        }

        if ( siteResearchStaff == null  && ( srcReporter.getFirstName() != null && srcReporter.getLastName() != null )) {
        	siteResearchStaff = site.findSiteResearchStaffByName(srcReporter.getFirstName(), srcReporter.getLastName());
        }

       	if ( siteResearchStaff != null) {
        	// Copy the investigator details
       			
       		 if(siteResearchStaff.getResearchStaff().isUser()){
       			if ( aeReportDest.getReporter() != null) aeReportDest.setReporter(new Reporter());
       			copyFromSiteResearchStaffDetails(siteResearchStaff, aeReportDest.getReporter());
       		}
        		
        } else {
        
        	//3. If the Reporter is not a SiteResearchStaff, Then he could be a investigator.
        	
        	SiteInvestigator siteInvestigator = null;
            
            if ( srcReporter.getPrimaryIdentifierValue() != null ) {
            	siteInvestigator = site.findSiteInvestigatorByIdentifier(srcReporter.getPrimaryIdentifierValue());
            }
            	
            if ( siteInvestigator == null  && srcReporter.getEmailAddress() != null) {
            	siteInvestigator = site.findSiteInvestigatorByEmail(srcReporter.getEmailAddress());
            }

            if ( siteInvestigator == null  && srcReporter.getFirstName() != null && srcReporter.getLastName() != null) {
            	siteInvestigator = site.findSiteInvestigatorByName(srcReporter.getFirstName(), srcReporter.getLastName());
            }

            if ( siteInvestigator != null) {
            	// Copy the investigator details
            	if ( aeReportDest.getPhysician() != null) aeReportDest.setPhysician(new Physician());
            	copyFromSiteInvestigatorDetails(siteInvestigator, aeReportDest.getPhysician());
            } else {
            		  outcome.addWarning("WR-SM-1", "Given Submitter is no longer associated to the study");
            }
        	
        }
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
    
    /**
     *  Manual copy from Input object to Domain Model.
     * @param srcPhysician
     * @param destPhysician
     */
    
    public void copyFromSiteInvestigatorDetails(SiteInvestigator siteInvestigator, Physician destPhysician) {
    	 destPhysician.setTitle( (siteInvestigator.getTitle() != null ? siteInvestigator.getTitle() : siteInvestigator.getInvestigator().getTitle())  );
         destPhysician.setVersion((siteInvestigator.getVersion() != null ? siteInvestigator.getVersion() : siteInvestigator.getInvestigator().getVersion()));
         destPhysician.setMiddleName((siteInvestigator.getMiddleName() != null ? siteInvestigator.getMiddleName() : siteInvestigator.getInvestigator().getMiddleName()));
         destPhysician.setLastName((siteInvestigator.getLastName() != null ? siteInvestigator.getLastName() : siteInvestigator.getInvestigator().getLastName()));
         destPhysician.setFirstName((siteInvestigator.getFirstName() != null ? siteInvestigator.getFirstName() : siteInvestigator.getInvestigator().getFirstName()));
         destPhysician.setEmailAddress((siteInvestigator.getEmailAddress() != null ? siteInvestigator.getEmailAddress() : siteInvestigator.getInvestigator().getEmailAddress()));
         destPhysician.setPhoneNumber((siteInvestigator.getPhoneNumber() != null ? siteInvestigator.getPhoneNumber() : siteInvestigator.getInvestigator().getPhoneNumber()));
         destPhysician.setFaxNumber((siteInvestigator.getFaxNumber() != null ? siteInvestigator.getFaxNumber() : siteInvestigator.getInvestigator().getFaxNumber()));
         destPhysician.setAddress(siteInvestigator.getAddress());
         
         destPhysician.setInvestigator(siteInvestigator.getInvestigator());
         
    }
    
}
