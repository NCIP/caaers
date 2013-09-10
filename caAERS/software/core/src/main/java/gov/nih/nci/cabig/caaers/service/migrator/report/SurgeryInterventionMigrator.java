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
    		outcome.addWarning("WR-SIM-1", "Input doesn't contain any Surgery Intervention Values.");
    		return;
    	}
    	
    	if ( srcSurgeryInterventions.size() > 1) {
    		outcome.addError("ER-SIM-1", "Input contains more than one Surgery Intervention");
    		return;
    	}
		
    	if ( destSurgeryInterventions == null ) {
    		destSurgeryInterventions = new ArrayList<SurgeryIntervention>();
    	}

        Study study = aeReportDest.getStudy();

        List<OtherIntervention> otherSurgeryList = study.getActiveStudySurgeries();
        if ( srcSurgeryInterventions.size() > 0 && ( otherSurgeryList == null || otherSurgeryList.isEmpty() ) ) {
            outcome.addError("ER-SIM-2", "Study doesn't contain any Active Surgery Intervention." );
            return;
        } 
        
        OtherIntervention oi = otherSurgeryList.get(0);
    	// Copy the SurgeryInterventions Information from Source to Destination.
    	for ( SurgeryIntervention surIntervention : srcSurgeryInterventions) {
    		            
            InterventionSite resultSite = findInterventionSite(surIntervention.getInterventionSite(), outcome);
            if ( outcome.hasErrors()) {
                return;
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
     * find Intervention Site from the List.
     */
    private InterventionSite findInterventionSite(InterventionSite site, DomainObjectImportOutcome<ExpeditedAdverseEventReport> outcome) {
                      
           List<InterventionSite> resultLst = interventionSiteDao.searchByExampleIgnoreCase(site, false);
           if(resultLst == null || resultLst.isEmpty()) {
           	outcome.addError("ER-SIM-3", "Matching surgery intervention site is not found for " + site.getName());
           	return null;
           }
           if(resultLst.size() > 1 ) {
           	outcome.addError("ER-SIM-4", "Multiple matching suregery intervention sites found for " + site.getName());
           	return null;
           }
           return resultLst.get(0);
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
			outcome.addError("ER-SIM-5", "'Surgery Intervention date' cannot be a future date.");
		}
	}
}
