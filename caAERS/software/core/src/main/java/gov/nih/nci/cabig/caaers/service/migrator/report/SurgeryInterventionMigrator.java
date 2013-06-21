/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.service.migrator.report;

import gov.nih.nci.cabig.caaers.dao.InterventionSiteDao;
import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.service.migrator.Migrator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * User:medaV
 * Date: 1/21/13
 */
public class SurgeryInterventionMigrator implements Migrator<ExpeditedAdverseEventReport> {

    public InterventionSiteDao getInterventionSiteDao() {
        return interventionSiteDao;
    }

    public void setInterventionSiteDao(InterventionSiteDao interventionSiteDao) {
        this.interventionSiteDao = interventionSiteDao;
    }

    private InterventionSiteDao interventionSiteDao;

    
	public void migrate(ExpeditedAdverseEventReport aeReportSrc, ExpeditedAdverseEventReport aeReportDest, DomainObjectImportOutcome<ExpeditedAdverseEventReport> outcome) {
		List<SurgeryIntervention> srcSurgeryInterventions = aeReportSrc.getSurgeryInterventions();
		List<SurgeryIntervention> destSurgeryInterventions = aeReportDest.getSurgeryInterventions();
    	
    	if ( srcSurgeryInterventions == null || srcSurgeryInterventions.size() == 0) {
    		outcome.addWarning("WR-SIM-1", "Input doesn't contain any SurgeryIntervention Values.");
    		return;
    	}
		
    	if ( destSurgeryInterventions == null ) {
    		destSurgeryInterventions = new ArrayList<SurgeryIntervention>();
    	}

        Study study = aeReportDest.getStudy();

        List<OtherIntervention> otherSurgeryList = study.getActiveStudySurgeries();
        List<String> iSites = new ArrayList<String>() ;
        for ( SurgeryIntervention surIntervention : srcSurgeryInterventions) {
            iSites.add(surIntervention.getInterventionSite().getName());
        }

        List<InterventionSite> interventionSitesList = loadInterventionSites(iSites);

    	// Copy the SurgeryInterventions Information from Source to Destination.
    	for ( SurgeryIntervention surIntervention : srcSurgeryInterventions) {
            // NOTE: As per discussion, Always pick the first Surgery Intervention defined on Study. -- To be Fixed.

            OtherIntervention oi = null;
            if ( otherSurgeryList.size() > 0) {
                oi =  otherSurgeryList.get(0);
            }

            if ( oi == null ) {
                outcome.addError("ER-SIM-1", "Study doesn't contain any Active Surgery Radiation." );
                break;
            }
            InterventionSite resultSite =   findInterventionSite(interventionSitesList,surIntervention.getInterventionSite().getName());
            if ( resultSite == null ) {
                outcome.addError("ER-SIM-2", "Intervention Site is not found for " + surIntervention.getInterventionSite().getName() );
                break;
            }
            validateSurgeyInterventionDates(surIntervention, outcome);
            SurgeryIntervention destSurgeryIntervention = new SurgeryIntervention();
            destSurgeryIntervention.setInterventionSite(resultSite);
            destSurgeryIntervention.setStudySurgery(oi);
            destSurgeryIntervention.setReport(aeReportDest);
    		copyProperties(surIntervention, destSurgeryIntervention);
    		destSurgeryInterventions.add(destSurgeryIntervention);

    	}
	}

    /**
     * Load intervention Sites.
     */
     private  List<InterventionSite> loadInterventionSites(List<String> interventionSites) {

         String[] sitesArray = interventionSites.toArray(new String[interventionSites.size()]);
         List<InterventionSite> resultSites = interventionSiteDao.getBySubname(sitesArray);

         return resultSites;

     }

    /**
     * find Intervention Site from the List.
     */
    private InterventionSite findInterventionSite(List<InterventionSite> interventionSitesList, String siteName) {
           InterventionSite result  = null;
           for (InterventionSite site :  interventionSitesList) {
              if ( site.getName().equals(siteName)) {
                  result = site;
                  break;
              }
           }
           return result;
    }


    /**
	 * Copy the Details from the UserInput.
	 * @param src
	 * @param dest
	 */
	private void copyProperties(SurgeryIntervention src, SurgeryIntervention dest) {
		if (src.getDescription() != null)
            dest.setDescription(src.getDescription());
        if (src.getInterventionDate() != null)
		    dest.setInterventionDate(src.getInterventionDate());
        if (src.getTreatmentArm() != null)
		    dest.setTreatmentArm(src.getTreatmentArm());
	}
	
	/**
	 * Validate SurgeyIntervention Dates.
	 * @param surgeryIntervention
	 * @param outcome
	 */
	private void validateSurgeyInterventionDates(SurgeryIntervention surgeryIntervention,  DomainObjectImportOutcome<ExpeditedAdverseEventReport> outcome){
		if(surgeryIntervention.getInterventionDate() != null && surgeryIntervention.getInterventionDate().after(new Date())){
			outcome.addError("ER-SIM-3", "'Surgery Intervention date' cannot be a future date.");
		}
	}
}
