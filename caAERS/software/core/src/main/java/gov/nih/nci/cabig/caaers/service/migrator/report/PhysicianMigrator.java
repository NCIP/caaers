package gov.nih.nci.cabig.caaers.service.migrator.report;

import org.apache.axis.utils.StringUtils;

import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.service.migrator.Migrator;

/**
 * User:medaV
 * Date: 1/14/13
 */
public class PhysicianMigrator implements Migrator<ExpeditedAdverseEventReport> {
	
    public void migrate(ExpeditedAdverseEventReport aeReportSrc, ExpeditedAdverseEventReport aeReportDest, DomainObjectImportOutcome<ExpeditedAdverseEventReport> outcome) {
    	
    	Physician srcPhysician = aeReportSrc.getPhysician();
    	if ( aeReportDest.getPhysician() == null ) aeReportDest.setPhysician(new Physician());
    	
    	 if(srcPhysician == null ||  (StringUtils.isEmpty(srcPhysician.getPrimaryIdentifierValue())  && StringUtils.isEmpty(srcPhysician.getEmailAddress()) 
    			 && StringUtils.isEmpty(srcPhysician.getFirstName()) && StringUtils.isEmpty(srcPhysician.getLastName())))  {
             outcome.addWarning("ER-PM-1", "Physician is missing in the source");
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
        	
        // 2. Follow the path Study to retrieve the Investigator. 
        SiteInvestigator siteInvestigator = null;
        
        if ( srcPhysician.getPrimaryIdentifierValue() != null ) {
        	siteInvestigator = site.findSiteInvestigatorByIdentifier(srcPhysician.getPrimaryIdentifierValue());
        }
        	
        if ( siteInvestigator == null  && srcPhysician.getEmailAddress() != null) {
        	siteInvestigator = site.findSiteInvestigatorByEmail(srcPhysician.getEmailAddress());
        }

        if ( siteInvestigator == null  && srcPhysician.getFirstName() != null && srcPhysician.getLastName() != null) {
        	siteInvestigator = site.findSiteInvestigatorByName(srcPhysician.getFirstName(), srcPhysician.getLastName());
        }

        if ( siteInvestigator != null) {
        	// Copy the investigator details
        	copyFromSiteInvestigatorDetails(siteInvestigator, aeReportDest.getPhysician());
        } else {
        		  outcome.addWarning("WR-PM-1", "Given Physician is no longer associated to the study");
        }
        		
        // 3. if investigator is not found create a new physician and do a Manual copy.
        copyPhysicianDetails(srcPhysician, aeReportDest.getPhysician());
        	
    }
    
    /**
     *  Manual copy from Input object to Domain Model.
     * @param srcPhysician
     * @param destPhysician
     */
    
    public void copyPhysicianDetails(Physician srcPhysician, Physician destPhysician) {
    	 if (srcPhysician.getTitle() != null) 
    	 destPhysician.setTitle(srcPhysician.getTitle());
    	 if (srcPhysician.getVersion() != null)
         destPhysician.setVersion(srcPhysician.getVersion());
    	 if (srcPhysician.getMiddleName() != null)
         destPhysician.setMiddleName(srcPhysician.getMiddleName());
    	 if (srcPhysician.getLastName() != null)
         destPhysician.setLastName(srcPhysician.getLastName());
    	 if (srcPhysician.getFirstName() != null)
         destPhysician.setFirstName(srcPhysician.getFirstName());
    	 if (srcPhysician.getAddress() != null)
         destPhysician.setAddress(srcPhysician.getAddress());
    	 if (srcPhysician.getEmailAddress() != null)
         destPhysician.setEmailAddress(srcPhysician.getEmailAddress());
    	 if (srcPhysician.getPhoneNumber() != null)
         destPhysician.setPhoneNumber(srcPhysician.getPhoneNumber());
    	 if (srcPhysician.getFaxNumber() != null)
         destPhysician.setFaxNumber(srcPhysician.getFaxNumber());
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
